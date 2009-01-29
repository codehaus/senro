package ro.siveco.senro.designer.util;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.builder.PanelBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MessageBox extends Dialog implements ActionListener
{
    protected JTextArea messageTextArea;
    protected JButton okButton;
    protected JButton cancelButton;
    protected int response;
    protected int type;

    // action commands
    public static final String OK_ACTION = "Ok";
    public static final String CANCEL_ACTION = "Cancel";
    public static final int OK_OPTION = 0;
    public static final int CANCEL_OPTION = 1;

    // type
    public static final int OK_BUTTON = 0;
    public static final int OK_CANCEL_BUTTON = 1;

    public static final String NO_MESSAGE = "No Message";


    public MessageBox(JFrame frame, String title, String message, int type)
    {
        super(frame, title, true);
        this.type = type;

        if((message == null) || (message.trim().length() == 0)) {
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

        Panel button_panel = new Panel();
        if (type == OK_BUTTON) {
            button_panel.setLayout(new GridLayout(1, 1));
            button_panel.add(okButton = new JButton(OK_ACTION));
            okButton.setActionCommand(OK_ACTION);
            okButton.addActionListener(this);
        }
        if (type == OK_CANCEL_BUTTON) {
            button_panel.setLayout(new GridLayout(1, 2));
            button_panel.add(okButton = new JButton(OK_ACTION));
            okButton.setActionCommand(OK_ACTION);
            okButton.addActionListener(this);
            button_panel.add(cancelButton = new JButton(CANCEL_ACTION));
            cancelButton.setActionCommand(CANCEL_ACTION);
            cancelButton.addActionListener(this);
        }

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

    public static int showDialogBox(JFrame frame, String title, String message, int type)
    {
        MessageBox dialog = new MessageBox(frame, title, message, type);
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
        }
        if (action_cmd.equals(CANCEL_ACTION)) {
            response = CANCEL_OPTION;
        }
        dispose();
    }
}
