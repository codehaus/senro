package org.senro.component;

import wicket.markup.html.basic.Label;
import wicket.markup.html.panel.Panel;

import java.io.Serializable;

/**
 * @authorClaudiu Dumitrescu
 */
public class LabelPanelPair implements Serializable {

    private Label label;

    private Panel panel;


    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public Panel getPanel() {
        return panel;
    }

    public void setPanel(Panel panel) {
        this.panel = panel;
    }
}
