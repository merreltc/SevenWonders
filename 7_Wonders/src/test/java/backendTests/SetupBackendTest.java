package backendTests;
import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Test;

import backend.SetUpHandler;
import dataStructures.GameBoard;

public class SetupBackendTest {

	@Test
	public void testValidPlayerNum() {
		
		SetUpHandler.setPlayerNum(3);
		assertEquals(3, SetUpHandler.getPlayerNum());
		SetUpHandler.setPlayerNum(7);
		assertEquals(7, SetUpHandler.getPlayerNum());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidPlayerNum2() {
		SetUpHandler.setPlayerNum(2);
		fail();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidPlayerNum8() {
		SetUpHandler.setPlayerNum(8);
		fail();
	}
	
	@Test
	public void testInvalidPlayerNum2ErrorMessage() {	
		try{
			SetUpHandler.setPlayerNum(2);
		} catch (IllegalArgumentException error){
			String message = "Cannot play with 2 players";
			assertEquals(message, error.getMessage());
		}
	}
	
	@Test
	public void testInvalidPlayerNum8ErrorMessage() {
		try{
			SetUpHandler.setPlayerNum(8);
		} catch (IllegalArgumentException error){
			String message = "Cannot play with 8 players";
			assertEquals(message, error.getMessage());
		}
	}
	
	@Test
	public void testCreateGameBoardDefault() {
		GameBoard board = EasyMock.createStrictMock(GameBoard.class);
		
		SetUpHandler.setPlayerNum(3);
		//EasyMock.expect(SetUpHandler.createDefaultGameBoard()).andReturn(board);
		
		EasyMock.replay(board);
		
		SetUpHandler.setUp(3);
		
		EasyMock.verify(board);
	}
	
	@Test
	public void testCreateGameBoardMinPlayers() {
		GameBoard board = SetUpHandler.setUp(3);
		
		assertEquals(3, board.getNumPlayers());
	}
	
	@Test
	public void testCreateGameBoardMaxPlayers() {
		GameBoard board = SetUpHandler.setUp(7);
		
		assertEquals(7, board.getNumPlayers());
	}
}
