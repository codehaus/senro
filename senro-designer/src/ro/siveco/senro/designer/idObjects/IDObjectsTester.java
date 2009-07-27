package ro.siveco.senro.designer.idObjects;

import ro.siveco.senro.designer.util.UIUtil;
import ro.siveco.senro.designer.components.SenroTextArea;
import ro.siveco.senro.designer.components.SenroTextField;
import ro.siveco.senro.designer.components.SenroCheckBox;
import ro.siveco.senro.designer.components.SenroComboBox;
import ro.siveco.senro.designer.components.SenroLabel;

import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.BorderLayout;

public class IDObjectsTester
{
    public static void main(String[] args)
    {
        GridView gv = new GridView();
        ComponentSetManager comp_set_manager = new ComponentSetManager();
        gv.addGridSelectionListener(comp_set_manager);
        gv.setModel(comp_set_manager);
        List<Component> comps = new ArrayList<Component>();
        SenroLabel sl = new SenroLabel();
        sl.setText("My Label 1");
        comps.add(sl);
        SenroTextArea ta = new SenroTextArea();
        ta.setText("Olala-TextArea");
        comps.add(ta );
        SenroLabel tf = new SenroLabel();
        tf.setText("Hello!");
        comps.add(tf);
        comps.add(new SenroCheckBox());
        SenroComboBox scb = new SenroComboBox();
        comps.add(scb);
        SenroLabel sl1 = new SenroLabel();
        sl1.setText("My Label 2");
        comps.add(sl1);
        comp_set_manager.loadData(comps);
        comp_set_manager.setEnabled(true);

        JFrame frame = new JFrame("Test");
        Container cont = frame.getContentPane();
        cont.setLayout(new BorderLayout());
        cont.add(comp_set_manager.getPresentationPanel(), BorderLayout.CENTER);
        frame.pack();
        UIUtil.locateOnScreenCenter(frame);
        frame.setVisible(true);
    }
}
