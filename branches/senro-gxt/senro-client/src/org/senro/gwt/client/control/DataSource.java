package org.senro.gwt.client.control;

import java.util.List;

import org.senro.gwt.client.model.DataObject;

/**
 * DataSource is an abstract class that defines a basic API for providing
 * enterprise objects. It exists primarily as a simple means for a display group
 * (DisplayGroup) or other higher level class to access a store of objects.
 * 
 * @author FlaviusB
 */
public interface DataSource {
	/**
	 * Implemented by subclasses to return the FQN of class that provides
	 * information about the objects provided by the receiver.
	 * 
	 * @return FQN of class
	 */
	String classDescriptionForObjects();

	/**
	 * Creates a new object, inserts it in the receiver's collection of objects
	 * if appropriate, and returns the object. Returns null if the receiver
	 * can't create the object or can't insert it. You should invoke
	 * insertObject after this method to actually add the new object to the
	 * receiver.
	 * 
	 * 
	 * @return newly created object
	 */
	DataObject createObject();

	/**
	 * Implemented by subclasses to delete an object.
	 * 
	 * @param anObject
	 */
	void deleteObject(DataObject anObject);

	/**
	 * Implemented by subclasses to return the receiver's EditingContext.
	 * 
	 * @return
	 */
	EditingContext editingContext();

	/**
	 * Implemented by subclasses to fetch and return the objects provided by the
	 * receiver.
	 * 
	 * @return
	 */
	List<DataObject> fetchObjects();

	/**
	 * Implemented by subclasses to insert objects.
	 * 
	 * @param anObject
	 */
	void insertObject(DataObject anObject);

	/**
	 * Returns the receiver's FetchSpecification or null if no fetch
	 * specification is set.
	 * 
	 * @return
	 */
	FetchSpecification fetchSpecification();

	/**
	 * Sets the fetch specification used when supplying objects to fetchSpec.
	 * 
	 * @param aFetchSpec
	 */
	void setFetchSpecification(FetchSpecification aFetchSpec);
}
