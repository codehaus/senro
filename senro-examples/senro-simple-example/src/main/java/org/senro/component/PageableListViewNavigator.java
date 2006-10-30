package org.senro.component;

import wicket.markup.html.basic.Label;
import wicket.markup.html.list.PageableListView;
import wicket.markup.html.navigation.paging.PagingNavigator;
import wicket.model.PropertyModel;
import wicket.util.string.AppendingStringBuffer;

/**
 * A customized navigation bar for the lists
 * <p/>
 * Author: Claudiu Dumitrescu
 */
public class PageableListViewNavigator extends PagingNavigator {
    private final PageableListView pageableListView;

    /**
     * @param id
     * @param pageableListView
     */
    public PageableListViewNavigator(final String id, final PageableListView pageableListView) {
        super(id, pageableListView);

        this.pageableListView = pageableListView;
        add(new Label("headline", new PropertyModel(this, "headlineText")));
    }

    /**
     * Subclasses may override it to provide their own text.
     *
     * @return head line text
     */
    public CharSequence getHeadlineText() {
        int firstListItem = pageableListView.getCurrentPage() * pageableListView.getRowsPerPage();
        AppendingStringBuffer buf = new AppendingStringBuffer(80);
        buf.append(String.valueOf(pageableListView.getList().size())).append(
                " items found, displaying ").append(String.valueOf(firstListItem + 1)).append(
                " to ").append(String.valueOf(firstListItem + pageableListView.getRowsPerPage()))
                .append(".");

		return buf;
	}
}
