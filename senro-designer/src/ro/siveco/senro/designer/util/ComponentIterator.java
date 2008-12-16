package ro.siveco.senro.designer.util;

import java.awt.Component;
import java.awt.Container;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class ComponentIterator
{
    private final List<Component> components = new ArrayList<Component>();
    private final Predicate filter;
    private final Iterator<Component> compIterator;
    private Component nextComp = null;

    public ComponentIterator(Component root, Predicate comp_filter)
    {
        filter = comp_filter == null ? Predicate.True : comp_filter;
        addComponentToList(root);
        compIterator = components.iterator();
        updateNext();
    }

    public ComponentIterator(Component root)
    {
        this(root, null);
    }

    private void addComponentToList(Component comp)
    {
        if(comp == null) {
            return;
        }
        components.add(comp);
        if(comp instanceof Container) {
            Container c_comp = (Container)comp;
            Component[] comps = c_comp.getComponents();
            for(Component crt_comp : comps) {
                addComponentToList(crt_comp);
            }
        }
    }

    private void updateNext()
    {
        nextComp = null;
        while(compIterator.hasNext()) {
            Component c = compIterator.next();
            if(filter.accept(c)) {
                nextComp = c;
                break;
            }
        }
    }

    public boolean hasNext()
    {
        return nextComp != null;
    }

    public Component next()
    {
        Component ret_comp = nextComp;
        updateNext();
        return ret_comp;
    }

}
