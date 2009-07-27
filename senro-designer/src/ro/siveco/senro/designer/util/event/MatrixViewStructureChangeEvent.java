package ro.siveco.senro.designer.util.event;

public class MatrixViewStructureChangeEvent extends ChangeEvent
{
    public static final int ADD_COLUMN    = 0;
    public static final int ADD_ROW       = 1;
    public static final int REMOVE_COLUMN = 2;
    public static final int REMOVE_ROW    = 3;

    private int changeType;

    public MatrixViewStructureChangeEvent(Object the_source, int changeType)
    {
        super(the_source);
        this.changeType = changeType;
    }

    public int getChangeType()
    {
        return changeType;
    }
}
