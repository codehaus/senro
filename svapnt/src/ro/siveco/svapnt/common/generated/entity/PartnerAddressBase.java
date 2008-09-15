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
import ro.siveco.svapnt.common.entity.PartnerAddress;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Adrese firme, persoane fizice: Entitate de intersectie intre
 * companii si adrese
 * </p>
 * <p>
 * <br>Vezi documentul Dictionar Date<br>
 * </p>
 * <p>
 * Adrese persoane fizice sau juridice<br>
 * </p>
 * <p>
 * Adrese persoane juridice
 * </p>
 *
 * tableName PARTNER_ADDRESS
 * constraintNamePK PK_PRTNR_ADDRSS
 * validFrom validFrom
 * validTo validTo
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.common.session.PartnerAddressMgrLocal.JNDI_NAME )
public abstract class PartnerAddressBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "common_PartnerAddress";
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

    @Column( name = "POSTAL_CODE" )
	private java.lang.String			    postalCode;

    @Column( name = "TEXT" )
	private java.lang.String			    text;

    @Column( name = "NO" )
	private java.lang.String			    no;

    @Column( name = "BLOCK" )
	private java.lang.String			    block;

    @Column( name = "FLOOR" )
	private java.lang.String			    floor;

    @Column( name = "STAIR" )
	private java.lang.String			    stair;

    @Column( name = "APT_NO" )
	private java.lang.String			    aptNo;

    @Column( name = "VALID_FROM" )
	private java.util.Date			    validFrom;

    @Column( name = "VALID_TO" )
	private java.util.Date			    validTo;

    @Column( name = "NEIGHBOURHOOD" )
	private java.lang.String			    neighbourhood;

	/* Vduped Attributes */

	/* Relationships */

    @ManyToOne( optional =  true )
    @JoinColumn( name = "ENTRANCE_TYPE_ID" )
// entranceType
	private ro.siveco.svapnt.common.entity.EntranceType			    entranceType;

    @ManyToOne( optional =  true )
    @JoinColumn( name = "BUILDING_TYPE_ID" )
// buildingType
	private ro.siveco.svapnt.common.entity.BuildingType			    buildingType;

    @ManyToOne( optional =  true )
    @JoinColumn( name = "STREET_ID" )
// street
	private ro.siveco.svapnt.common.entity.Street			    street;

    @ManyToOne( optional =  true )
    @JoinColumn( name = "PREMISES_TYPE_ID" )
// premisesType
	private ro.siveco.svapnt.common.entity.PremisesType			    premisesType;

    @ManyToOne( optional =  true )
    @JoinColumn( name = "COUNTRY_ID" )
// country
	private ro.siveco.svapnt.common.entity.Country			    country;

    @ManyToOne( optional =  false )
    @JoinColumn( name = "ADDRESS_TYPE_ID" )
// addressType
	private ro.siveco.svapnt.common.entity.AddressType			    addressType;

    @ManyToOne( optional =  false )
    @JoinColumn( name = "PARTNER_ID" )
// partner
	private ro.siveco.svapnt.common.entity.Partner			    partner;

    @ManyToOne( optional =  true )
    @JoinColumn( name = "CITY_ID" )
// city
	private ro.siveco.svapnt.common.entity.City			    city;

    @ManyToOne( optional =  true )
    @JoinColumn( name = "DISTRICT_ID" )
// district
	private ro.siveco.svapnt.common.entity.District			    district;

    @ManyToOne( optional =  true )
    @JoinColumn( name = "FLOOR_TYPE_ID" )
// floorType
	private ro.siveco.svapnt.common.entity.FloorType			    floorType;

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

    @Column( name = "POSTAL_CODE" )
    @PersistentFieldGetter( fieldName = "postalCode" )
@OrderNo("60")
@VisibleDetail(true)
@VisibleList(true)
@Length("39")
@VisibleReferred(true)
	/**
	  *  Access method for the postalCode property.
	  *  @return the current value of the postalCode property
	  */
	public java.lang.String getPostalCode()
	{
		return postalCode;
	}

	/**
	  *  Set method for the postalCode property.
	  */
	public void setPostalCode( java.lang.String _postalCode )
	{
		postalCode = _postalCode;
	}

    @Column( name = "TEXT" )
    @PersistentFieldGetter( fieldName = "text" )
