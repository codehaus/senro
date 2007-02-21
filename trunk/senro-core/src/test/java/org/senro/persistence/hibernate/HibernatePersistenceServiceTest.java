package org.senro.persistence.hibernate;

import org.hibernate.criterion.DetachedCriteria;
import org.senro.demo.good.Apple;
import org.senro.persistence.PersistenceService;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;
import org.springframework.util.FileCopyUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * Author: Claudiu Dumitrescu
 */
public class HibernatePersistenceServiceTest extends AbstractDependencyInjectionSpringContextTests {
    PersistenceService persistenceService;
    DataSource dataSource;

    protected void onSetUp() throws Exception {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        String sql = new String(FileCopyUtils.copyToByteArray(getClass().getResourceAsStream("/testData.sql")));
        template.execute(sql);
    }

    public void setPersistenceService(PersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    protected String[] getConfigLocations() {
        return new String[]{"classpath:senroMetadataManagerTestContext.xml"};
    }

    public void testGetAllInstancesWithoutSorting() {
        List appleList = persistenceService.getAllInstances(Apple.class);
        assertNotNull(appleList);
        assertEquals(appleList.size(), 3);
    }

    public void testCriteriaGet() {
        DetachedCriteria query = DetachedCriteria.forClass(Apple.class);
        List appleList = persistenceService.getAllInstances(query);
        assertNotNull(appleList);
        assertEquals(appleList.size(), 3);
    }
//   Why does core have a dependency on Wicket now? :)   
//    public void testGetAllInstancesWithSorting(){
//        List appleList = persistenceService.getAllInstances(Apple.class,new SortParam("id", true));
//        assertNotNull(appleList);
//
//    }
}
