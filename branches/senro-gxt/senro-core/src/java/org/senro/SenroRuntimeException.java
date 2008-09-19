package org.senro;

/**
 * @author Flavius Burca
 *
 */
public class SenroRuntimeException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public SenroRuntimeException()
    {
        super();
    }

    public SenroRuntimeException(String message)
    {
        super(message);
    }

    public SenroRuntimeException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public SenroRuntimeException(Throwable cause)
    {
        super(cause);
    }

}
