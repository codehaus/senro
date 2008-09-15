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
import ro.siveco.svapnt.common.entity.Zone;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Zone (geografice, comerciale etc)<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date<br>
 * </p>
 *
 * constraintNamePK PK_ZONES
 * tableName ZONES
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.common.session.ZoneMgrLocal.JNDI_NAME )
public abstract class ZoneBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "common_Zone";
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

    @Column( name = "RESPONSIBLE" )
	private java.lang.String			    responsible;

	/* Vduped Attributes */

	/* Relationships */

    @OneToMany( mappedBy = "zone", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.ZoneDetail>     zoneDetails = new ArrayList();

    @OneToMany( mappedBy = "zone", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.PartnerZone>     partnerZones = new ArrayList();

    @ManyToOne( optional =  false )
    @JoinColumn( name = "ZONE_TYPE_ID" )
// zoneType
	private ro.siveco.svapnt.common.entity.ZoneType			    zoneType;

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
@Length("20")
@VisibleReferred(true)
@UniqueKey("UK_ZONES")
@Required(true)
@VisibleList(true)
@VisibleDetail(true)
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
@OrderNo("2")
@Required(true)
@VisibleDetail(true)
@VisibleList(true)
@VisibleReferred(true)
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

    @Column( name = "RESPONSIBLE" )
    @PersistentFieldGetter( fieldName = "responsible" )
@VisibleDetail(true)
@VisibleList(true)
@OrderNo("4")
@Length("100")
	/**
	  *  Access method for the responsible property.
	  *  @return the current value of the responsible property
	  */
	public java.lang.String getResponsible()
	{
		return responsible;
	}

	/**
	  *  Set method for the responsible property.
	  */
	public void setResponsible( java.lang.String _responsible )
	{
		responsible = _responsible;
	}


	/* Vduped Attribute setters */

	/* Relationship getters & setters */

    @OneToMany( mappedBy = "zone", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "zoneDetail" )
	/**
	  *  Access method for the zoneDetails property.
	  *  @return the current value of the zoneDetails property
	  */
	public Collection<ro.siveco.svapnt.common.entity.ZoneDetail> getZoneDetails()
	{
		return zoneDetails;
	}

	/**
	  *  Set method for the zoneDetails property.
	  */
	public void setZoneDetails( Collection<ro.siveco.svapnt.common.entity.ZoneDetail> _zoneDetails )
	{
		zoneDetails = _zoneDetails;
	}


    @OneToMany( mappedBy = "zone", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "partnerZone" )
	/**
	  *  Access method for the partnerZones property.
	  *  @return the current value of the partnerZones property
	  */
	public Collection<ro.siveco.svapnt.common.entity.PartnerZone> getPartnerZones()
	{
		return partnerZones;
	}

	/**
	  *  Set method for the partnerZones property.
	  */
	public void setPartnerZones( Collection<ro.siveco.svapnt.common.entity.PartnerZone> _partnerZones )
	{
		partnerZones = _partnerZones;
	}

    @PersistentToOneGetter( relName = "zoneType" )

    @ManyToOne( optional =  false )
    @JoinColumn( name = "ZONE_TYPE_ID" )
// zoneType
	/**
	  *  Access method for the zoneType property.
	  *  @return the current value of the zoneType property
	  */
	public ro.siveco.svapnt.common.entity.ZoneType getZoneType()
	{
		return zoneType;
	}

	/**
	  *  Set method for the zoneType property.
	  */
	public void setZoneType( ro.siveco.svapnt.common.entity.ZoneType _zoneType )
	{
		zoneType = _zoneType;
	}


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                                         
    public static final String NQ_findByCode = "common_Zone_findByCode";
    public static Zone findByCode( java.lang.String code ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( code);
        return ( Zone ) getGenericSession().getSingleResult(NQ_findByCode, params);
    }

	//Unique keys methods with Relations as parameters
	//model defined finders


    public static Zone find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( Zone.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
