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

import java.awt.BorderLayout;
import java.awt.Dimension;

import java.io.File;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.JList;


import com.jeta.forms.components.panel.FormPanel;

import com.jeta.open.gui.framework.JETAPanel;

import com.jeta.swingbuilder.gui.utils.FormDesignerUtils;
import com.jeta.swingbuilder.store.ProjectModel;



/**
 * Displays the view for editing the current project settings
 *
 * @author Jeff Tassin
 */
public class ProjectSettingsView extends JETAPanel
{
   /**
    * The projectSettings.jfrm form
    */
   private FormPanel              m_view;

   /**
    * The list model for the source path
    */
   private DefaultListModel       m_list_model = new DefaultListModel();

   /**
    * Set when we are editing an existing project
    */
   private ProjectModel           m_existing_model;


   /**
    * ctor
    */
   public ProjectSettingsView()
   {
      initialize( null );
   }

   /**
    * ctor
    */
   public ProjectSettingsView( ProjectModel pmodel )
   {
      m_existing_model = pmodel;
      initialize( pmodel );
   }


   public void addPath( String path )
   {
      String projectPath = "";
      if ( m_existing_model == null )
      {
	 projectPath = getProjectFile();
      }
      else
      {
	 projectPath = m_existing_model.getProjectPath();
      }

      path = PathParser.getRelativePath( new File(projectPath), new File(path) );
      if ( !m_list_model.contains( path ) )
      {
	 m_list_model.addElement( path );
      }
   }

   /**
    * Deletes the selected path from the view
    */
   public void deleteSelectedPath()
   {
      JList list = m_view.getList( ProjectSettingsNames.ID_DIRECTORY_LIST );
      int index = list.getSelectedIndex();
      if ( index >= 0 )
      {
	 m_list_model.removeElementAt( index );
      }
   }

   /**
    * @return a project model defined by the information in this view.
    */
   public ProjectModel getModel()
   {
      ProjectModel pmodel = new ProjectModel();
      Collection paths = getPaths();
      Iterator iter = paths.iterator();
      while( iter.hasNext() )
      {
	 pmodel.addSourcePath( (String)iter.next() );
      }

      if ( m_existing_model == null )
      {
	 pmodel.setProjectPath( getProjectFile() );
      }
      else
      {
	 pmodel.setProjectPath( m_existing_model.getProjectPath() );
      }
      
      pmodel.setClassPath( m_view.getText( ProjectSettingsNames.ID_PROJECT_CLASSPATH ) );
      return pmodel;

   }

   public String getProjectFile()
   {
      String path = FormDesignerUtils.fastTrim( getText( ProjectSettingsNames.ID_PROJECT_FILE_PATH ) );
      char c = File.separatorChar;
      if ( c == '\\' )
	 path = path.replace( '/', File.separatorChar );
      else
	 path = path.replace( '\\', File.separatorChar );

      return path;
   }

   /**
    * @return a collection of source paths (String objects) in this view
    */
   public Collection getPaths()
   {
      LinkedList list = new LinkedList();
      for( int index=0; index < m_list_model.size(); index++ )
      {
	 list.add( m_list_model.elementAt(index) );
      }
      return list;
   }

   /**
    * Initializes the view
    */
   public void initialize( ProjectModel pmodel )
   {
      setLayout( new BorderLayout() );
      m_view = new FormPanel( "com/jeta/swingbuilder/gui/project/projectSettings.jfrm" );

      FormPanel src_panel = new FormPanel( "com/jeta/swingbuilder/gui/project/sourcePaths.jfrm" );
      
      add( m_view, BorderLayout.CENTER );
      setBorder( javax.swing.BorderFactory.createEmptyBorder( 10, 10, 10, 10 ) );

      m_view.getButton( ProjectSettingsNames.ID_PROJECT_FILE_BTN ).setPreferredSize( new Dimension(24,10) );
      m_view.getButton( ProjectSettingsNames.ID_PROJECT_CLASSPATH_BTN ).setPreferredSize( new Dimension(24,10) );

      JList list = m_view.getList( ProjectSettingsNames.ID_DIRECTORY_LIST );
      list.setModel( m_list_model );
      setController( new ProjectSettingsController(this) );

      if ( pmodel != null )
      {
	 m_view.enableComponent( ProjectSettingsNames.ID_PROJECT_FILE_PATH, false );
	 m_view.enableComponent( ProjectSettingsNames.ID_PROJECT_FILE_BTN, false );

	 m_view.setText( ProjectSettingsNames.ID_PROJECT_FILE_PATH, pmodel.getProjectPath() );
	 Collection paths = pmodel.getSourcePaths();
	 Iterator iter = paths.iterator();
	 while( iter.hasNext() )
	 {
	    m_list_model.addElement( iter.next() );
	 }
	 m_view.setText( ProjectSettingsNames.ID_PROJECT_CLASSPATH, pmodel.getClassPath() );
      }
      
   }

}

