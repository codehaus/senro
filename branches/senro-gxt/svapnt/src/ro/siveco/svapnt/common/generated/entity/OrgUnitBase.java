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
import ro.siveco.svapnt.common.entity.OrgUnit;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Structuri organizatorice : contine atat unitatile organizatorice
 * (sucursale, filiale etc) cat si departamentele, birouri,
 * ateliere etc.
 * </p>
 * <p>
 * <br>Vezi documentul Dictionar Date<br>
 * </p>
 * <p>
 * Date cu privire la Casele de Asigurari de Sanatate<br>
 * </p>
 * <p>
 * Data necesare evidentei cererilor de acreditare
 * </p>
 *
 * tree true
 * tableName ORG_UNITS
 * constraintNamePK PK_ORG_UNITS
 * entityID 2
 * finderInfo findByParameterIdAndOrgUnitIsNull|1|is null;findByDocTypeIdAndNullOrgUnit|2|is null
 * treeDisplay code
 * treeTooltip name
 * validFrom validFrom
 * validTo validTo
 * entityLevel entityLevel
 * treeLeftBound leftBound
 * treeRightBound rightBound
 * permanentLog true
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.common.session.OrgUnitMgrLocal.JNDI_NAME )
public abstract class OrgUnitBase extends ro.siveco.svapnt.common.entity.PartnerOrgUnit implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "common_OrgUnit";
	    public static String getEntityName()
	    {
	        return ENTITY_NAME;
	    }

	/* Attributes */

    @Column( name = "OU_FLAG", updatable = false )
	private java.lang.Short			    ouFlag;

	/* Vduped Attributes */

    @Column( name = "CODE" )
	private java.lang.String			    vdupOrgUnit_code;

    @Column( name = "NAME" )
	private java.lang.String			    vdupOrgUnit_name;

	/* Relationships */

    @OneToMany( mappedBy = "payrollOrgUnit", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.Employee>     payrollEmployees = new ArrayList();

    @ManyToOne( optional =  false )
    @JoinColumn( name = "ORG_UNIT_TYPE_ID" )
// orgUnitType
	private ro.siveco.svapnt.common.entity.OrgUnitType			    orgUnitType;

    @OneToMany( mappedBy = "hrOrgUnit", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.Employee>     hrEmployees = new ArrayList();

    @OneToMany( mappedBy = "orgUnit", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.configuration.entity.ParamValue>     paramValues = new ArrayList();

    @OneToMany( mappedBy = "orgUnit", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.OrgUnitContact>     orgUnitContact = new ArrayList();

	/* Attribute getters & setters */
    @Column( name = "OU_FLAG", updatable = false )
    @PersistentFieldGetter( fieldName = "ouFlag" )
@DomainSplitter(true)
	/**
	  *  Access method for the ouFlag property.
	  *  @return the current value of the ouFlag property
	  */
	public java.lang.Short getOuFlag()
	{
		return ouFlag;
	}

	/**
	  *  Set method for the ouFlag property.
	  */
	public void setOuFlag( java.lang.Short _ouFlag )
	{
		ouFlag = _ouFlag;
	}


	/* Vduped Attribute setters */
	/**
	  *  Set method for the duplicated code property.
	  */
	public void setCode( java.lang.String _code )
	{
	    super.setCode( _code );
		vdupOrgUnit_code = _code;
	}

	/**
	  *  Set method for the duplicated name property.
	  */
	public void setName( java.lang.String _name )
	{
	    super.setName( _name );
		vdupOrgUnit_name = _name;
	}


	/* Relationship getters & setters */

    @OneToMany( mappedBy = "payrollOrgUnit", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "payrollEmployees" )
	/**
	  *  Access method for the payrollEmployees property.
	  *  @return the current value of the payrollEmployees property
	  */
	public Collection<ro.siveco.svapnt.common.entity.Employee> getPayrollEmployees()
	{
		return payrollEmployees;
	}

	/**
	  *  Set method for the payrollEmployees property.
	  */
	public void setPayrollEmployees( Collection<ro.siveco.svapnt.common.entity.Employee> _payrollEmployees )
	{
		payrollEmployees = _payrollEmployees;
	}

    @PersistentToOneGetter( relName = "orgUnitType" )

    @ManyToOne( optional =  false )
    @JoinColumn( name = "ORG_UNIT_TYPE_ID" )
// orgUnitType
	/**
	  *  Access method for the orgUnitType property.
	  *  @return the current value of the orgUnitType property
	  */
	public ro.siveco.svapnt.common.entity.OrgUnitType getOrgUnitType()
	{
		return orgUnitType;
	}

	/**
	  *  Set method for the orgUnitType property.
	  */
	public void setOrgUnitType( ro.siveco.svapnt.common.entity.OrgUnitType _orgUnitType )
	{
		orgUnitType = _orgUnitType;
	}


    @OneToMany( mappedBy = "hrOrgUnit", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "hrEmployees" )
	/**
	  *  Access method for the hrEmployees property.
	  *  @return the current value of the hrEmployees property
	  */
	public Collection<ro.siveco.svapnt.common.entity.Employee> getHrEmployees()
	{
		return hrEmployees;
	}

	/**
	  *  Set method for the hrEmployees property.
	  */
	public void setHrEmployees( Collection<ro.siveco.svapnt.common.entity.Employee> _hrEmployees )
	{
		hrEmployees = _hrEmployees;
	}


    @OneToMany( mappedBy = "orgUnit", cascade = {PERSIST, MERGE, REFRESH} )
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


    @OneToMany( mappedBy = "orgUnit", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "orgUnitContact" )
	/**
	  *  Access method for the orgUnitContact property.
	  *  @return the current value of the orgUnitContact property
	  */
	public Collection<ro.siveco.svapnt.common.entity.OrgUnitContact> getOrgUnitContact()
	{
		return orgUnitContact;
	}

	/**
	  *  Set method for the orgUnitContact property.
	  */
	public void setOrgUnitContact( Collection<ro.siveco.svapnt.common.entity.OrgUnitContact> _orgUnitContact )
	{
		orgUnitContact = _orgUnitContact;
	}


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                                                                                                                                                                          
    public static final String NQ_findByCode = "common_OrgUnit_findByCode";
    public static OrgUnit findByCode( java.lang.String code ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( code);
        return ( OrgUnit ) getGenericSession().getSingleResult(NQ_findByCode, params);
    }

	//Unique keys methods with Relations as parameters
	//model defined finders


    public static OrgUnit find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( OrgUnit.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/


//[start] Tree finders
						
	//[end] Tree finders

}
