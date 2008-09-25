package ro.siveco.senro.designer.ui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

public class MatrixView extends JPanel
{

    private static final long serialVersionUID = 1L;
    private static final int NO_VALUE = -1;

    private MatrixModel model = null;
    private CellFactory cellFactory = null;

    private MatrixCell[][] cells = null;

    private int cellWidth;
    private int cellHeight;
    private int cellHorizBorder = 0;
    private int cellVertBorder = 0;

    private final Set<CellCoordinates> selectedCells = new HashSet<CellCoordinates>();
    private Set<MatrixSelectionListener> selectionListeners = new HashSet<MatrixSelectionListener>();
    private Color selectionColor;
    private int selectionBorderWidth;

    private CellCoordinates draggedCell = null;
    private int deltaXFromMouseToCellOrigin = NO_VALUE;
    private int deltaYFromMouseToCellOrigin = NO_VALUE;
    private int mouseX = NO_VALUE;
    private int mouseY = NO_VALUE;
    private Color underDraggedCellColor;
    MatrixMouseListener matrixMouseListener = new MatrixMouseListener();
    MatrixKeyListener matrixKeyListener = new MatrixKeyListener();

    public MatrixView()
    {
        setAutoscrolls(true);
        setBackground(Color.WHITE);
        setCellWidth(100);
        setCellHeight(100);
        setCellHorizBorder(4);
        setCellVertBorder(6);
        setSelectionColor(Color.RED);
        setSelectionBorderWidth(5);
        setUnderDraggedCellColor(Color.CYAN);
        setEnabled(true);
    }

    public void setEnabled(boolean enabled)
    {
        super.setEnabled(enabled);
        if (enabled) {
            addMouseListener(matrixMouseListener);
            addMouseMotionListener(matrixMouseListener);
            setFocusable(true);
            addKeyListener(matrixKeyListener);

        } else {
            removeMouseListener(matrixMouseListener);
            removeMouseMotionListener(matrixMouseListener);
            setFocusable(false);
            removeKeyListener(matrixKeyListener);
        }
        setSelectedCells(Collections.<CellCoordinates>emptySet());
    }

    public void addMatrixSelectionListener(MatrixSelectionListener matrix_selection_listener)
    {
        selectionListeners.add(matrix_selection_listener);
    }

     public void removeMatrixSelectionListener(MatrixSelectionListener matrix_selection_listener)
    {
        selectionListeners.remove(matrix_selection_listener);
    }

    public void paint(Graphics g)
    {
        BufferedImage buff = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D buff_g = buff.createGraphics();
        super.paint(buff_g);
        for (int col = 0; col < model.colCount(); col++) {
            for (int row = 0; row < model.rowCount(); row++) {
                if (draggedCell != null && col == draggedCell.col && row == draggedCell.row) {
                    continue;
                }
                // matrix cell paint
                paintMatrixCellAtPoint(buff_g, col, row, col * cellWidth, row * cellHeight);
            }
        }
        // draw selection border of the selected cells
        drawSelectedCellsBorder(buff_g);

        // draw under dragged cell border and draggedCell and its selection border
        paintDraggedCell(buff_g);
        g.drawImage(buff, 0, 0, getWidth(), getHeight(), this);
    }

    public void paintMatrixCellAtPoint(Graphics g, int col, int row, int cell_origin_x, int cell_origin_y)
    {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.translate(cell_origin_x + cellHorizBorder, cell_origin_y + cellVertBorder);
        g2d.setClip(0, 0, cellWidth - 2 * cellHorizBorder, cellHeight - 2 * cellVertBorder);
        MatrixCell cell = cells[col][row];
        if(cell != null) {
            cell.paint(g2d, getCellAvailableDimension());
        }
        g2d.dispose();
    }

    public void drawSelectedCellsBorder(Graphics g)
    {
        g.setColor(selectionColor);
        for (CellCoordinates selectedCell : selectedCells) {
            if (selectedCell.equals(draggedCell)) {
                continue;
            }
            for (int i = 0; i < selectionBorderWidth; i++) {
                g.drawRect(selectedCell.col * cellWidth + i, selectedCell.row * cellHeight + i, cellWidth - 2 * i, cellHeight - 2 * i);
            }
        }
    }

