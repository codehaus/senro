package ro.siveco.svapnt.exceptions;

//@javax.ejb.ApplicationException( rollback = true)
public class DAOException extends BaseException
{
    public DAOException()
    {   super( "DAOException thrown" );
    }

    public DAOException( String msg )
    {   super( msg );
    }

	public DAOException( Exception e )
	{	super( e );
	}

	public DAOException( String key, Exception ex )
	{   super( key, ex );
	}

	public DAOException( String key, String bundle )
	{   super( key, bundle );
	}

	public DAOException( String key, String bundle, Object arg1 )
	{	super( key, bundle, arg1 );
	}

	public DAOException( String key, String bundle, Object arg1, Object arg2 )
	{	super( key, bundle, arg1, arg2 );
	}

	public DAOException( String key, String bundle, Object arg1, Object arg2, Object arg3 )
	{	super( key, bundle, arg1, arg2, arg3 );
	}

	public DAOException( String key, String bundle, Object arg1, Object arg2, Object arg3, Object arg4 )
	{	super( key, bundle, arg1, arg2, arg3, arg4 );
	}

	public DAOException( String key, String bundle, Object[] args )
	{	super( key, bundle, args );
	}
}
