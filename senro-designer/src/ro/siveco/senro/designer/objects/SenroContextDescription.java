package ro.siveco.senro.designer.objects;

public class SenroContextDescription extends ObjectDescription
{
    public static final String OBJECT_CLASS_NAME = "SenroContextDescription";

    public String getObjectClassName()
    {
        return OBJECT_CLASS_NAME;
    }

     public boolean canBeDeleted()
    {
        return false;
    }
}
