package org.senro.persistence;

import java.io.Serializable;
import java.util.List;

/**
 * Generic interface for tree entities.
 *
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public interface GenericTreeEntityBean extends Serializable {
	List<? extends GenericTreeEntityBean> getChildren();
}
