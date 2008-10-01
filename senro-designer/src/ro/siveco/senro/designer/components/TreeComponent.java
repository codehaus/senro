package ro.siveco.senro.designer.components;

public class TreeComponent extends PanelComponent
{
    public static final int WIDTH = 50;
    public static final int HEIGHT = 30;
    public static final String DEFAULT_TEXT = "Tree";

    private String list;
    private String text;

    public TreeComponent()
    {
        super(DEFAULT_TEXT, WIDTH, HEIGHT);
    }

    public String getList()
    {
        return list;
    }

    public void setList(String list)
    {
        this.list = list;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }
}
