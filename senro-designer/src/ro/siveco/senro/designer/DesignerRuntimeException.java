package ro.siveco.senro.designer;

public class DesignerRuntimeException extends RuntimeException
{

    public DesignerRuntimeException()
    {
    }

    public DesignerRuntimeException(String message)
    {
        super(message);
    }

    public DesignerRuntimeException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public DesignerRuntimeException(Throwable cause)
    {
        super(cause);
    }
}
