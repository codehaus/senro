package org.senro.examples.simple;

import wicket.markup.html.WebPage;
import wicket.markup.html.basic.Label;

/*
*  Copyright 2004-2006 Brian Topping
*
*/

/**
 * @author Brian Topping
 * @date Sep 27, 2006 2:37:39 PM
 */
public class Home extends WebPage {
    public Home() {
        new Label("message", "Hello World!");
    }
}
