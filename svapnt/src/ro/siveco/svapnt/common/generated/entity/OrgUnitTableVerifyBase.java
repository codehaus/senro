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
import ro.siveco.svapnt.common.entity.OrgUnitTableVerify;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Contine tabele in care se verifica valabilitatea structurilor
 * organizatorice (in cazul in care se modifica valabilitatea unei
 * structuri organizatorice, se verifica incadrarea datelor
 * tranzactiilor efectuate pe structura respectiva in noul interval
 * de valabiliatate)<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * tableName ORG_UNIT_TABLE_VERIFYS
 * constraintNamePK PK_OU_TBL_VRF
 *
 */

 

 
@MappedSuperclass
public abstract class OrgUnitTableVerifyBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "common_OrgUnitTableVerify";
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

    @Column( name = "TABLE_NAME" )
	private java.lang.String			    tableName;

    @Column( name = "TABLE_LINK_NAME" )
	private java.lang.String			    tableLinkName;

    @Column( name = "TABLES_LINK" )
	private java.lang.String			    tablesLink;

    @Column( name = "ORG_UNIT_COLUMN" )
	private java.lang.String			    orgUnitColumn;

    @Column( name = "DATE_COLUMN" )
	private java.lang.String			    dateColumn;

    @Column( name = "CONSTRAINT_TYPE" )
	private java.lang.String			    constraintType;

	/* Vduped Attributes */

	/* Relationships */

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

    @Column( name = "TABLE_NAME" )
    @PersistentFieldGetter( fieldName = "tableName" )
@Length("100")
	/**
	  *  Access method for the tableName property.
	  *  @return the current value of the tableName property
	  */
	public java.lang.String getTableName()
	{
		return tableName;
	}

	/**
	  *  Set method for the tableName property.
	  */
	public void setTableName( java.lang.String _tableName )
	{
		tableName = _tableName;
	}

    @Column( name = "TABLE_LINK_NAME" )
    @PersistentFieldGetter( fieldName = "tableLinkName" )
@Length("100")
	/**
	  *  Access method for the tableLinkName property.
	  *  @return the current value of the tableLinkName property
	  */
	public java.lang.String getTableLinkName()
	{
		return tableLinkName;
	}

	/**
	  *  Set method for the tableLinkName property.
	  */
	public void setTableLinkName( java.lang.String _tableLinkName )
	{
		tableLinkName = _tableLinkName;
	}

    @Column( name = "TABLES_LINK" )
    @PersistentFieldGetter( fieldName = "tablesLink" )
@Length("100")
	/**
	  *  Access method for the tablesLink property.
	  *  @return the current value of the tablesLink property
	  */
	public java.lang.String getTablesLink()
	{
		return tablesLink;
	}

	/**
	  *  Set method for the tablesLink property.
	  */
	public void setTablesLink( java.lang.String _tablesLink )
	{
		tablesLink = _tablesLink;
	}

    @Column( name = "ORG_UNIT_COLUMN" )
    @PersistentFieldGetter( fieldName = "orgUnitColumn" )
@Length("100")
	/**
	  *  Access method for the orgUnitColumn property.
	  *  @return the current value of the orgUnitColumn property
	  */
	public java.lang.String getOrgUnitColumn()
	{
		return orgUnitColumn;
	}

	/**
	  *  Set method for the orgUnitColumn property.
	  */
	public void setOrgUnitColumn( java.lang.String _orgUnitColumn )
	{
		orgUnitColumn = _orgUnitColumn;
	}

    @Column( name = "DATE_COLUMN" )
    @PersistentFieldGetter( fieldName = "dateColumn" )
@Length("100")
	/**
	  *  Access method for the dateColumn property.
	  *  @return the current value of the dateColumn property
	  */
	public java.lang.String getDateColumn()
	{
		return dateColumn;
	}

	/**
	  *  Set method for the dateColumn property.
	  */
	public void setDateColumn( java.lang.String _dateColumn )
	{
		dateColumn = _dateColumn;
	}

    @Column( name = "CONSTRAINT_TYPE" )
    @PersistentFieldGetter( fieldName = "constraintType" )
@Length("100")
	/**
	  *  Access method for the constraintType property.
	  *  @return the current value of the constraintType property
	  */
	public java.lang.String getConstraintType()
	{
		return constraintType;
	}

	/**
	  *  Set method for the constraintType property.
	  */
	public void setConstraintType( java.lang.String _constraintType )
	{
		constraintType = _constraintType;
	}


	/* Vduped Attribute setters */

	/* Relationship getters & setters */

/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                              
	//Unique keys methods with Relations as parameters
	//model defined finders


    public static OrgUnitTableVerify find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( OrgUnitTableVerify.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
