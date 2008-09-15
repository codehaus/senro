package ro.siveco.svapnt.common.session;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;

import org.senro.annotations.SessionName;
import org.senro.persistence.ejb3.GenericSessionBean;

import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.svapnt.exceptions.BaseException;
import ro.siveco.svapnt.exceptions.BusinessException;
import ro.siveco.svapnt.exceptions.BusinessExceptions;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.exceptions.LookUpException;

/**
 * Created by IntelliJ IDEA. User: stefanpo Date: Apr 4, 2006 Time: 5:52:32 PM
 */
@Stateless
public class GenericSessionImpl implements GenericSession
{
    @PersistenceUnit
    protected EntityManagerFactory emf;

    @PersistenceContext
    protected EntityManager em;

    public Query createQuery( String queryString ) throws DAOException
    {
        try
        {
            return em.createQuery( queryString );
        }
        catch( IllegalStateException e )
        {
            e.printStackTrace();
            throw new DAOException( e );

        }
        catch( IllegalArgumentException e )
        {
            e.printStackTrace();
            throw new DAOException( e );
        }
    }

    public Query createNativeQuery( String sqlString ) throws DAOException
    {
        try
        {
            return em.createNativeQuery( sqlString );
        }
        catch( IllegalStateException e )
        {
            e.printStackTrace();
            throw new DAOException( e );

        }
        catch( IllegalArgumentException e )
        {
            e.printStackTrace();
            throw new DAOException( e );
        }
    }

    public Query createNativeQuery( String sqlString, Class resultClass ) throws DAOException
    {
        try
        {
            return em.createNativeQuery( sqlString, resultClass );
        }
        catch( IllegalStateException e )
        {
            e.printStackTrace();
            throw new DAOException( e );

        }
        catch( IllegalArgumentException e )
        {
            e.printStackTrace();
            throw new DAOException( e );
        }
    }


    public Query createNativeQuery( String sqlString, String resultSetMapping ) throws DAOException
    {
        try
        {
            return em.createNativeQuery( sqlString, resultSetMapping );
        }
        catch( IllegalStateException e )
        {
            e.printStackTrace();
            throw new DAOException( e );

        }
        catch( IllegalArgumentException e )
        {
            e.printStackTrace();
            throw new DAOException( e );
        }
    }

    public Query createNamedQuery( String queryName ) throws DAOException
    {
        try
        {
            return em.createNamedQuery( queryName );
        }
        catch( IllegalStateException e )
        {
            e.printStackTrace();
            throw new DAOException( e );

        }
        catch( IllegalArgumentException e )
        {
            e.printStackTrace();
            throw new DAOException( e );
        }
    }

    public static void throwNotImplemented() throws BusinessExceptions
    {
        BusinessExceptions be = new BusinessExceptions();
        be.addException( new BusinessException( "not implemented" ) );
        throw be;
    }

    public static <T> T getInstance( Class<T> localIntf ) throws LookUpException
    {
        String jndiName = null;
        try
        {
            jndiName = ( String )( localIntf.getField( "JNDI_NAME" ).get( null ) );
        }
        catch( IllegalAccessException e )
        {
            System.err.println( "Error retrieving the " + localIntf.getName() + " interface: " + e.getMessage() );
            e.printStackTrace();
            throw new LookUpException( e );
        }
        catch( NoSuchFieldException e )
        {
            System.err.println( "Error retrieving the " + localIntf.getName() + " interface: " + e.getMessage() );
            e.printStackTrace();
            throw new LookUpException( e );
        }
        return ( T )getInstance( jndiName );
    }

    public static <T> T getInstance( String jndiName ) throws LookUpException
    {
        try
        {
            InitialContext context = new InitialContext();
            return ( T )context.lookup( jndiName );
        }
        catch( NamingException e )
        {
            System.err.println( "Error retrieving the " + jndiName + " interface: " + e.getMessage() );
            e.printStackTrace();
            throw new LookUpException( e );
        }

    }

