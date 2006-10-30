package org.senro.page;

import org.senro.servlet.SenroApplication;
import org.senro.metadata.Metadata;
import org.senro.metadata.impl.MetadataClass;
import wicket.markup.html.list.ListItem;
import wicket.markup.html.list.ListView;

import java.util.List;

/**
 * Author: Claudiu Dumitrescu
 */
public class HomePage extends BasePage {

    public HomePage() {
        super();
        List metadataList = ((SenroApplication) getApplication()).getMetadataManager().getAllMetadata();
        ListView listView = new ListView("entities", metadataList) {
            protected void populateItem(final ListItem item) {
                MetadataClass classMetadata = (MetadataClass) item.getModelObject();
                try {
                    item.add(ListPage.link(classMetadata));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        add(listView);

    }

}
