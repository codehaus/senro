package ro.siveco.senro.designer.components;

import javax.swing.*;
import java.awt.*;

public class PanelComponent extends JPanel
{
    public static final Color FOREGROUND_COLOR = Color.GRAY;
    public static final int BORDER = 3;
    public static final Font FONT = new Font("SansSerif", Font.BOLD, 12);

    public PanelComponent(String text, int width, int height)
    {
        JLabel textL = new JLabel(text, JLabel.CENTER);
        textL.setFont(FONT);
        textL.setForeground(FOREGROUND_COLOR);
        setPreferredSize(new Dimension(width, height));
        setLayout(new GridLayout(1, 1));
        setBorder(BorderFactory.createLineBorder(FOREGROUND_COLOR, BORDER));
        add(textL);
    }
}
