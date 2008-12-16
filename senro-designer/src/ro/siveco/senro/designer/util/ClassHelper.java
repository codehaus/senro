package ro.siveco.senro.designer.util;

public class ClassHelper
{
    public static String getShortName(Class c)
    {
        if(c == null) {
            return "<null>";
        } else {
            String[] name_comp = c.getName().split("\\.");
            return name_comp[name_comp.length - 1].replaceAll("\\$", ".");
        }
    }

    public static String getShortClassName(Object o)
    {
        if(o == null) {
            return "<null>";
        } else {
            return getShortName(o.getClass());
        }
    }

}
