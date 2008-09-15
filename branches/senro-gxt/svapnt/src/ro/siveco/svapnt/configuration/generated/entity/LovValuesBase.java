/*
 * Warning: Generated code! Do not modify!
 * Atentiune: Cod sursa generat! Nu modifica!
 * Source template: EntityBaseJava.vsl
 * Source template version: $Rel$
 */
package ro.siveco.svapnt.configuration.generated.entity;

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
import ro.siveco.svapnt.configuration.entity.LovValues;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Valori liste de valori<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.configuration.session.LovValuesMgrLocal.JNDI_NAME )
public abstract class LovValuesBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "configuration_LovValues";
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

    @Column( name = "VAL" )
	private java.lang.String			    val;

	/* Vduped Attributes */

	/* Relationships */

    @ManyToOne( optional =  false )
    @JoinColumn( name = "LOV_ID" )
// lov
	private ro.siveco.svapnt.configuration.entity.Lov			    lov;

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

    @Column( name = "VAL" )
    @PersistentFieldGetter( fieldName = "val" )
@VisibleList(true)
@VisibleDetail(true)
@Required(true)
@Length("255")
@VisibleLength("50")
@UniqueKey("UK_COM_LOVV")
@VisibleInCombo(true)
	/**
	  *  Access method for the val property.
	  *  @return the current value of the val property
	  */
	public java.lang.String getVal()
	{
		return val;
	}

	/**
	  *  Set method for the val property.
	  */
	public void setVal( java.lang.String _val )
	{
		val = _val;
	}


	/* Vduped Attribute setters */

	/* Relationship getters & setters */
    @PersistentToOneGetter( relName = "lov" )

    @ManyToOne( optional =  false )
    @JoinColumn( name = "LOV_ID" )
// lov
    @UniqueKey
	/**
	  *  Access method for the lov property.
	  *  @return the current value of the lov property
	  */
	public ro.siveco.svapnt.configuration.entity.Lov getLov()
	{
		return lov;
	}

	/**
	  *  Set method for the lov property.
	  */
	public void setLov( ro.siveco.svapnt.configuration.entity.Lov _lov )
	{
		lov = _lov;
	}


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                                                                                                                                                
    public static final String NQ_findByValAndLov = "configuration_LovValues_findByValAndLov";
    public static LovValues findByValAndLov( java.lang.String val, ro.siveco.svapnt.configuration.entity.Lov lov ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( val);
                params.add(lov.getId());
        return ( LovValues ) getGenericSession().getSingleResult(NQ_findByValAndLov, params);
    }

	//Unique keys methods with Relations as parameters
	//model defined finders


    public static LovValues find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( LovValues.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
