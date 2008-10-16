package ro.siveco.senro.designer.basic;

public class DesignerObjectReference<T extends SenroDesignerObject> implements DesignerObjectListener
{
    private final T owner;
    private T destination = null;

    public DesignerObjectReference(T owner_obj)
    {
        owner = owner_obj;
        owner.addListener(this);
    }

    public T getDestination()
    {
        return destination;
    }

    public void setDestination(T destination_obj)
    {
        if(destination != null) {
            destination.removeListener(this);
        }
        destination = destination_obj;
        destination.addListener(this);
    }

    public void objectWillBeDeleted(SenroDesignerObject obj)
    {
        obj.removeListener(this);
        if(obj == owner) {
            owner.removeListener(this);
            destination.removeListener(this);
            destination = null;
        }
        if(obj == destination) {
            destination.removeListener(this);
            destination = null;
        }
    }

}
