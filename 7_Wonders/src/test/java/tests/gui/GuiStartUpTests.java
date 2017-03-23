package tests.gui;

import org.junit.Assert;
import org.junit.Test;
import guiMain.*;

public class GuiStartUpTests {

	@Test
	public void testMakeFrame(){
		GuiMainMenu menu = new GuiMainMenu();
		menu.Start();
		Assert.assertNotNull(menu.GetFrame());
	}
	
	@Test
	public void testSize(){
		GuiMainMenu menu = new GuiMainMenu();
		menu.Start();
		Assert.assertEquals(1000, menu.GetFrame().getHeight());
		Assert.assertEquals(1000, menu.GetFrame().getWidth());
	}
	
	@Test
	public void testVisible(){
		GuiMainMenu menu = new GuiMainMenu();
		menu.Start();
		Assert.assertTrue(menu.GetFrame().isVisible());
	}
	
	@Test
	public void testTitle(){
		GuiMainMenu menu = new GuiMainMenu();
		menu.Start();
		Assert.assertEquals("Seven Wonders", menu.GetFrame().getTitle());
	}
}
