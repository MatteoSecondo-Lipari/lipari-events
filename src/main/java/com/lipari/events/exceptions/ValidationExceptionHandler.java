package com.lipari.events.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ValidationExceptionHandler {

	@ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleConstraintViolationException(ConstraintViolationException ex) {
		
		final Map<String, Object> body = new HashMap<>();

		body.put("status", HttpStatus.BAD_REQUEST.value());
	    body.put("error", "Bad Request");
		
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
        	body.put(violation.getPropertyPath().toString(), violation.getMessage());
        }
        
        return body;
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        final Map<String, Object> body = new HashMap<>();

        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Bad Request");

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            body.put(error.getField(), error.getDefaultMessage());
        }

        return body;
    }
	
	@ExceptionHandler({ CustomJsonParseException.class, MultipartException.class,
		FileUploadException.class, HttpMediaTypeNotSupportedException.class, MissingServletRequestPartException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleEnumViolationException(Exception ex) {
		final Map<String, Object> body = new HashMap<>();

		body.put("status", HttpStatus.BAD_REQUEST.value());
	    body.put("error", "Bad Request");
	    body.put("message", ex.getMessage());
  
        return body;
	}
	
}
