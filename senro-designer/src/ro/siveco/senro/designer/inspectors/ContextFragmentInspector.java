package ro.siveco.senro.designer.inspectors;

public class ContextFragmentInspector extends SCInspector
{
    public static final String CF_INSPECTOR_TITLE = "Context Fragment Inspector";

    public ContextFragmentInspector()
    {
        super();
        title = CF_INSPECTOR_TITLE;
        nameTF.setEditable(false);
        idTF.setEditable(false);
    }
}
