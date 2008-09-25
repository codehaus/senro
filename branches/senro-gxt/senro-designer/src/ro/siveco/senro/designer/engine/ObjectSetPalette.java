package ro.siveco.senro.designer.engine;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;

import javax.swing.*;
import java.util.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.net.URL;

import ro.siveco.senro.designer.objects.ObjectDescription;
import ro.siveco.senro.designer.ui.SfdCell;

public class ObjectSetPalette extends JPanel implements ActionListener
{
    public static final String ARROW_BUTTON_ACTION_CMD = "NoObjectInstance";
    public static final String ARROW_FILE_NAME = "PaletteArrow.png";
    public static final int TOGGLE_BUTTON_WIDTH = 60;
    public static final int TOGGLE_BUTTON_HEIGHT = 20;

    protected Map<String, Class> actionCmdToObjClass = new HashMap<String, Class>();
    protected Set<JToggleButton> toggleButtons = new HashSet<JToggleButton>();
    protected String selectedActionCmd = ARROW_BUTTON_ACTION_CMD;

    public ObjectSetPalette(String name)
    {
        setName(name);
        addToggleButton(ARROW_FILE_NAME, ARROW_BUTTON_ACTION_CMD, null);
    }

    public void setEnabled(boolean enabled)
    {
        for(JToggleButton button: toggleButtons) {
            button.setEnabled(enabled);
        }
    }

    public void addToggleButton(String img_file_name, String action_cmd, Class obj_class)
    {
        JToggleButton the_button;
        URL img_url = ObjectSetPalette.class.getResource(DesignerManager.RELATIVE_PATH_TO_IMG + img_file_name);
        if (img_url != null) {
            ImageIcon img_icon = new ImageIcon(img_url);
            the_button = new JToggleButton(SfdCell.getScaledImageIcon(img_icon, TOGGLE_BUTTON_WIDTH, TOGGLE_BUTTON_HEIGHT));
        } else {
            the_button = new JToggleButton(action_cmd);
        }
        the_button.setPreferredSize(new Dimension(TOGGLE_BUTTON_WIDTH, TOGGLE_BUTTON_HEIGHT));
        the_button.setActionCommand(action_cmd);
        the_button.addActionListener(this);
        toggleButtons.add(the_button);
        actionCmdToObjClass.put(action_cmd, obj_class);
        update();
    }

    public void removeToggleButton(String action_cmd)
    {
        actionCmdToObjClass.remove(action_cmd);
        toggleButtons.remove(getToggleButtonWithCmd(action_cmd));
        update();
    }

    private JToggleButton getToggleButtonWithCmd(String action_cmd)
    {
        for (JToggleButton button : toggleButtons) {
            if (button.getActionCommand().equals(action_cmd)) {
                return button;
            }
        }
        return null;
    }

    public void selectToggleButtonWithCmd(String action_cmd)
    {
        JToggleButton button_to_select = getToggleButtonWithCmd(action_cmd);
        if(button_to_select != null) {
            button_to_select.setSelected(true);
        }
        for (JToggleButton button : toggleButtons) {
            if (button == button_to_select) {
                continue;
            }
            button.setSelected(false);
        }
        selectedActionCmd = action_cmd;
    }

    public void update()
    {
        int n = toggleButtons.size();
        String cols = "1dlu, fill:pref, 1dlu";
        StringBuffer rows_buff = new StringBuffer("1dlu");
        for (int i = 0; i < n; i++) {
            rows_buff.append(", fill:pref, 1dlu");
        }
        FormLayout layout = new FormLayout(cols, rows_buff.toString());
        setLayout(layout);
        setBorder(null);
        CellConstraints cc = new CellConstraints();
        int col = 2;
        int row = 2;
        for (JToggleButton toggleButton : toggleButtons) {
            if (toggleButton.getActionCommand().equals(ARROW_BUTTON_ACTION_CMD)) {
                add(toggleButton, cc.xy(2, 2));
            } else {
                row += 2;
                add(toggleButton, cc.xy(col, row));
            }
        }
        revalidate();
    }


    public void actionPerformed(ActionEvent e)
    {
        String selected_action_cmd = e.getActionCommand();

        JToggleButton clicked_button = getToggleButtonWithCmd(selected_action_cmd);
        if (clicked_button != null && clicked_button.isSelected()) {
            for (JToggleButton button : toggleButtons) {
                if (button == clicked_button) {
                    continue;
                }
                button.setSelected(false);
            }
            selectedActionCmd = selected_action_cmd;
        } else {
            selectToggleButtonWithCmd(ARROW_BUTTON_ACTION_CMD);
        }
    }

    public ObjectDescription getSelectedObjectInstance()
    {
        ObjectDescription obj_desc = null;
        Class obj_instance_class = actionCmdToObjClass.get(selectedActionCmd);
        if (obj_instance_class != null) {
            try {
                obj_desc = (ObjectDescription) obj_instance_class.newInstance();
            }
            catch (Exception ex) {
                throw new EngineRuntimeException("Cannot create instance for class: '" + obj_instance_class.getName() + "'", ex);
            }
        }
        return obj_desc;
    }
}
