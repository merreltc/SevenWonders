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
	
}
