package ro.siveco.senro.designer.engine;

import ro.siveco.senro.designer.association.AssociationDescription;
import ro.siveco.senro.designer.util.UIUtil;

import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;

public class AssociationChooser extends JDialog
{
    private static AssociationChooser sharedChooser = null;

    private JList assocList;
    List<AssociationDescription> descriptions;
    private AssociationDescription selectedDescription;

    public AssociationChooser()
    {
        super(DesignerManager.getSharedDesignerManager().getMainFrame(), "Choose association");
        FormLayout layout = new FormLayout("fill:12px, fill:pref:grow, fill:12px",
                                           "fill:12px, fill:pref:grow, fill:17px, center:default, fill:12px");
        JPanel content = new JPanel(layout);
        CellConstraints cc = new CellConstraints();
        content.add(getButtonPanel(), cc.xy(2, 4));
        assocList = new JList();
        JScrollPane scroll_pane = new JScrollPane(assocList);
        assocList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        content.add(scroll_pane, cc.xy(2, 2));
        getRootPane().setLayout(new BorderLayout());
        getRootPane().add(content, BorderLayout.CENTER);
    }

    private JPanel getButtonPanel()
    {
        JPanel button_panel = new JPanel();
        FormLayout layout = new FormLayout("center:default:grow, fill:pref, fill:5px, fill:pref",
                                           "center:default");
        layout.setColumnGroups(new int[][] { { 2, 4 } });
        button_panel.setLayout(layout);
        JButton ok_button = new JButton("Ok");
        ok_button.setName("ok.button");
        JButton cancel_button = new JButton("Cancel");
        cancel_button.setName("cancel.button");
        CellConstraints cc = new CellConstraints();
        button_panel.add(ok_button, cc.xy(2, 1));
        button_panel.add(cancel_button, cc.xy(4, 1));
        ok_button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                ok();
            }
        });
        cancel_button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                cancel();
            }
        });
        return button_panel;
    }

    public void setAssociationDescriptions(List<AssociationDescription> descriptions)
    {
        selectedDescription = null;
        this.descriptions = new ArrayList<AssociationDescription>(descriptions);
        assocList.setModel(new AbstractListModel()
        {
            public int getSize()
            {
                return AssociationChooser.this.descriptions.size();
            }

            public Object getElementAt(int index)
            {
                return AssociationChooser.this.descriptions.get(index).getName();
            }
        });
    }

    public void ok()
    {
        int sel_idx = assocList.getSelectedIndex();
        if(sel_idx < 0) {
            return;
        }
        selectedDescription = descriptions.get(sel_idx);
        setVisible(false);
    }

    public void cancel()
    {
        setVisible(false);
    }

    public AssociationDescription getSelectedDescription()
    {
        return selectedDescription;
    }

    public static AssociationDescription chooseAssociationDescription(List<AssociationDescription> factories)
    {
        if(sharedChooser == null) {
            sharedChooser = new AssociationChooser();
        }
        sharedChooser.setAssociationDescriptions(factories);
        sharedChooser.pack();
        UIUtil.locateOnScreenCenter(sharedChooser);
        sharedChooser.setModal(true);
        sharedChooser.setVisible(true);
        return sharedChooser.getSelectedDescription();
    }

}
