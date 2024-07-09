package com.lipari.events.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lipari.events.exceptions.EnumDeserializer;

@JsonDeserialize(using = EnumDeserializer.class)
public enum ERole {
	ROLE_ADMIN,
	ROLE_ENTERTAINER,
	ROLE_CUSTOMER
}
