package org.senro.servlet;

import org.senro.metadata.Metadata;
import org.senro.metadata.MetadataManager;
import org.senro.metadata.exception.NoMetadataFoundException;
import org.senro.metadata.impl.SenroMetadataManager;
import org.senro.page.HomePage;
import org.senro.persistence.PersistenceService;
import wicket.spring.SpringWebApplication;

import java.lang.reflect.AnnotatedElement;

/**
 * Created by <a href="mailto:claudiu.dumitrescu@gmail.com">Claudiu Dumitrescu</a>
 */
public class SenroApplication extends SpringWebApplication {

    MetadataManager metadataManager;
    PersistenceService persistenceService;


    public MetadataManager getMetadataManager() {
        if (metadataManager == null) {
            synchronized (this) {
                if (metadataManager == null) {
                    metadataManager = (MetadataManager) createSpringBeanProxy(
                            SenroMetadataManager.class, "metadataManager");
                }
            }
        }
        return metadataManager;
    }

    public PersistenceService getPersistenceService() {
        if (persistenceService == null) {
            synchronized (this) {
                if (persistenceService == null) {
                    persistenceService = (PersistenceService) createSpringBeanProxy(
                            PersistenceService.class, "persistenceService");
                }
            }
        }
        return persistenceService;
    }

    public Class getHomePage() {
        return HomePage.class;
    }

    public Metadata getMetadata(AnnotatedElement annotatedElement) throws NoMetadataFoundException {
        return getMetadataManager().getMetadata(annotatedElement);
    }
}
