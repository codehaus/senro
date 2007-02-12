package org.senro.exception;

public class SenroBaseException extends Exception
{
	public SenroBaseException(Exception ex) {
		super(ex);
	}

	public SenroBaseException(String message) {
		super(message);
	}
}
