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
import ro.siveco.svapnt.common.entity.PersonDocument;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Documente persoane<br>
 * </p>
 * <p>
 * Vezi documentul Dictionar Date<br>
 * </p>
 * <p>
 * Date despre asigurat<br>
 * </p>
 * <p>
 * Informatii de identificare persoane fizice cu studii
 * non-medicale superioare<br>
 * </p>
 *
 * validFrom validFrom
 * validTo validTo
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.common.session.PersonDocumentMgrLocal.JNDI_NAME )
public abstract class PersonDocumentBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "common_PersonDocument";
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

    @Column( name = "NO" )
	private java.lang.String			    no;

    @Column( name = "VALID_FROM" )
	private java.util.Date			    validFrom;

    @Column( name = "VALID_TO" )
	private java.util.Date			    validTo;

    @Column( name = "ISSUER" )
	private java.lang.String			    issuer;

	/* Vduped Attributes */

	/* Relationships */

    @ManyToOne( optional =  false )
    @JoinColumn( name = "PERSON_ID" )
// person
	private ro.siveco.svapnt.common.entity.Person			    person;

    @ManyToOne( optional =  false )
    @JoinColumn( name = "DOCUMENT_TYPE_ID" )
// documentType
	private ro.siveco.svapnt.common.entity.DocumentType			    documentType;

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

    @Column( name = "NO" )
    @PersistentFieldGetter( fieldName = "no" )
@VisibleList(true)
@VisibleDetail(true)
@Required(true)
@Length("100")
@UniqueKey("UK_PERSON_DOC|1")
@OrderNo("20")
	/**
	  *  Access method for the no property.
	  *  @return the current value of the no property
	  */
	public java.lang.String getNo()
	{
		return no;
	}

	/**
	  *  Set method for the no property.
	  */
	public void setNo( java.lang.String _no )
	{
		no = _no;
	}

    @Column( name = "VALID_FROM" )
    @PersistentFieldGetter( fieldName = "validFrom" )
@VisibleDetail(true)
@VisibleList(true)
@Required(true)
@OrderNo("40")
@UniqueKey("UK_PERSON_DOC|2")
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
@OrderNo("50")
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

    @Column( name = "ISSUER" )
    @PersistentFieldGetter( fieldName = "issuer" )
@VisibleList(true)
@VisibleDetail(true)
@Length("100")
@OrderNo("30")
	/**
	  *  Access method for the issuer property.
	  *  @return the current value of the issuer property
	  */
	public java.lang.String getIssuer()
	{
		return issuer;
	}

	/**
	  *  Set method for the issuer property.
	  */
	public void setIssuer( java.lang.String _issuer )
	{
		issuer = _issuer;
	}


	/* Vduped Attribute setters */

	/* Relationship getters & setters */
    @PersistentToOneGetter( relName = "person" )

    @ManyToOne( optional =  false )
    @JoinColumn( name = "PERSON_ID" )
// person
    @UniqueKey
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

    @PersistentToOneGetter( relName = "documentType" )

    @ManyToOne( optional =  false )
    @JoinColumn( name = "DOCUMENT_TYPE_ID" )
// documentType
    @UniqueKey
	/**
	  *  Access method for the documentType property.
	  *  @return the current value of the documentType property
	  */
	public ro.siveco.svapnt.common.entity.DocumentType getDocumentType()
	{
		return documentType;
	}

	/**
	  *  Set method for the documentType property.
	  */
	public void setDocumentType( ro.siveco.svapnt.common.entity.DocumentType _documentType )
	{
		documentType = _documentType;
	}


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
    public static final String NQ_findByNoAndValidFromAndPersonAndDocumentType = "common_PersonDocument_findByNoAndValidFromAndPersonAndDocumentType";
    public static PersonDocument findByNoAndValidFromAndPersonAndDocumentType( java.lang.String no, java.util.Date validFrom, ro.siveco.svapnt.common.entity.Person person, ro.siveco.svapnt.common.entity.DocumentType documentType ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( no);
                params.add( validFrom);
                params.add(person.getId());
                params.add(documentType.getId());
        return ( PersonDocument ) getGenericSession().getSingleResult(NQ_findByNoAndValidFromAndPersonAndDocumentType, params);
    }

	//Unique keys methods with Relations as parameters
	//model defined finders


    public static PersonDocument find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( PersonDocument.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
