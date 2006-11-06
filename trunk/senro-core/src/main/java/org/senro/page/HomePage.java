package org.senro.page;

import org.senro.metadata.model.impl.MetadataClass;
import org.senro.metadata.exception.NoMetadataFoundException;
import org.senro.metadata.Metadata;
import wicket.markup.html.list.ListItem;
import wicket.markup.html.list.ListView;

import java.util.List;

/**
 * @authorClaudiu Dumitrescu
 */
public class HomePage extends BasePage {

    public HomePage() throws Exception {
        super();
        List metadataList = getAllMetadataClass(); //((SenroApplication) getApplication()).getMetadataManager().getAllMetadata(MetadataClass.class);
        ListView listView = new ListView("entities", metadataList) {
            protected void populateItem(final ListItem item) {
                Metadata classMetadata = (Metadata) item.getModelObject();
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
