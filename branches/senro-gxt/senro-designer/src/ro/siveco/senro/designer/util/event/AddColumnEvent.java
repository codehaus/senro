package ro.siveco.senro.designer.util.event;

import ro.siveco.senro.designer.components.TableComponent;

public class AddColumnEvent extends ComponentChangeEvent
{
    private final TableComponent.SenroTableColumn column;

    public AddColumnEvent(Object the_source, TableComponent.SenroTableColumn column)
    {
        super(the_source);
        this.column = column;
    }

    public TableComponent.SenroTableColumn getColumn()
    {
        return column;
    }
}
