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
import ro.siveco.svapnt.configuration.entity.Module;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Module<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * constraintNamePK PK_MODULES
 * tableName MODULES
 * tree true
 * treeDisplay code
 * treeTooltip description
 * treeLeftBound leftBound
 * treeRightBound rightBound
 * entityLevel entityLevel
 * noDelete true
 * noInsert true
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.configuration.session.ModuleMgrLocal.JNDI_NAME )
public abstract class ModuleBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "configuration_Module";
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

    @Column( name = "LEFT_BOUND" )
	private java.lang.Double			    leftBound;

    @Column( name = "RIGHT_BOUND" )
	private java.lang.Double			    rightBound;

    @Column( name = "ENTITY_LEVEL" )
	private java.lang.Long			    entityLevel;

    @Column( name = "IS_LOGGED" )
	private java.lang.String			    isLogged;

    @Column( name = "TYPE" )
	private java.lang.String			    type;

	/* Vduped Attributes */

	/* Relationships */

    @ManyToOne( optional =  true )
    @JoinColumn( name = "PARENT_ID" )
// parent
	private ro.siveco.svapnt.configuration.entity.Module			    parent;

    @OneToMany( mappedBy = "parent", cascade = ALL )
	private Collection<ro.siveco.svapnt.configuration.entity.Module>     children = new ArrayList();

    @OneToMany( mappedBy = "module", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.configuration.entity.ModuleRight>     moduleRights = new ArrayList();

    @OneToMany( mappedBy = "module", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.configuration.entity.Parameter>     parameters = new ArrayList();

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
@OrderNo("30")
@VisibleReferred(true)
@UniqueKey("UK_MODULES")
@Required(true)
@Length("100")
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

    @Column( name = "DESCRIPTION" )
    @PersistentFieldGetter( fieldName = "description" )
@Required(true)
@OrderNo("10")
@VisibleDetail(true)
@Length("200")
@VisibleList(true)
@Widget("TA")
@Unchangeable(true)
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

    @Column( name = "LEFT_BOUND" )
    @PersistentFieldGetter( fieldName = "leftBound" )
	/**
	  *  Access method for the leftBound property.
	  *  @return the current value of the leftBound property
	  */
	public java.lang.Double getLeftBound()
	{
		return leftBound;
	}

	/**
	  *  Set method for the leftBound property.
	  */
	public void setLeftBound( java.lang.Double _leftBound )
	{
		leftBound = _leftBound;
	}

    @Column( name = "RIGHT_BOUND" )
    @PersistentFieldGetter( fieldName = "rightBound" )
	/**
	  *  Access method for the rightBound property.
	  *  @return the current value of the rightBound property
	  */
	public java.lang.Double getRightBound()
	{
		return rightBound;
	}

	/**
	  *  Set method for the rightBound property.
	  */
	public void setRightBound( java.lang.Double _rightBound )
	{
		rightBound = _rightBound;
	}

    @Column( name = "ENTITY_LEVEL" )
    @PersistentFieldGetter( fieldName = "entityLevel" )
	/**
	  *  Access method for the entityLevel property.
	  *  @return the current value of the entityLevel property
	  */
	public java.lang.Long getEntityLevel()
	{
		return entityLevel;
	}

	/**
	  *  Set method for the entityLevel property.
	  */
	public void setEntityLevel( java.lang.Long _entityLevel )
	{
		entityLevel = _entityLevel;
	}

    @Column( name = "IS_LOGGED" )
    @PersistentFieldGetter( fieldName = "isLogged" )
@Widget("CK")
@VisibleList(true)
@VisibleDetail(true)
@OrderNo("40")
	/**
	  *  Access method for the isLogged property.
	  *  @return the current value of the isLogged property
	  */
	public java.lang.String getIsLogged()
	{
		return isLogged;
	}

	/**
	  *  Set method for the isLogged property.
	  */
	public void setIsLogged( java.lang.String _isLogged )
	{
		isLogged = _isLogged;
	}

    @Column( name = "TYPE" )
    @PersistentFieldGetter( fieldName = "type" )
@Widget("CB")
@VisibleDetail(true)
@VisibleList(true)
@VisibleSelector(true)
@WidgetValues("menu|menu;submenu|submenu;list_form|list/form;tab_page|tab page;button|button")
@Unchangeable(true)
@VisibleReferred(true)
@Length("15")
@OrderNo("20")
	/**
	  *  Access method for the type property.
	  *  @return the current value of the type property
	  */
	public java.lang.String getType()
	{
		return type;
	}

	/**
	  *  Set method for the type property.
	  */
	public void setType( java.lang.String _type )
	{
		type = _type;
	}


	/* Vduped Attribute setters */

	/* Relationship getters & setters */
    @PersistentToOneGetter( relName = "parent" )

    @ManyToOne( optional =  true )
    @JoinColumn( name = "PARENT_ID" )
// parent
    @VisibleReferred
	/**
	  *  Access method for the parent property.
	  *  @return the current value of the parent property
	  */
	public ro.siveco.svapnt.configuration.entity.Module getParent()
	{
		return parent;
	}

	/**
	  *  Set method for the parent property.
	  */
	public void setParent( ro.siveco.svapnt.configuration.entity.Module _parent )
	{
		parent = _parent;
	}


    @OneToMany( mappedBy = "parent", cascade = ALL )
    @Transactional
    @PersistentToManyGetter( relName = "child" )
	/**
	  *  Access method for the children property.
	  *  @return the current value of the children property
	  */
	public Collection<ro.siveco.svapnt.configuration.entity.Module> getChildren()
	{
		return children;
	}

	/**
	  *  Set method for the children property.
	  */
	public void setChildren( Collection<ro.siveco.svapnt.configuration.entity.Module> _children )
	{
		children = _children;
	}


    @OneToMany( mappedBy = "module", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "moduleRight" )
	/**
	  *  Access method for the moduleRights property.
	  *  @return the current value of the moduleRights property
	  */
	public Collection<ro.siveco.svapnt.configuration.entity.ModuleRight> getModuleRights()
	{
		return moduleRights;
	}

	/**
	  *  Set method for the moduleRights property.
	  */
	public void setModuleRights( Collection<ro.siveco.svapnt.configuration.entity.ModuleRight> _moduleRights )
	{
		moduleRights = _moduleRights;
	}


    @OneToMany( mappedBy = "module", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "parameter" )
	/**
	  *  Access method for the parameters property.
	  *  @return the current value of the parameters property
	  */
	public Collection<ro.siveco.svapnt.configuration.entity.Parameter> getParameters()
	{
		return parameters;
	}

	/**
	  *  Set method for the parameters property.
	  */
	public void setParameters( Collection<ro.siveco.svapnt.configuration.entity.Parameter> _parameters )
	{
		parameters = _parameters;
	}


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                                                                     
    public static final String NQ_findByCode = "configuration_Module_findByCode";
    public static Module findByCode( java.lang.String code ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( code);
        return ( Module ) getGenericSession().getSingleResult(NQ_findByCode, params);
    }

	//Unique keys methods with Relations as parameters
	//model defined finders


    public static Module find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( Module.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/


//[start] Tree finders

	/**
	 *  Returns true if vModule has children nodes
	 */
	public static <T extends GenericEntity> boolean hasChildren( T entity ) throws DAOException
	{
		Module vModule = (Module) entity;
		return (getGenericSession().findMultiple(Module.class, "parent = "+vModule.getId().toString()).size() > 0);
	}

	/**
	 *  Returns a vector with children based on the ID of the node
	 */
	public static <T extends GenericEntity> Vector<T> findChildren( String ID ) throws DAOException
	{
		if (!StringTool.nullOrBlank(ID))
			return new Vector(getGenericSession().findMultiple(Module.class, "parent = "+ID));
	   	else
			return new Vector(getGenericSession().findMultiple(Module.class, "parent is null "));
	}

	/**
	 *  Returns a vector with all roots
	 */
	public static <T extends GenericEntity> Vector<T> findAllRoots() throws DAOException
	{
		return new Vector(getGenericSession().findMultiple(Module.class, "parent is null"));
	}

	/**
	 *  Returns a vector with all children
	 */
	public static <T extends GenericEntity,K extends GenericEntity> Vector<T> findAllChildren( K entity ) throws DAOException
	{
		Module vModule = (Module) entity;
		return new Vector(getGenericSession().findMultiple(Module.class, "leftBound between "+vModule.getLeftBound()+" and "+vModule.getRightBound()));
	}

	/**
	 * Returns the next root, based on the current one
	 */
	public static <T extends GenericEntity,K extends GenericEntity> T findNextRoot( K entity ) throws DAOException
	{
		Module vModule = (Module) entity;
		for( T root: (Collection<T>)getGenericSession().findMultiple(Module.class, "parent is null and id > "+vModule.getId()+" order by id"))
            return root;
        return null;
	}

		/**
	 * Returns a vector of brothers, for the current entity
	 */
	public static <T extends GenericEntity,K extends GenericEntity> Vector<T> findBrothers( K entity ) throws DAOException
	{
		Module vModule = (Module) entity;
		if (vModule.getParent() != null)
		{
			if (vModule.getParent().getId() != null)
				return new Vector(getGenericSession().findMultiple(Module.class, "parent = "+vModule.getParent().getId()+" and not (id="+vModule.getId()+")"));
			else
				return new Vector(getGenericSession().findMultiple(Module.class, "parent = "+vModule.getParent().getId()));
		}
		else
		{
			if (vModule.getId() != null)
				return new Vector(getGenericSession().findMultiple(Module.class, "parent is null and not (id="+vModule.getId()+")"));
			else
				return new Vector(getGenericSession().findMultiple(Module.class, "parent is null"));
		}
	}

	/**
	 * Returns the first (leftmost) child of an entity
	 */
	public static <T extends GenericEntity,K extends GenericEntity> T findFirstChild( K entity ) throws DAOException
	{
		Module vModule = (Module) entity;
		for( T child: (Collection<T>)getGenericSession().findMultiple(Module.class, "parent="+vModule.getId()+" order by leftBound"))
            return child;
        return null;
	}

	/**
	 * Returns the last child of an entity
	 */
	public static <T extends GenericEntity,K extends GenericEntity> T findLastChild( K entity ) throws DAOException
	{
		Module vModule = (Module) entity;
		for( T child: (Collection<T>)getGenericSession().findMultiple(Module.class, "parent="+vModule.getId()+" order by leftBound desc"))
            return child;
        return null;
	}

	/**
	 * First (leftmost) root in the tree
	 */
	public static <T extends GenericEntity> T findFirstRoot() throws DAOException
	{
		for( T root: (Collection<T>)getGenericSession().findMultiple(Module.class, "parent is null order by leftBound"))
            return root;
        return null;
	}


	/**
	 * Last (rightmost) root in the tree
	 */
	public static <T extends GenericEntity> T findLastRoot() throws DAOException
	{
		for( T root: (Collection<T>)getGenericSession().findMultiple(Module.class, "parent is null order by leftBound desc"))
            return root;
        return null;
	}


	/**
	 * Finds the root of the current node
	 */
		public static <T extends GenericEntity,K extends GenericEntity> T findRoot( K entity ) throws DAOException
	{
		Module vModule = (Module) entity;
		for( T child: (Collection<T>)getGenericSession().findMultiple(Module.class, "not(leftBound > "+vModule.getId()+") and not("+vModule.getId()+" > rightBound) order by leftBound"))
            return child;
        return null;
	}

						
	//[end] Tree finders

}
