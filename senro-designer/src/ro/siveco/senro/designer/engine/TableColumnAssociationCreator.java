package ro.siveco.senro.designer.engine;

import ro.siveco.senro.designer.components.TableComponent;
import ro.siveco.senro.designer.association.AssociationInstance;
import ro.siveco.senro.designer.association.AssociationDescription;

import java.util.List;

public class TableColumnAssociationCreator implements AssociationCreator
{
    public static final String TABLE_ASSOC_NAME = "TableAssoc";
    public static final String TABLE_COLUMN_ASSOC_NAME = "TableColumnAssoc";
    public static final String DISPLAY_GROUP_BINDING_NAME = "displayGroup";

    public void createAssociation(Object o)
    {
        TableComponent.SenroTableColumn col = (TableComponent.SenroTableColumn)o;
        TableComponent table = col.getTable();
        List<AssociationInstance> table_assocs = table.getAssociations();
        AssociationInstance assoc_bind = null;
        for(AssociationInstance table_assoc : table_assocs) {
            AssociationDescription desc = table_assoc.getDescription();
            if(TABLE_ASSOC_NAME.equals(desc.getName())) {
                List<AssociationInstance.BindingInstance> bindings = table_assoc.getBindings();
                for(AssociationInstance.BindingInstance binding : bindings) {
                    if(DISPLAY_GROUP_BINDING_NAME.equals(binding.getDescription().getName())) {
                        if(binding.getValue() != null) {
                            System.out.println("Create TableColumn association");
                            AssociationDescription tc_assoc_desc = AssociationDescription.getAssociationDescription(TABLE_COLUMN_ASSOC_NAME);
                            tc_assoc_desc.create(table_assoc, col);
                        }
                    }
                }
            }
        }

    }
}
