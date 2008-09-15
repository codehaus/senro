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


package com.jeta.swingbuilder.codegen.builder.properties;

import java.awt.Component;
import java.beans.PropertyDescriptor;

import java.lang.reflect.Method;

import java.util.Collection;
import java.util.Iterator;

import javax.swing.JComboBox;

import com.jeta.forms.gui.beans.JETAPropertyDescriptor;
import com.jeta.forms.store.properties.ItemsProperty;

import com.jeta.swingbuilder.codegen.builder.*;

public class ItemsPropertyWriter implements PropertyWriter
{
   /**
    * PropertyWriter implementation
    */
   public void writeProperty( DeclarationManager declMgr, BeanWriter writer, JETAPropertyDescriptor pd, Object value )
   {
      try
      {
	 if ( value instanceof ItemsProperty )
	 {
	    ItemsProperty iprop = (ItemsProperty)value;
	    if ( JComboBox.class.isAssignableFrom( writer.getBeanType() ) )
	    {
	       Collection items = iprop.getItems();
	       if ( items != null )
	       {
		  Iterator iter = items.iterator();
		  while( iter.hasNext() )
		  {
		     MethodStatement ms = new MethodStatement( writer.getBeanVariable(), "addItem" );
		     ms.addParameter( new StringExpression( iter.next().toString() ) );
		     writer.addStatement( ms );
		  }
	       }
	    }
	 }
      }
      catch( Exception e )
      {
	 e.printStackTrace();
      }
   }
}
