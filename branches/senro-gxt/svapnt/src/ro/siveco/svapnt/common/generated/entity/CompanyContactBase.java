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
import ro.siveco.svapnt.common.entity.CompanyContact;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Contacte firme, persoane fizice
 * </p>
 * <p>
 * <br>Vezi documentul Dictionar Date<br>
 * </p>
 * <p>
 * Informatii de identificare a persoanelor juridice<br>
 * </p>
 * <p>
 * Persoane de contact pentru persoane juridice<br>
 * </p>
 *
 * constraintNamePK PK_COMPANY_CONTACT
 * tableName COMPANY_CONTACTS
 * validFrom validFrom
 * validTo validTo
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.common.session.CompanyContactMgrLocal.JNDI_NAME )
public abstract class CompanyContactBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "common_CompanyContact";
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

    @Column( name = "NAME_CONTACT" )
	private java.lang.String			    nameContact;

    @Column( name = "PHONE_NO_1" )
	private java.lang.String			    phoneNo1;

    @Column( name = "FAX_NO" )
	private java.lang.String			    faxNo;

    @Column( name = "PHONE_NO_2" )
	private java.lang.String			    phoneNo2;

    @Column( name = "TELEX" )
	private java.lang.String			    telex;

    @Column( name = "DESC_01" )
	private java.lang.String			    desc01;

    @Column( name = "DESC_02" )
	private java.lang.String			    desc02;

    @Column( name = "COMMENTS" )
	private java.lang.String			    comments;

    @Column( name = "PID" )
	private java.lang.String			    pid;

    @Column( name = "VALID_FROM" )
	private java.util.Date			    validFrom;

    @Column( name = "VALID_TO" )
	private java.util.Date			    validTo;

	/* Vduped Attributes */

	/* Relationships */

    @ManyToOne( optional =  false )
    @JoinColumn( name = "CONTACT_TYPE_ID" )
// contactType
	private ro.siveco.svapnt.common.entity.ContactType			    contactType;

    @ManyToOne( optional =  false )
    @JoinColumn( name = "COMPANY_ID" )
// company
	private ro.siveco.svapnt.common.entity.Company			    company;

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
@OrderNo("2")
@Length("20")
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

    @Column( name = "NAME_CONTACT" )
    @PersistentFieldGetter( fieldName = "nameContact" )
@VisibleReferred(true)
@VisibleDetail(true)
@OrderNo("3")
@Required(true)
@VisibleList(true)
@Length("100")
@UniqueKey("UK_COMPANY_CONTACT|2")
	/**
	  *  Access method for the nameContact property.
	  *  @return the current value of the nameContact property
	  */
	public java.lang.String getNameContact()
	{
		return nameContact;
	}

	/**
	  *  Set method for the nameContact property.
	  */
	public void setNameContact( java.lang.String _nameContact )
	{
		nameContact = _nameContact;
	}

    @Column( name = "PHONE_NO_1" )
    @PersistentFieldGetter( fieldName = "phoneNo1" )
@VisibleList(true)
@VisibleDetail(true)
@OrderNo("5")
@Length("25")
	/**
	  *  Access method for the phoneNo1 property.
	  *  @return the current value of the phoneNo1 property
	  */
	public java.lang.String getPhoneNo1()
	{
		return phoneNo1;
	}

	/**
	  *  Set method for the phoneNo1 property.
	  */
	public void setPhoneNo1( java.lang.String _phoneNo1 )
	{
		phoneNo1 = _phoneNo1;
	}

    @Column( name = "FAX_NO" )
    @PersistentFieldGetter( fieldName = "faxNo" )
@Length("25")
@OrderNo("7")
@VisibleList(true)
@VisibleDetail(true)
	/**
	  *  Access method for the faxNo property.
	  *  @return the current value of the faxNo property
	  */
	public java.lang.String getFaxNo()
	{
		return faxNo;
	}

	/**
	  *  Set method for the faxNo property.
	  */
	public void setFaxNo( java.lang.String _faxNo )
	{
		faxNo = _faxNo;
	}

    @Column( name = "PHONE_NO_2" )
    @PersistentFieldGetter( fieldName = "phoneNo2" )
