package ro.siveco.senro.designer.components;

import ro.siveco.senro.designer.basic.SenroDesignerObject;
import ro.siveco.senro.designer.basic.DesignerObjectListener;
import ro.siveco.senro.designer.basic.UIDesignerObject;
import ro.siveco.senro.designer.basic.UIDesignerObjectDelegate;
import ro.siveco.senro.designer.association.AssociationInstance;

import java.util.Map;
import java.util.List;

public class GridAllocatorRenderer extends PanelComponent implements UIDesignerObject
{
    public static final int WIDTH = 150;
    public static final int HEIGHT = 30;
    public static final String DEFAULT_TEXT = "Grid Allocator Renderer";
    private final UIDesignerObjectDelegate udoDelegate;
    private String gridAllocator;

    public GridAllocatorRenderer()
    {
        super(DEFAULT_TEXT, WIDTH, HEIGHT);
        udoDelegate = new UIDesignerObjectDelegate(this);
    }

    public String getGridAllocator()
    {
        return gridAllocator;
    }

    public void setGridAllocator(String gridAllocator)
    {
        this.gridAllocator = gridAllocator;
    }

    @Override
    public String getName()
    {
        return udoDelegate.getName();
    }

    @Override
    public void setName(String obj_name)
    {
        udoDelegate.setName(obj_name);
        super.setName(obj_name);
    }

    @Override
    public String getId()
    {
        return udoDelegate.getId();
    }

    @Override
    public void setId(String obj_id)
    {
        udoDelegate.setId(obj_id);
    }

    @Override
    public void addListener(DesignerObjectListener listener)
    {
        udoDelegate.addListener(listener);
    }

    @Override
    public void removeListener(DesignerObjectListener listener)
    {
        udoDelegate.removeListener(listener);
    }

    @Override
    public void updateLinks(Map<String, SenroDesignerObject> obj_map)
    {
        udoDelegate.updateLinks(obj_map);
    }

    @Override
    public String getRow()
    {
        return udoDelegate.getRow();
    }

    @Override
    public void setRow(String _row)
    {
        udoDelegate.setRow(_row);
    }

    @Override
    public String getColumn()
    {
        return udoDelegate.getColumn();
    }

    @Override
    public void setColumn(String _col)
    {
        udoDelegate.setColumn(_col);
    }

    @Override
    public void addAssociation(AssociationInstance assoc)
    {
        udoDelegate.addAssociation(assoc);
    }

    @Override
    public void removeAssociation(AssociationInstance assoc)
    {
        udoDelegate.removeAssociation(assoc);
    }

    @Override
    public List<AssociationInstance> getAssociations()
    {
        return udoDelegate.getAssociations();
    }

}