package backendTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import backend.RotateHandler;
import dataStructures.GameBoard;
import dataStructures.Player;
import dataStructures.GameBoard.Direction;

public class RotateHandlerTest {

	@Test
	public void testChangeRotateDirectionAndResetPositionsMin() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine"));
		players.add(new Player("Captain America"));
		players.add(new Player("Black Widow"));

		GameBoard board = new GameBoard(players);
		RotateHandler rotateHandler = new RotateHandler(board);
		
		rotateHandler.changeRotateDirectionAndResetPositions(Direction.CLOCKWISE);
		comparePlayerPositions(players, board, 0, 1, 2);

		rotateHandler.changeRotateDirectionAndResetPositions(Direction.COUNTERCLOCKWISE);
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
		RotateHandler rotateHandler = new RotateHandler(board);
		
		rotateHandler.changeRotateDirectionAndResetPositions(Direction.CLOCKWISE);
		comparePlayerPositions(players, board, 0, 1, 6);

		rotateHandler.changeRotateDirectionAndResetPositions(Direction.COUNTERCLOCKWISE);
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
		RotateHandler rotateHandler = new RotateHandler(board);
		
		rotateHandler.rotateClockwise();
		rotateHandler.changeRotateDirectionAndResetPositions(Direction.COUNTERCLOCKWISE);
		
		comparePlayerPositions(players, board, 0, 4, 1);

		rotateHandler.rotateCounterClockwise();
		rotateHandler.changeRotateDirectionAndResetPositions(Direction.CLOCKWISE);
		
		comparePlayerPositions(players, board, 0, 1, 4);
	}
	
	@Test
	public void testGetPlayerPositionsOnRotateClockwiseMin() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine"));
		players.add(new Player("Captain America"));
		players.add(new Player("Black Widow"));

		GameBoard board = new GameBoard(players);
		RotateHandler rotateHandler = new RotateHandler(board);
		
		rotateHandler.rotateClockwise();
		
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
		RotateHandler rotateHandler = new RotateHandler(board);
		rotateHandler.rotateClockwise();
		
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
		RotateHandler rotateHandler = new RotateHandler(board);
		rotateHandler.rotateClockwise();
		rotateHandler.rotateClockwise();

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
		RotateHandler rotateHandler = new RotateHandler(board);

		for (int i = 0; i < 10; i++) {
			rotateHandler.rotateClockwise();
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
		RotateHandler rotateHandler = new RotateHandler(board);
		
		rotateHandler.changeRotateDirectionAndResetPositions(Direction.COUNTERCLOCKWISE);
		rotateHandler.rotateCounterClockwise();
		
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
		RotateHandler rotateHandler = new RotateHandler(board);
		
		rotateHandler.changeRotateDirectionAndResetPositions(Direction.COUNTERCLOCKWISE);
		rotateHandler.rotateCounterClockwise();

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
		RotateHandler rotateHandler = new RotateHandler(board);
		
		rotateHandler.changeRotateDirectionAndResetPositions(Direction.COUNTERCLOCKWISE);
		rotateHandler.rotateCounterClockwise();
		rotateHandler.rotateCounterClockwise();

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
		RotateHandler rotateHandler = new RotateHandler(board);
		
		rotateHandler.changeRotateDirectionAndResetPositions(Direction.COUNTERCLOCKWISE);

		for (int i = 0; i < 10; i++) {
			rotateHandler.rotateCounterClockwise();
		}

		comparePlayerPositions(players, board, 0, 4, 1);
	}

	public void comparePlayerPositions(ArrayList<Player> players, GameBoard board, int currIndex, int nextIndex,
			int previousIndex) {
		assertEquals(players.get(currIndex), board.getCurrentPlayer());
		assertEquals(players.get(nextIndex), board.getNextPlayer());
		assertEquals(players.get(previousIndex), board.getPreviousPlayer());
	}
}
