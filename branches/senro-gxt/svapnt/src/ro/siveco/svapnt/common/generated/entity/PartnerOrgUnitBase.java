/*
 * Warning: Generated code! Do not modify!
 * Atentiune: Cod sursa generat! Nu modifica!
 * Source template: EntityBaseJava.vsl
 * Source template version: $Rel$
 */
package ro.siveco.svapnt.common.generated.entity;

import javax.persistence.*;
import ro.siveco.svapnt.common.entity.annotations.*;
import org.senro.annotations.*;
import static javax.persistence.InheritanceType.*;
import static javax.persistence.GenerationType.*;
import static javax.persistence.CascadeType.*;
import ro.siveco.svapnt.common.entity.annotations.OrderBy;
import ro.siveco.svapnt.common.entity.annotations.PersistentFieldGetter;
import ro.siveco.svapnt.common.entity.annotations.PersistentToOneGetter;
import ro.siveco.svapnt.common.entity.annotations.PersistentToManyGetter;
import ro.siveco.svapnt.common.entity.PartnerOrgUnit;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Structuri organizatorice pe parteneri<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * tree true
 * entityID 3
 * treeLeftBound leftBound
 * treeRightBound rightBound
 * treeDisplay code
 * treeTooltip name
 * entityLevel entityLevel
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.common.session.PartnerOrgUnitMgrLocal.JNDI_NAME )
public abstract class PartnerOrgUnitBase extends ro.siveco.svapnt.common.entity.Partner implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "common_PartnerOrgUnit";
	    public static String getEntityName()
	    {
	        return ENTITY_NAME;
	    }

	/* Attributes */

    @Column( name = "DESCRIPTION" )
	private java.lang.String			    description;

    @Column( name = "PREFIX_ID" )
	private java.lang.Integer			    prefixId;

    @Column( name = "ENTITY_LEVEL" )
	private java.lang.Long			    entityLevel = 1L;

    @Column( name = "PRINT_ORDER" )
	private java.lang.Long			    printOrder;

    @Column( name = "PUO_FLAG", updatable = false )
	private java.lang.Short			    puoFlag;

    @Column( name = "LEFT_BOUND" )
	private java.lang.Double			    leftBound;

    @Column( name = "RIGHT_BOUND" )
	private java.lang.Double			    rightBound;

	/* Vduped Attributes */

    @Column( name = "CODE" )
	private java.lang.String			    vdupPartnerOrgUnit_code;

    @Column( name = "NAME" )
	private java.lang.String			    vdupPartnerOrgUnit_name;

	/* Relationships */

    @OneToMany( mappedBy = "partnerOrgUnit", cascade = ALL )
	private Collection<ro.siveco.svapnt.common.entity.PartnerOrgUnit>     partnerOrgUnits = new ArrayList();

    @ManyToOne( optional =  true )
    @JoinColumn( name = "PARENT_ORG_UNIT_ID" )
// partnerOrgUnit
	private ro.siveco.svapnt.common.entity.PartnerOrgUnit			    partnerOrgUnit;

	/* Attribute getters & setters */
    @Column( name = "DESCRIPTION" )
    @PersistentFieldGetter( fieldName = "description" )
@VisibleDetail(true)
@VisibleList(true)
@OrderNo("30")
@Length("100")
	/**
	  *  Access method for the description property.
	  *  @return the current value of the description property
	  */
	public java.lang.String getDescription()
	{
		return description;
	}

	/**
	  *  Set method for the description property.
	  */
	public void setDescription( java.lang.String _description )
	{
		description = _description;
	}

    @Column( name = "PREFIX_ID" )
    @PersistentFieldGetter( fieldName = "prefixId" )
	/**
	  *  Access method for the prefixId property.
	  *  @return the current value of the prefixId property
	  */
	public java.lang.Integer getPrefixId()
	{
		return prefixId;
	}

	/**
	  *  Set method for the prefixId property.
	  */
	public void setPrefixId( java.lang.Integer _prefixId )
	{
		prefixId = _prefixId;
	}

    @Column( name = "ENTITY_LEVEL" )
    @PersistentFieldGetter( fieldName = "entityLevel" )
	/**
	  *  Access method for the entityLevel property.
	  *  @return the current value of the entityLevel property
	  */
	public java.lang.Long getEntityLevel()
	{
		return entityLevel;
	}

	/**
	  *  Set method for the entityLevel property.
	  */
	public void setEntityLevel( java.lang.Long _entityLevel )
	{
		entityLevel = _entityLevel;
	}

    @Column( name = "PRINT_ORDER" )
    @PersistentFieldGetter( fieldName = "printOrder" )
@VisibleDetail(true)
@VisibleList(true)
@OrderNo("55")
@Unfiltered(true)
	/**
	  *  Access method for the printOrder property.
	  *  @return the current value of the printOrder property
	  */
	public java.lang.Long getPrintOrder()
	{
		return printOrder;
	}

	/**
	  *  Set method for the printOrder property.
	  */
	public void setPrintOrder( java.lang.Long _printOrder )
	{
		printOrder = _printOrder;
	}

    @Column( name = "PUO_FLAG", updatable = false )
    @PersistentFieldGetter( fieldName = "puoFlag" )
