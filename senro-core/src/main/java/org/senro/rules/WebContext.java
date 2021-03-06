package org.senro.rules;

import java.io.Serializable;

import org.senro.metadata.Metadata;

import wicket.Page;

/**
 * This class holds general information regarding the web context
 * in which an action take place
 *
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public class WebContext implements Serializable {
    private Action action;
    private Page page;
    private Metadata entity;
    private Metadata property;

    public WebContext() {
    	this(null, null, null, null);
    }

    public WebContext(Action action) {
    	this(action, null, null, null);
    }

    public WebContext(Action action, Page page) {
    	this(action, page, null, null);
    }

    public WebContext(Action action, Page page, Metadata entity) {
    	this(action, page, entity, null);
    }

    public WebContext(Action action, Page page, Metadata entity, Metadata property) {
    	this.action = action;
    	this.page = page;
    	this.entity = entity;
    	this.property = property;
    }

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public Metadata getEntity() {
		return entity;
	}

	public void setEntity(Metadata entity) {
		this.entity = entity;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Metadata getProperty() {
		return property;
	}

	public void setProperty(Metadata property) {
		this.property = property;
	}
}
