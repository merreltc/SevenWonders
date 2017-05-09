package guiMain;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class RenderImage {
	
	private HashMap<String, Image> images = new HashMap<String, Image>(); 
	ResourceBundle messages = ResourceBundle.getBundle("message", Locale.getDefault());
	
	public static void draw(Graphics graphics, Image image, int x, int y, int width, int height) {
			graphics.drawImage(image, x, y, width, height, null);
	}
	
	public Image getImage(String name) {
		Object[] messageArgs = {name};
		MessageFormat format = new MessageFormat("");
		format.setLocale(Locale.getDefault());
		format.applyPattern(messages.getString("loadImageError"));
		
		if (this.images.containsKey(name)){
			return this.images.get(name);
		}
		try {
			BufferedImage image = ImageIO.read(new File("Images\\" + name + ".png"));
			this.images.put(name, image);
			return image;
		}
		catch (IOException e) {
				System.err.println(format.format(messageArgs));
		}
		
		try {
			BufferedImage image = ImageIO.read(new File("Images\\x.png"));
			this.images.put(name, image);
			return image;
		}
		catch (IOException e) {
				System.err.println(format.format(messageArgs));
		}
		return new BufferedImage(0, 0, 0);
	}

}
