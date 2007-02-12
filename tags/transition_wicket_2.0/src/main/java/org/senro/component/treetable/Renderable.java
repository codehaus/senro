package org.senro.component.treetable;

import java.io.Serializable;
import javax.swing.tree.TreeNode;

import wicket.Response;

/**
 * Interface for cell renderers.
 *
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public interface Renderable extends Serializable {

	/**
	 * Renders the content of the cell to the response.
	 *
	 * @param node  The node for the row. Will be null for header
	 * @param response Response where the renderer is supposed to write the content.
	 */
	public void render(TreeNode node, Response response);
}
