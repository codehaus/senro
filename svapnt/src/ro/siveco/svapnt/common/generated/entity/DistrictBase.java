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
import ro.siveco.svapnt.common.entity.District;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Districte/Judete<br>
 * </p>
 * <p>
 * Vezi documentul dictionar de date<br>
 * </p>
 * <p>
 * Catalogul de judete
 * </p>
 *
 * tableName DISTRICTS
 * constraintNamePK PK_DISTRICTS
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.common.session.DistrictMgrLocal.JNDI_NAME )
public abstract class DistrictBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "common_District";
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

	/* Vduped Attributes */

	/* Relationships */

    @OneToMany( mappedBy = "district", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.ZoneDetail>     zoneDetails = new ArrayList();

    @ManyToOne( optional =  false )
    @JoinColumn( name = "COUNTRY_ID" )
// country
	private ro.siveco.svapnt.common.entity.Country			    country;

    @OneToMany( mappedBy = "district", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.City>     cities = new ArrayList();

    @OneToMany( mappedBy = "district", cascade = {PERSIST, MERGE, REFRESH} )
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
@VisibleDetail(true)
@UniqueKey("UK_DISTRICTS")
@VisibleReferred(true)
@OrderNo("1")
@Length("20")
@VisibleList(true)
@Required(true)
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
@VisibleDetail(true)
@Length("2000")
@Widget("TA")
@OrderNo("4")
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
@VisibleDetail(true)
@OrderNo("2")
@Length("100")
@VisibleList(true)
@Required(true)
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


	/* Vduped Attribute setters */

	/* Relationship getters & setters */

    @OneToMany( mappedBy = "district", cascade = {PERSIST, MERGE, REFRESH} )
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


    @OneToMany( mappedBy = "district", cascade = {PERSIST, MERGE, REFRESH} )
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


    @OneToMany( mappedBy = "district", cascade = {PERSIST, MERGE, REFRESH} )
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
                                                                                                                                                         
    public static final String NQ_findByCode = "common_District_findByCode";
    public static District findByCode( java.lang.String code ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( code);
        return ( District ) getGenericSession().getSingleResult(NQ_findByCode, params);
    }

	//Unique keys methods with Relations as parameters
	//model defined finders


    public static District find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( District.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
