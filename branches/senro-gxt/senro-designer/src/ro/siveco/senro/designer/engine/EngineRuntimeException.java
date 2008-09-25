package ro.siveco.senro.designer.engine;

import ro.siveco.senro.designer.DesignerRuntimeException;

public class EngineRuntimeException extends DesignerRuntimeException
{
    public EngineRuntimeException()
    {
    }

    public EngineRuntimeException(String message)
    {
        super(message);
    }

    public EngineRuntimeException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public EngineRuntimeException(Throwable cause)
    {
        super(cause);
    }

}
