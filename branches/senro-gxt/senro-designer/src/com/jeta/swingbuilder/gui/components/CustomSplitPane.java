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


package com.jeta.swingbuilder.gui.components;

import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JSplitPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import javax.swing.plaf.basic.BasicSplitPaneDivider;


/**
 *
 * @author Jeff Tassin
 */
public class CustomSplitPane extends JSplitPane
{
   private boolean m_firsttime = true;
   private double  m_divlocation = 0.5f;
   /** an integer value for the divider location.  If not -1, then takes pecendence of the proportional value */
   private int     m_idivlocation = -1; 
   
   /**
    * Creates an instance of a CustomSplitPane
    */
   public CustomSplitPane(int newOrientation )
   {       
      super(newOrientation);
   }

   public void setDividerLocation( int divlocation )
   {
      if ( m_firsttime )
      {
	 m_idivlocation = divlocation;
      }
      super.setDividerLocation( divlocation );
   } 

   public void setDividerLocation( double propLocation )
   {
      if ( m_firsttime )
      {
	 m_divlocation = propLocation;
	 m_idivlocation = -1;
      }
      super.setDividerLocation( propLocation );
   } 

  
   public void paint(Graphics g)
   {   
      if ( m_firsttime )
      { 
	 m_firsttime = false;

	 if ( m_idivlocation < 0 )
	    setDividerLocation(m_divlocation);
	 else
	    setDividerLocation( m_idivlocation );

      }
      super.paint(g);
   } 


}
