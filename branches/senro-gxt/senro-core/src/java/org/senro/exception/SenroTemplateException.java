package org.senro.exception;

public class SenroTemplateException extends SenroBaseException {
	private static final long serialVersionUID = 1L;

	public SenroTemplateException(String message) {
		super(message);
	}

	public SenroTemplateException(Exception exception) {
		super(exception);
	}
}
