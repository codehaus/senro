package org.senro.persistence.hibernate;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;
import org.senro.persistence.PersistenceService;
import org.senro.demo.good.Apple;

import java.util.List;

import wicket.extensions.markup.html.repeater.util.SortParam;

/**
 * Author: Claudiu Dumitrescu
 */
public class HibernatePersistenceServiceTest extends AbstractDependencyInjectionSpringContextTests {
    PersistenceService persistenceService;


    public void setPersistenceService(PersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }

    protected String[] getConfigLocations() {
        return new String[]{"classpath:senroMetadataManagerTestContext.xml"};
    }

    public void testGetAllInstancesWithoutSorting(){
        List appleList = persistenceService.getAllInstances(Apple.class);
        assertNotNull(appleList);

    }
    public void testGetAllInstancesWithSorting(){
        List appleList = persistenceService.getAllInstances(Apple.class,new SortParam("id", true));
        assertNotNull(appleList);

    }
}
