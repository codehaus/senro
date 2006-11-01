package org.senro.page;

import org.apache.commons.beanutils.PropertyUtils;
import org.senro.component.ButtonPanel;
import org.senro.component.ButtonsListView;
import org.senro.component.EditForm;
import org.senro.component.PageLinkPanel;
import org.senro.metadata.impl.MetadataClass;
import org.senro.metadata.util.MetadataManagerUtils;
import org.senro.metadata.exception.NoMetadataFoundException;
import org.senro.metadata.Metadata;
import org.senro.servlet.SenroApplication;
import wicket.Page;
import wicket.RequestCycle;
import wicket.markup.html.WebPage;
import wicket.markup.html.form.Form;
import wicket.markup.html.link.IPageLink;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Claudiu Dumitrescu
 */
public class EditPage extends BasePage {

    public EditPage(MetadataClass classDescriptor) {
        this(classDescriptor, null);
    }

    public EditPage(MetadataClass metadata, Object objectToEdit) {
        if (objectToEdit == null) {
            try {
                objectToEdit = ((Class) PropertyUtils.getProperty(metadata, "type")).newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        final EditForm form = new EditForm("editForm", objectToEdit);

        form.add(new ButtonsListView("buttons", buildButtonsList()));

        add(form);

    }

    /**
     * Build a list with defaults buttons for this type of page.
     *
     * @return
     */
    protected List buildButtonsList() {
        List buttonsList = new ArrayList();
        buttonsList.add(new ButtonPanel("Save") {
            public void onClick() throws NoMetadataFoundException {
                doSave((Form) getPage().get("editForm"));    //To change body of overridden methods use File | Settings | File Templates.
            }
        });

        buttonsList.add(new ButtonPanel("Apply") {
            public void onClick() {
                doApply((Form) getPage().get("editForm"));    //To change body of overridden methods use File | Settings | File Templates.
            }
        });
        return buttonsList;
    }

    /**
     * Default Apply button
     *
     * @param form
     */
    private void doApply(Form form) {
        Object modelObject = form.getModelObject();
        try {
            System.out.println(PropertyUtils.getProperty(modelObject, "author"));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deffault 'Save' button
     *
     * @param form
     */
    public void doSave(Form form) throws NoMetadataFoundException {
        ((SenroApplication) getApplication()).getPersistenceService().save(form.getModelObject());
        RequestCycle requestCycle = getRequestCycle();
        Metadata metadata = getMetadata(MetadataManagerUtils.getUniqueIdentifier(form.getModelObject().getClass()));
        Page page = new ListPage((MetadataClass) metadata);
        requestCycle.setResponsePage(page);
        requestCycle.setRedirect(true);
    }

    public static PageLinkPanel link(final MetadataClass classDescriptor, final Object objectToEdit) {
        return new PageLinkPanel("edit", new IPageLink() {
            public Page getPage() {
                return new EditPage(classDescriptor, objectToEdit);
            }

            public Class getPageIdentity() {
                return EditPage.class;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });

    }
}
