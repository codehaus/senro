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
import ro.siveco.svapnt.common.entity.PartnerAttributeWrapper;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Entitate ajutatoare interna aplicatiei<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.common.session.PartnerAttributeWrapperMgrLocal.JNDI_NAME )
public abstract class PartnerAttributeWrapperBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "common_PartnerAttributeWrapper";
	    public static String getEntityName()
	    {
	        return ENTITY_NAME;
	    }

	/* Attributes */

    @Column( name = "ID" )
    @Id
    @GeneratedValue( strategy = SEQUENCE, generator = "SVAPNT_UTILS_SEQ" )
    @SequenceGenerator( name = "SVAPNT_UTILS_SEQ", sequenceName = "SVAPNT_UTILS_SEQ" )
	private java.lang.Long			    id;

    @Column( name = "UPD_TIMESTAMP" )
    @Version
	private java.lang.Long			    updTimestamp;

    @Column( name = "POSITION" )
	private java.lang.Integer			    position;

    @Column( name = "IS_LIST" )
	private java.lang.String			    isList;

    @Column( name = "HAS_DEFAULT_VALUE" )
	private java.lang.String			    hasDefaultValue;

    @Column( name = "REQUIRES_VALIDATION" )
	private java.lang.String			    requiresValidation;

    @Column( name = "ATTRIBUTE_VALUE_ID" )
	private java.lang.Long			    attributeValueId;

    @Column( name = "CATEGORIES" )
	private java.lang.String			    categories;

	/* Vduped Attributes */

	/* Relationships */

    @ManyToOne( optional =  false )
    @JoinColumn( name = "PARTNER_ID" )
// partner
	private ro.siveco.svapnt.common.entity.Partner			    partner;

    @ManyToOne( optional =  false )
    @JoinColumn( name = "ATTRIBUTE_ID" )
