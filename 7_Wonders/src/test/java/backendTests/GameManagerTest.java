package backendTests;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Test;

import backend.GameManager;
import dataStructures.GameBoard;

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

	@Test
	public void testDefaultSetUp() {
		GameManager manager = EasyMock.createMockBuilder(GameManager.class).withConstructor(3).createMock();

		manager.setUpGame();

		EasyMock.replay(manager);

		EasyMock.verify(manager);
	}
	
	@Test
	public void testDefaultSetUpGetGameBoard() {
		GameManager manager = new GameManager(3);
		
		GameBoard board = manager.getGameBoard();
		
		assertEquals(new GameBoard(), board);
		
	}
}
