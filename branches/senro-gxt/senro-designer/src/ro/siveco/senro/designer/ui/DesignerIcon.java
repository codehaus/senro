package ro.siveco.senro.designer.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class DesignerIcon implements Icon
{
    public static final int DEFAULT_WIDTH = 12;
    public static final int DEFAULT_HEIGHT = 12;
    public static final Font FONT = new Font("SansSerif", Font.BOLD, 7);

    private int width;
    private int height;
    private String text = "";

    public DesignerIcon(int width, int height, String text)
    {
        this.width = width;
        this.height = height;
        this.text = text;
    }

    public DesignerIcon(String text)
    {
        this(DEFAULT_WIDTH, DEFAULT_HEIGHT, text);
    }

    public void paintIcon(Component c, Graphics g, int x, int y)
    {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(c.getBackground());
        g2d.fillRect(x + 1, y + 1, width - 2, height - 2);

        g2d.setColor(c.getForeground().brighter());
        g2d.drawRect(x + 1, y + 1, width - 2, height - 2);

        g2d.setColor(Color.RED);
        g2d.setFont(FONT);
        if(text != null) {
            Rectangle2D text_rect =  FONT.getStringBounds(text, g2d.getFontRenderContext());
		    int text_height = text_rect.getBounds().height;
		    int text_width = text_rect.getBounds().width;
            float ascent = FONT.getLineMetrics(text, g2d.getFontRenderContext()).getAscent();
            float text_baseline_y = (height - text_height)/2f + ascent + y + 1;
		    float text_start_x = (text_width >= width ? 1 : (width - text_width)/2f) + x;
		    g2d.drawString(text, text_start_x, text_baseline_y);
        }

        g2d.dispose();
    }

    public int getIconWidth()
    {
        return width;
    }

    public int getIconHeight()
    {
        return height;
    }
}
