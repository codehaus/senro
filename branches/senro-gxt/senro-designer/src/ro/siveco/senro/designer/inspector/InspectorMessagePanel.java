package ro.siveco.senro.designer.inspector;

import javax.swing.*;
import java.awt.*;

public class InspectorMessagePanel extends JPanel
{
  public static final String NULL_INSPECTOR = "No object to inspect";
  public static final String NO_INSPECTOR = "No inspector for class: \n";

  protected JTextArea messageTA;

  public InspectorMessagePanel()
  {
    super();
    messageTA = new JTextArea();
    messageTA.setLineWrap(true);
    messageTA.setWrapStyleWord(true);
    messageTA.setEditable(false);  
    JScrollPane comment_pane = new JScrollPane(messageTA);
    comment_pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    comment_pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    setLayout(new GridLayout(1, 1));
    add(messageTA);
  }

  public void setMessageForClass(String class_name)
  {
    String message = (class_name == null ? NULL_INSPECTOR : NO_INSPECTOR + class_name);
    messageTA.setText(message);
  }
}