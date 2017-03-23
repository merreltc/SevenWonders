package tests.gui;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import guiMain.*;

public class GuiStartUpTests {

	private GuiMainMenu menu;
	
	@Before
	public void SetUp(){
		menu = new GuiMainMenu();
		menu.Start();
	}
	
	
	@Test
	public void testMakeFrame(){
		Assert.assertNotNull(menu.GetFrame());
	}
	
	@Test
	public void testSize(){
		Assert.assertEquals(1000, menu.GetFrame().getHeight());
		Assert.assertEquals(1000, menu.GetFrame().getWidth());
	}
	
	@Test
	public void testVisible(){
		Assert.assertTrue(menu.GetFrame().isVisible());
	}
	
	@Test
	public void testTitle(){
		Assert.assertEquals("Seven Wonders", menu.GetFrame().getTitle());
	}
}
