package ro.siveco.senro.designer;

import ro.siveco.senro.designer.engine.DesignerManager;

public class DesignerLauncher
{

    public static void main(String[] args)
    {
//        Properties p = System.getProperties();
//        for(Object k : p.keySet()) {
//            System.out.println(k + " = " + p.get(k));
//        }
//        System.out.println();
        System.setProperty("jeta1.debug", "true");
        IBForms fi = new IBForms();
        fi.launch(args);
        DesignerManager.getSharedDesignerManager().awake();
    }

}
