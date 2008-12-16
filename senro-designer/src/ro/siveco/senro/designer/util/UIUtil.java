package ro.siveco.senro.designer.util;

import javax.swing.*;
import java.awt.Image;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.Point;
import java.awt.AWTException;
import java.awt.Component;
import java.awt.Dimension;
import java.net.URL;

public class UIUtil
{

    private static Cursor noCursor = null;

    public static Image getImage(String image_path)
    {
        ImageIcon icon = null;
        URL img_URL = UIUtil.class.getResource(image_path);
        if(img_URL != null) {
            icon = new ImageIcon(img_URL, image_path);
            return icon.getImage();
        } else {
            return null;
        }
    }

    public static Cursor getNoCursor()
    {
        if(noCursor == null) {
            ImageIcon no_icon = null;
            URL img_URL = UIUtil.class.getResource("/resources/cursors/no_m.png");
            if(img_URL != null) {
                no_icon = new ImageIcon(img_URL, "NoIcon");
                int width = no_icon.getIconWidth();
                int height = no_icon.getIconHeight();
                noCursor = Toolkit.getDefaultToolkit().createCustomCursor(no_icon.getImage(),
                                                                          new Point(width/2, height/2), "NoCursor");
            }
        }
        return noCursor;
    }

    public static Cursor getLinkCursor()
    {
        return Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
    }

    public static void locateOnScreenCenter(Component component)
    {
        Dimension paneSize = component.getSize();
        Dimension screenSize = component.getToolkit().getScreenSize();
        component.setLocation((screenSize.width - paneSize.width)/2,
                              (screenSize.height - paneSize.height)/2);
    }

}
