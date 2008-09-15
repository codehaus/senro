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
import ro.siveco.svapnt.common.entity.PartnerAttributeValue;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Valori atribute parteneri<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * finders com_pav_1|false||AND|;com_pav_2|false||AND|;com_pav_3|false||AND|;com_pav_4|false||AND|;com_pav_5|false||AND|;com_pav_6|false||AND|
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.common.session.PartnerAttributeValueMgrLocal.JNDI_NAME )
public abstract class PartnerAttributeValueBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "common_PartnerAttributeValue";
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

    @Column( name = "MIN_DATE_VAL" )
	private java.util.Date			    minDateVal;

    @Column( name = "MAX_DATE_VALUE" )
	private java.util.Date			    maxDateValue;

    @Column( name = "MIN_STRING_VALUE" )
	private java.lang.String			    minStringValue;

    @Column( name = "MAX_STRING_VALUE" )
	private java.lang.String			    maxStringValue;

    @Column( name = "MIN_NUMBER_VALUE" )
	private java.lang.Double			    minNumberValue;

    @Column( name = "MAX_NUMBER_VALUE" )
	private java.lang.Double			    maxNumberValue;

    @Column( name = "VALUE_TYPE" )
	private java.lang.String			    valueType;

    @Column( name = "NO_TIMES_WAS_USED" )
	private java.lang.Long			    noTimesWasUsed = 0L;

    @Column( name = "LAST_USED_DATE" )
	private java.util.Date			    lastUsedDate;

	/* Vduped Attributes */

	/* Relationships */

    @ManyToOne( optional =  false )
    @JoinColumn( name = "PARTNER_ATTRIBUTE_ID" )
