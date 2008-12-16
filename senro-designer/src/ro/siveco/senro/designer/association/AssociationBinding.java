package ro.siveco.senro.designer.association;

public class AssociationBinding
{
    public static final int ON_CLICK        = 1;
    public static final int ON_DOUBLE_CLICK = 2;
    public static final int ON_MOUSE_DOWN   = 4;
    public static final int ON_MOUSE_UP     = 8;
    public static final int ON_MOUSE_OVER   = 16;
    public static final int ON_MOUSE_OUT    = 32;
    public static final int ON_MOUSE_MOVE   = 64;
    public static final int ON_KEY_DOWN     = 128;
    public static final int ON_KEY_UP       = 512;
    public static final int ON_BLUR         = 140;
    public static final int ON_CHANGE       = 170;
    public static final int ON_FOCUS        = 380;
    public static final int ON_VALID        = 710;

    private String name;
    private String value;
    private int event;

    public AssociationBinding(String name)
    {
        this.name = name;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public int getEvent()
    {
        return event;
    }

    public void setEvent(int event)
    {
        this.event = event;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}
