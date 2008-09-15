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
import ro.siveco.svapnt.configuration.entity.Parameter;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Parametri aplicatiei<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * tableName PARAMS
 * constraintNamePK PK_PARAMS
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.configuration.session.ParameterMgrLocal.JNDI_NAME )
public abstract class ParameterBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "configuration_Parameter";
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

    @Column( name = "PARAM_NAME" )
	private java.lang.String			    paramName;

    @Column( name = "MODIFYABLE" )
	private java.lang.String			    modifyable;

    @Column( name = "LENGTH" )
	private java.lang.Long			    length;

    @Column( name = "VERIFY_TABLE" )
	private java.lang.String			    verifyTable;

    @Column( name = "VERIFY_COLUMN" )
	private java.lang.String			    verifyColumn;

    @Column( name = "IS_NULL_FLAG" )
	private java.lang.String			    isNullFlag;

    @Column( name = "DESCRIPTION" )
	private java.lang.String			    description;

    @Column( name = "ORG_UNIT_SETABLE_FLAG" )
	private java.lang.String			    orgUnitSetableFlag;

    @Column( name = "USER_DEFINED" )
	private java.lang.String			    userDefined;

    @Column( name = "USAGE" )
	private java.lang.Long			    usage;

    @Column( name = "DATA_TYPE" )
	private java.lang.String			    dataType;

	/* Vduped Attributes */

	/* Relationships */

    @ManyToOne( optional =  true )
    @JoinColumn( name = "LOV_ID" )
