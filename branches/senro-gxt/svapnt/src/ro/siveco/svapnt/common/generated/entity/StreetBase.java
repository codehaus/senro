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
import ro.siveco.svapnt.common.entity.Street;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Strazi/artere<br>
 * </p>
 * <p>
 * Vezi documentul dictionar de date<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date<br>
 * </p>
 *
 * constraintNamePK PK_STREETS
 * tableName STREETS
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.common.session.CityStreetsMgrLocal.JNDI_NAME )
public abstract class StreetBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "common_Street";
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

    @Column( name = "SUBURB" )
	private java.lang.String			    suburb;

    @Column( name = "SECTOR" )
	private java.lang.String			    sector;

    @Column( name = "START_OF_UNEVEN_NUMBERS_SECTIN" )
	private java.lang.String			    startOfUnevenNumbersSection;

    @Column( name = "START_OF_EVEN_NUMBERS_SECTION" )
	private java.lang.String			    startOfEvenNumbersSection;

    @Column( name = "END_OF_UNEVEN_NUMBERS_SECTION" )
	private java.lang.String			    endOfUnevenNumbersSection;

    @Column( name = "END_OF_EVEN_NUMBERS_SECTION" )
	private java.lang.String			    endOfEvenNumbersSection;

	/* Vduped Attributes */

	/* Relationships */

    @ManyToOne( optional =  false )
    @JoinColumn( name = "STREET_TYPE_ID" )
