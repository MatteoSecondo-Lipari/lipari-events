package com.lipari.events.exceptions;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.lipari.events.models.EGender;

public class EGenderDeserializer extends JsonDeserializer<EGender>{
	
	@Override
	public EGender deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JacksonException {
		String value = p.getText();
				
        try {
            return EGender.valueOf(value);
        } catch (IllegalArgumentException e) {
        	String message = "Invalid value for enum: " + value;
        	
            throw new CustomJsonParseException(message);
        }
	}

}
