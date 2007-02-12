package org.senro.component.treetable;

import java.io.Serializable;

import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;

import wicket.Component;
import wicket.MarkupContainer;
import wicket.RequestCycle;
import wicket.ResourceReference;
import wicket.Response;
import wicket.ajax.AjaxRequestTarget;
import wicket.ajax.markup.html.AjaxFallbackLink;
import wicket.ajax.markup.html.AjaxLink;
import wicket.behavior.HeaderContributor;
import wicket.markup.ComponentTag;
import wicket.markup.MarkupStream;
import wicket.markup.html.WebMarkupContainer;
import wicket.markup.html.link.Link;
import wicket.markup.html.tree.DefaultAbstractTree;
import wicket.model.IModel;
import wicket.model.Model;

/**
 * Tree class that contains functions related to presentation
 *
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public abstract class DefaultBaseTree extends BaseTree {
	/**
	 * The type of junction links and node selection links.
	 */
	public enum LinkType { AJAX, AJAX_FALLBACK, REGULAR }

	/**
	 * The type of tree:
	 * 	REGULAR - displays a regular tree
	 *  LIST - displays a simple list, organised as a rootless tree with
	 *  items as children for the null root node.
	 */
	public enum TreeType { REGULAR, LIST }

	public interface LinkCallback extends Serializable {
		void onClick(AjaxRequestTarget target);
	}

	/**
	 * Reference to the css file.
	 */
	private static final ResourceReference CSS =
		new ResourceReference(DefaultBaseTree.class, "tree.css");

	/** Reference to the icon of closed tree folder */
	private static final ResourceReference FOLDER_CLOSED =
		new ResourceReference(DefaultBaseTree.class, "folder-closed.gif");

	/** Reference to the icon of open tree folder */
	private static final ResourceReference FOLDER_OPEN =
		new ResourceReference(DefaultBaseTree.class, "folder-open.gif");

	/** Reference to the icon of tree item (not a folder) */
	private static final ResourceReference ITEM =
		new ResourceReference(DefaultBaseTree.class, "item.gif");

	private LinkType linkType = LinkType.AJAX;
	private TreeType treeType = TreeType.REGULAR;

	public DefaultBaseTree(MarkupContainer parent, String id, IModel<TreeModel> model, TreeType treeType) {
		super(parent, id, model);
		setTreeType(treeType);
		init();
	}

	/**
	 * Returns the current type of links on tree items.
	 *
	 * @return The link type
	 */
	public LinkType getLinkType() {
		return linkType;
	}

	/**
	 * Sets the type of links on tree items. After the link type is changed, the
	 * whole tree is rebuild and re-rendered.
	 *
	 * @param linkType
	 *            type of links
	 */
	public void setLinkType(LinkType linkType) {
		if (this.linkType != linkType) {
			this.linkType = linkType;
			invalidateAll();
		}
	}

	protected ResourceReference getCSS() {
		return CSS;
	}

	/**
	 * Returns the resource reference of default closed tree folder.
	 *
	 * @return The package resource reference
	 */
	protected ResourceReference getFolderClosed() {
		return FOLDER_CLOSED;
	}

	/**
	 * Returns the resource reference of default open tree folder.
	 *
	 * @return The package resource reference
	 */
	protected ResourceReference getFolderOpen() {
		return FOLDER_OPEN;
	};

	/**
	 * Returns the resource reference of default tree item (not folder).
	 *
	 * @return The package resource reference
	 */
	protected ResourceReference getItem() {
		return ITEM;
	}

	/**
	 * Returns the resource reference for icon of specified tree node.
	 *
	 * @param node
	 *            The node
	 * @return The package resource reference
	 */
	protected ResourceReference getNodeIcon(TreeNode node) {
		if (node.isLeaf())
			return getItem();
		else {
			if (isNodeExpanded(node))
				return getFolderOpen();
			else
				return getFolderClosed();
		}
	}

	/**
	 * Creates the indentation element. This element should be placed as first
	 * element in the tree item markup to ensure proper indentaion of the tree
	 * item. This implementation also takes care of lines that connect nodes.
	 *
	 * @param parent
	 *            The component parent
	 * @param id
	 *            The component id
	 * @param node
	 *            The tree node for which to create the identation element
	 * @param level
	 *            The current level
	 * @return The indentation component
	 */
	protected Component newIndentation(MarkupContainer parent, String id, final TreeNode node,
			final int level) {

		WebMarkupContainer result = new WebMarkupContainer(parent, id) {
			protected void onComponentTagBody(MarkupStream markupStream, ComponentTag openTag) {
				Response response = RequestCycle.get().getResponse();
				TreeNode parent = node.getParent();

				CharSequence urls[] = new CharSequence[level];
				for (int i = 0; i < level; ++i) {
					if (isNodeLast(parent))
						urls[i] = "indent-blank";
					else
						urls[i] = "indent-line";

					parent = parent.getParent();
				}

				for (int i = level - 1; i >= 0; --i)
					response.write("<span class=\"" + urls[i] + "\"></span>");
			}
		};

		result.setRenderBodyOnly(true);
		return result;
	}

	/**
	 * Creates an image placed on junction link. This image actually consists of
	 * two spans with different css classes. These classes are specified
	 * according to the stylesheet to make the junction image look well together
	 * with lines connecting nodes.
	 *
	 * @param parent
	 *            The component parent
	 * @param id
	 *            The component id
	 * @param node
	 *            The tree node
	 * @return The component that resprents a junction
	 */
	protected MarkupContainer newJunctionImage(MarkupContainer parent, final String id,
			final TreeNode node) {
		WebMarkupContainer result = new WebMarkupContainer(parent, id) {
			protected void onComponentTag(ComponentTag tag) {
				super.onComponentTag(tag);

				final String cssClassInner;

				if (!node.isLeaf())
					cssClassInner = isNodeExpanded(node) ? "minus" : "plus";
				else if (treeType == TreeType.REGULAR)
					cssClassInner = "corner";
				else
					cssClassInner = "";


				final String cssClassOuter;

				if (treeType == TreeType.REGULAR) {
					if (isNodeLast(node))
						cssClassOuter = "junction-last";
					else
						cssClassOuter = "junction";
				}
				else {
					cssClassOuter = "";
				}


				Response response = RequestCycle.get().getResponse();
				response.write("<span class=\"" + cssClassOuter + "\"><span class=\""
						+ cssClassInner + "\"></span></span>");
			}
		};

		result.setRenderBodyOnly(true);
		return result;
	}

	/**
	 * Creates the junction link for given node. Also (optionally) creates the
	 * junction image. If the node is a leaf (it has no children), the created
	 * junction link is non-functional.
	 *
	 * @param parent
	 *            parent component of the link
	 *
	 * @param id
	 *            wicket:id of the component
	 *
	 * @param imageId
	 *            wicket:id of the image. this can be null, in that case image
	 *            is not created. image is supposed to be placed on the link
	 *            (link is parent of image)
	 *
	 * @param node
	 *            tree node for which the link should be created.
	 * @return The link component
	 */
	protected Component newJunctionLink(MarkupContainer parent, final String id,
			final String imageId, final TreeNode node) {

		final MarkupContainer junctionLink;

		if (!node.isLeaf()) {
			junctionLink = newLink(parent, id, new LinkCallback() {
				public void onClick(AjaxRequestTarget target) {
					if (isNodeExpanded(node))
						getTreeState().collapseNode(node);
					else
						getTreeState().expandNode(node);

					onJunctionLinkClicked(target, node);
					updateTree(target);
				}
			});
		}
		else {
			junctionLink = new WebMarkupContainer(parent, id) {
				protected void onComponentTag(ComponentTag tag) {
					super.onComponentTag(tag);
					tag.put("onclick", "return false");
				}
			};
		}

		if (imageId != null)
			newJunctionImage(junctionLink, imageId, node);

		return junctionLink;
	}


	/**
	 * Creates a link of type specified by current linkType. When the links is
	 * clicked it calls the specified callback.
	 *
	 * @param parent
	 *            The parent component
	 * @param id
	 *            The component id
	 * @param callback
	 *            The link call back
	 * @return The link component
	 */
	private MarkupContainer newLink(MarkupContainer parent, String id, final LinkCallback callback) {
		if (getLinkType() == LinkType.REGULAR) {
			return new Link(parent, id) {
				@Override
				public void onClick() {
					callback.onClick(null);
				}
			};
		}
		else if (getLinkType() == LinkType.AJAX) {
			return new AjaxLink(parent, id) {
				@Override
				public void onClick(AjaxRequestTarget target) {
					callback.onClick(target);
				}
			};
		}
		else {
			return new AjaxFallbackLink(parent, id) {
				@Override
				public void onClick(AjaxRequestTarget target) {
					callback.onClick(target);
				}
			};
		}
	}

	/**
	 * Creates the icon for current node. By default uses image reference
	 * specified by {@link DefaultAbstractTree#getNodeIcon(TreeNode)}.
	 *
	 * @param parent
	 *            The parent component
	 * @param id
	 *            The component id
	 * @param node
	 *            The tree node
	 * @return The web component that represents the icon of the current node
	 */
	protected Component newNodeIcon(MarkupContainer parent, String id, final TreeNode node) {
		return new WebMarkupContainer(parent, id) {
			protected void onComponentTag(ComponentTag tag) {
				super.onComponentTag(tag);
				tag.put("style", "background-image: url('" + RequestCycle.get().urlFor(getNodeIcon(node)) + "')");
			}
		};
	}

	/**
	 * Creates a link that can be used to select / unselect the specified node.
	 *
	 * @param parent
	 *            The parent component
	 * @param id
	 *            The component id
	 * @param node
	 *            The parent node
	 * @return The component that represents the link
	 */
	protected MarkupContainer newNodeLink(MarkupContainer parent, String id, final TreeNode node) {
		return newLink(parent, id, new LinkCallback() {
			public void onClick(AjaxRequestTarget target) {
				getTreeState().selectNode(node, !getTreeState().isNodeSelected(node));
				onNodeLinkClicked(target, node);
				updateTree(target);
			}
		});
	}

	/**
	 * Callback function called after user clicked on an junction link. The node
	 * has already been expanded/collapsed (depending on previous status).
	 *
	 * @param target
	 *            Request target - may be null on non-ajax call
	 *
	 * @param node
	 *            Node for which this callback is relevant
	 */
	protected void onJunctionLinkClicked(AjaxRequestTarget target, TreeNode node) {
	}

	/**
	 * This callback method is called after user has selected / deselected the
	 * given node.
	 *
	 * @param target
	 *            Request target - may be null on non-ajax call
	 *
	 * @param node
	 *            Node for which this this callback is fired.
	 */
	protected void onNodeLinkClicked(AjaxRequestTarget target, TreeNode node) {
	}

	/**
	 * Performs the tree initialization. Adds header contribution for the
	 * stylesheet.
	 */
	private void init() {
		ResourceReference css = getCSS();

		if (css != null)
			add(HeaderContributor.forCss(css.getScope(), css.getName()));
	}

	/**
	 * The type of tree: REGULAR or LIST
	 */
	public TreeType getTreeType() {
		return treeType;
	}

	/**
	 * The type of tree: REGULAR or LIST
	 */
	public void setTreeType(TreeType treeType) {
		this.treeType = treeType;
	}
}
