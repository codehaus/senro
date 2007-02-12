package org.senro.component.treetable.datasource;

import java.io.Serializable;
import java.util.List;

/**
 * This interface defines a data source for populating tree data structures.
 *
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public interface TreeDataSource extends Serializable {

	/**
	 * Adds a filter condition to this datasource.
	 *
	 * @param propertyExpression
	 * @param filterCondition
	 */
	void addFilterCondition(String propertyExpression, String filterCondition);

	/**
	 * Removes all filter conditions for this datasource
	 */
	void clearFilterConditions();

	/**
	 * Get the children of a given node in the tree.
	 *
	 * @param node - The node that children are being requested for.
	 * @return
	 * 		A (possibly empty) array of children for the node
	 */
	List getChildren(Object node);

	Object getRoot();
}
