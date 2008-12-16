package org.senro.gwt.client.control;

import java.util.Comparator;
import java.util.List;

/**
 * A SortOrdering object specifies the way that a group of objects should be
 * sorted, using a property key and a method selector for comparing values of
 * that property. SortOrderings are used both to generate SQL when fetching rows
 * from a database server, and to sort objects in memory. FetchSpecification
 * objects use an array of SortOrderings, which are applied in series to perform
 * sorts by more than one property.
 * 
 * @author FlaviusB
 * 
 */
public interface SortOrdering {
	public static Comparator<?> CompareAscending = null;
	public static Comparator<?> CompareCaseInsensitiveAscending = null;
	public static Comparator<?> CompareCaseInsensitiveDescending = null;
	public static Comparator<?> CompareDescending = null;
	
	/**
	 * The Comparison interface defines methods for comparing values. These
	 * methods are used for sorting value objects. Support for these methods is
	 * provided for String, Number, and Date using
	 * SortOrdering.ComparisonSupport
	 * 
	 * @author FlaviusB
	 * 
	 */
	public static interface Comparison {
		int compareAscending(Object other);

		int compareDescending(Object other);

		int compareCaseInsensitiveAscending(Object other);

		int compareCaseInsensitiveDescending(Object other);
	}

	/**
	 * ComparisonSupport provides default implementations of the
	 * SortOrdering.Comparison interface and a registry for support objects.
	 * 
	 * @author FlaviusB
	 * 
	 */
	public static interface ComparisonSupport {
		/**
		 * Returned when the object arguments are in ascending order (the value
		 * of the first argument is less than the value of the second).
		 */
		public static final int OrderedAscending = 1;

		/**
		 * Returned when the object arguments are in descending order (the value
		 * of the first argument is greater than the value of the second).
		 */
		public static final int OrderedDescending = -1;

		/**
		 * Returned when the values of the object arguments are equivalent.
		 * Normally this means equal as defined by the equals method.
		 */
		public static final int OrderedSame = 0;

		/**
		 * Returns the ordering of left relative to right.
		 * 
		 * @param left
		 *            - the left hand side object
		 * @param right
		 *            - the right hand side object
		 * @return OrderedAscending if right is naturally ordered after left,
		 *         OrderedDescending if it's naturally ordered before left, and
		 *         OrderedSame if they're equivalent for ordering purposes.
		 */
		public int compareAscending(Object left, Object right);

		/**
		 * Returns the ordering of left relative to right.
		 * 
		 * @param left
		 *            - the left hand side object
		 * @param right
		 *            - the right hand side object
		 * @return OrderedAscending if right is naturally ordered before left,
		 *         OrderedDescending if it's naturally ordered after left, and
		 *         OrderedSame if they're equivalent for ordering purposes
		 */
		public int compareDescending(Object left, Object right);

		/**
		 * Returns the ordering of left relative to right, ignoring case.
		 * 
		 * @param left
		 *            - the left hand side object
		 * @param right
		 *            - the right hand side object
		 * @return OrderedAscending if right is naturally ordered ignoring case
		 *         after left, OrderedDescending if it's naturally ordered
		 *         before left, and OrderedSame if they're equivalent for
		 *         ordering purposes
		 */
		public int compareCaseInsensitiveAscending(Object left, Object right);

		/**
		 * Returns the ordering of left relative to right, ignoring case.
		 * 
		 * @param left
		 *            - the left hand side object
		 * @param right
		 *            - the right hand side object
		 * @return OrderedAscending if right is naturally ordered ignoring case
		 *         before left, OrderedDescending if it's naturally ordered
		 *         after left, and OrderedSame if they're equivalent for
		 *         ordering purposes
		 */
		public int compareCaseInsensitiveDescending(Object left, Object right);

		/**
		 * Compares the two objects using selector. You should use this method
		 * to compare value objects instead of calling selector directly. This
		 * method is the entry point for the comparison support, and calls
		 * methods in support objects if appropriate.
		 * 
		 * @param left
		 *            - the object to compare as the left hand side of the
		 *            inequality
		 * @param right
		 *            - the object to compare as the right hand side of the
		 *            inequality
		 * @param selector
		 *            - method selector to use to compare the left and right
		 *            objects
		 * @return OrderedAscending if right is naturally ordered after left,
		 *         OrderedDescending if it's naturally ordered before left, and
		 *         OrderedSame if they're equivalent for ordering purposes
		 */
		public int compareValues(Object left, Object right,
				Comparator<?> selector);

		/**
		 * Sets support as the support object to be used for comparing instances
		 * of aClass. When compareValues is called, the methods in support are
		 * used to do the comparison for instances of aClass.
		 * 
		 * @param support
		 *            - the comparison support object to be used when comparing
		 *            instances of aClass
		 * @param aClass
		 *            - the Class of objects that will be compared using support
		 */
		public void setSupportForClass(ComparisonSupport support, Class<?> aClass);

		/**
		 * Returns the support object used for doing sort ordering comparisons
		 * for instances of aClass.
		 * 
		 * @param aClass
		 *            - the Class to create a comparison support object for
		 * @return the support object used for doing sort ordering comparisons
		 *         for instances of aClass
		 */
		public ComparisonSupport supportForClass(Class<?> aClass);
	}

	/**
	 * Returns the method selector used to compare values when sorting.
	 * 
	 * @return the method selector used to compare values when sorting
	 */
	public Comparator<?> selector();

	/**
	 * Sorts objects in array in place according to the SortOrderings in
	 * sortOrderings.
	 * 
	 * @param <E>
	 * @param array
	 *            - array of objects to sort
	 * @param sortOrderings
	 *            - - an array of sort orderings to use to sort array by
	 */
	public <E> void sortArray(List<E> array, List<SortOrdering> sortOrderings);

	/**
	 * Creates and returns a new array by sorting objects according to the
	 * SortOrderings in sortOrderings.
	 * 
	 * @param <E>
	 * @param array
	 *            - array of objects to sort
	 * @param sortOrderings
	 *            - array of SortOrderings to sort objects in array by
	 * @return
	 */
	public <E> List<E> sortedArray(List<E> array,
			List<SortOrdering> sortOrderings);
}
