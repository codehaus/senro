/*
 * Warning: Generated code! Do not modify!
 * Atentiune: Cod sursa generat! Nu modifica!
 * Source template: SessionBaseJava.vsl
 * Source template version: $Rel$
 */
package ro.siveco.svapnt.configuration.generated.session;

import java.util.Collection;
import java.util.Iterator;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import ro.siveco.tools.ObjectTool;
import ro.siveco.tools.StringTool;
import ro.siveco.tools.DateTool;
import java.util.Vector;
import ro.siveco.svapnt.exceptions.*;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.svapnt.common.session.GenericTreeSession;

import ro.siveco.svapnt.configuration.entity.Module;

public abstract class ModuleMgrBase extends ro.siveco.svapnt.common.session.GenericSessionImpl
{
	public static <T> T getInstance() throws LookUpException
	{
		return (T) getInstance(ro.siveco.svapnt.configuration.session.ModuleMgrLocal.JNDI_NAME);
	}

	//model defined operations


	//operations:




	// Version operations


	// Tree operations


	/**
	 *  Returns true if vModule has children nodes
	 */
	public <T extends GenericEntity> boolean hasChildren( T entity ) throws DAOException
	{
		Module vModule = (Module) entity; 
		return (findMultiple(Module.class, "parent = "+vModule.getId().toString()).size() > 0);	
	}
	
		/**
	 *  Returns a vector with children based on the ID of the node
	 */
	public <T extends GenericEntity> Vector<T> getChildren( String ID ) throws DAOException
	{
		if (!StringTool.nullOrBlank(ID))
			return new Vector(findMultiple(Module.class, "parent = "+ID));
	   	else
			return new Vector(findMultiple(Module.class, "parent is null "));
	}
	
		/**
	 *  Returns a vector with all roots
	 */
	public <T extends GenericEntity> Vector<T> getAllRoots() throws DAOException
	{
		return new Vector(findMultiple(Module.class, "parent is null"));
	}
	
		/**
	 *  Returns a vector with all children
	 */
	public <T extends GenericEntity,K extends GenericEntity> Vector<T> getAllChildren( K entity ) throws DAOException
	{
		Module vModule = (Module) entity;
		return new Vector(findMultiple(Module.class, "leftBound between "+vModule.getLeftBound()+" and "+vModule.getRightBound()));
	}
	
		/**
	 * Returns the next root, based on the current one
	 */ 
	public <T extends GenericEntity,K extends GenericEntity> T getNextRoot( K entity ) throws DAOException
	{
		Module vModule = (Module) entity;
		for( T root: (Collection<T>)findMultiple(Module.class, "parent is null and id > "+vModule.getId()+" order by id"))
            		return root;
        	
        	return null;
	}
	
		/**
	 * Returns a vector of brothers, for the current entity 
	 */ 
	public <T extends GenericEntity,K extends GenericEntity> Vector<T> getBrothers( K entity ) throws DAOException
	{
		Module vModule = (Module) entity;
		if (vModule.getParent() != null)
		{
			if (vModule.getParent().getId() != null)
				return new Vector(findMultiple(Module.class, "parent = "+vModule.getParent().getId()+" and not (id="+vModule.getId()+")"));
			else
				return new Vector(findMultiple(Module.class, "parent = "+vModule.getParent().getId()));
		}
		else
		{
			if (vModule.getId() != null)
				return new Vector(findMultiple(Module.class, "parent is null and not (id="+vModule.getId()+")"));
			else
				return new Vector(findMultiple(Module.class, "parent is null"));
		}
	}
	
		/**
	 * Returns the first (leftmost) child of an entity 
	 */ 
	public <T extends GenericEntity,K extends GenericEntity> T getFirstChild( K entity ) throws DAOException
	{
		Module vModule = (Module) entity;
		for( T child: (Collection<T>)findMultiple(Module.class, "parent="+vModule.getId()+" order by leftBound"))
            		return child;
        	
        	return null;
	}
	
			/**
	 * Returns the last child of an entity 
	 */ 
	public <T extends GenericEntity,K extends GenericEntity> T getLastChild( K entity ) throws DAOException
	{
		Module vModule = (Module) entity;
		for( T child: (Collection<T>)findMultiple(Module.class, "parent="+vModule.getId()+" order by leftBound desc"))
            		return child;
        	
        	return null;
	}
	
	/**
	 * First (leftmost) root in the tree
	 */ 
	public <T extends GenericEntity> T getFirstRoot() throws DAOException
	{
		for( T root: (Collection<T>)findMultiple(Module.class, "parent is null order by leftBound"))
            		return root;
        	
        	return null;
	}
	
	
	/**
	 * Last (rightmost) root in the tree 
	 */ 
	public <T extends GenericEntity> T getLastRoot() throws DAOException
	{
		for( T root: (Collection<T>)findMultiple(Module.class, "parent is null order by leftBound desc"))
            		return root;
       		
       		return null;
	}
	
	
	/**
	 * Finds the root of the current node  
	 */ 
	public <T extends GenericEntity,K extends GenericEntity> T getRoot( K entity ) throws DAOException
	{
		Module vModule = (Module) entity;
		for( T child: (Collection<T>)findMultiple(Module.class, "not(leftBound > "+vModule.getId()+") and not("+vModule.getId()+" > rightBound) order by leftBound"))
        	    	return child;
        
        	return null;
	}
	
	public <T extends GenericEntity> T postDelete( T obj ) throws BaseException
	{
		Module vModule = (Module) obj;
		Module parent = vModule;
				
		// return the parent element from BD
		if (vModule.getParent() != null)
		{
			if (vModule.getParent().getId() != null)
				parent = vModule.getParent();
		}
			
		//parent.addMessages(vModule.getMessages());
			
		return (T)parent;
	}
	
	public <T extends GenericEntity> T preInsert( T obj ) throws BaseException
	{
		double left;
		double right;
		Module vModule = (Module) obj;
			
					Module firstChild;
				
		if (vModule.getParent() == null)
		{
			firstChild = (	Module) getFirstRoot();
			left = GenericTreeSession.LEFT_BOUND;
			right = GenericTreeSession.RIGHT_BOUND;
		}
		else
		{
			firstChild = (Module) getFirstChild(vModule.getParent());
			left = !ObjectTool.isNull(vModule.getParent().getLeftBound()) ? vModule.getParent().getLeftBound().doubleValue() : 0;
			right = !ObjectTool.isNull(vModule.getParent().getRightBound()) ? vModule.getParent().getRightBound().doubleValue() : 0;
		}
	
		double dist;
		if( firstChild == null )
			dist = ( right - left ) / 3;
		else
			dist = ( right - ( !ObjectTool.isNull(firstChild.getRightBound()) ? firstChild.getLeftBound().doubleValue() : 0) - left ) / 3 ;
		
		vModule.setLeftBound(new Double( left + dist ));
		vModule.setRightBound(new Double ( left + 2 * dist ));
		
		return (T)vModule;
	}
	
	
						
}
