package ro.siveco.senro.designer;

import com.jeta.forms.gui.form.FormComponent;
import com.jeta.swingbuilder.gui.editor.FormEditor;
import com.jeta.swingbuilder.gui.formmgr.AbstractFormManager;
import com.jeta.swingbuilder.gui.formmgr.FormManagerDesignUtils;

import java.awt.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/**
 * The main form manager for the application.
 */
public class IBMainFormManager extends AbstractFormManager
{

    private IBMainFrame mainFrame;

    public IBMainFormManager(IBMainFrame frame)
    {
        super(frame, frame);
        mainFrame = frame;
    }

    /**
     * Closes the form in the editor.
     */
    public void closeForm(String formId)
    {
        FormComponent form = getForm(formId);
        Collection child_forms = null;
        if (form != null) {
            child_forms = FormManagerDesignUtils.getNestedForms(this, form);
        }

        if (mainFrame != null) {
            mainFrame.removeForm(formId);
        }
        FormManagerDesignUtils.clearUnreferencedForms();
    }

    /**
     * @return the form that is currently active in the editor
     */
    public Container getCurrentEditor()
    {
        if (mainFrame != null) {
            return mainFrame.getCurrentEditor();
        } else {
            return null;
        }
    }

    /**
     * @return true if the given form is opened in a top level editor.
     */
    public boolean isOpened(String formId)
    {
        if (mainFrame == null) {
            return false;
        } else {
            return (mainFrame.getForm(formId) != null);
        }
    }

    /**
     * @return true if the given formId is opened in any editor either as a top
     *         level form or as a nest
     */
    public boolean isReferenced(String formId)
    {
        /**
         * check if any opened editors have the given form opened as a nested
         * child. If so, then simply return. Otherwise, we can safely remove the
         * form from the cache
         */
        Collection editors = mainFrame == null ? Collections.EMPTY_LIST : mainFrame.getEditors();
        Iterator iter = editors.iterator();
        while (iter.hasNext()) {
            FormEditor editor = (FormEditor) iter.next();
            FormComponent fc = editor.getForm();
            if (FormManagerDesignUtils.containsForm(fc, formId)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Only shows the form in the editor. No synchronization is made with any
     * other views.
     */
    public void showForm(String formId)
    {
        FormComponent fc = getForm(formId);
        if (fc != null) {
            if (mainFrame != null) {
                mainFrame.showForm(fc);
            }
        } else {
            System.out.println("MainFormManager.showForm failed: " + formId);
            assert (false);
        }
    }
}
