package org.senro.component;

import wicket.markup.html.panel.Panel;
import wicket.model.IModel;
import wicket.model.Model;
import wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import wicket.extensions.markup.html.repeater.refreshing.Item;
import wicket.extensions.markup.html.repeater.refreshing.OddEvenItem;
import wicket.Component;
import wicket.AttributeModifier;
import org.senro.metadata.Metadata;
import org.senro.metadata.MetadataManager;
import org.senro.metadata.util.MetadataAccessor;
import org.senro.servlet.SenroApplication;

import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Method;

/**
 * User: <a href="mailto:claudiu.dumitrescu@gmail.com">duclad</a>
 * Date: Dec 13, 2006
 * Time: 11:33:56 AM
 */
public class ListPanel extends Panel {
    static final String VERSION = "$Id: ListPanel.java,v 1.0 Dec 13, 2006 11:33:56 AM";
    private Object selectedEntity;

    public ListPanel(String panelId, final Metadata metadataClass, final MetadataManager metadataManager) {
        super(panelId);
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

        for (Method aField : metadataClass.getProperties()) {
            try {
                Metadata metadataProperty = metadataManager.getMetadata(aField);
                String name = MetadataAccessor.readMetadataInfo(metadataProperty, "name");
                columns.add(new PropertyColumn(new Model(name), name, name));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        MySortableDataProvider dataProvider = new MySortableDataProvider(metadataClass, "id");
        dataProvider.setPersistenceService(((SenroApplication) getApplication()).getPersistenceService());
        add(new DefaultDataTable("table", columns, dataProvider, 8) {
            protected Item newRowItem(String id, int index, IModel model) {
                return new HighlitableDataItem(id, index, model);
            }
        });

    }

    private class HighlitableDataItem extends OddEvenItem {
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
        public HighlitableDataItem(String id, int index, final IModel model) {
            super(id, index, model);

            add(new AttributeModifier("style", true, new Model("background-color:red;")) {
                public boolean isEnabled() {
                    return model.getObject(HighlitableDataItem.this).equals(selectedEntity);
                }
            });
        }
    }
}

