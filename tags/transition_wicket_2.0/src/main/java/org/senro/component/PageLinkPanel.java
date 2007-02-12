package org.senro.component;

import wicket.MarkupContainer;
import wicket.markup.html.link.IPageLink;
import wicket.markup.html.link.PageLink;
import wicket.markup.html.panel.Panel;
import wicket.markup.html.basic.Label;

/**
 * @author Claudiu Dumitrescu
 */
public class PageLinkPanel extends Panel {

    public PageLinkPanel(MarkupContainer parent, String linkId, IPageLink iPageLink) {
        super(parent, "linkPanel");
        PageLink pageLink = new PageLink(this, "link", iPageLink);
        new Label(pageLink, "link",linkId);
    }
}
