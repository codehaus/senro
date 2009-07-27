package ro.siveco.senro.designer.inspectors;

import ro.siveco.senro.designer.inspector.Inspector;
import ro.siveco.senro.designer.basic.UIDesignerObject;
import ro.siveco.senro.designer.util.event.Observer;
import ro.siveco.senro.designer.util.event.Event;
import ro.siveco.senro.designer.util.event.EventCenter;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.builder.PanelBuilder;

public class CommonUIInspector implements Inspector, Observer
{
    protected JPanel panel;
    protected UIDesignerObject uiDesignerObject;
    protected String title;
    protected JTextField nameTF;
    protected JTextField idTF;
    protected JTextField rowTF;
    protected JTextField colTF;
    protected Timer timer;

    public CommonUIInspector()
    {
        title = "Senro Designer Object Inspector";
        init();
        panel = buildPanel();
        addListeners();
    }

    protected void init()
    {
        uiDesignerObject = null;
        nameTF = new JTextField();
        idTF = new JTextField();
        rowTF = new JTextField();
        colTF = new JTextField();
        ActionListener updater = new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                updateUI();
            }
        };
        timer = new Timer(0, updater);
        timer.setRepeats(false);
    }

    protected JPanel buildPanel()
    {
        FormLayout layout = new FormLayout("1dlu, fill:pref, 1dlu, 120:grow, 1dlu",
                "1dlu, fill:pref,  1dlu, fill:pref, 1dlu, fill:pref, 1dlu, fill:pref, 1dlu");
        PanelBuilder builder = new PanelBuilder(layout);
        builder.setDefaultDialogBorder();
        CellConstraints cc = new CellConstraints();
        builder.add(new JLabel("Name", JLabel.RIGHT), cc.xy(2, 2));
        builder.add(nameTF, cc.xy(4, 2));
        builder.add(new JLabel("Id", JLabel.RIGHT), cc.xy(2, 4));
        builder.add(idTF, cc.xy(4, 4));
        builder.add(new JLabel("RowExpr", JLabel.RIGHT), cc.xy(2, 6));
        builder.add(rowTF, cc.xy(4, 6));
        builder.add(new JLabel("ColumnExpr", JLabel.RIGHT), cc.xy(2, 8));
        builder.add(colTF, cc.xy(4, 8));
        return builder.getPanel();
    }

    protected void addListeners()
    {
        nameTF.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (uiDesignerObject == null) {
                    return;
                }
                uiDesignerObject.setName(nameTF.getText());
            }
        });
        idTF.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (uiDesignerObject == null) {
                    return;
                }
                uiDesignerObject.setId(idTF.getText());
            }
        });
        rowTF.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (uiDesignerObject == null) {
                    return;
                }
                uiDesignerObject.setRowExpr(rowTF.getText());
            }
        });
        colTF.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (uiDesignerObject == null) {
                    return;
                }
                uiDesignerObject.setColumnExpr(colTF.getText());
            }
        });
    }

    public String getTitle()
    {
        return title;
    }

    public JPanel getPanel()
    {
        return panel;
    }

    public void setObject(Object o)
    {
        if (uiDesignerObject != null) {
            EventCenter.remove(this, uiDesignerObject, Event.class);
        }
        uiDesignerObject = (UIDesignerObject) o;
        if (uiDesignerObject != null) {
            EventCenter.add(this, uiDesignerObject, Event.class);
        }
        updateUI();
    }

    public void updateUI()
    {
        if (uiDesignerObject == null) {
            return;
        }
        nameTF.setText(uiDesignerObject.getName());
        idTF.setText(uiDesignerObject.getId());
        rowTF.setText(uiDesignerObject.getRowExpr());
        colTF.setText(uiDesignerObject.getColumnExpr());
    }

    public void handleEvent(Event event)
    {
        if(!timer.isRunning()) {
            timer.start();
        }
    }
}