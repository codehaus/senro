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
import ro.siveco.svapnt.common.entity.DocumentType;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Tipuri de documente autorizate<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * validTo validTo
 * validFrom validFrom
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.common.session.DocumentTypeMgrLocal.JNDI_NAME )
public abstract class DocumentTypeBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "common_DocumentType";
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

    @Column( name = "NAME" )
	private java.lang.String			    name;

    @Column( name = "VALID_FROM" )
	private java.util.Date			    validFrom;

    @Column( name = "VALID_TO" )
	private java.util.Date			    validTo;

    @Column( name = "PERSON" )
	private java.lang.String			    person;

    @Column( name = "COMPANY" )
	private java.lang.String			    company;

	/* Vduped Attributes */

	/* Relationships */

    @OneToMany( mappedBy = "documentType", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.CompanyDocument>     companyDocuments = new ArrayList();

    @OneToMany( mappedBy = "documentType", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.PersonDocument>     personDocuments = new ArrayList();

	/* Attribute getters & setters */
    @Column( name = "ID" )
    @PersistentFieldGetter( fieldName = "id" )
    @VisibleReferred
    @UniqueKey
    @Id
    @GeneratedValue( strategy = SEQUENCE, generator = "SVAPNT_UTILS_SEQ" )
    @SequenceGenerator( name = "SVAPNT_UTILS_SEQ", sequenceName = "SVAPNT_UTILS_SEQ" )
@DatabaseRequired(true)
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
@DatabaseRequired(true)
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
@Length("20")
@VisibleDetail(true)
@VisibleList(true)
@Required(true)
@UniqueKey("UK_DOCUMENT_TYPE")
@VisibleReferred(true)
@OrderNo("10")
@Unchangeable(true)
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

    @Column( name = "NAME" )
    @PersistentFieldGetter( fieldName = "name" )
@VisibleList(true)
@VisibleDetail(true)
@Length("100")
@Required(true)
@VisibleReferred(true)
@OrderNo("20")
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

    @Column( name = "VALID_FROM" )
    @PersistentFieldGetter( fieldName = "validFrom" )
@VisibleList(true)
@VisibleDetail(true)
@Required(true)
@OrderNo("50")
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
@OrderNo("60")
@FormColumn("2")
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

    @Column( name = "PERSON" )
    @PersistentFieldGetter( fieldName = "person" )
@VisibleList(true)
@VisibleDetail(true)
@OrderNo("30")
@VisibleSelector(false)
	/**
	  *  Access method for the person property.
	  *  @return the current value of the person property
	  */
	public java.lang.String getPerson()
	{
		return person;
	}

	/**
	  *  Set method for the person property.
	  */
	public void setPerson( java.lang.String _person )
	{
		person = _person;
	}

    @Column( name = "COMPANY" )
    @PersistentFieldGetter( fieldName = "company" )
@VisibleDetail(true)
@VisibleList(true)
@OrderNo("40")
@VisibleSelector(false)
	/**
	  *  Access method for the company property.
	  *  @return the current value of the company property
	  */
	public java.lang.String getCompany()
	{
		return company;
	}

	/**
	  *  Set method for the company property.
	  */
	public void setCompany( java.lang.String _company )
	{
		company = _company;
	}


	/* Vduped Attribute setters */

	/* Relationship getters & setters */

    @OneToMany( mappedBy = "documentType", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "companyDocument" )
	/**
	  *  Access method for the companyDocuments property.
	  *  @return the current value of the companyDocuments property
	  */
	public Collection<ro.siveco.svapnt.common.entity.CompanyDocument> getCompanyDocuments()
	{
		return companyDocuments;
	}

	/**
	  *  Set method for the companyDocuments property.
	  */
	public void setCompanyDocuments( Collection<ro.siveco.svapnt.common.entity.CompanyDocument> _companyDocuments )
	{
		companyDocuments = _companyDocuments;
	}


    @OneToMany( mappedBy = "documentType", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "personDocument" )
	/**
	  *  Access method for the personDocuments property.
	  *  @return the current value of the personDocuments property
	  */
	public Collection<ro.siveco.svapnt.common.entity.PersonDocument> getPersonDocuments()
	{
		return personDocuments;
	}

	/**
	  *  Set method for the personDocuments property.
	  */
	public void setPersonDocuments( Collection<ro.siveco.svapnt.common.entity.PersonDocument> _personDocuments )
	{
		personDocuments = _personDocuments;
	}


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                                                 
    public static final String NQ_findByCode = "common_DocumentType_findByCode";
    public static DocumentType findByCode( java.lang.String code ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( code);
        return ( DocumentType ) getGenericSession().getSingleResult(NQ_findByCode, params);
    }

	//Unique keys methods with Relations as parameters
	//model defined finders


    public static DocumentType find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( DocumentType.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
