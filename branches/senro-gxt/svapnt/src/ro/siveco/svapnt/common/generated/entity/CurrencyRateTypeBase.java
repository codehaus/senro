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
import ro.siveco.svapnt.common.entity.CurrencyRateType;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Tipuri de cursuri valutare<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * tableName CURRENCY_RATE_TYPES
 * constraintNamePK PK_CRNCY_RATE_TYPE
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.common.session.CurrencyRateTypeMgrLocal.JNDI_NAME )
public abstract class CurrencyRateTypeBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "common_CurrencyRateType";
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

    @Column( name = "REF_TYPE" )
	private java.lang.Integer			    refType;

	/* Vduped Attributes */

	/* Relationships */

    @OneToMany( mappedBy = "currencyRateType", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.CurrencyRate>     currencyRates = new ArrayList();

    @OneToMany( mappedBy = "currencyRateType", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.CurrencyRateTypeUser>     currencyRateTypeUsers = new ArrayList();

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
@VisibleReferred(true)
@Length("20")
@VisibleList(true)
@VisibleDetail(true)
@UniqueKey("UK_CRNCY_RATETYPES")
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

    @Column( name = "DESCRIPTION" )
    @PersistentFieldGetter( fieldName = "description" )
@Length("100")
@Required(true)
@VisibleReferred(true)
@VisibleList(true)
@OrderNo("2")
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

    @Column( name = "REF_TYPE" )
    @PersistentFieldGetter( fieldName = "refType" )
@Required(true)
@VisibleList(true)
@OrderNo("3")
@VisibleDetail(true)
@WidgetValues("1|To local currency;2|To reference currency")
@Widget("CB")
@Unfiltered(true)
	/**
	  *  Access method for the refType property.
	  *  @return the current value of the refType property
	  */
	public java.lang.Integer getRefType()
	{
		return refType;
	}

	/**
	  *  Set method for the refType property.
	  */
	public void setRefType( java.lang.Integer _refType )
	{
		refType = _refType;
	}


	/* Vduped Attribute setters */

	/* Relationship getters & setters */

    @OneToMany( mappedBy = "currencyRateType", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "currencyRate" )
	/**
	  *  Access method for the currencyRates property.
	  *  @return the current value of the currencyRates property
	  */
	public Collection<ro.siveco.svapnt.common.entity.CurrencyRate> getCurrencyRates()
	{
		return currencyRates;
	}

	/**
	  *  Set method for the currencyRates property.
	  */
	public void setCurrencyRates( Collection<ro.siveco.svapnt.common.entity.CurrencyRate> _currencyRates )
	{
		currencyRates = _currencyRates;
	}


    @OneToMany( mappedBy = "currencyRateType", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "currencyRateTypeUser" )
	/**
	  *  Access method for the currencyRateTypeUsers property.
	  *  @return the current value of the currencyRateTypeUsers property
	  */
	public Collection<ro.siveco.svapnt.common.entity.CurrencyRateTypeUser> getCurrencyRateTypeUsers()
	{
		return currencyRateTypeUsers;
	}

	/**
	  *  Set method for the currencyRateTypeUsers property.
	  */
	public void setCurrencyRateTypeUsers( Collection<ro.siveco.svapnt.common.entity.CurrencyRateTypeUser> _currencyRateTypeUsers )
	{
		currencyRateTypeUsers = _currencyRateTypeUsers;
	}


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                            
    public static final String NQ_findByCode = "common_CurrencyRateType_findByCode";
    public static CurrencyRateType findByCode( java.lang.String code ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( code);
        return ( CurrencyRateType ) getGenericSession().getSingleResult(NQ_findByCode, params);
    }

	//Unique keys methods with Relations as parameters
	//model defined finders


    public static CurrencyRateType find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( CurrencyRateType.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
