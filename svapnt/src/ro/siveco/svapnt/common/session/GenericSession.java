package ro.siveco.svapnt.common.session;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.Query;

import org.senro.persistence.ejb3.GenericSessionBean;

import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.svapnt.exceptions.BaseException;
import ro.siveco.svapnt.exceptions.DAOException;

/**
 * Created by IntelliJ IDEA.
 * User: stefanpo
 * Date: Apr 5, 2006
 * Time: 11:27:30 AM
 */
@Local
public interface GenericSession extends GenericSessionBean
{
    static final String SESSION_NAME = "GenericSessionImpl";
    static final String JNDI_NAME = "svapnt/" + SESSION_NAME + "/local";

    <T extends GenericEntity> Query getQuery(Class<T> entity, String condition) throws DAOException;

    Query createQuery(String queryString) throws DAOException;

    Query createNativeQuery(String sqlString) throws DAOException;

    Query createNativeQuery(String sqlString, Class resultClass) throws DAOException;

    Query createNativeQuery(String sqlString, String resultSetMapping) throws DAOException;

    Query createNamedQuery(String queryName) throws DAOException;

    <T extends GenericEntity> T insert(T obj, boolean businessValidation, boolean intrinsicValidation) throws BaseException;
    <T extends GenericEntity> T insert(T obj, boolean businessValidation) throws BaseException;
    <T extends GenericEntity> T insert(T obj) throws BaseException;

    <T extends GenericEntity> T update(T obj, boolean businessValidation, boolean intrinsicValidation) throws BaseException;
    <T extends GenericEntity> T update(T obj, boolean businessValidation) throws BaseException;
    <T extends GenericEntity> T update(T obj) throws BaseException;

    <T extends GenericEntity> T delete(T obj, boolean businessValidation, boolean intrinsicValidation) throws BaseException;
    <T extends GenericEntity> T delete(T obj, boolean businessValidation) throws BaseException;
    <T extends GenericEntity> T delete(T obj) throws BaseException;

    <T extends GenericEntity> T preInsert(T obj) throws BaseException;

    <T extends GenericEntity> T insteadOfInsert(T obj) throws BaseException;

    <T extends GenericEntity> T postInsert(T obj) throws BaseException;

    <T extends GenericEntity> T preUpdate(T obj, T oldObj) throws BaseException;
    <T extends GenericEntity> T preUpdate(T obj) throws BaseException;

    <T extends GenericEntity> T insteadOfUpdate(T obj, T oldObj) throws BaseException;
    <T extends GenericEntity> T insteadOfUpdate(T obj) throws BaseException;

    <T extends GenericEntity> T postUpdate(T obj, T oldObj) throws BaseException;
    <T extends GenericEntity> T postUpdate(T obj) throws BaseException;

    <T extends GenericEntity> T preDelete(T obj) throws BaseException;

    <T extends GenericEntity> T insteadOfDelete(T obj) throws BaseException;

    <T extends GenericEntity> T postDelete(T obj) throws BaseException;

    <T extends GenericEntity> T find(Class<T> entity, String id) throws DAOException;

    <T extends GenericEntity> T findSingle(Class<T> entity, String condition) throws DAOException;

    <T extends GenericEntity> List<T> findMultiple(Class<T> entity, String condition) throws DAOException;

//validation methods

    void validateForInsert(GenericEntity obj, boolean intrinsicValidation) throws BaseException;
    void validateForInsert(GenericEntity obj) throws BaseException;
    void validateForUpdate(GenericEntity obj, GenericEntity oldObj, boolean intrinsicValidation) throws BaseException;
    void validateForUpdate(GenericEntity obj, GenericEntity oldObj) throws BaseException;
    void validateForUpdate(GenericEntity obj) throws BaseException;
    void validateForDelete(GenericEntity obj, boolean intrinsicValidation) throws BaseException;
    void validateForDelete(GenericEntity obj) throws BaseException;

    Object getSingleResult(String namedQuery, List params) throws DAOException;
    List getResultList(String namedQuery, List params) throws DAOException;




}
