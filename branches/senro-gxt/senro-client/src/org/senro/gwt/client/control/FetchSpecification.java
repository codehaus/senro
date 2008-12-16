package org.senro.gwt.client.control;

import java.util.List;
import java.util.Map;

/**
 * A FetchSpecification collects the criteria needed to select and order a
 * group of records or enterprise objects, whether from an external repository
 * such as a relational database or an internal store such as an
 * EditingContext. A FetchSpecification contains these elements:
 * 
 * The name of an entity for which to fetch records or objects. This is the only
 * mandatory element. An Qualifier, indicating which properties to select by
 * and how to do so. An array of SortOrderings, which indicate how the
 * selected records or objects should be ordered when fetched. An indicator of
 * whether to produce distinct results or not. Normally if a record or object is
 * selected several times, such as when forming a join, it appears several times
 * in the fetched results. A FetchSpecification that makes distinct
 * selections causes duplicates to be filtered out, so each record or object
 * selected appears exactly once in the result set. An indicator of whether to
 * fetch deeply or not. This is used with inheritance hierarchies when fetching
 * for an entity with sub entities. A deep fetch produces all instances of the
 * entity and its sub entities, while a shallow fetch produces instances only of
 * the entity in the fetch specification. A fetch limit indicating how many
 * objects to fetch before giving the user or program an opportunity to
 * intervene. A listing of relationships for which the destination of the
 * relationship should be prefetched along with the entity being fetched. Proper
 * use of this feature allows for substantially increased performance in some
 * cases.
 * 
 * @author FlaviusB
 * 
 */
public interface FetchSpecification {
	/**
	 * Returns the name of the entity to be fetched
	 * 
	 * @return
	 */
	java.lang.String entityName();

	/**
	 * Sets the name of the root entity to be fetched to entityName.
	 * 
	 * @param aName
	 */
	void setEntityName(java.lang.String aName);

	/**
	 * Returns the fetch limit value which indicates the maximum number of
	 * objects to fetch. Use 0 (zero) to indicate no fetch limit. The default is
	 * 0.
	 * 
	 * @return
	 */
	int fetchLimit();

	/**
	 * Sets the fetch limit value which indicates the maximum number of objects
	 * to fetch. Use 0 (zero) to indicate no fetch limit. The default is 0.
	 * 
	 * @param aLimit
	 */
	void setFetchLimit(int aLimit);

	/**
	 * 
	 * @return
	 */
	int fetchOffset();

	void setFetchOffset(int aOffset);

	/**
	 * Returns the receiver's hints, which other objects can use to alter or
	 * optimize fetch operations.
	 * 
	 * @return
	 */
	Map<String, String> hints();

	/**
	 * Sets the receiver's hints to hints. Any object that uses an
	 * FetchSpecification can define its own hints that it uses to alter or
	 * optimize fetch operations.
	 * 
	 * @param aHintMap
	 */
	void setHints(java.util.Map aHintMap);

	/**
	 * Returns an array of relationship key paths that should be prefetched
	 * along with the main fetch. For example, if fetching from a Movie entity,
	 * you might specify paths of the form: ("directors","roles.talent",
	 * "plotSummary").
	 * 
	 * @return
	 */
	List<String> prefetchingRelationshipKeyPaths();

	/**
	 * Sets an array of relationship key paths that should be prefetched along
	 * with the main fetch. For example, if fetching from a Movie entity, you
	 * might specify paths of the form: ("directors","roles.talent",
	 * "plotSummary").
	 * 
	 * Prefetching increases the initial fetch cost, but it can improve overall
	 * performance by reducing the number of round trips made to the database
	 * server. Assigning relationships to prefetch also has an effect on how a
	 * fetch specification refreshes.
	 * 
	 * Refreshing refers to existing objects being overwritten with fetched
	 * values. This allows the application to see changes to the database that
	 * have been made by someone else. Normally, when an FetchSpecification is
	 * set to refresh using setRefreshesRefetchedObjects, it only refreshes the
	 * objects you are fetching. For example, if you fetch employees, you do not
	 * also fetch the employees' departments. However, if you prefetch
	 * relationships, the refetch is propagated for all of the relationships
	 * specified.
	 * 
	 * @param aKeyPathList
	 */
	void setPrefetchingRelationshipKeyPaths(java.util.List aKeyPathList);

	/**
	 * Returns the Qualifier that indicates which records or objects the
	 * receiver is to fetch.
	 * 
	 * @return
	 */
	Qualifier qualifier();

	/**
	 * Sets this fetch specification's qualifier to qualifier. If qualifier is
	 * empty, this fetch specification will have its qualifier set to null
	 * 
	 * @param aQualifier
	 */
	void setQualifier(Qualifier aQualifier);

	/**
	 * Returns true if existing objects are overwritten with fetched values when
	 * they've been updated or changed. Returns false if existing objects aren't
	 * touched when their data is refetched (the fetched data is simply
	 * discarded). The default is false. Note that this setting does not affect
	 * relationships.
	 * 
	 * @return
	 */
	boolean refreshesRefetchedObjects();

	/**
	 * Sets the receiver's array of SortOrderings to sortOrderings. When a fetch
	 * is performed with the receiver, the results are sorted by applying each
	 * SortOrdering in the array (serially in lowest-array-index-first order).
	 * 
	 * @param aSortList
	 */
	void setSortOrderings(java.util.List<SortOrdering> aSortList);

	/**
	 * Sets whether duplicate objects or records are removed after fetching. If
	 * flag is true they are removed (that is, proper sets are returned). If
	 * flag is false they aren't (multisets are returned). FetchSpecifications
	 * by default don't use distinct.
	 * 
	 * @param shouldUseDistinct
	 */
	void setUsesDistinct(boolean shouldUseDistinct);

	/**
	 * Returns the receiver's array of SortOrderings. When a fetch is performed
	 * with the receiver, the results are sorted by applying each SortOrdering
	 * in the array (serially in lowest-array-index-first order).
	 * 
	 * @return
	 */
	List<SortOrdering> sortOrderings();

	/**
	 * Returns true if duplicate objects or records are removed after fetching,
	 * false if they aren't. FetchSpecifications by default don't use distinct
	 * (that is, they return multisets).
	 * 
	 * @return
	 */
	boolean usesDistinct();
}
