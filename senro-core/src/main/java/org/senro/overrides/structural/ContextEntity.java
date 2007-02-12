package org.senro.overrides.structural;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public class ContextEntity {
	private String name;
	private String pkg;
	private List<ContextProperty> properties;
	private List<Meta> metas;

	public ContextEntity() {
		properties = new ArrayList<ContextProperty>();
		metas = new ArrayList<Meta>();
	}

	public void addMeta(Meta meta) {
		metas.add(meta);
	}

	public void addProperty(ContextProperty property) {
		properties.add(property);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPkg() {
		return pkg;
	}

	public void setPkg(String pkg) {
		this.pkg = pkg;
	}

	public List<ContextProperty> getProperties() {
		return properties;
	}

	public List<Meta> getMetas() {
		return metas;
	}
}
