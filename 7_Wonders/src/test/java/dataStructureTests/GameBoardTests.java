package dataStructureTests;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameBoardTests {

	@Test
	public void testDefaultGameBoard() {
		GameBoard board = new GameBoard();
		
		assertEquals(0, board.getNumPlayers());
	}

}