// streetType
	private ro.siveco.svapnt.common.entity.StreetType			    streetType;

    @OneToMany( mappedBy = "street", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.PartnerAddress>     partnerAddresses = new ArrayList();

    @ManyToOne( optional =  false )
    @JoinColumn( name = "CITY_ID" )
// city
	private ro.siveco.svapnt.common.entity.City			    city;

    @OneToMany( mappedBy = "street", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.ZoneDetail>     zoneDetails = new ArrayList();

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
@Length("40")
@Required(true)
@VisibleList(true)
@OrderNo("1")
@VisibleReferred(true)
@VisibleDetail(true)
@UniqueKey("UK_STREETS")
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
@Length("100")
@VisibleDetail(true)
@Required(true)
@VisibleList(true)
@OrderNo("2")
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

    @Column( name = "SUBURB" )
    @PersistentFieldGetter( fieldName = "suburb" )
@VisibleList(true)
@Length("100")
@OrderNo("5")
@VisibleDetail(true)
	/**
	  *  Access method for the suburb property.
	  *  @return the current value of the suburb property
	  */
	public java.lang.String getSuburb()
	{
		return suburb;
	}

	/**
	  *  Set method for the suburb property.
	  */
	public void setSuburb( java.lang.String _suburb )
	{
		suburb = _suburb;
	}

    @Column( name = "SECTOR" )
    @PersistentFieldGetter( fieldName = "sector" )
@VisibleList(true)
@Length("100")
@VisibleDetail(true)
@OrderNo("6")
	/**
	  *  Access method for the sector property.
	  *  @return the current value of the sector property
	  */
	public java.lang.String getSector()
	{
		return sector;
	}

	/**
	  *  Set method for the sector property.
	  */
	public void setSector( java.lang.String _sector )
	{
		sector = _sector;
	}

    @Column( name = "START_OF_UNEVEN_NUMBERS_SECTIN" )
    @PersistentFieldGetter( fieldName = "startOfUnevenNumbersSection" )
@OrderNo("7")
@VisibleDetail(true)
@Length("100")
	/**
	  *  Access method for the startOfUnevenNumbersSection property.
	  *  @return the current value of the startOfUnevenNumbersSection property
	  */
	public java.lang.String getStartOfUnevenNumbersSection()
	{
		return startOfUnevenNumbersSection;
	}

	/**
	  *  Set method for the startOfUnevenNumbersSection property.
	  */
	public void setStartOfUnevenNumbersSection( java.lang.String _startOfUnevenNumbersSection )
	{
		startOfUnevenNumbersSection = _startOfUnevenNumbersSection;
	}

    @Column( name = "START_OF_EVEN_NUMBERS_SECTION" )
    @PersistentFieldGetter( fieldName = "startOfEvenNumbersSection" )
@OrderNo("9")
@Length("100")
@VisibleDetail(true)
	/**
	  *  Access method for the startOfEvenNumbersSection property.
	  *  @return the current value of the startOfEvenNumbersSection property
	  */
	public java.lang.String getStartOfEvenNumbersSection()
	{
		return startOfEvenNumbersSection;
	}

	/**
	  *  Set method for the startOfEvenNumbersSection property.
	  */
	public void setStartOfEvenNumbersSection( java.lang.String _startOfEvenNumbersSection )
	{
		startOfEvenNumbersSection = _startOfEvenNumbersSection;
	}

    @Column( name = "END_OF_UNEVEN_NUMBERS_SECTION" )
    @PersistentFieldGetter( fieldName = "endOfUnevenNumbersSection" )
@VisibleDetail(true)
@OrderNo("8")
@Length("100")
	/**
	  *  Access method for the endOfUnevenNumbersSection property.
	  *  @return the current value of the endOfUnevenNumbersSection property
	  */
	public java.lang.String getEndOfUnevenNumbersSection()
	{
		return endOfUnevenNumbersSection;
	}

	/**
	  *  Set method for the endOfUnevenNumbersSection property.
	  */
	public void setEndOfUnevenNumbersSection( java.lang.String _endOfUnevenNumbersSection )
	{
		endOfUnevenNumbersSection = _endOfUnevenNumbersSection;
	}

    @Column( name = "END_OF_EVEN_NUMBERS_SECTION" )
    @PersistentFieldGetter( fieldName = "endOfEvenNumbersSection" )
@Length("100")
@VisibleDetail(true)
@OrderNo("10")
	/**
	  *  Access method for the endOfEvenNumbersSection property.
	  *  @return the current value of the endOfEvenNumbersSection property
	  */
	public java.lang.String getEndOfEvenNumbersSection()
	{
		return endOfEvenNumbersSection;
	}

	/**
	  *  Set method for the endOfEvenNumbersSection property.
	  */
	public void setEndOfEvenNumbersSection( java.lang.String _endOfEvenNumbersSection )
	{
		endOfEvenNumbersSection = _endOfEvenNumbersSection;
	}


	/* Vduped Attribute setters */

	/* Relationship getters & setters */
    @PersistentToOneGetter( relName = "streetType" )

    @ManyToOne( optional =  false )
    @JoinColumn( name = "STREET_TYPE_ID" )
// streetType
	/**
	  *  Access method for the streetType property.
	  *  @return the current value of the streetType property
	  */
	public ro.siveco.svapnt.common.entity.StreetType getStreetType()
	{
		return streetType;
	}

	/**
	  *  Set method for the streetType property.
	  */
	public void setStreetType( ro.siveco.svapnt.common.entity.StreetType _streetType )
	{
		streetType = _streetType;
	}


    @OneToMany( mappedBy = "street", cascade = {PERSIST, MERGE, REFRESH} )
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

    @PersistentToOneGetter( relName = "city" )

    @ManyToOne( optional =  false )
    @JoinColumn( name = "CITY_ID" )
// city
    @UniqueKey
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


    @OneToMany( mappedBy = "street", cascade = {PERSIST, MERGE, REFRESH} )
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


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                                                                                                                                                                                                              
    public static final String NQ_findByCodeAndCity = "common_Street_findByCodeAndCity";
    public static Street findByCodeAndCity( java.lang.String code, ro.siveco.svapnt.common.entity.City city ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( code);
                params.add(city.getId());
        return ( Street ) getGenericSession().getSingleResult(NQ_findByCodeAndCity, params);
    }

	//Unique keys methods with Relations as parameters
	//model defined finders


    public static Street find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( Street.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
