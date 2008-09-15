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
import ro.siveco.svapnt.common.entity.CurrencyRateTypeUser;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Tipul de curs folosit de utilizatorul conectat la aplicatie<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * tableName CURRENCY_RATE_TYPE_USERS
 * constraintNamePK PK_CURR_RT_TYP_USR
 *
 */

 

 
@MappedSuperclass
public abstract class CurrencyRateTypeUserBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "common_CurrencyRateTypeUser";
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

    @Column( name = "APP_USER" )
	private java.lang.String			    appUser;

	/* Vduped Attributes */

	/* Relationships */

    @ManyToOne( optional =  true )
    @JoinColumn( name = "CRNCY_RATE_TYPE_ID" )
// currencyRateType
	private ro.siveco.svapnt.common.entity.CurrencyRateType			    currencyRateType;

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

    @Column( name = "APP_USER" )
    @PersistentFieldGetter( fieldName = "appUser" )
@Required(true)
@VisibleDetail(true)
@OrderNo("2")
@VisibleList(true)
@Length("30")
@UniqueKey("UK_CRN_RATE_TYPE_USER")
	/**
	  *  Access method for the appUser property.
	  *  @return the current value of the appUser property
	  */
	public java.lang.String getAppUser()
	{
		return appUser;
	}

	/**
	  *  Set method for the appUser property.
	  */
	public void setAppUser( java.lang.String _appUser )
	{
		appUser = _appUser;
	}


	/* Vduped Attribute setters */

	/* Relationship getters & setters */
    @PersistentToOneGetter( relName = "currencyRateType" )

    @ManyToOne( optional =  true )
    @JoinColumn( name = "CRNCY_RATE_TYPE_ID" )
// currencyRateType
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


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                           
    public static final String NQ_findByAppUser = "common_CurrencyRateTypeUser_findByAppUser";
    public static CurrencyRateTypeUser findByAppUser( java.lang.String appUser ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( appUser);
        return ( CurrencyRateTypeUser ) getGenericSession().getSingleResult(NQ_findByAppUser, params);
    }

	//Unique keys methods with Relations as parameters
	//model defined finders


    public static CurrencyRateTypeUser find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( CurrencyRateTypeUser.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
