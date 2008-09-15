package ro.siveco.svapnt.exceptions;

import ro.siveco.tools.*;
import ro.siveco.svapnt.utils.*;

import java.util.*;
import javax.ejb.ApplicationException;

@javax.ejb.ApplicationException( rollback = true)
public class BaseException extends Exception
{
    protected String				key;
    protected String				bundle;
    protected Object[]				args;

    public BaseException()
    {   super( "BaseException thrown" );
    }

    /**
     * @param e - exception
     */
    public BaseException( Exception e )
    {	super( e );
    }

    /**
     * Using the default bundle
     * @param key - key used to extract the message from the implicit bundle
     */
    public BaseException( String key )
    {	initAttributes( key, null, null );
    }

    /**
     * Get a key using the default bundle
     * @param key - key used to extract the message from the implicit bundle
     * @param ex - exception sent as a parameter
     */
    public BaseException( String key, Exception ex )
    {	Object[] args = new Object[ 1 ];
        args[ 0 ] = ex;
        initAttributes( key, null, args );
    }

    /**
     * Get a key from a given bundle
     * @param key - key used to extract the message from the bundle
     * @param bundle - bundle to be used. If null, the implicit bundle is used
     */
    public BaseException( String key, String bundle )
    {	initAttributes( key, bundle, null );
    }

    /**
     * Constructor with 1 argument
     * @param key - key used to extract the message from the bundle
     * @param bundle - bundle to be used. If null, the implicit bundle is used
     * @param arg1 - argument
     */
    public BaseException( String key, String bundle, Object arg1 )
    {	Object[] args = new Object[ 1 ];
        args[ 0 ] = arg1;
        initAttributes( key, bundle, args );
    }

    /**
     * Constructor with 2 arguments
     * @param key - key used to extract the message from the bundle
     * @param bundle - bundle to be used. If null, the implicit bundle is used
     * @param arg1 - argument
     * @param arg2 - argument
     */
    public BaseException( String key, String bundle, Object arg1, Object arg2 )
    {	Object[] args = new Object[ 2 ];
        args[ 0 ] = arg1;
        args[ 1 ] = arg2;
        initAttributes( key, bundle, args );
    }

    /**
     * Constructor with 3 arguments
     * @param key - key used to extract the message from the bundle
     * @param bundle - bundle to be used. If null, the implicit bundle is used
     * @param arg1 - argument
     * @param arg2 - argument
     * @param arg3 - argument
     */
    public BaseException( String key, String bundle, Object arg1, Object arg2, Object arg3 )
    {	Object[] args = new Object[ 3 ];
        args[ 0 ] = arg1;
        args[ 1 ] = arg2;
        args[ 2 ] = arg3;
        initAttributes( key, bundle, args );
    }

    /**
     * Constructor with 4 arguments
     * @param key - key used to extract the message from the bundle
     * @param bundle - bundle to be used. If null, the implicit bundle is used
     * @param arg1 - argument
     * @param arg2 - argument
     * @param arg3 - argument
     * @param arg4 - argument
     */
    public BaseException( String key, String bundle, Object arg1, Object arg2, Object arg3, Object arg4 )
    {	Object[] args = new Object[ 4 ];
        args[ 0 ] = arg1;
        args[ 1 ] = arg2;
        args[ 2 ] = arg3;
        args[ 3 ] = arg4;
        initAttributes( key, bundle, args );
    }

    /**
     * Constructor with array of arguments, using the default bundle
     * @param key - key used to extract the message from the bundle
     * @param args - array of arguments
     */
    public BaseException( String key, Object[] args )
    {	initAttributes( key, null, args );
    }

    /**
     * Constructor with array of arguments
     * @param key - key used to extract the message from the bundle
     * @param bundle - bundle to be used. If null, the implicit bundle is used
     * @param args - array of arguments
     */
    public BaseException( String key, String bundle, Object[] args )
    {	initAttributes( key, bundle, args );
    }

    private void initAttributes( String key, String bundle, Object[] args )
    {	this.key = key;
        this.args = args;
        this.bundle = bundle;
    }

    public String getKey()
    {	return key;
    }

    public String getBundle()
    {	return bundle;
    }

    public Object[] getArgs()
    {	return args;
    }

    public String getMessage()
    {	return getMessage( this, Locale.getDefault() );
    }

//    public String getOriginalMessage()
//    {
//        return super.getMessage();
//    }

    public String getMessage( Locale locale )
    {	return getMessage( this, locale );
    }

    public String getMessage( Object exception )
    {	return getMessage( exception, Locale.getDefault() );
    }

    public String getMessage( Object exception, Locale locale )
    {
        String				msg = "";

        msg = (exception == null) ? "" : ((BaseException)exception).getCause().getMessage();

        return msg;
    }

    private String[] getReplacements( Object[] args, Locale locale )
    {
       return new String[ 0 ];
    }
}
