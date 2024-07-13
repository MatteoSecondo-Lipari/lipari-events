package com.lipari.events.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lipari.events.entities.EntertainerEntity;
import com.lipari.events.mappers.EntertainerMapper;
import com.lipari.events.models.EntertainerDTO;
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
	public Account createStripeAccount() throws StripeException {
		StripeClient client = new StripeClient("sk_test_51OrGCUGjZ7RLeJMqT3ykVjC3DJmA0w3YxBCsBRJEmqpo6U493CM8368ug0bWxxjQqimkU30mi0ZSq9Y89PFWedqS00PRErjXsK");

		AccountCreateParams params =
		  AccountCreateParams.builder().setType(AccountCreateParams.Type.EXPRESS).build();

		return client.accounts().create(params);
	}

	@Override
	public AccountLink linkToStripeAccount(String accountId) throws StripeException {
		StripeClient client = new StripeClient("sk_test_51OrGCUGjZ7RLeJMqT3ykVjC3DJmA0w3YxBCsBRJEmqpo6U493CM8368ug0bWxxjQqimkU30mi0ZSq9Y89PFWedqS00PRErjXsK");

		AccountLinkCreateParams params =
		  AccountLinkCreateParams.builder()
		    .setAccount(accountId)
		    .setRefreshUrl("https://google.com")
		    .setReturnUrl("https://facebook.com")
		    .setType(AccountLinkCreateParams.Type.ACCOUNT_ONBOARDING)
		    .build();

		return client.accountLinks().create(params);
	}

}
