package ro.siveco.senro.designer.inspector;

import javax.swing.*;

public interface Inspector
{
    public String getTitle();

    public JPanel getPanel();

    public void setObject(Object o);

    public void updateUI();
}
