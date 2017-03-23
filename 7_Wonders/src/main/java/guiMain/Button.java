package guiMain;

import java.awt.Point;
import java.awt.geom.Point2D;

public class Button {
	private Point point;

	public Button(int x, int y, int width, int height){
		point = new Point(width, height);
	}
	
	public Point2D getBounds(){
		return point;
	}
	
	public Point2D getPosition() {
		return new Point(250, 250);
	}
	
}
