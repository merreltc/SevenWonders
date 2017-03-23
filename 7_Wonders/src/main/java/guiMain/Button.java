package guiMain;

import java.awt.Point;
import java.awt.geom.Point2D;

public class Button {
	private Point boundPoint;
	private Point positionPoint;

	public Button(int x, int y, int width, int height) {
		boundPoint = new Point(width, height);
		positionPoint = new Point(x, y);
	}

	public Point2D getBounds() {
		return boundPoint;
	}

	public Point2D getPosition() {
		return positionPoint;
	}

}
