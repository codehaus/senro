package org.senro.component;

import wicket.markup.html.basic.Label;
import wicket.markup.html.panel.Panel;
import wicket.markup.MarkupStream;
import wicket.Component;
import wicket.MarkupContainer;
import wicket.model.IModel;

import java.io.Serializable;

/**
 * @author Claudiu Dumitrescu
 */
public class LabelPanelPair extends Component implements Serializable {

    private Label label;
    private Panel panel;

    public LabelPanelPair(MarkupContainer parent, String id) {
        super(parent, id);
    }

    public LabelPanelPair(MarkupContainer parent, String id, IModel model) {
        super(parent, id, model);
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

	@Override
	protected void onRender(MarkupStream markupStream) {
	}
}
