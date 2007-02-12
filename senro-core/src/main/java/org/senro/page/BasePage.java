package org.senro.page;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.senro.metadata.Metadata;
import org.senro.metadata.MetadataManager;
import org.senro.metadata.exception.NoMetadataFoundException;
import org.senro.metadata.util.Instance;
import org.senro.metadata.util.MetadataAccessor;
import org.senro.persistence.PersistenceService;
import org.senro.servlet.SenroApplication;

import wicket.markup.html.WebPage;
import wicket.markup.html.link.BookmarkablePageLink;

/**
 * @authorClaudiu Dumitrescu
 */
public abstract class BasePage extends WebPage {
    public BasePage() {
        new BookmarkablePageLink(this, "home-link", HomePage.class);
    }

    /**
     * Get a metadata from metadata management system.
     *
     * @param element Element to search by (e.g class name, a specific method or field).
     * @return A metadata.
     * @throws NoMetadataFoundException
     */
    protected Metadata getMetadata(AnnotatedElement element) throws NoMetadataFoundException {
        MetadataManager metadataManager = ((SenroApplication) getApplication()).getMetadataManager();
        return metadataManager.getMetadata(element);
    }

    /**
     * Get a metadata list containing only class metadata.
     * <p/>
     * ATTENTION: dont't count on this method as it might be removed!
     *
     * @return A metadata list.
     * @throws Exception
     */
    protected List<Metadata> getAllMetadataClass() throws Exception {
        MetadataManager metadataManager = ((SenroApplication) getApplication()).getMetadataManager();
        List<Metadata> metadataList = new ArrayList<Metadata>();
        Iterator<Class> typesSet = ((Collection) PropertyUtils.getProperty(metadataManager, "types")).iterator();
        while (typesSet.hasNext()) {
            Class aClass = typesSet.next();
            metadataList.add(metadataManager.getMetadata(aClass));
        }
        return metadataList;
    }

    @Override
    protected void onAttach() {
    	((SenroApplication)getApplication()).getPersistenceService().startTransaction();
    	super.onAttach();
    }

    @Override
    protected void onDetach() {
    	super.onDetach();
    	((SenroApplication)getApplication()).getPersistenceService().endTransaction();
    }

    /**
     * Return a default persistence service implementing CRUD operations.
     * @return Default persistence service.
     */
    protected PersistenceService getPersistenceService() {
        return ((SenroApplication) getApplication()).getPersistenceService();

    }

    protected void fireRules() {
    	((SenroApplication)getApplication()).getRulesEngine().fireRules();
    }
}
