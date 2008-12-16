package org.senro.ui.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.senro.gwt.client.model.ui.context.SenroTask;

/**
 * This is the default mapping of the standard Senro tasks ( see {@link SenroTask} ) to their corresponding
 * template files.
 * 
 * @author FlaviusB
 */
public class TaskDefaults {
	private static Map<String, String> TASK_DEFAULTS = new HashMap<String, String>();
	
	public static String get( String taskName ) {
		return TASK_DEFAULTS.get( taskName );
	}
	
	public static Set<String> getTasks() {
		return TASK_DEFAULTS.keySet();
	}
	
	public static void init( Map<String, String> taskDefaults ) {
		TASK_DEFAULTS.clear();
		TASK_DEFAULTS.putAll(taskDefaults);
	}
}

