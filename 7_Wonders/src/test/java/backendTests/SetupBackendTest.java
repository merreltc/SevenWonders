package backendTests;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.easymock.EasyMock;
import org.junit.Test;

import backend.SetUpHandler;
import dataStructures.GameBoard;

public class SetupBackendTest {

	@Test
	public void testValidPlayerNum() {
		assertTrue(SetUpHandler.validatePlayerNum(3));
		assertTrue(SetUpHandler.validatePlayerNum(7));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidPlayerNum2() {
		SetUpHandler.validatePlayerNum(2);
		fail();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidPlayerNum8() {
		SetUpHandler.validatePlayerNum(8);
		fail();
	}
	
	@Test
	public void testInvalidPlayerNum2ErrorMessage() {	
		try{
			SetUpHandler.validatePlayerNum(2);
		} catch (IllegalArgumentException error){
			String message = "Cannot play with 2 players";
			assertEquals(message, error.getMessage());
		}
	}
	
	@Test
	public void testInvalidPlayerNum8ErrorMessage() {
		try{
			SetUpHandler.validatePlayerNum(8);
		} catch (IllegalArgumentException error){
			String message = "Cannot play with 8 players";
			assertEquals(message, error.getMessage());
		}
	}
	
	@Test
	public void testSetUpReturnsPlayerNames() {
		ArrayList<String> playerNames = new ArrayList<String>();
		SetUpHandler.setUp(playerNames);
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
