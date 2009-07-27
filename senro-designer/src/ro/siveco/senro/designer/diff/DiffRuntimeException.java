package ro.siveco.senro.designer.diff;

import ro.siveco.senro.designer.DesignerRuntimeException;

public class DiffRuntimeException extends DesignerRuntimeException
{
    public DiffRuntimeException()
    {
    }

    public DiffRuntimeException(String message)
    {
        super(message);
    }

    public DiffRuntimeException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public DiffRuntimeException(Throwable cause)
    {
        super(cause);
    }

}