@Required(true)
@VisibleList(true)
@OrderNo("40")
@VisibleDetail(true)
@Label("Department/Branch Office")
@DomainSplitter(true)
@Length("3")
	/**
	  *  Access method for the puoFlag property.
	  *  @return the current value of the puoFlag property
	  */
	public java.lang.Short getPuoFlag()
	{
		return puoFlag;
	}

	/**
	  *  Set method for the puoFlag property.
	  */
	public void setPuoFlag( java.lang.Short _puoFlag )
	{
		puoFlag = _puoFlag;
	}

    @Column( name = "LEFT_BOUND" )
    @PersistentFieldGetter( fieldName = "leftBound" )
	/**
	  *  Access method for the leftBound property.
	  *  @return the current value of the leftBound property
	  */
	public java.lang.Double getLeftBound()
	{
		return leftBound;
	}

	/**
	  *  Set method for the leftBound property.
	  */
	public void setLeftBound( java.lang.Double _leftBound )
	{
		leftBound = _leftBound;
	}

    @Column( name = "RIGHT_BOUND" )
    @PersistentFieldGetter( fieldName = "rightBound" )
	/**
	  *  Access method for the rightBound property.
	  *  @return the current value of the rightBound property
	  */
	public java.lang.Double getRightBound()
	{
		return rightBound;
	}

	/**
	  *  Set method for the rightBound property.
	  */
	public void setRightBound( java.lang.Double _rightBound )
	{
		rightBound = _rightBound;
	}


	/* Vduped Attribute setters */
	/**
	  *  Set method for the duplicated code property.
	  */
	public void setCode( java.lang.String _code )
	{
	    super.setCode( _code );
		vdupPartnerOrgUnit_code = _code;
	}

	/**
	  *  Set method for the duplicated name property.
	  */
	public void setName( java.lang.String _name )
	{
	    super.setName( _name );
		vdupPartnerOrgUnit_name = _name;
	}


	/* Relationship getters & setters */

    @OneToMany( mappedBy = "partnerOrgUnit", cascade = ALL )
    @Transactional
    @PersistentToManyGetter( relName = "partnerOrgUnit" )
	/**
	  *  Access method for the partnerOrgUnits property.
	  *  @return the current value of the partnerOrgUnits property
	  */
	public Collection<ro.siveco.svapnt.common.entity.PartnerOrgUnit> getPartnerOrgUnits()
	{
		return partnerOrgUnits;
	}

	/**
	  *  Set method for the partnerOrgUnits property.
	  */
	public void setPartnerOrgUnits( Collection<ro.siveco.svapnt.common.entity.PartnerOrgUnit> _partnerOrgUnits )
	{
		partnerOrgUnits = _partnerOrgUnits;
	}

    @PersistentToOneGetter( relName = "partnerOrgUnit" )

    @ManyToOne( optional =  true )
    @JoinColumn( name = "PARENT_ORG_UNIT_ID" )
