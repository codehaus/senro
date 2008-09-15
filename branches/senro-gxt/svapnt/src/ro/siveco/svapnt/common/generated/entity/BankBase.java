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
import ro.siveco.svapnt.common.entity.Bank;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Banci<br>
 * </p>
 * <p>
 * Vezi documentul dictionar de date<>
 * </p>
 * <p>
 * Catalogul de banci
 * </p>
 *
 * constraintNamePK PK_BANKS
 * tableName BANKS
 * entityID 1
 * validFrom validFrom
 * validTo validTo
 * entityLevel entityLevel
 * permanentLog true
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.common.session.BankMgrLocal.JNDI_NAME )
public abstract class BankBase extends ro.siveco.svapnt.common.entity.Company implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "common_Bank";
	    public static String getEntityName()
	    {
	        return ENTITY_NAME;
	    }

	/* Attributes */

    @Column( name = "BRANCH" )
	private java.lang.String			    branch;

    @Column( name = "SWIFT_INT_CODE" )
	private java.lang.String			    swiftIntCode;

    @Column( name = "SWIFT_NAT_CODE" )
	private java.lang.String			    swiftNatCode;

	/* Vduped Attributes */

    @Column( name = "CODE" )
	private java.lang.String			    vdupBank_code;

    @Column( name = "NAME" )
	private java.lang.String			    vdupBank_name;

	/* Relationships */

    @OneToMany( mappedBy = "bank", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.common.entity.BankAccount>     accounts = new ArrayList();

	/* Attribute getters & setters */
    @Column( name = "BRANCH" )
    @PersistentFieldGetter( fieldName = "branch" )
@VisibleDetail(true)
@Length("100")
@VisibleList(true)
@OrderNo("30")
	/**
	  *  Access method for the branch property.
	  *  @return the current value of the branch property
	  */
	public java.lang.String getBranch()
	{
		return branch;
	}

	/**
	  *  Set method for the branch property.
	  */
	public void setBranch( java.lang.String _branch )
	{
		branch = _branch;
	}

    @Column( name = "SWIFT_INT_CODE" )
    @PersistentFieldGetter( fieldName = "swiftIntCode" )
@VisibleDetail(true)
@OrderNo("40")
@Length("20")
	/**
	  *  Access method for the swiftIntCode property.
	  *  @return the current value of the swiftIntCode property
	  */
	public java.lang.String getSwiftIntCode()
	{
		return swiftIntCode;
	}

	/**
	  *  Set method for the swiftIntCode property.
	  */
	public void setSwiftIntCode( java.lang.String _swiftIntCode )
	{
		swiftIntCode = _swiftIntCode;
	}

    @Column( name = "SWIFT_NAT_CODE" )
    @PersistentFieldGetter( fieldName = "swiftNatCode" )
@VisibleDetail(true)
@OrderNo("50")
@Length("20")
	/**
	  *  Access method for the swiftNatCode property.
	  *  @return the current value of the swiftNatCode property
	  */
	public java.lang.String getSwiftNatCode()
	{
		return swiftNatCode;
	}

	/**
	  *  Set method for the swiftNatCode property.
	  */
	public void setSwiftNatCode( java.lang.String _swiftNatCode )
	{
		swiftNatCode = _swiftNatCode;
	}


	/* Vduped Attribute setters */
	/**
	  *  Set method for the duplicated code property.
	  */
	public void setCode( java.lang.String _code )
	{
	    super.setCode( _code );
		vdupBank_code = _code;
	}

	/**
	  *  Set method for the duplicated name property.
	  */
	public void setName( java.lang.String _name )
	{
	    super.setName( _name );
		vdupBank_name = _name;
	}


	/* Relationship getters & setters */

    @OneToMany( mappedBy = "bank", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "accounts" )
	/**
	  *  Access method for the accounts property.
	  *  @return the current value of the accounts property
	  */
	public Collection<ro.siveco.svapnt.common.entity.BankAccount> getAccounts()
	{
		return accounts;
	}

	/**
	  *  Set method for the accounts property.
	  */
	public void setAccounts( Collection<ro.siveco.svapnt.common.entity.BankAccount> _accounts )
	{
		accounts = _accounts;
	}


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
    public static final String NQ_findByCode = "common_Bank_findByCode";
    public static Bank findByCode( java.lang.String code ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( code);
        return ( Bank ) getGenericSession().getSingleResult(NQ_findByCode, params);
    }

    public static final String NQ_findByUic = "common_Bank_findByUic";
    public static Bank findByUic( java.lang.String uic ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( uic);
        return ( Bank ) getGenericSession().getSingleResult(NQ_findByUic, params);
    }

	//Unique keys methods with Relations as parameters
	//model defined finders


    public static Bank find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( Bank.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
