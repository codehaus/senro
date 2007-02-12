package org.senro.component;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.senro.component.factory.EntityComponentFactory;
import org.senro.component.grid.GridPanel;
import org.senro.component.grid.Widget;
import org.senro.exception.SenroBaseException;
import org.senro.metadata.Metadata;
import org.senro.metadata.exception.NoMetadataFoundException;
import org.senro.page.ListPage;
import org.senro.servlet.SenroApplication;

import wicket.MarkupContainer;
import wicket.Page;
import wicket.RequestCycle;
import wicket.ajax.AjaxRequestTarget;
import wicket.extensions.ajax.markup.html.tabs.AjaxTabbedPanel;
import wicket.extensions.markup.html.tabs.ITab;
import wicket.markup.html.form.Button;
import wicket.markup.html.form.Form;
import wicket.markup.html.panel.FeedbackPanel;
import wicket.model.Model;

/**
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public class EditForm extends Form {
	public static final int INSERT = 100;
	public static final int UPDATE = 101;

	public EditForm(
			MarkupContainer parent,
			String id,
			final Object entity,
			int mode,
			final FeedbackPanel feedbackPanel)
	{
		super(parent, id, new Model(entity));
		setOutputMarkupId(true);

		EntityComponentFactory componentFactory = new EntityComponentFactory(
				((SenroApplication)getApplication()).getPersistenceService(),
				((SenroApplication)getApplication()).getMetadataManager(),
				mode
		);

		Metadata classMetadata = getMetadata(entity.getClass());

		List<Widget> gridItems = new ArrayList<Widget>();
		List<ITab> tabs = new ArrayList<ITab>();

		/* add all entity fields for now */
        for (Method aField : classMetadata.getProperties()) {
        	Metadata meta = getMetadata(aField);
			componentFactory.createFormWidgets( meta, entity, gridItems, tabs );
        }

        new GridPanel(this, "grid", gridItems);

        AjaxButton<String> btnBack = new AjaxButton<String>(this, "btnBack", "Back") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				doBack();
			}
        };
        Button<String> btnSave = new Button<String>(this, "btnSave", new Model<String>("Save")) {
			@Override
			public void onSubmit() {
				doSave();
			}
        };
        Button<String> btnDelete = new Button<String>(this, "btnDelete", new Model<String>("Delete")) {
			@Override
			public void onSubmit() {
				doDelete();
			}
        };

        btnBack.setDefaultFormProcessing(false);
        btnDelete.setDefaultFormProcessing(false);

        if (mode == INSERT)
        	btnDelete.setEnabled(false);

        if (tabs.size() > 0) {
        	new AjaxTabbedPanel(this, "tabs", tabs) {
        		@Override
        		protected void onAttach() {
        			((SenroApplication)getApplication()).getPersistenceService().startTransaction();
        			super.onAttach();
        		}

        		@Override
        		protected void onDetach() {
        			super.onDetach();
        			((SenroApplication)getApplication()).getPersistenceService().endTransaction();
        		}
        	};
        }
        else
        	new EmptyPanel(this, "tabs", null);
	}


    /**
     * Default Apply button
     *
     * @param form
     */
    private void doDelete() {
        try {
			((SenroApplication)getApplication()).getPersistenceService()
				.remove(getModelObject());
			info("Entity deleted successfuly");
		} catch (SenroBaseException e) {
			Throwable rootCause = e;
			while ( rootCause.getCause() != null) {
                rootCause = rootCause.getCause();
            }
			error(rootCause.getMessage());
		}
    }

    /**
     * Default 'Save' button
     *
     * @param form
     */
    public void doSave() {
    	Object object = getModelObject();

    	try {
			((SenroApplication)getApplication()).getPersistenceService()
				.save(getModelObject());
			info("Entity saved successfuly");
		} catch (SenroBaseException e) {
			Throwable rootCause = e;
			while ( rootCause.getCause() != null) {
                rootCause = rootCause.getCause();
            }
			error(rootCause.getMessage());
		}
    }

    /**
     * Default 'Back' button
     *
     * @param form
     */
    private void doBack() {
    	Metadata metadata = getMetadata(getModelObject().getClass());
    	Page page = new ListPage(metadata);
    	RequestCycle requestCycle = getRequestCycle();
    	requestCycle.setResponsePage(page);
    	requestCycle.setRedirect(true);
	}

	private Metadata getMetadata(AnnotatedElement annotatedElement) {
        Metadata metadata = null;
        try {
            metadata = ((SenroApplication) getApplication()).getMetadata(annotatedElement);
        } catch (NoMetadataFoundException e) {
            e.printStackTrace();
        }
        return metadata;
    }
}
