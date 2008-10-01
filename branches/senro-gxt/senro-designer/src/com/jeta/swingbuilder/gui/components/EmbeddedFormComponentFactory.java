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

import com.jeta.forms.gui.common.FormException;
import com.jeta.forms.gui.components.ComponentSource;

import com.jeta.forms.gui.beans.JETABeanFactory;
import com.jeta.forms.gui.beans.JETABean;

import com.jeta.forms.gui.form.GridComponent;
import com.jeta.forms.gui.form.GridView;
import com.jeta.forms.gui.form.FormComponent;


import com.jeta.open.gui.framework.JETADialog;
import com.jeta.open.gui.utils.JETAToolbox;
import com.jeta.open.i18n.I18N;


/**
 * ComponentFactory for creating embedded forms.
 *
 * @author Jeff Tassin
 */
public class EmbeddedFormComponentFactory extends FormComponentFactoryBase
{

    /**
     * ctor
     */
    public EmbeddedFormComponentFactory()
    {
    }

    /**
     * ctor
     */
    public EmbeddedFormComponentFactory(ComponentSource compsrc)
    {
        super(compsrc);
    }

    /**
     * Helper method for creating a composite component that has a GridView as a child.
     */
    public FormComponent create(ComponentSource compsrc, String compName, GridView parentView) throws FormException
    {
        FormComponent fc = create(compsrc, compName, parentView, 3, 3, true);
        GridView.fillCells(fc.getChildView(), compsrc);
        return fc;
    }

    public GridComponent createComponent(String compName, GridView parentView) throws FormException
    {
        return create(getComponentSource(), compName, parentView);
    }

    public String getGridViewClassName()
    {
        return GridView.class.getName();
    }

}
