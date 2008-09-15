package org.senro.ui;

import org.senro.gwt.client.exception.SenroUIException;
import org.senro.gwt.client.model.ui.SenroContainerComponent;
import org.senro.gwt.client.model.ui.context.SenroContext;

/**
 * 
 * @author Flavius Burca
 *
 */
public interface ComponentFactory {
	public SenroContainerComponent createComponent( SenroContext ctx ) throws SenroUIException;
}
