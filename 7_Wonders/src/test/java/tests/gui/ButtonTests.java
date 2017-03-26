package tests.gui;

import java.awt.Point;
import java.awt.geom.Point2D;

import org.junit.Test;
import guiMain.Button;
import junit.framework.Assert;

public class ButtonTests {

	@Test
	public void testButtonCreation() {
		Button button = new Button(new Point(0, 0), new Point(100, 100), "3");
		Point2D testpoint = new Point(100, 100);
		Assert.assertEquals(testpoint, button.getBounds());
	}

	@Test
	public void testButtonCreation2() {
		Button button = new Button(new Point(0, 0), new Point(50, 50), "3");
		Point2D testpoint = new Point(50, 50);
		Assert.assertEquals(testpoint, button.getBounds());
	}

	@Test
	public void testButtonCreationPosition() {
		Button button = new Button(new Point(250, 250), new Point(100, 100), "3");
		Point2D testpoint = new Point(250, 250);
		Assert.assertEquals(testpoint, button.getPosition());
	}

	@Test
	public void testButtonCreationPosition2() {
		Button button = new Button(new Point(20, 20), new Point(100, 100), "3");
		Point2D testpoint = new Point(20, 20);
		Assert.assertEquals(testpoint, button.getPosition());
	}
	
	@Test
	public void testButtonValue1() {
		Button button = new Button(new Point(20, 20), new Point(100, 100), "3");
		Assert.assertEquals("3", button.getValue());
	}
	
	@Test
	public void testButtonValue2() {
		Button button = new Button(new Point(20, 20), new Point(100, 100), "4");
		Assert.assertEquals("4", button.getValue());
	}
	
	@Test
	public void testButtonCollisionTopBoundTrue(){
		Button button = new Button(new Point(20, 20), new Point(100, 100), "4");
		Assert.assertTrue(button.checkButtonClicked(new Point(40,20)));
	}
	
	@Test
	public void testButtonCollisionTopBoundFalse(){
		Button button = new Button(new Point(20, 20), new Point(100, 100), "4");
		Assert.assertFalse(button.checkButtonClicked(new Point(40,19)));
	}
	
	@Test
	public void testButtonCollisionBottomBoundTrue(){
		Button button = new Button(new Point(20, 20), new Point(100, 100), "4");
		Assert.assertTrue(button.checkButtonClicked(new Point(40,120)));
	}
	
	@Test
	public void testButtonCollisionBottomBoundFalse(){
		Button button = new Button(new Point(20, 20), new Point(100, 100), "4");
		Assert.assertFalse(button.checkButtonClicked(new Point(40,121)));
	}
	
	@Test
	public void testButtonCollisionLeftBoundTrue(){
		Button button = new Button(new Point(20, 20), new Point(100, 100), "4");
		Assert.assertTrue(button.checkButtonClicked(new Point(20,60)));
	}
	
	@Test
	public void testButtonCollisionLeftBoundFalse(){
		Button button = new Button(new Point(20, 20), new Point(100, 100), "4");
		Assert.assertFalse(button.checkButtonClicked(new Point(19,60)));
	}
	
	@Test
	public void testButtonCollisionRightBoundTrue(){
		Button button = new Button(new Point(20, 20), new Point(100, 100), "4");
		Assert.assertTrue(button.checkButtonClicked(new Point(120,60)));
	}
	
	@Test
	public void testButtonCollisionRightBoundFalse(){
		Button button = new Button(new Point(20, 20), new Point(100, 100), "4");
		Assert.assertFalse(button.checkButtonClicked(new Point(121,60)));
	}
}
