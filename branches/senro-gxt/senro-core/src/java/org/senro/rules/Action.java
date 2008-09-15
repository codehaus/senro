package org.senro.rules;

import java.io.Serializable;

/**
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public class Action implements Serializable {
	private int type;
	private String name;

	public static Action INIT = new Action(100, "Init");
	public static Action LIST_ENTITY = new Action(101, "ListEntity");
	public static Action EDIT_ENTITY = new Action(102, "EditEntity");

	public Action(int type, String name) {
		this.type = type;
		this.name = name;
	}
}
