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


package com.jeta.swingbuilder.store;

import java.io.Externalizable;
import java.io.IOException;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import com.jeta.forms.gui.common.FormUtils;
import com.jeta.open.support.EmptyCollection;


/**
 * Defines the settings for a project.
 * @author Jeff Tassin
 */
public class ProjectModel implements Externalizable
{
   static final long serialVersionUID = 3273178622430479212L;

   public static final int VERSION = 2;

   /**
    * The list of source paths for the application
    */
   private LinkedList       m_source_paths;

   /**
    * This is the path to the project file.  This is
    * transient because the user can rename/move the file.
    */
   private transient String        m_project_path;

   /**
    * The path where classes are stored (can be null).
    */
   private String           m_class_path;

   /**
    * ctor
    */
   public ProjectModel()
   {
   }

   /**
    * Adds a source path to this model
    */
   public void addSourcePath( String path )
   {
      if ( m_source_paths == null )
	 m_source_paths = new LinkedList();

      m_source_paths.add( path );
   }

   public String getClassPath()
   {
      return m_class_path;
   }

   /**
    * @return a collection of String objects that are the paths
    * where source and image files can be found.
    */
   public Collection getSourcePaths()
   {
      if ( m_source_paths == null )
	 return EmptyCollection.getInstance();
      else
	 return m_source_paths;
   }

   /**
    * @return the path to the project file
    */
   public String getProjectPath()
   {
      return m_project_path;
   }

   /**
    * Externalizable Implementation
    */
   public void readExternal( java.io.ObjectInput in) throws ClassNotFoundException, IOException
   {
      int version = in.readInt();
      m_source_paths = (LinkedList)in.readObject();
      if ( version == 2 )
      {
	 m_class_path = (String)in.readObject();
      }

      LinkedList fixed_src_paths = new LinkedList();
      if ( m_source_paths != null )
      {
	 Iterator iter = m_source_paths.iterator();
	 while( iter.hasNext() )
	 {
	    String path = (String)iter.next();
	    path = FormUtils.fixPath(path);
	    fixed_src_paths.add( path );
	 }
      }
      m_source_paths = fixed_src_paths;
      m_class_path = FormUtils.fixPath( m_class_path );

   }

   /**
    * Externalizable Implementation
    */
   public void writeExternal( java.io.ObjectOutput out) throws IOException
   {
      out.writeInt( VERSION );
      out.writeObject( m_source_paths );
      out.writeObject( m_class_path );
   }

   /**
    * Set the path to the project file
    */
   public void setProjectPath( String path )
   {
      m_project_path = path;
   }


   public void setClassPath( String classPath )
   {
      m_class_path = classPath;
   }
}
