package com.depromeet.watni.exception;

public class BadRequestException extends RuntimeException {

	private BadRequestException() { }

	public BadRequestException(String message) {
		super(message);
	}
}