// lov
	private ro.siveco.svapnt.configuration.entity.Lov			    lov;

    @OneToMany( mappedBy = "parameter", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.configuration.entity.ParamValue>     paramValues = new ArrayList();

    @ManyToOne( optional =  false )
    @JoinColumn( name = "APPLICATION_ID" )
// module
	private ro.siveco.svapnt.configuration.entity.Module			    module;

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

    @Column( name = "PARAM_NAME" )
    @PersistentFieldGetter( fieldName = "paramName" )
@VisibleList(true)
@Length("20")
@VisibleDetail(true)
@OrderNo("20")
@VisibleReferred(true)
@Required(true)
@UniqueKey("UK_PARAMS")
	/**
	  *  Access method for the paramName property.
	  *  @return the current value of the paramName property
	  */
	public java.lang.String getParamName()
	{
		return paramName;
	}

	/**
	  *  Set method for the paramName property.
	  */
	public void setParamName( java.lang.String _paramName )
	{
		paramName = _paramName;
	}

    @Column( name = "MODIFYABLE" )
    @PersistentFieldGetter( fieldName = "modifyable" )
@Required(true)
@VisibleDetail(true)
@VisibleList(true)
@Widget("CK")
@OrderNo("50")
	/**
	  *  Access method for the modifyable property.
	  *  @return the current value of the modifyable property
	  */
	public java.lang.String getModifyable()
	{
		return modifyable;
	}

	/**
	  *  Set method for the modifyable property.
	  */
	public void setModifyable( java.lang.String _modifyable )
	{
		modifyable = _modifyable;
	}

    @Column( name = "LENGTH" )
    @PersistentFieldGetter( fieldName = "length" )
@Length("10")
@OrderNo("60")
@VisibleDetail(true)
@VisibleList(true)
@Required(true)
	/**
	  *  Access method for the length property.
	  *  @return the current value of the length property
	  */
	public java.lang.Long getLength()
	{
		return length;
	}

	/**
	  *  Set method for the length property.
	  */
	public void setLength( java.lang.Long _length )
	{
		length = _length;
	}

    @Column( name = "VERIFY_TABLE" )
    @PersistentFieldGetter( fieldName = "verifyTable" )
@VisibleList(true)
@OrderNo("70")
@Length("30")
@VisibleDetail(true)
	/**
	  *  Access method for the verifyTable property.
	  *  @return the current value of the verifyTable property
	  */
	public java.lang.String getVerifyTable()
	{
		return verifyTable;
	}

	/**
	  *  Set method for the verifyTable property.
	  */
	public void setVerifyTable( java.lang.String _verifyTable )
	{
		verifyTable = _verifyTable;
	}

    @Column( name = "VERIFY_COLUMN" )
    @PersistentFieldGetter( fieldName = "verifyColumn" )
@Length("30")
@OrderNo("80")
@VisibleList(true)
@VisibleDetail(true)
	/**
	  *  Access method for the verifyColumn property.
	  *  @return the current value of the verifyColumn property
	  */
	public java.lang.String getVerifyColumn()
	{
		return verifyColumn;
	}

	/**
	  *  Set method for the verifyColumn property.
	  */
	public void setVerifyColumn( java.lang.String _verifyColumn )
	{
		verifyColumn = _verifyColumn;
	}

    @Column( name = "IS_NULL_FLAG" )
    @PersistentFieldGetter( fieldName = "isNullFlag" )
@VisibleList(true)
@Required(true)
@Widget("CK")
@Label("Is null")
@OrderNo("90")
@VisibleDetail(true)
	/**
	  *  Access method for the isNullFlag property.
	  *  @return the current value of the isNullFlag property
	  */
	public java.lang.String getIsNullFlag()
	{
		return isNullFlag;
	}

	/**
	  *  Set method for the isNullFlag property.
	  */
	public void setIsNullFlag( java.lang.String _isNullFlag )
	{
		isNullFlag = _isNullFlag;
	}

    @Column( name = "DESCRIPTION" )
    @PersistentFieldGetter( fieldName = "description" )
@OrderNo("100")
@VisibleDetail(true)
@Length("255")
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

    @Column( name = "ORG_UNIT_SETABLE_FLAG" )
    @PersistentFieldGetter( fieldName = "orgUnitSetableFlag" )
@VisibleDetail(true)
@Widget("CK")
@OrderNo("110")
@Label("Org unit setting")
@VisibleList(true)
	/**
	  *  Access method for the orgUnitSetableFlag property.
	  *  @return the current value of the orgUnitSetableFlag property
	  */
	public java.lang.String getOrgUnitSetableFlag()
	{
		return orgUnitSetableFlag;
	}

	/**
	  *  Set method for the orgUnitSetableFlag property.
	  */
	public void setOrgUnitSetableFlag( java.lang.String _orgUnitSetableFlag )
	{
		orgUnitSetableFlag = _orgUnitSetableFlag;
	}

    @Column( name = "USER_DEFINED" )
    @PersistentFieldGetter( fieldName = "userDefined" )
@ReadOnly(true)
@VisibleDetail(true)
@VisibleList(false)
	/**
	  *  Access method for the userDefined property.
	  *  @return the current value of the userDefined property
	  */
	public java.lang.String getUserDefined()
	{
		return userDefined;
	}

	/**
	  *  Set method for the userDefined property.
	  */
	public void setUserDefined( java.lang.String _userDefined )
	{
		userDefined = _userDefined;
	}

    @Column( name = "USAGE" )
    @PersistentFieldGetter( fieldName = "usage" )
@VisibleDetail(false)
@VisibleList(false)
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

    @Column( name = "DATA_TYPE" )
    @PersistentFieldGetter( fieldName = "dataType" )
@VisibleDetail(true)
@Required(true)
@WidgetValues("N|Number;C|Character;D|Date;B|Boolean")
@Widget("CB")
@VisibleList(true)
@Length("1")
@OrderNo("200")
	/**
	  *  Access method for the dataType property.
	  *  @return the current value of the dataType property
	  */
	public java.lang.String getDataType()
	{
		return dataType;
	}

	/**
	  *  Set method for the dataType property.
	  */
	public void setDataType( java.lang.String _dataType )
	{
		dataType = _dataType;
	}


	/* Vduped Attribute setters */

	/* Relationship getters & setters */
    @PersistentToOneGetter( relName = "lov" )

    @ManyToOne( optional =  true )
    @JoinColumn( name = "LOV_ID" )
// lov
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


    @OneToMany( mappedBy = "parameter", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "paramValue" )
	/**
	  *  Access method for the paramValues property.
	  *  @return the current value of the paramValues property
	  */
	public Collection<ro.siveco.svapnt.configuration.entity.ParamValue> getParamValues()
	{
		return paramValues;
	}

	/**
	  *  Set method for the paramValues property.
	  */
	public void setParamValues( Collection<ro.siveco.svapnt.configuration.entity.ParamValue> _paramValues )
	{
		paramValues = _paramValues;
	}

    @PersistentToOneGetter( relName = "module" )

    @ManyToOne( optional =  false )
    @JoinColumn( name = "APPLICATION_ID" )
// module
	/**
	  *  Access method for the module property.
	  *  @return the current value of the module property
	  */
	public ro.siveco.svapnt.configuration.entity.Module getModule()
	{
		return module;
	}

	/**
	  *  Set method for the module property.
	  */
	public void setModule( ro.siveco.svapnt.configuration.entity.Module _module )
	{
		module = _module;
	}


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                                                                                                              
    public static final String NQ_findByParamName = "configuration_Parameter_findByParamName";
    public static Parameter findByParamName( java.lang.String paramName ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( paramName);
        return ( Parameter ) getGenericSession().getSingleResult(NQ_findByParamName, params);
    }

	//Unique keys methods with Relations as parameters
	//model defined finders


    public static Parameter find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( Parameter.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
