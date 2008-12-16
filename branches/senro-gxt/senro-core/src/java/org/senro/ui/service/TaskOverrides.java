package org.senro.ui.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * A basic mechanism for overriding the default task to template mappings defined in the {@link TaskDefaults}
 * class.
 * <p> 
 * This relies on a properties file named <i>TaskOverrides.properties</i> that must 
 * lie at the root of the classpath.
 * </p>
 * The properties file lines contain the following information:
 * <p>
 * qualified_entity_name.task = template_file
 * </p>
 * <p>
 * (e.g. ro.siveco.svapnt.common.entity.City.list = SimpleForm.xml )
 *  </p>
 *  
 * @author FlaviusB
 */
public class TaskOverrides {
	public static Map<String, String> TASK_OVERRIDES = new HashMap<String, String>();

	public static String get( String entity, String task ) {
		return TASK_OVERRIDES.get( entity+"."+task );
	}
	
	public static Set<String> getEntityTasks() {
		return TASK_OVERRIDES.keySet();
	}
	
	public static void init( Map<String, String> taskDefaults ) {
		TASK_OVERRIDES.clear();
		TASK_OVERRIDES.putAll(taskDefaults);
	}
}
