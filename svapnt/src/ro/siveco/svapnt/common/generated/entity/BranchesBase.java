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
import ro.siveco.svapnt.common.entity.Branches;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Sucursale<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in dictionarul de
 * date.
 * </p>
 *
 * entityID 1
 * validFrom validFrom
 * validTo validTo
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.common.session.BranchesMgrLocal.JNDI_NAME )
public abstract class BranchesBase extends ro.siveco.svapnt.common.entity.OrgUnit implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "common_Branches";
	    public static String getEntityName()
	    {
	        return ENTITY_NAME;
	    }

	/* Attributes */

	/* Vduped Attributes */

    @Column( name = "CODE" )
	private java.lang.String			    vdupBranches_code;

    @Column( name = "NAME" )
	private java.lang.String			    vdupBranches_name;

	/* Relationships */

	/* Attribute getters & setters */

	/* Vduped Attribute setters */
	/**
	  *  Set method for the duplicated code property.
	  */
	public void setCode( java.lang.String _code )
	{
	    super.setCode( _code );
		vdupBranches_code = _code;
	}

	/**
	  *  Set method for the duplicated name property.
	  */
	public void setName( java.lang.String _name )
	{
	    super.setName( _name );
		vdupBranches_name = _name;
	}


	/* Relationship getters & setters */

/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                                                                                                                                                                          
    public static final String NQ_findByCode = "common_Branches_findByCode";
    public static Branches findByCode( java.lang.String code ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( code);
        return ( Branches ) getGenericSession().getSingleResult(NQ_findByCode, params);
    }

	//Unique keys methods with Relations as parameters
	//model defined finders


    public static Branches find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( Branches.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
