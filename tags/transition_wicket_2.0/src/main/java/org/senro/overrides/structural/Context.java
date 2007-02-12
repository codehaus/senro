package org.senro.overrides.structural;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public class Context {
	private List<ContextKey> keys;
	private List<ContextEntity> entities;

	public Context() {
		keys = new ArrayList<ContextKey>();
		entities = new ArrayList<ContextEntity>();
	}

	public void addContextKey(ContextKey contextKey) {
		keys.add(contextKey);
	}

	public void addContextEntity(ContextEntity entity) {
		entities.add(entity);
	}

	public List<ContextEntity> getEntities() {
		return entities;
	}

	public List<ContextKey> getKeys() {
		return keys;
	}
}
