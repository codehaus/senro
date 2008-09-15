/*
 *                 Sun Public License Notice
 * 
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 * 
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2000 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package org.netbeans.editor.ext.java;

/**
* Java completion method parameter
*
* @author Miloslav Metelka
* @version 1.00
*/

/** Description of the method parameter */
public interface JCParameter extends Comparable {

    /** Name of the parameter */
    public String getName();

    /** Type of the parameter */
    public JCType getType();

}
