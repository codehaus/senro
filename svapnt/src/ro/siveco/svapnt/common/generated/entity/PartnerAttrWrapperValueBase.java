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
import ro.siveco.svapnt.common.entity.PartnerAttrWrapperValue;
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
 * formColumns 3
 * validTo validTo
 * validFrom validFrom
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.common.session.PartnerAttrWrapperValueMgrLocal.JNDI_NAME )
public abstract class PartnerAttrWrapperValueBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "common_PartnerAttrWrapperValue";
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

    @Column( name = "MIN_STRING_VAL" )
	private java.lang.String			    minStringVal;

    @Column( name = "MIN_DATE_VAL" )
	private java.util.Date			    minDateVal;

    @Column( name = "MIN_NUMBER_VAL" )
	private java.lang.Double			    minNumberVal;

    @Column( name = "MAX_STRING_VAL" )
	private java.lang.String			    maxStringVal;

    @Column( name = "MAX_DATE_VAL" )
	private java.util.Date			    maxDateVal;

    @Column( name = "MAX_NUMBER_VAL" )
	private java.lang.Double			    maxNumberVal;

    @Column( name = "DEFAULT_VALUE" )
	private java.lang.String			    defaultValue;

    @Column( name = "VALID_FROM" )
	private java.util.Date			    validFrom;

    @Column( name = "VALID_TO" )
	private java.util.Date			    validTo;

	/* Vduped Attributes */

	/* Relationships */

    @ManyToOne( optional =  false )
    @JoinColumn( name = "WRAPPER_ID" )
// wrapper
	private ro.siveco.svapnt.common.entity.PartnerAttributeWrapper			    wrapper;

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

    @Column( name = "MIN_STRING_VAL" )
    @PersistentFieldGetter( fieldName = "minStringVal" )
@VisibleList(true)
@UniqueKey("UK_PARTNER_ATTR_VAL|1")
@VisibleDetail(true)
@Length("100")
@OrderNo("10")
@OrderBy("3|ASC")
	/**
	  *  Access method for the minStringVal property.
	  *  @return the current value of the minStringVal property
	  */
	public java.lang.String getMinStringVal()
	{
		return minStringVal;
	}

	/**
	  *  Set method for the minStringVal property.
	  */
	public void setMinStringVal( java.lang.String _minStringVal )
	{
		minStringVal = _minStringVal;
	}

    @Column( name = "MIN_DATE_VAL" )
    @PersistentFieldGetter( fieldName = "minDateVal" )
@VisibleList(true)
@UniqueKey("UK_PARTNER_ATTR_VAL|3")
@VisibleDetail(true)
@OrderNo("30")
@OrderBy("1|ASC")
	/**
	  *  Access method for the minDateVal property.
	  *  @return the current value of the minDateVal property
	  */
	public java.util.Date getMinDateVal()
	{
		return minDateVal;
	}

	/**
	  *  Set method for the minDateVal property.
	  */
	public void setMinDateVal( java.util.Date _minDateVal )
	{
		minDateVal = _minDateVal;
	}

    @Column( name = "MIN_NUMBER_VAL" )
    @PersistentFieldGetter( fieldName = "minNumberVal" )
@VisibleList(true)
@UniqueKey("UK_PARTNER_ATTR_VAL|5")
@VisibleDetail(true)
@OrderNo("50")
@OrderBy("5|ASC")
	/**
	  *  Access method for the minNumberVal property.
	  *  @return the current value of the minNumberVal property
	  */
	public java.lang.Double getMinNumberVal()
	{
		return minNumberVal;
	}

	/**
	  *  Set method for the minNumberVal property.
	  */
	public void setMinNumberVal( java.lang.Double _minNumberVal )
	{
		minNumberVal = _minNumberVal;
	}

    @Column( name = "MAX_STRING_VAL" )
    @PersistentFieldGetter( fieldName = "maxStringVal" )
@VisibleDetail(true)
@VisibleList(true)
@Length("100")
@OrderNo("20")
@UniqueKey("UK_PARTNER_ATTR_VAL|2")
@OrderBy("4|ASC")
	/**
	  *  Access method for the maxStringVal property.
	  *  @return the current value of the maxStringVal property
	  */
	public java.lang.String getMaxStringVal()
	{
		return maxStringVal;
	}

	/**
	  *  Set method for the maxStringVal property.
	  */
	public void setMaxStringVal( java.lang.String _maxStringVal )
	{
		maxStringVal = _maxStringVal;
	}

    @Column( name = "MAX_DATE_VAL" )
    @PersistentFieldGetter( fieldName = "maxDateVal" )