    private Map<String, GenericSession> sessions = new HashMap<String, GenericSession>();

    public GenericSession getSession( GenericEntity obj ) throws LookUpException
    {
        String sessionName = obj.getClass().getAnnotation( SessionName.class ).name();
        System.out.println( "Sess name: " + sessionName );
        if( !sessions.containsKey( sessionName ) )
        {
            sessions.put( sessionName, ( GenericSession )getInstance( sessionName ) );
        }
        return sessions.get( sessionName );
    }

//    public <T> Collection<T> loadLazy(GenericEntity entity, String property)
//    {
//        Collection<T> ret = null;
//        Object inst = em.find(entity.getClass(), entity.getId());
//        Class instCls = inst.getClass();
//
//        while(true)
//            try {
//                Field prop = instCls.getDeclaredField(property);
//                prop.setAccessible(true);
//                ret = (Collection<T>)prop.get(inst);
//                ret.size();
//                return ret;
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (NoSuchFieldException e) {
//                instCls = instCls.getSuperclass();
//                if( instCls == null || instCls == GenericEntity.class )
//                {
//                    System.out.println("*** loadLazy couldn't find property: " + entity.getClass() + "." + property + "; returning null");
//                    return null;
//                }
//            }
//    }
//

    public final <T extends GenericEntity> T insert( T obj, boolean businessValidation, boolean intrinsicValidation )
        throws BaseException
    {
        try
        {
            GenericSession session = getSession( obj );
            obj = session.preInsert( obj );
            if( businessValidation )
            {
                session.validateForInsert( obj, intrinsicValidation );
            }
            T repl = session.insteadOfInsert( obj );
            if( repl == null )
            {
                repl = obj;
                em.persist( repl );
            }

            return session.postInsert( repl );
        }
        catch( IllegalStateException e )
        {
            e.printStackTrace();
            throw new DAOException( e );

        }
        catch( IllegalArgumentException e )
        {
            e.printStackTrace();
            throw new DAOException( e );
        }
        catch( TransactionRequiredException e )
        {
            e.printStackTrace();
            throw new DAOException( e );
        }
        catch( PersistenceException e )
        {
            e.printStackTrace();
            throw new DAOException( e );
        }

    }

    public final <T extends GenericEntity> T insert( T obj ) throws BaseException
    {
        return insert( obj, true, true );
    }

    public final <T extends GenericEntity> T insert( T obj, boolean businessValidation ) throws BaseException
    {
        return insert( obj, businessValidation, true );
    }

    public final <T extends GenericEntity> T update( T obj, boolean businessValidation, boolean intrinsicValidation )
        throws BaseException
    {
        try
        {
            System.out.println( "*********************** BEFORE CONTAINS" );
            if( !em.contains( obj ) )
            {
                System.out.println( "*********************** AFTER CONTAINS BEFORE MERGE" );
                obj = em.merge( obj );
                System.out.println( "*********************** AFTER MERGE" );
            }

            System.out.println( "*********************** AFTER CONTAINS" );
            T oldObj = getInDBVersion( obj);

            GenericSession session = getSession( obj );
            obj = session.preUpdate( obj, oldObj );
            if( businessValidation )
            {
                session.validateForUpdate( obj, oldObj, intrinsicValidation );
            }
            T repl = session.insteadOfUpdate( obj, oldObj );

            if( repl == null )
            {
                repl = obj;
                em.flush();
            }

            return session.postUpdate( repl, oldObj );

        }
        catch( IllegalStateException e )
        {
            e.printStackTrace();
            throw new DAOException( e );
        }
        catch( IllegalArgumentException e )
        {
            e.printStackTrace();
            throw new DAOException( e );
        }
        catch( TransactionRequiredException e )
        {
            e.printStackTrace();
            throw new DAOException( e );
        }
        catch( PersistenceException e )
        {
            e.printStackTrace();
            throw new DAOException( e );
        }

    }

    public final <T extends GenericEntity> T update( T obj, boolean businessValidation ) throws BaseException
    {
        return update( obj, businessValidation, true );
    }

    public final <T extends GenericEntity> T update( T obj ) throws BaseException
    {
        return update( obj, true, true );
    }

    public final <T extends GenericEntity> T delete( T obj, boolean businessValidation, boolean intrinsicValidation )
        throws BaseException
    {
        try
        {
            if( !em.contains( obj ) )
            {
                obj = em.merge( obj );
            }

            GenericSession session = getSession( obj );
            obj = session.preDelete( obj );
            if( businessValidation )
            {
                session.validateForDelete( obj, intrinsicValidation );
            }
            T repl = session.insteadOfDelete( obj );
            if( repl == null )
            {
                repl = obj;
                em.remove( repl );
            }

            return session.postDelete( repl );
        }
        catch( IllegalStateException e )
        {
            e.printStackTrace();
            throw new DAOException( e );

        }
        catch( IllegalArgumentException e )
        {
            e.printStackTrace();
            throw new DAOException( e );
        }
        catch( TransactionRequiredException e )
        {
            e.printStackTrace();
            throw new DAOException( e );
        }
        catch( PersistenceException e )
        {
            e.printStackTrace();
            throw new DAOException( e );
        }

    }

    public final <T extends GenericEntity> T delete( T obj ) throws BaseException
    {
        return delete( obj, true, true );
    }

    public final <T extends GenericEntity> T delete( T obj, boolean businessValidation ) throws BaseException
    {
        return delete( obj, businessValidation, true );
    }


    public <T extends GenericEntity> T preInsert( T obj ) throws BaseException
    {   //do not add code here... this is only to be overrided
        return obj;
    }

    public <T extends GenericEntity> T insteadOfInsert( T obj ) throws BaseException
    {   //do not add code here... this is only to be overrided
        return null;
    }

    public <T extends GenericEntity> T postInsert( T obj ) throws BaseException
    {   //do not add code here... this is only to be overrided
        return obj;
    }

    public <T extends GenericEntity> T preUpdate( T obj, T oldObj ) throws BaseException
    {   //do not add code here... this is only to be overrided
        return preUpdate( obj);
    }

    public <T extends GenericEntity> T preUpdate( T obj ) throws BaseException
    {   //do not add code here... this is only to be overrided
        return obj;
    }

    public <T extends GenericEntity> T insteadOfUpdate( T obj, T oldObj ) throws BaseException
    {   //do not add code here... this is only to be overrided
        return insteadOfUpdate( obj);
    }

    public <T extends GenericEntity> T insteadOfUpdate( T obj ) throws BaseException
    {   //do not add code here... this is only to be overrided
        return null;
    }

    public <T extends GenericEntity> T postUpdate( T obj, T oldObj ) throws BaseException
    {   //do not add code here... this is only to be overrided
        return postUpdate(obj);
    }

    public <T extends GenericEntity> T postUpdate( T obj ) throws BaseException
    {   //do not add code here... this is only to be overrided
        return obj;
    }

    public <T extends GenericEntity> T preDelete( T obj ) throws BaseException
    {   //do not add code here... this is only to be overrided
        return obj;
    }

    public <T extends GenericEntity> T insteadOfDelete( T obj ) throws BaseException
    {   //do not add code here... this is only to be overrided
        return null;
    }

    public <T extends GenericEntity> T postDelete( T obj ) throws BaseException
    {   //do not add code here... this is only to be overrided
        return obj;
    }

    public final void validateForInsert( GenericEntity obj, boolean intrinsicValidation ) throws BaseException
    {
        BusinessExceptions be = new BusinessExceptions();

        validateForInsert( obj, be, intrinsicValidation );

        if( be.getExceptions().size() > 0 )
        {
            throw be;
        }

    }

    public final void validateForInsert( GenericEntity obj ) throws BaseException
    {
        validateForInsert( obj, true);
    }

    public final void validateForUpdate( GenericEntity obj, GenericEntity oldObj, boolean intrinsicValidation )
        throws BaseException
    {
        BusinessExceptions be = new BusinessExceptions();

        validateForUpdate( obj, oldObj, be, intrinsicValidation );

        if( be.getExceptions().size() > 0 )
        {
            throw be;
        }

    }

    public final void validateForUpdate( GenericEntity obj, GenericEntity oldObj ) throws BaseException
    {
        validateForUpdate( obj, oldObj, true);
    }

    public final void validateForUpdate( GenericEntity obj ) throws BaseException
    {
        validateForUpdate( obj, getInDBVersion( obj));
    }

    public final void validateForDelete( GenericEntity obj, boolean intrinsicValidation ) throws BaseException
    {
        BusinessExceptions be = new BusinessExceptions();

        validateForDelete( obj, be, intrinsicValidation );

        if( be.getExceptions().size() > 0 )
        {
            throw be;
        }
    }

    public final void validateForDelete( GenericEntity obj ) throws BaseException
    {
        validateForDelete(obj, true);
    }

    protected void validateForInsert( GenericEntity obj, BusinessExceptions bizExceptions ) throws BaseException
    {   //do not add code here... this is only to be overrided
    }

    protected void validateForUpdate( GenericEntity obj, GenericEntity oldObj, BusinessExceptions bizExceptions ) throws BaseException
    {   //do not add code here... this is only to be overrided
        validateForUpdate(obj, bizExceptions );
    }

    protected void validateForDelete( GenericEntity obj, BusinessExceptions bizExceptions ) throws BaseException
    {   //do not add code here... this is only to be overrided
    }

    protected void validateForInsert( GenericEntity obj, BusinessExceptions bizExceptions, boolean intrinsicValidation ) throws BaseException
    {   //do not add code here... this is only to be overrided
        validateForInsert(obj, bizExceptions);
    }

    protected void validateForUpdate( GenericEntity obj, BusinessExceptions bizExceptions ) throws BaseException
    {   //do not add code here... this is only to be overrided
    }

    protected void validateForUpdate( GenericEntity obj, GenericEntity oldObj, BusinessExceptions bizExceptions, boolean intrinsicValidation ) throws BaseException
    {   //do not add code here... this is only to be overrided
        validateForUpdate(obj, oldObj, bizExceptions );
    }

    protected void validateForDelete( GenericEntity obj, BusinessExceptions bizExceptions, boolean intrinsicValidation ) throws BaseException
    {   //do not add code here... this is only to be overrided
        validateForDelete(obj, bizExceptions);
    }

    public <T extends GenericEntity> T find( Class<T> entity, String id ) throws DAOException
    {
        try
        {
            return em.find( entity, new Long( id ) );
        }
        catch( IllegalStateException e )
        {
            e.printStackTrace();
            throw new DAOException( e );

        }
        catch( IllegalArgumentException e )
        {
            e.printStackTrace();
            throw new DAOException( e );
        }
    }

    private <T extends GenericEntity> String getEntityName( Class<? super T> entity )
    {
        try
        {
            return ( String )entity.getField( "ENTITY_NAME" ).get( null );
        }
        catch( IllegalAccessException e )
        {
            e.printStackTrace();
        }
        catch( NoSuchFieldException e )
        {
            e.printStackTrace();
        }
        return null;
    }

    public <T extends GenericEntity> Query getQuery( Class<T> entity, String condition ) throws DAOException
    {
        try
        {
            String queryString = "from " + getEntityName( entity );
            if( condition != null )
            {
                queryString += " where " + condition;
            }


            return em.createQuery( queryString );
        }
        catch( Exception e )
        {
            e.printStackTrace();
            throw new DAOException( e );

        }
    }

    public <T extends GenericEntity> T findSingle( Class<T> entity, String condition ) throws DAOException
    {
        try
        {
            return ( T )getQuery( entity, condition ).getSingleResult();
        }
        catch( PersistenceException e )
        {
            e.printStackTrace();
            throw new DAOException( e );
        }
        catch( IllegalStateException e )
        {
            e.printStackTrace();
            throw new DAOException( e );
        }
    }

    public <T extends GenericEntity> List<T> findMultiple( Class<T> entity, String condition ) throws DAOException
    {
        try
        {
            return getQuery( entity, condition ).getResultList();
        }
        catch( IllegalStateException e )
        {
            e.printStackTrace();
            throw new DAOException( e );
        }
    }



    private <T extends GenericEntity> T getInDBVersion(T obj) throws DAOException
    {
        if (obj == null)
        {
            return null;
        }

        EntityManager eman;
        T dbObj;

        try
        {
            eman = emf.createEntityManager( );
            dbObj = (T) eman.find(obj.getClass(), obj.getId());
            eman.close();
        }
        catch(IllegalStateException e)
        {
            e.printStackTrace();
            throw new DAOException( e);
        }
        catch(IllegalArgumentException e)
        {
            e.printStackTrace();
            throw new DAOException( e);
        }

        return (T) dbObj;
    }

    public final Object getSingleResult( String namedQuery, List params ) throws DAOException
    {
        try
        {

            Query query = createNamedQuery( namedQuery);
            setQueryParams( query, params);
            Object o = query.getSingleResult();
            return o;
        }
        catch(NoResultException e)
        {
//            e.printStackTrace( );
            throw new DAOException( e);
        }
        catch(EntityNotFoundException e)
        {
//            e.printStackTrace( );
            throw new DAOException( e);
        }
        catch(NonUniqueResultException e)
        {
            e.printStackTrace( );
            throw new DAOException( e);
        }
        catch(IllegalStateException e)
        {
            e.printStackTrace( );
            throw new DAOException( e);
        }
        catch(IllegalArgumentException e)
        {
            e.printStackTrace( );
            throw new DAOException( e);
        }

    }

    public final List getResultList( String namedQuery, List params ) throws DAOException
    {
        try
        {
            Query query = createNamedQuery( namedQuery);
            setQueryParams( query, params);

            return query.getResultList();
        }
        catch(NoResultException e)
        {
//            e.printStackTrace( );
            throw new DAOException( e);
        }
        catch(EntityNotFoundException e)
        {
//            e.printStackTrace( );
            throw new DAOException( e);
        }
        catch(NonUniqueResultException e)
        {
//            e.printStackTrace( );
            throw new DAOException( e);
        }
        catch(IllegalStateException e)
        {
            e.printStackTrace( );
            throw new DAOException( e);
        }
        catch(IllegalArgumentException e)
        {
            e.printStackTrace( );
            throw new DAOException( e);
        }

    }

    private void setQueryParams( Query query, List params)
    {
        int i = 0;
        for(Object o : params)
        {
            query.setParameter( ++i, o);
        }
    }

    private void test(GenericEntity en)
    {
    }

    /** SENRO **/
	public <T> T genericDelete(T entity) throws Exception {
		return (T) delete( (GenericEntity)entity );
	}

	public <T> T genericInsert(T entity) throws Exception {
		return (T) insert( (GenericEntity)entity );
	}

	public <T> T genericUpdate(T entity) throws Exception {
		GenericEntity ge = (GenericEntity)entity;

		if (ge.getId() == null)
			return (T) insert( (GenericEntity)entity );
		else
			return (T) update( (GenericEntity)entity );
	}

	public <T> T genericFind(Class<T> entity, String id) throws Exception {
		return (T) find((Class<? extends GenericEntity>)entity, id);
	}

	public <T> List<T> genericFindMultiple(Class<T> entity, String condition) throws Exception {
		return (List<T>) findMultiple((Class<? extends GenericEntity>)entity, condition);
	}

	public <T> T genericFindSingle(Class<T> entity, String condition) throws Exception {
		return (T) findSingle((Class<? extends GenericEntity>)entity, condition);
	}
}
