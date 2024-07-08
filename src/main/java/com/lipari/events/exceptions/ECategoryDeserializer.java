package com.lipari.events.exceptions;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.lipari.events.entities.ECategory;

public class ECategoryDeserializer extends JsonDeserializer<ECategory>{

	@Override
	public ECategory deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JacksonException {
		String value = p.getText();
				
        try {
            return ECategory.valueOf(value);
        } catch (IllegalArgumentException e) {
        	String message = "Invalid value for category: " + value + ". Expected values: ";
        	
        	for (ECategory c : ECategory.values()) {
				message += c.name() + ", ";
			}
        	
            throw new CustomJsonParseException(message);
        }
	}

	
}
