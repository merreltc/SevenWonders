package guiMain;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class RenderImage {


	public void draw(Graphics graphics, String name, int x, int y, int width, int height) {

		try {
			/* TODO Add correct File path to the below command */
			BufferedImage image = ImageIO.read(new File(name));
			graphics.drawImage(image, x, y, width, height, null);
		} catch (IOException e) {
			System.out.println("Fail");
		}

	}

}
