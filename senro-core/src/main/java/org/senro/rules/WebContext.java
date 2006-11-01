package org.senro.rules;

/**
 * This class holds general information regarding the web context in which an action take place
 * Author: Claudiu Dumitrescu
 */
public class WebContext {

    private String pageName;
    private String requestedAction;


    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getRequestedAction() {
        return requestedAction;
    }

    public void setRequestedAction(String requestedAction) {
        this.requestedAction = requestedAction;
    }
}
