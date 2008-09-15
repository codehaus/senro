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
import ro.siveco.svapnt.common.entity.Employee;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Salariati<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * tableName EMPLOYEES
 * constraintNamePK PK_EMPLOYEES
 * entityID 1
 * validFrom validFrom
 * validTo validTo
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.common.session.EmployeeMgrLocal.JNDI_NAME )
public abstract class EmployeeBase extends ro.siveco.svapnt.common.entity.Person implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "common_Employee";
	    public static String getEntityName()
	    {
	        return ENTITY_NAME;
	    }

	/* Attributes */

    @Column( name = "HOME_PHONE" )
	private java.lang.String			    homePhone;

    @Column( name = "WORK_PHONE" )
	private java.lang.String			    workPhone;

    @Column( name = "WORK_PHONE_INTERIOR" )
	private java.lang.String			    workPhoneInterior;

    @Column( name = "MARITAL_STATUS" )
	private java.lang.Short			    maritalStatus;

    @Column( name = "GENDER" )
	private java.lang.Character			    gender;

	/* Vduped Attributes */

    @Column( name = "CODE" )
	private java.lang.String			    vdupEmployee_code;

    @Column( name = "NAME" )
	private java.lang.String			    vdupEmployee_name;

    @Column( name = "PID" )
	private java.lang.String			    vdupEmployee_pid;

    @Column( name = "BIRTH_DATE" )
	private java.util.Date			    vdupEmployee_birthDate;

	/* Relationships */

    @ManyToOne( optional =  false )
    @JoinColumn( name = "RES_TRADE_ID" )
// resTrade
	private ro.siveco.svapnt.common.entity.ResTrade			    resTrade;

    @ManyToOne( optional =  true )
    @JoinColumn( name = "PY_ORG_UNIT_ID" )
// payrollOrgUnit
	private ro.siveco.svapnt.common.entity.OrgUnit			    payrollOrgUnit;

    @ManyToOne( optional =  true )
    @JoinColumn( name = "HR_ORG_UNIT_ID" )
// hrOrgUnit
	private ro.siveco.svapnt.common.entity.OrgUnit			    hrOrgUnit;

    @ManyToOne( optional =  true )
    @JoinColumn( name = "RELIGION_ID" )
