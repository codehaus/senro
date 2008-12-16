package org.senro.gwt.client.assoc.util;

import java.util.HashMap;
import java.util.Map;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Listener;

public class NonReentrantListener implements Listener {
	private Object listenerGroup;
	private Listener wrappedListener;

	private static Map<Object, Boolean> listenersMap = new HashMap<Object, Boolean>();

	public NonReentrantListener( Object listenerGroup, Listener wrappedListener )
	{
		this.listenerGroup = listenerGroup;
		this.wrappedListener = wrappedListener;
	}

	public void handleEvent( BaseEvent be )
	{
		Boolean inListener;

		if ( ( inListener = listenersMap.get( listenerGroup ) ) == null )
			listenersMap.put( listenerGroup, inListener = Boolean.FALSE );


		if ( !inListener.booleanValue() )
		{
			listenersMap.put( listenerGroup, inListener = Boolean.TRUE );
			wrappedListener.handleEvent( be );
			listenersMap.put( listenerGroup, inListener = Boolean.FALSE );
		}
	}

}
