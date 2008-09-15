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
import ro.siveco.svapnt.common.entity.BankAccount;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Conturi bancare
 * </p>
 * <p>
 * <br>Vezi documentul Dictionar Date<br>
 * </p>
 * <p>
 * Informatii de identificare a persoanelor juridice<br>
 * </p>
 * <p>
 * Conturi bancare persoane fizice sau juridice<br>
 * </p>
 *
 * constraintNamePK PK_BANK_ACCOUNTS
 * tableName BANK_ACCOUNTS
 * finders common_BankAccount_1|true||AND|AND|AND|
 * validFrom validFrom
 * validTo validTo
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.common.session.BankAccountPartnerMgrLocal.JNDI_NAME )
public abstract class BankAccountBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "common_BankAccount";
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

    @Column( name = "BANK_ACCOUNT" )
	private java.lang.String			    bankAccount;

    @Column( name = "NAME" )
	private java.lang.String			    name;

    @Column( name = "PREFERRED_ACCOUNT" )
	private java.lang.String			    preferredAccount;

    @Column( name = "ACCOUNT_TYPE" )
	private java.lang.String			    accountType;

    @Column( name = "VALID_FROM" )
	private java.util.Date			    validFrom;

    @Column( name = "VALID_TO" )
	private java.util.Date			    validTo;

    @Column( name = "CREDIT_LIMIT" )
	private java.lang.Double			    creditLimit;

    @Column( name = "INTEREST_RATE" )
	private java.lang.Double			    interestRate;

	/* Vduped Attributes */

	/* Relationships */

    @ManyToOne( optional =  false )
    @JoinColumn( name = "PARTNER_ID" )
// partner
	private ro.siveco.svapnt.common.entity.Partner			    partner;

    @ManyToOne( optional =  false )
    @JoinColumn( name = "CRNCY_ID" )
// currency
	private ro.siveco.svapnt.common.entity.Currency			    currency;

    @ManyToOne( optional =  false )
    @JoinColumn( name = "BANK_ID" )
// bank
	private ro.siveco.svapnt.common.entity.Bank			    bank;

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

    @Column( name = "BANK_ACCOUNT" )
    @PersistentFieldGetter( fieldName = "bankAccount" )
@Length("30")
@VisibleList(true)
@UniqueKey("UK_BANK_ACCTS")
@Required(true)
@VisibleDetail(true)
@OrderNo("1")
@VisibleReferred(true)
	/**
	  *  Access method for the bankAccount property.
	  *  @return the current value of the bankAccount property
	  */
	public java.lang.String getBankAccount()
	{
		return bankAccount;
	}

	/**
	  *  Set method for the bankAccount property.
	  */
	public void setBankAccount( java.lang.String _bankAccount )
	{
		bankAccount = _bankAccount;
	}

    @Column( name = "NAME" )
    @PersistentFieldGetter( fieldName = "name" )
@Length("100")
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

    @Column( name = "PREFERRED_ACCOUNT" )
    @PersistentFieldGetter( fieldName = "preferredAccount" )
@VisibleDetail(true)
@OrderNo("7")
@Length("1")
@Required(true)
@VisibleList(true)
@Widget("CK")
	/**
	  *  Access method for the preferredAccount property.
	  *  @return the current value of the preferredAccount property
	  */
	public java.lang.String getPreferredAccount()
	{
		return preferredAccount;
	}

	/**
	  *  Set method for the preferredAccount property.
	  */
	public void setPreferredAccount( java.lang.String _preferredAccount )
	{
		preferredAccount = _preferredAccount;
	}

    @Column( name = "ACCOUNT_TYPE" )
    @PersistentFieldGetter( fieldName = "accountType" )
@OrderNo("2")
@VisibleDetail(true)
@VisibleList(true)
@Length("1")
@WidgetValues("0|Currenct operations;1|Deposit;2|Credit letter;3|Guarantee;4|Liability;5|Credit")
@Widget("CB")
@Required(true)
	/**
	  *  Access method for the accountType property.
	  *  @return the current value of the accountType property
	  */
	public java.lang.String getAccountType()
	{
		return accountType;
	}

	/**
	  *  Set method for the accountType property.
	  */
	public void setAccountType( java.lang.String _accountType )
	{
		accountType = _accountType;
	}

    @Column( name = "VALID_FROM" )
    @PersistentFieldGetter( fieldName = "validFrom" )
@VisibleDetail(true)
@Required(true)
@OrderNo("9")
@VisibleList(true)
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
@OrderNo("10")
@VisibleList(true)
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

    @Column( name = "CREDIT_LIMIT" )
    @PersistentFieldGetter( fieldName = "creditLimit" )
@VisibleList(true)
@VisibleDetail(true)
@OrderNo("11")
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

    @Column( name = "INTEREST_RATE" )
    @PersistentFieldGetter( fieldName = "interestRate" )
@VisibleDetail(true)
@VisibleList(true)
@OrderNo("12")
@Unfiltered(true)
	/**
	  *  Access method for the interestRate property.
	  *  @return the current value of the interestRate property
	  */
	public java.lang.Double getInterestRate()
	{
		return interestRate;
	}

	/**
	  *  Set method for the interestRate property.
	  */
	public void setInterestRate( java.lang.Double _interestRate )
	{
		interestRate = _interestRate;
	}


	/* Vduped Attribute setters */

	/* Relationship getters & setters */
    @PersistentToOneGetter( relName = "partner" )

    @ManyToOne( optional =  false )
    @JoinColumn( name = "PARTNER_ID" )
// partner
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

    @PersistentToOneGetter( relName = "bank" )

    @ManyToOne( optional =  false )
    @JoinColumn( name = "BANK_ID" )
// bank
    @UniqueKey
	/**
	  *  Access method for the bank property.
	  *  @return the current value of the bank property
	  */
	public ro.siveco.svapnt.common.entity.Bank getBank()
	{
		return bank;
	}

	/**
	  *  Set method for the bank property.
	  */
	public void setBank( ro.siveco.svapnt.common.entity.Bank _bank )
	{
		bank = _bank;
	}


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                                                                                                                                                                                                                           
    public static final String NQ_findByBankAccountAndBank = "common_BankAccount_findByBankAccountAndBank";
    public static BankAccount findByBankAccountAndBank( java.lang.String bankAccount, ro.siveco.svapnt.common.entity.Bank bank ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( bankAccount);
                params.add(bank.getId());
        return ( BankAccount ) getGenericSession().getSingleResult(NQ_findByBankAccountAndBank, params);
    }

	//Unique keys methods with Relations as parameters
	//model defined finders

    public static final String NQ_F1findByPreferredAccountAndCurrencyCodeAndPartnerCodeAndPartnerType = "BankAccount_F1findByPreferredAccountAndCurrencyCodeAndPartnerCodeAndPartnerType";
       
    public static BankAccount findByPreferredAccountAndCurrencyCodeAndPartnerCodeAndPartnerType( java.lang.String p1_PreferredAccount, java.lang.String p2_CurrencyCode, java.lang.String p3_PartnerCode, java.lang.Short p4_PartnerType) throws DAOException
    {

        ArrayList params = new ArrayList();

            params.add( p1_PreferredAccount);
            params.add( p2_CurrencyCode);
            params.add( p3_PartnerCode);
            params.add( p4_PartnerType);
        return ( BankAccount ) getGenericSession().getSingleResult(NQ_F1findByPreferredAccountAndCurrencyCodeAndPartnerCodeAndPartnerType, params);
    }

    public static BankAccount find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( BankAccount.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
