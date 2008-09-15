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
import ro.siveco.svapnt.common.entity.PersonalIdCard;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Acte de identititate persoane fizice si salariati
 * </p>
 * <p>
 * <br>Vezi documentul Dictionar Date<br>
 * </p>
 * <p>
 * Informatii de identificare persoane fizice cu studii
 * non-medicale superioare<br>
 * </p>
 *
 * constraintNamePK PK_PERS_CARDS
 * tableName PERS_CARDS
 * checkValability issueDate
 * validFrom issueDate
 * validTo endDate
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.common.session.PersonIdCardMgrLocal.JNDI_NAME )
public abstract class PersonalIdCardBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "common_PersonalIdCard";
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

    @Column( name = "SERIES" )
	private java.lang.String			    series;

    @Column( name = "ID_NUMBER" )
	private java.lang.String			    idNumber;

    @Column( name = "ISSUE_DATE" )
	private java.util.Date			    issueDate;

    @Column( name = "EXP_DATE" )
	private java.util.Date			    expDate;

    @Column( name = "END_DATE" )
	private java.util.Date			    endDate;

    @Column( name = "ISSUED_BY" )
	private java.lang.String			    issuedBy;

    @Column( name = "DESCRIPTION" )
	private java.lang.String			    description;

	/* Vduped Attributes */

	/* Relationships */

    @ManyToOne( optional =  false )
    @JoinColumn( name = "PERS_CARD_TYPE_ID" )
// personalIDCardType
	private ro.siveco.svapnt.common.entity.PersonalIDCardType			    personalIDCardType;

    @ManyToOne( optional =  false )
    @JoinColumn( name = "PERSON_ID" )
// person
	private ro.siveco.svapnt.common.entity.Person			    person;

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

    @Column( name = "SERIES" )
    @PersistentFieldGetter( fieldName = "series" )
@UniqueKey("UK_PERS_CARDS")
@OrderNo("10")
@VisibleList(true)
@Length("20")
@VisibleDetail(true)
@VisibleReferred(true)
@VisibleSelector(true)
@FormColumn("0")
	/**
	  *  Access method for the series property.
	  *  @return the current value of the series property
	  */
	public java.lang.String getSeries()
	{
		return series;
	}

	/**
	  *  Set method for the series property.
	  */
	public void setSeries( java.lang.String _series )
	{
		series = _series;
	}

    @Column( name = "ID_NUMBER" )
    @PersistentFieldGetter( fieldName = "idNumber" )
@VisibleSelector(true)
@Required(true)
@VisibleDetail(true)
@VisibleList(true)
@VisibleReferred(true)
@Length("20")
@UniqueKey("UK_PERS_CARDS")
@OrderNo("20")
	/**
	  *  Access method for the idNumber property.
	  *  @return the current value of the idNumber property
	  */
	public java.lang.String getIdNumber()
	{
		return idNumber;
	}

	/**
	  *  Set method for the idNumber property.
	  */
	public void setIdNumber( java.lang.String _idNumber )
	{
		idNumber = _idNumber;
	}

    @Column( name = "ISSUE_DATE" )
    @PersistentFieldGetter( fieldName = "issueDate" )
@VisibleDetail(true)
@VisibleReferred(true)
@Required(true)
@VisibleList(true)
@OrderNo("60")
@FormColumn("0")
	/**
	  *  Access method for the issueDate property.
	  *  @return the current value of the issueDate property
	  */
	public java.util.Date getIssueDate()
	{
		return issueDate;
	}

	/**
	  *  Set method for the issueDate property.
	  */
	public void setIssueDate( java.util.Date _issueDate )
	{
		issueDate = _issueDate;
	}

    @Column( name = "EXP_DATE" )
    @PersistentFieldGetter( fieldName = "expDate" )
@VisibleDetail(true)
@VisibleList(true)
@OrderNo("50")
	/**
	  *  Access method for the expDate property.
	  *  @return the current value of the expDate property
	  */
	public java.util.Date getExpDate()
	{
		return expDate;
	}

	/**
	  *  Set method for the expDate property.
	  */
	public void setExpDate( java.util.Date _expDate )
	{
		expDate = _expDate;
	}

    @Column( name = "END_DATE" )
    @PersistentFieldGetter( fieldName = "endDate" )
@VisibleDetail(true)
@OrderNo("70")
@VisibleList(true)
	/**
	  *  Access method for the endDate property.
	  *  @return the current value of the endDate property
	  */
	public java.util.Date getEndDate()
	{
		return endDate;
	}

	/**
	  *  Set method for the endDate property.
	  */
	public void setEndDate( java.util.Date _endDate )
	{
		endDate = _endDate;
	}

    @Column( name = "ISSUED_BY" )
    @PersistentFieldGetter( fieldName = "issuedBy" )
@VisibleList(true)
@Length("50")
@VisibleDetail(true)
@OrderNo("30")
	/**
	  *  Access method for the issuedBy property.
	  *  @return the current value of the issuedBy property
	  */
	public java.lang.String getIssuedBy()
	{
		return issuedBy;
	}

	/**
	  *  Set method for the issuedBy property.
	  */
	public void setIssuedBy( java.lang.String _issuedBy )
	{
		issuedBy = _issuedBy;
	}

    @Column( name = "DESCRIPTION" )
    @PersistentFieldGetter( fieldName = "description" )
@VisibleLength("30")
@VisibleList(true)
@Length("255")
@OrderNo("40")
@VisibleDetail(true)
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


	/* Vduped Attribute setters */

	/* Relationship getters & setters */
    @PersistentToOneGetter( relName = "personalIDCardType" )

    @ManyToOne( optional =  false )
    @JoinColumn( name = "PERS_CARD_TYPE_ID" )
// personalIDCardType
    @UniqueKey
	/**
	  *  Access method for the personalIDCardType property.
	  *  @return the current value of the personalIDCardType property
	  */
	public ro.siveco.svapnt.common.entity.PersonalIDCardType getPersonalIDCardType()
	{
		return personalIDCardType;
	}

	/**
	  *  Set method for the personalIDCardType property.
	  */
	public void setPersonalIDCardType( ro.siveco.svapnt.common.entity.PersonalIDCardType _personalIDCardType )
	{
		personalIDCardType = _personalIDCardType;
	}

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


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                                                                                                                                                                                                                                                                        
    public static final String NQ_findBySeriesAndIdNumberAndPersonalIDCardType = "common_PersonalIdCard_findBySeriesAndIdNumberAndPersonalIDCardType";
    public static PersonalIdCard findBySeriesAndIdNumberAndPersonalIDCardType( java.lang.String series, java.lang.String idNumber, ro.siveco.svapnt.common.entity.PersonalIDCardType personalIDCardType ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( series);
                params.add( idNumber);
                params.add(personalIDCardType.getId());
        return ( PersonalIdCard ) getGenericSession().getSingleResult(NQ_findBySeriesAndIdNumberAndPersonalIDCardType, params);
    }

	//Unique keys methods with Relations as parameters
	//model defined finders


    public static PersonalIdCard find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( PersonalIdCard.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
