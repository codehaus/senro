package ro.siveco.senro.designer.idObjects;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

import ro.siveco.senro.designer.ui.CellCoordinates;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;

public class GridView extends JPanel implements ActionListener
{

    private static final long serialVersionUID = 1L;
    public static final String ADD_COLUMN = "Add Column";
    public static final String ADD_ROW = "Add Row";
    public static final String REMOVE_COLUMN = "Remove Column";
    public static final String REMOVE_ROW = "Remove Row";

    public static final int MIN_COL_WIDTH = 10;
    public static final int MIN_ROW_HEIGHT = 10;
    public static final int NO_VALUE = -1;

    private GridModel model = null;

    protected int[] colWidths;
    protected int[] rowHeights;

    private CellCoordinates selectedCell = null;
    private Set<GridSelectionListener> selectionListeners = new HashSet<GridSelectionListener>();
    private Color selectionColor;
    private Color linesColor;
    private int linesWidth;

    private GridMouseListener gridMouseListener = new GridMouseListener();
    private GridKeyListener gridKeyListener = new GridKeyListener();
    private JPopupMenu menu = new JPopupMenu("Modify View");

    public GridView()
    {
        setSelectionColor(Color.RED.darker());
        setLinesWidth(2);
        populatePopupMenu();
        updateUI();
        setEnabled(true);
    }

    public void setEnabled(boolean enabled)
    {
        if(enabled) {
            addMouseListener(gridMouseListener);
            addMouseMotionListener(gridMouseListener);
            setFocusable(true);
            addKeyListener(gridKeyListener);

        } else {
            removeMouseListener(gridMouseListener);
            removeMouseMotionListener(gridMouseListener);
            setFocusable(false);
            removeKeyListener(gridKeyListener);
        }
        selectedCell = null;
    }

    public void addGridSelectionListener(GridSelectionListener grid_selection_listener)
    {
        selectionListeners.add(grid_selection_listener);
    }

    public void removeGridSelectionListener(GridSelectionListener grid_selection_listener)
    {
        selectionListeners.remove(grid_selection_listener);
    }

    public void paint(Graphics g)
    {
        BufferedImage buff = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D buff_g = buff.createGraphics();

        updateComponentsSizes();
        super.paint(buff_g);
        updateColWidths();
        updateRowHeights();
        paintGridComponents(buff_g);
        paintVerticalLines(buff_g);
        paintHorizontalLines(buff_g);
        // draw selection border of the selected cell
        drawSelectedCellBorder(buff_g);
        g.drawImage(buff, 0, 0, getWidth(), getHeight(), this);
    }

    private void paintGridComponents(Graphics g)
    {
        FormLayout layout = model.getLayout();
        int cols = layout.getColumnCount();
        int rows = layout.getRowCount();
        for(int col = 0; col < cols; col++) {
            for(int row = 0; row < rows; row++) {
                Component comp = model.getDataAt(col, row);
                if(comp == null) {
                    continue;
                }
                paintGridCompAtPoint(comp, g, col, row, getTotalColWidthBeforeCol(col), getTotalRowHeightsBeforeRow(row));
            }
        }
    }

    private void paintGridCompAtPoint(Component comp, Graphics g, int col, int row, int cell_origin_x, int cell_origin_y)
    {
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.translate(cell_origin_x, cell_origin_y);
        int cellWidth = colWidths[col];
        int cellHeight = rowHeights[row];
        g2d.setClip(0, 0, cellWidth, cellHeight);
        comp.paint(g2d);
        g2d.dispose();
    }

