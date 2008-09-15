package ro.siveco.svapnt.exceptions;

/**
 * Created by IntelliJ IDEA.
 * User: MonicaD
 * Date: Mar 18, 2004
 * Time: 12:46:46 PM
 * To change this template use Options | File Templates.
 */

//@javax.ejb.ApplicationException( rollback = true)
public class LookUpException extends BaseException
{
    public LookUpException()
    {   super( "LookUpException thrown" );
    }

    public LookUpException( String msg )
    {   super( msg );
    }

    public LookUpException( Exception e)
    {   super( e );
    }
}
