package ro.siveco.senro.designer.inspectors;

import ro.siveco.senro.designer.components.SenroTree;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class TreeInspector extends CommonUIInspector
{
    public static final String TREE_INSPECTOR_TITLE = "Tree Inspector";

    protected SenroTree senroTree;

    public TreeInspector()
    {
        title = TREE_INSPECTOR_TITLE;
    }

    protected void init()
    {
        super.init();
        senroTree = null;
    }

    public void setObject(Object o)
    {
        senroTree = (SenroTree) o;
        super.setObject(o);
    }
}