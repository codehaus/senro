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
import ro.siveco.svapnt.common.entity.PersonContact;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Persoane de contact<br>
 * </p>
 * <p>
 * Vezi documentul Dictionar Date<br>
 * </p>
 * <p>
 * Informatii de identificare persoane fizice cu studii
 * non-medicale superioare<br>
 * </p>
 * <p>
 * Persoane de contact pentru persoane juridice<br>
 * </p>
 *
 * validTo validTo
 * validFrom validFrom
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.common.session.PersonContactMgrLocal.JNDI_NAME )
public abstract class PersonContactBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "common_PersonContact";
	    public static String getEntityName()
	    {
	        return ENTITY_NAME;
	    }

	/* Attributes */

    @Column( name = "CODE" )
	private java.lang.String			    code;

    @Column( name = "CONTACT_NAME" )
	private java.lang.String			    contactName;

    @Column( name = "PID" )
	private java.lang.String			    pid;

    @Column( name = "PHONE_NO_1" )
	private java.lang.String			    phoneNo1;

    @Column( name = "PHONE_NO_2" )
	private java.lang.String			    phoneNo2;

    @Column( name = "FAX_NO" )
	private java.lang.String			    faxNo;

    @Column( name = "TELEX" )
	private java.lang.String			    telex;

    @Column( name = "COMMENTS" )
	private java.lang.String			    comments;

    @Column( name = "VALID_FROM" )
	private java.util.Date			    validFrom;

    @Column( name = "VALID_TO" )
	private java.util.Date			    validTo;

    @Column( name = "ID" )
    @Id
    @GeneratedValue( strategy = SEQUENCE, generator = "SVAPNT_UTILS_SEQ" )
    @SequenceGenerator( name = "SVAPNT_UTILS_SEQ", sequenceName = "SVAPNT_UTILS_SEQ" )
	private java.lang.Long			    id;

    @Column( name = "UPD_TIMESTAMP" )
    @Version
	private java.lang.Long			    updTimestamp;

	/* Vduped Attributes */

	/* Relationships */

    @ManyToOne( optional =  false )
    @JoinColumn( name = "PERSON_ID" )
// person
	private ro.siveco.svapnt.common.entity.Person			    person;

    @ManyToOne( optional =  false )
    @JoinColumn( name = "CONTACT_TYPE_ID" )
// contactType
	private ro.siveco.svapnt.common.entity.ContactType			    contactType;

	/* Attribute getters & setters */
    @Column( name = "CODE" )
    @PersistentFieldGetter( fieldName = "code" )
@Required(true)
@Length("20")
@VisibleList(true)
@VisibleDetail(true)
@OrderNo("20")
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

    @Column( name = "CONTACT_NAME" )
    @PersistentFieldGetter( fieldName = "contactName" )
@Required(true)
@VisibleDetail(true)
@Length("100")
@VisibleList(true)
@OrderNo("30")
	/**
	  *  Access method for the contactName property.
	  *  @return the current value of the contactName property
	  */
	public java.lang.String getContactName()
	{
		return contactName;
	}

	/**
	  *  Set method for the contactName property.
	  */
	public void setContactName( java.lang.String _contactName )
	{
		contactName = _contactName;
	}

    @Column( name = "PID" )
    @PersistentFieldGetter( fieldName = "pid" )
@Length("13")
@VisibleList(true)
@VisibleDetail(true)
@OrderNo("40")
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

    @Column( name = "PHONE_NO_1" )
    @PersistentFieldGetter( fieldName = "phoneNo1" )
@Length("25")
@VisibleList(true)
@VisibleDetail(true)
@OrderNo("50")
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

    @Column( name = "PHONE_NO_2" )
    @PersistentFieldGetter( fieldName = "phoneNo2" )
@VisibleList(true)
@VisibleDetail(true)
@Length("25")
@OrderNo("60")
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

    @Column( name = "FAX_NO" )
    @PersistentFieldGetter( fieldName = "faxNo" )
@VisibleDetail(true)
@VisibleList(true)
@Length("25")
@OrderNo("70")
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

    @Column( name = "TELEX" )
    @PersistentFieldGetter( fieldName = "telex" )
@VisibleList(true)
@Length("25")
@VisibleDetail(true)
@OrderNo("80")
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

    @Column( name = "COMMENTS" )
    @PersistentFieldGetter( fieldName = "comments" )
@VisibleDetail(true)
@Widget("TA")
@Length("200")
@Unfiltered(true)
@OrderNo("110")
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

    @Column( name = "VALID_FROM" )
    @PersistentFieldGetter( fieldName = "validFrom" )
@VisibleList(true)
@VisibleDetail(true)
@Required(true)
@OrderNo("90")
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
@VisibleList(true)
@OrderNo("100")
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


	/* Vduped Attribute setters */

	/* Relationship getters & setters */
    @PersistentToOneGetter( relName = "person" )

    @ManyToOne( optional =  false )
    @JoinColumn( name = "PERSON_ID" )
// person
	/**
	  *  Access method for the person property.
	  *  @return the current value of the person property
	  */
	public ro.siveco.svapnt.common.entity.Person getPerson()
	{
		return person;
	}

	/**
	  *  Set method for the person property.
	  */
	public void setPerson( ro.siveco.svapnt.common.entity.Person _person )
	{
		person = _person;
	}

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


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                    
	//Unique keys methods with Relations as parameters
	//model defined finders


    public static PersonContact find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( PersonContact.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