@Length("1500")
@VisibleDetail(true)
@OrderNo("160")
@VisibleList(true)
@Widget("TA")
@DatabaseRequired(true)
@ReadOnly(true)
	/**
	  *  Access method for the text property.
	  *  @return the current value of the text property
	  */
	public java.lang.String getText()
	{
		return text;
	}

	/**
	  *  Set method for the text property.
	  */
	public void setText( java.lang.String _text )
	{
		text = _text;
	}

    @Column( name = "NO" )
    @PersistentFieldGetter( fieldName = "no" )
@OrderNo("75")
@VisibleReferred(true)
@Length("20")
@VisibleDetail(true)
	/**
	  *  Access method for the no property.
	  *  @return the current value of the no property
	  */
	public java.lang.String getNo()
	{
		return no;
	}

	/**
	  *  Set method for the no property.
	  */
	public void setNo( java.lang.String _no )
	{
		no = _no;
	}

    @Column( name = "BLOCK" )
    @PersistentFieldGetter( fieldName = "block" )
@Length("20")
@OrderNo("90")
@VisibleDetail(true)
@VisibleReferred(true)
	/**
	  *  Access method for the block property.
	  *  @return the current value of the block property
	  */
	public java.lang.String getBlock()
	{
		return block;
	}

	/**
	  *  Set method for the block property.
	  */
	public void setBlock( java.lang.String _block )
	{
		block = _block;
	}

    @Column( name = "FLOOR" )
    @PersistentFieldGetter( fieldName = "floor" )
@Length("20")
@OrderNo("130")
@VisibleReferred(true)
@VisibleDetail(true)
	/**
	  *  Access method for the floor property.
	  *  @return the current value of the floor property
	  */
	public java.lang.String getFloor()
	{
		return floor;
	}

	/**
	  *  Set method for the floor property.
	  */
	public void setFloor( java.lang.String _floor )
	{
		floor = _floor;
	}

    @Column( name = "STAIR" )
    @PersistentFieldGetter( fieldName = "stair" )
@Length("20")
@VisibleDetail(true)
@OrderNo("110")
@VisibleReferred(true)
	/**
	  *  Access method for the stair property.
	  *  @return the current value of the stair property
	  */
	public java.lang.String getStair()
	{
		return stair;
	}

	/**
	  *  Set method for the stair property.
	  */
	public void setStair( java.lang.String _stair )
	{
		stair = _stair;
	}

    @Column( name = "APT_NO" )
    @PersistentFieldGetter( fieldName = "aptNo" )
@Length("20")
@VisibleReferred(true)
@VisibleDetail(true)
@OrderNo("150")
	/**
	  *  Access method for the aptNo property.
	  *  @return the current value of the aptNo property
	  */
	public java.lang.String getAptNo()
	{
		return aptNo;
	}

	/**
	  *  Set method for the aptNo property.
	  */
	public void setAptNo( java.lang.String _aptNo )
	{
		aptNo = _aptNo;
	}

    @Column( name = "VALID_FROM" )
    @PersistentFieldGetter( fieldName = "validFrom" )
@Required(true)
@VisibleDetail(true)
@OrderNo("170")
@VisibleList(true)
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
@VisibleDetail(true)
@VisibleList(true)
@OrderNo("180")
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

    @Column( name = "NEIGHBOURHOOD" )
    @PersistentFieldGetter( fieldName = "neighbourhood" )
