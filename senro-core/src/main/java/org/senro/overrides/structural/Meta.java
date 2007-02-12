package org.senro.overrides.structural;

/**
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public class Meta {
	private String key;
	private String value;
	private boolean remove;

	public Meta() {}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public boolean isRemove() {
		return remove;
	}

	public void setRemove(boolean remove) {
		this.remove = remove;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
