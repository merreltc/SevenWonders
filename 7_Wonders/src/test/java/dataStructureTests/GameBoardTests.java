package dataStructureTests;

import static org.junit.Assert.*;

import org.junit.Test;

import dataStructures.GameBoard;

public class GameBoardTests {

	@Test
	public void testDefaultGameBoard() {
		GameBoard board = new GameBoard();
		
		assertEquals(0, board.getNumPlayers());
	}

}
