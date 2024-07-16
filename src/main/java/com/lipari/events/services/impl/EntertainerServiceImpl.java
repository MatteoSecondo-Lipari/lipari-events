package com.lipari.events.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lipari.events.entities.EntertainerEntity;
import com.lipari.events.mappers.EntertainerMapper;
import com.lipari.events.models.EntertainerDTO;
import com.lipari.events.models.EntertainerNNEventsDTO;
import com.lipari.events.models.EventStatsDashboardDTO;
import com.lipari.events.models.constraints.EntertainerConstraintsDTO;
import com.lipari.events.repositories.EntertainerRepository;
import com.lipari.events.services.EntertainerService;

@Service
public class EntertainerServiceImpl implements EntertainerService {
	
	@Autowired
	EntertainerRepository entertainerRepository;
	
	@Autowired
	EntertainerMapper entertainerMapper;

	@Override
	public EntertainerDTO createOrUpdateEntertainer(EntertainerConstraintsDTO entertainer) {
		EntertainerEntity ee = entertainerMapper.costraintsDtoToEntity(entertainer);
		return entertainerMapper.entityToDto(entertainerRepository.save(ee));
	}

	@Override
	public List<EntertainerDTO> getAllEntertainers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntertainerDTO getEntertainerById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EntertainerDTO> getEntertainerByStageName(String stageName) {
		return entertainerRepository.getByStageNameStartingWith(stageName).stream()
				.map(entertainerMapper::entityToDto).toList();
	}
	
	@Override
	public List<EntertainerNNEventsDTO> getEventWithEntertainers(String entertainers) {
		return entertainerRepository.getByStageNameStartingWith(entertainers).stream()
				.map(entertainerMapper::entityNNToDto).toList();
	}
	
	public List<EventStatsDashboardDTO> getEventStatistics(long event_id) {
        List<Object[]> results = entertainerRepository.getEventStatistics(event_id);
        
        // Converte in stream di dati, mappa questo results con la funzione e la ritorna in formato lista.
        return results.stream().map(this::mapToEventStatsDashboardDTO).toList();
        
        //Object[] diventa EventStatsDashboardDTO e con .toList diventa List<EventStatsDashboardDTO>
    }

	//Molto Molto brutto (ma funziona)
    public EventStatsDashboardDTO mapToEventStatsDashboardDTO(Object[] result) {
        return new EventStatsDashboardDTO(
            ((Number) result[0]).longValue(),  // event_id
            (String) result[1],                // event_name
            ((Number) result[2]).floatValue(), // seats_price
            ((Number) result[3]).floatValue(), // stand_price
            ((Number) result[4]).intValue(),   // location_seats_capacity
            ((Number) result[5]).intValue(),   // location_max_capacity
            ((Number) result[6]).longValue(),  // tickets_sold
            ((Number) result[7]).longValue(),  // remaining_tickets
            ((Number) result[8]).longValue(),  // number_of_seats_tickets_sold
            ((Number) result[9]).longValue(),  // number_of_standing_tickets_sold
            ((Number) result[10]).floatValue() // total_revenue
        );
    }

}
