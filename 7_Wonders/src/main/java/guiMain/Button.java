package guiMain;

import java.awt.Point;
import java.awt.geom.Point2D;

public class Button {
	private Point point;

	public Button(int width, int height){
		point = new Point(width, height);
	}
	
	public Point2D getBounds(){
		return point;
	}
	
}
