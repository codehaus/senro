package ro.siveco.senro.designer.inspector;

import javax.swing.*;
import java.awt.*;

public class InspectorMessagePanel extends JPanel
{
    public static final String NULL_INSPECTOR = "No object to inspect";
    public static final String NO_INSPECTOR = "No inspector for class: \n";
    protected Font taFont = new Font("SansSerif", Font.BOLD, 12);
    protected Font lFont = new Font("SansSerif", Font.BOLD, 14);

    protected JTextArea messageTA;
    protected JLabel messageL;
    protected JScrollPane messageSP;

    public InspectorMessagePanel()
    {
        super();
        messageTA = new JTextArea();
        messageTA.setLineWrap(true);
        messageTA.setWrapStyleWord(true);
        messageTA.setEditable(false);
        messageTA.setFont(taFont);
        messageL = new JLabel(NULL_INSPECTOR, JLabel.CENTER);
        messageL.setFont(lFont);
        messageL.setForeground(Color.LIGHT_GRAY);
        messageSP = new JScrollPane(messageTA);
        messageSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        messageSP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        setLayout(new GridLayout(1, 1));
    }

    public void setMessageForClass(String class_name)
    {
        removeAll();
        if (class_name == null) {
            add(messageL);
        } else {
            messageTA.setText(NO_INSPECTOR + class_name);
            add(messageSP);
        }
        updateUI();
        revalidate();
    }
}