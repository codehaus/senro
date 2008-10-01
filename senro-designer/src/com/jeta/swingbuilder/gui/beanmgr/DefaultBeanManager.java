/**
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

package com.jeta.swingbuilder.gui.beanmgr;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.*;

import com.jeta.forms.beanmgr.BeanManager;
import com.jeta.forms.components.line.HorizontalLineComponent;
import com.jeta.forms.components.line.VerticalLineComponent;
import com.jeta.forms.gui.beans.JETABeanFactory;
import com.jeta.forms.gui.common.FormException;

import com.jeta.swingbuilder.gui.utils.FormDesignerUtils;
import com.jeta.swingbuilder.resources.Icons;

import com.jeta.swingbuilder.store.ImportedBeanInfo;
import com.jeta.swingbuilder.store.ImportedBeansModel;

import com.jeta.forms.components.colors.JETAColorWell;

import com.jeta.swingbuilder.interfaces.app.ObjectStore;
import com.jeta.swingbuilder.common.ComponentNames;

import com.jeta.forms.components.border.TitledBorderLabel;
import com.jeta.forms.components.border.TitledBorderBottom;
import com.jeta.forms.components.border.TitledBorderSide;

import com.jeta.open.i18n.I18N;
import com.jeta.open.registry.JETARegistry;

/**
 * The bean manager is responsible for managing imported beans in the builder.
 *
 * @author Jeff Tassin
 */
public class DefaultBeanManager implements BeanManager {
   /**
    * The underyling data model
    */
   private ImportedBeansModel m_ibm;

   /**
    * The class loader for the beans
    */
   private BeanLoader m_loader;

   /**
    * A collection of DefaultBean objects. These are the standard Swing
    * components supported by the designer.
    */
   private LinkedList m_default_beans = new LinkedList();

   /**
    * ctor
    */
   public DefaultBeanManager() {
      try {
         ObjectStore os = (ObjectStore) JETARegistry.lookup(ComponentNames.APPLICATION_STATE_STORE);
         m_ibm = (ImportedBeansModel) os.load(ImportedBeansModel.COMPONENT_ID);
      } catch (Exception e) {
         e.printStackTrace();
      }
      if (m_ibm == null) {
         m_ibm = new ImportedBeansModel();
      }

      registerDefaultBeans();
      registerBeans(m_ibm, getBeanLoader());
   }

   /**
    * @return the underlying class loader for loading imported beans. This can
    *         be null
    */
   public ClassLoader getClassLoader() throws FormException {
      return getBeanLoader().getClassLoader();
   }

   public BeanLoader getBeanLoader() {
      if (m_loader == null) {
         m_loader = new BeanLoader(m_ibm.getUrls());
      }
      return m_loader;
   }

   /**
    * @return the class for the given bean class name
    */
   public Class getBeanClass(String beanClassName) throws FormException {
      try {
         return getBeanLoader().getClass(beanClassName);
      } catch ( Throwable t ) {
         if ( t instanceof FormException )
            throw (FormException)t;
         else {
             t.printStackTrace();
             throw new FormException( t.getMessage(), null );
         }
      }
   }

   /**
    * @return the default beans supported by the application.
    */
   public Collection getDefaultBeans() {
      return m_default_beans;
   }

   /**
    * @return the imported beans in the model
    */
   public Collection getImportedBeans() {
      return m_ibm.getImportedBeans();
   }

   /**
    * @return the underlying data model
    */
   public ImportedBeansModel getModel() {
      return m_ibm;
   }

   /**
    * Registers all imported beans with the JETABeanFactory
    */
   private void registerBeans(ImportedBeansModel ibm, BeanLoader loader) {
      try {
         if (ibm != null) {
            JETABeanFactory.clearCustomFactories();

            Collection beans = ibm.getImportedBeans();
            Iterator iter = beans.iterator();
            while (iter.hasNext()) {
               ImportedBeanInfo bi = (ImportedBeanInfo) iter.next();
               try {
                  JETABeanFactory.tryRegisterCustomFactory(loader.getClass(bi.getBeanName()), bi.isScrollable());
               } catch (Throwable e) {
                  e.printStackTrace();
               }
            }
         }
      } catch (Throwable e) {
         e.printStackTrace();
      }
   }

