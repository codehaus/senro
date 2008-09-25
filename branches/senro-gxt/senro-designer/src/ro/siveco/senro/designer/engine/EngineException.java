package ro.siveco.senro.designer.engine;

import ro.siveco.senro.designer.DesignerException;

public class EngineException extends DesignerException
{
    public EngineException()
    {
    }

    public EngineException(String message)
    {
        super(message);
    }

    public EngineException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public EngineException(Throwable cause)
    {
        super(cause);
    }

}

