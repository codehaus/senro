package org.senro.rules;

/**
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public class FileMonitorEvent {
	public static int NEW_FILE = 100;
	public static int FILE_CHANGED = 200;
	public static int REPOSITORY_LOADED = 300;
	public static int FILE_DELETED = 400;

	private Object object;
	private int event;

	public FileMonitorEvent(int event) {
		this(event, null);
	}

	public FileMonitorEvent(int event, Object object) {
		this.object = object;
		this.event = event;
	}

	public Object getAttachedObject() {
		return object;
	}

	public int getEvent() {
		return event;
	}
}
