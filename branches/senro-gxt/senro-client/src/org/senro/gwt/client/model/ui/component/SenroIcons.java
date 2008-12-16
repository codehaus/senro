package org.senro.gwt.client.model.ui.component;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ImageBundle;

/**
 * GWT {@link ImageBundle} for Senro icons
 * 
 * @author CristiS
 */
public interface SenroIcons extends ImageBundle {
	@Resource("org/senro/gwt/client/model/ui/component/fold_on.gif")
	public AbstractImagePrototype selectorOn();
	
	@Resource("org/senro/gwt/client/model/ui/component/fold_off.gif")
	public AbstractImagePrototype selectorOff();
}
