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
import ro.siveco.svapnt.common.entity.PartnerDisplayOption;
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
@SessionName( name = ro.siveco.svapnt.common.session.PartnerDisplayOptionsMgrLocal.JNDI_NAME )
public abstract class PartnerDisplayOptionBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "common_PartnerDisplayOption";
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

    @Column( name = "WORKING_SCHEDULER" )
	private java.lang.String			    workingScheduler;

    @Column( name = "ENLISTED" )
	private java.lang.String			    enlisted;

    @Column( name = "EMPLOYEES" )
	private java.lang.String			    employees;

    @Column( name = "PERMANENCE_SCHEDULER" )
	private java.lang.String			    permanenceScheduler;

    @Column( name = "TRANSPORT_VEHICLES" )
	private java.lang.String			    transportVehicles;

    @Column( name = "MEDICAL_DEVICES" )
	private java.lang.String			    medicalDevices;

    @Column( name = "SUBUNITS" )
	private java.lang.String			    subunits;

    @Column( name = "LEAVES" )
	private java.lang.String			    leaves;

    @Column( name = "DOCUMENTS" )
	private java.lang.String			    documents;

    @Column( name = "BANK_ACCOUNTS" )
	private java.lang.String			    bankAccounts;

    @Column( name = "CONTACTS" )
	private java.lang.String			    contacts;

	/* Vduped Attributes */

	/* Relationships */

    @ManyToOne( optional =  false )
    @JoinColumn( name = "PARTNER_CATEGORY_ID" )
// partnerCategory
	private ro.siveco.svapnt.common.entity.PartnerCategory			    partnerCategory;

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

    @Column( name = "WORKING_SCHEDULER" )
    @PersistentFieldGetter( fieldName = "workingScheduler" )
@VisibleDetail(true)
@VisibleList(true)
@OrderNo("50")
	/**
	  *  Access method for the workingScheduler property.
	  *  @return the current value of the workingScheduler property
	  */
	public java.lang.String getWorkingScheduler()
	{
		return workingScheduler;
	}

	/**
	  *  Set method for the workingScheduler property.
	  */
	public void setWorkingScheduler( java.lang.String _workingScheduler )
	{
		workingScheduler = _workingScheduler;
	}

    @Column( name = "ENLISTED" )
    @PersistentFieldGetter( fieldName = "enlisted" )
@VisibleDetail(true)
@VisibleList(true)
@OrderNo("70")
	/**
	  *  Access method for the enlisted property.
	  *  @return the current value of the enlisted property
	  */
	public java.lang.String getEnlisted()
	{
		return enlisted;
	}

	/**
	  *  Set method for the enlisted property.
	  */
	public void setEnlisted( java.lang.String _enlisted )
	{
		enlisted = _enlisted;
	}

    @Column( name = "EMPLOYEES" )
    @PersistentFieldGetter( fieldName = "employees" )
@VisibleList(true)
@VisibleDetail(true)
@OrderNo("30")
	/**
	  *  Access method for the employees property.
	  *  @return the current value of the employees property
	  */
	public java.lang.String getEmployees()
	{
		return employees;
	}

	/**
	  *  Set method for the employees property.
	  */
	public void setEmployees( java.lang.String _employees )
	{
		employees = _employees;
	}

    @Column( name = "PERMANENCE_SCHEDULER" )
    @PersistentFieldGetter( fieldName = "permanenceScheduler" )
@VisibleDetail(true)
@VisibleList(true)
@OrderNo("60")
	/**
	  *  Access method for the permanenceScheduler property.
	  *  @return the current value of the permanenceScheduler property
	  */
	public java.lang.String getPermanenceScheduler()
	{
		return permanenceScheduler;
	}

	/**
	  *  Set method for the permanenceScheduler property.
	  */
	public void setPermanenceScheduler( java.lang.String _permanenceScheduler )
	{
		permanenceScheduler = _permanenceScheduler;
	}

    @Column( name = "TRANSPORT_VEHICLES" )
    @PersistentFieldGetter( fieldName = "transportVehicles" )
