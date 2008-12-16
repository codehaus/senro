package ro.siveco.senro.designer.util;

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

}
