package org.senro.persistence;

import java.util.List;

/**
 * Generic interface for tree entities.
 *
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public interface GenericTreeEntityBean extends GenericEntityBean {
	List<? extends GenericTreeEntityBean> getChildren();
}
