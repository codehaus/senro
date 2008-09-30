package ro.siveco.senro.designer.components;

public class TemplateComponent extends PanelComponent
{
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    public static final String DEFAULT_TEXT = "Template";

    public TemplateComponent()
    {
        super(DEFAULT_TEXT, WIDTH, HEIGHT);
    }
}
