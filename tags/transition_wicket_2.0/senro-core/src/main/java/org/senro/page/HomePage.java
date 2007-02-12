package org.senro.page;

import java.util.List;

import org.senro.component.ComponentFactoryCache;
import org.senro.metadata.Metadata;
import org.senro.rules.Action;
import org.senro.rules.ThreadLocalContext;
import org.senro.rules.WebContext;

import wicket.markup.html.list.ListItem;
import wicket.markup.html.list.ListView;

/**
 * @authorClaudiu Dumitrescu
 */
public class HomePage extends BasePage {

    public HomePage() throws Exception {
        super();
        List metadataList = getAllMetadataClass(); //((SenroApplication) getApplication()).getMetadataManager().getAllMetadata(MetadataClass.class);
        ListView listView = new ListView(this, "entities", metadataList) {
            protected void populateItem(final ListItem item) {
                Metadata classMetadata = (Metadata) item.getModelObject();
                try {
                    ListPage.link(item, classMetadata);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        ThreadLocalContext.put(new WebContext(Action.INIT));
        ThreadLocalContext.put(ComponentFactoryCache.defaultWidgetMapping);
        fireRules();
    }

}
