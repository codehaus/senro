package ro.siveco.senro.designer.util;

import ro.siveco.senro.designer.DesignerException;

public class XmlException extends DesignerException
{

    public XmlException()
    {
    }

    public XmlException(String message)
    {
        super(message);
    }

    public XmlException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public XmlException(Throwable cause)
    {
        super(cause);
    }
}
