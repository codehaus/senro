package ro.siveco.senro.designer.diff;

import ro.siveco.senro.designer.DesignerException;

public class DiffException extends DesignerException
{
    public DiffException()
    {
    }

    public DiffException(String message)
    {
        super(message);
    }

    public DiffException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public DiffException(Throwable cause)
    {
        super(cause);
    }

}