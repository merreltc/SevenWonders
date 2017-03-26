package guiMain;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class RenderImage {

	private Image image;
	private JPanel panel;
	private Graphics g;

	public RenderImage() {
	}

	public void draw(Graphics g, String name, int x, int y, int width, int height) {
		BufferedImage img = null;
		try {
			/* TODO Add correct File path to the below command */
			img = ImageIO.read(new File("images\\" + name + ".png"));
			g.drawImage(img, x, y, width, height, null);
		} catch (IOException e) {
			System.out.println("Fail");
		}

	}

}
