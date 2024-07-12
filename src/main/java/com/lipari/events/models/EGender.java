package com.lipari.events.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lipari.events.exceptions.EGenderDeserializer;

@JsonDeserialize(using = EGenderDeserializer.class)
public enum EGender {
	M,
	F,
	NOT_SPECIFIED
}