@VisibleDetail(true)
@VisibleList(true)
@OrderNo("10")
	/**
	  *  Access method for the transportVehicles property.
	  *  @return the current value of the transportVehicles property
	  */
	public java.lang.String getTransportVehicles()
	{
		return transportVehicles;
	}

	/**
	  *  Set method for the transportVehicles property.
	  */
	public void setTransportVehicles( java.lang.String _transportVehicles )
	{
		transportVehicles = _transportVehicles;
	}

    @Column( name = "MEDICAL_DEVICES" )
    @PersistentFieldGetter( fieldName = "medicalDevices" )
@VisibleList(true)
@VisibleDetail(true)
@OrderNo("40")
	/**
	  *  Access method for the medicalDevices property.
	  *  @return the current value of the medicalDevices property
	  */
	public java.lang.String getMedicalDevices()
	{
		return medicalDevices;
	}

	/**
	  *  Set method for the medicalDevices property.
	  */
	public void setMedicalDevices( java.lang.String _medicalDevices )
	{
		medicalDevices = _medicalDevices;
	}

    @Column( name = "SUBUNITS" )
    @PersistentFieldGetter( fieldName = "subunits" )
@VisibleList(true)
@VisibleDetail(true)
@OrderNo("10")
	/**
	  *  Access method for the subunits property.
	  *  @return the current value of the subunits property
	  */
	public java.lang.String getSubunits()
	{
		return subunits;
	}

	/**
	  *  Set method for the subunits property.
	  */
	public void setSubunits( java.lang.String _subunits )
	{
		subunits = _subunits;
	}

    @Column( name = "LEAVES" )
    @PersistentFieldGetter( fieldName = "leaves" )
@VisibleDetail(true)
@VisibleList(true)
@OrderNo("110")
	/**
	  *  Access method for the leaves property.
	  *  @return the current value of the leaves property
	  */
	public java.lang.String getLeaves()
	{
		return leaves;
	}

	/**
	  *  Set method for the leaves property.
	  */
	public void setLeaves( java.lang.String _leaves )
	{
		leaves = _leaves;
	}

    @Column( name = "DOCUMENTS" )
    @PersistentFieldGetter( fieldName = "documents" )
@VisibleList(true)
@VisibleDetail(true)
@OrderNo("100")
	/**
	  *  Access method for the documents property.
	  *  @return the current value of the documents property
	  */
	public java.lang.String getDocuments()
	{
		return documents;
	}

	/**
	  *  Set method for the documents property.
	  */
	public void setDocuments( java.lang.String _documents )
	{
		documents = _documents;
	}

    @Column( name = "BANK_ACCOUNTS" )
    @PersistentFieldGetter( fieldName = "bankAccounts" )
@VisibleDetail(true)
@VisibleList(true)
@OrderNo("80")
	/**
	  *  Access method for the bankAccounts property.
	  *  @return the current value of the bankAccounts property
	  */
	public java.lang.String getBankAccounts()
	{
		return bankAccounts;
	}

	/**
	  *  Set method for the bankAccounts property.
	  */
	public void setBankAccounts( java.lang.String _bankAccounts )
	{
		bankAccounts = _bankAccounts;
	}

    @Column( name = "CONTACTS" )
    @PersistentFieldGetter( fieldName = "contacts" )
@VisibleDetail(true)
@VisibleList(true)
@OrderNo("90")
	/**
	  *  Access method for the contacts property.
	  *  @return the current value of the contacts property
	  */
	public java.lang.String getContacts()
	{
		return contacts;
	}

	/**
	  *  Set method for the contacts property.
	  */
	public void setContacts( java.lang.String _contacts )
	{
		contacts = _contacts;
	}


	/* Vduped Attribute setters */

	/* Relationship getters & setters */
    @PersistentToOneGetter( relName = "partnerCategory" )

    @ManyToOne( optional =  false )
    @JoinColumn( name = "PARTNER_CATEGORY_ID" )
// partnerCategory
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


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                              
	//Unique keys methods with Relations as parameters
	//model defined finders


    public static PartnerDisplayOption find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( PartnerDisplayOption.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