// attribute
	private ro.siveco.svapnt.common.entity.PartnerAttribute			    attribute;

    @OneToMany( mappedBy = "wrapper", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.PartnerAttrWrapperValue>     partnerAttrWrapperValues = new ArrayList();

	/* Attribute getters & setters */
    @Column( name = "ID" )
    @PersistentFieldGetter( fieldName = "id" )
    @VisibleReferred
    @UniqueKey
    @Id
    @GeneratedValue( strategy = SEQUENCE, generator = "SVAPNT_UTILS_SEQ" )
    @SequenceGenerator( name = "SVAPNT_UTILS_SEQ", sequenceName = "SVAPNT_UTILS_SEQ" )
	/**
	  *  Access method for the id property.
	  *  @return the current value of the id property
	  */
	public java.lang.Long getId()
	{
		return id;
	}

	/**
	  *  Set method for the id property.
	  */
	public void setId( java.lang.Long _id )
	{
		id = _id;
	}

    @Column( name = "UPD_TIMESTAMP" )
    @PersistentFieldGetter( fieldName = "updTimestamp" )
    @Version
	/**
	  *  Access method for the updTimestamp property.
	  *  @return the current value of the updTimestamp property
	  */
	public java.lang.Long getUpdTimestamp()
	{
		return updTimestamp;
	}

	/**
	  *  Set method for the updTimestamp property.
	  */
	public void setUpdTimestamp( java.lang.Long _updTimestamp )
	{
		updTimestamp = _updTimestamp;
	}

    @Column( name = "POSITION" )
    @PersistentFieldGetter( fieldName = "position" )
@OrderBy("1|ASC")
@VisibleList(true)
@OrderNo("10")
@ListOrderNo("10")
	/**
	  *  Access method for the position property.
	  *  @return the current value of the position property
	  */
	public java.lang.Integer getPosition()
	{
		return position;
	}

	/**
	  *  Set method for the position property.
	  */
	public void setPosition( java.lang.Integer _position )
	{
		position = _position;
	}

    @Column( name = "IS_LIST" )
    @PersistentFieldGetter( fieldName = "isList" )
@VisibleList(true)
@OrderNo("100")
@ListOrderNo("100")
	/**
	  *  Access method for the isList property.
	  *  @return the current value of the isList property
	  */
	public java.lang.String getIsList()
	{
		return isList;
	}

	/**
	  *  Set method for the isList property.
	  */
	public void setIsList( java.lang.String _isList )
	{
		isList = _isList;
	}

    @Column( name = "HAS_DEFAULT_VALUE" )
    @PersistentFieldGetter( fieldName = "hasDefaultValue" )
@VisibleList(true)
@OrderNo("110")
@ListOrderNo("110")
	/**
	  *  Access method for the hasDefaultValue property.
	  *  @return the current value of the hasDefaultValue property
	  */
	public java.lang.String getHasDefaultValue()
	{
		return hasDefaultValue;
	}

	/**
	  *  Set method for the hasDefaultValue property.
	  */
	public void setHasDefaultValue( java.lang.String _hasDefaultValue )
	{
		hasDefaultValue = _hasDefaultValue;
	}

    @Column( name = "REQUIRES_VALIDATION" )
    @PersistentFieldGetter( fieldName = "requiresValidation" )
@VisibleList(true)
@OrderNo("120")
@ListOrderNo("120")
	/**
	  *  Access method for the requiresValidation property.
	  *  @return the current value of the requiresValidation property
	  */
	public java.lang.String getRequiresValidation()
	{
		return requiresValidation;
	}

	/**
	  *  Set method for the requiresValidation property.
	  */
	public void setRequiresValidation( java.lang.String _requiresValidation )
	{
		requiresValidation = _requiresValidation;
	}

    @Column( name = "ATTRIBUTE_VALUE_ID" )
    @PersistentFieldGetter( fieldName = "attributeValueId" )
@VisibleList(true)
@OrderNo("130")
@ListOrderNo("130")
	/**
	  *  Access method for the attributeValueId property.
	  *  @return the current value of the attributeValueId property
	  */
	public java.lang.Long getAttributeValueId()
	{
		return attributeValueId;
	}

	/**
	  *  Set method for the attributeValueId property.
	  */
	public void setAttributeValueId( java.lang.Long _attributeValueId )
	{
		attributeValueId = _attributeValueId;
	}

    @Column( name = "CATEGORIES" )
    @PersistentFieldGetter( fieldName = "categories" )
@OrderNo("500")
@Length("300")
	/**
	  *  Access method for the categories property.
	  *  @return the current value of the categories property
	  */
	public java.lang.String getCategories()
	{
		return categories;
	}

	/**
	  *  Set method for the categories property.
	  */
	public void setCategories( java.lang.String _categories )
	{
		categories = _categories;
	}


	/* Vduped Attribute setters */

	/* Relationship getters & setters */
    @PersistentToOneGetter( relName = "partner" )

    @ManyToOne( optional =  false )
    @JoinColumn( name = "PARTNER_ID" )
// partner
    @UniqueKey
	/**
	  *  Access method for the partner property.
	  *  @return the current value of the partner property
	  */
	public ro.siveco.svapnt.common.entity.Partner getPartner()
	{
		return partner;
	}

	/**
	  *  Set method for the partner property.
	  */
	public void setPartner( ro.siveco.svapnt.common.entity.Partner _partner )
	{
		partner = _partner;
	}

    @PersistentToOneGetter( relName = "attribute" )

    @ManyToOne( optional =  false )
    @JoinColumn( name = "ATTRIBUTE_ID" )
// attribute
    @UniqueKey
	/**
	  *  Access method for the attribute property.
	  *  @return the current value of the attribute property
	  */
	public ro.siveco.svapnt.common.entity.PartnerAttribute getAttribute()
	{
		return attribute;
	}

	/**
	  *  Set method for the attribute property.
	  */
	public void setAttribute( ro.siveco.svapnt.common.entity.PartnerAttribute _attribute )
	{
		attribute = _attribute;
	}


    @OneToMany( mappedBy = "wrapper", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "partnerAttrWrapperValue" )
	/**
	  *  Access method for the partnerAttrWrapperValues property.
	  *  @return the current value of the partnerAttrWrapperValues property
	  */
	public Collection<ro.siveco.svapnt.common.entity.PartnerAttrWrapperValue> getPartnerAttrWrapperValues()
	{
		return partnerAttrWrapperValues;
	}

	/**
	  *  Set method for the partnerAttrWrapperValues property.
	  */
	public void setPartnerAttrWrapperValues( Collection<ro.siveco.svapnt.common.entity.PartnerAttrWrapperValue> _partnerAttrWrapperValues )
	{
		partnerAttrWrapperValues = _partnerAttrWrapperValues;
	}


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                                                                                                                                                                                                                  
	//Unique keys methods with Relations as parameters

    public static final String NQ_findByPartnerAndAttribute = "common_PartnerAttributeWrapper_UK_PARTNER_ATTR_WR_findByPartnerAndAttribute";
    public static PartnerAttributeWrapper findByPartnerAndAttribute( ro.siveco.svapnt.common.entity.Partner partner, ro.siveco.svapnt.common.entity.PartnerAttribute attribute ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add(partner.getId());
                params.add(attribute.getId());
        return ( PartnerAttributeWrapper ) getGenericSession().getSingleResult(NQ_findByPartnerAndAttribute, params);
    }

	//model defined finders


    public static PartnerAttributeWrapper find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( PartnerAttributeWrapper.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
