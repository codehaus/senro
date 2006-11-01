package org.senro.component;

import wicket.markup.html.link.Link;
import wicket.markup.html.panel.Panel;
import wicket.model.IModel;

/**
 * Created by <a href="mailto:claudiu.dumitrescu@gmail.com">Claudiu Dumitrescu</a>
 */
public abstract class ActionPanel extends Panel {
    /**
     * @param id    component id
     * @param model model for contact
     */
    public ActionPanel(String id, IModel model) {
        super(id, model);
        add(new Link("select") {
            public void onClick() {
                doAction(getParent().getModelObject());
            }
        });
    }

    public abstract void doAction(Object modelObject);
}