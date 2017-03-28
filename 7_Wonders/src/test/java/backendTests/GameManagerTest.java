package backendTests;

import static org.junit.Assert.*;

import org.junit.Test;

import backend.GameManager;

public class GameManagerTest {

	@Test
	public void testGameManagerGetMinPlayers() {
		GameManager manager = new GameManager(3);
		
		assertEquals(3, manager.getPlayerNum());
	}
	
	@Test
	public void testGameManagerGetMaxPlayers() {
		GameManager manager = new GameManager(7);
		
		assertEquals(7, manager.getPlayerNum());
	}

}