// religion
	private ro.siveco.svapnt.common.entity.Religion			    religion;

    @OneToMany( mappedBy = "employee", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.configuration.entity.User>     user = new ArrayList();

    @OneToMany( mappedBy = "employee", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.EmployeeCard>     employeeCards = new ArrayList();

	/* Attribute getters & setters */
    @Column( name = "HOME_PHONE" )
    @PersistentFieldGetter( fieldName = "homePhone" )
@OrderNo("80")
@VisibleDetail(true)
@Length("20")
@Unfiltered(true)
	/**
	  *  Access method for the homePhone property.
	  *  @return the current value of the homePhone property
	  */
	public java.lang.String getHomePhone()
	{
		return homePhone;
	}

	/**
	  *  Set method for the homePhone property.
	  */
	public void setHomePhone( java.lang.String _homePhone )
	{
		homePhone = _homePhone;
	}

    @Column( name = "WORK_PHONE" )
    @PersistentFieldGetter( fieldName = "workPhone" )
@VisibleDetail(true)
@Length("20")
@OrderNo("85")
@Unfiltered(true)
	/**
	  *  Access method for the workPhone property.
	  *  @return the current value of the workPhone property
	  */
	public java.lang.String getWorkPhone()
	{
		return workPhone;
	}

	/**
	  *  Set method for the workPhone property.
	  */
	public void setWorkPhone( java.lang.String _workPhone )
	{
		workPhone = _workPhone;
	}

    @Column( name = "WORK_PHONE_INTERIOR" )
    @PersistentFieldGetter( fieldName = "workPhoneInterior" )
@OrderNo("90")
@VisibleDetail(true)
@Length("20")
@Unfiltered(true)
	/**
	  *  Access method for the workPhoneInterior property.
	  *  @return the current value of the workPhoneInterior property
	  */
	public java.lang.String getWorkPhoneInterior()
	{
		return workPhoneInterior;
	}

	/**
	  *  Set method for the workPhoneInterior property.
	  */
	public void setWorkPhoneInterior( java.lang.String _workPhoneInterior )
	{
		workPhoneInterior = _workPhoneInterior;
	}

    @Column( name = "MARITAL_STATUS" )
    @PersistentFieldGetter( fieldName = "maritalStatus" )
@OrderNo("62")
@VisibleDetail(true)
@WidgetValues("1|Married;2|Single;3|Divorced;4|Widowed")
@Widget("CB")
	/**
	  *  Access method for the maritalStatus property.
	  *  @return the current value of the maritalStatus property
	  */
	public java.lang.Short getMaritalStatus()
	{
		return maritalStatus;
	}

	/**
	  *  Set method for the maritalStatus property.
	  */
	public void setMaritalStatus( java.lang.Short _maritalStatus )
	{
		maritalStatus = _maritalStatus;
	}

    @Column( name = "GENDER" )
    @PersistentFieldGetter( fieldName = "gender" )
@OrderNo("64")
@WidgetValues("1|Male;2|Female;3|Not specified;4|Bisexual")
@VisibleDetail(true)
@Widget("CB")
	/**
	  *  Access method for the gender property.
	  *  @return the current value of the gender property
	  */
	public java.lang.Character getGender()
	{
		return gender;
	}

	/**
	  *  Set method for the gender property.
	  */
	public void setGender( java.lang.Character _gender )
	{
		gender = _gender;
	}


	/* Vduped Attribute setters */
	/**
	  *  Set method for the duplicated code property.
	  */
	public void setCode( java.lang.String _code )
	{
	    super.setCode( _code );
		vdupEmployee_code = _code;
	}

	/**
	  *  Set method for the duplicated name property.
	  */
	public void setName( java.lang.String _name )
	{
	    super.setName( _name );
		vdupEmployee_name = _name;
	}

	/**
	  *  Set method for the duplicated pid property.
	  */
	public void setPid( java.lang.String _pid )
	{
	    super.setPid( _pid );
		vdupEmployee_pid = _pid;
	}

	/**
	  *  Set method for the duplicated birthDate property.
	  */
	public void setBirthDate( java.util.Date _birthDate )
	{
	    super.setBirthDate( _birthDate );
		vdupEmployee_birthDate = _birthDate;
	}


	/* Relationship getters & setters */
    @PersistentToOneGetter( relName = "resTrade" )

    @ManyToOne( optional =  false )
    @JoinColumn( name = "RES_TRADE_ID" )
// resTrade
	/**
	  *  Access method for the resTrade property.
	  *  @return the current value of the resTrade property
	  */
	public ro.siveco.svapnt.common.entity.ResTrade getResTrade()
	{
		return resTrade;
	}

	/**
	  *  Set method for the resTrade property.
	  */
	public void setResTrade( ro.siveco.svapnt.common.entity.ResTrade _resTrade )
	{
		resTrade = _resTrade;
	}

    @PersistentToOneGetter( relName = "payrollOrgUnit" )

    @ManyToOne( optional =  true )
    @JoinColumn( name = "PY_ORG_UNIT_ID" )
// payrollOrgUnit
	/**
	  *  Access method for the payrollOrgUnit property.
	  *  @return the current value of the payrollOrgUnit property
	  */
	public ro.siveco.svapnt.common.entity.OrgUnit getPayrollOrgUnit()
	{
		return payrollOrgUnit;
	}

	/**
	  *  Set method for the payrollOrgUnit property.
	  */
	public void setPayrollOrgUnit( ro.siveco.svapnt.common.entity.OrgUnit _payrollOrgUnit )
	{
		payrollOrgUnit = _payrollOrgUnit;
	}

    @PersistentToOneGetter( relName = "hrOrgUnit" )

    @ManyToOne( optional =  true )
    @JoinColumn( name = "HR_ORG_UNIT_ID" )
// hrOrgUnit
	/**
	  *  Access method for the hrOrgUnit property.
	  *  @return the current value of the hrOrgUnit property
	  */
	public ro.siveco.svapnt.common.entity.OrgUnit getHrOrgUnit()
	{
		return hrOrgUnit;
	}

	/**
	  *  Set method for the hrOrgUnit property.
	  */
	public void setHrOrgUnit( ro.siveco.svapnt.common.entity.OrgUnit _hrOrgUnit )
	{
		hrOrgUnit = _hrOrgUnit;
	}

    @PersistentToOneGetter( relName = "religion" )

    @ManyToOne( optional =  true )
    @JoinColumn( name = "RELIGION_ID" )
// religion
	/**
	  *  Access method for the religion property.
	  *  @return the current value of the religion property
	  */
	public ro.siveco.svapnt.common.entity.Religion getReligion()
	{
		return religion;
	}

	/**
	  *  Set method for the religion property.
	  */
	public void setReligion( ro.siveco.svapnt.common.entity.Religion _religion )
	{
		religion = _religion;
	}


    @OneToMany( mappedBy = "employee", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "user" )
	/**
	  *  Access method for the user property.
	  *  @return the current value of the user property
	  */
	public Collection<ro.siveco.svapnt.configuration.entity.User> getUser()
	{
		return user;
	}

	/**
	  *  Set method for the user property.
	  */
	public void setUser( Collection<ro.siveco.svapnt.configuration.entity.User> _user )
	{
		user = _user;
	}


    @OneToMany( mappedBy = "employee", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "employeeCard" )
	/**
	  *  Access method for the employeeCards property.
	  *  @return the current value of the employeeCards property
	  */
	public Collection<ro.siveco.svapnt.common.entity.EmployeeCard> getEmployeeCards()
	{
		return employeeCards;
	}

	/**
	  *  Set method for the employeeCards property.
	  */
	public void setEmployeeCards( Collection<ro.siveco.svapnt.common.entity.EmployeeCard> _employeeCards )
	{
		employeeCards = _employeeCards;
	}


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
    public static final String NQ_findByCode = "common_Employee_findByCode";
    public static Employee findByCode( java.lang.String code ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( code);
        return ( Employee ) getGenericSession().getSingleResult(NQ_findByCode, params);
    }

    public static final String NQ_findByPid = "common_Employee_findByPid";
    public static Employee findByPid( java.lang.String pid ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( pid);
        return ( Employee ) getGenericSession().getSingleResult(NQ_findByPid, params);
    }

	//Unique keys methods with Relations as parameters
	//model defined finders


    public static Employee find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( Employee.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
