package guiMain;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;

public class Button extends Interactable{

	public Button(Point position, Point bounds, String value) {
		super(position, bounds, value);
	}

	@Override
	public void draw(Graphics graphics) {
		super.draw(graphics, Color.BLUE, Color.RED);
	}

}
