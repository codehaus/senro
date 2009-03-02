package ro.siveco.senro.designer.util.ui;

import java.util.List;
import java.util.ArrayList;

public class ObjectChooser
{
    private final static DefaultIdentifierCreator defaultIdentifierCreator = new DefaultIdentifierCreator();

    public static <T> T choose(String title, List<T> items)
    {
        return choose(title, items, null);
    }

    @SuppressWarnings({ "unchecked" })
    public static <T> T choose(String title, List<? extends T> items, IdentifierCreator<T> ic)
    {
        if(items == null || items.size() == 0) {
            return null;
        }
        if(ic == null) {
            ic = (IdentifierCreator<T>)defaultIdentifierCreator;
        }
        List<String> ids = new ArrayList<String>();
        for(T item : items) {
            ids.add(ic.createIdentifier(item));
        }
        int item_idx = ChoosePanel.getChooseIndex(ids, title);
        if(item_idx < 0) {
            return null;
        } else {
            return items.get(item_idx);
        }
    }

    public static interface IdentifierCreator<T>
    {
        public String createIdentifier(T t);
    }

    private static class DefaultIdentifierCreator implements IdentifierCreator<Object>
    {

        public String createIdentifier(Object o)
        {
            return o == null ? "<null>" : o.toString();
        }
    }

}
