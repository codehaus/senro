package org.senro.component;

import wicket.markup.html.basic.Label;
import wicket.markup.html.panel.Panel;
import wicket.model.Model;

/**
 * Author: Claudiu Dumitrescu
 */
public class LabelPanel extends Panel {


    public LabelPanel(String label) {
        super("labelPanel");
        Label lbl = new Label("label", label);
        add(lbl);
    }
}
