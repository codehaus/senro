package ro.siveco.senro.designer.inspectors;

public class ContextFragmentInspector extends SCInspector
{
    public static final String CF_INSPECTOR_TITLE = "Context Fragment Inspector";

    public ContextFragmentInspector()
    {
        title = CF_INSPECTOR_TITLE;
    }

    protected void init()
    {
        super.init();
        nameTF.setEditable(false);
        idTF.setEditable(false);
    }
}
