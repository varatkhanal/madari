package com.madari.exceptions;

public enum ErrorMessages {
	MISSING_REQUIRED_FIELD("Missing required field, Please check documentation for required fields"),
	RECORD_ALREADY_EXIST("Record already exist"),
	INTERNAL_SERVER_ERROR("Internal server error"),
	NO_RECORD_FOUND("Record with provided id is not found"),
	AUTHENTICATION_FAILED("Authentication failed"),
	COULD_NOT_UPDATE_RECORD("could not update record"),
	COULD_NOT_DELETE_RECORD("could not delete record"),
	EMAIL_ADDRESS_NOT_VARIFIED("Email address could not be varified");
	
	private String errorMessage;
	
	ErrorMessages(String errorMessage){
		this.errorMessage=errorMessage;
	}
	
	public String getErrorMessage() {
		
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		
		this.errorMessage=errorMessage;
	}
	
}
