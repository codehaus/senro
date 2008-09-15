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
import ro.siveco.svapnt.common.entity.Calendar;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Sarbatori legale<br>
 * </p>
 * <p>
 * Vezi documentul dictionar de date<br>
 * </p>
 * <p>
 * Catalogul de zile libere
 * </p>
 *
 * tableName CALENDARS
 * constraintNamePK PK_CALENDARS
 * finders findForTimePeriod=|false||AND|
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.common.session.CalendarMgrLocal.JNDI_NAME )
public abstract class CalendarBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "common_Calendar";
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

    @Column( name = "DAY" )
	private java.util.Date			    day;

    @Column( name = "DESCRIPTION" )
	private java.lang.String			    description;

	/* Vduped Attributes */

	/* Relationships */

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

    @Column( name = "DAY" )
    @PersistentFieldGetter( fieldName = "day" )
@Label("Date")
@VisibleDetail(true)
@UniqueKey("UK_CALENDARS")
@Required(true)
@VisibleReferred(true)
@VisibleList(true)
@OrderNo("1")
	/**
	  *  Access method for the day property.
	  *  @return the current value of the day property
	  */
	public java.util.Date getDay()
	{
		return day;
	}

	/**
	  *  Set method for the day property.
	  */
	public void setDay( java.util.Date _day )
	{
		day = _day;
	}

    @Column( name = "DESCRIPTION" )
    @PersistentFieldGetter( fieldName = "description" )
@VisibleList(true)
@Required(true)
@Length("100")
@VisibleDetail(true)
@OrderNo("2")
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


	/* Vduped Attribute setters */

	/* Relationship getters & setters */

/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                     
    public static final String NQ_findByDay = "common_Calendar_findByDay";
    public static Calendar findByDay( java.util.Date day ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( day);
        return ( Calendar ) getGenericSession().getSingleResult(NQ_findByDay, params);
    }

	//Unique keys methods with Relations as parameters
	//model defined finders

    public static final String NQ_F1findForTimePeriod = "Calendar_F1findForTimePeriod";
       
    public static List<Calendar> findByfindForTimePeriod( java.util.Date p1_Day, java.util.Date p2_Day) throws DAOException
    {

        ArrayList params = new ArrayList();

            params.add( p1_Day);
            params.add( p2_Day);
        return ( List<Calendar> ) getGenericSession().getResultList(NQ_F1findForTimePeriod, params);
    }

    public static Calendar find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( Calendar.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
