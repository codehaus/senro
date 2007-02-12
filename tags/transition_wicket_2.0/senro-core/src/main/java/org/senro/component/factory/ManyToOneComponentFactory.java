package org.senro.component.factory;

import java.util.List;

import org.senro.component.ComponentFactoryCache;
import org.senro.component.ComponentType;
import org.senro.component.EditForm;
import org.senro.component.PanelComponent;
import org.senro.component.grid.AbstractWidget;
import org.senro.component.grid.Widget;
import org.senro.component.selector.SelectorModel;
import org.senro.metadata.Metadata;
import org.senro.metadata.MetadataManager;
import org.senro.metadata.util.MetadataUtils;
import org.senro.persistence.PersistenceService;

import wicket.MarkupContainer;
import wicket.extensions.markup.html.tabs.ITab;
import wicket.model.Model;
import wicket.model.PropertyModel;

/**
* @author Flavius Burca <flavius.burca@gmail.com>
*/
public class ManyToOneComponentFactory extends EntityComponentFactory {

	public ManyToOneComponentFactory(PersistenceService persistenceService,  MetadataManager metadataManager, int mode) {
		super(persistenceService, metadataManager, mode);
	}

	@Override
	public void createFormWidgets(final Metadata metadata, final Object entity, List<Widget> gridItems, List<ITab> tabs) {
		final String propertyName = MetadataUtils.getName(metadata);
		final Boolean isRequired = MetadataUtils.isRequired(metadata);
		boolean isReadOnly = MetadataUtils.isReadOnly(metadata);
		boolean isUnchangeable = MetadataUtils.isUnchangeable(metadata) && (mode == EditForm.UPDATE);
		final boolean isEnabled = isReadOnly || isUnchangeable;
		final int orderNo = MetadataUtils.getOrderNo(metadata);

		Widget labelWidget = new AbstractWidget(orderNo) {
			public PanelComponent createComponent(MarkupContainer<?> parent, String id) {
				return ComponentFactoryCache.getComponent(ComponentType.Label, parent, id, new Model<String>(propertyName));
			}
		};

		final Class fkType = MetadataUtils.getType(metadata);
		final SelectorModel selectorModel = new SelectorModel(fkType, new PropertyModel(entity, propertyName));

		Widget widget = new AbstractWidget(orderNo) {
			public PanelComponent createComponent(MarkupContainer<?> parent, String id) {
				PanelComponent component = ComponentFactoryCache.getComponent(ComponentType.Selector, parent, id, selectorModel);
				component.setReadOnly(isEnabled);
				return component;
			}
		};

		labelWidget.setNextWidget(widget);
		gridItems.add(labelWidget);
		gridItems.add(widget);
	}
}
