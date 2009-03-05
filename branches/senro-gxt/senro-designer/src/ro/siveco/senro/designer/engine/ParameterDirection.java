package ro.siveco.senro.designer.engine;

public enum ParameterDirection
{
    IN("in"),
    OUT("out"),
    INOUT("inout");

    private final String name;

    private ParameterDirection(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public static ParameterDirection getDirection(String dir_name)
    {
        if(dir_name == null) {
            return null;
        } else {
            return valueOf(dir_name.toUpperCase());
        }
    }

}
