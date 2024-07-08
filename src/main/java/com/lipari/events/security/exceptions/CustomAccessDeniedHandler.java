package com.lipari.events.security.exceptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		logger.error("Unauthorized error: {}", accessDeniedException.getMessage());
		
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);

		final Map<String, Object> body = new HashMap<>();
	    body.put("status", HttpServletResponse.SC_FORBIDDEN);
	    body.put("error", "Unauthorized");
	    body.put("message", accessDeniedException.getMessage());
	    body.put("path", request.getServletPath());

	    final ObjectMapper mapper = new ObjectMapper();
	    mapper.writeValue(response.getOutputStream(), body);
	}

}
