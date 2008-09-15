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
import ro.siveco.svapnt.common.entity.Partner;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Firme si persoane fizice. Tine de asemenea inregistrari
 * corespondente unitatilor organizatorice, salariatilor,
 * magaziilor, bancilor. - Entitate din care se deriveaza unitati
 * organizatorice, banci, companii, salariati<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * constraintNamePK PK_PARTNERS
 * tableName PARTNERS
 * validFrom validFrom
 * validTo validTo
 *
 */

 

 
@MappedSuperclass
@Inheritance( strategy = JOINED )
@SessionName( name = ro.siveco.svapnt.common.session.PartnerMgrLocal.JNDI_NAME )
public abstract class PartnerBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "common_Partner";
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

    @Column( name = "NAME" )
	private java.lang.String			    name;

    @Column( name = "TYPE", updatable = false )
	private java.lang.Short			    type;

    @Column( name = "VALID_FROM" )
	private java.util.Date			    validFrom;

    @Column( name = "VALID_TO" )
	private java.util.Date			    validTo;

    @Column( name = "CATEGORY_VALID_FROM" )
	private java.util.Date			    categoryValidFrom;

	/* Vduped Attributes */

	/* Relationships */

    @OneToMany( mappedBy = "partner", cascade = ALL )
	private Collection<ro.siveco.svapnt.common.entity.PartnerZone>     partnerZones = new ArrayList();

    @ManyToOne( optional =  false )
    @JoinColumn( name = "COUNTRY_ID" )
// country
	private ro.siveco.svapnt.common.entity.Country			    country;

    @ManyToOne( optional =  true )
    @JoinColumn( name = "CITY_ID" )
