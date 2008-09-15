package ro.siveco.svapnt.exceptions;

import java.util.*;

/**
 * Business Exception occured.
 */
//@javax.ejb.ApplicationException( rollback = true)
public class BusinessException extends BaseException
{
	public BusinessException()
	{	super( "BusinessException thrown. No more details." );
	}

	public BusinessException( String msg )
	{	super( msg );
	}

	public BusinessException( Exception ex )
	{	super( ex );
	}

	public BusinessException( String key, Exception ex )
	{   super( key, ex );
	}

  	public BusinessException( String key, String bundle )
	{   super( key, bundle );
	}

	public BusinessException( String key, String bundle, Object arg1 )
	{	super( key, bundle, arg1 );
	}

	public BusinessException( String key, String bundle, Object arg1, Object arg2 )
	{	super( key, bundle, arg1, arg2 );
	}

	public BusinessException( String key, String bundle, Object arg1, Object arg2, Object arg3 )
	{	super( key, bundle, arg1, arg2, arg3 );
	}

	public BusinessException( String key, String bundle, Object arg1, Object arg2, Object arg3, Object arg4 )
	{	super( key, bundle, arg1, arg2, arg3, arg4 );
	}

	public BusinessException( String key, String bundle, Object[] args )
	{	super( key, bundle, args );
	}

}
