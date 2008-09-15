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
import ro.siveco.svapnt.common.entity.City;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Localitati<br>
 * </p>
 * <p>
 * Vezi documentul dictionar de date<br>
 * </p>
 * <p>
 * Catalogul de orase
 * </p>
 *
 * constraintNamePK PK_CITIES
 * tableName CITIES
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.common.session.CityMgrLocal.JNDI_NAME )
public abstract class CityBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "common_City";
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

    @Column( name = "COMMENTS" )
	private java.lang.String			    comments;

    @Column( name = "NAME" )
	private java.lang.String			    name;

    @Column( name = "PHONE_AREA_CODE" )
	private java.lang.String			    phoneAreaCode;

    @Column( name = "ZIP_CODE" )
	private java.lang.String			    zipCode;

	/* Vduped Attributes */

	/* Relationships */

    @OneToMany( mappedBy = "city", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.Partner>     partners = new ArrayList();

    @OneToMany( mappedBy = "city", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.Street>     streets = new ArrayList();

    @OneToMany( mappedBy = "city", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.ZoneDetail>     zoneDetails = new ArrayList();

    @OneToMany( mappedBy = "city", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.PartnerAddress>     partnerAddresses = new ArrayList();

    @ManyToOne( optional =  false )
    @JoinColumn( name = "CITY_TYPE_ID" )
// cityType
	private ro.siveco.svapnt.common.entity.CityType			    cityType;

    @ManyToOne( optional =  false )
    @JoinColumn( name = "DISTRICT_ID" )
// district
	private ro.siveco.svapnt.common.entity.District			    district;

    @ManyToOne( optional =  true )
    @JoinColumn( name = "CITY_ID" )
// city
	private ro.siveco.svapnt.common.entity.City			    city;

    @OneToMany( mappedBy = "city", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.City>     superiors = new ArrayList();

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
@Required(true)
@VisibleList(true)
@Length("20")
@VisibleDetail(true)
@UniqueKey("UK_CITIES")
@OrderNo("10")
@VisibleReferred(true)
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

    @Column( name = "COMMENTS" )
    @PersistentFieldGetter( fieldName = "comments" )
@Widget("TA")
@Length("2000")
@OrderNo("80")
@VisibleDetail(true)
@Unfiltered(true)
	/**
	  *  Access method for the comments property.
	  *  @return the current value of the comments property
	  */
	public java.lang.String getComments()
	{
		return comments;
	}

	/**
	  *  Set method for the comments property.
	  */
	public void setComments( java.lang.String _comments )
	{
		comments = _comments;
	}

    @Column( name = "NAME" )
    @PersistentFieldGetter( fieldName = "name" )
@Length("100")
@VisibleList(true)
@VisibleDetail(true)
@OrderNo("20")
@Required(true)
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

    @Column( name = "PHONE_AREA_CODE" )
    @PersistentFieldGetter( fieldName = "phoneAreaCode" )
@Length("20")
@VisibleDetail(true)
@OrderNo("60")
	/**
	  *  Access method for the phoneAreaCode property.
	  *  @return the current value of the phoneAreaCode property
	  */
	public java.lang.String getPhoneAreaCode()
	{
		return phoneAreaCode;
	}

	/**
	  *  Set method for the phoneAreaCode property.
	  */
	public void setPhoneAreaCode( java.lang.String _phoneAreaCode )
	{
		phoneAreaCode = _phoneAreaCode;
	}

    @Column( name = "ZIP_CODE" )
    @PersistentFieldGetter( fieldName = "zipCode" )
@VisibleList(true)
@OrderNo("70")
@Length("30")
@VisibleDetail(true)
	/**
	  *  Access method for the zipCode property.
	  *  @return the current value of the zipCode property
	  */
	public java.lang.String getZipCode()
	{
		return zipCode;
	}

	/**
	  *  Set method for the zipCode property.
	  */
	public void setZipCode( java.lang.String _zipCode )
	{
		zipCode = _zipCode;
	}


	/* Vduped Attribute setters */

	/* Relationship getters & setters */

    @OneToMany( mappedBy = "city", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "partner" )
	/**
	  *  Access method for the partners property.
	  *  @return the current value of the partners property
	  */
	public Collection<ro.siveco.svapnt.common.entity.Partner> getPartners()
	{
		return partners;
	}

	/**
	  *  Set method for the partners property.
	  */
	public void setPartners( Collection<ro.siveco.svapnt.common.entity.Partner> _partners )
	{
		partners = _partners;
	}


    @OneToMany( mappedBy = "city", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "street" )
	/**
	  *  Access method for the streets property.
	  *  @return the current value of the streets property
	  */
	public Collection<ro.siveco.svapnt.common.entity.Street> getStreets()
	{
		return streets;
	}

	/**
	  *  Set method for the streets property.
	  */
	public void setStreets( Collection<ro.siveco.svapnt.common.entity.Street> _streets )
	{
		streets = _streets;
	}


    @OneToMany( mappedBy = "city", cascade = {PERSIST, MERGE, REFRESH} )
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


    @OneToMany( mappedBy = "city", cascade = {PERSIST, MERGE, REFRESH} )
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

    @PersistentToOneGetter( relName = "cityType" )

    @ManyToOne( optional =  false )
    @JoinColumn( name = "CITY_TYPE_ID" )
// cityType
	/**
	  *  Access method for the cityType property.
	  *  @return the current value of the cityType property
	  */
	public ro.siveco.svapnt.common.entity.CityType getCityType()
	{
		return cityType;
	}

	/**
	  *  Set method for the cityType property.
	  */
	public void setCityType( ro.siveco.svapnt.common.entity.CityType _cityType )
	{
		cityType = _cityType;
	}

    @PersistentToOneGetter( relName = "district" )

    @ManyToOne( optional =  false )
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


    @OneToMany( mappedBy = "city", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "superior" )
	/**
	  *  Access method for the superiors property.
	  *  @return the current value of the superiors property
	  */
	public Collection<ro.siveco.svapnt.common.entity.City> getSuperiors()
	{
		return superiors;
	}

	/**
	  *  Set method for the superiors property.
	  */
	public void setSuperiors( Collection<ro.siveco.svapnt.common.entity.City> _superiors )
	{
		superiors = _superiors;
	}


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                                                                                 
    public static final String NQ_findByCode = "common_City_findByCode";
    public static City findByCode( java.lang.String code ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( code);
        return ( City ) getGenericSession().getSingleResult(NQ_findByCode, params);
    }

	//Unique keys methods with Relations as parameters
	//model defined finders


    public static City find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( City.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
