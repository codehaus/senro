package org.senro.gwt.client.control;

import java.util.List;

import org.senro.gwt.client.model.DataObject;

/**
 * The EOEditingContext.Delegate interface defines methods that an
 * EOEditingContext can invoke in its delegate. Delegates are not required to
 * provide implementations for all of the methods in the interface, and you do
 * not have to use the implements keyword to specify that the object implements
 * the Delegate interface. Instead, declare and implement any subset of the
 * methods declared in the interface that you need, and use the EOEditingContext
 * method setDelegate method to assign the object as the delegate. An editing
 * context can determine if the delegate doesn't implement a delegate method and
 * only attempts to invoke the methods the delegate actually implements.
 * 
 * @author FlaviusB
 * 
 */
public interface EditingContext extends ObjectStore {
	List<DataObject> deletedObjects();

	List<DataObject> insertedObjects();

	List<DataObject> updatedObjects();

	void deleteObject(DataObject anObject);

	void insertObject(DataObject anObject);

	void updateObject(DataObject anObject);

	DataObject proxyForDataObjectID(String uri, String id);

	void forgetDataObject(DataObject object);

	boolean hasChanges();

	void invalidateAllObjects();

	void invalidateObjectsWithIDs(List<String> anArray);

	DataObject objectForID(String id);

	List<DataObject> objectsWithFetchSpecification(FetchSpecification aFetchSpec);

	void objectWillChange(DataObject anObject);

	ObjectStore parentObjectStore();

	void processRecentChanges(); // ??

	void recordObject(DataObject object);

	List<DataObject> registeredObjects();

	void reset();

	void revert();

	ObjectStore rootObjectStore();

	void saveChanges();

	void setStopsValidationAfterFirstError(boolean stopsValidation); // ????

	void undo();

	void redo();
}
