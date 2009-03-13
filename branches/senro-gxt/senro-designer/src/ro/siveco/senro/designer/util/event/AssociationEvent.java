package ro.siveco.senro.designer.util.event;

import ro.siveco.senro.designer.association.AssociationInstance;

public abstract class AssociationEvent extends ObjectChangeEvent
{
    private final AssociationInstance association;

    public AssociationEvent(Object the_source, AssociationInstance association)
    {
        super(the_source);
        this.association = association;
    }

    public AssociationInstance getAssociation()
    {
        return association;
    }

}
