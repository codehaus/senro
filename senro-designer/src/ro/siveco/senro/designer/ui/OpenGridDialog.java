package ro.siveco.senro.designer.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.builder.PanelBuilder;

public class OpenGridDialog extends Dialog implements ActionListener
{
    public static final String TITLE = "Open Grids";
    public static final Dimension BUTTON_SIZE = new Dimension(100, 20);
    public static final Dimension LIST_SCROLLPANE_PREF_SIZE = new Dimension(250, 100);

    protected JList gridFileNamesList;
    protected JButton okButton;
    protected JButton cancelButton;
    protected List<String> selectedGridFileNames = new ArrayList<String>();

    // action commands
    protected static final String OK_ACTION = "Ok";
    protected static final String CANCEL_ACTION = "Cancel";

    public OpenGridDialog(Frame owner, List<String> grid_file_names_list)
    {
        super(owner, TITLE, true);
        int owner_x = owner.getLocationOnScreen().x;
        int owner_y = owner.getLocationOnScreen().y;
        setLocation(owner_x + 50, owner_y + 100);

        DefaultListModel list_data_model = new DefaultListModel();
        for (String grid_file_name : grid_file_names_list) {
            list_data_model.addElement(grid_file_name);
        }
        gridFileNamesList = new JList(list_data_model);
        gridFileNamesList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        gridFileNamesList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e)
            {
                if(e.getClickCount() == 2) {
                    Object[] sel_obj = gridFileNamesList.getSelectedValues();
                    for (Object o : sel_obj) {
                        selectedGridFileNames.add((String) o);
                    }
                    dispose();
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(gridFileNamesList);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(LIST_SCROLLPANE_PREF_SIZE);

        okButton = new JButton(OK_ACTION);
        okButton.setActionCommand(OK_ACTION);
        okButton.addActionListener(this);
        okButton.setPreferredSize(BUTTON_SIZE);
        cancelButton = new JButton(CANCEL_ACTION);
        cancelButton.setActionCommand(CANCEL_ACTION);
        cancelButton.addActionListener(this);
        cancelButton.setPreferredSize(BUTTON_SIZE);

        FormLayout layout = new FormLayout("1dlu, fill:pref:grow, 1dlu, fill:pref:grow, 1dlu",
                "1dlu, fill:pref:grow, 1dlu, fill:pref, 1dlu");
        PanelBuilder builder = new PanelBuilder(layout);
        builder.setDefaultDialogBorder();
        CellConstraints cc = new CellConstraints();
        builder.add(scrollPane, cc.xyw(2, 2, 3));
        builder.add(okButton, cc.xy(2, 4));
        builder.add(cancelButton, cc.xy(4, 4));

        setLayout(new BorderLayout());
        add(builder.getPanel(), BorderLayout.CENTER);
    }

    public static List<String> showOpenGridDialog(Frame owner, List<String> grid_file_names_list)
    {
        OpenGridDialog dialog = new OpenGridDialog(owner, grid_file_names_list);
        dialog.pack();
        dialog.setVisible(true);
        return dialog.selectedGridFileNames;
    }

    public void actionPerformed(ActionEvent e)
    {
        String action_cmd = e.getActionCommand();

        if (action_cmd.equals(OK_ACTION)) {
            Object[] sel_obj = gridFileNamesList.getSelectedValues();
            for (Object o : sel_obj) {
                selectedGridFileNames.add((String) o);
            }
        }
        if (action_cmd.equals(CANCEL_ACTION)) {
            selectedGridFileNames.clear();
        }
        dispose();
    }
}
