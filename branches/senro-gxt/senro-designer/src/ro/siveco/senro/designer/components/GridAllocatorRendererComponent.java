package ro.siveco.senro.designer.components;

public class GridAllocatorRendererComponent extends PanelComponent
{
    public static final int WIDTH = 150;
    public static final int HEIGHT = 30;
    public static final String DEFAULT_TEXT = "Grid Allocator Renderer";

    private String gridAllocator;

    public GridAllocatorRendererComponent()
    {
        super(DEFAULT_TEXT, WIDTH, HEIGHT);
    }

    public String getGridAllocator()
    {
        return gridAllocator;
    }

    public void setGridAllocator(String gridAllocator)
    {
        this.gridAllocator = gridAllocator;
    }
}
