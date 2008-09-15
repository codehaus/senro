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
import ro.siveco.svapnt.common.entity.BuildingType;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Tip Cladire<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in dictionarul de
 * date.
 * </p>
 *
 * tableName BUILDING_TYPES
 * constraintNamePK PK_BUILDING_TYPES
 * defaultValueFlag implicitValueFlag
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.common.session.BuildingTypeMgrLocal.JNDI_NAME )
public abstract class BuildingTypeBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "common_BuildingType";
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

	/* Vduped Attributes */

	/* Relationships */

    @OneToMany( mappedBy = "buildingType", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.PartnerAddress>     partnerAddresses = new ArrayList();

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
@Length("20")
@VisibleReferred(true)
@UniqueKey("UK_BUILDING_TYPES")
@VisibleDetail(true)
@Required(true)
@OrderNo("1")
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
@OrderNo("2")
@VisibleDetail(true)
@Length("100")
@Required(true)
@VisibleList(true)
@VisibleReferred(true)
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

    @Column( name = "IMPLICIT_VALUE_FLAG" )
    @PersistentFieldGetter( fieldName = "implicitValueFlag" )
@Label("Default building type")
@OrderNo("3")
@VisibleList(true)
@Widget("CK")
@VisibleDetail(true)
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


	/* Vduped Attribute setters */

	/* Relationship getters & setters */

    @OneToMany( mappedBy = "buildingType", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "partnerAddress" )
	/**
	  *  Access method for the partnerAddresses property.
	  *  @return the current value of the partnerAddresses property
	  */
	public Collection<ro.siveco.svapnt.common.entity.PartnerAddress> getPartnerAddresses()
	{
		return partnerAddresses;
	}

	/**
	  *  Set method for the partnerAddresses property.
	  */
	public void setPartnerAddresses( Collection<ro.siveco.svapnt.common.entity.PartnerAddress> _partnerAddresses )
	{
		partnerAddresses = _partnerAddresses;
	}


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                            
    public static final String NQ_findByCode = "common_BuildingType_findByCode";
    public static BuildingType findByCode( java.lang.String code ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( code);
        return ( BuildingType ) getGenericSession().getSingleResult(NQ_findByCode, params);
    }

	//Unique keys methods with Relations as parameters
	//model defined finders


    public static BuildingType find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( BuildingType.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
