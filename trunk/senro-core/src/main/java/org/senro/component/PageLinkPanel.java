package org.senro.component;

import wicket.markup.html.link.IPageLink;
import wicket.markup.html.link.PageLink;
import wicket.markup.html.panel.Panel;
import wicket.markup.html.basic.Label;

/**
 * @authorClaudiu Dumitrescu
 */
public class PageLinkPanel extends Panel {


    public PageLinkPanel(String linkId, IPageLink iPageLink) {
        super("linkPanel");
        PageLink pageLink = new PageLink("link", iPageLink);
        pageLink.add(new Label("link",linkId));
        add(pageLink);
    }
}
