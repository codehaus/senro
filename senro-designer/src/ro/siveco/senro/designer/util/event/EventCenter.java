package ro.siveco.senro.designer.util.event;

import ro.siveco.senro.designer.DesignerRuntimeException;
import ro.siveco.senro.designer.util.WeakSet;

import java.util.Map;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.WeakHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class EventCenter
{

    private static final EventCenter sharedCenter = new EventCenter();

    protected Map<Class<? extends Event>, EventNode> nodes;
    protected EventNode rootNode;
    private ReentrantReadWriteLock nodesLock = new ReentrantReadWriteLock();

    public EventCenter()
    {
        super();
        nodes = Collections.synchronizedMap(new HashMap<Class<? extends Event>, EventNode>());
        rootNode = new EventNode(null);
        nodes.put(Event.class, rootNode);
    }

    @SuppressWarnings({ "unchecked" })
    private EventNode nodeForClass(Class<? extends Event> event_class)
        throws DesignerRuntimeException
    {
        nodesLock.writeLock().lock();
        try {
            EventNode c_node = nodes.get(event_class);
            if(c_node != null)
                return c_node;
            if(event_class == null) {
                throw new DesignerRuntimeException("Event class cannot be null.");
            }
            EventNode super_node = nodeForClass((Class<? extends Event>)event_class.getSuperclass());
            c_node = new EventNode(super_node);
            nodes.put(event_class, c_node);
            return c_node;
        }
        finally {
            nodesLock.writeLock().unlock();
        }
    }

    public void addObserver(Observer observer, Object object, Class<? extends Event> event_class)
        throws DesignerRuntimeException
    {
        nodeForClass(event_class).addObserver(observer, object);
    }

    public void addObserver(Observer observer, Set objects, Class<? extends Event> event_class)
        throws DesignerRuntimeException
    {
        EventNode node = nodeForClass(event_class);
        for(Object object : objects) {
            node.addObserver(observer, object);
        }
    }

    public void removeObserver(Observer observer, Object object, Class<? extends Event> event_class)
        throws DesignerRuntimeException
    {
        nodeForClass(event_class).removeObserver(observer, object);
    }

    public void removeObserver(Observer observer, Set objects, Class<? extends Event> event_class)
    {
        EventNode n = nodeForClass(event_class);
        for(Object object : objects) {
            n.removeObserver(observer, object);
        }
    }

    public void postEvent(Event event)
    {
        Set<Observer> collected_observers = new HashSet<Observer>();
        nodeForClass(event.getClass()).putObjectsForEvent(event, collected_observers);
        for(Observer observer : collected_observers) {
            observer.handleEvent(event);
        }
    }

    public static void add(Observer observer, Object object, Class<? extends Event> event_class)
    {
        sharedCenter.addObserver(observer, object, event_class);
    }

    public static void add(Observer observer, Set objects, Class<? extends Event> event_class)
    {
        sharedCenter.addObserver(observer, objects, event_class);
    }

    public static void remove(Observer observer, Object object, Class<? extends Event> event_class)
    {
        sharedCenter.removeObserver(observer, object, event_class);
    }

    public static void remove(Observer observer, Set objects, Class<? extends Event> event_class)
    {
        sharedCenter.removeObserver(observer, objects, event_class);
    }

    public static void post(Event event)
    {
        sharedCenter.postEvent(event);
    }

    protected static class EventNode
    {
        private final Set<Observer> eventObservers = Collections.synchronizedSet(new WeakSet<Observer>());

        private final Map<Object, WeakSet<Observer>> objectObservers =
            Collections.synchronizedMap(new WeakHashMap<Object, WeakSet<Observer>>());

        private final EventNode superNode;

        public EventNode(EventNode super_node)
        {
            superNode = super_node;
        }

        public synchronized void addObserver(Observer observer, Object source)
            throws DesignerRuntimeException
        {
            if(observer == null) {
                throw new NullPointerException("Observer cannot be null.");
            }
            if(source == null)
                eventObservers.add(observer);
            else {
                WeakSet<Observer> obs_set = objectObservers.get(source);
                if(obs_set == null) {
                    obs_set = new WeakSet<Observer>();
                    objectObservers.put(source, obs_set);
                }
                obs_set.add(observer);
            }
        }

        public synchronized void removeObserver(Observer observer, Object source)
            throws DesignerRuntimeException
        {
            if(observer == null) {
                throw new NullPointerException("Observer cannot be null.");
            }
            if(source == null)
                eventObservers.remove(observer);
            else {
                WeakSet obs_set = objectObservers.get(source);
                if(obs_set != null) {
                    obs_set.remove(observer);
                    if(obs_set.size() == 0)
                        objectObservers.remove(obs_set);
                }
            }
        }

        public synchronized void putObjectsForEvent(Event e, Set<Observer> n_set)
        {
            n_set.addAll(eventObservers);
            Set<Observer> obs_set = objectObservers.get(e.getSource());
            if(obs_set != null)
                n_set.addAll(obs_set);
            if(superNode != null)
                superNode.putObjectsForEvent(e, n_set);
        }

        public EventNode getSuperNode()
        {
            return superNode;
        }

    }

    public static EventCenter get()
    {
        return sharedCenter;
    }

}
