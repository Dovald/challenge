package com.avoris.challenge.exception;

public class InvalidParametersException extends RuntimeException{

	private static final long serialVersionUID = 1363849454906985289L;
	
	public InvalidParametersException(String errorMessage) {
        super(errorMessage);
    }

}
