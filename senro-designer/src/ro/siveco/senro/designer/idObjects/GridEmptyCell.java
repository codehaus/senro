package ro.siveco.senro.designer.idObjects;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Color;

public class GridEmptyCell extends JPanel
{
    public static final Dimension DEFAULT_SIZE = new Dimension(GridView.MIN_COL_WIDTH, GridView.MIN_ROW_HEIGHT);

    public Dimension getPreferredSize()
    {
        return DEFAULT_SIZE;
    }

    public Dimension getMinimumSize()
    {
        return DEFAULT_SIZE;
    }

    public Color getBackground()
    {
       return Color.BLUE;
    }
}
