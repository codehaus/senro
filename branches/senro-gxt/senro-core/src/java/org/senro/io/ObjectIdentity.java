package org.senro.io;

import java.io.Serializable;

/**
 * @author Flavius Burca
 *
 */
public class ObjectIdentity implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private final String entityName;
    private final Serializable id;

    public ObjectIdentity( String entityName, Serializable id )
    {
        this.entityName = entityName;
        this.id = id;
    }

    public String getEntityName()
    {
        return entityName;
    }

    public Serializable getId()
    {
        return id;
    }
}
