package dataStructureTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import dataStructures.GameBoard;
import dataStructures.GameBoard.Direction;
import dataStructures.Player;

public class GameBoardTests {

	@Test
	public void testGameBoardGMinPlayers() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player());
		players.add(new Player());
		players.add(new Player());
		GameBoard board = new GameBoard(players);

		
		assertEquals(3, board.getNumPlayers());
	}

	@Test
	public void testGameBoardMaxPlayers() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player());
		players.add(new Player());
		players.add(new Player());
		players.add(new Player());
		players.add(new Player());
		players.add(new Player());
		players.add(new Player());

		GameBoard board = new GameBoard(players);

		assertEquals(7, board.getNumPlayers());
	}

	@Test
	public void testGameBoardGetPlayers() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player());
		players.add(new Player());
		players.add(new Player());

		GameBoard board = new GameBoard(players);

		assertEquals(players, board.getPlayers());
	}
	
	@Test
	public void testGameBoardGetPlayerByIndex() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine"));
		players.add(new Player("Captain America"));
		players.add(new Player("Black Widow"));

		GameBoard board = new GameBoard(players);

		for(int i = 0; i < players.size(); i++) {
			assertEquals(players.get(i), board.getPlayer(i));
		}
	}
	
	@Test
	public void testGameBoardGetPlayerCoinTotalOnStart() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine"));
		players.add(new Player("Captain America"));
		players.add(new Player("Black Widow"));

		GameBoard board = new GameBoard(players);

		for(int i = 0; i < players.size(); i++) {
			assertEquals(3, board.getPlayerCoinTotal(i));
		}
	}
	
	@Test
	public void testGetPlayerPositionsOnStartMin() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine"));
		players.add(new Player("Captain America"));
		players.add(new Player("Black Widow"));

		GameBoard board = new GameBoard(players);

		assertEquals(players.get(0), board.getCurrentPlayer());
		assertEquals(players.get(1), board.getNextPlayer());
		assertEquals(players.get(2), board.getPreviousPlayer());
	}

	
	@Test
	public void testGetPlayerPositionsOnStartMax() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine"));
		players.add(new Player("Captain America"));
		players.add(new Player("Black Widow"));
		players.add(new Player("Hulk"));
		players.add(new Player("Iron Man"));
		players.add(new Player("Spider Man"));
		players.add(new Player("Thor"));

		GameBoard board = new GameBoard(players);

		assertEquals(players.get(0), board.getCurrentPlayer());
		assertEquals(players.get(1), board.getNextPlayer());
		assertEquals(players.get(6), board.getPreviousPlayer());
	}
	
	@Test
	public void testGetPlayerPositionsOnRotateClockwiseMin() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine"));
		players.add(new Player("Captain America"));
		players.add(new Player("Black Widow"));

		GameBoard board = new GameBoard(players);
		board.rotateClockwise();

		assertEquals(players.get(1), board.getCurrentPlayer());
		assertEquals(players.get(2), board.getNextPlayer());
		assertEquals(players.get(0), board.getPreviousPlayer());
	}
	
	@Test
	public void testGetPlayerPositionsOnRotateClockwiseMax() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine"));
		players.add(new Player("Captain America"));
		players.add(new Player("Black Widow"));
		players.add(new Player("Hulk"));
		players.add(new Player("Iron Man"));
		players.add(new Player("Spider Man"));
		players.add(new Player("Thor"));

		GameBoard board = new GameBoard(players);
		board.rotateClockwise();

		assertEquals(players.get(1), board.getCurrentPlayer());
		assertEquals(players.get(2), board.getNextPlayer());
		assertEquals(players.get(0), board.getPreviousPlayer());
	}
	
	@Test
	public void testGetPlayerPositionsOnRotateClockwiseTwice() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine"));
		players.add(new Player("Captain America"));
		players.add(new Player("Black Widow"));
		players.add(new Player("Hulk"));
		players.add(new Player("Iron Man"));

		GameBoard board = new GameBoard(players);
		board.rotateClockwise();
		board.rotateClockwise();

		assertEquals(players.get(2), board.getCurrentPlayer());
		assertEquals(players.get(3), board.getNextPlayer());
		assertEquals(players.get(1), board.getPreviousPlayer());
	}
	
	@Test
	public void testGetPlayerPositionsOnRotateClockwiseMany() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine"));
		players.add(new Player("Captain America"));
		players.add(new Player("Black Widow"));
		players.add(new Player("Hulk"));
		players.add(new Player("Iron Man"));

		GameBoard board = new GameBoard(players);

		for(int i = 0; i < 10; i++){
			board.rotateClockwise();
		}
		
		assertEquals(players.get(0), board.getCurrentPlayer());
		assertEquals(players.get(1), board.getNextPlayer());
		assertEquals(players.get(4), board.getPreviousPlayer());
	}
	
	@Test
	public void testGetPlayerPositionsOnRotateCounterClockwiseMin() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine"));
		players.add(new Player("Captain America"));
		players.add(new Player("Black Widow"));

		GameBoard board = new GameBoard(players);
		board.rotateCounterClockwise();

		assertEquals(players.get(2), board.getCurrentPlayer());
		assertEquals(players.get(1), board.getNextPlayer());
		assertEquals(players.get(0), board.getPreviousPlayer());
	}
	
	@Test
	public void testGetPlayerPositionsOnRotateCounterClockwiseMax() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine"));
		players.add(new Player("Captain America"));
		players.add(new Player("Black Widow"));
		players.add(new Player("Hulk"));
		players.add(new Player("Iron Man"));
		players.add(new Player("Spider Man"));
		players.add(new Player("Thor"));
		
		GameBoard board = new GameBoard(players);
		board.rotateCounterClockwise();

		assertEquals(players.get(6), board.getCurrentPlayer());
		assertEquals(players.get(5), board.getNextPlayer());
		assertEquals(players.get(0), board.getPreviousPlayer());
	}
	
	@Test
	public void testGetPlayerPositionsOnRotateCounterClockwiseTwice() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine"));
		players.add(new Player("Captain America"));
		players.add(new Player("Black Widow"));
		players.add(new Player("Hulk"));
		players.add(new Player("Iron Man"));

		GameBoard board = new GameBoard(players);
		board.rotateCounterClockwise();
		board.rotateCounterClockwise();

		assertEquals(players.get(3), board.getCurrentPlayer());
		assertEquals(players.get(2), board.getNextPlayer());
		assertEquals(players.get(4), board.getPreviousPlayer());
	}
	
	@Test
	public void testGetPlayerPositionsOnRotateCounterClockwiseMany() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine"));
		players.add(new Player("Captain America"));
		players.add(new Player("Black Widow"));
		players.add(new Player("Hulk"));
		players.add(new Player("Iron Man"));

		GameBoard board = new GameBoard(players);

		for(int i = 0; i < 10; i++){
			board.rotateCounterClockwise();
		}
		
		assertEquals(players.get(0), board.getCurrentPlayer());
		assertEquals(players.get(4), board.getNextPlayer());
		assertEquals(players.get(2), board.getPreviousPlayer());
	}
	
	@Test
	public void testChangeRotateDirectionAndResetPositionsMin() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine"));
		players.add(new Player("Captain America"));
		players.add(new Player("Black Widow"));

		GameBoard board = new GameBoard(players);
		board.changeRotateDirectionAndResetPositions(Direction.CLOCKWISE);

		assertEquals(players.get(0), board.getCurrentPlayer());
		assertEquals(players.get(1), board.getNextPlayer());
		assertEquals(players.get(2), board.getPreviousPlayer());
	}
}