@VisibleDetail(true)
@Length("150")
@VisibleList(true)
@OrderNo("50")
@VisibleLength("50")
	/**
	  *  Access method for the neighbourhood property.
	  *  @return the current value of the neighbourhood property
	  */
	public java.lang.String getNeighbourhood()
	{
		return neighbourhood;
	}

	/**
	  *  Set method for the neighbourhood property.
	  */
	public void setNeighbourhood( java.lang.String _neighbourhood )
	{
		neighbourhood = _neighbourhood;
	}


	/* Vduped Attribute setters */

	/* Relationship getters & setters */
    @PersistentToOneGetter( relName = "entranceType" )

    @ManyToOne( optional =  true )
    @JoinColumn( name = "ENTRANCE_TYPE_ID" )
// entranceType
	/**
	  *  Access method for the entranceType property.
	  *  @return the current value of the entranceType property
	  */
	public ro.siveco.svapnt.common.entity.EntranceType getEntranceType()
	{
		return entranceType;
	}

	/**
	  *  Set method for the entranceType property.
	  */
	public void setEntranceType( ro.siveco.svapnt.common.entity.EntranceType _entranceType )
	{
		entranceType = _entranceType;
	}

    @PersistentToOneGetter( relName = "buildingType" )

    @ManyToOne( optional =  true )
    @JoinColumn( name = "BUILDING_TYPE_ID" )
// buildingType
	/**
	  *  Access method for the buildingType property.
	  *  @return the current value of the buildingType property
	  */
	public ro.siveco.svapnt.common.entity.BuildingType getBuildingType()
	{
		return buildingType;
	}

	/**
	  *  Set method for the buildingType property.
	  */
	public void setBuildingType( ro.siveco.svapnt.common.entity.BuildingType _buildingType )
	{
		buildingType = _buildingType;
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

    @PersistentToOneGetter( relName = "premisesType" )

    @ManyToOne( optional =  true )
    @JoinColumn( name = "PREMISES_TYPE_ID" )
// premisesType
	/**
	  *  Access method for the premisesType property.
	  *  @return the current value of the premisesType property
	  */
	public ro.siveco.svapnt.common.entity.PremisesType getPremisesType()
	{
		return premisesType;
	}

	/**
	  *  Set method for the premisesType property.
	  */
	public void setPremisesType( ro.siveco.svapnt.common.entity.PremisesType _premisesType )
	{
		premisesType = _premisesType;
	}

    @PersistentToOneGetter( relName = "country" )

    @ManyToOne( optional =  true )
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

    @PersistentToOneGetter( relName = "addressType" )

    @ManyToOne( optional =  false )
    @JoinColumn( name = "ADDRESS_TYPE_ID" )
// addressType
	/**
	  *  Access method for the addressType property.
	  *  @return the current value of the addressType property
	  */
	public ro.siveco.svapnt.common.entity.AddressType getAddressType()
	{
		return addressType;
	}

	/**
	  *  Set method for the addressType property.
	  */
	public void setAddressType( ro.siveco.svapnt.common.entity.AddressType _addressType )
	{
		addressType = _addressType;
	}

    @PersistentToOneGetter( relName = "partner" )

    @ManyToOne( optional =  false )
    @JoinColumn( name = "PARTNER_ID" )
// partner
	/**
	  *  Access method for the partner property.
	  *  @return the current value of the partner property
	  */
	public ro.siveco.svapnt.common.entity.Partner getPartner()
	{
		return partner;
	}

	/**
	  *  Set method for the partner property.
	  */
	public void setPartner( ro.siveco.svapnt.common.entity.Partner _partner )
	{
		partner = _partner;
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

    @PersistentToOneGetter( relName = "floorType" )

    @ManyToOne( optional =  true )
    @JoinColumn( name = "FLOOR_TYPE_ID" )
// floorType
	/**
	  *  Access method for the floorType property.
	  *  @return the current value of the floorType property
	  */
	public ro.siveco.svapnt.common.entity.FloorType getFloorType()
	{
		return floorType;
	}

	/**
	  *  Set method for the floorType property.
	  */
	public void setFloorType( ro.siveco.svapnt.common.entity.FloorType _floorType )
	{
		floorType = _floorType;
	}


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                                                                                                            
	//Unique keys methods with Relations as parameters
	//model defined finders


    public static PartnerAddress find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( PartnerAddress.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
