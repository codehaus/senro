package ro.siveco.svapnt.common.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.senro.persistence.GenericEntityBean;

import ro.siveco.svapnt.common.session.GenericSession;
import ro.siveco.svapnt.common.session.GenericSessionImpl;
import ro.siveco.svapnt.exceptions.BaseException;
import ro.siveco.svapnt.exceptions.LookUpException;


public abstract class GenericEntity implements GenericEntityBean
{
    private Map extraProperties = new HashMap();
    private static final String APP_MESSAGES = "appMessages";
    private static final String EXTRA_PARAMS = "extraParams";

    public static final String ENTITY_NAME = "common_GenericEntity";
    public static Map<String, Class> entityRepository = new HashMap<String, Class>();

    public static String getEntityName()
    {
        return ENTITY_NAME;
    }

    /**
     * @deprecated use addMessage instead
     * @param msg
     * @param level
     */
    public void setMessage( BaseException msg, String level )
    {
        addMessage( msg, level );
    }

    /**
     * Sets the messages.
     * @param messages - new set of messages that this entity will carry
     */
    public void setAllMessages( Vector messages )
    {
        extraProperties.put(APP_MESSAGES, messages);
    }

    /**
     * Adds a "message" to the vector of messages.
     * @param msg - a BaseException, used to transmit a message
     * @param level - the level of the message, used from the Constants interface (info, warning or error)
     */
    public void addMessage( BaseException msg, String level )
    {
        if( extraProperties.get(APP_MESSAGES) == null )
        {   extraProperties.put(APP_MESSAGES, new ArrayList());
        }
    }

    /**
     * Method used to add a collection of messages
     * @param msgs - collection of messages
     */
    public void addMessages( List msgs )
    {
        if(  msgs != null )
        {
            if( extraProperties.get(APP_MESSAGES) == null )
            {   extraProperties.put(APP_MESSAGES, new ArrayList());
            }

            ((List)extraProperties.get(APP_MESSAGES)).addAll( msgs );
        }
    }

    /**
     * Resets the messages vector
     */
    public void resetMessages()
    {   extraProperties.remove(APP_MESSAGES);
    }

    /**
     * returns the message list
     * @return list of messages
     */
    public List getMessages()
    {   return (List)extraProperties.get(APP_MESSAGES);
    }

    public Object getExtraParams()
    {   return extraProperties.get(EXTRA_PARAMS);
    }

    public void setExtraParams( Object obj )
    {   extraProperties.put(EXTRA_PARAMS, obj);
    }

    public void setExtraProperty( String name, Object value )
    {
        if( value == null )
            extraProperties.remove(name);
        else
            extraProperties.put(name, value);
    }

    public Object getExtraProperty( String name )
    {
        return extraProperties.get(name);
    }

    public Long getId()
    {
        return 0L;
    }

    private static GenericSession session = null;

    public static GenericSession getGenericSession()
    {

        if( session == null)
        {
            try
            {
                session = GenericSessionImpl.getInstance(GenericSession.JNDI_NAME);
            }
            catch(LookUpException e)
            {
                e.printStackTrace( );
                //should not happen
            }
        }
        return session;
    }

//    protected <T> Collection<T> loadLazy(String property)
//    {
//        return getGenericSession().loadLazy(this, property);
//    }
/*
    public static <T extends GenericEntity,K extends GenericEntity> Vector<T> findBrothers( K entity ) throws DAOException
    {return null;}
    public static <T extends GenericEntity> Vector<T> findAllChildren( String ID ) throws DAOException
    {return null;}
    public static <T extends GenericEntity,K extends GenericEntity> Vector<T> getAllChildren( K entity ) throws DAOException
    {return null;}
    public static <T extends GenericEntity> boolean hasChildren( T entity ) throws DAOException
    {return true;}
    public static <T extends GenericEntity,K extends GenericEntity> T findFirstChild( K entity ) throws DAOException
    {return null;}
    public static <T extends GenericEntity,K extends GenericEntity> T findLastChild( K entity ) throws DAOException
    {return null;}
    public static <T extends GenericEntity> T findFirstRoot() throws DAOException
    {return null;}
    public static <T extends GenericEntity> Vector<T> findAllRoots() throws DAOException
    {return null;}
    public static <T extends GenericEntity> T findLastRoot() throws DAOException
    {return null;}
    public static <T extends GenericEntity,K extends GenericEntity> T findRoot( K entity ) throws DAOException
    {return null;}
    public static <T extends GenericEntity,K extends GenericEntity> T findNextRoot( K entity ) throws DAOException
    {return null;}

  */

}
