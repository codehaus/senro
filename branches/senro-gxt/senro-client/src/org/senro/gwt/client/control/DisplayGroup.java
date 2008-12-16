package org.senro.gwt.client.control;

import java.util.List;

import org.senro.gwt.client.model.DataObject;

public interface DisplayGroup {
	List<DataObject> deletedObjects();
	List<DataObject> insertedObjects();
	List<DataObject> updatedObjects();
	
	boolean deleteObjectAtIndex(int anIndex);
	DataObject insertNewObjectAtIndex(int anIndex);
	void insertObjectAtIndex(DataObject anObject, int anIndex); 
	
	List<DataObject> allObjects();
	boolean clearSelection();
	DataSource dataSource();
	boolean deleteSelection();
	List<DataObject> displayedObjects();
	boolean fetch();
	void redisplay();
	DataObject selectedObject();
	void setSelectedObject(DataObject anObject); 
	List<DataObject> selectedObjects();
	void setSelectedObjects(java.util.List<DataObject> aList);
	Object selectedObjectValueForKey(java.lang.String aKey);
	boolean setSelectedObjectValue(java.lang.Object aValue, java.lang.String aKey);
	boolean setValueForObject(java.lang.Object aValue, java.lang.Object anObject, java.lang.String aKey);
	boolean setValueForObjectAtIndex(java.lang.Object aValue, int anIndex, java.lang.String aKey);
	List<Long> selectionIndexes();
	boolean setSelectionIndexes(java.util.List<Long> aList); 
	boolean selectNext();
	boolean selectObject(DataObject anObject);
	boolean selectPrevious();
	boolean selectsFirstObjectAfterFetch();
	void setSelectsFirstObjectAfterFetch(boolean selectsFirst); 
	void setDataSource(DataSource aDataSource);
	boolean fetchesOnLoad();
	void setFetchesOnLoad(boolean willFetch);
	void setInsertedObjectDefaultValues(java.util.Map aMap);
	List sortOrderings();
	void setSortOrderings(java.util.List aList);
	void setUsesOptimisticRefresh(boolean isOptimistic); 
	boolean usesOptimisticRefresh();
	java.lang.Object valueForObject(DataObject anObject, java.lang.String aKey); 
	Object valueForObjectAtIndex(int anIndex, java.lang.String aKey);
	
	Qualifier qualifier();
	void setQualifier(Qualifier aQualifier);
	void updateDisplayedObjects();
}
