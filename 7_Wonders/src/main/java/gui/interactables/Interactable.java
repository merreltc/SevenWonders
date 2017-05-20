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
	
	private class InteractableData {
		protected Point boundPoint;
		protected Point positionPoint;
		protected String value;
		protected boolean drawUnit = true;
		protected Image image = null;
		protected Color backgroundColor;
		protected Color textColor;
	}
	
	RenderImage renderer = new RenderImage();
	private InteractableData data = new InteractableData();


	public Interactable(Point positionPoint, Point boundPoint, String value, Color backgroundColor, Color textColor) {
		this.data.boundPoint = boundPoint;
		this.data.positionPoint = positionPoint;
		this.data.value = value;
		this.data.backgroundColor = backgroundColor;
		this.data.textColor = textColor;
	}

	public boolean checkButtonClicked(Point point) {
		if (this.data.positionPoint.x <= point.x && this.data.positionPoint.x + this.data.boundPoint.x >= point.x) {
			if (data.positionPoint.y <= point.y && data.positionPoint.y + data.boundPoint.y >= point.y) {
				return true;
			}
		}
		return false;
	}

	public void draw(Graphics graphics) {
		if (data.drawUnit) {
			graphics.setColor(data.backgroundColor);
			graphics.fillRect(this.data.positionPoint.x, this.data.positionPoint.y, this.data.boundPoint.x, this.data.boundPoint.y);
			graphics.setColor(data.textColor);
			graphics.setFont(Constants.ButtonFont);
			graphics.drawString(data.value, data.positionPoint.x + 10, data.positionPoint.y + this.data.boundPoint.y - 10);
		} else {
			renderer.setImage(this.data.image);
			renderer.draw(graphics,
					new int[] { this.data.positionPoint.x, this.data.positionPoint.y, this.data.boundPoint.x, this.data.boundPoint.y });
		}
	}

	public void hide() {
		this.data.drawUnit = false;
	}

	public Point2D getPosition() {
		return this.data.positionPoint;
	}

	public String getValue() {
		return this.data.value;
	}

	public void addImage(Image image) {
		this.data.image = image;
		this.data.drawUnit = false;
	}

}
