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
import ro.siveco.svapnt.common.entity.Person;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Persoane fizice<br>
 * </p>
 * <p>
 * Vezi documentul Dictionar Date<br>
 * </p>
 * <p>
 * Informatii de identificare persoane fizice cu studii
 * non-medicale superioare
 * </p>
 *
 * constraintNamePK PK_PERSONS
 * tableName PERSONS
 * entityID 1
 * validFrom birthDate
 * validTo validTo
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.common.session.PersonMgrLocal.JNDI_NAME )
public abstract class PersonBase extends ro.siveco.svapnt.common.entity.Partner implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "common_Person";
	    public static String getEntityName()
	    {
	        return ENTITY_NAME;
	    }

	/* Attributes */

    @Column( name = "FIRST_NAME" )
	private java.lang.String			    firstName;

    @Column( name = "LAST_NAME" )
	private java.lang.String			    lastName;

    @Column( name = "PID" )
	private java.lang.String			    pid;

    @Column( name = "BIRTH_DATE" )
	private java.util.Date			    birthDate;

    @Column( name = "PERS_TYPE" )
	private java.lang.Character			    persType;

    @Column( name = "COMMENTS" )
	private java.lang.String			    comments;

    @Transient
	private java.lang.String			    documentsTab;

    @Transient
	private java.lang.String			    bankAccountsTab;

    @Transient
	private java.lang.String			    contactsTab;

    @Transient
	private java.lang.String			    workingSchedulerTab;

    @Transient
	private java.lang.String			    permanenceSchedulerTab;

    @Transient
	private java.lang.String			    leavesTab;

    @Transient
	private java.lang.String			    enlistedTab;

    @Column( name = "SPLIT_TYPE", updatable = false )
	private java.lang.Short			    splitType;

	/* Vduped Attributes */

    @Column( name = "CODE" )
	private java.lang.String			    vdupPerson_code;

    @Column( name = "NAME" )
	private java.lang.String			    vdupPerson_name;

	/* Relationships */

    @OneToMany( mappedBy = "person", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.PersonalIdCard>     personalIdCards = new ArrayList();

    @OneToMany( mappedBy = "person", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.PersonDocument>     personDocuments = new ArrayList();

    @OneToMany( mappedBy = "person", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.PersonContact>     personContacts = new ArrayList();

	/* Attribute getters & setters */
    @Column( name = "FIRST_NAME" )
    @PersistentFieldGetter( fieldName = "firstName" )
@VisibleDetail(true)
@Required(true)
@Length("100")
@VisibleList(true)
@OrderNo("30")
	/**
	  *  Access method for the firstName property.
	  *  @return the current value of the firstName property
	  */
	public java.lang.String getFirstName()
	{
		return firstName;
	}

	/**
	  *  Set method for the firstName property.
	  */
	public void setFirstName( java.lang.String _firstName )
	{
		firstName = _firstName;
	}

    @Column( name = "LAST_NAME" )
    @PersistentFieldGetter( fieldName = "lastName" )
@OrderNo("40")
@Length("100")
@Required(true)
@VisibleDetail(true)
@VisibleList(true)
	/**
	  *  Access method for the lastName property.
	  *  @return the current value of the lastName property
	  */
	public java.lang.String getLastName()
	{
		return lastName;
	}

	/**
	  *  Set method for the lastName property.
	  */
	public void setLastName( java.lang.String _lastName )
	{
		lastName = _lastName;
	}

    @Column( name = "PID" )
    @PersistentFieldGetter( fieldName = "pid" )
@VisibleList(true)
@VisibleDetail(true)
@UniqueKey("UK_PERSONS_PID")
@OrderNo("50")
@Length("20")
@DuplicateInVerticalMapping(true)
	/**
	  *  Access method for the pid property.
	  *  @return the current value of the pid property
	  */
	public java.lang.String getPid()
	{
		return pid;
	}

	/**
	  *  Set method for the pid property.
	  */
	public void setPid( java.lang.String _pid )
	{
		pid = _pid;
	}

    @Column( name = "BIRTH_DATE" )
    @PersistentFieldGetter( fieldName = "birthDate" )
@VisibleDetail(true)
@OrderNo("60")
@VisibleList(true)
@DuplicateInVerticalMapping(true)
@Required(true)
	/**
	  *  Access method for the birthDate property.
	  *  @return the current value of the birthDate property
	  */
	public java.util.Date getBirthDate()
	{
		return birthDate;
	}

	/**
	  *  Set method for the birthDate property.
	  */
	public void setBirthDate( java.util.Date _birthDate )
	{
		birthDate = _birthDate;
	}

    @Column( name = "PERS_TYPE" )
    @PersistentFieldGetter( fieldName = "persType" )
@WidgetValues("C|Client;S|Supplier;P|Partner")
@Widget("CB")
@OrderNo("12")
@Required(true)
@VisibleList(true)
@VisibleDetail(true)
	/**
	  *  Access method for the persType property.
	  *  @return the current value of the persType property
	  */
	public java.lang.Character getPersType()
	{
		return persType;
	}

	/**
	  *  Set method for the persType property.
	  */
	public void setPersType( java.lang.Character _persType )
	{
		persType = _persType;
	}

    @Column( name = "COMMENTS" )
    @PersistentFieldGetter( fieldName = "comments" )
@VisibleDetail(true)
@Widget("TA")
@Length("200")
@OrderNo("450")
	/**
	  *  Access method for the comments property.
	  *  @return the current value of the comments property
	  */
	public java.lang.String getComments()
	{
		return comments;
	}

	/**
	  *  Set method for the comments property.
	  */
	public void setComments( java.lang.String _comments )
	{
		comments = _comments;
	}

    @Transient
@NoDatabase(true)
	/**
	  *  Access method for the documentsTab property.
	  *  @return the current value of the documentsTab property
	  */
	public java.lang.String getDocumentsTab()
	{
		return documentsTab;
	}

	/**
	  *  Set method for the documentsTab property.
	  */
	public void setDocumentsTab( java.lang.String _documentsTab )
	{
		documentsTab = _documentsTab;
	}

    @Transient
@NoDatabase(true)
	/**
	  *  Access method for the bankAccountsTab property.
	  *  @return the current value of the bankAccountsTab property
	  */
	public java.lang.String getBankAccountsTab()
	{
		return bankAccountsTab;
	}

	/**
	  *  Set method for the bankAccountsTab property.
	  */
	public void setBankAccountsTab( java.lang.String _bankAccountsTab )
	{
		bankAccountsTab = _bankAccountsTab;
	}

    @Transient
@NoDatabase(true)
	/**
	  *  Access method for the contactsTab property.
	  *  @return the current value of the contactsTab property
	  */
	public java.lang.String getContactsTab()
	{
		return contactsTab;
	}

	/**
	  *  Set method for the contactsTab property.
	  */
	public void setContactsTab( java.lang.String _contactsTab )
	{
		contactsTab = _contactsTab;
	}

    @Transient
@NoDatabase(true)
	/**
	  *  Access method for the workingSchedulerTab property.
	  *  @return the current value of the workingSchedulerTab property
	  */
	public java.lang.String getWorkingSchedulerTab()
	{
		return workingSchedulerTab;
	}

	/**
	  *  Set method for the workingSchedulerTab property.
	  */
	public void setWorkingSchedulerTab( java.lang.String _workingSchedulerTab )
	{
		workingSchedulerTab = _workingSchedulerTab;
	}

    @Transient
@NoDatabase(true)
	/**
	  *  Access method for the permanenceSchedulerTab property.
	  *  @return the current value of the permanenceSchedulerTab property
	  */
	public java.lang.String getPermanenceSchedulerTab()
	{
		return permanenceSchedulerTab;
	}

	/**
	  *  Set method for the permanenceSchedulerTab property.
	  */
	public void setPermanenceSchedulerTab( java.lang.String _permanenceSchedulerTab )
	{
		permanenceSchedulerTab = _permanenceSchedulerTab;
	}

    @Transient
@NoDatabase(true)
	/**
	  *  Access method for the leavesTab property.
	  *  @return the current value of the leavesTab property
	  */
	public java.lang.String getLeavesTab()
	{
		return leavesTab;
	}

	/**
	  *  Set method for the leavesTab property.
	  */
	public void setLeavesTab( java.lang.String _leavesTab )
	{
		leavesTab = _leavesTab;
	}

    @Transient
@NoDatabase(true)
	/**
	  *  Access method for the enlistedTab property.
	  *  @return the current value of the enlistedTab property
	  */
	public java.lang.String getEnlistedTab()
	{
		return enlistedTab;
	}

	/**
	  *  Set method for the enlistedTab property.
	  */
	public void setEnlistedTab( java.lang.String _enlistedTab )
	{
		enlistedTab = _enlistedTab;
	}

    @Column( name = "SPLIT_TYPE", updatable = false )
    @PersistentFieldGetter( fieldName = "splitType" )
@DomainSplitter(true)
	/**
	  *  Access method for the splitType property.
	  *  @return the current value of the splitType property
	  */
	public java.lang.Short getSplitType()
	{
		return splitType;
	}

	/**
	  *  Set method for the splitType property.
	  */
	public void setSplitType( java.lang.Short _splitType )
	{
		splitType = _splitType;
	}


	/* Vduped Attribute setters */
	/**
	  *  Set method for the duplicated code property.
	  */
	public void setCode( java.lang.String _code )
	{
	    super.setCode( _code );
		vdupPerson_code = _code;
	}

	/**
	  *  Set method for the duplicated name property.
	  */
	public void setName( java.lang.String _name )
	{
	    super.setName( _name );
		vdupPerson_name = _name;
	}


	/* Relationship getters & setters */

    @OneToMany( mappedBy = "person", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "personalIdCard" )
	/**
	  *  Access method for the personalIdCards property.
	  *  @return the current value of the personalIdCards property
	  */
	public Collection<ro.siveco.svapnt.common.entity.PersonalIdCard> getPersonalIdCards()
	{
		return personalIdCards;
	}

	/**
	  *  Set method for the personalIdCards property.
	  */
	public void setPersonalIdCards( Collection<ro.siveco.svapnt.common.entity.PersonalIdCard> _personalIdCards )
	{
		personalIdCards = _personalIdCards;
	}


    @OneToMany( mappedBy = "person", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "personDocument" )
	/**
	  *  Access method for the personDocuments property.
	  *  @return the current value of the personDocuments property
	  */
	public Collection<ro.siveco.svapnt.common.entity.PersonDocument> getPersonDocuments()
	{
		return personDocuments;
	}

	/**
	  *  Set method for the personDocuments property.
	  */
	public void setPersonDocuments( Collection<ro.siveco.svapnt.common.entity.PersonDocument> _personDocuments )
	{
		personDocuments = _personDocuments;
	}


    @OneToMany( mappedBy = "person", cascade = {PERSIST, MERGE, REFRESH} )
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


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                                                                                                                                                                                                                                                           
    public static final String NQ_findByCode = "common_Person_findByCode";
    public static Person findByCode( java.lang.String code ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( code);
        return ( Person ) getGenericSession().getSingleResult(NQ_findByCode, params);
    }

    public static final String NQ_findByPid = "common_Person_findByPid";
    public static Person findByPid( java.lang.String pid ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( pid);
        return ( Person ) getGenericSession().getSingleResult(NQ_findByPid, params);
    }

	//Unique keys methods with Relations as parameters
	//model defined finders


    public static Person find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( Person.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
