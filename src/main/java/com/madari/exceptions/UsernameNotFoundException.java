package com.madari.exceptions;

public class UsernameNotFoundException extends RuntimeException{
	UsernameNotFoundException(String message){
		super(message);
	}
}
