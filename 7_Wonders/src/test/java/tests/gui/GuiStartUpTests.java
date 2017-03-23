package tests.gui;

import javax.swing.Timer;

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
	
	@Test
	public void testTimer(){
		Timer time = menu.GetTimer();
		Assert.assertEquals(20, time.getDelay());
	}
	
	@Test
	public void testAddGuiMainMenuToFrame(){
		Assert.assertEquals(1, menu.GetFrame().getComponentCount());
	}
	
	@Test
	public void testAddKeyListener() {
		Assert.assertEquals(1, menu.GetFrame().getKeyListeners().length);
	}
	
	@Test
	public void testAddMouseListener(){
		Assert.assertEquals(1, menu.GetFrame().getMouseListeners().length);
	}
}
