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
import ro.siveco.svapnt.common.entity.ContactType;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Tipuri de contacte firme, persoane fizice<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * constraintNamePK PK_CONTACT_TYPES
 * tableName CONTACT_TYPES
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.common.session.ContactTypeMgrLocal.JNDI_NAME )
public abstract class ContactTypeBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "common_ContactType";
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

	/* Vduped Attributes */

	/* Relationships */

    @OneToMany( mappedBy = "contactType", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.CompanyContact>     companyContacts = new ArrayList();

    @OneToMany( mappedBy = "contactType", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.PersonContact>     personContacts = new ArrayList();

    @OneToMany( mappedBy = "contactType", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.OrgUnitContact>     orgUnitContacts = new ArrayList();

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
@OrderNo("1")
@Required(true)
@VisibleReferred(true)
@VisibleDetail(true)
@Length("20")
@VisibleList(true)
@UniqueKey("UK_CONTACT_TYPES")
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
@VisibleDetail(true)
@OrderNo("2")
@Length("100")
@Required(true)
@VisibleList(true)
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


	/* Vduped Attribute setters */

	/* Relationship getters & setters */

    @OneToMany( mappedBy = "contactType", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "companyContact" )
	/**
	  *  Access method for the companyContacts property.
	  *  @return the current value of the companyContacts property
	  */
	public Collection<ro.siveco.svapnt.common.entity.CompanyContact> getCompanyContacts()
	{
		return companyContacts;
	}

	/**
	  *  Set method for the companyContacts property.
	  */
	public void setCompanyContacts( Collection<ro.siveco.svapnt.common.entity.CompanyContact> _companyContacts )
	{
		companyContacts = _companyContacts;
	}


    @OneToMany( mappedBy = "contactType", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "personContact" )
	/**
	  *  Access method for the personContacts property.
	  *  @return the current value of the personContacts property
	  */
	public Collection<ro.siveco.svapnt.common.entity.PersonContact> getPersonContacts()
	{
		return personContacts;
	}

	/**
	  *  Set method for the personContacts property.
	  */
	public void setPersonContacts( Collection<ro.siveco.svapnt.common.entity.PersonContact> _personContacts )
	{
		personContacts = _personContacts;
	}


    @OneToMany( mappedBy = "contactType", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "orgUnitContact" )
	/**
	  *  Access method for the orgUnitContacts property.
	  *  @return the current value of the orgUnitContacts property
	  */
	public Collection<ro.siveco.svapnt.common.entity.OrgUnitContact> getOrgUnitContacts()
	{
		return orgUnitContacts;
	}

	/**
	  *  Set method for the orgUnitContacts property.
	  */
	public void setOrgUnitContacts( Collection<ro.siveco.svapnt.common.entity.OrgUnitContact> _orgUnitContacts )
	{
		orgUnitContacts = _orgUnitContacts;
	}


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                     
    public static final String NQ_findByCode = "common_ContactType_findByCode";
    public static ContactType findByCode( java.lang.String code ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( code);
        return ( ContactType ) getGenericSession().getSingleResult(NQ_findByCode, params);
    }

	//Unique keys methods with Relations as parameters
	//model defined finders


    public static ContactType find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( ContactType.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
