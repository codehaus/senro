package ro.siveco.senro.designer.util.event;

public class ColumnSelectionChangeEvent extends ComponentChangeEvent
{
    private final int oldSelectedSenroColumnIdx;
    private final int newSelectedSenroColumnIdx;

    public ColumnSelectionChangeEvent(Object the_source, int oldSelectedSenroColumnIdx, int newSelectedSenroColumnIdx)
    {
        super(the_source);
        this.oldSelectedSenroColumnIdx = oldSelectedSenroColumnIdx;
        this.newSelectedSenroColumnIdx = newSelectedSenroColumnIdx;
    }

    public int getOldSelectedSenroColumnIdx()
    {
        return oldSelectedSenroColumnIdx;
    }

    public int getNewSelectedSenroColumnIdx()
    {
        return newSelectedSenroColumnIdx;
    }
}
