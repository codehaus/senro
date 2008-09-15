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
import ro.siveco.svapnt.common.entity.Currency;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Valute (monede)<br>
 * </p>
 * <p>
 * Vezi documentul dictionar de date<br>
 * </p>
 * <p>
 * Catalogul de valute
 * </p>
 *
 * tableName CURRENCIES
 * constraintNamePK PK_CURRENCIES
 * validTo validTo
 * validFrom validFrom
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.common.session.CurrencyMgrLocal.JNDI_NAME )
public abstract class CurrencyBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "common_Currency";
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

    @Column( name = "DECIMALS" )
	private java.lang.Integer			    decimals;

    @Column( name = "PRICE_DECIMALS" )
	private java.lang.Integer			    priceDecimals;

    @Column( name = "MULTIPLICATION_FACTOR" )
	private java.lang.Integer			    multiplicationFactor = 1;

    @Column( name = "NUMERIC_CODE" )
	private java.lang.String			    numericCode;

    @Column( name = "ORDER_NO" )
	private java.lang.Integer			    orderNo;

    @Column( name = "VALID_FROM" )
	private java.util.Date			    validFrom;

    @Column( name = "VALID_TO" )
	private java.util.Date			    validTo;

	/* Vduped Attributes */

	/* Relationships */

    @OneToMany( mappedBy = "currency", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.Country>     countries = new ArrayList();

    @OneToMany( mappedBy = "refferedCrncy", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.CurrencyRate>     refferingRates = new ArrayList();

    @OneToMany( mappedBy = "baseCrncy", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.CurrencyRate>     basedRates = new ArrayList();

    @OneToMany( mappedBy = "currency", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.Company>     companies = new ArrayList();

    @OneToMany( mappedBy = "currency", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.ResTrade>     resTrades = new ArrayList();

    @OneToMany( mappedBy = "currency", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.BankAccount>     bankAccounts = new ArrayList();

    @OneToMany( mappedBy = "currency", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.CompanyProfile>     companyProfiles = new ArrayList();

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
@VisibleDetail(true)
@Length("20")
@VisibleReferred(true)
@OrderNo("1")
@UniqueKey("UK_CURRENCIES")
@VisibleList(true)
@Required(true)
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
@VisibleList(true)
@Required(true)
@OrderNo("2")
@Length("100")
@VisibleDetail(true)
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

    @Column( name = "DECIMALS" )
    @PersistentFieldGetter( fieldName = "decimals" )
@VisibleList(true)
@OrderNo("3")
@Required(true)
@VisibleDetail(true)
@Unfiltered(true)
	/**
	  *  Access method for the decimals property.
	  *  @return the current value of the decimals property
	  */
	public java.lang.Integer getDecimals()
	{
		return decimals;
	}

	/**
	  *  Set method for the decimals property.
	  */
	public void setDecimals( java.lang.Integer _decimals )
	{
		decimals = _decimals;
	}

    @Column( name = "PRICE_DECIMALS" )
    @PersistentFieldGetter( fieldName = "priceDecimals" )
@Required(true)
@OrderNo("40")
@VisibleDetail(true)
@VisibleList(true)
	/**
	  *  Access method for the priceDecimals property.
	  *  @return the current value of the priceDecimals property
	  */
	public java.lang.Integer getPriceDecimals()
	{
		return priceDecimals;
	}

	/**
	  *  Set method for the priceDecimals property.
	  */
	public void setPriceDecimals( java.lang.Integer _priceDecimals )
	{
		priceDecimals = _priceDecimals;
	}

    @Column( name = "MULTIPLICATION_FACTOR" )
    @PersistentFieldGetter( fieldName = "multiplicationFactor" )
@OrderNo("50")
@Required(true)
@VisibleDetail(true)
@VisibleList(true)
	/**
	  *  Access method for the multiplicationFactor property.
	  *  @return the current value of the multiplicationFactor property
	  */
	public java.lang.Integer getMultiplicationFactor()
	{
		return multiplicationFactor;
	}

	/**
	  *  Set method for the multiplicationFactor property.
	  */
	public void setMultiplicationFactor( java.lang.Integer _multiplicationFactor )
	{
		multiplicationFactor = _multiplicationFactor;
	}

    @Column( name = "NUMERIC_CODE" )
    @PersistentFieldGetter( fieldName = "numericCode" )
@OrderNo("60")
@UniqueKey("UK_CURRENCIES_NC")
@VisibleList(true)
@VisibleDetail(true)
@Required(true)
@Length("20")
@Unfiltered(true)
	/**
	  *  Access method for the numericCode property.
	  *  @return the current value of the numericCode property
	  */
	public java.lang.String getNumericCode()
	{
		return numericCode;
	}

	/**
	  *  Set method for the numericCode property.
	  */
	public void setNumericCode( java.lang.String _numericCode )
	{
		numericCode = _numericCode;
	}

    @Column( name = "ORDER_NO" )
    @PersistentFieldGetter( fieldName = "orderNo" )
@VisibleDetail(true)
@OrderNo("70")
	/**
	  *  Access method for the orderNo property.
	  *  @return the current value of the orderNo property
	  */
	public java.lang.Integer getOrderNo()
	{
		return orderNo;
	}

	/**
	  *  Set method for the orderNo property.
	  */
	public void setOrderNo( java.lang.Integer _orderNo )
	{
		orderNo = _orderNo;
	}

    @Column( name = "VALID_FROM" )
    @PersistentFieldGetter( fieldName = "validFrom" )
@VisibleDetail(true)
@OrderNo("80")
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
@OrderNo("90")
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

    @OneToMany( mappedBy = "currency", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "country" )
	/**
	  *  Access method for the countries property.
	  *  @return the current value of the countries property
	  */
	public Collection<ro.siveco.svapnt.common.entity.Country> getCountries()
	{
		return countries;
	}

	/**
	  *  Set method for the countries property.
	  */
	public void setCountries( Collection<ro.siveco.svapnt.common.entity.Country> _countries )
	{
		countries = _countries;
	}


    @OneToMany( mappedBy = "refferedCrncy", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "refferingRates" )
	/**
	  *  Access method for the refferingRates property.
	  *  @return the current value of the refferingRates property
	  */
	public Collection<ro.siveco.svapnt.common.entity.CurrencyRate> getRefferingRates()
	{
		return refferingRates;
	}

	/**
	  *  Set method for the refferingRates property.
	  */
	public void setRefferingRates( Collection<ro.siveco.svapnt.common.entity.CurrencyRate> _refferingRates )
	{
		refferingRates = _refferingRates;
	}


    @OneToMany( mappedBy = "baseCrncy", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "basedRates" )
	/**
	  *  Access method for the basedRates property.
	  *  @return the current value of the basedRates property
	  */
	public Collection<ro.siveco.svapnt.common.entity.CurrencyRate> getBasedRates()
	{
		return basedRates;
	}

	/**
	  *  Set method for the basedRates property.
	  */
	public void setBasedRates( Collection<ro.siveco.svapnt.common.entity.CurrencyRate> _basedRates )
	{
		basedRates = _basedRates;
	}


    @OneToMany( mappedBy = "currency", cascade = {PERSIST, MERGE, REFRESH} )
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


    @OneToMany( mappedBy = "currency", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "resTrade" )
	/**
	  *  Access method for the resTrades property.
	  *  @return the current value of the resTrades property
	  */
	public Collection<ro.siveco.svapnt.common.entity.ResTrade> getResTrades()
	{
		return resTrades;
	}

	/**
	  *  Set method for the resTrades property.
	  */
	public void setResTrades( Collection<ro.siveco.svapnt.common.entity.ResTrade> _resTrades )
	{
		resTrades = _resTrades;
	}


    @OneToMany( mappedBy = "currency", cascade = {PERSIST, MERGE, REFRESH} )
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


    @OneToMany( mappedBy = "currency", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "companyProfile" )
	/**
	  *  Access method for the companyProfiles property.
	  *  @return the current value of the companyProfiles property
	  */
	public Collection<ro.siveco.svapnt.common.entity.CompanyProfile> getCompanyProfiles()
	{
		return companyProfiles;
	}

	/**
	  *  Set method for the companyProfiles property.
	  */
	public void setCompanyProfiles( Collection<ro.siveco.svapnt.common.entity.CompanyProfile> _companyProfiles )
	{
		companyProfiles = _companyProfiles;
	}


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                                                                                                                                                                         
    public static final String NQ_findByCode = "common_Currency_findByCode";
    public static Currency findByCode( java.lang.String code ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( code);
        return ( Currency ) getGenericSession().getSingleResult(NQ_findByCode, params);
    }

    public static final String NQ_findByNumericCode = "common_Currency_findByNumericCode";
    public static Currency findByNumericCode( java.lang.String numericCode ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( numericCode);
        return ( Currency ) getGenericSession().getSingleResult(NQ_findByNumericCode, params);
    }

	//Unique keys methods with Relations as parameters
	//model defined finders


    public static Currency find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( Currency.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
