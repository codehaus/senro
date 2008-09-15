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
import ro.siveco.svapnt.common.entity.PartnerCategoryAttribute;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Atribute categorii de parteneri<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.common.session.PartnerCategoryAttributeMgrLocal.JNDI_NAME )
public abstract class PartnerCategoryAttributeBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "common_PartnerCategoryAttribute";
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

    @Column( name = "IS_LIST" )
	private java.lang.String			    isList;

    @Column( name = "HAS_DEFAULT_VALUE" )
	private java.lang.String			    hasDefaultValue;

    @Column( name = "REQUIRES_VALIDATION" )
	private java.lang.String			    requiresValidation;

    @Column( name = "POSITION" )
	private java.lang.Integer			    position;

	/* Vduped Attributes */

	/* Relationships */

    @ManyToOne( optional =  false )
    @JoinColumn( name = "PARTNER_CATEGORY_ID" )
// partnerCategory
	private ro.siveco.svapnt.common.entity.PartnerCategory			    partnerCategory;

    @ManyToOne( optional =  false )
    @JoinColumn( name = "PARTNER_ATTRIBUTE_ID" )
// partnerAttribute
	private ro.siveco.svapnt.common.entity.PartnerAttribute			    partnerAttribute;

    @ManyToOne( optional =  true )
    @JoinColumn( name = "PARTNER_ATTRIBUTE_VALUE_ID" )
// partnerAttributeValue
	private ro.siveco.svapnt.common.entity.PartnerAttributeValue			    partnerAttributeValue;

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

    @Column( name = "IS_LIST" )
    @PersistentFieldGetter( fieldName = "isList" )
@VisibleDetail(true)
@VisibleList(true)
@OrderNo("20")
@Label("Allow list of values")
@FormColumn("0")
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
@VisibleDetail(true)
@OrderNo("40")
@FormColumn("0")
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
@VisibleDetail(true)
@OrderNo("30")
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

    @Column( name = "POSITION" )
    @PersistentFieldGetter( fieldName = "position" )
@Required(true)
@VisibleDetail(true)
@OrderNo("60")
@FormColumn("0")
@VisibleList(true)
@OrderBy("1|ASC")
@VisibleReferred(true)
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


	/* Vduped Attribute setters */

	/* Relationship getters & setters */
    @PersistentToOneGetter( relName = "partnerCategory" )

    @ManyToOne( optional =  false )
    @JoinColumn( name = "PARTNER_CATEGORY_ID" )
// partnerCategory
    @UniqueKey
	/**
	  *  Access method for the partnerCategory property.
	  *  @return the current value of the partnerCategory property
	  */
	public ro.siveco.svapnt.common.entity.PartnerCategory getPartnerCategory()
	{
		return partnerCategory;
	}

	/**
	  *  Set method for the partnerCategory property.
	  */
	public void setPartnerCategory( ro.siveco.svapnt.common.entity.PartnerCategory _partnerCategory )
	{
		partnerCategory = _partnerCategory;
	}

    @PersistentToOneGetter( relName = "partnerAttribute" )

    @ManyToOne( optional =  false )
    @JoinColumn( name = "PARTNER_ATTRIBUTE_ID" )
// partnerAttribute
    @UniqueKey
	/**
	  *  Access method for the partnerAttribute property.
	  *  @return the current value of the partnerAttribute property
	  */
	public ro.siveco.svapnt.common.entity.PartnerAttribute getPartnerAttribute()
	{
		return partnerAttribute;
	}

	/**
	  *  Set method for the partnerAttribute property.
	  */
	public void setPartnerAttribute( ro.siveco.svapnt.common.entity.PartnerAttribute _partnerAttribute )
	{
		partnerAttribute = _partnerAttribute;
	}

    @PersistentToOneGetter( relName = "partnerAttributeValue" )

    @ManyToOne( optional =  true )
    @JoinColumn( name = "PARTNER_ATTRIBUTE_VALUE_ID" )
// partnerAttributeValue
	/**
	  *  Access method for the partnerAttributeValue property.
	  *  @return the current value of the partnerAttributeValue property
	  */
	public ro.siveco.svapnt.common.entity.PartnerAttributeValue getPartnerAttributeValue()
	{
		return partnerAttributeValue;
	}

	/**
	  *  Set method for the partnerAttributeValue property.
	  */
	public void setPartnerAttributeValue( ro.siveco.svapnt.common.entity.PartnerAttributeValue _partnerAttributeValue )
	{
		partnerAttributeValue = _partnerAttributeValue;
	}


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                                                                                                                                                                                                                 
	//Unique keys methods with Relations as parameters

    public static final String NQ_findByPartnerCategoryAndPartnerAttribute = "common_PartnerCategoryAttribute_UK_CATEG_ATTR_findByPartnerCategoryAndPartnerAttribute";
    public static PartnerCategoryAttribute findByPartnerCategoryAndPartnerAttribute( ro.siveco.svapnt.common.entity.PartnerCategory partnerCategory, ro.siveco.svapnt.common.entity.PartnerAttribute partnerAttribute ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add(partnerCategory.getId());
                params.add(partnerAttribute.getId());
        return ( PartnerCategoryAttribute ) getGenericSession().getSingleResult(NQ_findByPartnerCategoryAndPartnerAttribute, params);
    }

	//model defined finders


    public static PartnerCategoryAttribute find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( PartnerCategoryAttribute.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
