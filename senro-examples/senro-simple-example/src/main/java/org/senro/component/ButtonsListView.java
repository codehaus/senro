package org.senro.component;

import wicket.markup.html.list.ListView;
import wicket.markup.html.list.ListItem;

import java.util.List;

/**
 * Author: Claudiu Dumitrescu
 */
public class ButtonsListView extends ListView {

    public ButtonsListView(String id, List list) {
        super(id, list);
    }


    protected void populateItem(final ListItem item) {
        ButtonPanel bp= (ButtonPanel) item.getModelObject();
        item.add(bp);
    }
}

