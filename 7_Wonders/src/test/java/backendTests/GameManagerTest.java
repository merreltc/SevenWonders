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

		assertEquals(3, manager.getNumPlayers());
	}

	@Test
	public void testGameManagerGetMaxPlayers() {
		GameManager manager = new GameManager(7);

		assertEquals(7, manager.getNumPlayers());
	}

	@Test
	public void testDefaultSetUp() {
		GameManager manager = EasyMock.createMockBuilder(GameManager.class).withConstructor(3).createMock();

		manager.setUpGame(3);

		EasyMock.replay(manager);

		EasyMock.verify(manager);
	}
	
	@Test
	public void testDefaultSetUpGetGameBoard() {
		GameManager manager = EasyMock.createMockBuilder(GameManager.class).withConstructor(3).addMockedMethod("getGameBoard").createMock();
		GameBoard board = EasyMock.strictMock(GameBoard.class);
		
		EasyMock.expect(manager.getGameBoard()).andReturn(board);
		
		EasyMock.replay(manager, board);
		
		manager.getGameBoard();
		
		EasyMock.verify(manager, board);
	}
	
	@Test
	public void testSetUpGameBoardMinPlayers() {
		GameManager manager = new GameManager(3);
		
		assertEquals(3, manager.getNumPlayers());
	}
	
	@Test
	public void testSetUpGameBoardMaxPlayers() {
		GameManager manager = new GameManager(7);
		
		assertEquals(7, manager.getNumPlayers());
	}
	
	@Test
	public void testGetPlayers() {
		GameManager manager = new GameManager(7);
		
		assertEquals(3, manager.getPlayers().size());
	}
}
