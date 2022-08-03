package com.clepalhome.vendas.services.exception;

public class EmptyDataAccessException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public EmptyDataAccessException(String msg) {
		super(msg);
	}
	
	public EmptyDataAccessException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
