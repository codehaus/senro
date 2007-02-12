package org.senro.component.treetable;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.TreeNode;

import org.senro.component.treetable.ColumnPosition.Alignment;
import org.senro.component.treetable.ColumnPosition.Unit;

import wicket.Component;
import wicket.MarkupContainer;
import wicket.RequestCycle;
import wicket.Response;
import wicket.markup.MarkupFragment;
import wicket.markup.MarkupStream;
import wicket.markup.html.WebMarkupContainer;

/**
 * Class that renders cell of columns aligned to the left or to the right.
 *
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public class LeftRightColumnsView extends WebMarkupContainer {

	private final List<Column> columns = new ArrayList<Column>();
	private final List<Component> components = new ArrayList<Component>();
	private TreeNode node;
	private final List<Renderable> renderables = new ArrayList<Renderable>();


	public LeftRightColumnsView(MarkupContainer parent, String id, TreeNode node) {
		super(parent, id);
		setRenderBodyOnly(true);
		this.node = node;
	}

	/**
	 * Adds a column to be rendered.
	 *
	 * @param column
	 *            The column to add
	 * @param component
	 *            The component
	 * @param renderable
	 *            The renderer
	 */
	public void addColumn(Column column, Component component, Renderable renderable) {
		if (column.isVisible()) {
			if (column.getLocation().getAlignment() == Alignment.LEFT) {
				columns.add(column);
				components.add(component);
				renderables.add(renderable);
			}
			else {
				columns.add(0, column);
				components.add(0, component);
				renderables.add(0, renderable);
			}
		}
	}

	protected void onRender(final MarkupStream markupStream) {
		final int markupStart = markupStream.getCurrentIndex();
		Response response = RequestCycle.get().getResponse();

		boolean firstLeft = true;
		boolean rendered = false;

		for (int i = 0; i < columns.size(); ++i) {
			Column column = columns.get(i);
			Component component = components.get(i);
			Renderable renderable = renderables.get(i);

			response.write("<span class=\"b_\" style=\"" + renderColumnStyle(column) + "\">");

			if (column.getLocation().getAlignment() == Alignment.LEFT && firstLeft == true) {
				// first column has different style
				response.write("<span class=\"d_\">");
				firstLeft = false;
			}
			else {
				response.write("<span class=\"c_\">");
			}

			if (component != null)
			{
				markupStream.setCurrentIndex(markupStart);
				component.render(markupStream);
				rendered = true;
			}
			else if (renderable != null)
				renderable.render(node, response);
			else
				throw new IllegalStateException("Either Renderable or Component must be created for this noode");

			response.write("</span></span>\n");
		}

		if (rendered == false)
			markupStream.skipComponent();
	}


	/**
	 * Renders the float css atribute of the given column.
	 *
	 * @param column
	 *            The
	 * @return The column as a string
	 */
	private String renderColumnFloat(Column column)
	{
		ColumnPosition location = column.getLocation();
		if (location.getAlignment() == Alignment.LEFT)
			return "left";
		else if (location.getAlignment() == Alignment.RIGHT)
			return "right";
		else
			throw new IllegalStateException("Wrong column alignment.");
	}

	/**
	 * Renders content of the style attribute for the given column.
	 *
	 * @param column
	 *            The column to render the style attribute from
	 * @return The style as a string
	 */
	private String renderColumnStyle(Column column)
	{
		return "width:" + renderColumnWidth(column) + ";float:" + renderColumnFloat(column);
	}

	/**
	 * Renders width of given column as string.
	 *
	 * @param column
	 *            The column to render as a string
	 * @return The column as a string
	 */
	private String renderColumnWidth(Column column)
	{
		ColumnPosition location = column.getLocation();
		return "" + location.getSize() + renderUnit(location.getUnit());
	}

	/**
	 * Renders given unit as string.
	 *
	 * @param unit The unit to render to a string
	 * @return The unit as a string
	 */
	private String renderUnit(Unit unit)
	{
		if (unit == Unit.EM)
			return "em";
		else if (unit == Unit.PX)
			return "px";
		else if (unit == Unit.PERCENT)
			return "%";
		else
			throw new IllegalStateException("Wrong column unit for column aligned left or right.");
	}

	@Override
	public final MarkupFragment getMarkupFragment(final String subPath) {
		return getMarkupFragment();
	}
}
