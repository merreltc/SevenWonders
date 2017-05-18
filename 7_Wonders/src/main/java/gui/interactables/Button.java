package gui.interactables;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;

import utils.RenderImage;

public class Button extends Interactable {
	public Button(Point position, Point bounds, String value) {
		super(position, bounds, value, Color.BLUE, Color.RED);
	}

}
