package guiMain;

import java.awt.Point;
import java.awt.geom.Point2D;

public class Button {
	private Point boundPoint;
	private Point positionPoint;

	public Button(Point position, Point bounds, String value) {
		boundPoint = bounds;
		positionPoint = position;
	}

	public Point2D getBounds() {
		return boundPoint;
	}

	public Point2D getPosition() {
		return positionPoint;
	}
}
