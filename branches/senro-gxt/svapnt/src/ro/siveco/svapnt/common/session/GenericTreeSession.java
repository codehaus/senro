package ro.siveco.svapnt.common.session;

import java.util.Collection;
import java.util.Vector;

import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.svapnt.exceptions.DAOException;

/**
 * Generic interface for tree entities
 * @author FlaviusB
 */
public interface GenericTreeSession extends GenericSession 
{
	public static final double LEFT_BOUND = 0;
	public static final double RIGHT_BOUND = Math.pow( 3, 32 );
	
	public <T extends GenericEntity,K extends GenericEntity> Vector<T> getBrothers( K entity ) throws DAOException;
	public <T extends GenericEntity> Vector<T> getChildren( String ID ) throws DAOException;
	public <T extends GenericEntity,K extends GenericEntity> Vector<T> getAllChildren( K entity ) throws DAOException;
	public <T extends GenericEntity> boolean hasChildren( T entity ) throws DAOException;
	public <T extends GenericEntity,K extends GenericEntity> T getFirstChild( K entity ) throws DAOException;
	public <T extends GenericEntity,K extends GenericEntity> T getLastChild( K entity ) throws DAOException;
	public <T extends GenericEntity> T getFirstRoot() throws DAOException;
	public <T extends GenericEntity> Vector<T> getAllRoots() throws DAOException;
	public <T extends GenericEntity> T getLastRoot() throws DAOException;
	public <T extends GenericEntity,K extends GenericEntity> T getRoot( K entity ) throws DAOException;
	public <T extends GenericEntity,K extends GenericEntity> T getNextRoot( K entity ) throws DAOException;
}
