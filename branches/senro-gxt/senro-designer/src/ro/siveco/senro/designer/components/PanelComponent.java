package ro.siveco.senro.designer.components;

import javax.swing.*;
import java.awt.*;

public class PanelComponent extends JPanel
{
    public static final Color FOREGROUND_COLOR = Color.GRAY;
    public static final int BORDER = 3;
    public static final Font FONT = new Font("SansSerif", Font.BOLD, 12);
    protected JLabel panelText;

    public PanelComponent(String text, int width, int height)
    {
        panelText = new JLabel(text, JLabel.CENTER);
        panelText.setFont(FONT);
        panelText.setForeground(FOREGROUND_COLOR);
        setPreferredSize(new Dimension(width, height));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(FOREGROUND_COLOR, BORDER));
        add(panelText, BorderLayout.CENTER);
    }
}