// partnerOrgUnit
	/**
	  *  Access method for the partnerOrgUnit property.
	  *  @return the current value of the partnerOrgUnit property
	  */
	public ro.siveco.svapnt.common.entity.PartnerOrgUnit getPartnerOrgUnit()
	{
		return partnerOrgUnit;
	}

	/**
	  *  Set method for the partnerOrgUnit property.
	  */
	public void setPartnerOrgUnit( ro.siveco.svapnt.common.entity.PartnerOrgUnit _partnerOrgUnit )
	{
		partnerOrgUnit = _partnerOrgUnit;
	}


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                                                                                                                                                      
    public static final String NQ_findByCode = "common_PartnerOrgUnit_findByCode";
    public static PartnerOrgUnit findByCode( java.lang.String code ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( code);
        return ( PartnerOrgUnit ) getGenericSession().getSingleResult(NQ_findByCode, params);
    }

	//Unique keys methods with Relations as parameters
	//model defined finders


    public static PartnerOrgUnit find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( PartnerOrgUnit.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/


//[start] Tree finders
	
	/**
	 *  Returns true if vPartnerOrgUnit has children nodes
	 */
	public static <T extends GenericEntity> boolean hasChildren( T entity ) throws DAOException
	{
		PartnerOrgUnit vPartnerOrgUnit = (PartnerOrgUnit) entity;
		return (getGenericSession().findMultiple(PartnerOrgUnit.class, "partnerOrgUnit = "+vPartnerOrgUnit.getId().toString()).size() > 0);
	}

	/**
	 *  Returns a vector with children based on the ID of the node
	 */
	public static <T extends GenericEntity> Vector<T> findChildren( String ID ) throws DAOException
	{
		if (!StringTool.nullOrBlank(ID))
			return new Vector(getGenericSession().findMultiple(PartnerOrgUnit.class, "partnerOrgUnit = "+ID));
	   	else
			return new Vector(getGenericSession().findMultiple(PartnerOrgUnit.class, "partnerOrgUnit is null "));
	}

	/**
	 *  Returns a vector with all roots
	 */
	public static <T extends GenericEntity> Vector<T> findAllRoots() throws DAOException
	{
		return new Vector(getGenericSession().findMultiple(PartnerOrgUnit.class, "partnerOrgUnit is null"));
	}

	/**
	 *  Returns a vector with all children
	 */
	public static <T extends GenericEntity,K extends GenericEntity> Vector<T> findAllChildren( K entity ) throws DAOException
	{
		PartnerOrgUnit vPartnerOrgUnit = (PartnerOrgUnit) entity;
		return new Vector(getGenericSession().findMultiple(PartnerOrgUnit.class, "leftBound between "+vPartnerOrgUnit.getLeftBound()+" and "+vPartnerOrgUnit.getRightBound()));
	}

	/**
	 * Returns the next root, based on the current one
	 */
	public static <T extends GenericEntity,K extends GenericEntity> T findNextRoot( K entity ) throws DAOException
	{
		PartnerOrgUnit vPartnerOrgUnit = (PartnerOrgUnit) entity;
		for( T root: (Collection<T>)getGenericSession().findMultiple(PartnerOrgUnit.class, "partnerOrgUnit is null and id > "+vPartnerOrgUnit.getId()+" order by id"))
            return root;
        return null;
	}

		/**
	 * Returns a vector of brothers, for the current entity
	 */
	public static <T extends GenericEntity,K extends GenericEntity> Vector<T> findBrothers( K entity ) throws DAOException
	{
		PartnerOrgUnit vPartnerOrgUnit = (PartnerOrgUnit) entity;
		if (vPartnerOrgUnit.getPartnerOrgUnit() != null)
		{
			if (vPartnerOrgUnit.getPartnerOrgUnit().getId() != null)
				return new Vector(getGenericSession().findMultiple(PartnerOrgUnit.class, "partnerOrgUnit = "+vPartnerOrgUnit.getPartnerOrgUnit().getId()+" and not (id="+vPartnerOrgUnit.getId()+")"));
			else
				return new Vector(getGenericSession().findMultiple(PartnerOrgUnit.class, "partnerOrgUnit = "+vPartnerOrgUnit.getPartnerOrgUnit().getId()));
		}
		else
		{
			if (vPartnerOrgUnit.getId() != null)
				return new Vector(getGenericSession().findMultiple(PartnerOrgUnit.class, "partnerOrgUnit is null and not (id="+vPartnerOrgUnit.getId()+")"));
			else
				return new Vector(getGenericSession().findMultiple(PartnerOrgUnit.class, "partnerOrgUnit is null"));
		}
	}

	/**
	 * Returns the first (leftmost) child of an entity
	 */
	public static <T extends GenericEntity,K extends GenericEntity> T findFirstChild( K entity ) throws DAOException
	{
		PartnerOrgUnit vPartnerOrgUnit = (PartnerOrgUnit) entity;
		for( T child: (Collection<T>)getGenericSession().findMultiple(PartnerOrgUnit.class, "partnerOrgUnit="+vPartnerOrgUnit.getId()+" order by leftBound"))
            return child;
        return null;
	}

	/**
	 * Returns the last child of an entity
	 */
	public static <T extends GenericEntity,K extends GenericEntity> T findLastChild( K entity ) throws DAOException
	{
		PartnerOrgUnit vPartnerOrgUnit = (PartnerOrgUnit) entity;
		for( T child: (Collection<T>)getGenericSession().findMultiple(PartnerOrgUnit.class, "partnerOrgUnit="+vPartnerOrgUnit.getId()+" order by leftBound desc"))
            return child;
        return null;
	}

	/**
	 * First (leftmost) root in the tree
	 */
	public static <T extends GenericEntity> T findFirstRoot() throws DAOException
	{
		for( T root: (Collection<T>)getGenericSession().findMultiple(PartnerOrgUnit.class, "partnerOrgUnit is null order by leftBound"))
            return root;
        return null;
	}


	/**
	 * Last (rightmost) root in the tree
	 */
	public static <T extends GenericEntity> T findLastRoot() throws DAOException
	{
		for( T root: (Collection<T>)getGenericSession().findMultiple(PartnerOrgUnit.class, "partnerOrgUnit is null order by leftBound desc"))
            return root;
        return null;
	}


	/**
	 * Finds the root of the current node
	 */
		public static <T extends GenericEntity,K extends GenericEntity> T findRoot( K entity ) throws DAOException
	{
		PartnerOrgUnit vPartnerOrgUnit = (PartnerOrgUnit) entity;
		for( T child: (Collection<T>)getGenericSession().findMultiple(PartnerOrgUnit.class, "not(leftBound > "+vPartnerOrgUnit.getId()+") and not("+vPartnerOrgUnit.getId()+" > rightBound) order by leftBound"))
            return child;
        return null;
	}

			
	//[end] Tree finders

}
