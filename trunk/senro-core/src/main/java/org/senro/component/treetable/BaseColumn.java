package org.senro.component.treetable;

import javax.swing.tree.TreeNode;

import org.senro.component.EmptyPanel;

import wicket.Component;
import wicket.MarkupContainer;
import wicket.Response;
import wicket.markup.html.basic.Label;
import wicket.model.Model;
import wicket.util.string.Strings;

/**
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public abstract class BaseColumn implements Column {
	private String header = null;
	private ColumnPosition location = null;
	private TreeTable treeTable = null;
	private boolean contentAsTooltip = false;
	private boolean escapeContent = true;
	private String propertyExpression;
	private Component filterComponent = null;
	private Component headerComponent = null;
	private String filterExpression = "";

	public BaseColumn(ColumnPosition location, String header, String propertyExpression) {
		this.location = location;
		this.header = header;
		this.propertyExpression = propertyExpression;
	}

	public abstract String getNodeValue(TreeNode node);

	public boolean isContentAsTooltip() {
		return contentAsTooltip;
	}

	public boolean isEscapeContent() {
		return escapeContent;
	}

	public ColumnPosition getLocation() {
		return location;
	}

	public int getSpan(TreeNode node) {
		return 0;
	}

	public boolean isVisible() {
		return true;
	}

	public boolean isRelocatable() {
		return true;
	}

	public Component newHeader(MarkupContainer parent, String id) {
		headerComponent = new Label(parent, id, header);
		return headerComponent;
	}

	public Component newFilter(final MarkupContainer parent, final String id) {
		//filterComponent =  new AjaxTextField(parent, id, new Model(filterExpression));
		filterComponent = new EmptyPanel(parent,id,null);
		return filterComponent;
	}

	public Component newCell(MarkupContainer parent, String id, TreeNode node, int level) {
		return null;
	}

	public Renderable newCell(TreeNode node, int level) {
		return new Renderable() {
			public void render(TreeNode node, Response response) {
				String content = getNodeValue(node);

				if (isEscapeContent())
					content = Strings.escapeMarkup(content).toString();

				response.write("<span");

				if (isContentAsTooltip())
					response.write(" title=\"" + content + "\"");

				response.write(">");
				response.write(content);
				response.write("</span>");
			}
		};
	}

	public void setTreeTable(TreeTable treeTable) {
		this.treeTable = treeTable;
	}

	protected TreeTable getTreeTable() {
		return treeTable;
	}

	public void setContentAsTooltip(boolean contentAsTooltip) {
		this.contentAsTooltip = contentAsTooltip;
	}

	public void setEscapeContent(boolean escapeContent) {
		this.escapeContent = escapeContent;
	}

	public String getPropertyExpression() {
		return propertyExpression;
	}

	public Component getFilterComponent() {
		return filterComponent;
	}

	public Component getHeaderComponent() {
		return headerComponent;
	}

	public String getFilterExpression() {
		return filterExpression;
	}

	public void setFilterExpression(String filterExpression) {
		this.filterExpression = filterExpression;
	}
}
