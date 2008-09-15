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
import ro.siveco.svapnt.common.entity.PartnerCategory;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Categorii de parteneri<br>
 * </p>
 * <p>
 * Vezi documentul dictionar de date<br>
 * </p>
 * <p>
 * Categorii de contribuabili
 * </p>
 *
 * validFrom validFrom
 * validTo validTo
 * formColumns 5
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.common.session.PartnerCategoryMgrLocal.JNDI_NAME )
public abstract class PartnerCategoryBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "common_PartnerCategory";
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

    @Column( name = "CODE" )
	private java.lang.String			    code;

    @Column( name = "DESCRIPTION" )
	private java.lang.String			    description;

    @Column( name = "NAME" )
	private java.lang.String			    name;

    @Column( name = "VALID_FROM" )
	private java.util.Date			    validFrom;

    @Column( name = "VALID_TO" )
	private java.util.Date			    validTo;

    @Column( name = "BANK" )
	private java.lang.String			    bank;

    @Column( name = "COMPANY" )
	private java.lang.String			    company;

    @Column( name = "STORE" )
	private java.lang.String			    store;

    @Column( name = "PERSON" )
	private java.lang.String			    person;

    @Column( name = "ORG_UNIT" )
	private java.lang.String			    orgUnit;

	/* Vduped Attributes */

	/* Relationships */

    @OneToMany( mappedBy = "partnerCategory", cascade = ALL )
	private Collection<ro.siveco.svapnt.common.entity.PartnerCategoryAttribute>     partnerCategoryAttributes = new ArrayList();

    @OneToMany( mappedBy = "category", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.PartnerCategoryWrapper>     partnerCategoryWrappers = new ArrayList();

    @OneToMany( mappedBy = "defaultCategory", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.Partner>     partners = new ArrayList();

    @OneToMany( mappedBy = "partnerCategory", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.PartnerDisplayOption>     partnerDisplayOption = new ArrayList();

	/* Attribute getters & setters */
    @Column( name = "ID" )
    @PersistentFieldGetter( fieldName = "id" )
    @VisibleReferred
    @UniqueKey
    @Id
    @GeneratedValue( strategy = SEQUENCE, generator = "SVAPNT_UTILS_SEQ" )
    @SequenceGenerator( name = "SVAPNT_UTILS_SEQ", sequenceName = "SVAPNT_UTILS_SEQ" )
@DatabaseRequired(true)
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
@DatabaseRequired(true)
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

    @Column( name = "CODE" )
    @PersistentFieldGetter( fieldName = "code" )
@OrderNo("10")
@VisibleList(true)
@UniqueKey("UK_PARTNER_CATEG")
@Length("10")
@VisibleDetail(true)
@Required(true)
@VisibleReferred(true)
@VisibleInCombo(true)
@Unchangeable(true)
	/**
	  *  Access method for the code property.
	  *  @return the current value of the code property
	  */
	public java.lang.String getCode()
	{
		return code;
	}

	/**
	  *  Set method for the code property.
	  */
	public void setCode( java.lang.String _code )
	{
		code = _code;
	}

    @Column( name = "DESCRIPTION" )
    @PersistentFieldGetter( fieldName = "description" )
@Length("200")
@OrderNo("30")
@VisibleList(true)
@VisibleDetail(true)
@Widget("TA")
@Unfiltered(true)
@FormColumn("0")
@VisibleInCombo(true)
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

    @Column( name = "NAME" )
    @PersistentFieldGetter( fieldName = "name" )
@VisibleList(true)
@Length("100")
@VisibleDetail(true)
@OrderNo("20")
@Required(true)
@VisibleReferred(true)
	/**
	  *  Access method for the name property.
	  *  @return the current value of the name property
	  */
	public java.lang.String getName()
	{
		return name;
	}

	/**
	  *  Set method for the name property.
	  */
	public void setName( java.lang.String _name )
	{
		name = _name;
	}

    @Column( name = "VALID_FROM" )
    @PersistentFieldGetter( fieldName = "validFrom" )
@VisibleDetail(true)
@VisibleList(true)
@OrderNo("90")
@Required(true)
@FormColumn("0")
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
@VisibleDetail(true)
@VisibleList(true)
@OrderNo("100")
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

    @Column( name = "BANK" )
    @PersistentFieldGetter( fieldName = "bank" )
@VisibleDetail(true)
@VisibleList(true)
@OrderNo("40")
@Unchangeable(true)
	/**
	  *  Access method for the bank property.
	  *  @return the current value of the bank property
	  */
	public java.lang.String getBank()
	{
		return bank;
	}

	/**
	  *  Set method for the bank property.
	  */
	public void setBank( java.lang.String _bank )
	{
		bank = _bank;
	}

    @Column( name = "COMPANY" )
    @PersistentFieldGetter( fieldName = "company" )
@VisibleDetail(true)
@VisibleList(true)
@OrderNo("50")
@Unchangeable(true)
	/**
	  *  Access method for the company property.
	  *  @return the current value of the company property
	  */
	public java.lang.String getCompany()
	{
		return company;
	}

	/**
	  *  Set method for the company property.
	  */
	public void setCompany( java.lang.String _company )
	{
		company = _company;
	}

    @Column( name = "STORE" )
    @PersistentFieldGetter( fieldName = "store" )
@VisibleDetail(true)
@VisibleList(true)
@OrderNo("60")
@Unchangeable(true)
	/**
	  *  Access method for the store property.
	  *  @return the current value of the store property
	  */
	public java.lang.String getStore()
	{
		return store;
	}

	/**
	  *  Set method for the store property.
	  */
	public void setStore( java.lang.String _store )
	{
		store = _store;
	}

    @Column( name = "PERSON" )
    @PersistentFieldGetter( fieldName = "person" )
@VisibleList(true)
@VisibleDetail(true)
@OrderNo("70")
@Unchangeable(true)
	/**
	  *  Access method for the person property.
	  *  @return the current value of the person property
	  */
	public java.lang.String getPerson()
	{
		return person;
	}

	/**
	  *  Set method for the person property.
	  */
	public void setPerson( java.lang.String _person )
	{
		person = _person;
	}

    @Column( name = "ORG_UNIT" )
    @PersistentFieldGetter( fieldName = "orgUnit" )
@VisibleList(true)
@VisibleDetail(true)
@OrderNo("80")
@Unchangeable(true)
	/**
	  *  Access method for the orgUnit property.
	  *  @return the current value of the orgUnit property
	  */
	public java.lang.String getOrgUnit()
	{
		return orgUnit;
	}

	/**
	  *  Set method for the orgUnit property.
	  */
	public void setOrgUnit( java.lang.String _orgUnit )
	{
		orgUnit = _orgUnit;
	}


	/* Vduped Attribute setters */

	/* Relationship getters & setters */

    @OneToMany( mappedBy = "partnerCategory", cascade = ALL )
    @Transactional
    @PersistentToManyGetter( relName = "partnerCategoryAttribute" )
	/**
	  *  Access method for the partnerCategoryAttributes property.
	  *  @return the current value of the partnerCategoryAttributes property
	  */
	public Collection<ro.siveco.svapnt.common.entity.PartnerCategoryAttribute> getPartnerCategoryAttributes()
	{
		return partnerCategoryAttributes;
	}

	/**
	  *  Set method for the partnerCategoryAttributes property.
	  */
	public void setPartnerCategoryAttributes( Collection<ro.siveco.svapnt.common.entity.PartnerCategoryAttribute> _partnerCategoryAttributes )
	{
		partnerCategoryAttributes = _partnerCategoryAttributes;
	}


    @OneToMany( mappedBy = "category", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "partnerCategoryWrapper" )
	/**
	  *  Access method for the partnerCategoryWrappers property.
	  *  @return the current value of the partnerCategoryWrappers property
	  */
	public Collection<ro.siveco.svapnt.common.entity.PartnerCategoryWrapper> getPartnerCategoryWrappers()
	{
		return partnerCategoryWrappers;
	}

	/**
	  *  Set method for the partnerCategoryWrappers property.
	  */
	public void setPartnerCategoryWrappers( Collection<ro.siveco.svapnt.common.entity.PartnerCategoryWrapper> _partnerCategoryWrappers )
	{
		partnerCategoryWrappers = _partnerCategoryWrappers;
	}


    @OneToMany( mappedBy = "defaultCategory", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "partner" )
	/**
	  *  Access method for the partners property.
	  *  @return the current value of the partners property
	  */
	public Collection<ro.siveco.svapnt.common.entity.Partner> getPartners()
	{
		return partners;
	}

	/**
	  *  Set method for the partners property.
	  */
	public void setPartners( Collection<ro.siveco.svapnt.common.entity.Partner> _partners )
	{
		partners = _partners;
	}


    @OneToMany( mappedBy = "partnerCategory", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "partnerDisplayOption" )
	/**
	  *  Access method for the partnerDisplayOption property.
	  *  @return the current value of the partnerDisplayOption property
	  */
	public Collection<ro.siveco.svapnt.common.entity.PartnerDisplayOption> getPartnerDisplayOption()
	{
		return partnerDisplayOption;
	}

	/**
	  *  Set method for the partnerDisplayOption property.
	  */
	public void setPartnerDisplayOption( Collection<ro.siveco.svapnt.common.entity.PartnerDisplayOption> _partnerDisplayOption )
	{
		partnerDisplayOption = _partnerDisplayOption;
	}


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                                                                             
    public static final String NQ_findByCode = "common_PartnerCategory_findByCode";
    public static PartnerCategory findByCode( java.lang.String code ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( code);
        return ( PartnerCategory ) getGenericSession().getSingleResult(NQ_findByCode, params);
    }

	//Unique keys methods with Relations as parameters
	//model defined finders


    public static PartnerCategory find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( PartnerCategory.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
