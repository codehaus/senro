package org.senro.component.treetable;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.swing.tree.TreeNode;

import org.senro.component.treetable.ColumnPosition.Unit;

import wicket.Component;
import wicket.MarkupContainer;
import wicket.RequestCycle;
import wicket.Response;
import wicket.markup.MarkupFragment;
import wicket.markup.MarkupStream;
import wicket.response.NullResponse;

/**
 * Class that renders cells of columns that are center-aligned.
 * Keeps track of widths and column spans.
 *
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public class CenterColumnsView extends MarkupContainer {

	private final List<Column> columns = new ArrayList<Column>();
	private final List<Component> components = new ArrayList<Component>();
	private TreeNode node;
	private final List<Renderable> renderables = new ArrayList<Renderable>();
	private boolean treeHasLeftColumn;

	public CenterColumnsView(MarkupContainer parent, String id, TreeNode node, boolean treeHasLeftColumn) {
		super(parent, id);
		this.node = node;
		this.treeHasLeftColumn = treeHasLeftColumn;
	}

	/**
	 * Adds a column to be rendered.
	 *
	 * @param column
	 *            The column to render
	 * @param component
	 *            The component
	 * @param renderable
	 *            The renderer
	 */
	public void addColumn(Column column, Component component, Renderable renderable) {
		if (column.isVisible())
		{
			columns.add(column);
			components.add(component);
			renderables.add(renderable);
		}
	}

	/**
	 * Computes the percentagle widths of columns. If a column spans over other
	 * columns, the widths of those columns will be zero.
	 *
	 * @return widths of columns
	 */
	protected double[] computeColumnWidths() {
		double result[] = new double[columns.size()];
		Arrays.fill(result, 0d);

		double sum = 0d;
		double whole = 99.8d;

		for (Column column : columns) {
			if (column.getLocation().getUnit() != Unit.PROPORTIONAL)
				throw new IllegalStateException("CENTER aligned columns must have PROPORTIONAL unit set.");

			sum += column.getLocation().getSize();
		}

		int index = 0;
		int spanColumn = 0;
		int spanLeft = 0;

		for (Column column : columns) {
			int ix = index;

			if (spanLeft > 0) {
				ix = spanColumn;
				--spanLeft;
			}

			result[ix] += Math.round((column.getLocation().getSize()) / sum * whole);

			if (spanLeft == 0 && column.getSpan(node) > 1) {
				int maxSpan = columns.size() - columns.indexOf(column);
				int span = column.getSpan(node) - 1;
				spanColumn = index;
				spanLeft = span < maxSpan ? span : maxSpan;
			}

			++index;
		}

		double together = 0d;

		for (int i = 0; i < result.length; i++)
			together += result[i];

		if (together > 99.8d) {
			for (int i = result.length - 1; i >= 0; --i) {
				if (result[i] != 0d) {
					result[i] -= together - 99.8d;
					break;
				}
			}
		}

		return result;
	}

	@Override
	protected void onRender(final MarkupStream markupStream) {
		final int markupStart = markupStream.getCurrentIndex();
		Response response = RequestCycle.get().getResponse();
		double widths[] = computeColumnWidths();

		boolean rendered = false;

		NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
		nf.setMaximumFractionDigits(0);
		nf.setMaximumFractionDigits(2);

		for (int i = 0; i < columns.size(); ++i) {
			Component component = (Component)components.get(i);
			Renderable renderable = (Renderable)renderables.get(i);
			Column column = (Column)columns.get(i);

			response.write("<span class=\"b_\" style=\"width:" + nf.format(widths[i]) + "%\">");

			if (!treeHasLeftColumn && i == 0)
				response.write("<span class=\"d_\">");
			else
				response.write("<span class=\"c_\">");

			if (component != null) {
				markupStream.setCurrentIndex(markupStart);
				component.render(markupStream);
				rendered = true;
			}
			else if (renderable != null)
				renderable.render(node, response);
			else
				throw new IllegalStateException("Either Renderable or Component must be created for this noode");

			response.write("</span></span>\n");

			int span = column.getSpan(node);

			if (span > 1) {
				for (int j = 1; j < span && i < components.size(); ++j) {
					++i;
					if (components.get(i) != null) {
						Response old = RequestCycle.get().setResponse(NullResponse.getInstance());
						markupStream.setCurrentIndex(markupStart);
						components.get(i).render(markupStream);
						RequestCycle.get().setResponse(old);
						rendered = true;
					}
				}
			}
		}

		if (rendered == false)
			markupStream.skipComponent();
	}

	@Override
	public final MarkupFragment getMarkupFragment(final String subPath) {
		return getMarkupFragment();
	}

}
