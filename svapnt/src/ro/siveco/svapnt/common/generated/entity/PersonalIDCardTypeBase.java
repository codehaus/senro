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
import ro.siveco.svapnt.common.entity.PersonalIDCardType;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Tipuri de acte de identitate<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * constraintNamePK PK_ID_CARD_TYPES
 * tableName PERS_ID_CARD_TYPES
 * versionable true
 * validTo validTo
 * validFrom validFrom
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.common.session.PersonalIDCardTypeMgrLocal.JNDI_NAME )
public abstract class PersonalIDCardTypeBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "common_PersonalIDCardType";
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

    @Column( name = "CATEGORY" )
	private java.lang.String			    category;

    @Column( name = "VALID_FROM" )
	private java.util.Date			    validFrom;

    @Column( name = "VALID_TO" )
	private java.util.Date			    validTo;

    @Column( name = "VALID" )
	private java.lang.Short			    valid;

	/* Vduped Attributes */

	/* Relationships */

    @OneToMany( mappedBy = "personalIDCardType", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.PersonalIdCard>     personalIdCards = new ArrayList();

    @OneToMany( mappedBy = "personalIDCardType", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.EmployeeCard>     employeeCards = new ArrayList();

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
@UniqueKey("UK_ID_CARD_TYPES")
@Length("20")
@VisibleReferred(true)
@OrderNo("1")
@Required(true)
@VisibleDetail(true)
@VisibleList(true)
@VisibleInCombo(true)
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
@VisibleDetail(true)
@Required(true)
@OrderNo("2")
@VisibleList(true)
@Length("100")
@VisibleInCombo(true)
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

    @Column( name = "CATEGORY" )
    @PersistentFieldGetter( fieldName = "category" )
@VisibleList(true)
@VisibleDetail(true)
@Required(true)
@Length("20")
@WidgetValues("1|PASAPORTAL;2|SERVICIU")
@Widget("CB")
@OrderNo("3")
	/**
	  *  Access method for the category property.
	  *  @return the current value of the category property
	  */
	public java.lang.String getCategory()
	{
		return category;
	}

	/**
	  *  Set method for the category property.
	  */
	public void setCategory( java.lang.String _category )
	{
		category = _category;
	}

    @Column( name = "VALID_FROM" )
    @PersistentFieldGetter( fieldName = "validFrom" )
@VisibleList(true)
@ReadOnly(true)
@VisibleDetail(true)
@DatabaseRequired(true)
@OrderNo("200")
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
@OrderNo("210")
@VisibleList(true)
@VisibleDetail(true)
@ReadOnly(true)
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

    @Column( name = "VALID" )
    @PersistentFieldGetter( fieldName = "valid" )
@DatabaseRequired(true)
	/**
	  *  Access method for the valid property.
	  *  @return the current value of the valid property
	  */
	public java.lang.Short getValid()
	{
		return valid;
	}

	/**
	  *  Set method for the valid property.
	  */
	public void setValid( java.lang.Short _valid )
	{
		valid = _valid;
	}


	/* Vduped Attribute setters */

	/* Relationship getters & setters */

    @OneToMany( mappedBy = "personalIDCardType", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "personalIdCard" )
	/**
	  *  Access method for the personalIdCards property.
	  *  @return the current value of the personalIdCards property
	  */
	public Collection<ro.siveco.svapnt.common.entity.PersonalIdCard> getPersonalIdCards()
	{
		return personalIdCards;
	}

	/**
	  *  Set method for the personalIdCards property.
	  */
	public void setPersonalIdCards( Collection<ro.siveco.svapnt.common.entity.PersonalIdCard> _personalIdCards )
	{
		personalIdCards = _personalIdCards;
	}


    @OneToMany( mappedBy = "personalIDCardType", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "employeeCard" )
	/**
	  *  Access method for the employeeCards property.
	  *  @return the current value of the employeeCards property
	  */
	public Collection<ro.siveco.svapnt.common.entity.EmployeeCard> getEmployeeCards()
	{
		return employeeCards;
	}

	/**
	  *  Set method for the employeeCards property.
	  */
	public void setEmployeeCards( Collection<ro.siveco.svapnt.common.entity.EmployeeCard> _employeeCards )
	{
		employeeCards = _employeeCards;
	}


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                                                 
    public static final String NQ_findByCode = "common_PersonalIDCardType_findByCode";
    public static PersonalIDCardType findByCode( java.lang.String code ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( code);
        return ( PersonalIDCardType ) getGenericSession().getSingleResult(NQ_findByCode, params);
    }

	//Unique keys methods with Relations as parameters
	//model defined finders


    public static PersonalIDCardType find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( PersonalIDCardType.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
