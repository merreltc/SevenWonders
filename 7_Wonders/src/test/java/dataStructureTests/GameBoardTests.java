package dataStructureTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import dataStructures.GameBoard;
import dataStructures.Player;

public class GameBoardTests {

	@Test
	public void testDefaultGameBoard() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player());
		players.add(new Player());
		players.add(new Player());

		GameBoard board = new GameBoard();

		assertEquals(3, board.getPlayers().size());
		assertEquals(3, board.getNumPlayers());
	}

	@Test
	public void testGameBoardMinPlayers() {
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
}
