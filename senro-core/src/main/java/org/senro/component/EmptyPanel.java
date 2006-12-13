package org.senro.component;

import wicket.markup.html.panel.Panel;
import wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import wicket.extensions.markup.html.repeater.refreshing.Item;
import wicket.extensions.markup.html.repeater.refreshing.OddEvenItem;
import wicket.model.Model;
import wicket.model.IModel;
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
public class EmptyPanel extends Panel {

    public EmptyPanel(String id) {
        super(id);
    }


}
