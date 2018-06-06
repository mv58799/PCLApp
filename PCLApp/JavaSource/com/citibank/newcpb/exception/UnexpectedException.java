package com.citibank.newcpb.exception;

public class UnexpectedException extends RuntimeException {

	public UnexpectedException(int code_, String message_) {
		super("[" + code_ + "] " + message_);
	}

	public UnexpectedException(String message_) {
		super(message_);
	}

	public UnexpectedException(int code_, String message_, Throwable rootCause_) {
		super(rootCause_.getMessage() + " - [" + code_ + "] " + message_ + " - " + rootCause_.getClass().getName(),
				rootCause_);
	}

	public UnexpectedException(String message_, Throwable rootCause_) {
		super(message_ + " - " + rootCause_.getClass().getName(), rootCause_);
	}
}