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
import ro.siveco.svapnt.configuration.entity.Lov;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Liste de valori<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.configuration.session.LovMgrLocal.JNDI_NAME )
public abstract class LovBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "configuration_Lov";
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

    @Column( name = "NAME" )
	private java.lang.String			    name;

    @Column( name = "DESCRIPTION" )
	private java.lang.String			    description;

    @Column( name = "USAGE" )
	private java.lang.Long			    usage;

	/* Vduped Attributes */

	/* Relationships */

    @OneToMany( mappedBy = "lov", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.configuration.entity.LovValues>     lovValues = new ArrayList();

    @ManyToOne( optional =  false )
    @JoinColumn( name = "DATA_TYPE_ID" )
// dataType
	private ro.siveco.svapnt.configuration.entity.DataType			    dataType;

    @OneToMany( mappedBy = "lov", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.configuration.entity.Parameter>     parameters = new ArrayList();

	/* Attribute getters & setters */
    @Column( name = "ID" )
    @PersistentFieldGetter( fieldName = "id" )
    @VisibleReferred
    @UniqueKey
    @Id
    @GeneratedValue( strategy = SEQUENCE, generator = "SVAPNT_UTILS_SEQ" )
    @SequenceGenerator( name = "SVAPNT_UTILS_SEQ", sequenceName = "SVAPNT_UTILS_SEQ" )
@UniqueKeyDb("UK_CFG_LOVDT")
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

    @Column( name = "NAME" )
    @PersistentFieldGetter( fieldName = "name" )
@VisibleDetail(true)
@UniqueKey("UK_CFG_LOV1")
@Length("20")
@VisibleList(true)
@Required(true)
@VisibleReferred(true)
@VisibleInCombo(true)
@OrderNo("10")
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

    @Column( name = "DESCRIPTION" )
    @PersistentFieldGetter( fieldName = "description" )
@Length("255")
@VisibleDetail(true)
@VisibleList(true)
@OrderNo("20")
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

    @Column( name = "USAGE" )
    @PersistentFieldGetter( fieldName = "usage" )
@ReadOnly(true)
@VisibleList(true)
@VisibleDetail(true)
@OrderNo("30")
	/**
	  *  Access method for the usage property.
	  *  @return the current value of the usage property
	  */
	public java.lang.Long getUsage()
	{
		return usage;
	}

	/**
	  *  Set method for the usage property.
	  */
	public void setUsage( java.lang.Long _usage )
	{
		usage = _usage;
	}


	/* Vduped Attribute setters */

	/* Relationship getters & setters */

    @OneToMany( mappedBy = "lov", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "lovValues" )
	/**
	  *  Access method for the lovValues property.
	  *  @return the current value of the lovValues property
	  */
	public Collection<ro.siveco.svapnt.configuration.entity.LovValues> getLovValues()
	{
		return lovValues;
	}

	/**
	  *  Set method for the lovValues property.
	  */
	public void setLovValues( Collection<ro.siveco.svapnt.configuration.entity.LovValues> _lovValues )
	{
		lovValues = _lovValues;
	}

    @PersistentToOneGetter( relName = "dataType" )

    @ManyToOne( optional =  false )
    @JoinColumn( name = "DATA_TYPE_ID" )
// dataType
	/**
	  *  Access method for the dataType property.
	  *  @return the current value of the dataType property
	  */
	public ro.siveco.svapnt.configuration.entity.DataType getDataType()
	{
		return dataType;
	}

	/**
	  *  Set method for the dataType property.
	  */
	public void setDataType( ro.siveco.svapnt.configuration.entity.DataType _dataType )
	{
		dataType = _dataType;
	}


    @OneToMany( mappedBy = "lov", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "parameter" )
	/**
	  *  Access method for the parameters property.
	  *  @return the current value of the parameters property
	  */
	public Collection<ro.siveco.svapnt.configuration.entity.Parameter> getParameters()
	{
		return parameters;
	}

	/**
	  *  Set method for the parameters property.
	  */
	public void setParameters( Collection<ro.siveco.svapnt.configuration.entity.Parameter> _parameters )
	{
		parameters = _parameters;
	}


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                                         
    public static final String NQ_findByName = "configuration_Lov_findByName";
    public static Lov findByName( java.lang.String name ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( name);
        return ( Lov ) getGenericSession().getSingleResult(NQ_findByName, params);
    }

	//Unique keys methods with Relations as parameters
	//model defined finders


    public static Lov find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( Lov.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
