package ro.siveco.senro.designer;

public class DesignerException extends Exception
{

    public DesignerException()
    {
    }

    public DesignerException(String message)
    {
        super(message);
    }

    public DesignerException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public DesignerException(Throwable cause)
    {
        super(cause);
    }
}
