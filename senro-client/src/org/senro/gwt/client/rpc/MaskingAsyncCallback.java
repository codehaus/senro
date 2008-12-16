package org.senro.gwt.client.rpc;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.widget.Component;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author FlaviusB
 */
public abstract class MaskingAsyncCallback<T> implements AsyncCallback<T>{
	private static Component extElement;

	private static void showPleaseWait() {
		extElement = (( Component ) Registry.get( "contentPanel" ));
		extElement.el().mask( "Processing request" );
	}
	
	public static void hidePleaseWait() {
		if ( extElement != null ) 
			extElement.el().unmask();
	}
	
	public void onSuccess(T result) {
	}
	
	public void onFailure(Throwable caught) {
	}
}
