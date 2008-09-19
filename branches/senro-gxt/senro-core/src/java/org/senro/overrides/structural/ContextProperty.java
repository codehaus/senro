package org.senro.overrides.structural;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public class ContextProperty {
	private String name;
	private String type;
	private List<Meta> metas;

	public ContextProperty() {
		metas = new ArrayList<Meta>();
	}

	public void addMeta(Meta meta) {
		metas.add(meta);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Meta> getMetas() {
		return metas;
	}
}
