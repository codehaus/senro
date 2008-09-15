/*
 * Warning: Generated code! Do not modify!
 * Atentiune: Cod sursa generat! Nu modifica!
 * Source template: SessionBaseJava.vsl
 * Source template version: $Rel$
 */
package ro.siveco.svapnt.common.generated.session;

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

import ro.siveco.svapnt.common.entity.PartnerOrgUnit;

public abstract class PartnerOrgUnitMgrBase extends ro.siveco.svapnt.common.session.PartnerMgrImpl
{
	public static <T> T getInstance() throws LookUpException
	{
		return (T) getInstance(ro.siveco.svapnt.common.session.PartnerOrgUnitMgrLocal.JNDI_NAME);
	}

	//model defined operations


	//operations:




	// Version operations


	// Tree operations

	
	/**
	 *  Returns true if vPartnerOrgUnit has children nodes
	 */
	public <T extends GenericEntity> boolean hasChildren( T entity ) throws DAOException
	{
		PartnerOrgUnit vPartnerOrgUnit = (PartnerOrgUnit) entity; 
		return (findMultiple(PartnerOrgUnit.class, "partnerOrgUnit = "+vPartnerOrgUnit.getId().toString()).size() > 0);	
	}
	
		/**
	 *  Returns a vector with children based on the ID of the node
	 */
	public <T extends GenericEntity> Vector<T> getChildren( String ID ) throws DAOException
	{
		if (!StringTool.nullOrBlank(ID))
			return new Vector(findMultiple(PartnerOrgUnit.class, "partnerOrgUnit = "+ID));
	   	else
			return new Vector(findMultiple(PartnerOrgUnit.class, "partnerOrgUnit is null "));
	}
	
		/**
	 *  Returns a vector with all roots
	 */
	public <T extends GenericEntity> Vector<T> getAllRoots() throws DAOException
	{
		return new Vector(findMultiple(PartnerOrgUnit.class, "partnerOrgUnit is null"));
	}
	
		/**
	 *  Returns a vector with all children
	 */
	public <T extends GenericEntity,K extends GenericEntity> Vector<T> getAllChildren( K entity ) throws DAOException
	{
		PartnerOrgUnit vPartnerOrgUnit = (PartnerOrgUnit) entity;
		return new Vector(findMultiple(PartnerOrgUnit.class, "leftBound between "+vPartnerOrgUnit.getLeftBound()+" and "+vPartnerOrgUnit.getRightBound()));
	}
	
		/**
	 * Returns the next root, based on the current one
	 */ 
	public <T extends GenericEntity,K extends GenericEntity> T getNextRoot( K entity ) throws DAOException
	{
		PartnerOrgUnit vPartnerOrgUnit = (PartnerOrgUnit) entity;
		for( T root: (Collection<T>)findMultiple(PartnerOrgUnit.class, "partnerOrgUnit is null and id > "+vPartnerOrgUnit.getId()+" order by id"))
            		return root;
        	
        	return null;
	}
	
		/**
	 * Returns a vector of brothers, for the current entity 
	 */ 
	public <T extends GenericEntity,K extends GenericEntity> Vector<T> getBrothers( K entity ) throws DAOException
	{
		PartnerOrgUnit vPartnerOrgUnit = (PartnerOrgUnit) entity;
		if (vPartnerOrgUnit.getPartnerOrgUnit() != null)
		{
			if (vPartnerOrgUnit.getPartnerOrgUnit().getId() != null)
				return new Vector(findMultiple(PartnerOrgUnit.class, "partnerOrgUnit = "+vPartnerOrgUnit.getPartnerOrgUnit().getId()+" and not (id="+vPartnerOrgUnit.getId()+")"));
			else
				return new Vector(findMultiple(PartnerOrgUnit.class, "partnerOrgUnit = "+vPartnerOrgUnit.getPartnerOrgUnit().getId()));
		}
		else
		{
			if (vPartnerOrgUnit.getId() != null)
				return new Vector(findMultiple(PartnerOrgUnit.class, "partnerOrgUnit is null and not (id="+vPartnerOrgUnit.getId()+")"));
			else
				return new Vector(findMultiple(PartnerOrgUnit.class, "partnerOrgUnit is null"));
		}
	}
	
