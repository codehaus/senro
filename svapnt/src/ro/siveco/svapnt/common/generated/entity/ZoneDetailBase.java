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
import ro.siveco.svapnt.common.entity.ZoneDetail;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Detalii zone (geografice, comerciale etc)<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date<br>
 * </p>
 *
 * constraintNamePK PK_ZONE_DETAILS
 * tableName ZONE_DETAILS
 *
 */

 

 
@MappedSuperclass
public abstract class ZoneDetailBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "common_ZoneDetail";
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

    @Column( name = "ORDER_NO" )
	private java.lang.Long			    orderNo;

    @Column( name = "TYPE" )
	private java.lang.Short			    type;

	/* Vduped Attributes */

	/* Relationships */

    @ManyToOne( optional =  false )
    @JoinColumn( name = "ZONE_ID" )
// zone
	private ro.siveco.svapnt.common.entity.Zone			    zone;

    @ManyToOne( optional =  false )
    @JoinColumn( name = "COUNTRY_ID" )
// country
	private ro.siveco.svapnt.common.entity.Country			    country;

    @ManyToOne( optional =  true )
    @JoinColumn( name = "DISTRICT_ID" )
// district
	private ro.siveco.svapnt.common.entity.District			    district;

    @ManyToOne( optional =  true )
    @JoinColumn( name = "CITY_ID" )
// city
	private ro.siveco.svapnt.common.entity.City			    city;

    @ManyToOne( optional =  true )
    @JoinColumn( name = "STREET_ID" )
// street
	private ro.siveco.svapnt.common.entity.Street			    street;

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

    @Column( name = "ORDER_NO" )
    @PersistentFieldGetter( fieldName = "orderNo" )
@UniqueKey("UK_ZONE_DETAILS")
@Required(true)
@OrderNo("1")
@VisibleList(true)
@VisibleDetail(true)
	/**
	  *  Access method for the orderNo property.
	  *  @return the current value of the orderNo property
	  */
	public java.lang.Long getOrderNo()
	{
		return orderNo;
	}

	/**
	  *  Set method for the orderNo property.
	  */
	public void setOrderNo( java.lang.Long _orderNo )
	{
		orderNo = _orderNo;
	}

    @Column( name = "TYPE" )
    @PersistentFieldGetter( fieldName = "type" )
@VisibleList(true)
@Widget("CB")
@WidgetValues("1|Country;2|District;3|City;4|Street")
@VisibleDetail(true)
@OrderNo("2")
@Required(true)
	/**
	  *  Access method for the type property.
	  *  @return the current value of the type property
	  */
	public java.lang.Short getType()
	{
		return type;
	}

	/**
	  *  Set method for the type property.
	  */
	public void setType( java.lang.Short _type )
	{
		type = _type;
	}


	/* Vduped Attribute setters */

	/* Relationship getters & setters */
    @PersistentToOneGetter( relName = "zone" )

    @ManyToOne( optional =  false )
    @JoinColumn( name = "ZONE_ID" )
// zone
    @UniqueKey
	/**
	  *  Access method for the zone property.
	  *  @return the current value of the zone property
	  */
	public ro.siveco.svapnt.common.entity.Zone getZone()
	{
		return zone;
	}

	/**
	  *  Set method for the zone property.
	  */
	public void setZone( ro.siveco.svapnt.common.entity.Zone _zone )
	{
		zone = _zone;
	}

    @PersistentToOneGetter( relName = "country" )

    @ManyToOne( optional =  false )
    @JoinColumn( name = "COUNTRY_ID" )
// country
	/**
	  *  Access method for the country property.
	  *  @return the current value of the country property
	  */
	public ro.siveco.svapnt.common.entity.Country getCountry()
	{
		return country;
	}

	/**
	  *  Set method for the country property.
	  */
	public void setCountry( ro.siveco.svapnt.common.entity.Country _country )
	{
		country = _country;
	}

    @PersistentToOneGetter( relName = "district" )

    @ManyToOne( optional =  true )
    @JoinColumn( name = "DISTRICT_ID" )
// district
	/**
	  *  Access method for the district property.
	  *  @return the current value of the district property
	  */
	public ro.siveco.svapnt.common.entity.District getDistrict()
	{
		return district;
	}

	/**
	  *  Set method for the district property.
	  */
	public void setDistrict( ro.siveco.svapnt.common.entity.District _district )
	{
		district = _district;
	}

    @PersistentToOneGetter( relName = "city" )

    @ManyToOne( optional =  true )
    @JoinColumn( name = "CITY_ID" )
// city
	/**
	  *  Access method for the city property.
	  *  @return the current value of the city property
	  */
	public ro.siveco.svapnt.common.entity.City getCity()
	{
		return city;
	}

	/**
	  *  Set method for the city property.
	  */
	public void setCity( ro.siveco.svapnt.common.entity.City _city )
	{
		city = _city;
	}

    @PersistentToOneGetter( relName = "street" )

    @ManyToOne( optional =  true )
    @JoinColumn( name = "STREET_ID" )
// street
	/**
	  *  Access method for the street property.
	  *  @return the current value of the street property
	  */
	public ro.siveco.svapnt.common.entity.Street getStreet()
	{
		return street;
	}

	/**
	  *  Set method for the street property.
	  */
	public void setStreet( ro.siveco.svapnt.common.entity.Street _street )
	{
		street = _street;
	}


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                                                                                                                                                                                                           
    public static final String NQ_findByOrderNoAndZone = "common_ZoneDetail_findByOrderNoAndZone";
    public static ZoneDetail findByOrderNoAndZone( java.lang.Long orderNo, ro.siveco.svapnt.common.entity.Zone zone ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( orderNo);
                params.add(zone.getId());
        return ( ZoneDetail ) getGenericSession().getSingleResult(NQ_findByOrderNoAndZone, params);
    }

	//Unique keys methods with Relations as parameters
	//model defined finders


    public static ZoneDetail find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( ZoneDetail.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
