package guiMain;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;

public abstract class Interactable {
	
	private Point boundPoint;
	private Point positionPoint;
	private String value;
	private boolean showValue = true;
	private boolean drawUnit = true;

	
	public Interactable(Point positionPoint, Point boundPoint, String value){
		this.boundPoint = boundPoint;
		this.positionPoint = positionPoint;
		this.value = value;
	}

	public boolean checkButtonClicked(Point point) {
		if (positionPoint.x <= point.x && positionPoint.x + boundPoint.x >= point.x) {
			if (positionPoint.y <= point.y && positionPoint.y + boundPoint.y >= point.y) {
				return true;
			}
		}
		return false;
	}
	
	public void draw(Graphics graphics, Color backgroundColor, Color textColor) {
		if (drawUnit) {
			graphics.setColor(backgroundColor);
			graphics.fillRect(this.positionPoint.x, this.positionPoint.y, this.boundPoint.x, this.boundPoint.y);
			if (showValue) {
				graphics.setColor(textColor);
				graphics.setFont(new Font("Courier New", Font.BOLD, 50));
				graphics.drawString(value, positionPoint.x + 10, positionPoint.y + this.boundPoint.y - 10);
			}
		}
	}
	
	
	public Point2D getBounds() {
		return this.boundPoint;
	}

	public Point2D getPosition() {
		return this.positionPoint;
	}

	public String getValue() {
		return this.value;
	}
	
	public void showValue(boolean show){
		showValue = show;
	}
	
	public abstract void draw(Graphics graphics);
	
}
