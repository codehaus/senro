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
import ro.siveco.svapnt.common.entity.PartnerCategoryWrapper;
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
 * validTo validTo
 * validFrom validFrom
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.common.session.PartnerCategoryWrapperMgrLocal.JNDI_NAME )
public abstract class PartnerCategoryWrapperBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "common_PartnerCategoryWrapper";
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

    @Column( name = "VALID_FROM" )
	private java.util.Date			    validFrom;

    @Column( name = "VALID_TO" )
	private java.util.Date			    validTo;

	/* Vduped Attributes */

	/* Relationships */

    @ManyToOne( optional =  false )
    @JoinColumn( name = "PARTNER_ID" )
// partner
	private ro.siveco.svapnt.common.entity.Partner			    partner;

    @ManyToOne( optional =  false )
    @JoinColumn( name = "CATEGORY_ID" )
// category
	private ro.siveco.svapnt.common.entity.PartnerCategory			    category;

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

    @Column( name = "VALID_FROM" )
    @PersistentFieldGetter( fieldName = "validFrom" )
@VisibleList(true)
@VisibleDetail(true)
@Required(true)
@OrderNo("20")
	/**
	  *  Access method for the validFrom property.
	  *  @return the current value of the validFrom property
	  */
	public java.util.Date getValidFrom()
	{
		return validFrom;
	}

	/**
	  *  Set method for the validFrom property.
	  */
	public void setValidFrom( java.util.Date _validFrom )
	{
		validFrom = _validFrom;
	}

    @Column( name = "VALID_TO" )
    @PersistentFieldGetter( fieldName = "validTo" )
@VisibleList(true)
@VisibleDetail(true)
@OrderNo("30")
	/**
	  *  Access method for the validTo property.
	  *  @return the current value of the validTo property
	  */
	public java.util.Date getValidTo()
	{
		return validTo;
	}

	/**
	  *  Set method for the validTo property.
	  */
	public void setValidTo( java.util.Date _validTo )
	{
		validTo = _validTo;
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

    @PersistentToOneGetter( relName = "category" )

    @ManyToOne( optional =  false )
    @JoinColumn( name = "CATEGORY_ID" )
// category
    @UniqueKey
	/**
	  *  Access method for the category property.
	  *  @return the current value of the category property
	  */
	public ro.siveco.svapnt.common.entity.PartnerCategory getCategory()
	{
		return category;
	}

	/**
	  *  Set method for the category property.
	  */
	public void setCategory( ro.siveco.svapnt.common.entity.PartnerCategory _category )
	{
		category = _category;
	}


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                                                                                                                                                                                      
	//Unique keys methods with Relations as parameters

    public static final String NQ_findByPartnerAndCategory = "common_PartnerCategoryWrapper_UK_PARTNER_CATEGORY_findByPartnerAndCategory";
    public static PartnerCategoryWrapper findByPartnerAndCategory( ro.siveco.svapnt.common.entity.Partner partner, ro.siveco.svapnt.common.entity.PartnerCategory category ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add(partner.getId());
                params.add(category.getId());
        return ( PartnerCategoryWrapper ) getGenericSession().getSingleResult(NQ_findByPartnerAndCategory, params);
    }

	//model defined finders


    public static PartnerCategoryWrapper find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( PartnerCategoryWrapper.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
