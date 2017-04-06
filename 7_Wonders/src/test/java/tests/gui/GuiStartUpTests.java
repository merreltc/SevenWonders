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
	}
	
	
	@Test
	public void testMakeFrame(){
		Assert.assertNotNull(menu.getFrame());
	}
	
	@Test
	public void testSize(){
		Assert.assertEquals(1000, menu.getFrame().getHeight());
		Assert.assertEquals(1900, menu.getFrame().getWidth());
	}
	
	@Test
	public void testVisible(){
		Assert.assertTrue(menu.getFrame().isVisible());
	}
	
	@Test
	public void testTitle(){
		Assert.assertEquals("Seven Wonders", menu.getFrame().getTitle());
	}
	
	@Test
	public void testTimer(){
		Timer time = menu.getTimer();
		Assert.assertEquals(20, time.getDelay());
	}
	
	@Test
	public void testAddGuiMainMenuToFrame(){
		Assert.assertEquals(1, menu.getFrame().getComponentCount());
	}
	
	@Test
	public void testAddKeyListener() {
		Assert.assertEquals(1, menu.getFrame().getKeyListeners().length);
	}
	
	@Test
	public void testAddMouseListener(){
		Assert.assertEquals(1, menu.getFrame().getMouseListeners().length);
	}
	
	@Test
	public void testTimeIsRunning(){
		Timer time = menu.getTimer();
		Assert.assertTrue(time.isRunning());
	}
}
