package org.senro.gwt.client.model.ui.context;

import java.io.Serializable;


import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author Flavius Burca
 *
 */
public class EntityRef implements ContextElement, IsSerializable, Serializable {
	private String entityName;
	private Long id;
	
	public EntityRef() {	
	}
	
	public EntityRef( String entityName, Long id ) {
		setEntityName(entityName);
		setId(id);
	}
	
	public String getEntityName() {
		return entityName;
	}
	
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return entityName+", id: "+(id == null ? "" : id);
	}
}
