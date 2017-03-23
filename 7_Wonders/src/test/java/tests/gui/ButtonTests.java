package tests.gui;

import java.awt.Point;
import java.awt.geom.Point2D;

import org.junit.Test;
import guiMain.Button;
import junit.framework.Assert;

public class ButtonTests {

	@Test
	public void testButtonCreation(){
		Button button = new Button(0, 0, 100, 100);
		Point2D testpoint = new Point(100,100);
		Assert.assertEquals(testpoint, button.getBounds());
	}
	
	@Test
	public void testButtonCreation2() {
		Button button = new Button(0, 0, 50, 50);
		Point2D testpoint = new Point(50,50);
		Assert.assertEquals(testpoint, button.getBounds());
	}
	
	@Test
	public void testButtonCreationPosition(){
		Button button = new Button(250, 250, 100, 100);
		button.getPosition();
	}
}
