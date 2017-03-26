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

	public Button(Point position, Point bounds, String value) {
		this.boundPoint = bounds;
		this.positionPoint = position;
		this.value = value;
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
	
	public boolean checkButtonClicked(Point point){
		if (positionPoint.x <= point.x && positionPoint.x + boundPoint.x >= point.x){
			if (positionPoint.y <= point.y && positionPoint.y + boundPoint.y >= point.y){
				return true;
			}
		}
		return false;
	}
	
	public void draw(Graphics graphics){
		graphics.setColor(Color.green);
		graphics.fillRect(this.positionPoint.x, this.positionPoint.y, this.boundPoint.x, this.boundPoint.y);
		graphics.setColor(Color.RED);
		graphics.setFont(new Font("Courier New", Font.BOLD, 50));
		graphics.drawString(value, positionPoint.x + 10, positionPoint.y + this.boundPoint.y - 10);
	}
}
