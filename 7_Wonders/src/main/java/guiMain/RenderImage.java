package guiMain;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class RenderImage {
	
	public static void draw(Graphics graphics, Image image, int x, int y, int width, int height) {
			graphics.drawImage(image, x, y, width, height, null);
	}
	
	public static Image getImage(String name) {
		try {
			BufferedImage image = ImageIO.read(new File("Images\\" + name + ".png"));
			return image;
		}
		catch (IOException e) {
				System.err.println("Cannot load image:" + name);
		}
		
		try {
			BufferedImage image = ImageIO.read(new File("Images\\x.png"));
			return image;
		}
		catch (IOException e) {
				System.err.println("Cannot load image:" + name);
		}
		return new BufferedImage(0, 0, 0);
	}

}
