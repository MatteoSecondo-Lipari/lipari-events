package com.lipari.events.controllers;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.lipari.events.entities.EntertainerEntity;
import com.lipari.events.mappers.EntertainerMapper;
import com.lipari.events.models.ERole;
import com.lipari.events.models.EntertainerDTO;
import com.lipari.events.models.EventDTO;

import com.lipari.events.models.EntertainerNNEventsDTO;
import com.lipari.events.models.EventWithSubcategoryWithoutloopDTO;
import com.lipari.events.models.SearchResultsDTO;

import com.lipari.events.models.constraints.EventConstraintsDTO;
import com.lipari.events.models.constraints.EventsEntertainersConstraintsDTO;
import com.lipari.events.payload.MessageResponse;

import com.lipari.events.repositories.UserRepository;
import com.lipari.events.security.user_details.UserDetailsImpl;

import com.lipari.events.services.EntertainerService;

import com.lipari.events.services.EventService;
import com.lipari.events.services.EventsEntertainersService;
import com.lipari.events.services.ImageService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/event")
public class EventController {
	
	@Autowired
	EventService eventService;
	
	@Autowired
	ImageService imageService;
	
	@Autowired
	EventsEntertainersService eventsEntertainersService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	EntertainerMapper entertainerMapper;

	@Autowired
	EntertainerService entertainerService;


	@PreAuthorize("hasAnyRole('ROLE_ENTERTAINER', 'ROLE_ADMIN')")
	@PostMapping(path = "/save", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<?> createEvent(
			@RequestPart MultipartFile image,
			@RequestPart @Valid EventConstraintsDTO event) {

		UserDetailsImpl userDetailsImpl = (UserDetailsImpl)SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		String authority = userDetailsImpl.getAuthorities()
				.stream().findFirst().get().getAuthority();

		if(authority.equals(ERole.ROLE_ENTERTAINER.name())) {
			//adding current user to entertainers
			if(event.getEntertainers() == null) {
				event.setEntertainers(new ArrayList<EntertainerDTO>());
			}

			String email = userDetailsImpl.getEmail();

			EntertainerEntity entertainerE = userRepository.findByEmail(email).get().getEntertainer();
			EntertainerDTO entertainer = entertainerMapper.entityToDto(entertainerE); 

			event.getEntertainers().add(entertainer);
		} else {
			if(event.getEntertainers() == null) {
				return ResponseEntity.badRequest().body(
						new MessageResponse("entertainers: Must be not empty", 400));
			}
		}

		//entertainers stripe account validation
		try {
			if(!eventService.isPresentEntertainersStripeAccount(event.getEntertainers())) {
				return ResponseEntity.badRequest().body(
						new MessageResponse("Some entertainers hasn't any stripe account connected", 400));
			}
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new MessageResponse("Entertainer not found", 404));
		}

		//image validation
		String path = null;
		boolean isValid = image.getContentType() == null || (image.getContentType().equals("image/jpeg") ||
				image.getContentType().equals("image/jpg"));

		if(!image.isEmpty() && isValid) {
			try {
				path = imageService.saveImage(image);
			} catch (IOException e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(
						new MessageResponse("Something went wrong uploading image", HttpStatus.INTERNAL_SERVER_ERROR.value()));
			}
		} else if(!image.isEmpty() && !isValid) {
			return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(
					new MessageResponse("Only JPEG and JPG files are allowed", HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()));
		}

		EventWithSubcategoryWithoutloopDTO newEvent =  eventService.createOrUpdateEvent(event, path);

		//associate entertainers to this new event
		event.getEntertainers().forEach(e ->
		eventsEntertainersService.createOrUpdate(
				new EventsEntertainersConstraintsDTO(newEvent, e, 100 / event.getEntertainers().size()))
		);

		return ResponseEntity.ok(newEvent);
	}

	@GetMapping("/{id}/image")
	public ResponseEntity<?> getEventImage(@PathVariable long id) {
		try {
			String imagePath = eventService.getEventDTOById(id).getImagePath();
			Path filePath = imageService.getImagePath(imagePath);
			Resource resource = new UrlResource(filePath.toUri());

			if (resource.exists() || resource.isReadable()) {
				return ResponseEntity.ok()
						.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
						.contentType(MediaType.IMAGE_JPEG)
						.body(resource);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
						new MessageResponse("Image not existing", HttpStatus.NOT_FOUND.value()));
			}
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new MessageResponse("Event not existing", HttpStatus.NOT_FOUND.value()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
					new MessageResponse("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR.value()));
		}
	}

	@GetMapping("/searchbar/{search}")
	public ResponseEntity<SearchResultsDTO> search(@PathVariable String search) {
		List<EventWithSubcategoryWithoutloopDTO> events = eventService.getEventWithName(search);
		List<EntertainerNNEventsDTO> entertainers = entertainerService.getEventWithEntertainers(search);

		if (events.isEmpty() && entertainers.isEmpty()) {
			SearchResultsDTO nothing = new SearchResultsDTO(events, entertainers);
			return ResponseEntity.ok(nothing);
		}

		SearchResultsDTO results = new SearchResultsDTO(events, entertainers);
		return ResponseEntity.ok(results);
	}

	@GetMapping("/newest")
	public List<EventWithSubcategoryWithoutloopDTO> newest(){
		return eventService.getTop20newestEvents();
	}

	@GetMapping("/all")
	public List<EventWithSubcategoryWithoutloopDTO> getAllEvents() {
		return eventService.getAllEvents();
	}
	
	@GetMapping("/{id}")
	public EventWithSubcategoryWithoutloopDTO getEventById(@PathVariable long id) {
		return eventService.getEventWithSubcategoryWithoutloopDTOById(id);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PutMapping("/update")
	public EventWithSubcategoryWithoutloopDTO updateEvent(@RequestBody EventConstraintsDTO event) {
		return eventService.createOrUpdateEvent(event, null);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> deleteEvent(@PathVariable long id) {

		if(!eventService.deleteEvent(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new MessageResponse("Event not existing", 404));
		}
		
		return ResponseEntity.ok().body(
				new MessageResponse("Event deleted successfully", 200));
	}

}
