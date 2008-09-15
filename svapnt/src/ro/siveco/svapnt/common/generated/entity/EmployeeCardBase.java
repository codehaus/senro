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
import ro.siveco.svapnt.common.entity.EmployeeCard;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Defineste legitimatiile sau alte acte care atesta calitatea de
 * angajat<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * constraintNamePK PK_EMP_CARDS
 * checkValability issueDate
 * validTo endDate
 * validFrom issueDate
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.common.session.EmployeeCardMgrLocal.JNDI_NAME )
public abstract class EmployeeCardBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "common_EmployeeCard";
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

    @Column( name = "CARD_NUMBER" )
	private java.lang.String			    cardNumber;

    @Column( name = "ISSUE_DATE" )
	private java.util.Date			    issueDate;

    @Column( name = "END_DATE" )
	private java.util.Date			    endDate;

    @Column( name = "DESCRIPTION" )
	private java.lang.String			    description;

	/* Vduped Attributes */

	/* Relationships */

    @ManyToOne( optional =  false )
    @JoinColumn( name = "PERSONAL_IDCARD_TYPE_ID" )
// personalIDCardType
	private ro.siveco.svapnt.common.entity.PersonalIDCardType			    personalIDCardType;

    @ManyToOne( optional =  false )
    @JoinColumn( name = "EMPLOYEE_ID" )
// employee
	private ro.siveco.svapnt.common.entity.Employee			    employee;

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

    @Column( name = "CARD_NUMBER" )
    @PersistentFieldGetter( fieldName = "cardNumber" )
@VisibleSelector(true)
@Required(true)
@VisibleDetail(true)
@VisibleList(true)
@VisibleReferred(true)
@Length("20")
@UniqueKey("UK_EMP_CARDS")
@OrderNo("20")
	/**
	  *  Access method for the cardNumber property.
	  *  @return the current value of the cardNumber property
	  */
	public java.lang.String getCardNumber()
	{
		return cardNumber;
	}

	/**
	  *  Set method for the cardNumber property.
	  */
	public void setCardNumber( java.lang.String _cardNumber )
	{
		cardNumber = _cardNumber;
	}

    @Column( name = "ISSUE_DATE" )
    @PersistentFieldGetter( fieldName = "issueDate" )
@VisibleDetail(true)
@VisibleReferred(true)
@Required(true)
@VisibleList(true)
@OrderNo("40")
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

    @Column( name = "END_DATE" )
    @PersistentFieldGetter( fieldName = "endDate" )
@VisibleDetail(true)
@OrderNo("50")
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

    @Column( name = "DESCRIPTION" )
    @PersistentFieldGetter( fieldName = "description" )
@VisibleLength("30")
@VisibleList(true)
@Length("255")
@OrderNo("30")
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
    @JoinColumn( name = "PERSONAL_IDCARD_TYPE_ID" )
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

    @PersistentToOneGetter( relName = "employee" )

    @ManyToOne( optional =  false )
    @JoinColumn( name = "EMPLOYEE_ID" )
// employee
	/**
	  *  Access method for the employee property.
	  *  @return the current value of the employee property
	  */
	public ro.siveco.svapnt.common.entity.Employee getEmployee()
	{
		return employee;
	}

	/**
	  *  Set method for the employee property.
	  */
	public void setEmployee( ro.siveco.svapnt.common.entity.Employee _employee )
	{
		employee = _employee;
	}


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                                                                                                                                                                                  
    public static final String NQ_findByCardNumberAndPersonalIDCardType = "common_EmployeeCard_findByCardNumberAndPersonalIDCardType";
    public static EmployeeCard findByCardNumberAndPersonalIDCardType( java.lang.String cardNumber, ro.siveco.svapnt.common.entity.PersonalIDCardType personalIDCardType ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( cardNumber);
                params.add(personalIDCardType.getId());
        return ( EmployeeCard ) getGenericSession().getSingleResult(NQ_findByCardNumberAndPersonalIDCardType, params);
    }

	//Unique keys methods with Relations as parameters
	//model defined finders


    public static EmployeeCard find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( EmployeeCard.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
