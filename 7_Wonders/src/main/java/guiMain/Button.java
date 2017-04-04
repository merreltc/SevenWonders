package guiMain;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;

public class Button {
	private Point boundPoint;
	private Point positionPoint;
	private String value;
	private boolean showValue;
	private boolean drawButton = true;

	public Button(Point position, Point bounds, String value) {
		this.boundPoint = bounds;
		this.positionPoint = position;
		this.value = value;
		this.showValue = true;
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
		if (drawButton) {
			graphics.setColor(Color.GREEN);
			graphics.fillRect(this.positionPoint.x, this.positionPoint.y, this.boundPoint.x, this.boundPoint.y);
			if (showValue) {
				graphics.setColor(Color.RED);
				graphics.setFont(new Font("Courier New", Font.BOLD, 50));
				graphics.drawString(value, positionPoint.x + 10, positionPoint.y + this.boundPoint.y - 10);
			}
		}
	}
	
	public void showValue(boolean show){
		showValue = show;
		
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

}
