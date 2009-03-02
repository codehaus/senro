package ro.siveco.senro.designer.util.ui;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;

import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import ro.siveco.senro.designer.engine.DesignerManager;
import ro.siveco.senro.designer.util.UIUtil;

public class ChoosePanel extends JDialog
{
    private static ChoosePanel sharedChooser = null;

    private JList itemsList;
    List<String> items;
    private int selectedIndex;

    public ChoosePanel(String title)
    {
        super(DesignerManager.getSharedDesignerManager().getMainFrame(), title);
        FormLayout layout = new FormLayout("fill:12px, fill:pref:grow, fill:12px",
                                           "fill:12px, fill:pref:grow, fill:17px, center:default, fill:12px");
        JPanel content = new JPanel(layout);
        CellConstraints cc = new CellConstraints();
        content.add(getButtonPanel(), cc.xy(2, 4));
        itemsList = new JList();
        JScrollPane scroll_pane = new JScrollPane(itemsList);
        itemsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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

    public void setItemsList(List<String> items_list)
    {
        selectedIndex = -1;
        items = new ArrayList<String>(items_list);
        itemsList.setModel(new AbstractListModel()
        {
            public int getSize()
            {
                return ChoosePanel.this.items.size();
            }

            public Object getElementAt(int index)
            {
                return ChoosePanel.this.items.get(index);
            }
        });
    }

    public void ok()
    {
        int sel_idx = itemsList.getSelectedIndex();
        if(sel_idx < 0) {
            return;
        }
        selectedIndex = sel_idx;
        setVisible(false);
    }

    public void cancel()
    {
        setVisible(false);
    }

    public int getSelectedIndex()
    {
        return selectedIndex;
    }

    public static int getChooseIndex(List<String> items, String title)
    {
        if(sharedChooser == null) {
            sharedChooser = new ChoosePanel("");
        }
        sharedChooser.setTitle(title);
        sharedChooser.setItemsList(items);
        sharedChooser.pack();
        UIUtil.locateOnScreenCenter(sharedChooser);
        sharedChooser.setModal(true);
        sharedChooser.setVisible(true);
        return sharedChooser.getSelectedIndex();
    }


}
