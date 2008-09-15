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
import ro.siveco.svapnt.common.entity.Company;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Companii<br>
 * </p>
 * <p>
 * Vezi documentul Dictionar Date<br>
 * </p>
 * <p>
 * Date despre contribuabil <br>
 * </p>
 * <p>
 * Informatii de identificare a persoanelor juridice<br>
 * </p>
 * <p>
 * Date de identificare a furnizorului de servicii medicale ?i
 * farmaceutice<br>
 * </p>
 * <p>
 * Date cu privire la partenerii sistemului
 * </p>
 *
 * tableName COMPANIES
 * constraintNamePK PK_COMPANIES
 * entityID 1
 * validFrom validFrom
 * validTo validTo
 * entityLevel entityLevel
 * finders findByFiscalCode|true||
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.common.session.CompanyMgrLocal.JNDI_NAME )
public abstract class CompanyBase extends ro.siveco.svapnt.common.entity.PartnerOrgUnit implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "common_Company";
	    public static String getEntityName()
	    {
	        return ENTITY_NAME;
	    }

	/* Attributes */

    @Column( name = "UIC" )
	private java.lang.String			    uic;

    @Column( name = "UICDATE" )
	private java.util.Date			    uicDate;

    @Column( name = "REGISTER_NUMBER" )
	private java.lang.String			    registerNumber;

    @Column( name = "REGISTER_DATE" )
	private java.util.Date			    registerDate;

    @Column( name = "COMP_TYPE" )
	private java.lang.Character			    compType;

    @Column( name = "CREDIBILITY" )
	private java.lang.Long			    credibility;

    @Column( name = "CREDIT_LIMIT" )
	private java.lang.Double			    creditLimit;

    @Column( name = "SIRUES_CODE" )
	private java.lang.String			    siruesCode;

    @Column( name = "VATPAYER_FLAG" )
	private java.lang.String			    vatPayerFlag;

    @Transient
	private java.lang.String			    employeesTab;

    @Transient
	private java.lang.String			    enlistedTab;

    @Transient
	private java.lang.String			    permanenceSchedulerTab;

    @Transient
	private java.lang.String			    workingSchedulerTab;

    @Transient
	private java.lang.String			    medicalDevicesTab;

    @Transient
	private java.lang.String			    transportVehiclesTab;

    @Transient
	private java.lang.String			    subunitsTab;

    @Transient
	private java.lang.String			    documentsTab;

    @Transient
	private java.lang.String			    bankAccountsTab;

    @Transient
	private java.lang.String			    contactsTab;

    @Column( name = "FISCAL_ATTRIBUTE" )
	private java.lang.String			    fiscalAttribute;

    @Column( name = "FISCAL_ATTRIBUTE_DATE" )
	private java.util.Date			    fiscalAttributeDate;

    @Column( name = "COMP_FLAG", updatable = false )
	private java.lang.Short			    compFlag;

	/* Vduped Attributes */

    @Column( name = "CODE" )
	private java.lang.String			    vdupCompany_code;

    @Column( name = "NAME" )
	private java.lang.String			    vdupCompany_name;

	/* Relationships */

    @OneToMany( mappedBy = "company", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.CompanyContact>     companyContacts = new ArrayList();

    @ManyToOne( optional =  false )
    @JoinColumn( name = "CMP_ORG_ID" )
// companyOrg
	private ro.siveco.svapnt.common.entity.CompanyOrg			    companyOrg;

    @ManyToOne( optional =  false )
    @JoinColumn( name = "CRNCY_ID" )
// currency
	private ro.siveco.svapnt.common.entity.Currency			    currency;

    @ManyToOne( optional =  true )
    @JoinColumn( name = "CMP_CLASSIF_ID" )
// companyClassification
	private ro.siveco.svapnt.common.entity.CompanyClassification			    companyClassification;

    @ManyToOne( optional =  true )
    @JoinColumn( name = "CMP_PROFILE_ID" )
// companyProfile
	private ro.siveco.svapnt.common.entity.CompanyProfile			    companyProfile;

    @OneToMany( mappedBy = "partner", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.CompanyDocument>     companyDocuments = new ArrayList();

	/* Attribute getters & setters */
    @Column( name = "UIC" )
    @PersistentFieldGetter( fieldName = "uic" )
@VisibleDetail(true)
@Length("20")
@VisibleReferred(true)
@Required(true)
@OrderNo("60")
@VisibleList(true)
@Label("CUI")
@UniqueKey("UK_CMPNS_UIC")
	/**
	  *  Access method for the uic property.
	  *  @return the current value of the uic property
	  */
	public java.lang.String getUic()
	{
		return uic;
	}

	/**
	  *  Set method for the uic property.
	  */
	public void setUic( java.lang.String _uic )
	{
		uic = _uic;
	}

    @Column( name = "UICDATE" )
    @PersistentFieldGetter( fieldName = "uicDate" )
@VisibleDetail(true)
@VisibleList(true)
@OrderNo("80")
	/**
	  *  Access method for the uicDate property.
	  *  @return the current value of the uicDate property
	  */
	public java.util.Date getUicDate()
	{
		return uicDate;
	}

	/**
	  *  Set method for the uicDate property.
	  */
	public void setUicDate( java.util.Date _uicDate )
	{
		uicDate = _uicDate;
	}

    @Column( name = "REGISTER_NUMBER" )
    @PersistentFieldGetter( fieldName = "registerNumber" )
@OrderNo("150")
@Length("20")
@VisibleList(true)
@VisibleDetail(true)
	/**
	  *  Access method for the registerNumber property.
	  *  @return the current value of the registerNumber property
	  */
	public java.lang.String getRegisterNumber()
	{
		return registerNumber;
	}

	/**
	  *  Set method for the registerNumber property.
	  */
	public void setRegisterNumber( java.lang.String _registerNumber )
	{
		registerNumber = _registerNumber;
	}

    @Column( name = "REGISTER_DATE" )
    @PersistentFieldGetter( fieldName = "registerDate" )
@VisibleList(true)
@VisibleDetail(true)
@OrderNo("160")
	/**
	  *  Access method for the registerDate property.
	  *  @return the current value of the registerDate property
	  */
	public java.util.Date getRegisterDate()
	{
		return registerDate;
	}

	/**
	  *  Set method for the registerDate property.
	  */
	public void setRegisterDate( java.util.Date _registerDate )
	{
		registerDate = _registerDate;
	}

    @Column( name = "COMP_TYPE" )
    @PersistentFieldGetter( fieldName = "compType" )
@Widget("CB")
@WidgetValues("C|Client;S|Supplier;P|Partner")
@OrderNo("15")
@VisibleList(true)
@VisibleDetail(true)
	/**
	  *  Access method for the compType property.
	  *  @return the current value of the compType property
	  */
	public java.lang.Character getCompType()
	{
		return compType;
	}

	/**
	  *  Set method for the compType property.
	  */
	public void setCompType( java.lang.Character _compType )
	{
		compType = _compType;
	}

    @Column( name = "CREDIBILITY" )
    @PersistentFieldGetter( fieldName = "credibility" )
@OrderNo("210")
@Widget("CB")
@WidgetValues("1|Very good;2|Good;3|Normal;4|Bad;5|Very bad")
	/**
	  *  Access method for the credibility property.
	  *  @return the current value of the credibility property
	  */
	public java.lang.Long getCredibility()
	{
		return credibility;
	}

	/**
	  *  Set method for the credibility property.
	  */
	public void setCredibility( java.lang.Long _credibility )
	{
		credibility = _credibility;
	}

    @Column( name = "CREDIT_LIMIT" )
    @PersistentFieldGetter( fieldName = "creditLimit" )
@OrderNo("220")
	/**
	  *  Access method for the creditLimit property.
	  *  @return the current value of the creditLimit property
	  */
	public java.lang.Double getCreditLimit()
	{
		return creditLimit;
	}

	/**
	  *  Set method for the creditLimit property.
	  */
	public void setCreditLimit( java.lang.Double _creditLimit )
	{
		creditLimit = _creditLimit;
	}

    @Column( name = "SIRUES_CODE" )
    @PersistentFieldGetter( fieldName = "siruesCode" )
@Length("20")
@OrderNo("140")
	/**
	  *  Access method for the siruesCode property.
	  *  @return the current value of the siruesCode property
	  */
	public java.lang.String getSiruesCode()
	{
		return siruesCode;
	}

	/**
	  *  Set method for the siruesCode property.
	  */
	public void setSiruesCode( java.lang.String _siruesCode )
	{
		siruesCode = _siruesCode;
	}

    @Column( name = "VATPAYER_FLAG" )
    @PersistentFieldGetter( fieldName = "vatPayerFlag" )
@Widget("CK")
@OrderNo("230")
@Label("VAT")
	/**
	  *  Access method for the vatPayerFlag property.
	  *  @return the current value of the vatPayerFlag property
	  */
	public java.lang.String getVatPayerFlag()
	{
		return vatPayerFlag;
	}

	/**
	  *  Set method for the vatPayerFlag property.
	  */
	public void setVatPayerFlag( java.lang.String _vatPayerFlag )
	{
		vatPayerFlag = _vatPayerFlag;
	}

    @Transient
@NoDatabase(true)
@OrderNo("256")
	/**
	  *  Access method for the employeesTab property.
	  *  @return the current value of the employeesTab property
	  */
	public java.lang.String getEmployeesTab()
	{
		return employeesTab;
	}

	/**
	  *  Set method for the employeesTab property.
	  */
	public void setEmployeesTab( java.lang.String _employeesTab )
	{
		employeesTab = _employeesTab;
	}

    @Transient
@NoDatabase(true)
@OrderNo("255")
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

    @Transient
@NoDatabase(true)
@OrderNo("254")
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
@OrderNo("253")
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
@OrderNo("252")
	/**
	  *  Access method for the medicalDevicesTab property.
	  *  @return the current value of the medicalDevicesTab property
	  */
	public java.lang.String getMedicalDevicesTab()
	{
		return medicalDevicesTab;
	}

	/**
	  *  Set method for the medicalDevicesTab property.
	  */
	public void setMedicalDevicesTab( java.lang.String _medicalDevicesTab )
	{
		medicalDevicesTab = _medicalDevicesTab;
	}

    @Transient
@OrderNo("251")
@NoDatabase(true)
	/**
	  *  Access method for the transportVehiclesTab property.
	  *  @return the current value of the transportVehiclesTab property
	  */
	public java.lang.String getTransportVehiclesTab()
	{
		return transportVehiclesTab;
	}

	/**
	  *  Set method for the transportVehiclesTab property.
	  */
	public void setTransportVehiclesTab( java.lang.String _transportVehiclesTab )
	{
		transportVehiclesTab = _transportVehiclesTab;
	}

    @Transient
@NoDatabase(true)
@OrderNo("257")
	/**
	  *  Access method for the subunitsTab property.
	  *  @return the current value of the subunitsTab property
	  */
	public java.lang.String getSubunitsTab()
	{
		return subunitsTab;
	}

	/**
	  *  Set method for the subunitsTab property.
	  */
	public void setSubunitsTab( java.lang.String _subunitsTab )
	{
		subunitsTab = _subunitsTab;
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

    @Column( name = "FISCAL_ATTRIBUTE" )
    @PersistentFieldGetter( fieldName = "fiscalAttribute" )
@VisibleList(true)
@VisibleDetail(true)
@OrderNo("90")
@Length("50")
	/**
	  *  Access method for the fiscalAttribute property.
	  *  @return the current value of the fiscalAttribute property
	  */
	public java.lang.String getFiscalAttribute()
	{
		return fiscalAttribute;
	}

	/**
	  *  Set method for the fiscalAttribute property.
	  */
	public void setFiscalAttribute( java.lang.String _fiscalAttribute )
	{
		fiscalAttribute = _fiscalAttribute;
	}

    @Column( name = "FISCAL_ATTRIBUTE_DATE" )
    @PersistentFieldGetter( fieldName = "fiscalAttributeDate" )
@VisibleDetail(true)
@OrderNo("100")
	/**
	  *  Access method for the fiscalAttributeDate property.
	  *  @return the current value of the fiscalAttributeDate property
	  */
	public java.util.Date getFiscalAttributeDate()
	{
		return fiscalAttributeDate;
	}

	/**
	  *  Set method for the fiscalAttributeDate property.
	  */
	public void setFiscalAttributeDate( java.util.Date _fiscalAttributeDate )
	{
		fiscalAttributeDate = _fiscalAttributeDate;
	}

    @Column( name = "COMP_FLAG", updatable = false )
    @PersistentFieldGetter( fieldName = "compFlag" )
@DomainSplitter(true)
	/**
	  *  Access method for the compFlag property.
	  *  @return the current value of the compFlag property
	  */
	public java.lang.Short getCompFlag()
	{
		return compFlag;
	}

	/**
	  *  Set method for the compFlag property.
	  */
	public void setCompFlag( java.lang.Short _compFlag )
	{
		compFlag = _compFlag;
	}


	/* Vduped Attribute setters */
	/**
	  *  Set method for the duplicated code property.
	  */
	public void setCode( java.lang.String _code )
	{
	    super.setCode( _code );
		vdupCompany_code = _code;
	}

	/**
	  *  Set method for the duplicated name property.
	  */
	public void setName( java.lang.String _name )
	{
	    super.setName( _name );
		vdupCompany_name = _name;
	}


	/* Relationship getters & setters */

    @OneToMany( mappedBy = "company", cascade = {PERSIST, MERGE, REFRESH} )
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

    @PersistentToOneGetter( relName = "companyOrg" )

    @ManyToOne( optional =  false )
    @JoinColumn( name = "CMP_ORG_ID" )
// companyOrg
	/**
	  *  Access method for the companyOrg property.
	  *  @return the current value of the companyOrg property
	  */
	public ro.siveco.svapnt.common.entity.CompanyOrg getCompanyOrg()
	{
		return companyOrg;
	}

	/**
	  *  Set method for the companyOrg property.
	  */
	public void setCompanyOrg( ro.siveco.svapnt.common.entity.CompanyOrg _companyOrg )
	{
		companyOrg = _companyOrg;
	}

    @PersistentToOneGetter( relName = "currency" )

    @ManyToOne( optional =  false )
    @JoinColumn( name = "CRNCY_ID" )
// currency
	/**
	  *  Access method for the currency property.
	  *  @return the current value of the currency property
	  */
	public ro.siveco.svapnt.common.entity.Currency getCurrency()
	{
		return currency;
	}

	/**
	  *  Set method for the currency property.
	  */
	public void setCurrency( ro.siveco.svapnt.common.entity.Currency _currency )
	{
		currency = _currency;
	}

    @PersistentToOneGetter( relName = "companyClassification" )

    @ManyToOne( optional =  true )
    @JoinColumn( name = "CMP_CLASSIF_ID" )
// companyClassification
	/**
	  *  Access method for the companyClassification property.
	  *  @return the current value of the companyClassification property
	  */
	public ro.siveco.svapnt.common.entity.CompanyClassification getCompanyClassification()
	{
		return companyClassification;
	}

	/**
	  *  Set method for the companyClassification property.
	  */
	public void setCompanyClassification( ro.siveco.svapnt.common.entity.CompanyClassification _companyClassification )
	{
		companyClassification = _companyClassification;
	}

    @PersistentToOneGetter( relName = "companyProfile" )

    @ManyToOne( optional =  true )
    @JoinColumn( name = "CMP_PROFILE_ID" )
// companyProfile
	/**
	  *  Access method for the companyProfile property.
	  *  @return the current value of the companyProfile property
	  */
	public ro.siveco.svapnt.common.entity.CompanyProfile getCompanyProfile()
	{
		return companyProfile;
	}

	/**
	  *  Set method for the companyProfile property.
	  */
	public void setCompanyProfile( ro.siveco.svapnt.common.entity.CompanyProfile _companyProfile )
	{
		companyProfile = _companyProfile;
	}


    @OneToMany( mappedBy = "partner", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "companyDocument" )
	/**
	  *  Access method for the companyDocuments property.
	  *  @return the current value of the companyDocuments property
	  */
	public Collection<ro.siveco.svapnt.common.entity.CompanyDocument> getCompanyDocuments()
	{
		return companyDocuments;
	}

	/**
	  *  Set method for the companyDocuments property.
	  */
	public void setCompanyDocuments( Collection<ro.siveco.svapnt.common.entity.CompanyDocument> _companyDocuments )
	{
		companyDocuments = _companyDocuments;
	}


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
    public static final String NQ_findByCode = "common_Company_findByCode";
    public static Company findByCode( java.lang.String code ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( code);
        return ( Company ) getGenericSession().getSingleResult(NQ_findByCode, params);
    }

    public static final String NQ_findByUic = "common_Company_findByUic";
    public static Company findByUic( java.lang.String uic ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( uic);
        return ( Company ) getGenericSession().getSingleResult(NQ_findByUic, params);
    }

	//Unique keys methods with Relations as parameters
	//model defined finders

    public static final String NQ_F1findAllEntities = "Company_F1findAllEntities";
       
    public static Company findByfindAllEntities( ) throws DAOException
    {

        ArrayList params = new ArrayList();

        return ( Company ) getGenericSession().getSingleResult(NQ_F1findAllEntities, params);
    }

    public static Company find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( Company.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
