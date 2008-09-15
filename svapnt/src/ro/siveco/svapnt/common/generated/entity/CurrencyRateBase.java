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
import ro.siveco.svapnt.common.entity.CurrencyRate;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Cursuri valutare exprimate fata de valuta locala sau fata de
 * valuta de referinta (in functie de atributul refType din tipuri
 * de cursuri valutare)<br>
 * </p>
 * <p>
 * Vezi documentul dictionar de date<br>
 * </p>
 * <p>
 * Catalogul de rate de schimb
 * </p>
 *
 * constraintNamePK PK_CRNCY_RATES
 * tableName CURRENCY_RATES
 * finders acc_currencyRate_1|true||AND|AND|AND|;acc_currencyRate_2|false||AND|AND|
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.common.session.CurrencyRateMgrLocal.JNDI_NAME )
public abstract class CurrencyRateBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "common_CurrencyRate";
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

    @Column( name = "VALUE" )
	private java.lang.Double			    value;

    @Column( name = "RATE_DATE" )
	private java.util.Date			    rateDate;

	/* Vduped Attributes */

	/* Relationships */

    @ManyToOne( optional =  false )
    @JoinColumn( name = "CRNCY_RATE_TYPE_ID" )
// currencyRateType
	private ro.siveco.svapnt.common.entity.CurrencyRateType			    currencyRateType;

    @ManyToOne( optional =  false )
    @JoinColumn( name = "REF_CRNCY_ID" )
// refferedCrncy
	private ro.siveco.svapnt.common.entity.Currency			    refferedCrncy;

    @ManyToOne( optional =  false )
    @JoinColumn( name = "BASE_CRNCY_ID" )
// baseCrncy
	private ro.siveco.svapnt.common.entity.Currency			    baseCrncy;

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

    @Column( name = "VALUE" )
    @PersistentFieldGetter( fieldName = "value" )
@Required(true)
@VisibleList(true)
@VisibleDetail(true)
@OrderNo("5")
@Unfiltered(true)
	/**
	  *  Access method for the value property.
	  *  @return the current value of the value property
	  */
	public java.lang.Double getValue()
	{
		return value;
	}

	/**
	  *  Set method for the value property.
	  */
	public void setValue( java.lang.Double _value )
	{
		value = _value;
	}

    @Column( name = "RATE_DATE" )
    @PersistentFieldGetter( fieldName = "rateDate" )
@VisibleDetail(true)
@Required(true)
@OrderNo("4")
@VisibleList(true)
@UniqueKey("COM_CURRNECY_RATE")
	/**
	  *  Access method for the rateDate property.
	  *  @return the current value of the rateDate property
	  */
	public java.util.Date getRateDate()
	{
		return rateDate;
	}

	/**
	  *  Set method for the rateDate property.
	  */
	public void setRateDate( java.util.Date _rateDate )
	{
		rateDate = _rateDate;
	}


	/* Vduped Attribute setters */

	/* Relationship getters & setters */
    @PersistentToOneGetter( relName = "currencyRateType" )

    @ManyToOne( optional =  false )
    @JoinColumn( name = "CRNCY_RATE_TYPE_ID" )
// currencyRateType
    @UniqueKey
	/**
	  *  Access method for the currencyRateType property.
	  *  @return the current value of the currencyRateType property
	  */
	public ro.siveco.svapnt.common.entity.CurrencyRateType getCurrencyRateType()
	{
		return currencyRateType;
	}

	/**
	  *  Set method for the currencyRateType property.
	  */
	public void setCurrencyRateType( ro.siveco.svapnt.common.entity.CurrencyRateType _currencyRateType )
	{
		currencyRateType = _currencyRateType;
	}

    @PersistentToOneGetter( relName = "refferedCrncy" )

    @ManyToOne( optional =  false )
    @JoinColumn( name = "REF_CRNCY_ID" )
