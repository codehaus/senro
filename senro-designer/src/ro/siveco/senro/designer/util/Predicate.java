package ro.siveco.senro.designer.util;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

public interface Predicate
{
    public boolean accept(Object o);

    public static final Predicate True = new TruePredicate();
    public static final Predicate False = new FalsePredicate();

    public static class TruePredicate implements Predicate
    {

        public boolean accept(Object o)
        {
            return true;
        }

    }

    public static class FalsePredicate implements Predicate
    {

        public boolean accept(Object o)
        {
            return false;
        }

    }

    public static class Util
    {
        public static <T> List<T> filterList(Collection<T> list, Predicate p)
        {
            List<T> filteredList = new ArrayList<T>();
            for(T t : list) {
                if(p.accept(t)) {
                    filteredList.add(t);
                }
            }
            return filteredList;
        }
    }

}
