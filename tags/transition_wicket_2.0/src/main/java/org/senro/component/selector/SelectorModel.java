package org.senro.component.selector;

import wicket.model.IModel;
import wicket.model.PropertyModel;

/**
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public class SelectorModel implements IModel {
	private PropertyModel propertyModel;
	private Class entityClass;

	public SelectorModel(Class entityClass, PropertyModel propertyModel) {
		this.entityClass = entityClass;
		this.propertyModel = propertyModel;
	}

	public Object getObject() {
		return propertyModel.getObject();
	}

	public void setObject(Object object) {
		propertyModel.setObject(object);
	}

	public void detach() {
		propertyModel.detach();
	}

	public Class getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class entityClass) {
		this.entityClass = entityClass;
	}

	public PropertyModel getPropertyModel() {
		return propertyModel;
	}

	public void setPropertyModel(PropertyModel propertyModel) {
		this.propertyModel = propertyModel;
	}


}
