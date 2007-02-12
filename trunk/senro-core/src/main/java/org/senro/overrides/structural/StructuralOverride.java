package org.senro.overrides.structural;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public class StructuralOverride {
	private List<Context> contexts;

	public StructuralOverride() {
		contexts = new ArrayList<Context>();
	}

	public void addContext(Context context) {
		contexts.add(context);
	}
}
