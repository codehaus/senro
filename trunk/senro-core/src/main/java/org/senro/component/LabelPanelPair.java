package org.senro.component;

import wicket.markup.html.basic.Label;
import wicket.markup.html.panel.Panel;
import wicket.markup.MarkupStream;
import wicket.Component;
import wicket.model.IModel;

import java.io.Serializable;

/**
 * @authorClaudiu Dumitrescu
 */
public class LabelPanelPair extends Component implements Serializable {

    private Label label;

    private Panel panel;

    public LabelPanelPair(String id) {
        super(id);
    }

    public LabelPanelPair(String id, IModel model) {
        super(id, model);
    }


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

    /**
     * ATENTION!!! THIS METHOD DOES NOTHING FOR THIS CLASS.
     * @param markupStream
     */
    protected void onRender(final MarkupStream markupStream) {
        //do nothing
    }
}
