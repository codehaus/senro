/*
 * Warning: Generated code! Do not modify!
 * Atentiune: Cod sursa generat! Nu modifica!
 * Source template: EntityBaseJava.vsl
 * Source template version: $Rel$
 */
package ro.siveco.svapnt.configuration.generated.entity;

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
import ro.siveco.svapnt.configuration.entity.Translation;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Traduceri pentru date<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * constraintNamePK PK_TRANSLATIONS
 * tableName TRANSLATIONS
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.configuration.session.TranslationMgrLocal.JNDI_NAME )
public abstract class TranslationBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "configuration_Translation";
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

    @Column( name = "COLUMN_TRANSLATED" )
	private java.lang.String			    columnTranslated;

    @Column( name = "ID_ROW" )
	private java.lang.String			    idRow;

	/* Vduped Attributes */

	/* Relationships */

    @ManyToOne( optional =  false )
    @JoinColumn( name = "LANGUAGE_ID" )
// language
	private ro.siveco.svapnt.configuration.entity.Language			    language;

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

    @Column( name = "COLUMN_TRANSLATED" )
    @PersistentFieldGetter( fieldName = "columnTranslated" )
@OrderNo("2")
@VisibleDetail(true)
@Required(true)
@Length("2000")
@VisibleList(true)
@Widget("TA")
	/**
	  *  Access method for the columnTranslated property.
	  *  @return the current value of the columnTranslated property
	  */
	public java.lang.String getColumnTranslated()
	{
		return columnTranslated;
	}

	/**
	  *  Set method for the columnTranslated property.
	  */
	public void setColumnTranslated( java.lang.String _columnTranslated )
	{
		columnTranslated = _columnTranslated;
	}

    @Column( name = "ID_ROW" )
    @PersistentFieldGetter( fieldName = "idRow" )
@Length("100")
@Required(true)
	/**
	  *  Access method for the idRow property.
	  *  @return the current value of the idRow property
	  */
	public java.lang.String getIdRow()
	{
		return idRow;
	}

	/**
	  *  Set method for the idRow property.
	  */
	public void setIdRow( java.lang.String _idRow )
	{
		idRow = _idRow;
	}


	/* Vduped Attribute setters */

	/* Relationship getters & setters */
    @PersistentToOneGetter( relName = "language" )

    @ManyToOne( optional =  false )
    @JoinColumn( name = "LANGUAGE_ID" )
// language
    @UniqueKey
	/**
	  *  Access method for the language property.
	  *  @return the current value of the language property
	  */
	public ro.siveco.svapnt.configuration.entity.Language getLanguage()
	{
		return language;
	}

	/**
	  *  Set method for the language property.
	  */
	public void setLanguage( ro.siveco.svapnt.configuration.entity.Language _language )
	{
		language = _language;
	}


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                                                    
	//Unique keys methods with Relations as parameters

    public static final String NQ_findByLanguage = "configuration_Translation_UK_TRANSLATIONS_findByLanguage";
    public static Translation findByLanguage( ro.siveco.svapnt.configuration.entity.Language language ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add(language.getId());
        return ( Translation ) getGenericSession().getSingleResult(NQ_findByLanguage, params);
    }

	//model defined finders


    public static Translation find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( Translation.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
