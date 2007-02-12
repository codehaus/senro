package org.senro.component.treetable;

/**
 * This interface can be used to watch out for TreeTable rendering events.
 * This is useful if you want to alter the default rendering behaviour.
 *
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public interface TreeTableEventListener {
	public void update(TreeTableEvent event);
}