    public void paintDraggedCell(Graphics g)
    {
        if (draggedCell != null) {
            g.setColor(underDraggedCellColor);
            int underDraggedCellCol = mouseX / cellWidth;
            int underDraggedCellRow = mouseY / cellHeight;
            if(underDraggedCellCol < getModel().colCount() && underDraggedCellRow < getModel().rowCount()) {
               for (int i = 0; i < selectionBorderWidth; i++) {
                g.drawRect(underDraggedCellCol * cellWidth + i, underDraggedCellRow * cellHeight + i, cellWidth - 2 * i,
                        cellHeight - 2 * i);
                }
            }
            paintMatrixCellAtPoint(g, draggedCell.col, draggedCell.row, mouseX - deltaXFromMouseToCellOrigin,
                    mouseY - deltaYFromMouseToCellOrigin);
            g.setColor(selectionColor);
            for (int i = 0; i < selectionBorderWidth; i++) {
                g.drawRect(mouseX - deltaXFromMouseToCellOrigin + i, mouseY - deltaYFromMouseToCellOrigin + i,
                        cellWidth - 2 * i, cellHeight - 2 * i);
            }
        }
    }

    public Dimension getCellAvailableDimension()
    {
        return new Dimension(cellWidth - 2 * cellHorizBorder, cellHeight - 2 * cellVertBorder);
    }

    public MatrixModel getModel()
    {
        return model;
    }

    public void setModel(MatrixModel model)
    {
        this.model = model;
        model.setMatrixView(this);
        if (!isReady()) {
            return;
        }
        refreshCellStructure();
    }

    public CellFactory getCellFactory()
    {
        return cellFactory;
    }

    public void setCellFactory(CellFactory cellFactory)
    {
        this.cellFactory = cellFactory;
        cellFactory.setMatrixView(this);
        if (!isReady()) {
            return;
        }
        refreshCellStructure();
    }

    public void modelDataDidChanged(List<CellCoordinates> changed_data)
    {
        if (changed_data.size() == 0) {
            for (int col = 0; col < model.colCount(); col++) {
                for (int row = 0; row < model.rowCount(); row++) {
                    cells[col][row] = cellFactory.getCellForData(model.getDataAt(col, row));
                }
            }
        } else {
            for (CellCoordinates cellCoordinates : changed_data) {
                int col = cellCoordinates.col;
                int row = cellCoordinates.row;
                cells[col][row] = cellFactory.getCellForData(model.getDataAt(col, row));
            }
        }

        repaintVisibleRect();
    }

    public boolean isReady()
    {
        return cellFactory != null && model != null;
    }

    public void refreshCellStructure()
    {
        cells = new MatrixCell[model.colCount()][model.rowCount()];
        updateBounds();
        modelDataDidChanged(Collections.<CellCoordinates>emptyList());
    }

    protected void updateBounds()
    {
        setBounds(0, 0, model.colCount() * cellWidth, model.rowCount() * cellHeight);
        setPreferredSize(new Dimension(model.colCount() * cellWidth, model.rowCount() * cellHeight));
        revalidate();
    }

    protected void repaintVisibleRect()
    {
        Container parent = getParent();
        if(parent != null) {
            if(parent.getParent() instanceof JScrollPane){
                JScrollPane scroll_pane = (JScrollPane) parent.getParent();
                Rectangle visible_rect = scroll_pane.getViewport().getViewRect();
                repaint(0,visible_rect.getLocation().x,
                          visible_rect.getLocation().y,
                          visible_rect.getSize().width,
                          visible_rect.getSize().height);
            } else {
                repaint();
            }
        }
    }

    public int getCellWidth()
    {
        return cellWidth;
    }

    public void setCellWidth(int cellWidth)
    {
        this.cellWidth = cellWidth;
        if (!isReady()) {
            return;
        }
        updateBounds();
        repaintVisibleRect();
    }

    public int getCellHeight()
    {
        return cellHeight;
    }

    public void setCellHeight(int cellHeight)
    {
        this.cellHeight = cellHeight;
        if (!isReady()) {
            return;
        }
        updateBounds();
        repaintVisibleRect();
    }

    public int getCellHorizBorder()
    {
        return cellHorizBorder;
    }

    public void setCellHorizBorder(int cellHorizBorder)
    {
        this.cellHorizBorder = cellHorizBorder;
        revalidate();
        repaintVisibleRect();
    }

    public int getCellVertBorder()
    {
        return cellVertBorder;
    }

    public void setCellVertBorder(int cellVertBorder)
    {
        this.cellVertBorder = cellVertBorder;
        revalidate();
        repaintVisibleRect();
    }

    public Set<CellCoordinates> getSelectedCells()
    {
        return selectedCells;
    }

    public void setSelectedCells(Set<CellCoordinates> selected_cells)
    {
        if(selectedCells.equals(selected_cells))
            return;
        selectedCells.clear();
        selectedCells.addAll(selected_cells);
        notifySelectionListeners();
    }

