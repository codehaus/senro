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
import ro.siveco.svapnt.common.entity.CityType;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Tip Localitate<br>
 * </p>
 * <p>
 * Vezi documentul dictionar de date<br>
 * </p>
 * <p>
 * Catalogul de tipuri orase
 * </p>
 *
 * constraintNamePK PK_CITY_TYPES
 * tableName CITY_TYPES
 * defaultValueFlag implicitValueFlag
 * formColumns 4
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.common.session.CityTypeMgrLocal.JNDI_NAME )
public abstract class CityTypeBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "common_CityType";
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

    @Column( name = "IMPLICIT_VALUE_FLAG" )
	private java.lang.String			    implicitValueFlag;

    @Column( name = "URBAN_FLAG" )
	private java.lang.String			    urbanFlag;

	/* Vduped Attributes */

	/* Relationships */

    @OneToMany( mappedBy = "cityType", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.City>     cities = new ArrayList();

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
@OrderNo("10")
@Required(true)
@Length("20")
@VisibleDetail(true)
@UniqueKey("UK_CITY_TYPES")
@VisibleReferred(true)
@VisibleList(true)
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
@OrderNo("20")
@VisibleDetail(true)
@Required(true)
@VisibleReferred(true)
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

    @Column( name = "IMPLICIT_VALUE_FLAG" )
    @PersistentFieldGetter( fieldName = "implicitValueFlag" )
@VisibleList(true)
@OrderNo("30")
@Widget("CK")
@VisibleDetail(true)
@Label("Default city type")
@Unfiltered(true)
	/**
	  *  Access method for the implicitValueFlag property.
	  *  @return the current value of the implicitValueFlag property
	  */
	public java.lang.String getImplicitValueFlag()
	{
		return implicitValueFlag;
	}

	/**
	  *  Set method for the implicitValueFlag property.
	  */
	public void setImplicitValueFlag( java.lang.String _implicitValueFlag )
	{
		implicitValueFlag = _implicitValueFlag;
	}

    @Column( name = "URBAN_FLAG" )
    @PersistentFieldGetter( fieldName = "urbanFlag" )
@VisibleDetail(true)
@VisibleList(true)
@OrderNo("40")
	/**
	  *  Access method for the urbanFlag property.
	  *  @return the current value of the urbanFlag property
	  */
	public java.lang.String getUrbanFlag()
	{
		return urbanFlag;
	}

	/**
	  *  Set method for the urbanFlag property.
	  */
	public void setUrbanFlag( java.lang.String _urbanFlag )
	{
		urbanFlag = _urbanFlag;
	}


	/* Vduped Attribute setters */

	/* Relationship getters & setters */

    @OneToMany( mappedBy = "cityType", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "city" )
	/**
	  *  Access method for the cities property.
	  *  @return the current value of the cities property
	  */
	public Collection<ro.siveco.svapnt.common.entity.City> getCities()
	{
		return cities;
	}

	/**
	  *  Set method for the cities property.
	  */
	public void setCities( Collection<ro.siveco.svapnt.common.entity.City> _cities )
	{
		cities = _cities;
	}


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                                   
    public static final String NQ_findByCode = "common_CityType_findByCode";
    public static CityType findByCode( java.lang.String code ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( code);
        return ( CityType ) getGenericSession().getSingleResult(NQ_findByCode, params);
    }

	//Unique keys methods with Relations as parameters
	//model defined finders


    public static CityType find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( CityType.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
