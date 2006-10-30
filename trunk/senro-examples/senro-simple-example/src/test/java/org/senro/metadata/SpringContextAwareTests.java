package org.senro.metadata;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

/**
 * Author: Claudiu Dumitrescu
 */
public class SpringContextAwareTests extends AbstractDependencyInjectionSpringContextTests {
    protected String[] getConfigLocations() {
        return new String[]{"classpath:applicationContext.xml"};
    }
}
