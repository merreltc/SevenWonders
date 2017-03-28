package backendTests;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameManagerTest {

	@Test
	public void testGameManagerGetMinPlayers() {
		GameManager manager = new GameManager(3);
		
		assertEquals(3, manager.getPlayerNum());
	}

}
