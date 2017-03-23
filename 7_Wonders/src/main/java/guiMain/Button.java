package guiMain;

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
	
	public boolean checkCollision(Point point){
		return true;
	}
}
