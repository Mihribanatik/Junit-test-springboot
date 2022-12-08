package com.test.springboot.exception;

public class ResourceNotFountException extends RuntimeException{

	public ResourceNotFountException(String message){
		super(message);
	}

	public ResourceNotFountException(String message, Throwable cause){
		super(message,cause);
	}
}