// refferedCrncy
    @UniqueKey
	/**
	  *  Access method for the refferedCrncy property.
	  *  @return the current value of the refferedCrncy property
	  */
	public ro.siveco.svapnt.common.entity.Currency getRefferedCrncy()
	{
		return refferedCrncy;
	}

	/**
	  *  Set method for the refferedCrncy property.
	  */
	public void setRefferedCrncy( ro.siveco.svapnt.common.entity.Currency _refferedCrncy )
	{
		refferedCrncy = _refferedCrncy;
	}

    @PersistentToOneGetter( relName = "baseCrncy" )

    @ManyToOne( optional =  false )
    @JoinColumn( name = "BASE_CRNCY_ID" )
// baseCrncy
    @UniqueKey
	/**
	  *  Access method for the baseCrncy property.
	  *  @return the current value of the baseCrncy property
	  */
	public ro.siveco.svapnt.common.entity.Currency getBaseCrncy()
	{
		return baseCrncy;
	}

	/**
	  *  Set method for the baseCrncy property.
	  */
	public void setBaseCrncy( ro.siveco.svapnt.common.entity.Currency _baseCrncy )
	{
		baseCrncy = _baseCrncy;
	}


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
    public static final String NQ_findByRateDateAndCurrencyRateTypeAndRefferedCrncyAndBaseCrncy = "common_CurrencyRate_findByRateDateAndCurrencyRateTypeAndRefferedCrncyAndBaseCrncy";
    public static CurrencyRate findByRateDateAndCurrencyRateTypeAndRefferedCrncyAndBaseCrncy( java.util.Date rateDate, ro.siveco.svapnt.common.entity.CurrencyRateType currencyRateType, ro.siveco.svapnt.common.entity.Currency refferedCrncy, ro.siveco.svapnt.common.entity.Currency baseCrncy ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( rateDate);
                params.add(currencyRateType.getId());
                params.add(refferedCrncy.getId());
                params.add(baseCrncy.getId());
        return ( CurrencyRate ) getGenericSession().getSingleResult(NQ_findByRateDateAndCurrencyRateTypeAndRefferedCrncyAndBaseCrncy, params);
    }

	//Unique keys methods with Relations as parameters
	//model defined finders

    public static final String NQ_F1findByRateDateAndCurrencyRateTypeCodeAndRefferedCrncyCodeAndBaseCrncyCode = "CurrencyRate_F1findByRateDateAndCurrencyRateTypeCodeAndRefferedCrncyCodeAndBaseCrncyCode";
       
    public static CurrencyRate findByRateDateAndCurrencyRateTypeCodeAndRefferedCrncyCodeAndBaseCrncyCode( java.util.Date p1_RateDate, java.lang.String p2_CurrencyRateTypeCode, java.lang.String p3_RefferedCrncyCode, java.lang.String p3_BaseCrncyCode) throws DAOException
    {

        ArrayList params = new ArrayList();

            params.add( p1_RateDate);
            params.add( p2_CurrencyRateTypeCode);
            params.add( p3_RefferedCrncyCode);
            params.add( p3_BaseCrncyCode);
        return ( CurrencyRate ) getGenericSession().getSingleResult(NQ_F1findByRateDateAndCurrencyRateTypeCodeAndRefferedCrncyCodeAndBaseCrncyCode, params);
    }
    public static final String NQ_F2findByCurrencyRateTypeCodeAndRefferedCrncyCodeAndBaseCrncyCodeOrderByRateDate = "CurrencyRate_F2findByCurrencyRateTypeCodeAndRefferedCrncyCodeAndBaseCrncyCodeOrderByRateDate";
       
    public static List<CurrencyRate> findByCurrencyRateTypeCodeAndRefferedCrncyCodeAndBaseCrncyCodeOrderByRateDate( java.lang.String p1_CurrencyRateTypeCode, java.lang.String p2_RefferedCrncyCode, java.lang.String p2_BaseCrncyCode) throws DAOException
    {

        ArrayList params = new ArrayList();

            params.add( p1_CurrencyRateTypeCode);
            params.add( p2_RefferedCrncyCode);
            params.add( p2_BaseCrncyCode);
        return ( List<CurrencyRate> ) getGenericSession().getResultList(NQ_F2findByCurrencyRateTypeCodeAndRefferedCrncyCodeAndBaseCrncyCodeOrderByRateDate, params);
    }

    public static CurrencyRate find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( CurrencyRate.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
