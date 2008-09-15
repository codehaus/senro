package ro.siveco.senro.designer;

public class DesignerLauncher
{

    public static void main(String[] args)
    {
        System.setProperty("jeta1.debug", "true");
        IBForms fi = new IBForms();
        fi.launch(args);
    }

}
