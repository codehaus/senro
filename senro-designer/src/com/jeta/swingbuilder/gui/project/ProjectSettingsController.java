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

package com.jeta.swingbuilder.gui.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import javax.swing.JFileChooser;

import com.jeta.swingbuilder.gui.filechooser.FileChooserConfig;
import com.jeta.swingbuilder.gui.filechooser.TSFileChooserFactory;
import com.jeta.swingbuilder.gui.filechooser.TSFileFilter;
import com.jeta.open.gui.framework.JETAController;


/**
 * The controller for the ProjectSettingsView class
 *
 * @author Jeff Tassin
 */
public class ProjectSettingsController extends JETAController
{
   /**
    * The view we are handling events for
    */
   private ProjectSettingsView          m_view;

   /**
    * ctor
    */
   public ProjectSettingsController( ProjectSettingsView view )
   {
      super( view );
      m_view = view;
      assignAction( ProjectSettingsNames.ID_ADD_PATH, new AddPathAction() );
      assignAction( ProjectSettingsNames.ID_DELETE_PATH, new DeletePathAction() );
      assignAction( ProjectSettingsNames.ID_PROJECT_FILE_BTN, new ProjectFileAction() );
      assignAction( ProjectSettingsNames.ID_PROJECT_CLASSPATH_BTN, new ProjectClassPathAction() );
   }

   /**
    * Adds a path to the project
    */
   public class AddPathAction implements ActionListener
   {
      public void actionPerformed( ActionEvent evt )
      {
	 FileChooserConfig fcc = new FileChooserConfig( (String)null, JFileChooser.DIRECTORIES_ONLY, (TSFileFilter)null );
	 fcc.setParentComponent( m_view );
	 File f = TSFileChooserFactory.showOpenDialog( fcc );
	 if ( f != null )
	 {
	    m_view.addPath( f.getPath() );
	 }
      }
   }


   /**
    * Deletes a path from the project
    */
   public class DeletePathAction implements ActionListener
   {
      public void actionPerformed( ActionEvent evt )
      {
	 m_view.deleteSelectedPath();
      }
   }

   /**
    * Adds a class path to the project
    */
   public class ProjectClassPathAction implements ActionListener
   {
      public void actionPerformed( ActionEvent evt )
      {
	 File f = TSFileChooserFactory.showOpenDialog( JFileChooser.DIRECTORIES_ONLY );
	 if ( f != null )
	 {
	    File projfile = new File(m_view.getProjectFile());
	    String project_parent = projfile.getParent();
	    String classpath = PathParser.getRelativePath( new File(project_parent), f );
	    m_view.setText( ProjectSettingsNames.ID_PROJECT_CLASSPATH, classpath );
	 }
      }
   }


   public class ProjectFileAction implements ActionListener
   {
      public void actionPerformed( ActionEvent evt )
      {
	 File f = TSFileChooserFactory.showSaveDialog( "project", new TSFileFilter( "jfpr", "Project Files (*.jfpr)" ) );
	 if ( f != null )
	 {
	    String fname = f.getName();
	    if ( fname != null && fname.length() > 0 )
	    {
	       int pos = fname.indexOf( "." );
	       if ( pos < 0 )
	       {
		  fname = fname + ".jfpr";
	       }
	    }
	    String path = f.getParent() + File.separatorChar + fname;
	    m_view.setText( ProjectSettingsNames.ID_PROJECT_FILE_PATH, path );
	 }
      }
   }
}
