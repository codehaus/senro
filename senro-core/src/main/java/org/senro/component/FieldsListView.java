package org.senro.component;

import wicket.markup.html.list.ListItem;
import wicket.markup.html.list.ListView;

import java.util.List;

/**
 * @authorClaudiu Dumitrescu
 */
public class FieldsListView extends ListView {

    public FieldsListView(String id, List list) {
        super(id, list);
    }


    protected void populateItem(final ListItem item) {
        LabelPanelPair lpp = (LabelPanelPair) item.getModelObject();
        item.add(lpp.getLabel());
        item.add(lpp.getPanel());
    }
}

