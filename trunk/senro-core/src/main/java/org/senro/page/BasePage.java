package org.senro.page;

import wicket.markup.html.WebPage;
import wicket.markup.html.link.BookmarkablePageLink;
import wicket.markup.html.link.Link;
import wicket.model.IModel;
import org.senro.metadata.Metadata;
import org.senro.metadata.MetadataManager;
import org.senro.metadata.exception.NoMetadataFoundException;
import org.senro.metadata.util.MetadataManagerUtils;
import org.senro.servlet.SenroApplication;

/**
 * Author: Claudiu Dumitrescu
 */
public abstract class BasePage extends WebPage {


    public BasePage() {
        add(new BookmarkablePageLink("home-link", HomePage.class));

    }

    protected Metadata getMetadata(String metadataUniqueIdentifier) throws NoMetadataFoundException {
        MetadataManager metadataManager = ((SenroApplication) getApplication()).getMetadataManager();
        return  metadataManager.getMetadata(metadataUniqueIdentifier);
    }


}
