package org.senro.page;

import org.senro.component.EditForm;
import org.senro.metadata.Metadata;
import org.senro.metadata.util.MetadataUtils;

import wicket.markup.html.panel.FeedbackPanel;

/**
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public class EditPage extends BasePage {

    public EditPage(Metadata classDescriptor) {
        this(classDescriptor, null);
    }

    public EditPage(Metadata classMetadata, Object objectToEdit) {
    	int formMode = EditForm.UPDATE;

        if (objectToEdit == null) {
            try {
            	Class objectType = MetadataUtils.getType(classMetadata);
                objectToEdit = objectType.newInstance();
                formMode = EditForm.INSERT;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        if (formMode == EditForm.UPDATE) {
        	// reclaim the object
        	getPersistenceService().reattach(objectToEdit);
        }

        final FeedbackPanel feedbackPanel = new FeedbackPanel(this, "feebackPanel");
        feedbackPanel.setOutputMarkupId(true);

        final EditForm form = new EditForm(this, "editForm", objectToEdit, formMode, feedbackPanel);
    }
}
