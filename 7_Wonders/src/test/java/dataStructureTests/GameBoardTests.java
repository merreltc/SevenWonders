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

		for (int i = 0; i < players.size(); i++) {
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

		for (int i = 0; i < players.size(); i++) {
			assertEquals(3, board.getPlayerCoinTotal(i));
		}
	}

	@Test
	public void testChangeRotateDirectionAndResetPositionsMin() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine"));
		players.add(new Player("Captain America"));
		players.add(new Player("Black Widow"));

		GameBoard board = new GameBoard(players);
		
		board.changeRotateDirectionAndResetPositions(Direction.CLOCKWISE);
		comparePlayerPositions(players, board, 0, 1, 2);

		board.changeRotateDirectionAndResetPositions(Direction.COUNTERCLOCKWISE);
		comparePlayerPositions(players, board, 0, 2, 1);
	}

	@Test
	public void testChangeRotateDirectionAndResetPositionsMax() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine"));
		players.add(new Player("Captain America"));
		players.add(new Player("Black Widow"));
		players.add(new Player("Hulk"));
		players.add(new Player("Iron Man"));
		players.add(new Player("Spider Man"));
		players.add(new Player("Thor"));

		GameBoard board = new GameBoard(players);
		
		board.changeRotateDirectionAndResetPositions(Direction.CLOCKWISE);
		comparePlayerPositions(players, board, 0, 1, 6);

		board.changeRotateDirectionAndResetPositions(Direction.COUNTERCLOCKWISE);
		comparePlayerPositions(players, board, 0, 6, 1);
	}

	@Test
	public void testChangeRotateDirectionAndResetPositionsAfterOppositeRotate() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine"));
		players.add(new Player("Captain America"));
		players.add(new Player("Black Widow"));
		players.add(new Player("Hulk"));
		players.add(new Player("Iron Man"));

		GameBoard board = new GameBoard(players);
		board.rotateClockwise();
		board.changeRotateDirectionAndResetPositions(Direction.COUNTERCLOCKWISE);
		
		comparePlayerPositions(players, board, 0, 4, 1);

		board.rotateCounterClockwise();
		board.changeRotateDirectionAndResetPositions(Direction.CLOCKWISE);
		
		comparePlayerPositions(players, board, 0, 1, 4);
	}

	@Test
	public void testGetPlayerPositionsOnStartMin() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine"));
		players.add(new Player("Captain America"));
		players.add(new Player("Black Widow"));

		GameBoard board = new GameBoard(players);
		
		comparePlayerPositions(players, board, 0, 1, 2);
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
		
		comparePlayerPositions(players, board, 0, 1, 6);
	}

	@Test
	public void testGetPlayerPositionsOnRotateClockwiseMin() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine"));
		players.add(new Player("Captain America"));
		players.add(new Player("Black Widow"));

		GameBoard board = new GameBoard(players);
		board.rotateClockwise();
		
		comparePlayerPositions(players, board, 1, 2, 0);
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
		
		comparePlayerPositions(players, board, 1, 2, 0);
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

		comparePlayerPositions(players, board, 2, 3, 1);
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

		for (int i = 0; i < 10; i++) {
			board.rotateClockwise();
		}
		
		comparePlayerPositions(players, board, 0, 1, 4);
	}

	@Test
	public void testGetPlayerPositionsOnRotateCounterClockwiseMin() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine"));
		players.add(new Player("Captain America"));
		players.add(new Player("Black Widow"));

		GameBoard board = new GameBoard(players);
		board.changeRotateDirectionAndResetPositions(Direction.COUNTERCLOCKWISE);
		board.rotateCounterClockwise();
		
		comparePlayerPositions(players, board, 2, 1, 0);
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
		board.changeRotateDirectionAndResetPositions(Direction.COUNTERCLOCKWISE);
		board.rotateCounterClockwise();

		comparePlayerPositions(players, board, 6, 5, 0);
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
		board.changeRotateDirectionAndResetPositions(Direction.COUNTERCLOCKWISE);
		board.rotateCounterClockwise();
		board.rotateCounterClockwise();

		comparePlayerPositions(players, board, 3, 2, 4);
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
		board.changeRotateDirectionAndResetPositions(Direction.COUNTERCLOCKWISE);

		for (int i = 0; i < 10; i++) {
			board.rotateCounterClockwise();
		}

		comparePlayerPositions(players, board, 0, 4, 1);
	}

	public void comparePlayerPositions(ArrayList<Player> players, GameBoard board, int currIndex, int nextIndex,
			int previousIndex) {
		assertEquals(players.get(currIndex), board.getCurrentPlayer());
		assertEquals(players.get(nextIndex), board.getNextPlayer());
		assertEquals(players.get(previousIndex), board.getPreviousPlayer());
	}
	
	@Test
	public void testSetCurrentPlayerValid() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine"));
		players.add(new Player("Captain America"));
		players.add(new Player("Black Widow"));
		players.add(new Player("Hulk"));
		players.add(new Player("Iron Man"));

		GameBoard board = new GameBoard(players);
		board.setCurrentPlayer(0);
		
		assertEquals(players.get(0), board.getCurrentPlayer());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetCurrentPlayerInvalidNeg1() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine"));
		players.add(new Player("Captain America"));
		players.add(new Player("Black Widow"));
		players.add(new Player("Hulk"));
		players.add(new Player("Iron Man"));

		GameBoard board = new GameBoard(players);
		board.setCurrentPlayer(-1);
		fail();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetCurrentPlayerInvalidMaxPlus1() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine"));
		players.add(new Player("Captain America"));
		players.add(new Player("Black Widow"));
		players.add(new Player("Hulk"));
		players.add(new Player("Iron Man"));

		GameBoard board = new GameBoard(players);
		board.setCurrentPlayer(5);
		fail();
	}
	
	@Test
	public void testSetNextPlayerValid() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine"));
		players.add(new Player("Captain America"));
		players.add(new Player("Black Widow"));
		players.add(new Player("Hulk"));
		players.add(new Player("Iron Man"));

		GameBoard board = new GameBoard(players);
		board.setNextPlayer(0);
		
		assertEquals(players.get(0), board.getNextPlayer());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetNextPlayerInvalidNeg1() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine"));
		players.add(new Player("Captain America"));
		players.add(new Player("Black Widow"));
		players.add(new Player("Hulk"));
		players.add(new Player("Iron Man"));

		GameBoard board = new GameBoard(players);
		board.setNextPlayer(-1);
		fail();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetNextPlayerInvalidMaxPlus1() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine"));
		players.add(new Player("Captain America"));
		players.add(new Player("Black Widow"));
		players.add(new Player("Hulk"));
		players.add(new Player("Iron Man"));

		GameBoard board = new GameBoard(players);
		board.setNextPlayer(5);
		fail();
	}
}