    private void updateComponentsSizes()
    {
        FormLayout layout = model.getLayout();
        int cols = layout.getColumnCount();
        int rows = layout.getRowCount();
        for(int col = 0; col < cols; col++) {
            for(int row = 0; row < rows; row++) {
                Component comp = model.getDataAt(col, row);
                if(comp == null) {
                    continue;
                }
                Dimension pref_dim = comp.getPreferredSize();
                if(pref_dim.height < MIN_ROW_HEIGHT) {
                    pref_dim.height = MIN_ROW_HEIGHT;
                }
                if(pref_dim.width < MIN_ROW_HEIGHT) {
                    pref_dim.width = MIN_ROW_HEIGHT;
                }
                Dimension min_dim = comp.getMinimumSize();
                if(min_dim.height < MIN_ROW_HEIGHT) {
                    min_dim.height = MIN_ROW_HEIGHT;
                }
                if(min_dim.width < MIN_ROW_HEIGHT) {
                    min_dim.width = MIN_ROW_HEIGHT;
                }
            }
        }
    }

    private void updateColWidths()
    {
        FormLayout layout = model.getLayout();
        int cols = layout.getColumnCount();
        int rows = layout.getRowCount();
        colWidths = new int[cols];
        for(int col = 0; col < cols; col++) {
            colWidths[col] = MIN_COL_WIDTH;
            for(int row = 0; row < rows; row++) {
                Component comp = model.getDataAt(col, row);
                if(comp == null) {
                    continue;
                }
                Dimension comp_dim = comp.getSize();
                colWidths[col] = Math.max(colWidths[col], comp_dim.width);
            }
        }
    }

    private void updateRowHeights()
    {
        FormLayout layout = model.getLayout();
        int cols = layout.getColumnCount();
        int rows = layout.getRowCount();
        rowHeights = new int[rows];
        for(int row = 0; row < rows; row++) {
            rowHeights[row] = MIN_ROW_HEIGHT;
            for(int col = 0; col < cols; col++) {
                Component comp = model.getDataAt(col, row);
                if(comp == null) {
                    continue;
                }
                Dimension comp_dim = comp.getSize();
                rowHeights[row] = Math.max(rowHeights[row], comp_dim.height);
            }
        }
    }

    private void paintHorizontalLines(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setColor(linesColor);
        for(int i = 0; i < rowHeights.length; i++) {
            g2d.drawLine(0, 0, getTotalColWidth(), 0);
            g2d.translate(0, rowHeights[i]);
        }
        g2d.drawLine(0, 0, getTotalColWidth(), 0);
        g2d.dispose();
    }

    private void paintVerticalLines(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setColor(linesColor);
        for(int i = 0; i < colWidths.length; i++) {
            g2d.drawLine(0, 0, 0, getTotalRowHeights());
            g2d.translate(colWidths[i], 0);
        }
        g2d.drawLine(0, 0, 0, getTotalRowHeights());
        g2d.dispose();
    }

    private int getTotalRowHeights()
    {
        int total_h = 0;
        for(int rowHeight = 0; rowHeight < rowHeights.length; rowHeight++) {
            total_h += rowHeights[rowHeight];
        }
        return total_h;
    }

    private int getTotalRowHeightsBeforeRow(int row)
    {
        int total_h = 0;
        for(int rowHeight = 0; rowHeight < row; rowHeight++) {
            total_h += rowHeights[rowHeight];
        }
        return total_h;
    }

    private int getTotalColWidth()
    {
        int total_w = 0;
        for(int colWidth = 0; colWidth < colWidths.length; colWidth++) {
            total_w += colWidths[colWidth];
        }
        return total_w;
    }

    private int getTotalColWidthBeforeCol(int col)
    {
        int total_w = 0;
        for(int colWidth = 0; colWidth < col; colWidth++) {
            total_w += colWidths[colWidth];
        }
        return total_w;
    }

    public void drawSelectedCellBorder(Graphics g)
    {
        if(selectedCell == null || selectedCell.row == NO_VALUE || selectedCell.col == NO_VALUE) {
            return;
        }
        g.setColor(selectionColor);
        int cellWidth = colWidths[selectedCell.col];
        int cellHeight = rowHeights[selectedCell.row];
        for(int i = 0; i < linesWidth; i++) {
            g.drawRect(getTotalColWidthBeforeCol(selectedCell.col) + i,
                       getTotalRowHeightsBeforeRow(selectedCell.row) + i, cellWidth - 2*i, cellHeight - 2*i);
        }
    }

