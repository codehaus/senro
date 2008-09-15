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
import ro.siveco.svapnt.configuration.entity.Language;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * constraintNamePK PK_LANGUAGES
 * tableName LANGUAGES
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.configuration.session.LanguageMgrLocal.JNDI_NAME )
public abstract class LanguageBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "configuration_Language";
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

    @Column( name = "DESCRIPTION" )
	private java.lang.String			    description;

    @Column( name = "DEFAULT_LANGUAGE_FLAG" )
	private java.lang.String			    defaultLanguageFlag;

	/* Vduped Attributes */

	/* Relationships */

    @OneToMany( mappedBy = "language", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.configuration.entity.Translation>     translations = new ArrayList();

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
@VisibleList(true)
@VisibleDetail(true)
@OrderNo("1")
@VisibleReferred(true)
@UniqueKey("UK_LANGUAGES")
@Required(true)
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

    @Column( name = "DESCRIPTION" )
    @PersistentFieldGetter( fieldName = "description" )
@Required(true)
@OrderNo("2")
@VisibleDetail(true)
@Length("100")
@VisibleList(true)
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

    @Column( name = "DEFAULT_LANGUAGE_FLAG" )
    @PersistentFieldGetter( fieldName = "defaultLanguageFlag" )
@VisibleList(true)
@Widget("CK")
@OrderNo("3")
@VisibleDetail(true)
@Label("Is default language")
	/**
	  *  Access method for the defaultLanguageFlag property.
	  *  @return the current value of the defaultLanguageFlag property
	  */
	public java.lang.String getDefaultLanguageFlag()
	{
		return defaultLanguageFlag;
	}

	/**
	  *  Set method for the defaultLanguageFlag property.
	  */
	public void setDefaultLanguageFlag( java.lang.String _defaultLanguageFlag )
	{
		defaultLanguageFlag = _defaultLanguageFlag;
	}


	/* Vduped Attribute setters */

	/* Relationship getters & setters */

    @OneToMany( mappedBy = "language", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "translation" )
	/**
	  *  Access method for the translations property.
	  *  @return the current value of the translations property
	  */
	public Collection<ro.siveco.svapnt.configuration.entity.Translation> getTranslations()
	{
		return translations;
	}

	/**
	  *  Set method for the translations property.
	  */
	public void setTranslations( Collection<ro.siveco.svapnt.configuration.entity.Translation> _translations )
	{
		translations = _translations;
	}


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                            
    public static final String NQ_findByCode = "configuration_Language_findByCode";
    public static Language findByCode( java.lang.String code ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( code);
        return ( Language ) getGenericSession().getSingleResult(NQ_findByCode, params);
    }

	//Unique keys methods with Relations as parameters
	//model defined finders


    public static Language find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( Language.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
