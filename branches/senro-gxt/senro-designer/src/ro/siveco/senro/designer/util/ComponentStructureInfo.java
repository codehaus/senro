package ro.siveco.senro.designer.util;

import java.awt.Component;
import java.awt.Container;

public class ComponentStructureInfo
{
    public static String IDENT_STRING = "    ";

    public static String getStructureInfo(Component comp)
    {
        StringBuilder b = new StringBuilder();
        printLine(b, 0, "<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        printLine(b, 0, "");
        printStructureInfo(comp, b, 0);
        return b.toString();
    }

    private static void printStructureInfo(Component comp, StringBuilder b, int ident)
    {
        if(comp instanceof Container) {
            printLine(b, ident, "<" + getStartTagText(comp) + ">");
            Container c_comp = (Container)comp;
            Component[] comps = c_comp.getComponents();
            for(Component crt_comp : comps) {
                printStructureInfo(crt_comp, b, ident + 1);
            }
            printLine(b, ident, "</" + getSimpleClassName(comp) + ">");
        } else {
            printLine(b, ident, "<" + getStartTagText(comp) + "/>");
        }
//        printComponentInfo(b, ident, comp);
//        if(comp instanceof Container) {
//            Container c_comp = (Container)comp;
//            Component[] comps = c_comp.getComponents();
//            for(Component crt_comp : comps) {
//                printStructureInfo(crt_comp, b, ident + 1);
//            }
//        }
    }

    private static void printIdent(StringBuilder b, int ident)
    {
        for(int i = 0; i < ident; i++) {
            b.append(IDENT_STRING);
        }
    }

    private static void printLine(StringBuilder b, int ident, String txt)
    {
        printIdent(b, ident);
        b.append(txt);
        b.append("\n");
    }

    private static void printComponentInfo(StringBuilder b, int ident, Component comp)
    {
        String txt;
        if(comp == null) {
            txt = "<null>";
        } else {
            txt = getSimpleClassName(comp);
            String name = comp.getName();
            if(name != null && name.length() != 0) {
                txt += "[" + name + "]";
            }
        }
        printLine(b, ident, txt);
    }

    private static String getStartTagText(Component cmp)
    {
        String cmp_name = cmp.getName();
        StringBuilder b = new StringBuilder();
        b.append(getSimpleClassName(cmp));
        if(cmp_name != null && cmp_name.length() != 0) {
            b.append(" name = \"").append(cmp_name).append("\"");
        }
        return b.toString();
    }

    private static String getSimpleClassName(Component cmp)
    {
        String[] name_comp = cmp.getClass().getName().split("\\.");
        return name_comp[name_comp.length - 1].replaceAll("\\$", ".");
    }

}
