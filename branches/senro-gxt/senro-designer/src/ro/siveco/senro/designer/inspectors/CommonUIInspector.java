package ro.siveco.senro.designer.inspectors;

import ro.siveco.senro.designer.inspector.Inspector;
import ro.siveco.senro.designer.basic.UIDesignerObject;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.builder.PanelBuilder;

public class CommonUIInspector implements Inspector, ActionListener
{
    protected JPanel panel;
    protected UIDesignerObject uiDesignerObject;
    protected String title;
    protected JTextField nameTF = new JTextField();
    protected JTextField idTF = new JTextField();
    protected JTextField rowTF = new JTextField();
    protected JTextField colTF = new JTextField();

    public CommonUIInspector()
    {
        title = "Senro Designer Object Inspector";
        FormLayout layout = new FormLayout("1dlu, fill:pref, 1dlu, 120:grow, 1dlu",
                "1dlu:grow, fill:pref,  1dlu, fill:pref, 1dlu, fill:pref, 1dlu, fill:pref, 1dlu:grow");
        PanelBuilder builder = new PanelBuilder(layout);
        builder.setDefaultDialogBorder();
        CellConstraints cc = new CellConstraints();
        builder.add(new JLabel("Name", JLabel.RIGHT), cc.xy(2, 2));
        nameTF.addActionListener(this);
        builder.add(nameTF, cc.xy(4, 2));
        builder.add(new JLabel("Id", JLabel.RIGHT), cc.xy(2, 4));
        idTF.addActionListener(this);
        builder.add(idTF, cc.xy(4, 4));
        builder.add(new JLabel("Row", JLabel.RIGHT), cc.xy(2, 6));
        rowTF.addActionListener(this);
        builder.add(rowTF, cc.xy(4, 6));
        builder.add(new JLabel("Column", JLabel.RIGHT), cc.xy(2, 8));
        colTF.addActionListener(this);
        builder.add(colTF, cc.xy(4, 8));
        panel = builder.getPanel();
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
        uiDesignerObject = (UIDesignerObject) o;
        updateUI();
    }

    public void updateUI()
    {
        nameTF.setText(uiDesignerObject.getName());
        idTF.setText(uiDesignerObject.getId());
        rowTF.setText(uiDesignerObject.getRowExpr());
        colTF.setText(uiDesignerObject.getColumnExpr());
    }

    public void actionPerformed(ActionEvent e)
    {
        if(uiDesignerObject == null) {
            return;
        }
        Object source = e.getSource();
        if(source == nameTF) {
           uiDesignerObject.setName(nameTF.getText());
        } else if(source == idTF) {
           uiDesignerObject.setId(idTF.getText());
        } else if(source == rowTF) {
           uiDesignerObject.setRowExpr(rowTF.getText());
        } else if(source == colTF) {
           uiDesignerObject.setColumnExpr(colTF.getText());
        }
        updateUI();
    }
}