// partnerAttribute
	private ro.siveco.svapnt.common.entity.PartnerAttribute			    partnerAttribute;

    @OneToMany( mappedBy = "partnerAttributeValue", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.PartnerCategoryAttribute>     partnerCategoryAttributes = new ArrayList();

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

    @Column( name = "MIN_DATE_VAL" )
    @PersistentFieldGetter( fieldName = "minDateVal" )
@VisibleList(true)
@VisibleDetail(true)
@OrderNo("10")
@VisibleReferred(true)
@UniqueKey("UK_PART_ATTR_VAL|3")
@Label("Minimum value")
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

    @Column( name = "MAX_DATE_VALUE" )
    @PersistentFieldGetter( fieldName = "maxDateValue" )
@VisibleList(true)
@VisibleDetail(true)
@OrderNo("40")
@Label("Maximum value")
@VisibleReferred(true)
@UniqueKey("UK_PART_ATTR_VAL|6")
@OrderBy("2|ASC")
	/**
	  *  Access method for the maxDateValue property.
	  *  @return the current value of the maxDateValue property
	  */
	public java.util.Date getMaxDateValue()
	{
		return maxDateValue;
	}

	/**
	  *  Set method for the maxDateValue property.
	  */
	public void setMaxDateValue( java.util.Date _maxDateValue )
	{
		maxDateValue = _maxDateValue;
	}

    @Column( name = "MIN_STRING_VALUE" )
    @PersistentFieldGetter( fieldName = "minStringValue" )
@VisibleList(true)
@VisibleDetail(true)
@Length("100")
@OrderNo("20")
@VisibleReferred(true)
@UniqueKey("UK_PART_ATTR_VAL|2")
@Label("Minimum value")
@OrderBy("3|ASC")
	/**
	  *  Access method for the minStringValue property.
	  *  @return the current value of the minStringValue property
	  */
	public java.lang.String getMinStringValue()
	{
		return minStringValue;
	}

	/**
	  *  Set method for the minStringValue property.
	  */
	public void setMinStringValue( java.lang.String _minStringValue )
	{
		minStringValue = _minStringValue;
	}

    @Column( name = "MAX_STRING_VALUE" )
    @PersistentFieldGetter( fieldName = "maxStringValue" )
@VisibleList(true)
@VisibleDetail(true)
@Length("100")
@OrderNo("50")
@Label("Maximum value")
@FormColumn("0")
@VisibleReferred(true)
@UniqueKey("UK_PART_ATTR_VAL|5")
@OrderBy("4|ASC")
	/**
	  *  Access method for the maxStringValue property.
	  *  @return the current value of the maxStringValue property
	  */
	public java.lang.String getMaxStringValue()
	{
		return maxStringValue;
	}

	/**
	  *  Set method for the maxStringValue property.
	  */
	public void setMaxStringValue( java.lang.String _maxStringValue )
	{
		maxStringValue = _maxStringValue;
	}

    @Column( name = "MIN_NUMBER_VALUE" )
    @PersistentFieldGetter( fieldName = "minNumberValue" )
@VisibleList(true)
@VisibleDetail(true)
@OrderNo("30")
@VisibleReferred(true)
@UniqueKey("UK_PART_ATTR_VAL|1")
@Label("Minimum value")
@OrderBy("5|ASC")
	/**
	  *  Access method for the minNumberValue property.
	  *  @return the current value of the minNumberValue property
	  */
	public java.lang.Double getMinNumberValue()
	{
		return minNumberValue;
	}

	/**
	  *  Set method for the minNumberValue property.
	  */
	public void setMinNumberValue( java.lang.Double _minNumberValue )
	{
		minNumberValue = _minNumberValue;
	}

    @Column( name = "MAX_NUMBER_VALUE" )
    @PersistentFieldGetter( fieldName = "maxNumberValue" )
@VisibleDetail(true)
@VisibleList(true)
@OrderNo("60")
@Label("Maximum value")
@VisibleReferred(true)
@UniqueKey("UK_PART_ATTR_VAL|4")
@OrderBy("6|ASC")
	/**
	  *  Access method for the maxNumberValue property.
	  *  @return the current value of the maxNumberValue property
	  */
	public java.lang.Double getMaxNumberValue()
	{
		return maxNumberValue;
	}

	/**
	  *  Set method for the maxNumberValue property.
	  */
	public void setMaxNumberValue( java.lang.Double _maxNumberValue )
	{
		maxNumberValue = _maxNumberValue;
	}

    @Column( name = "VALUE_TYPE" )
    @PersistentFieldGetter( fieldName = "valueType" )
@Length("1")
@Required(true)
	/**
	  *  Access method for the valueType property.
	  *  @return the current value of the valueType property
	  */
	public java.lang.String getValueType()
	{
		return valueType;
	}

	/**
	  *  Set method for the valueType property.
	  */
	public void setValueType( java.lang.String _valueType )
	{
		valueType = _valueType;
	}

    @Column( name = "NO_TIMES_WAS_USED" )
    @PersistentFieldGetter( fieldName = "noTimesWasUsed" )
	/**
	  *  Access method for the noTimesWasUsed property.
	  *  @return the current value of the noTimesWasUsed property
	  */
	public java.lang.Long getNoTimesWasUsed()
	{
		return noTimesWasUsed;
	}

	/**
	  *  Set method for the noTimesWasUsed property.
	  */
	public void setNoTimesWasUsed( java.lang.Long _noTimesWasUsed )
	{
		noTimesWasUsed = _noTimesWasUsed;
	}

    @Column( name = "LAST_USED_DATE" )
    @PersistentFieldGetter( fieldName = "lastUsedDate" )
	/**
	  *  Access method for the lastUsedDate property.
	  *  @return the current value of the lastUsedDate property
	  */
	public java.util.Date getLastUsedDate()
	{
		return lastUsedDate;
	}

	/**
	  *  Set method for the lastUsedDate property.
	  */
	public void setLastUsedDate( java.util.Date _lastUsedDate )
	{
		lastUsedDate = _lastUsedDate;
	}


	/* Vduped Attribute setters */

	/* Relationship getters & setters */
    @PersistentToOneGetter( relName = "partnerAttribute" )

    @ManyToOne( optional =  false )
    @JoinColumn( name = "PARTNER_ATTRIBUTE_ID" )
// partnerAttribute
    @UniqueKey
	/**
	  *  Access method for the partnerAttribute property.
	  *  @return the current value of the partnerAttribute property
	  */
	public ro.siveco.svapnt.common.entity.PartnerAttribute getPartnerAttribute()
	{
		return partnerAttribute;
	}

	/**
	  *  Set method for the partnerAttribute property.
	  */
	public void setPartnerAttribute( ro.siveco.svapnt.common.entity.PartnerAttribute _partnerAttribute )
	{
		partnerAttribute = _partnerAttribute;
	}


    @OneToMany( mappedBy = "partnerAttributeValue", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "partnerCategoryAttribute" )
	/**
	  *  Access method for the partnerCategoryAttributes property.
	  *  @return the current value of the partnerCategoryAttributes property
	  */
	public Collection<ro.siveco.svapnt.common.entity.PartnerCategoryAttribute> getPartnerCategoryAttributes()
	{
		return partnerCategoryAttributes;
	}

	/**
	  *  Set method for the partnerCategoryAttributes property.
	  */
	public void setPartnerCategoryAttributes( Collection<ro.siveco.svapnt.common.entity.PartnerCategoryAttribute> _partnerCategoryAttributes )
	{
		partnerCategoryAttributes = _partnerCategoryAttributes;
	}


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
    public static final String NQ_findByMinDateValAndMaxDateValueAndMinStringValueAndMaxStringValueAndMinNumberValueAndMaxNumberValueAndPartnerAttribute = "common_PartnerAttributeValue_findByMinDateValAndMaxDateValueAndMinStringValueAndMaxStringValueAndMinNumberValueAndMaxNumberValueAndPartnerAttribute";
    public static PartnerAttributeValue findByMinDateValAndMaxDateValueAndMinStringValueAndMaxStringValueAndMinNumberValueAndMaxNumberValueAndPartnerAttribute( java.util.Date minDateVal, java.util.Date maxDateValue, java.lang.String minStringValue, java.lang.String maxStringValue, java.lang.Double minNumberValue, java.lang.Double maxNumberValue, ro.siveco.svapnt.common.entity.PartnerAttribute partnerAttribute ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( minDateVal);
                params.add( maxDateValue);
                params.add( minStringValue);
                params.add( maxStringValue);
                params.add( minNumberValue);
                params.add( maxNumberValue);
                params.add(partnerAttribute.getId());
        return ( PartnerAttributeValue ) getGenericSession().getSingleResult(NQ_findByMinDateValAndMaxDateValueAndMinStringValueAndMaxStringValueAndMinNumberValueAndMaxNumberValueAndPartnerAttribute, params);
    }

	//Unique keys methods with Relations as parameters
	//model defined finders

    public static final String NQ_F1findByMinDateValAndMaxDateValue = "PartnerAttributeValue_F1findByMinDateValAndMaxDateValue";
       
    public static List<PartnerAttributeValue> findByMinDateValAndMaxDateValue( java.util.Date p1_MinDateVal, java.util.Date p2_MaxDateValue) throws DAOException
    {

        ArrayList params = new ArrayList();

            params.add( p1_MinDateVal);
            params.add( p2_MaxDateValue);
        return ( List<PartnerAttributeValue> ) getGenericSession().getResultList(NQ_F1findByMinDateValAndMaxDateValue, params);
    }
    public static final String NQ_F2findByMinDateValAndMaxDateValue = "PartnerAttributeValue_F2findByMinDateValAndMaxDateValue";
       
    public static List<PartnerAttributeValue> findByMinDateValAndMaxDateValue( java.util.Date p1_MinDateVal) throws DAOException
    {

        ArrayList params = new ArrayList();

            params.add( p1_MinDateVal);
        return ( List<PartnerAttributeValue> ) getGenericSession().getResultList(NQ_F2findByMinDateValAndMaxDateValue, params);
    }
    public static final String NQ_F3findByMinStringValueAndMaxStringValue = "PartnerAttributeValue_F3findByMinStringValueAndMaxStringValue";
       
    public static List<PartnerAttributeValue> findByMinStringValueAndMaxStringValue( java.lang.String p1_MinStringValue, java.lang.String p2_MaxStringValue) throws DAOException
    {

        ArrayList params = new ArrayList();

            params.add( p1_MinStringValue);
            params.add( p2_MaxStringValue);
        return ( List<PartnerAttributeValue> ) getGenericSession().getResultList(NQ_F3findByMinStringValueAndMaxStringValue, params);
    }
    public static final String NQ_F4findByMinStringValueAndMaxStringValue = "PartnerAttributeValue_F4findByMinStringValueAndMaxStringValue";
       
    public static List<PartnerAttributeValue> findByMinStringValueAndMaxStringValue( java.lang.String p1_MinStringValue) throws DAOException
    {

        ArrayList params = new ArrayList();

            params.add( p1_MinStringValue);
        return ( List<PartnerAttributeValue> ) getGenericSession().getResultList(NQ_F4findByMinStringValueAndMaxStringValue, params);
    }
    public static final String NQ_F5findByMinNumberValueAndMaxNumberValue = "PartnerAttributeValue_F5findByMinNumberValueAndMaxNumberValue";
       
    public static List<PartnerAttributeValue> findByMinNumberValueAndMaxNumberValue( java.lang.Double p1_MinNumberValue, java.lang.Double p2_MaxNumberValue) throws DAOException
    {

        ArrayList params = new ArrayList();

            params.add( p1_MinNumberValue);
            params.add( p2_MaxNumberValue);
        return ( List<PartnerAttributeValue> ) getGenericSession().getResultList(NQ_F5findByMinNumberValueAndMaxNumberValue, params);
    }
    public static final String NQ_F6findByMinNumberValueAndMaxNumberValue = "PartnerAttributeValue_F6findByMinNumberValueAndMaxNumberValue";
       
    public static List<PartnerAttributeValue> findByMinNumberValueAndMaxNumberValue( java.lang.Double p1_MinNumberValue) throws DAOException
    {

        ArrayList params = new ArrayList();

            params.add( p1_MinNumberValue);
        return ( List<PartnerAttributeValue> ) getGenericSession().getResultList(NQ_F6findByMinNumberValueAndMaxNumberValue, params);
    }

    public static PartnerAttributeValue find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( PartnerAttributeValue.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
