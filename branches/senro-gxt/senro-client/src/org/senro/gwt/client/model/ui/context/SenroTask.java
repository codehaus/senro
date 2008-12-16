package org.senro.gwt.client.model.ui.context;

/**
 * A Senro task is used in conjunction with a {@link SenroContext} to provide information about
 * the UI view context for a specific entity.
 * <p> 
 * The supported values are:
 * <ul>
 * <li>INIT - application initialization view</li>
 * <li>LIST - the list view for the entity</li>
 * <li>VIEW - the read only form view for the entity</li>
 * <li>EDIT - the edit form view for the entity</li>
 * <li>NEW - the insert form view for the entity</li>
 * </ul>
 * </p>
 * 
 * @author FlaviusB
 */
public interface SenroTask  {
	public static final String INIT="init";
	public static final String LIST="list";
	public static final String VIEW="view";
	public static final String EDIT="edit";
	public static final String NEW="new";	
	public static final String SELECTOR="selector";
}
