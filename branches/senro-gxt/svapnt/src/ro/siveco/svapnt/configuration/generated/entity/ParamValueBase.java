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
import ro.siveco.svapnt.configuration.entity.ParamValue;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Parametri aplicatii specifici pe unitati organizatorice<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * tableName ORG_UNIT_PARAMS
 * constraintNamePK PK_ORG_UNIT_PARAMS
 * finders findByParameterIdAndOrgUnitIsNull|true| |AND| 
 * validFrom validFrom
 * validTo validTo
 *
 */

 

 
@MappedSuperclass
public abstract class ParamValueBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "configuration_ParamValue";
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

    @Column( name = "PARAM_VALUE" )
	private java.lang.String			    paramValue;

    @Column( name = "VALID_FROM" )
	private java.util.Date			    validFrom;

    @Column( name = "VALID_TO" )
	private java.util.Date			    validTo;

	/* Vduped Attributes */

	/* Relationships */

    @ManyToOne( optional =  true )
    @JoinColumn( name = "ORG_UNIT_ID" )
// orgUnit
	private ro.siveco.svapnt.common.entity.OrgUnit			    orgUnit;

    @ManyToOne( optional =  false )
    @JoinColumn( name = "PARAM_ID" )
// parameter
	private ro.siveco.svapnt.configuration.entity.Parameter			    parameter;

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

    @Column( name = "PARAM_VALUE" )
    @PersistentFieldGetter( fieldName = "paramValue" )
@OrderNo("20")
@Length("255")
@VisibleList(true)
@VisibleDetail(true)
@VisibleLength("50")
	/**
	  *  Access method for the paramValue property.
	  *  @return the current value of the paramValue property
	  */
	public java.lang.String getParamValue()
	{
		return paramValue;
	}

	/**
	  *  Set method for the paramValue property.
	  */
	public void setParamValue( java.lang.String _paramValue )
	{
		paramValue = _paramValue;
	}

    @Column( name = "VALID_FROM" )
    @PersistentFieldGetter( fieldName = "validFrom" )
@Required(true)
@OrderNo("30")
@VisibleList(true)
@VisibleDetail(true)
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
@OrderNo("40")
@VisibleDetail(true)
@VisibleList(true)
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


	/* Vduped Attribute setters */

	/* Relationship getters & setters */
    @PersistentToOneGetter( relName = "orgUnit" )

    @ManyToOne( optional =  true )
    @JoinColumn( name = "ORG_UNIT_ID" )
// orgUnit
	/**
	  *  Access method for the orgUnit property.
	  *  @return the current value of the orgUnit property
	  */
	public ro.siveco.svapnt.common.entity.OrgUnit getOrgUnit()
	{
		return orgUnit;
	}

	/**
	  *  Set method for the orgUnit property.
	  */
	public void setOrgUnit( ro.siveco.svapnt.common.entity.OrgUnit _orgUnit )
	{
		orgUnit = _orgUnit;
	}

    @PersistentToOneGetter( relName = "parameter" )

    @ManyToOne( optional =  false )
    @JoinColumn( name = "PARAM_ID" )
// parameter
	/**
	  *  Access method for the parameter property.
	  *  @return the current value of the parameter property
	  */
	public ro.siveco.svapnt.configuration.entity.Parameter getParameter()
	{
		return parameter;
	}

	/**
	  *  Set method for the parameter property.
	  */
	public void setParameter( ro.siveco.svapnt.configuration.entity.Parameter _parameter )
	{
		parameter = _parameter;
	}


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                   
	//Unique keys methods with Relations as parameters
	//model defined finders

    public static final String NQ_F1findByOrgUnitAndParameterId = "ParamValue_F1findByOrgUnitAndParameterId";
       
    public static ParamValue findByOrgUnitAndParameterId( java.lang.Long p2_ParameterId) throws DAOException
    {

        ArrayList params = new ArrayList();

            params.add( p2_ParameterId);
        return ( ParamValue ) getGenericSession().getSingleResult(NQ_F1findByOrgUnitAndParameterId, params);
    }

    public static ParamValue find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( ParamValue.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
