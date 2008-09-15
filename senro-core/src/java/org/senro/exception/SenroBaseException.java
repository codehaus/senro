package org.senro.exception;

/**
 * @author Flavius Burca
 *
 */
public class SenroBaseException extends Exception
{
	private static final long serialVersionUID = 1L;

	public SenroBaseException(Exception ex) {
		super(ex);
	}

	public SenroBaseException(String message) {
		super(message);
	}
}
