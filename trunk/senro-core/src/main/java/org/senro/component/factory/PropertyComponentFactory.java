package org.senro.component.factory;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.senro.component.ComponentFactoryCache;
import org.senro.component.ComponentType;
import org.senro.component.EditForm;
import org.senro.component.PanelComponent;
import org.senro.component.grid.AbstractWidget;
import org.senro.component.grid.Widget;
import org.senro.metadata.Metadata;
import org.senro.metadata.MetadataManager;
import org.senro.metadata.util.MetadataUtils;
import org.senro.persistence.PersistenceService;

import wicket.MarkupContainer;
import wicket.extensions.markup.html.tabs.ITab;
import wicket.markup.html.panel.Panel;
import wicket.model.Model;
import wicket.model.PropertyModel;

/**
* @author Flavius Burca <flavius.burca@gmail.com>
*/
public class PropertyComponentFactory extends EntityComponentFactory {
	public static final int MAX_SIZE = 30;

	public PropertyComponentFactory(PersistenceService persistenceService,  MetadataManager metadataManager, int mode) {
		super(persistenceService, metadataManager, mode);
	}

	@Override
	public void createFormWidgets(final Metadata metadata, final Object entity, List<Widget> gridItems, List<ITab> tabs) {
		final Class type = MetadataUtils.getType(metadata);
		final String propertyName = MetadataUtils.getName(metadata);
		final Boolean isRequired = MetadataUtils.isRequired(metadata);
		final int size = MetadataUtils.getSize(metadata, MAX_SIZE);
		final int orderNo = MetadataUtils.getOrderNo(metadata);
		boolean isReadOnly = MetadataUtils.isReadOnly(metadata);
		boolean isUnchangeable = MetadataUtils.isUnchangeable(metadata) && (mode == EditForm.UPDATE);
		final boolean isEnabled = isReadOnly || isUnchangeable;
		final String widgetType = MetadataUtils.getWidget(metadata);

		Widget labelWidget = new AbstractWidget(orderNo) {
			public PanelComponent createComponent(MarkupContainer<?> parent, String id) {
				return ComponentFactoryCache.getComponent(ComponentType.Label, parent, id, new Model<String>(propertyName));
			}
		};

		Widget widget = null;

		// default widget bindings
		if (Boolean.class.isAssignableFrom(type) || widgetType == MetadataUtils.WIDGET_CK) {
			// checkbox
			widget = new AbstractWidget(orderNo) {
				public PanelComponent createComponent(MarkupContainer<?> parent, String id) {
					PanelComponent component = ComponentFactoryCache.getComponent(ComponentType.Boolean, parent, id, new PropertyModel(entity, propertyName));
					component.setRequired(isRequired);
					component.setInternalLabel(propertyName);
					component.setReadOnly(isEnabled);
					return component;
				}
			};
		} else if (Date.class.isAssignableFrom(type)) {
			// date
			widget = new AbstractWidget(orderNo) {
				public PanelComponent createComponent(MarkupContainer<?> parent, String id) {
					PanelComponent component = ComponentFactoryCache.getComponent(ComponentType.Date, parent, id, new PropertyModel(entity, propertyName));
					component.setRequired(isRequired);
					component.setInternalLabel(propertyName);
					component.setReadOnly(isEnabled);
					return component;
				}
			};
		} else if (Short.class.isAssignableFrom(type) || widgetType == MetadataUtils.WIDGET_CB) {
			final Map<String,String> widgetValues = MetadataUtils.getWidgetValues(metadata);
			widget = new AbstractWidget(orderNo) {
				public PanelComponent createComponent(MarkupContainer<?> parent, String id) {
					PanelComponent component = ComponentFactoryCache.getComponent(ComponentType.Combobox, parent, id, new Model(widgetValues));
					component.getComponent().setModel(new PropertyModel(entity, propertyName));
					component.setRequired(isRequired);
					component.setInternalLabel(propertyName);
					component.setReadOnly(isEnabled);
					return component;
				}
			};
		} else if (widgetType == MetadataUtils.WIDGET_TA) {
			widget = new AbstractWidget(orderNo) {
				public Panel createComponent(MarkupContainer<?> parent, String id) {
					PanelComponent component = ComponentFactoryCache.getComponent(ComponentType.TextArea, parent, id, new PropertyModel(entity, propertyName));
					component.setRequired(isRequired);
					component.setInternalLabel(propertyName);
					component.setReadOnly(isEnabled);
					if (size > 0)
						component.setSize(size);
					return component;
				}
			};
		} else {
			// general input field
			widget = new AbstractWidget(orderNo) {
				public PanelComponent createComponent(MarkupContainer<?> parent, String id) {
					PanelComponent component = ComponentFactoryCache.getComponent(ComponentType.Generic, parent, id, new PropertyModel(entity, propertyName));
					component.setRequired(isRequired);
					component.setInternalLabel(propertyName);
					component.setReadOnly(isEnabled);
					if (size > 0)
						component.setSize(size);
					return component;
				}
			};
		}

		labelWidget.setNextWidget(widget);
		gridItems.add(labelWidget);
		gridItems.add(widget);
	}
}
