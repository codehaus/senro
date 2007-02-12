package org.senro.component.treetable;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Treetable rendering events
 *
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public class TreeTableEvent implements Serializable {
	public static int ADD_HEADER_COLUMN = 100;
	public static int ADD_FILTER_COLUMN = 101;
	public static int ADD_DATA_COLUMN = 102;

	public int event;
	public Object[] objects;

	public TreeTableEvent(int event, Object... objects) {
		this.event = event;
		this.objects = objects;
	}

	public List<?> getAssociatedObjects() {
		return Arrays.asList(objects);
	}

	public int getEvent() {
		return event;
	}
}
