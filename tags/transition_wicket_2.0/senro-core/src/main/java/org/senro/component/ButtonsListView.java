package org.senro.component;

import wicket.MarkupContainer;
import wicket.markup.html.list.ListView;
import wicket.markup.html.list.ListItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.senro.metadata.exception.NoMetadataFoundException;

/**
 * @authorClaudiu Dumitrescu
 */
public class ButtonsListView extends ListView {
	public interface ButtonCallback extends Serializable {
		public void onClick();
	}

	public Map<String, ButtonCallback> buttons;

    public ButtonsListView(MarkupContainer parent, String id, Map<String, ButtonCallback> buttons) {
        super(parent, id, new ArrayList<String>(buttons.keySet()));

        this.buttons = buttons;
    }


    protected void populateItem(final ListItem item) {
        ButtonPanel bp = new ButtonPanel(item, item.getModelObjectAsString()) {
			@Override
			public void onClick() throws NoMetadataFoundException {
				buttons.get( item.getModelObjectAsString() ).onClick();
			}
        };
    }
}

