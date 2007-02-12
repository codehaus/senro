package org.senro.component;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import wicket.MarkupContainer;
import wicket.markup.html.form.Form;
import wicket.model.IModel;

public class ComponentFactoryCache implements Serializable {
	public static Map<ComponentType, Class<? extends PanelComponent>> defaultWidgetMapping
			= new HashMap<ComponentType, Class<? extends PanelComponent>>();

	public static PanelComponent getComponent(ComponentType type, MarkupContainer<?> parent, String id, IModel model) {
		Class<? extends PanelComponent> componentClass =
			defaultWidgetMapping.get(type);

		if (componentClass == null)
			return null;

		try {
			Constructor<? extends PanelComponent> constructor = componentClass.getConstructor(
					new Class[] { MarkupContainer.class, String.class, IModel.class });
			PanelComponent component =
				constructor.newInstance(new Object[] { parent, id, model });
			return component;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
