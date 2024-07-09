package com.lipari.events.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lipari.events.exceptions.EnumDeserializer;

@JsonDeserialize(using = EnumDeserializer.class)
public enum ECategory {
	CONCERT,
	THEATRE,
	SPORT,
	EXHIBITIONS_AND_MUSEUMS,
	CINEMA,
	INTERNATIONAL_EVENT,
	OTHER_EVENT
}
