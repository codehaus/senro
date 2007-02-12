package org.senro.persistence.ejb3;

import java.util.List;

import javax.ejb.Local;

@Local
public interface GenericSessionBean
{
	public <T> T genericInsert(T entity) throws Exception;
	public <T> T genericUpdate(T entity) throws Exception;
	public <T> T genericDelete(T entity) throws Exception;
	public <T> T genericFindSingle(Class<T> entity, String condition) throws Exception;
	public <T> List<T> genericFindMultiple(Class<T> entity, String condition) throws Exception;
	public <T> T genericFind(Class<T> entity, String id) throws Exception;
}
