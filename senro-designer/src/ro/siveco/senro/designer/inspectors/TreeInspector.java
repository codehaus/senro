package ro.siveco.senro.designer.inspectors;

import ro.siveco.senro.designer.components.SenroTree;

import java.awt.event.ActionEvent;

public class TreeInspector extends CommonUIInspector
{
    public static final String TREE_INSPECTOR_TITLE = "Tree Inspector";

    protected SenroTree senroTree = null;

    public TreeInspector()
    {
        super();
        title = TREE_INSPECTOR_TITLE;
    }

    public void setObject(Object o)
    {
        senroTree = (SenroTree) o;
        super.setObject(o);
        updateUI();
    }

    public void updateUI()
    {
        if (senroTree == null) {
            return;
        }
        super.updateUI();
    }

    public void actionPerformed(ActionEvent e)
    {
        super.actionPerformed(e);
        updateUI();
    }
}