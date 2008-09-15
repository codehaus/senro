package org.senro.gwt.client.exception;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author Flavius Burca
 *
 */
public class SenroUIException extends Exception implements IsSerializable, Serializable {
	private String message;
	
	public SenroUIException() {
	}
	
	public SenroUIException(Throwable e) {
		super(e);
	}
	
	public SenroUIException(String message) {
		super(message);
		this.message = message;
	}
	
	public SenroUIException(String message, Throwable e) {
		super(message);
		this.message = message;
		initCause(e);
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
