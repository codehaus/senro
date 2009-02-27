package ro.siveco.senro.designer.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class SfdCell extends MatrixCell {

	protected ImageIcon icon = null;
	protected String description = null;
	protected MissingIcon missingIcon = new MissingIcon();
	protected Color textColor = Color.BLACK;
	protected Font font = new Font("SansSerif", Font.BOLD, 11);

	public SfdCell(MatrixView parent) {
		this(parent, null);
	}

	public SfdCell(MatrixView parent, ImageIcon icon) {
		this(parent, icon, null);
	}

	public SfdCell(MatrixView parent, ImageIcon icon, String description) {
		super(parent);
		this.icon = icon;
		this.description = description;
	}

	public void paint(Graphics2D g, Dimension  availableRect) {
		g.setFont(font);
        int icon_w = availableRect.width;
        int icon_h = availableRect.height;
        if(description != null) {
            Rectangle2D text_rect =  font.getStringBounds(description, g.getFontRenderContext());
		    int text_height = text_rect.getBounds().height;
		    int text_width = text_rect.getBounds().width;
            icon_h -= text_height;
            float ascent = font.getLineMetrics(description, g.getFontRenderContext()).getAscent();
            float text_baseline_y = icon_h + ascent;
		    float text_start_x = (text_width >= icon_w ? 0 : (icon_w - text_width)/2);
		    g.drawString(description, text_start_x, text_baseline_y);
        }

		if(icon == null) {
			missingIcon.paintIcon(parent, g, (icon_w - missingIcon.width)/2, (icon_h - missingIcon.height)/2);
			return;
		}
		ImageIcon scaled_icon = SfdCell.getScaledImageIcon(icon, icon_w, icon_h);
		if(scaled_icon != null)
			scaled_icon.paintIcon(parent, g, (icon_w - scaled_icon.getIconWidth())/2, (icon_h - scaled_icon.getIconHeight())/2);
	}

	public ImageIcon getIcon() {
		return icon;
	}

	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Color getTextColor() {
		return textColor;
	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public static ImageIcon getScaledImageIcon(ImageIcon icon, int new_w, int new_h){
		if(icon == null || new_w == 0 || new_h == 0)
			return null;
		int w = icon.getIconWidth();
		int h = icon.getIconHeight();
		double factor = Math.min((double)new_w/w, (double)new_h/h);
		Image scaled_img = icon.getImage().getScaledInstance((int)(w * factor), (int)(h* factor), Image.SCALE_SMOOTH);
		 return new ImageIcon(scaled_img);
	}

	public class MissingIcon implements Icon{

	    private int width = 32;
	    private int height = 32;

	    private BasicStroke stroke = new BasicStroke(4);

	    public void paintIcon(Component c, Graphics g, int x, int y) {
	        Graphics2D g2d = (Graphics2D) g.create();

	        g2d.setColor(Color.WHITE);
	        g2d.fillRect(x +1 ,y + 1,width -2 ,height -2);

	        g2d.setColor(Color.BLACK);
	        g2d.drawRect(x +1 ,y + 1,width -2 ,height -2);

	        g2d.setColor(Color.RED);

	        g2d.setStroke(stroke);
	        g2d.drawLine(x +10, y + 10, x + width -10, y + height -10);
	        g2d.drawLine(x +10, y + height -10, x + width -10, y + 10);

	        g2d.dispose();
	    }

	    public int getIconWidth() {
	        return width;
	    }

	    public int getIconHeight() {
	        return height;
	    }
	}
}
