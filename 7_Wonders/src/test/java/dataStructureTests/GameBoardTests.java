package dataStructureTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import dataStructures.GameBoard;
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
	public void testGameGetCurrentPlayerOnStart() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine"));
		players.add(new Player("Captain America"));
		players.add(new Player("Black Widow"));

		GameBoard board = new GameBoard(players);

		assertEquals(players.get(0), board.getCurrentPlayer());
	}
	
	@Test
	public void testGameGetNextPlayerOnStart() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine"));
		players.add(new Player("Captain America"));
		players.add(new Player("Black Widow"));

		GameBoard board = new GameBoard(players);

		assertEquals(players.get(1), board.getNextPlayer());
	}
	
	@Test
	public void testGameGetPreviousPlayerOnStart() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine"));
		players.add(new Player("Captain America"));
		players.add(new Player("Black Widow"));

		GameBoard board = new GameBoard(players);

		assertEquals(players.get(2), board.getPreviousPlayer());
	}
}
