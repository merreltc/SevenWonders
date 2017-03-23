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
	}
}
