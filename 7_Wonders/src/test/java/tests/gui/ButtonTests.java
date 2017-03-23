package tests.gui;

import java.awt.Point;
import java.awt.geom.Point2D;

import org.junit.Test;
import guiMain.Button;
import junit.framework.Assert;

public class ButtonTests {

	@Test
	public void testButtonCreation(){
		Button button = new Button(100, 100);
		Point2D testpoint = new Point(100,100);
		Assert.assertEquals(testpoint, button.getBounds());
	}
}
