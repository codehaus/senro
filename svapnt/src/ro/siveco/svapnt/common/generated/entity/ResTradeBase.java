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
import ro.siveco.svapnt.common.entity.ResTrade;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Functii personal<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date<br>
 * </p>
 *
 * tableName RES_TRADES
 * constraintNamePK PK_RES_TRADES
 * validFrom validFrom
 * validTo validTo
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.common.session.ResTradeMgrLocal.JNDI_NAME )
public abstract class ResTradeBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "common_ResTrade";
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

    @Column( name = "DEFAULT_PAYMENT" )
	private java.lang.Long			    defaultPayment;

    @Column( name = "DEFAULT_PAYTYPE" )
	private java.lang.String			    defaultPaytype;

    @Column( name = "DEFAULT_RATE" )
	private java.lang.Integer			    defaultRate;

    @Column( name = "DESCRIPTION" )
	private java.lang.String			    description;

    @Column( name = "LAST_RUN_DATE" )
	private java.util.Date			    lastRunDate;

    @Column( name = "PAID_HOURS_PER_DAY" )
	private java.lang.Long			    paidHoursPerDay;

    @Column( name = "PM_JOB_FACTOR" )
	private java.lang.Long			    pmJobFactor;

    @Column( name = "STRING_1" )
	private java.lang.String			    string1;

    @Column( name = "STRING_2" )
	private java.lang.String			    string2;

    @Column( name = "VALID_FROM" )
	private java.util.Date			    validFrom;

    @Column( name = "VALID_TO" )
	private java.util.Date			    validTo;

    @Column( name = "WRENCH_HOURS_PER_DAY" )
	private java.lang.Long			    wrenchHoursPerDay;

	/* Vduped Attributes */

	/* Relationships */

    @OneToMany( mappedBy = "resTrade", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.Employee>     employees = new ArrayList();

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
@UniqueKey("UK_RES_TRADE")
@VisibleDetail(true)
@Length("20")
@VisibleList(true)
@Required(true)
@OrderNo("10")
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

    @Column( name = "DEFAULT_PAYMENT" )
    @PersistentFieldGetter( fieldName = "defaultPayment" )
	/**
	  *  Access method for the defaultPayment property.
	  *  @return the current value of the defaultPayment property
	  */
	public java.lang.Long getDefaultPayment()
	{
		return defaultPayment;
	}

	/**
	  *  Set method for the defaultPayment property.
	  */
	public void setDefaultPayment( java.lang.Long _defaultPayment )
	{
		defaultPayment = _defaultPayment;
	}

    @Column( name = "DEFAULT_PAYTYPE" )
    @PersistentFieldGetter( fieldName = "defaultPaytype" )
@Length("20")
	/**
	  *  Access method for the defaultPaytype property.
	  *  @return the current value of the defaultPaytype property
	  */
	public java.lang.String getDefaultPaytype()
	{
		return defaultPaytype;
	}

	/**
	  *  Set method for the defaultPaytype property.
	  */
	public void setDefaultPaytype( java.lang.String _defaultPaytype )
	{
		defaultPaytype = _defaultPaytype;
	}

    @Column( name = "DEFAULT_RATE" )
    @PersistentFieldGetter( fieldName = "defaultRate" )
	/**
	  *  Access method for the defaultRate property.
	  *  @return the current value of the defaultRate property
	  */
	public java.lang.Integer getDefaultRate()
	{
		return defaultRate;
	}

	/**
	  *  Set method for the defaultRate property.
	  */
	public void setDefaultRate( java.lang.Integer _defaultRate )
	{
		defaultRate = _defaultRate;
	}

    @Column( name = "DESCRIPTION" )
    @PersistentFieldGetter( fieldName = "description" )
@VisibleList(true)
@VisibleDetail(true)
@Required(true)
@Length("100")
@OrderNo("20")
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

    @Column( name = "LAST_RUN_DATE" )
    @PersistentFieldGetter( fieldName = "lastRunDate" )
	/**
	  *  Access method for the lastRunDate property.
	  *  @return the current value of the lastRunDate property
	  */
	public java.util.Date getLastRunDate()
	{
		return lastRunDate;
	}

	/**
	  *  Set method for the lastRunDate property.
	  */
	public void setLastRunDate( java.util.Date _lastRunDate )
	{
		lastRunDate = _lastRunDate;
	}

    @Column( name = "PAID_HOURS_PER_DAY" )
    @PersistentFieldGetter( fieldName = "paidHoursPerDay" )
	/**
	  *  Access method for the paidHoursPerDay property.
	  *  @return the current value of the paidHoursPerDay property
	  */
	public java.lang.Long getPaidHoursPerDay()
	{
		return paidHoursPerDay;
	}

	/**
	  *  Set method for the paidHoursPerDay property.
	  */
	public void setPaidHoursPerDay( java.lang.Long _paidHoursPerDay )
	{
		paidHoursPerDay = _paidHoursPerDay;
	}

    @Column( name = "PM_JOB_FACTOR" )
    @PersistentFieldGetter( fieldName = "pmJobFactor" )
	/**
	  *  Access method for the pmJobFactor property.
	  *  @return the current value of the pmJobFactor property
	  */
	public java.lang.Long getPmJobFactor()
	{
		return pmJobFactor;
	}

	/**
	  *  Set method for the pmJobFactor property.
	  */
	public void setPmJobFactor( java.lang.Long _pmJobFactor )
	{
		pmJobFactor = _pmJobFactor;
	}

    @Column( name = "STRING_1" )
    @PersistentFieldGetter( fieldName = "string1" )
@Length("100")
	/**
	  *  Access method for the string1 property.
	  *  @return the current value of the string1 property
	  */
	public java.lang.String getString1()
	{
		return string1;
	}

	/**
	  *  Set method for the string1 property.
	  */
	public void setString1( java.lang.String _string1 )
	{
		string1 = _string1;
	}

    @Column( name = "STRING_2" )
    @PersistentFieldGetter( fieldName = "string2" )
@Length("100")
	/**
	  *  Access method for the string2 property.
	  *  @return the current value of the string2 property
	  */
	public java.lang.String getString2()
	{
		return string2;
	}

	/**
	  *  Set method for the string2 property.
	  */
	public void setString2( java.lang.String _string2 )
	{
		string2 = _string2;
	}

    @Column( name = "VALID_FROM" )
    @PersistentFieldGetter( fieldName = "validFrom" )
@VisibleList(true)
@VisibleDetail(true)
@Required(true)
@OrderNo("40")
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
@OrderNo("50")
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

    @Column( name = "WRENCH_HOURS_PER_DAY" )
    @PersistentFieldGetter( fieldName = "wrenchHoursPerDay" )
	/**
	  *  Access method for the wrenchHoursPerDay property.
	  *  @return the current value of the wrenchHoursPerDay property
	  */
	public java.lang.Long getWrenchHoursPerDay()
	{
		return wrenchHoursPerDay;
	}

	/**
	  *  Set method for the wrenchHoursPerDay property.
	  */
	public void setWrenchHoursPerDay( java.lang.Long _wrenchHoursPerDay )
	{
		wrenchHoursPerDay = _wrenchHoursPerDay;
	}


	/* Vduped Attribute setters */

	/* Relationship getters & setters */

    @OneToMany( mappedBy = "resTrade", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "employee" )
	/**
	  *  Access method for the employees property.
	  *  @return the current value of the employees property
	  */
	public Collection<ro.siveco.svapnt.common.entity.Employee> getEmployees()
	{
		return employees;
	}

	/**
	  *  Set method for the employees property.
	  */
	public void setEmployees( Collection<ro.siveco.svapnt.common.entity.Employee> _employees )
	{
		employees = _employees;
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
                                                                                                                                                                                                                               
    public static final String NQ_findByCode = "common_ResTrade_findByCode";
    public static ResTrade findByCode( java.lang.String code ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( code);
        return ( ResTrade ) getGenericSession().getSingleResult(NQ_findByCode, params);
    }

	//Unique keys methods with Relations as parameters
	//model defined finders


    public static ResTrade find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( ResTrade.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
