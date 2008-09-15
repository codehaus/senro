package ro.siveco.svapnt.exceptions;

import java.util.Vector;

import ro.siveco.tools.ObjectTool;

//@javax.ejb.ApplicationException( rollback = true)
public class Exceptions extends BaseException
{
    Vector exceptions;

    public Exceptions()
    {
        exceptions = new Vector();
    }

    public void addException( Exception e )
    {
        exceptions.add( e );
    }

	public void addAll( Exceptions e )
	{
		if( !ObjectTool.isNull( e ) )
			exceptions.addAll( e.getExceptions() );
	}

    public Vector getExceptions()
    {
        return exceptions;
    }

    public String getMessages()
    {
        StringBuffer str = new StringBuffer();

        for( int i = 0;i < exceptions.size(); i++)
            str.append( ((Exception)exceptions.get( i )).getMessage() + "/n" );

        return str.toString();
    }

    public boolean isEmpty()
    {
        if( exceptions.size() > 0 )
            return false;

        return true;
    }
}
