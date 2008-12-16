package org.senro.gwt.client.exception;

import java.io.Serializable;

/**
 * Generic UI Rendering exception.
 * This exception is supposed to be thrown whenever a UI rendering problem occurs.
 * It is also a support for delivering server-side exception messages to the client.
 */
public class SenroUIException extends Exception implements Serializable {
	private String message;
	
	public SenroUIException() {
	}
	
	/**
     * Constructs a new {@link SenroUIException} with the specified cause and a detail
     * message of <tt>(cause==null ? null : cause.toString())</tt> (which
     * typically contains the class and detail message of <tt>cause</tt>).
     * This constructor is useful for exceptions that are little more than
     * wrappers for other throwables.
     *
     * @param  cause the cause (which is saved for later retrieval by the
     *         {@link #getCause()} method).  (A <tt>null</tt> value is
     *         permitted, and indicates that the cause is nonexistent or
     *         unknown.)
     */
	public SenroUIException(Throwable cause) {
		super(cause);
	}
	
	/**
     * Constructs a new {@link SenroUIException} with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * <p>The {@link #fillInStackTrace()} method is called to initialize
     * the stack trace data in the newly created exception.
     *
     * @param   message   the detail message. The detail message is saved for
     *          later retrieval by the {@link #getMessage()} method.
     */
	public SenroUIException(String message) {
		super(message);
		this.message = message;
	}
	
	/**
     * Constructs a new throwable with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * <code>cause</code> is <i>not</i> automatically incorporated in
     * this throwable's detail message.
     *
     * <p>The {@link #fillInStackTrace()} method is called to initialize
     * the stack trace data in the newly created throwable.
     *
     * @param  message the detail message (which is saved for later retrieval
     *         by the {@link #getMessage()} method).
     * @param  cause the cause (which is saved for later retrieval by the
     *         {@link #getCause()} method).  (A <tt>null</tt> value is
     *         permitted, and indicates that the cause is nonexistent or
     *         unknown.)
     */
	public SenroUIException(String message, Throwable cause) {
		super(message);
		this.message = message;
		initCause(cause);
	}
	
	/**
     * Returns the detail message string of this throwable.
     *
     * @return  the detail message string of this {@link SenroUIException} instance
     *          (which may be <tt>null</tt>).
     */
	@Override
	public String getMessage() {
		return message;
	}
}
