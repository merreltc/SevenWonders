package utils;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

public class RenderImage {

	private HashMap<String, Image> images = new HashMap<String, Image>();
	private Image image;
	
	public RenderImage(){
	}

	public void draw(Graphics graphics, int[] bounds) {
		graphics.drawImage(image, bounds[0], bounds[1], bounds[2], bounds[3], null);
	}

	public Image getImage(String name) {
		ResourceBundle messages = Translate.getNewResourceBundle();
		String translated = TranslateWithTemplate.prepareStringTemplateWithStringArg(name, "loadImageError", messages);

		if (this.images.containsKey(name)) {
			this.image = this.images.get(name);
			return this.images.get(name);
		}
		try {
			BufferedImage image = ImageIO.read(new File("Images\\" + name + ".png"));
			this.images.put(name, image);
			this.image = image;
			return image;
		} catch (IOException e) {
			System.err.println(translated);
		}

		try {
			BufferedImage image = ImageIO.read(new File("Images\\x.png"));
			this.images.put(name, image);
			return image;
		} catch (IOException e) {
			System.err.println(translated);
		}
		return new BufferedImage(0, 0, 0);
	}
	
	public void setImage(Image image){
		this.image = image;
	}
}