    public GridModel getModel()
    {
        return model;
    }

    public void setModel(GridModel model)
    {
        this.model = model;
        model.setGridView(this);
        if(!isReady()) {
            return;
        }
        refresh();
    }

    public void modelDataDidChanged(List<CellCoordinates> changed_data)
    {
        refresh();
        repaintVisibleRect();
    }

    public boolean isReady()
    {
        return model != null;
    }

    public void refresh()
    {
        removeAll();
        FormLayout layout = model.getLayout();
        setLayout(layout);
        setBorder(null);
        CellConstraints cc = new CellConstraints();
        for(int col = 1; col <= layout.getColumnCount(); col++) {
            for(int row = 1; row <=layout.getRowCount(); row++) {
                Component comp = model.getDataAt(col - 1, row -1);
                if(comp == null) {
                    add(new GridEmptyCell(), cc.xy(col, row));
                } else {
                    add(comp, cc.xy(col, row));
                }
            }
        }
        revalidate();
    }

    protected void repaintVisibleRect()
    {
        Container parent = getParent();
        if(parent != null) {
            if(parent.getParent() instanceof JScrollPane) {
                JScrollPane scroll_pane = (JScrollPane)parent.getParent();
                Rectangle visible_rect = scroll_pane.getViewport().getViewRect();
                repaint(0, visible_rect.getLocation().x,
                        visible_rect.getLocation().y,
                        visible_rect.getSize().width,
                        visible_rect.getSize().height);
            } else {
                repaint();
            }
        }
    }

    public CellCoordinates getSelectedCell()
    {
        return selectedCell;
    }

    public void setSelectedCell(CellCoordinates selected_cell)
    {
        if(selectedCell == null && selected_cell == null) {
            return;
        }
        if(selectedCell != null && selectedCell.equals(selected_cell))
            return;
        selectedCell = selected_cell;
        repaintVisibleRect();
        notifySelectionListeners();
    }

    private void notifySelectionListeners()
    {
        for(GridSelectionListener sel_listener : selectionListeners) {
            sel_listener.selectionDidChange(selectedCell);
        }
    }

    public boolean isCellSelected(int col, int row)
    {
        return isCellSelected(new CellCoordinates(col, row));
    }

    public boolean isCellSelected(CellCoordinates cell_coordinates)
    {
        return selectedCell.equals(cell_coordinates);
    }

    public Color getSelectionColor()
    {
        return selectionColor;
    }

    public void setSelectionColor(Color selection_color)
    {
        selectionColor = selection_color;
        revalidate();
        repaintVisibleRect();
    }

    public Color getLinesColor()
    {
        return linesColor;
    }

    public void setLinesColor(Color lines_color)
    {
        linesColor = lines_color;
        revalidate();
        repaintVisibleRect();
    }

    public int getLinesWidth()
    {
        return linesWidth;
    }

    public void setLinesWidth(int lines_width)
    {
        linesWidth = lines_width;
        revalidate();
        repaintVisibleRect();
    }

    private void populatePopupMenu()
    {
        // Add Column Item
        JMenuItem ac_menu_item = new JMenuItem(ADD_COLUMN);
        ac_menu_item.setActionCommand(ADD_COLUMN);
        ac_menu_item.addActionListener(this);
        menu.add(ac_menu_item);
        // Add Row Item
        JMenuItem ar_menu_item = new JMenuItem(ADD_ROW);
        ar_menu_item.setActionCommand(ADD_ROW);
        ar_menu_item.addActionListener(this);
        menu.add(ar_menu_item);
        // Remove Column Item
        JMenuItem rc_menu_item = new JMenuItem(REMOVE_COLUMN);
        rc_menu_item.setActionCommand(REMOVE_COLUMN);
        rc_menu_item.addActionListener(this);
        menu.add(rc_menu_item);
        // Remove Row Item
        JMenuItem rr_menu_item = new JMenuItem(REMOVE_ROW);
        rr_menu_item.setActionCommand(REMOVE_ROW);
        rr_menu_item.addActionListener(this);
        menu.add(rr_menu_item);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(!isReady()) {
            return;
        }
        String selected_action_cmd = e.getActionCommand();
        if(selected_action_cmd.equals(ADD_COLUMN)) {
            model.addColumn();
        } else if(selected_action_cmd.equals(ADD_ROW)) {
            model.addRow();
        } else if(selected_action_cmd.equals(REMOVE_COLUMN)) {
            model.removeColumn();
        } else if(selected_action_cmd.equals(REMOVE_ROW)) {
            model.removeRow();
        }
    }

