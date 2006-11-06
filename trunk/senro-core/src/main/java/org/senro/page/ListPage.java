package org.senro.page;

import org.apache.commons.beanutils.PropertyUtils;
import org.senro.component.*;
import org.senro.metadata.impl.MetadataClass;
import org.senro.metadata.impl.MetadataProperty;
import org.senro.metadata.util.MetadataManagerUtils;
import org.senro.metadata.util.MetadataAccessor;
import org.senro.servlet.SenroApplication;
import wicket.AttributeModifier;
import wicket.Component;
import wicket.Page;
import wicket.RequestCycle;
import wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import wicket.extensions.markup.html.repeater.refreshing.Item;
import wicket.extensions.markup.html.repeater.refreshing.OddEvenItem;
import wicket.markup.html.form.Form;
import wicket.markup.html.link.IPageLink;
import wicket.model.IModel;
import wicket.model.Model;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @authorClaudiu Dumitrescu
 */
public class ListPage extends BasePage {

    private Object selectedEntity;


    public ListPage() {
    }


    public ListPage(final MetadataClass metadataClass) {

        List columns = new ArrayList();


        columns.add(new AbstractColumn(new Model("Select")) {
            public void populateItem(final Item cellItem, final String componentId, final IModel rowModel) {
                cellItem.add(new ActionPanel("cell", rowModel) {
                    public void doAction(Object modelObject) {
                        selectedEntity = modelObject;
                        Component item = cellItem;
                        while (!item.getClass().equals(HighlitableDataItem.class)) {
                            item = item.getParent();
                        }
                        ((HighlitableDataItem) item).toggleHighlite();
                    }
                });
            }
        });

        // this is probably broken?
        for (Method aField : metadataClass.getProperties()) {
            try {
                MetadataProperty metadataProperty = (MetadataProperty) getMetadata(aField);
                String name = (String) PropertyUtils.getProperty(metadataProperty, "name");
                columns.add(new PropertyColumn(new Model(name), name, name));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        MySortableDataProvider dataProvider = new MySortableDataProvider(metadataClass, "id");
        dataProvider.setPersistenceService(((SenroApplication) getApplication()).getPersistenceService());
        add(new DefaultDataTable("table", columns, dataProvider, 8) {
            protected Item newRowItem(String id, int index, IModel model) {
                return new HighlitableDataItem(id, index, model);    //To change body of overridden methods use File | Settings | File Templates.
            }
        });

        Form form = new Form("actionsForm");

        List buttonsList = new ArrayList();
        buttonsList.add(new ButtonPanel("Add") {
            public void onClick() {
                doAdd(metadataClass);    //To change body of overridden methods use File | Settings | File Templates.
            }
        });
        buttonsList.add(new ButtonPanel("Edit") {
            public void onClick() {
                doEdit(metadataClass, selectedEntity);    //To change body of overridden methods use File | Settings | File Templates.
            }
        });

        buttonsList.add(new ButtonPanel("Delete") {
            public void onClick() {
                doDelete(selectedEntity);    //To change body of overridden methods use File | Settings | File Templates.
            }
        });
        form.add(new ButtonsListView("buttons", buttonsList));
        add(form);
    }


    public static PageLinkPanel link(final MetadataClass metadata) throws Exception {

        return new PageLinkPanel(MetadataAccessor.readMetadataInfo(metadata, "displayName"), new IPageLink() {
            public Page getPage() {
                return new ListPage(metadata);
            }

            public Class getPageIdentity() {
                return ListPage.class;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });

    }


    /**
     * Build a list with defaults buttons for this type of page.
     *
     * @return
     */
//     protected List buildButtonsList() {
//
//         return buttonsList;
//     }
    private void doDelete(Object selectedEntity) {
        //To change body of created methods use File | Settings | File Templates.
    }

    private void doEdit(MetadataClass metadataClass, Object selectedEntity) {
        RequestCycle requestCycle = getRequestCycle();
        Page page = new EditPage(metadataClass, selectedEntity);
        requestCycle.setResponsePage(page);
        requestCycle.setRedirect(true);
    }

    /**
     * Sends the user to the add new entity page
     *
     * @param metadataClass Class metadata for entity to be created.
     */
    protected void doAdd(MetadataClass metadataClass) {
        RequestCycle requestCycle = getRequestCycle();
        Page page = new EditPage(metadataClass);
        requestCycle.setResponsePage(page);
        requestCycle.setRedirect(true);
    }

    private static class HighlitableDataItem extends OddEvenItem {
        private boolean highlite = false;

        /**
         * toggles highlite
         */
        public void toggleHighlite() {
            highlite = !highlite;
        }

        /**
         * Constructor
         *
         * @param id
         * @param index
         * @param model
         */
        public HighlitableDataItem(String id, int index, IModel model) {
            super(id, index, model);
            add(new AttributeModifier("style", true, new Model("background-color:red;")) {
                public boolean isEnabled() {
                    return highlite;
                }
            });
        }
    }

}