   /**
    * Registers a default bean used by the designer.
    */
   private void registerDefaultBean(String description, String className, Icon icon) {
      DefaultBean db = new DefaultBean(description, className, icon);
      m_default_beans.add(db);
   }

   /**
    * Loads the default Swing components supported by the application
    */
   private void registerDefaultBeans() {
      registerDefaultBean(I18N.getLocalizedMessage("Label"),
               com.jeta.forms.components.label.JETALabel.class.getName(), FormDesignerUtils.loadImage(Icons.LABEL_16));

//      registerDefaultBean(I18N.getLocalizedMessage("JRadioButton"), "javax.swing.JRadioButton", FormDesignerUtils
//               .loadImage(Icons.RADIO_16));

      registerDefaultBean(I18N.getLocalizedMessage("CheckBox"), "javax.swing.JCheckBox", FormDesignerUtils
               .loadImage(Icons.CHECK_16));

      registerDefaultBean(I18N.getLocalizedMessage("Button"), "javax.swing.JButton", FormDesignerUtils
               .loadImage(Icons.BUTTON_16));

//      registerDefaultBean(I18N.getLocalizedMessage("JToggleButton"), "javax.swing.JToggleButton", FormDesignerUtils
//               .loadImage(Icons.TOGGLE_BUTTON_16));

      registerDefaultBean(I18N.getLocalizedMessage("ComboBox"), "javax.swing.JComboBox", FormDesignerUtils
               .loadImage(Icons.COMBO_16));

//      registerDefaultBean(I18N.getLocalizedMessage("JList"), "javax.swing.JList", FormDesignerUtils
//               .loadImage(Icons.LIST_16));

//      registerDefaultBean(I18N.getLocalizedMessage("JTable"), "javax.swing.JTable", FormDesignerUtils
//               .loadImage(Icons.TABLE_16));

//      registerDefaultBean(I18N.getLocalizedMessage("JTree"), "javax.swing.JTree", FormDesignerUtils
//               .loadImage(Icons.TREE_16));

//      registerDefaultBean(I18N.getLocalizedMessage("JProgressBar"), "javax.swing.JProgressBar", FormDesignerUtils
//               .loadImage(Icons.PROGRESS_BAR_16));

//      registerDefaultBean(I18N.getLocalizedMessage("JSlider"), "javax.swing.JSlider", FormDesignerUtils
//               .loadImage(Icons.SLIDER_16));

//      registerDefaultBean(I18N.getLocalizedMessage("JSpinner"), "javax.swing.JSpinner", FormDesignerUtils
//               .loadImage(Icons.SPINNER_16));

      registerDefaultBean(I18N.getLocalizedMessage("TextField"), "javax.swing.JTextField", FormDesignerUtils
               .loadImage(Icons.TEXT_FIELD_16));

      registerDefaultBean(I18N.getLocalizedMessage("DateField"), "ro.siveco.senro.designer.components.DateFieldComponent", FormDesignerUtils
               .loadImage(Icons.TEXT_FIELD_16));

//      registerDefaultBean(I18N.getLocalizedMessage("JPasswordField"), "javax.swing.JPasswordField", FormDesignerUtils
//               .loadImage(Icons.PASSWORD_FIELD_16));

//      registerDefaultBean(I18N.getLocalizedMessage("JFormattedTextField"), "javax.swing.JFormattedTextField",
//               FormDesignerUtils.loadImage(Icons.FORMATTED_FIELD_16));

//      registerDefaultBean(I18N.getLocalizedMessage("JTextArea"), "javax.swing.JTextArea", FormDesignerUtils
//               .loadImage(Icons.TEXT_16));

//      registerDefaultBean(I18N.getLocalizedMessage("JEditorPane"), "javax.swing.JEditorPane", FormDesignerUtils
//               .loadImage(Icons.RICH_TEXT_16));

      registerDefaultBean(I18N.getLocalizedMessage("JTabbedPane"), "javax.swing.JTabbedPane", FormDesignerUtils
               .loadImage(Icons.TABPANE_16));

//      registerDefaultBean(I18N.getLocalizedMessage("Horizontal Line"), HorizontalLineComponent.class.getName(),
//               FormDesignerUtils.loadImage(Icons.HORIZONTAL_LINE_16));

//      registerDefaultBean(I18N.getLocalizedMessage("Vertical Line"), VerticalLineComponent.class.getName(),
//               FormDesignerUtils.loadImage(Icons.VERTICAL_LINE_16));

//      registerDefaultBean(I18N.getLocalizedMessage("Color Well"), JETAColorWell.class.getName(), FormDesignerUtils
//               .loadImage(Icons.COLOR_WELL_16));

//      registerDefaultBean(I18N.getLocalizedMessage("Image"), "com.jeta.forms.components.image.ImageComponent",
//               FormDesignerUtils.loadImage(Icons.PORTRAIT_16));

//      registerDefaultBean(I18N.getLocalizedMessage("Titled Border Label"), TitledBorderLabel.class.getName(),
//               FormDesignerUtils.loadImage(Icons.TITLE_BORDER_LABEL_16));

//      registerDefaultBean(I18N.getLocalizedMessage("Titled Border Bottom"), TitledBorderBottom.class.getName(),
//               FormDesignerUtils.loadImage(Icons.TITLE_BORDER_BOTTOM_16));

//      registerDefaultBean(I18N.getLocalizedMessage("Titled Border Side"), TitledBorderSide.class.getName(),
//               FormDesignerUtils.loadImage(Icons.TITLE_BORDER_SIDE_16));

//      registerDefaultBean(I18N.getLocalizedMessage("JGoodies Separator"),
//               com.jeta.forms.components.separator.TitledSeparator.class.getName(), FormDesignerUtils
//                        .loadImage(Icons.JGOODIES_SEPARATOR_16));
//      registerDefaultBean(I18N.getLocalizedMessage("SENRO Panel"),
//               JPanel.class.getName(), FormDesignerUtils
//                        .loadImage(Icons.PORTRAIT_16));
      registerDefaultBean(I18N.getLocalizedMessage("GridAllocatorRenderer"),
                         "ro.siveco.senro.designer.components.GridAllocatorRendererComponent",
                          FormDesignerUtils.loadImage(Icons.PORTRAIT_16));
      registerDefaultBean(I18N.getLocalizedMessage("Template"),
                         "ro.siveco.senro.designer.components.TemplateComponent",
                          FormDesignerUtils.loadImage(Icons.PORTRAIT_16));
      registerDefaultBean(I18N.getLocalizedMessage("RootPanel"),
                         "ro.siveco.senro.designer.components.RootPanelComponent",
                          FormDesignerUtils.loadImage(Icons.PORTRAIT_16));
      registerDefaultBean(I18N.getLocalizedMessage("Tree"),
                         "ro.siveco.senro.designer.components.TreeComponent",
                          FormDesignerUtils.loadImage(Icons.PORTRAIT_16));
   }

   public void setModel(ImportedBeansModel ibm) {
      m_ibm = ibm;
      m_loader = null;
      try {
         registerBeans(m_ibm, getBeanLoader());

         ObjectStore os = (ObjectStore) JETARegistry.lookup(ComponentNames.APPLICATION_STATE_STORE);
         os.store(ImportedBeansModel.COMPONENT_ID, m_ibm);
         os.flush(ImportedBeansModel.COMPONENT_ID);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

}
