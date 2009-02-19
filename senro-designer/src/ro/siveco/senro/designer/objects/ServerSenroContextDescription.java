package ro.siveco.senro.designer.objects;

public class ServerSenroContextDescription extends SenroContextDescription
{
    private static final long serialVersionUID = 1;

    public static final String OBJECT_CLASS_NAME = "ServerSenroContext";

    public String getObjectClassName()
    {
        return OBJECT_CLASS_NAME;
    }

    public boolean canBeDeleted()
    {
        return false;
    }
}