@VisibleDetail(true)
@OrderNo("6")
@VisibleList(true)
@Length("25")
	/**
	  *  Access method for the phoneNo2 property.
	  *  @return the current value of the phoneNo2 property
	  */
	public java.lang.String getPhoneNo2()
	{
		return phoneNo2;
	}

	/**
	  *  Set method for the phoneNo2 property.
	  */
	public void setPhoneNo2( java.lang.String _phoneNo2 )
	{
		phoneNo2 = _phoneNo2;
	}

    @Column( name = "TELEX" )
    @PersistentFieldGetter( fieldName = "telex" )
@VisibleDetail(true)
@VisibleList(true)
@OrderNo("8")
@Length("25")
	/**
	  *  Access method for the telex property.
	  *  @return the current value of the telex property
	  */
	public java.lang.String getTelex()
	{
		return telex;
	}

	/**
	  *  Set method for the telex property.
	  */
	public void setTelex( java.lang.String _telex )
	{
		telex = _telex;
	}

    @Column( name = "DESC_01" )
    @PersistentFieldGetter( fieldName = "desc01" )
@Length("100")
@OrderNo("9")
	/**
	  *  Access method for the desc01 property.
	  *  @return the current value of the desc01 property
	  */
	public java.lang.String getDesc01()
	{
		return desc01;
	}

	/**
	  *  Set method for the desc01 property.
	  */
	public void setDesc01( java.lang.String _desc01 )
	{
		desc01 = _desc01;
	}

    @Column( name = "DESC_02" )
    @PersistentFieldGetter( fieldName = "desc02" )
@OrderNo("10")
@Length("100")
	/**
	  *  Access method for the desc02 property.
	  *  @return the current value of the desc02 property
	  */
	public java.lang.String getDesc02()
	{
		return desc02;
	}

	/**
	  *  Set method for the desc02 property.
	  */
	public void setDesc02( java.lang.String _desc02 )
	{
		desc02 = _desc02;
	}

    @Column( name = "COMMENTS" )
    @PersistentFieldGetter( fieldName = "comments" )
@Length("200")
@OrderNo("13")
@VisibleDetail(true)
@Widget("TA")
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

    @Column( name = "PID" )
    @PersistentFieldGetter( fieldName = "pid" )
@Length("13")
@VisibleDetail(true)
@OrderNo("4")
@UniqueKey("UK_COMPANY_CONTACT|3")
	/**
	  *  Access method for the pid property.
	  *  @return the current value of the pid property
	  */
	public java.lang.String getPid()
	{
		return pid;
	}

	/**
	  *  Set method for the pid property.
	  */
	public void setPid( java.lang.String _pid )
	{
		pid = _pid;
	}

    @Column( name = "VALID_FROM" )
    @PersistentFieldGetter( fieldName = "validFrom" )
@VisibleDetail(true)
@Required(true)
@VisibleList(true)
@OrderNo("11")
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
@VisibleDetail(true)
@OrderNo("12")
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
    @PersistentToOneGetter( relName = "contactType" )

    @ManyToOne( optional =  false )
    @JoinColumn( name = "CONTACT_TYPE_ID" )
// contactType
	/**
	  *  Access method for the contactType property.
	  *  @return the current value of the contactType property
	  */
	public ro.siveco.svapnt.common.entity.ContactType getContactType()
	{
		return contactType;
	}

	/**
	  *  Set method for the contactType property.
	  */
	public void setContactType( ro.siveco.svapnt.common.entity.ContactType _contactType )
	{
		contactType = _contactType;
	}

    @PersistentToOneGetter( relName = "company" )

    @ManyToOne( optional =  false )
    @JoinColumn( name = "COMPANY_ID" )
// company
    @UniqueKey
	/**
	  *  Access method for the company property.
	  *  @return the current value of the company property
	  */
	public ro.siveco.svapnt.common.entity.Company getCompany()
	{
		return company;
	}

	/**
	  *  Set method for the company property.
	  */
	public void setCompany( ro.siveco.svapnt.common.entity.Company _company )
	{
		company = _company;
	}


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                                                                                                                                                                                                                                                                                                           
    public static final String NQ_findByNameContactAndPidAndCompany = "common_CompanyContact_findByNameContactAndPidAndCompany";
    public static CompanyContact findByNameContactAndPidAndCompany( java.lang.String nameContact, java.lang.String pid, ro.siveco.svapnt.common.entity.Company company ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( nameContact);
                params.add( pid);
                params.add(company.getId());
        return ( CompanyContact ) getGenericSession().getSingleResult(NQ_findByNameContactAndPidAndCompany, params);
    }

	//Unique keys methods with Relations as parameters
	//model defined finders


    public static CompanyContact find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( CompanyContact.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
