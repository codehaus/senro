/*
 * Copyright (C) 2005 Jeff Tassin
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package com.jeta.swingbuilder.gui.handler;

import java.awt.Cursor;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.InputEvent;

import com.jeta.forms.gui.form.GridOverlay;

import com.jeta.forms.gui.form.GridComponent;
import com.jeta.forms.gui.form.GridCellEvent;
import com.jeta.forms.gui.form.GridView;
import com.jeta.forms.gui.form.FormComponent;
import com.jeta.forms.gui.handler.CellMouseHandler;

import com.jeta.swingbuilder.gui.dnd.DesignerDragSource;
import com.jeta.swingbuilder.gui.editor.DesignGridOverlay;
import com.jeta.swingbuilder.gui.editor.DesignFormComponent;
import com.jeta.swingbuilder.gui.editor.FormEditor;
import com.jeta.swingbuilder.gui.formmgr.FormManagerDesignUtils;

import com.jeta.open.registry.JETARegistry;
import ro.siveco.senro.designer.basic.SenroDesignerObject;
import ro.siveco.senro.designer.util.UIUtil;
import ro.siveco.senro.designer.engine.DesignerManager;
import ro.siveco.senro.designer.engine.AssociationManager;
import ro.siveco.senro.designer.components.TableComponent;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableColumn;

public class GridMouseListener implements MouseListener, MouseMotionListener, DesignerDragSource
{
    /*# GridOverlay lnkGridOverlay; */

    /**
     * @directed
     */
    private CellMouseHandler m_delegate;

    /**
     * The top level grid overlay
     */
    private DesignGridOverlay m_topoverlay;

    private boolean m_drag_mouse = false;
    private boolean m_dragging = false;

    public GridMouseListener(GridOverlay topOverlay, CellMouseHandler delegate)
    {
        assert (delegate != null);

        m_delegate = delegate;
        m_topoverlay = (DesignGridOverlay) topOverlay;
    }

    /**
     * DesignerDragSource implementation
     */
    public void cancelDrag()
    {
        if (m_dragging) {
            m_topoverlay.setCursor(Cursor.getDefaultCursor());

            GridComponent dragsrc = null;
            CellMouseHandler draghandler = AbstractMouseHandler.getDragSource();
            if (draghandler != null) {
                dragsrc = draghandler.getGridComponent();
            }

            AbstractMouseHandler.setDragSource(null);
            FormManagerDesignUtils.deselectAll(m_topoverlay.getForm());
            if (dragsrc != null) {
                dragsrc.setSelected(true);
            }
            m_dragging = false;
        }
    }


    public boolean isDragging()
    {
        return m_drag_mouse;
    }

    /**
     * MouseListener implementation
     */
    public void mouseClicked(MouseEvent e)
    {
        if (m_delegate != null) {
            m_delegate.mouseClicked(e);
        }

        m_topoverlay.requestFocus();
        // obtain clicked SenroDesignerObject
        SenroDesignerObject target_obj = null;
        GridComponent gc = m_topoverlay.getCell(e.getPoint());
        if (gc != null) {
            target_obj = (SenroDesignerObject)getDesignerObjectAt((DesignFormComponent) gc, e);
        }
        // special treatment for TableComponent
        if (target_obj instanceof TableComponent) {
            TableComponent table = (TableComponent) target_obj;
            TableColumnModel columnmodel = table.getColumnModel();
            int viewcolumn = columnmodel.getColumnIndexAtX(e.getLocationOnScreen().x - table.getLocationOnScreen().x);
            if (viewcolumn < 0) {
                return;
            }
            TableColumn tablecol = columnmodel.getColumn(viewcolumn);
            int modelcolumn = tablecol.getModelIndex();
            int ctrlpressed = e.getModifiers() & InputEvent.CTRL_MASK;
            if (e.getClickCount() == 1 ) {
                if(ctrlpressed == 0) {
                    table.setSelectedSenroColumnIdx(-1);
                    table.clearSelection();                                        
                } else if(modelcolumn != -1) {
                    table.setSelectedSenroColumnIdx(modelcolumn);
                    table.setColumnSelectionInterval(modelcolumn, modelcolumn);
                }
            }
        }
        // create association if we are in association mode
        AssociationManager assoc_mng = DesignerManager.getSharedDesignerManager().getAssociationManager();
        if (assoc_mng.isAssociationMode()) {
            assoc_mng.createAssociation(target_obj);
            m_topoverlay.setCursor(null);
        }
    }

    public void mouseEntered(MouseEvent e)
    {
        if (m_delegate != null) {
            m_delegate.mouseEntered(e);
        }
    }

    public void mouseExited(MouseEvent e)
    {
        if (m_delegate != null) {
            m_delegate.mouseExited(e);
        }
    }

    public void mousePressed(MouseEvent e)
    {
        JETARegistry.rebind(DesignerDragSource.COMPONENT_ID, this);
        if (m_delegate != null) {
            m_delegate.mousePressed(e);
        }

        m_topoverlay.requestFocus();
        m_dragging = true;
        m_drag_mouse = false;
    }

    public void mouseReleased(MouseEvent e)
    {
        if (m_dragging) {
            m_dragging = false;
            m_drag_mouse = false;

            m_topoverlay.setCursor(Cursor.getDefaultCursor());
            if (m_delegate != null) {
                m_delegate.mouseReleased(e);
            }

            GridComponent dragsrc = null;
            CellMouseHandler draghandler = AbstractMouseHandler.getDragSource();
            if (draghandler != null) {
                dragsrc = draghandler.getGridComponent();
            }

            AbstractMouseHandler.setDragSource(null);

            if (dragsrc != null && dragsrc.isSelected()) {
                dragsrc.fireGridCellEvent(new GridCellEvent(GridCellEvent.CELL_SELECTED, dragsrc));
            }
        }
    }

    /**
     * MouseMotionListener implemenation
     */
    public void mouseDragged(MouseEvent e)
    {
        if (m_dragging) {
            m_drag_mouse = true;
            m_topoverlay.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
            if (m_delegate != null) {
                m_delegate.mouseDragged(e);
            }
        }
    }

    public Component getDesignerObjectAt(DesignFormComponent dfc, MouseEvent e)
    {
        Component cmp = null;
        GridComponent cell = getComponent(dfc, e);
        if (cell == dfc) {
            cmp = dfc.getBeanDelegate();
        } else if (cell != null) {
            if (cell instanceof DesignFormComponent) {
                cmp = getDesignerObjectAt((DesignFormComponent) cell, e);
                if (cmp == null) {
                    cmp = cell.getBeanDelegate();
                }
            } else {
                if (cell.getBeanDelegate() instanceof JTabbedPane) {
                    JTabbedPane tab_pane = (JTabbedPane) cell.getBeanDelegate();
                    DesignFormComponent fc = (DesignFormComponent) getForm(tab_pane, e);
                    if (fc == null) {
                        cmp = tab_pane;
                    } else {
                        cmp = getDesignerObjectAt(fc, e);

                    }
                } else {
                    GridView gv = cell.getParentView();
                    GridComponent gc = gv.getOverlappingComponent(cell.getColumn(), cell.getRow());
                    if (gc == null) {
                        cmp = cell.getBeanDelegate();
                    } else {
                        cmp = gc.getBeanDelegate();
                    }
                }
            }
        }
        return cmp;
    }

    private FormComponent getForm(JTabbedPane tab_pane, MouseEvent e)
    {
        Component comp = tab_pane.getSelectedComponent();
        if (comp instanceof FormComponent) {
            FormComponent fc = (FormComponent) comp;
            Point local_pt = SwingUtilities.convertPoint((Component) e.getSource(), e.getPoint(), fc.getParent());
            if (local_pt.x >= fc.getX() && local_pt.x < (fc.getX() + fc.getWidth()) &&
                    local_pt.y >= fc.getY() && local_pt.y < (fc.getY() + fc.getHeight())) {
                return fc;
            }
        }
        return null;
    }

    private GridComponent getComponent(DesignFormComponent dfc, MouseEvent e)
    {
        DesignGridOverlay overlay = (DesignGridOverlay) dfc.getChildOverlay();
        Point local_pt = SwingUtilities.convertPoint((Component) e.getSource(), e.getPoint(), overlay);
        GridComponent cell = overlay.getCell(local_pt);
        if (cell == null) {
            DesignFormComponent parent = (DesignFormComponent) getParentForm(dfc);
            if (parent != null) {
                DesignGridOverlay parentoverlay = (DesignGridOverlay) parent.getChildOverlay();
                local_pt =
                        SwingUtilities.convertPoint((Component) e.getSource(), e.getPoint(), parentoverlay);

                int y = local_pt.y;
                int x = local_pt.x;

                if (x >= dfc.getCellX() && x <= dfc.getCellX() + dfc.getCellWidth() &&
                        y >= dfc.getCellY() && y <= dfc.getCellY() + dfc.getCellHeight()) {
                    cell = dfc;
                }
            }
        }
        return cell;
    }

    private FormComponent getParentForm(FormComponent comp)
    {
        Component parent = comp.getParent();
        while (parent != null) {
            if (parent instanceof FormComponent) {
                return (FormComponent) parent;
            } else if (parent instanceof FormEditor) {
                return null;
            } else if (parent instanceof javax.swing.JFrame) {
                return null;
            }
            parent = parent.getParent();
        }
        return null;
    }

    public void mouseMoved(MouseEvent e)
    {
        AssociationManager am = DesignerManager.getSharedDesignerManager().getAssociationManager();
        if (am.isAssociationMode()) {
            GridComponent gc = m_topoverlay.getCell(e.getPoint());
            if (gc == null) {
                m_topoverlay.setCursor(UIUtil.getNoCursor());
            } else {
                Component cmp = getDesignerObjectAt((DesignFormComponent) gc, e);
                if (cmp != null) {
                    if (am.accept((SenroDesignerObject) cmp)) {
                        m_topoverlay.setCursor(UIUtil.getLinkCursor());
                    } else {
                        m_topoverlay.setCursor(UIUtil.getNoCursor());
                    }
                } else {
                    m_topoverlay.setCursor(UIUtil.getNoCursor());
                }
            }
        } else {
            m_topoverlay.setCursor(null);
        }
        if (m_delegate != null) {
            m_delegate.mouseMoved(e);
        }
    }

}
