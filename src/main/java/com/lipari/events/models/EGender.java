package com.lipari.events.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lipari.events.exceptions.EnumDeserializer;

@JsonDeserialize(using = EnumDeserializer.class)
public enum EGender {
	M,
	F,
	NOT_SPECIFIED
}
