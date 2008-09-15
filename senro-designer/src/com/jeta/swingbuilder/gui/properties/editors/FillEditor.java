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

package com.jeta.swingbuilder.gui.properties.editors;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import java.beans.PropertyEditorSupport;

import com.jeta.forms.store.properties.effects.PaintProperty;

import com.jeta.swingbuilder.gui.effects.PaintAssignmentView;
import com.jeta.swingbuilder.gui.properties.JETAPropertyEditor;
import com.jeta.swingbuilder.gui.utils.FormDesignerUtils;
import com.jeta.swingbuilder.resources.Icons;


import com.jeta.open.gui.framework.JETADialog;
import com.jeta.open.gui.utils.JETAToolbox;
import com.jeta.open.i18n.I18N;


public class FillEditor extends JETAPropertyEditor
{
   /**
    * Used to render the value of our border
    */
   private ValuePainter        m_value_painter;

   private static ImageIcon[]         m_icon = { (ImageIcon)FormDesignerUtils.loadImage( Icons.PAINT_BRUSH_16 ) };


   /**
    * ctor
    */
   public FillEditor()
   {
      m_value_painter = new ValuePainter( I18N.getLocalizedMessage( "Solid" ) );
      m_value_painter.setPreImages( m_icon );
   }



   /**
    * Invokes a dialog used to update the property
    */ 
   public void invokePropertyDialog( Component comp )
   {
      PaintAssignmentView view = new PaintAssignmentView( (PaintProperty)getValue() );
      JETADialog dlg = (JETADialog)JETAToolbox.createDialog( JETADialog.class, (java.awt.Component)comp, true );
      dlg.setPrimaryPanel( view );
      dlg.setSize( dlg.getPreferredSize() );
      dlg.setTitle( I18N.getLocalizedMessage( "Edit Fill" ) );
      dlg.showCenter();
      if ( dlg.isOk() )
      {
	 setValue( view.getPaintProperty() );
      }
   }

   /**
    * Determines whether this class renders itself using the paintValue(Graphics g, Rectangle rect) method.
    * Generally, editors that are not JComponents are paintable.
    */
   public boolean isPaintable() 
   {
      return true;
   }

   /**
    * Method that renders the text on the given graphics context
    */
   public void paintValue(Graphics g, Rectangle rect) 
   {
      // forward the call to the value painter
      m_value_painter.paintValue( g, rect );
   }

   /**
    * 
    */
   public void setValue(Object value)
   {
      super.setValue( value );
      if ( value instanceof PaintProperty )
      {
	 m_value_painter.setValue( ((PaintProperty)value).toString() );
      }
      else
      {
	 assert( false );
      }
   }

   /**
    * @return true since we have a custom editor dialog for this type
    */
   public boolean supportsCustomEditor()
   {
      return true;
   }
}
