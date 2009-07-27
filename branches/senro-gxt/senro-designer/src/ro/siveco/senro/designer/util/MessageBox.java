package ro.siveco.senro.designer.util;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.builder.PanelBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class MessageBox extends Dialog implements ActionListener
{
    protected JTextArea messageTextArea;
    protected JButton okButton;
    protected JButton cancelButton;
    protected JButton customButton;
    protected String customAction = null;
    protected int response;
    protected int type;

    // action commands
    public static final String OK_ACTION = "Ok";
    public static final String CANCEL_ACTION = "Cancel";
    public static final int OK_OPTION = 0;
    public static final int CANCEL_OPTION = 1;
    public static final int CUSTOM_OPTION = 2;

    // type
    public static final int OK_BUTTON = 0;
    public static final int OK_CANCEL_BUTTON = 1;
    public static final int CUSTOM_BUTTON = 2;

    public static final String NO_MESSAGE = "No Message";

    public MessageBox(JFrame frame, String title, String message, int type)
    {
        this(frame, title, message, type, null);
    }

    public MessageBox(JFrame frame, String title, String message, int type, String custom_action)
    {
        super(frame, title, true);
        this.type = type;
        customAction = custom_action;

        if ((message == null) || (message.trim().length() == 0)) {
            message = NO_MESSAGE;
        }
        messageTextArea = new JTextArea(message);
        JScrollPane message_pane = new JScrollPane(messageTextArea);
        message_pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        message_pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        messageTextArea.setEditable(false);
        messageTextArea.setBackground(Color.white);
        messageTextArea.setForeground(Color.black);
        messageTextArea.setLineWrap(true);

        JPanel button_panel = getButtonsPanel(type);
        FormLayout layout = new FormLayout("10dlu, fill:pref:grow, fill:pref , fill:pref:grow, 10dlu",
                "10dlu, 100dlu:grow, 10dlu, fill:pref, 10dlu");
        PanelBuilder builder = new PanelBuilder(layout);
        builder.setDefaultDialogBorder();
        CellConstraints cc = new CellConstraints();
        message_pane.setPreferredSize(new Dimension(300, 300));
        builder.add(message_pane, cc.xyw(2, 2, 3));
        builder.add(button_panel, cc.xy(3, 4));
        add(builder.getPanel(), BorderLayout.CENTER);
    }

    private JPanel getButtonsPanel(int type)
    {
        JPanel button_panel = new JPanel();
        if (type == OK_BUTTON) {
            button_panel.setLayout(new GridLayout(1, 1));
            button_panel.add(okButton = new JButton(OK_ACTION));
            okButton.setActionCommand(OK_ACTION);
            okButton.addActionListener(this);
        } else if (type == OK_CANCEL_BUTTON) {
            button_panel.setLayout(new GridLayout(1, 2));
            button_panel.add(okButton = new JButton(OK_ACTION));
            okButton.setActionCommand(OK_ACTION);
            okButton.addActionListener(this);
            button_panel.add(cancelButton = new JButton(CANCEL_ACTION));
            cancelButton.setActionCommand(CANCEL_ACTION);
            cancelButton.addActionListener(this);
        } else if (type == CUSTOM_BUTTON) {
            if (customAction == null || StringUtils.isBlank(customAction)) {
                return getButtonsPanel(OK_CANCEL_BUTTON);
            }
            button_panel.setLayout(new GridLayout(1, 3));
            button_panel.add(okButton = new JButton(OK_ACTION));
            okButton.setActionCommand(OK_ACTION);
            okButton.addActionListener(this);
            button_panel.add(cancelButton = new JButton(CANCEL_ACTION));
            cancelButton.setActionCommand(CANCEL_ACTION);
            cancelButton.addActionListener(this);
            button_panel.add(customButton = new JButton(customAction));
            customButton.setActionCommand(customAction);
            customButton.addActionListener(this);
        }
        return button_panel;
    }

    public static int showDialogBox(JFrame frame, String title, String message, int type)
    {
        return showDialogBox(frame, title, message, type, null);
    }

    public static int showDialogBox(JFrame frame, String title, String message, int type, String custom_action)
    {
        MessageBox dialog = new MessageBox(frame, title, message, type, custom_action);
        dialog.setName(title);
        dialog.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        dialog.setLocation((screenSize.width - dialog.getSize().width) / 2,
                (screenSize.height - dialog.getSize().height) / 2);
        dialog.setVisible(true);
        return dialog.response;
    }

    public void actionPerformed(ActionEvent event)
    {
        String action_cmd = event.getActionCommand();

        if (action_cmd.equals(OK_ACTION)) {
            response = OK_OPTION;
        } else if (action_cmd.equals(CANCEL_ACTION)) {
            response = CANCEL_OPTION;
        } else if (action_cmd.equals(customAction)) {
            response = CUSTOM_OPTION;
        }
        dispose();
    }
}