    private void switchSelectionInCell(CellCoordinates clickedCell)
    {
        if(selectedCell == clickedCell) {
            selectedCell = null;
        } else {
            selectedCell = clickedCell;
        }
        repaintVisibleRect();
        notifySelectionListeners();
    }

    public int getClickedColumn(int mouse_x)
    {
        if(mouse_x < 0 || mouse_x > getWidth() || colWidths == null) {
            return NO_VALUE;
        }
        int before_clicked_col = 0;
        for(int col = 0; col < colWidths.length; col++) {
            int after_clicked_col = before_clicked_col + colWidths[col];
            if(mouse_x >= before_clicked_col && mouse_x < after_clicked_col) {
                return col;
            } else {
                before_clicked_col = after_clicked_col;
            }
        }
        return NO_VALUE;
    }

    public int getClickedRow(int mouse_y)
    {
        if(mouse_y < 0 || mouse_y > getHeight() || rowHeights == null) {
            return NO_VALUE;
        }
        int before_clicked_row = 0;
        for(int row = 0; row < rowHeights.length; row++) {
            int after_clicked_row = before_clicked_row + rowHeights[row];
            if(mouse_y >= before_clicked_row && mouse_y < after_clicked_row) {
                return row;
            } else {
                before_clicked_row = after_clicked_row;
            }
        }
        return NO_VALUE;
    }

    public class GridMouseListener extends MouseAdapter
    {

        public void mousePressed(MouseEvent e)
        {
            requestFocusInWindow();
            int x = e.getX();
            int y = e.getY();
            if(e.getButton() == MouseEvent.BUTTON3) {
                menu.show(GridView.this, x, y);
                return;
            }
            int selectedCol = getClickedColumn(x);
            int selectedRow = getClickedRow(y);
            CellCoordinates clickedCell = new CellCoordinates(selectedCol, selectedRow);
            if((e.getModifiersEx() & InputEvent.CTRL_DOWN_MASK) == InputEvent.CTRL_DOWN_MASK) {
                switchSelectionInCell(clickedCell);
            } else {
                setSelectedCell(clickedCell);
            }
            model.mousePressedAtCoordinates(clickedCell);
        }

        public void mouseClicked(MouseEvent e)
        {
            int x = e.getX();
            int y = e.getY();
            int selectedCol = getClickedColumn(x);
            int selectedRow = getClickedRow(y);
            CellCoordinates clickedCell = new CellCoordinates(selectedCol, selectedRow);
            model.mouseClickedAtCoordinates(clickedCell);
        }

        public void mouseMoved(MouseEvent e)
        {
            int x = e.getX();
            int y = e.getY();
            int selectedCol = getClickedColumn(x);
            int selectedRow = getClickedRow(y);
            CellCoordinates clickedCell = new CellCoordinates(selectedCol, selectedRow);
            model.mouseMovedAtCoordinates(clickedCell);
        }
    }

    public class GridKeyListener extends KeyAdapter
    {
        public void keyPressed(KeyEvent e)
        {
            if(e.getKeyCode() == KeyEvent.VK_DELETE && isFocusOwner() && !(selectedCell == null)) {
                model.removeObject(selectedCell);
                setSelectedCell(null);
            }
        }

    }
}