// city
	private ro.siveco.svapnt.common.entity.City			    city;

    @OneToMany( mappedBy = "partner", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.PartnerAddress>     partnerAddresses = new ArrayList();

    @OneToMany( mappedBy = "partner", cascade = ALL )
	private Collection<ro.siveco.svapnt.common.entity.BankAccount>     bankAccounts = new ArrayList();

    @OneToMany( mappedBy = "partner", cascade = ALL )
	private Collection<ro.siveco.svapnt.common.entity.PartnerCategoryWrapper>     partnerCategoryWrappers = new ArrayList();

    @ManyToOne( optional =  true )
    @JoinColumn( name = "DEFAULT_CATEGORY_ID" )
// defaultCategory
	private ro.siveco.svapnt.common.entity.PartnerCategory			    defaultCategory;

    @OneToMany( mappedBy = "partner", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.PartnerAttributeWrapper>     partnerAttributeWrappers = new ArrayList();

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

    @Column( name = "CODE" )
    @PersistentFieldGetter( fieldName = "code" )
@Length("20")
@OrderNo("10")
@Required(true)
@UniqueKey("UK_PARTNERS")
@DuplicateInVerticalMapping(true)
@VisibleList(true)
@VisibleDetail(true)
@VisibleReferred(true)
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

    @Column( name = "NAME" )
    @PersistentFieldGetter( fieldName = "name" )
@VisibleReferred(true)
@VisibleDetail(true)
@Required(true)
@Length("100")
@VisibleList(true)
@DuplicateInVerticalMapping(true)
@OrderNo("20")
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

    @Column( name = "TYPE", updatable = false )
    @PersistentFieldGetter( fieldName = "type" )
@UniqueKey("UK_PARTNERS")
@VisibleReferred(true)
@Required(true)
@DomainSplitter(true)
@VisibleList(true)
@OrderNo("11")
	/**
	  *  Access method for the type property.
	  *  @return the current value of the type property
	  */
	public java.lang.Short getType()
	{
		return type;
	}

	/**
	  *  Set method for the type property.
	  */
	public void setType( java.lang.Short _type )
	{
		type = _type;
	}

    @Column( name = "VALID_FROM" )
    @PersistentFieldGetter( fieldName = "validFrom" )
@VisibleDetail(true)
@Required(true)
@VisibleList(true)
@OrderNo("510")
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
@VisibleList(true)
@VisibleDetail(true)
@OrderNo("520")
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

    @Column( name = "CATEGORY_VALID_FROM" )
    @PersistentFieldGetter( fieldName = "categoryValidFrom" )
@OrderNo("355")
@VisibleDetail(true)
	/**
	  *  Access method for the categoryValidFrom property.
	  *  @return the current value of the categoryValidFrom property
	  */
	public java.util.Date getCategoryValidFrom()
	{
		return categoryValidFrom;
	}

	/**
	  *  Set method for the categoryValidFrom property.
	  */
	public void setCategoryValidFrom( java.util.Date _categoryValidFrom )
	{
		categoryValidFrom = _categoryValidFrom;
	}


	/* Vduped Attribute setters */

	/* Relationship getters & setters */

    @OneToMany( mappedBy = "partner", cascade = ALL )
    @Transactional
    @PersistentToManyGetter( relName = "partnerZone" )
	/**
	  *  Access method for the partnerZones property.
	  *  @return the current value of the partnerZones property
	  */
	public Collection<ro.siveco.svapnt.common.entity.PartnerZone> getPartnerZones()
	{
		return partnerZones;
	}

	/**
	  *  Set method for the partnerZones property.
	  */
	public void setPartnerZones( Collection<ro.siveco.svapnt.common.entity.PartnerZone> _partnerZones )
	{
		partnerZones = _partnerZones;
	}

    @PersistentToOneGetter( relName = "country" )

    @ManyToOne( optional =  false )
    @JoinColumn( name = "COUNTRY_ID" )
// country
	/**
	  *  Access method for the country property.
	  *  @return the current value of the country property
	  */
	public ro.siveco.svapnt.common.entity.Country getCountry()
	{
		return country;
	}

	/**
	  *  Set method for the country property.
	  */
	public void setCountry( ro.siveco.svapnt.common.entity.Country _country )
	{
		country = _country;
	}

    @PersistentToOneGetter( relName = "city" )

    @ManyToOne( optional =  true )
    @JoinColumn( name = "CITY_ID" )
// city
	/**
	  *  Access method for the city property.
	  *  @return the current value of the city property
	  */
	public ro.siveco.svapnt.common.entity.City getCity()
	{
		return city;
	}

	/**
	  *  Set method for the city property.
	  */
	public void setCity( ro.siveco.svapnt.common.entity.City _city )
	{
		city = _city;
	}


    @OneToMany( mappedBy = "partner", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "partnerAddress" )
	/**
	  *  Access method for the partnerAddresses property.
	  *  @return the current value of the partnerAddresses property
	  */
	public Collection<ro.siveco.svapnt.common.entity.PartnerAddress> getPartnerAddresses()
	{
		return partnerAddresses;
	}

	/**
	  *  Set method for the partnerAddresses property.
	  */
	public void setPartnerAddresses( Collection<ro.siveco.svapnt.common.entity.PartnerAddress> _partnerAddresses )
	{
		partnerAddresses = _partnerAddresses;
	}


    @OneToMany( mappedBy = "partner", cascade = ALL )
    @Transactional
    @PersistentToManyGetter( relName = "bankAccount" )
	/**
	  *  Access method for the bankAccounts property.
	  *  @return the current value of the bankAccounts property
	  */
	public Collection<ro.siveco.svapnt.common.entity.BankAccount> getBankAccounts()
	{
		return bankAccounts;
	}

	/**
	  *  Set method for the bankAccounts property.
	  */
	public void setBankAccounts( Collection<ro.siveco.svapnt.common.entity.BankAccount> _bankAccounts )
	{
		bankAccounts = _bankAccounts;
	}


    @OneToMany( mappedBy = "partner", cascade = ALL )
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

    @PersistentToOneGetter( relName = "defaultCategory" )

    @ManyToOne( optional =  true )
    @JoinColumn( name = "DEFAULT_CATEGORY_ID" )
// defaultCategory
	/**
	  *  Access method for the defaultCategory property.
	  *  @return the current value of the defaultCategory property
	  */
	public ro.siveco.svapnt.common.entity.PartnerCategory getDefaultCategory()
	{
		return defaultCategory;
	}

	/**
	  *  Set method for the defaultCategory property.
	  */
	public void setDefaultCategory( ro.siveco.svapnt.common.entity.PartnerCategory _defaultCategory )
	{
		defaultCategory = _defaultCategory;
	}


    @OneToMany( mappedBy = "partner", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "partnerAttributeWrapper" )
	/**
	  *  Access method for the partnerAttributeWrappers property.
	  *  @return the current value of the partnerAttributeWrappers property
	  */
	public Collection<ro.siveco.svapnt.common.entity.PartnerAttributeWrapper> getPartnerAttributeWrappers()
	{
		return partnerAttributeWrappers;
	}

	/**
	  *  Set method for the partnerAttributeWrappers property.
	  */
	public void setPartnerAttributeWrappers( Collection<ro.siveco.svapnt.common.entity.PartnerAttributeWrapper> _partnerAttributeWrappers )
	{
		partnerAttributeWrappers = _partnerAttributeWrappers;
	}


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                                                                                                                                                                                           
    public static final String NQ_findByCodeAndType = "common_Partner_findByCodeAndType";
    public static Partner findByCodeAndType( java.lang.String code, java.lang.Short type ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( code);
                params.add( type);
        return ( Partner ) getGenericSession().getSingleResult(NQ_findByCodeAndType, params);
    }

	//Unique keys methods with Relations as parameters
	//model defined finders


    public static Partner find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( Partner.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
