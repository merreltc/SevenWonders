package gui.interactables;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Point2D;

import constants.Constants;
import utils.RenderImage;

public abstract class Interactable {

	private Point boundPoint;
	private Point positionPoint;
	private String value;
	private boolean drawUnit = true;
	private Image image = null;
	RenderImage renderer = new RenderImage();
	private Color backgroundColor;
	private Color textColor;

	public Interactable(Point positionPoint, Point boundPoint, String value, Color backgroundColor, Color textColor) {
		this.boundPoint = boundPoint;
		this.positionPoint = positionPoint;
		this.value = value;
		this.backgroundColor = backgroundColor;
		this.textColor = textColor;
	}

	public boolean checkButtonClicked(Point point) {
		if (positionPoint.x <= point.x && positionPoint.x + boundPoint.x >= point.x) {
			if (positionPoint.y <= point.y && positionPoint.y + boundPoint.y >= point.y) {
				return true;
			}
		}
		return false;
	}

	public void draw(Graphics graphics) {
		if (drawUnit) {
			graphics.setColor(backgroundColor);
			graphics.fillRect(this.positionPoint.x, this.positionPoint.y, this.boundPoint.x, this.boundPoint.y);
			graphics.setColor(textColor);
			graphics.setFont(Constants.ButtonFont);
			graphics.drawString(value, positionPoint.x + 10, positionPoint.y + this.boundPoint.y - 10);
		} else {
			renderer.setImage(this.image);
			renderer.draw(graphics,
					new int[] { this.positionPoint.x, this.positionPoint.y, this.boundPoint.x, this.boundPoint.y });
		}
	}

	public void hide() {
		this.drawUnit = false;
	}

	public Point2D getPosition() {
		return this.positionPoint;
	}

	public String getValue() {
		return this.value;
	}

	public void addImage(Image image) {
		this.image = image;
		this.drawUnit = false;
	}

}