@VisibleDetail(true)
@VisibleList(true)
@OrderNo("40")
@UniqueKey("UK_PARTNER_ATTR_VAL|4")
@OrderBy("2|ASC")
	/**
	  *  Access method for the maxDateVal property.
	  *  @return the current value of the maxDateVal property
	  */
	public java.util.Date getMaxDateVal()
	{
		return maxDateVal;
	}

	/**
	  *  Set method for the maxDateVal property.
	  */
	public void setMaxDateVal( java.util.Date _maxDateVal )
	{
		maxDateVal = _maxDateVal;
	}

    @Column( name = "MAX_NUMBER_VAL" )
    @PersistentFieldGetter( fieldName = "maxNumberVal" )
@VisibleList(true)
@VisibleDetail(true)
@OrderNo("60")
@UniqueKey("UK_PARTNER_ATTR_VAL|6")
@OrderBy("6|ASC")
	/**
	  *  Access method for the maxNumberVal property.
	  *  @return the current value of the maxNumberVal property
	  */
	public java.lang.Double getMaxNumberVal()
	{
		return maxNumberVal;
	}

	/**
	  *  Set method for the maxNumberVal property.
	  */
	public void setMaxNumberVal( java.lang.Double _maxNumberVal )
	{
		maxNumberVal = _maxNumberVal;
	}

    @Column( name = "DEFAULT_VALUE" )
    @PersistentFieldGetter( fieldName = "defaultValue" )
@OrderNo("70")
	/**
	  *  Access method for the defaultValue property.
	  *  @return the current value of the defaultValue property
	  */
	public java.lang.String getDefaultValue()
	{
		return defaultValue;
	}

	/**
	  *  Set method for the defaultValue property.
	  */
	public void setDefaultValue( java.lang.String _defaultValue )
	{
		defaultValue = _defaultValue;
	}

    @Column( name = "VALID_FROM" )
    @PersistentFieldGetter( fieldName = "validFrom" )
@VisibleList(true)
@Required(true)
@VisibleDetail(true)
@OrderNo("80")
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
@VisibleList(true)
@VisibleDetail(true)
@OrderNo("90")
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
    @PersistentToOneGetter( relName = "wrapper" )

    @ManyToOne( optional =  false )
    @JoinColumn( name = "WRAPPER_ID" )
// wrapper
    @UniqueKey
	/**
	  *  Access method for the wrapper property.
	  *  @return the current value of the wrapper property
	  */
	public ro.siveco.svapnt.common.entity.PartnerAttributeWrapper getWrapper()
	{
		return wrapper;
	}

	/**
	  *  Set method for the wrapper property.
	  */
	public void setWrapper( ro.siveco.svapnt.common.entity.PartnerAttributeWrapper _wrapper )
	{
		wrapper = _wrapper;
	}


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
    public static final String NQ_findByMinStringValAndMinDateValAndMinNumberValAndMaxStringValAndMaxDateValAndMaxNumberValAndWrapper = "common_PartnerAttrWrapperValue_findByMinStringValAndMinDateValAndMinNumberValAndMaxStringValAndMaxDateValAndMaxNumberValAndWrapper";
    public static PartnerAttrWrapperValue findByMinStringValAndMinDateValAndMinNumberValAndMaxStringValAndMaxDateValAndMaxNumberValAndWrapper( java.lang.String minStringVal, java.util.Date minDateVal, java.lang.Double minNumberVal, java.lang.String maxStringVal, java.util.Date maxDateVal, java.lang.Double maxNumberVal, ro.siveco.svapnt.common.entity.PartnerAttributeWrapper wrapper ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( minStringVal);
                params.add( minDateVal);
                params.add( minNumberVal);
                params.add( maxStringVal);
                params.add( maxDateVal);
                params.add( maxNumberVal);
                params.add(wrapper.getId());
        return ( PartnerAttrWrapperValue ) getGenericSession().getSingleResult(NQ_findByMinStringValAndMinDateValAndMinNumberValAndMaxStringValAndMaxDateValAndMaxNumberValAndWrapper, params);
    }

	//Unique keys methods with Relations as parameters
	//model defined finders


    public static PartnerAttrWrapperValue find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( PartnerAttrWrapperValue.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
