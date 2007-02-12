package org.senro.component.treetable.datasource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

import org.senro.io.DetachableTreeModel;

/**
 * Basic abstract implementation of TreeDataSource
 *
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public abstract class BasicTreeDataSource implements TreeDataSource {
	private Map<String, String> filterConditions = new HashMap<String, String>();

	public void addFilterCondition(String propertyExpression, String filterCondition) {
		if (filterConditions.containsKey(propertyExpression))
			filterConditions.remove(propertyExpression);

		filterConditions.put(propertyExpression, filterCondition);
	}

	public void clearFilterConditions() {
		filterConditions.clear();
	}
}
