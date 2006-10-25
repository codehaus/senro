package org.senro.examples.simple;

import wicket.protocol.http.WebApplication;
import wicket.Page;

/*
*  Copyright 2004-2006 Brian Topping
*
*/

/**
 * @author Brian Topping
 * @date Sep 27, 2006 2:50:20 PM
 */
public class SimpleExampleApplication extends WebApplication {
    public Class<? extends Page> getHomePage() {
        return Home.class;
    }
}
