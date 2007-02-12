package org.senro.servlet;

import java.lang.reflect.AnnotatedElement;

import org.senro.metadata.Metadata;
import org.senro.metadata.MetadataManager;
import org.senro.metadata.aop.AOPMetadataManager;
import org.senro.metadata.exception.NoMetadataFoundException;
import org.senro.page.HomePage;
import org.senro.persistence.PersistenceService;
import org.senro.rules.IRulesEngine;
import org.senro.rules.RulesRepository;

import wicket.markup.html.AjaxServerAndClientTimeFilter;

/**
 * Created by <a href="mailto:claudiu.dumitrescu@gmail.com">Claudiu Dumitrescu</a>
 * Flavius Burca <flavius.burca@gmail.com>
 */
public class SenroApplication extends SpringWebApplication {

    MetadataManager metadataManager;
    PersistenceService persistenceService;
    IRulesEngine rulesEngine;

	@Override
	protected void init() {
    	getRequestCycleSettings().addResponseFilter(new AjaxServerAndClientTimeFilter());
    	getDebugSettings().setAjaxDebugModeEnabled(true);
    	getResourceSettings().setThrowExceptionOnMissingResource(true);

    	RulesRepository.initDefaultRepository(getWicketFilter().getFilterConfig().getServletContext().getRealPath("/"));
    	getRulesEngine().setRulesRepository(RulesRepository.DEFAULT_REPOSITORY);
	}

    public MetadataManager getMetadataManager() {
        if (metadataManager == null) {
            synchronized (this) {
                if (metadataManager == null) {
                    metadataManager = (MetadataManager) createSpringBeanProxy(
                            AOPMetadataManager.class, "metadataManager");
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

    public IRulesEngine getRulesEngine() {
    	if (rulesEngine == null) {
    		synchronized (this) {
				if (rulesEngine == null) {
					rulesEngine = (IRulesEngine) createSpringBeanProxy(IRulesEngine.class, "rulesEngine");
				}
			}
    	}
    	return rulesEngine;
    }

    public Class getHomePage() {
        return HomePage.class;
    }

    public Metadata getMetadata(AnnotatedElement annotatedElement) throws NoMetadataFoundException {
        return getMetadataManager().getMetadata(annotatedElement);
    }
}