		/**
	 * Returns the first (leftmost) child of an entity 
	 */ 
	public <T extends GenericEntity,K extends GenericEntity> T getFirstChild( K entity ) throws DAOException
	{
		PartnerOrgUnit vPartnerOrgUnit = (PartnerOrgUnit) entity;
		for( T child: (Collection<T>)findMultiple(PartnerOrgUnit.class, "partnerOrgUnit="+vPartnerOrgUnit.getId()+" order by leftBound"))
            		return child;
        	
        	return null;
	}
	
			/**
	 * Returns the last child of an entity 
	 */ 
	public <T extends GenericEntity,K extends GenericEntity> T getLastChild( K entity ) throws DAOException
	{
		PartnerOrgUnit vPartnerOrgUnit = (PartnerOrgUnit) entity;
		for( T child: (Collection<T>)findMultiple(PartnerOrgUnit.class, "partnerOrgUnit="+vPartnerOrgUnit.getId()+" order by leftBound desc"))
            		return child;
        	
        	return null;
	}
	
	/**
	 * First (leftmost) root in the tree
	 */ 
	public <T extends GenericEntity> T getFirstRoot() throws DAOException
	{
		for( T root: (Collection<T>)findMultiple(PartnerOrgUnit.class, "partnerOrgUnit is null order by leftBound"))
            		return root;
        	
        	return null;
	}
	
	
	/**
	 * Last (rightmost) root in the tree 
	 */ 
	public <T extends GenericEntity> T getLastRoot() throws DAOException
	{
		for( T root: (Collection<T>)findMultiple(PartnerOrgUnit.class, "partnerOrgUnit is null order by leftBound desc"))
            		return root;
       		
       		return null;
	}
	
	
	/**
	 * Finds the root of the current node  
	 */ 
	public <T extends GenericEntity,K extends GenericEntity> T getRoot( K entity ) throws DAOException
	{
		PartnerOrgUnit vPartnerOrgUnit = (PartnerOrgUnit) entity;
		for( T child: (Collection<T>)findMultiple(PartnerOrgUnit.class, "not(leftBound > "+vPartnerOrgUnit.getId()+") and not("+vPartnerOrgUnit.getId()+" > rightBound) order by leftBound"))
        	    	return child;
        
        	return null;
	}
	
	public <T extends GenericEntity> T postDelete( T obj ) throws BaseException
	{
		PartnerOrgUnit vPartnerOrgUnit = (PartnerOrgUnit) obj;
		PartnerOrgUnit parent = vPartnerOrgUnit;
				
		// return the parent element from BD
		if (vPartnerOrgUnit.getPartnerOrgUnit() != null)
		{
			if (vPartnerOrgUnit.getPartnerOrgUnit().getId() != null)
				parent = vPartnerOrgUnit.getPartnerOrgUnit();
		}
			
		//parent.addMessages(vPartnerOrgUnit.getMessages());
			
		return (T)parent;
	}
	
	public <T extends GenericEntity> T preInsert( T obj ) throws BaseException
	{
		double left;
		double right;
		PartnerOrgUnit vPartnerOrgUnit = (PartnerOrgUnit) obj;
			
					PartnerOrgUnit firstChild;
				
		if (vPartnerOrgUnit.getPartnerOrgUnit() == null)
		{
			firstChild = (	PartnerOrgUnit) getFirstRoot();
			left = GenericTreeSession.LEFT_BOUND;
			right = GenericTreeSession.RIGHT_BOUND;
		}
		else
		{
			firstChild = (PartnerOrgUnit) getFirstChild(vPartnerOrgUnit.getPartnerOrgUnit());
			left = !ObjectTool.isNull(vPartnerOrgUnit.getPartnerOrgUnit().getLeftBound()) ? vPartnerOrgUnit.getPartnerOrgUnit().getLeftBound().doubleValue() : 0;
			right = !ObjectTool.isNull(vPartnerOrgUnit.getPartnerOrgUnit().getRightBound()) ? vPartnerOrgUnit.getPartnerOrgUnit().getRightBound().doubleValue() : 0;
		}
	
		double dist;
		if( firstChild == null )
			dist = ( right - left ) / 3;
		else
			dist = ( right - ( !ObjectTool.isNull(firstChild.getRightBound()) ? firstChild.getLeftBound().doubleValue() : 0) - left ) / 3 ;
		
		vPartnerOrgUnit.setLeftBound(new Double( left + dist ));
		vPartnerOrgUnit.setRightBound(new Double ( left + 2 * dist ));
		
		return (T)vPartnerOrgUnit;
	}
	
	
			
}
