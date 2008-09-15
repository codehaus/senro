package ro.siveco.svapnt.common.session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.senro.persistence.ejb3.EntityBeansRepository;

@Stateless( name=EntityBeansRepository.SESSION_NAME )
@Local({ EntityBeansRepository.class })
public class EntityBeansRepositoryImpl implements EntityBeansRepository {
    private static Map<String, Class> entitiesMap = new HashMap<String, Class>();
    private static EntityBeansRepositoryImpl _instance;

    public static EntityBeansRepositoryImpl getInstance() {
    	if (_instance == null)
    		_instance = new EntityBeansRepositoryImpl();

    	return _instance;
    }
    /* (non-Javadoc)
	 * @see ro.siveco.svapnt.common.entity.EntityBeansRepositoryLocal#addEntity(java.lang.String, java.lang.Class)
	 */
    public void addEntity(String entityName, Class entityClass) {
        entitiesMap.put(entityName, entityClass);
    }

    /* (non-Javadoc)
	 * @see ro.siveco.svapnt.common.entity.EntityBeansRepositoryLocal#getAllEntities()
	 */
    public List getAllEntities(){
        return new ArrayList<Class>(entitiesMap.values());
    }
}
