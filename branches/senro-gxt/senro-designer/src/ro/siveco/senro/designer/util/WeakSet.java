package ro.siveco.senro.designer.util;

import java.util.AbstractSet;
import java.util.WeakHashMap;
import java.util.Collection;
import java.util.Iterator;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class WeakSet<E> extends AbstractSet<E> implements Cloneable, Serializable
{
    static final long serialVersionUID = 1L;

    private transient WeakHashMap<E, Object> map;

    // Dummy value to associate with an Object in the backing Map
    private static final Object PRESENT = new Object();

    public WeakSet()
    {
        map = new WeakHashMap<E, Object>();
    }

    public WeakSet(Collection<? extends E> c)
    {
        map = new WeakHashMap<E, Object>(Math.max((int)(c.size()/.75f) + 1, 16));
        addAll(c);
    }

    public WeakSet(int initialCapacity, float loadFactor)
    {
        map = new WeakHashMap<E, Object>(initialCapacity, loadFactor);
    }

    public WeakSet(int initialCapacity)
    {
        map = new WeakHashMap<E, Object>(initialCapacity);
    }

    public Iterator<E> iterator()
    {
        return map.keySet().iterator();
    }

    public int size()
    {
        return map.size();
    }

    public boolean isEmpty()
    {
        return map.isEmpty();
    }

    @SuppressWarnings({ "SuspiciousMethodCalls" })
    public boolean contains(Object o)
    {
        return map.containsKey(o);
    }

    public boolean add(E e)
    {
        return map.put(e, PRESENT) == null;
    }

    public boolean remove(Object o)
    {
        return map.remove(o) == PRESENT;
    }

    public void clear()
    {
        map.clear();
    }

    private void writeObject(ObjectOutputStream s) throws IOException
    {
        // Write out any hidden serialization magic
        s.defaultWriteObject();
        // Write out size
        s.writeInt(map.size());
        // Write out all elements in the proper order.
        for(Object o : map.keySet()) s.writeObject(o);
    }

    @SuppressWarnings({ "unchecked" })
    private void readObject(ObjectInputStream s)
        throws java.io.IOException, ClassNotFoundException
    {
        // Read in any hidden serialization magic
        s.defaultReadObject();
        map = new WeakHashMap<E, Object>();
        // Read in size
        int size = s.readInt();
        // Read in all elements in the proper order.
        for(int i = 0; i < size; i++) {
            E e = (E)s.readObject();
            map.put(e, PRESENT);
        }
    }

}
