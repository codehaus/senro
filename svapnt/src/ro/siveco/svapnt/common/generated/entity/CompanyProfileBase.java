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
import ro.siveco.svapnt.common.entity.CompanyProfile;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Profile clienti<br>
 * </p>
 * <p>
 * Vezi documentul Dictionar Date<br>
 * </p>
 * <p>
 * Informatii de identificare a persoanelor juridice<br>
 * </p>
 *
 * constraintNamePK PK_COMPANY_PROFILE
 * tableName COMPANY_PROFILES
 * validFrom validFrom
 * validTo validTo
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.common.session.CompanyProfileMgrLocal.JNDI_NAME )
public abstract class CompanyProfileBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "common_CompanyProfile";
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

    @Column( name = "CREDIT_LIMIT_FLAG" )
	private java.lang.String			    creditLimitFlag;

    @Column( name = "CREDIT_LIMIT_VALUE" )
	private java.lang.Double			    creditLimitValue;

    @Column( name = "MULTIPLE_INVOICE_CREDIT_FLAG" )
	private java.lang.String			    multipleInvoiceCreditFlag;

    @Column( name = "INVOICE_MIN_VALUE" )
	private java.lang.Double			    invoiceMinValue;

    @Column( name = "DISCOUNT_FLAG" )
	private java.lang.String			    discountFlag;

    @Column( name = "ISSUE_PRINTING_MODE" )
	private java.lang.String			    issuePrintingMode;

    @Column( name = "VALID_FROM" )
	private java.util.Date			    validFrom;

    @Column( name = "VALID_TO" )
	private java.util.Date			    validTo;

	/* Vduped Attributes */

	/* Relationships */

    @OneToMany( mappedBy = "companyProfile", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.Company>     companies = new ArrayList();

    @ManyToOne( optional =  true )
    @JoinColumn( name = "CRNCY_ID" )
// currency
	private ro.siveco.svapnt.common.entity.Currency			    currency;

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
@VisibleReferred(true)
@Length("20")
@UniqueKey("UK_CMP_PROFILES")
@OrderNo("10")
@VisibleList(true)
@Required(true)
@VisibleDetail(true)
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
@Required(true)
@Length("100")
@OrderNo("20")
@VisibleList(true)
@VisibleDetail(true)
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

    @Column( name = "CREDIT_LIMIT_FLAG" )
    @PersistentFieldGetter( fieldName = "creditLimitFlag" )
@VisibleList(true)
@VisibleDetail(true)
@Label("Credit limit")
@OrderNo("30")
@Widget("CK")
	/**
	  *  Access method for the creditLimitFlag property.
	  *  @return the current value of the creditLimitFlag property
	  */
	public java.lang.String getCreditLimitFlag()
	{
		return creditLimitFlag;
	}

	/**
	  *  Set method for the creditLimitFlag property.
	  */
	public void setCreditLimitFlag( java.lang.String _creditLimitFlag )
	{
		creditLimitFlag = _creditLimitFlag;
	}

    @Column( name = "CREDIT_LIMIT_VALUE" )
    @PersistentFieldGetter( fieldName = "creditLimitValue" )
@OrderNo("40")
@VisibleDetail(true)
@VisibleList(true)
	/**
	  *  Access method for the creditLimitValue property.
	  *  @return the current value of the creditLimitValue property
	  */
	public java.lang.Double getCreditLimitValue()
	{
		return creditLimitValue;
	}

	/**
	  *  Set method for the creditLimitValue property.
	  */
	public void setCreditLimitValue( java.lang.Double _creditLimitValue )
	{
		creditLimitValue = _creditLimitValue;
	}

    @Column( name = "MULTIPLE_INVOICE_CREDIT_FLAG" )
    @PersistentFieldGetter( fieldName = "multipleInvoiceCreditFlag" )
@VisibleList(true)
@Label("Multiple invoice credit")
@VisibleDetail(true)
@OrderNo("60")
	/**
	  *  Access method for the multipleInvoiceCreditFlag property.
	  *  @return the current value of the multipleInvoiceCreditFlag property
	  */
	public java.lang.String getMultipleInvoiceCreditFlag()
	{
		return multipleInvoiceCreditFlag;
	}

	/**
	  *  Set method for the multipleInvoiceCreditFlag property.
	  */
	public void setMultipleInvoiceCreditFlag( java.lang.String _multipleInvoiceCreditFlag )
	{
		multipleInvoiceCreditFlag = _multipleInvoiceCreditFlag;
	}

    @Column( name = "INVOICE_MIN_VALUE" )
    @PersistentFieldGetter( fieldName = "invoiceMinValue" )
@VisibleDetail(true)
@OrderNo("80")
	/**
	  *  Access method for the invoiceMinValue property.
	  *  @return the current value of the invoiceMinValue property
	  */
	public java.lang.Double getInvoiceMinValue()
	{
		return invoiceMinValue;
	}

	/**
	  *  Set method for the invoiceMinValue property.
	  */
	public void setInvoiceMinValue( java.lang.Double _invoiceMinValue )
	{
		invoiceMinValue = _invoiceMinValue;
	}

    @Column( name = "DISCOUNT_FLAG" )
    @PersistentFieldGetter( fieldName = "discountFlag" )
@VisibleDetail(true)
@Label("Discount")
@Widget("CK")
@OrderNo("90")
	/**
	  *  Access method for the discountFlag property.
	  *  @return the current value of the discountFlag property
	  */
	public java.lang.String getDiscountFlag()
	{
		return discountFlag;
	}

	/**
	  *  Set method for the discountFlag property.
	  */
	public void setDiscountFlag( java.lang.String _discountFlag )
	{
		discountFlag = _discountFlag;
	}

    @Column( name = "ISSUE_PRINTING_MODE" )
    @PersistentFieldGetter( fieldName = "issuePrintingMode" )
@Length("20")
@OrderNo("100")
@VisibleDetail(true)
	/**
	  *  Access method for the issuePrintingMode property.
	  *  @return the current value of the issuePrintingMode property
	  */
	public java.lang.String getIssuePrintingMode()
	{
		return issuePrintingMode;
	}

	/**
	  *  Set method for the issuePrintingMode property.
	  */
	public void setIssuePrintingMode( java.lang.String _issuePrintingMode )
	{
		issuePrintingMode = _issuePrintingMode;
	}

    @Column( name = "VALID_FROM" )
    @PersistentFieldGetter( fieldName = "validFrom" )
@VisibleList(true)
@VisibleDetail(true)
@Required(true)
@OrderNo("110")
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
@OrderNo("120")
@VisibleList(true)
@VisibleDetail(true)
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

    @OneToMany( mappedBy = "companyProfile", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "company" )
	/**
	  *  Access method for the companies property.
	  *  @return the current value of the companies property
	  */
	public Collection<ro.siveco.svapnt.common.entity.Company> getCompanies()
	{
		return companies;
	}

	/**
	  *  Set method for the companies property.
	  */
	public void setCompanies( Collection<ro.siveco.svapnt.common.entity.Company> _companies )
	{
		companies = _companies;
	}

    @PersistentToOneGetter( relName = "currency" )

    @ManyToOne( optional =  true )
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


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                                                                                          
    public static final String NQ_findByCode = "common_CompanyProfile_findByCode";
    public static CompanyProfile findByCode( java.lang.String code ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( code);
        return ( CompanyProfile ) getGenericSession().getSingleResult(NQ_findByCode, params);
    }

	//Unique keys methods with Relations as parameters
	//model defined finders


    public static CompanyProfile find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( CompanyProfile.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
