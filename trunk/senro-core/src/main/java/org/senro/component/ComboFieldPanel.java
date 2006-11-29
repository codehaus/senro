package org.senro.component;

import wicket.markup.html.panel.Panel;
import wicket.markup.html.form.*;
import wicket.markup.ComponentTag;
import wicket.model.PropertyModel;

import java.util.List;

import org.senro.metadata.util.MetadataAccessor;
import org.senro.metadata.Metadata;
import org.senro.metadata.MetadataManager;

/**
 * @authorClaudiu Dumitrescu
 */
public class ComboFieldPanel extends Panel {


    public ComboFieldPanel(String id, Object entity, List choicesList, IChoiceRenderer choiceRenderer) {
        super("valuePanel");
        DropDownChoice listChoice = new DropDownChoice("propertyValue", new PropertyModel(entity, id), choicesList, choiceRenderer);
//todo Claudiu: add abstract method for supporting wantOnSelectionChangedNotifications method.

        add(listChoice);
//        add(formComponentFeedbackBorder);

    }

}
