package org.senro.overrides.structural;

/**
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public class ContextKey {
	private String name;
	private String value;

	public ContextKey() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
