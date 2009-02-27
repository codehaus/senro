package ro.siveco.senro.designer.objects;

public class ContextFragmentDescription extends SCDescription
{
    private static final long serialVersionUID = 1;

    public static final String OBJECT_CLASS_NAME = "ContextFragment";
    public static final String CONTEXT_FRAGMENT_DEFAULT_NAME = "Context Fragment";

    public ContextFragmentDescription()
    {
        canBeDeleted = false;
        setName(CONTEXT_FRAGMENT_DEFAULT_NAME);
        setId(CONTEXT_FRAGMENT_DEFAULT_NAME);
    }

    public String getObjectClassName()
    {
        return OBJECT_CLASS_NAME;
    }

    public void setCanBeDeleted(boolean canBeDeleted)
    {
    }

}
