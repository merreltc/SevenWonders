package dataStructureTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import dataStructures.GameBoard;
import dataStructures.Player;

public class GameBoardTests {

	@Test
	public void testDefaultGameBoard() {
		GameBoard board = new GameBoard();
		
		assertEquals(0, board.getNumPlayers());
	}
	
	@Test
	public void testGameBoardMinPlayers(){
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player());
		players.add(new Player());
		players.add(new Player());
		
		GameBoard board = new GameBoard(players);
		
		assertEquals(3, board.getNumPlayers());
	}

}
