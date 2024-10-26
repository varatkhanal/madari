package com.madari.exceptions;

public class TopicServiceException extends RuntimeException{
private static final long serialVersionUID = 1L;
	
	public TopicServiceException(String message) {
		super(message);
	}
}