    private void notifySelectionListeners()
    {
        for(MatrixSelectionListener sel_listener : selectionListeners) {
            sel_listener.selectionDidChange(selectedCells);
        }
    }

    public boolean isCellSelected(int col, int row)
    {
        return isCellSelected(new CellCoordinates(col, row));
    }

    public boolean isCellSelected(CellCoordinates cell_coordinates)
    {
        return selectedCells.contains(cell_coordinates);
    }

    public Color getSelectionColor()
    {
        return selectionColor;
    }

    public void setSelectionColor(Color selectionColor)
    {
        this.selectionColor = selectionColor;
        revalidate();
        repaintVisibleRect();
    }

    public int getSelectionBorderWidth()
    {
        return selectionBorderWidth;
    }

    public void setSelectionBorderWidth(int selectionBorderWidth)
    {
        this.selectionBorderWidth = selectionBorderWidth;
        revalidate();
        repaintVisibleRect();
    }

    public void setSelectedCell(CellCoordinates selected_cell)
    {
        if(selectedCells.size() == 1 && selectedCells.iterator().next().equals(selected_cell)) {
            return;
        }
        selectedCells.clear();
        selectedCells.add(selected_cell);
        notifySelectionListeners();
    }

    private void switchSelectionInCell(CellCoordinates clickedCell)
    {
        if (selectedCells.contains(clickedCell)) {
            selectedCells.remove(clickedCell);
        } else {
            selectedCells.add(clickedCell);
        }
        notifySelectionListeners();
    }

    public Color getUnderDraggedCellColor()
    {
        return underDraggedCellColor;
    }

    public void setUnderDraggedCellColor(Color underDraggedCellColor)
    {
        this.underDraggedCellColor = underDraggedCellColor;
    }

    public void invalidateDraggParameters()
    {
        draggedCell = null;
        deltaXFromMouseToCellOrigin = NO_VALUE;
        deltaYFromMouseToCellOrigin = NO_VALUE;
        mouseX = NO_VALUE;
        mouseY = NO_VALUE;
    }

    public class MatrixMouseListener extends MouseAdapter
    {

        public void mousePressed(MouseEvent e)
        {
            requestFocusInWindow();
            int x = e.getX();
            int y = e.getY();
            int selectedCol = x / getCellWidth();
            int selectedRow = y / getCellHeight();
            CellCoordinates clickedCell = new CellCoordinates(selectedCol, selectedRow);
            if ((e.getModifiersEx() & InputEvent.CTRL_DOWN_MASK) == InputEvent.CTRL_DOWN_MASK) {
                switchSelectionInCell(clickedCell);
            } else {
                setSelectedCell(clickedCell);
            }
            repaintVisibleRect();
            model.mousePressedAtCoordinates(clickedCell);
        }

        public void mouseReleased(MouseEvent e)
        {
            if (draggedCell == null) {
                return;
            }
            mouseX = e.getX();
            mouseY = e.getY();
            CellCoordinates end_dragg_cell = new CellCoordinates(mouseX / cellWidth, mouseY / cellHeight);
            if(model.swapCells(draggedCell, end_dragg_cell)) {
                setSelectedCell(end_dragg_cell);
            }
            invalidateDraggParameters();
            repaintVisibleRect();
        }

        public void mouseDragged(MouseEvent e)
        {
            if (selectedCells.size() > 1 || selectedCells.isEmpty()) {
                return;
            }
            CellCoordinates sel_cell = selectedCells.iterator().next();
            if(model.getDataAt(sel_cell.col, sel_cell.row) == null) {
                return;
            }
            mouseX = e.getX();
            mouseY = e.getY();

            if (draggedCell == null) {
                int selectedCol = mouseX / getCellWidth();
                int selectedRow = mouseY / getCellHeight();
                draggedCell = new CellCoordinates(selectedCol, selectedRow);
                deltaXFromMouseToCellOrigin = mouseX - selectedCol * cellWidth;
                deltaYFromMouseToCellOrigin = mouseY - selectedRow * cellHeight;
            }
            repaintVisibleRect();
            Rectangle r = new Rectangle(mouseX, mouseY, 1, 1);
            scrollRectToVisible(r);
        }
    }

    public class MatrixKeyListener extends KeyAdapter
    {
        public void keyPressed(KeyEvent e)
        {
            if (e.getKeyCode() == KeyEvent.VK_DELETE && isFocusOwner() && !selectedCells.isEmpty()) {
                model.removeObjects(selectedCells);
                setSelectedCells(Collections.<CellCoordinates>emptySet());
            }
        }

    }
}
