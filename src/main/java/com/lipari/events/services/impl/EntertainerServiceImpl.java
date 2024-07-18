package com.lipari.events.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lipari.events.entities.EntertainerEntity;
import com.lipari.events.entities.LocationEntity;
import com.lipari.events.mappers.EntertainerMapper;
import com.lipari.events.models.EntertainerDTO;
import com.lipari.events.models.EntertainerNNEventsDTO;
import com.lipari.events.models.EventStatsDashboardDTO;
import com.lipari.events.models.constraints.EntertainerConstraintsDTO;
import com.lipari.events.repositories.EntertainerRepository;
import com.lipari.events.services.EntertainerService;
import com.stripe.StripeClient;
import com.stripe.exception.StripeException;
import com.stripe.model.Account;
import com.stripe.model.AccountLink;
import com.stripe.param.AccountCreateParams;
import com.stripe.param.AccountLinkCreateParams;

@Service
public class EntertainerServiceImpl implements EntertainerService {
	
	@Autowired
	EntertainerRepository entertainerRepository;
	
	@Autowired
	EntertainerMapper entertainerMapper;

	@Override
	public EntertainerDTO createEntertainer(EntertainerConstraintsDTO entertainer) {
		EntertainerEntity ee = entertainerMapper.costraintsDtoToEntity(entertainer);
		return entertainerMapper.entityToDto(entertainerRepository.save(ee));
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

	@Override
	public Account createStripeAccount() throws StripeException {
		StripeClient client = new StripeClient("sk_test_51OrGCUGjZ7RLeJMqT3ykVjC3DJmA0w3YxBCsBRJEmqpo6U493CM8368ug0bWxxjQqimkU30mi0ZSq9Y89PFWedqS00PRErjXsK");

		AccountCreateParams params =
		  AccountCreateParams.builder().setType(AccountCreateParams.Type.EXPRESS).build();

		return client.accounts().create(params);
	}

	@Override
	public AccountLink linkToOnboarding(String accountId) throws StripeException {
		StripeClient client = new StripeClient("sk_test_51OrGCUGjZ7RLeJMqT3ykVjC3DJmA0w3YxBCsBRJEmqpo6U493CM8368ug0bWxxjQqimkU30mi0ZSq9Y89PFWedqS00PRErjXsK");

		AccountLinkCreateParams params =
		  AccountLinkCreateParams.builder()
		    .setAccount(accountId)
		    .setRefreshUrl("http://localhost:4200")
		    .setReturnUrl("http://localhost:4200")
		    .setType(AccountLinkCreateParams.Type.ACCOUNT_ONBOARDING)
		    .build();

		return client.accountLinks().create(params);
	}
	
	
	@Override
	public List<EntertainerDTO> getAll() {
		return entertainerRepository.findAll().stream()
				.map(entertainerMapper::entityToDto).toList();
	}



	@Override
	public EntertainerDTO getById(long id) {
		return entertainerRepository.findById(id).map(entertainerMapper::entityToDto).orElseThrow();
		
	}

	@Override
	public EntertainerDTO update(EntertainerEntity entity) {
		if(entertainerRepository.existsById(entity.getId()))
		{
			EntertainerEntity addsevent = entertainerRepository.save(entity);
			return entertainerMapper.entityToDto(addsevent);
		}
		else {
			return null;
		}
		
	}

	@Override
	public boolean delete(long id) {
		EntertainerEntity entity = entertainerRepository.findById(id).orElseThrow();
		if(entity != null)
		{
			entertainerRepository.deleteById(id);
			return true;
		}
		else {
			return false;
		}
		
	}

}
