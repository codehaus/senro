package org.senro.page;

import org.apache.commons.beanutils.PropertyUtils;
import org.senro.metadata.Metadata;
import org.senro.metadata.MetadataManager;
import org.senro.metadata.exception.NoMetadataFoundException;
import org.senro.servlet.SenroApplication;
import wicket.markup.html.WebPage;
import wicket.markup.html.link.BookmarkablePageLink;

import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @authorClaudiu Dumitrescu
 */
public abstract class BasePage extends WebPage {


    public BasePage() {
        add(new BookmarkablePageLink("home-link", HomePage.class));

    }

    protected Metadata getMetadata(AnnotatedElement metadataUniqueIdentifier) throws NoMetadataFoundException {
        MetadataManager metadataManager = ((SenroApplication) getApplication()).getMetadataManager();
        return metadataManager.getMetadata(metadataUniqueIdentifier);
    }

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


}
