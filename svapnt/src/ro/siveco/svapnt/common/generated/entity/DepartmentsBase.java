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
import ro.siveco.svapnt.common.entity.Departments;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Departamente - derivat din structuri organizationale<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * entityID 2
 * validFrom validFrom
 * validTo validTo
 *
 */

 

 
@MappedSuperclass
public abstract class DepartmentsBase extends ro.siveco.svapnt.common.entity.OrgUnit implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "common_Departments";
	    public static String getEntityName()
	    {
	        return ENTITY_NAME;
	    }

	/* Attributes */

	/* Vduped Attributes */

    @Column( name = "CODE" )
	private java.lang.String			    vdupDepartments_code;

    @Column( name = "NAME" )
	private java.lang.String			    vdupDepartments_name;

	/* Relationships */

	/* Attribute getters & setters */

	/* Vduped Attribute setters */
	/**
	  *  Set method for the duplicated code property.
	  */
	public void setCode( java.lang.String _code )
	{
	    super.setCode( _code );
		vdupDepartments_code = _code;
	}

	/**
	  *  Set method for the duplicated name property.
	  */
	public void setName( java.lang.String _name )
	{
	    super.setName( _name );
		vdupDepartments_name = _name;
	}


	/* Relationship getters & setters */

/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                                                                                                                                                                          
    public static final String NQ_findByCode = "common_Departments_findByCode";
    public static Departments findByCode( java.lang.String code ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( code);
        return ( Departments ) getGenericSession().getSingleResult(NQ_findByCode, params);
    }

	//Unique keys methods with Relations as parameters
	//model defined finders


    public static Departments find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( Departments.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
