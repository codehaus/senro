package ro.siveco.senro.designer.util.event;

import ro.siveco.senro.designer.association.AssociationInstance;

public class RemoveAssociationEvent extends AssociationEvent
{

    public RemoveAssociationEvent(Object the_source, AssociationInstance association)
    {
        super(the_source, association);
    }

}
