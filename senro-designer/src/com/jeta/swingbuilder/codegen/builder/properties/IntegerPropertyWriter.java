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

import java.awt.Color;
import java.awt.Component;
import java.beans.PropertyDescriptor;

import java.lang.reflect.Method;

import com.jeta.forms.gui.beans.JETAPropertyDescriptor;

import com.jeta.forms.store.properties.ColorHolder;


import com.jeta.swingbuilder.codegen.builder.*;

public class IntegerPropertyWriter implements PropertyWriter
{

   /**
    * PropertyWriter implementation
    */
   public void writeProperty( DeclarationManager declMgr, BeanWriter writer, JETAPropertyDescriptor pd, Object value )
   {
      try
      {
	 if ( value instanceof Integer || value instanceof Long || value instanceof Short || value instanceof Byte )
	 {
	    Method write = pd.getWriteMethod();
	    if ( write != null )
	    {
	       MethodStatement ms = new MethodStatement( writer.getBeanVariable(), write.getName() );
	       if ( value instanceof Long )
	       {
		  ms.addParameter( value.toString() + "L" );
	       }
	       else if ( value instanceof Byte )
	       {
		  ms.addParameter( "(byte)" + value.toString() );
	       }
	       else if ( value instanceof Short )
	       {
		  ms.addParameter( "(short)" + value.toString() );
	       }
	       else
	       {
		  ms.addParameter( value.toString() );
	       }
	       writer.addStatement( ms );
	    }
	 }
      }
      catch( Exception e )
      {
	 e.printStackTrace();
      }
   }

}
