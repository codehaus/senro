package org.senro.sandbox.simple;

import org.apache.commons.beanutils.BeanUtils;
import org.senro.demo.good.Apple;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import java.util.List;

/**
 * Author: Claudiu Dumitrescu
 */
public class MappedMetadataTest extends AbstractDependencyInjectionSpringContextTests {

    private SimpleMetadataFactory metadataFactory;


      public void setMetadataFactory(SimpleMetadataFactory metadataFactory) {
          this.metadataFactory = metadataFactory;
      }

      protected String[] getConfigLocations() {
          return new String[]{"classpath:senroMetadataManagerTestContext.xml"};
      }



    public void testGetMethods() throws Exception {
        MappedMetadata mappedMetadata = metadataFactory.createClass(Apple.class);
        List methods = (List) mappedMetadata.getMethods();
        assertNotNull(methods);
    }

}
