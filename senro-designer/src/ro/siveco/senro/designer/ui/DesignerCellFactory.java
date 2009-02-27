package ro.siveco.senro.designer.ui;

import ro.siveco.senro.designer.objects.*;
import ro.siveco.senro.designer.engine.DesignerManager;

import javax.swing.*;
import java.util.Map;
import java.util.HashMap;

public class DesignerCellFactory implements CellFactory
{
    protected MatrixView matrixView = null;
    protected static Map<String, ImageIcon> iconForClass;
    public static final String DISPLAY_GROUP_IMAGE_FILE = "DisplayGroup.png";
    public static final String EDITING_CONTEXT_IMAGE_FILE = "EditingContext.png";
    public static final String SERVER_SENRO_CONTEXT_IMAGE_FILE = "SenroContext.png";
    public static final String GRID_ALLOCATOR_IMAGE_FILE = "GridAllocator.png";
    public static final String CLIENT_SENRO_CONTEXT_IMAGE_FILE = "SenroContext.png";

    public MatrixCell getCellForData(Object data)
    {
        if(data == null)
            return null;
        ObjectDescription obj_desc = (ObjectDescription) data;
        String class_name = obj_desc.getObjectClassName();
        String name = obj_desc.getName();
        ImageIcon icon = iconForClass.get(class_name);
        return new SfdCell(matrixView, icon, name);
    }

    public MatrixView getMatrixView()
    {
        return matrixView;
    }

    public void setMatrixView(MatrixView matrixView)
    {
        this.matrixView = matrixView;
    }

    static {
        iconForClass = new HashMap<String, ImageIcon>();
        ImageIcon dg_icon = DesignerManager.getIconForImage(DISPLAY_GROUP_IMAGE_FILE);
        ImageIcon ec_icon = DesignerManager.getIconForImage(EDITING_CONTEXT_IMAGE_FILE);
        ImageIcon ssc_icon = DesignerManager.getIconForImage(SERVER_SENRO_CONTEXT_IMAGE_FILE);
        ImageIcon ga_icon = DesignerManager.getIconForImage(GRID_ALLOCATOR_IMAGE_FILE);
        ImageIcon csc_icon = DesignerManager.getIconForImage(CLIENT_SENRO_CONTEXT_IMAGE_FILE);

        iconForClass.put(DisplayGroupDescription.OBJECT_CLASS_NAME, dg_icon);
        iconForClass.put(EditingContextDescription.OBJECT_CLASS_NAME, ec_icon);
        iconForClass.put(ContextFragmentDescription.OBJECT_CLASS_NAME, ssc_icon);
        iconForClass.put(GridAllocatorDescription.OBJECT_CLASS_NAME, ga_icon);
        iconForClass.put(SenroContextDescription.OBJECT_CLASS_NAME, csc_icon);
    }
}
