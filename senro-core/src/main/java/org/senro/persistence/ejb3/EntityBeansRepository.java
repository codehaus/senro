package org.senro.persistence.ejb3;

import java.util.List;

public interface EntityBeansRepository {
	public static final String SESSION_NAME = "EntityBeansRepositoryImpl";
	public static final String JNDI_NAME = "svapnt/" + SESSION_NAME + "/local";

    public void addEntity(String entityName, Class entityClass);
    public List<Class> getAllEntities();
}
