package ro.siveco.senro.designer.components;

import java.awt.Graphics;
import java.awt.Color;

public class TemplateRendererComponent extends TemplateComponent
{
    public void paint(Graphics g)
    {
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, 100, 100);
        // not implemented
//        super.paint(g);
    }
}
