package ro.siveco.senro.designer.util.event;

import ro.siveco.senro.designer.association.AssociationInstance;

public class AddAssociationEvent extends AssociationEvent
{

    public AddAssociationEvent(Object the_source, AssociationInstance association)
    {
        super(the_source, association);
    }

}
