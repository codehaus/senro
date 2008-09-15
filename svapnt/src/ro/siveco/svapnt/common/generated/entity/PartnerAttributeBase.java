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
import ro.siveco.svapnt.common.entity.PartnerAttribute;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Atribute parteneri<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.common.session.PartnerAttributeMgrLocal.JNDI_NAME )
public abstract class PartnerAttributeBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "common_PartnerAttribute";
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

    @Column( name = "ATTR_TYPE" )
	private java.lang.String			    attrType;

    @Column( name = "INTERVAL" )
	private java.lang.String			    interval;

    @Column( name = "BANK" )
	private java.lang.String			    bank;

    @Column( name = "COMPANY" )
	private java.lang.String			    company;

    @Column( name = "ORG_UNIT" )
	private java.lang.String			    orgUnit;

    @Column( name = "PERSON" )
	private java.lang.String			    person;

    @Column( name = "STORE" )
	private java.lang.String			    store;

    @Column( name = "HAS_IMPLICIT_VALUE" )
	private java.lang.String			    hasImplicitValue = "1";

    @Column( name = "HAS_VALIDATION" )
	private java.lang.String			    hasValidation = "0";

    @Column( name = "IS_MODIFIABLE" )
	private java.lang.String			    isModifiable = "D";

    @Column( name = "FIELD_NAME" )
	private java.lang.String			    fieldName;

    @Column( name = "SELECT_STATEMENT" )
	private java.lang.String			    selectStatement;

    @Column( name = "URL_SELECTOR" )
	private java.lang.String			    urlSelector;

    @Column( name = "ERROR_CODE" )
	private java.lang.String			    errorCode;

    @Column( name = "DEFAULT_WHERE" )
	private java.lang.String			    defaultWhere;

	/* Vduped Attributes */

	/* Relationships */

    @OneToMany( mappedBy = "partnerAttribute", cascade = ALL )
	private Collection<ro.siveco.svapnt.common.entity.PartnerAttributeValue>     partnerAttributeValues = new ArrayList();

    @OneToMany( mappedBy = "partnerAttribute", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.PartnerCategoryAttribute>     partnerCategoryAttributes = new ArrayList();

    @OneToMany( mappedBy = "attribute", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.PartnerAttributeWrapper>     partnerAttributeWrappers = new ArrayList();

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

    @Column( name = "CODE" )
    @PersistentFieldGetter( fieldName = "code" )
@VisibleDetail(true)
@Required(true)
@Length("5")
@VisibleList(true)
@VisibleReferred(true)
@OrderNo("10")
@UniqueKey("UK_PARTNER_ATTR")
@OrderBy("1|ASC")
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
@Required(true)
@VisibleDetail(true)
@VisibleList(true)
@Length("40")
@OrderNo("20")
@VisibleReferred(true)
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

    @Column( name = "ATTR_TYPE" )
    @PersistentFieldGetter( fieldName = "attrType" )
@WidgetValues("D|Date;S|String;N|Number")
@VisibleList(true)
@Widget("CB")
@VisibleDetail(true)
@Required(true)
@Label("Type")
@OrderNo("30")
@FormColumn("0")
@Unchangeable(true)
@Length("1")
@VisibleReferred(true)
	/**
	  *  Access method for the attrType property.
	  *  @return the current value of the attrType property
	  */
	public java.lang.String getAttrType()
	{
		return attrType;
	}

	/**
	  *  Set method for the attrType property.
	  */
	public void setAttrType( java.lang.String _attrType )
	{
		attrType = _attrType;
	}

    @Column( name = "INTERVAL" )
    @PersistentFieldGetter( fieldName = "interval" )
@VisibleList(true)
@VisibleDetail(true)
@OrderNo("40")
@Unchangeable(true)
@VisibleReferred(true)
	/**
	  *  Access method for the interval property.
	  *  @return the current value of the interval property
	  */
	public java.lang.String getInterval()
	{
		return interval;
	}

	/**
	  *  Set method for the interval property.
	  */
	public void setInterval( java.lang.String _interval )
	{
		interval = _interval;
	}

    @Column( name = "BANK" )
    @PersistentFieldGetter( fieldName = "bank" )
@VisibleDetail(true)
@VisibleList(true)
@OrderNo("50")
@FormColumn("0")
	/**
	  *  Access method for the bank property.
	  *  @return the current value of the bank property
	  */
	public java.lang.String getBank()
	{
		return bank;
	}

	/**
	  *  Set method for the bank property.
	  */
	public void setBank( java.lang.String _bank )
	{
		bank = _bank;
	}

    @Column( name = "COMPANY" )
    @PersistentFieldGetter( fieldName = "company" )
@VisibleDetail(true)
@VisibleList(true)
@OrderNo("60")
	/**
	  *  Access method for the company property.
	  *  @return the current value of the company property
	  */
	public java.lang.String getCompany()
	{
		return company;
	}

	/**
	  *  Set method for the company property.
	  */
	public void setCompany( java.lang.String _company )
	{
		company = _company;
	}

    @Column( name = "ORG_UNIT" )
    @PersistentFieldGetter( fieldName = "orgUnit" )
@VisibleDetail(true)
@VisibleList(true)
@OrderNo("70")
	/**
	  *  Access method for the orgUnit property.
	  *  @return the current value of the orgUnit property
	  */
	public java.lang.String getOrgUnit()
	{
		return orgUnit;
	}

	/**
	  *  Set method for the orgUnit property.
	  */
	public void setOrgUnit( java.lang.String _orgUnit )
	{
		orgUnit = _orgUnit;
	}

    @Column( name = "PERSON" )
    @PersistentFieldGetter( fieldName = "person" )
@VisibleList(true)
@VisibleDetail(true)
@OrderNo("80")
	/**
	  *  Access method for the person property.
	  *  @return the current value of the person property
	  */
	public java.lang.String getPerson()
	{
		return person;
	}

	/**
	  *  Set method for the person property.
	  */
	public void setPerson( java.lang.String _person )
	{
		person = _person;
	}

    @Column( name = "STORE" )
    @PersistentFieldGetter( fieldName = "store" )
@VisibleDetail(true)
@VisibleList(true)
@OrderNo("90")
	/**
	  *  Access method for the store property.
	  *  @return the current value of the store property
	  */
	public java.lang.String getStore()
	{
		return store;
	}

	/**
	  *  Set method for the store property.
	  */
	public void setStore( java.lang.String _store )
	{
		store = _store;
	}

    @Column( name = "HAS_IMPLICIT_VALUE" )
    @PersistentFieldGetter( fieldName = "hasImplicitValue" )
	/**
	  *  Access method for the hasImplicitValue property.
	  *  @return the current value of the hasImplicitValue property
	  */
	public java.lang.String getHasImplicitValue()
	{
		return hasImplicitValue;
	}

	/**
	  *  Set method for the hasImplicitValue property.
	  */
	public void setHasImplicitValue( java.lang.String _hasImplicitValue )
	{
		hasImplicitValue = _hasImplicitValue;
	}

    @Column( name = "HAS_VALIDATION" )
    @PersistentFieldGetter( fieldName = "hasValidation" )
	/**
	  *  Access method for the hasValidation property.
	  *  @return the current value of the hasValidation property
	  */
	public java.lang.String getHasValidation()
	{
		return hasValidation;
	}

	/**
	  *  Set method for the hasValidation property.
	  */
	public void setHasValidation( java.lang.String _hasValidation )
	{
		hasValidation = _hasValidation;
	}

    @Column( name = "IS_MODIFIABLE" )
    @PersistentFieldGetter( fieldName = "isModifiable" )
@Length("1")
	/**
	  *  Access method for the isModifiable property.
	  *  @return the current value of the isModifiable property
	  */
	public java.lang.String getIsModifiable()
	{
		return isModifiable;
	}

	/**
	  *  Set method for the isModifiable property.
	  */
	public void setIsModifiable( java.lang.String _isModifiable )
	{
		isModifiable = _isModifiable;
	}

    @Column( name = "FIELD_NAME" )
    @PersistentFieldGetter( fieldName = "fieldName" )
@Length("30")
	/**
	  *  Access method for the fieldName property.
	  *  @return the current value of the fieldName property
	  */
	public java.lang.String getFieldName()
	{
		return fieldName;
	}

	/**
	  *  Set method for the fieldName property.
	  */
	public void setFieldName( java.lang.String _fieldName )
	{
		fieldName = _fieldName;
	}

    @Column( name = "SELECT_STATEMENT" )
    @PersistentFieldGetter( fieldName = "selectStatement" )
@Length("1000")
	/**
	  *  Access method for the selectStatement property.
	  *  @return the current value of the selectStatement property
	  */
	public java.lang.String getSelectStatement()
	{
		return selectStatement;
	}

	/**
	  *  Set method for the selectStatement property.
	  */
	public void setSelectStatement( java.lang.String _selectStatement )
	{
		selectStatement = _selectStatement;
	}

    @Column( name = "URL_SELECTOR" )
    @PersistentFieldGetter( fieldName = "urlSelector" )
@Length("1000")
	/**
	  *  Access method for the urlSelector property.
	  *  @return the current value of the urlSelector property
	  */
	public java.lang.String getUrlSelector()
	{
		return urlSelector;
	}

	/**
	  *  Set method for the urlSelector property.
	  */
	public void setUrlSelector( java.lang.String _urlSelector )
	{
		urlSelector = _urlSelector;
	}

    @Column( name = "ERROR_CODE" )
    @PersistentFieldGetter( fieldName = "errorCode" )
@Length("100")
	/**
	  *  Access method for the errorCode property.
	  *  @return the current value of the errorCode property
	  */
	public java.lang.String getErrorCode()
	{
		return errorCode;
	}

	/**
	  *  Set method for the errorCode property.
	  */
	public void setErrorCode( java.lang.String _errorCode )
	{
		errorCode = _errorCode;
	}

    @Column( name = "DEFAULT_WHERE" )
    @PersistentFieldGetter( fieldName = "defaultWhere" )
@Length("1000")
	/**
	  *  Access method for the defaultWhere property.
	  *  @return the current value of the defaultWhere property
	  */
	public java.lang.String getDefaultWhere()
	{
		return defaultWhere;
	}

	/**
	  *  Set method for the defaultWhere property.
	  */
	public void setDefaultWhere( java.lang.String _defaultWhere )
	{
		defaultWhere = _defaultWhere;
	}


	/* Vduped Attribute setters */

	/* Relationship getters & setters */

    @OneToMany( mappedBy = "partnerAttribute", cascade = ALL )
    @Transactional
    @PersistentToManyGetter( relName = "partnerAttributeValue" )
	/**
	  *  Access method for the partnerAttributeValues property.
	  *  @return the current value of the partnerAttributeValues property
	  */
	public Collection<ro.siveco.svapnt.common.entity.PartnerAttributeValue> getPartnerAttributeValues()
	{
		return partnerAttributeValues;
	}

	/**
	  *  Set method for the partnerAttributeValues property.
	  */
	public void setPartnerAttributeValues( Collection<ro.siveco.svapnt.common.entity.PartnerAttributeValue> _partnerAttributeValues )
	{
		partnerAttributeValues = _partnerAttributeValues;
	}


    @OneToMany( mappedBy = "partnerAttribute", cascade = {PERSIST, MERGE, REFRESH} )
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


    @OneToMany( mappedBy = "attribute", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "partnerAttributeWrapper" )
	/**
	  *  Access method for the partnerAttributeWrappers property.
	  *  @return the current value of the partnerAttributeWrappers property
	  */
	public Collection<ro.siveco.svapnt.common.entity.PartnerAttributeWrapper> getPartnerAttributeWrappers()
	{
		return partnerAttributeWrappers;
	}

	/**
	  *  Set method for the partnerAttributeWrappers property.
	  */
	public void setPartnerAttributeWrappers( Collection<ro.siveco.svapnt.common.entity.PartnerAttributeWrapper> _partnerAttributeWrappers )
	{
		partnerAttributeWrappers = _partnerAttributeWrappers;
	}


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                                                                                                                              
    public static final String NQ_findByCode = "common_PartnerAttribute_findByCode";
    public static PartnerAttribute findByCode( java.lang.String code ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( code);
        return ( PartnerAttribute ) getGenericSession().getSingleResult(NQ_findByCode, params);
    }

	//Unique keys methods with Relations as parameters
	//model defined finders


    public static PartnerAttribute find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( PartnerAttribute.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
