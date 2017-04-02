package backendTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.easymock.EasyMock;
import org.junit.Test;

import backend.GameManager;
import dataStructures.GameBoard;
import dataStructures.Player;
import dataStructures.GameBoard.Direction;

public class GameManagerTest {

	@Test
	public void testSetUpGameBoardMinPlayers() {
		ArrayList<String> playerNames = new ArrayList<String>();
		playerNames.add("Wolverine");
		playerNames.add("Captain America");
		playerNames.add("Black Widow");
		GameManager manager = new GameManager(playerNames);

		assertEquals(3, manager.getNumPlayers());
	}

	@Test
	public void testSetUpGameBoardMaxPlayers() {
		ArrayList<String> playerNames = new ArrayList<String>();
		playerNames.add("Wolverine");
		playerNames.add("Captain America");
		playerNames.add("Black Widow");
		playerNames.add("Hulk");
		playerNames.add("Iron Man");
		playerNames.add("Spider Man");
		playerNames.add("Thor");
		GameManager manager = new GameManager(playerNames);

		assertEquals(7, manager.getNumPlayers());
	}
	
	@Test
	public void testTrade(){
		ArrayList<String> playerNames = new ArrayList<String>();
		playerNames.add("Wolverine");
		playerNames.add("Captain America");
		playerNames.add("Black Widow");
		GameManager manager = new GameManager(playerNames);
		
		manager.trade(manager.getPlayer(0), manager.getPlayer(1), 3);
		
		assertEquals(0, manager.getPlayerCoinTotal(0));
		assertEquals(6, manager.getPlayerCoinTotal(1));
	}
	
	@Test
	public void testGetCurrentPositionsOnStartMin() {
		ArrayList<String> playerNames = new ArrayList<String>();
		playerNames.add("Wolverine");
		playerNames.add("Captain America");
		playerNames.add("Black Widow");
		GameManager manager = new GameManager(playerNames);
		
		comparePlayerPositions(manager.getPlayers(), manager, 0, 1, 2);
	}
	
	@Test
	public void testGetPlayerPositionsOnStartMax() {
		ArrayList<String> playerNames = new ArrayList<String>();
		playerNames.add("Wolverine");
		playerNames.add("Captain America");
		playerNames.add("Black Widow");
		playerNames.add("Hulk");
		playerNames.add("Iron Man");
		playerNames.add("Spider Man");
		playerNames.add("Thor");

		GameManager manager = new GameManager(playerNames);
		
		comparePlayerPositions(manager.getPlayers(), manager, 0, 1, 6);
	}
	
	@Test
	public void testGetPlayerPositionsOnRotateClockwiseMin() {
		ArrayList<String> playerNames = new ArrayList<String>();
		playerNames.add("Wolverine");
		playerNames.add("Captain America");
		playerNames.add("Black Widow");
		
		GameManager manager = new GameManager(playerNames);
		manager.rotateClockwise();
		
		comparePlayerPositions(manager.getPlayers(), manager, 1, 2, 0);
	}
	
	@Test
	public void testGetPlayerPositionsOnRotateClockwiseMax() {
		ArrayList<String> playerNames = new ArrayList<String>();
		playerNames.add("Wolverine");
		playerNames.add("Captain America");
		playerNames.add("Black Widow");
		playerNames.add("Hulk");
		playerNames.add("Iron Man");
		playerNames.add("Spider Man");
		playerNames.add("Thor");
		GameManager manager = new GameManager(playerNames);

		manager.rotateClockwise();
		
		comparePlayerPositions(manager.getPlayers(), manager, 1, 2, 0);
	}
	
	@Test
	public void testGetPlayerPositionsOnRotateClockwiseTwice() {
		ArrayList<String> playerNames = new ArrayList<String>();
		playerNames.add("Wolverine");
		playerNames.add("Captain America");
		playerNames.add("Black Widow");
		playerNames.add("Hulk");
		playerNames.add("Iron Man");

		GameManager manager = new GameManager(playerNames);
		manager.rotateClockwise();
		manager.rotateClockwise();

		comparePlayerPositions(manager.getPlayers(), manager, 2, 3, 1);
	}
	
	@Test
	public void testChangeRotateDirectionAndResetPositionsMin() {
		ArrayList<String> playerNames = new ArrayList<String>();
		playerNames.add("Wolverine");
		playerNames.add("Captain America");
		playerNames.add("Black Widow");
		playerNames.add("Hulk");
		playerNames.add("Iron Man");
		playerNames.add("Spider Man");
		playerNames.add("Thor");
		
		GameManager manager = new GameManager(playerNames);
		
		manager.changeRotateDirectionAndResetPositions("Clockwise");
		comparePlayerPositions(manager.getPlayers(), manager, 0, 1, 6);

		manager.changeRotateDirectionAndResetPositions("CounterClockwise");
		comparePlayerPositions(manager.getPlayers(), manager, 0, 6, 1);
	}
	

	@Test
	public void testChangeRotateDirectionAndResetPositionsMax() {
		ArrayList<String> playerNames = new ArrayList<String>();
		playerNames.add("Wolverine");
		playerNames.add("Captain America");
		playerNames.add("Black Widow");
		
		GameManager manager = new GameManager(playerNames);
		
		manager.changeRotateDirectionAndResetPositions("Clockwise");
		comparePlayerPositions(manager.getPlayers(), manager, 0, 1, 2);

		manager.changeRotateDirectionAndResetPositions("CounterClockwise");
		comparePlayerPositions(manager.getPlayers(), manager, 0, 2, 1);
	}
	
	@Test
	public void testGetPlayerPositionsOnRotateCounterClockwiseMin() {
		ArrayList<String> playerNames = new ArrayList<String>();
		playerNames.add("Wolverine");
		playerNames.add("Captain America");
		playerNames.add("Black Widow");
		
		GameManager manager = new GameManager(playerNames);
		manager.changeRotateDirectionAndResetPositions("CounterClockwise");
		manager.rotateCounterClockwise();
		
		comparePlayerPositions(manager.getPlayers(), manager, 2, 1, 0);
	}
	
	@Test
	public void testGetPlayerPositionsOnRotateCounterClockwiseMax() {
		ArrayList<String> playerNames = new ArrayList<String>();
		playerNames.add("Wolverine");
		playerNames.add("Captain America");
		playerNames.add("Black Widow");
		playerNames.add("Hulk");
		playerNames.add("Iron Man");
		playerNames.add("Spider Man");
		playerNames.add("Thor");
		
		GameManager manager = new GameManager(playerNames);
		manager.changeRotateDirectionAndResetPositions("CounterClockwise");
		manager.rotateCounterClockwise();
		
		comparePlayerPositions(manager.getPlayers(), manager, 6, 5, 0);
	}
	
	@Test
	public void testGetPlayerPositionsOnRotateCounterClockwiseTwice() {
		ArrayList<String> playerNames = new ArrayList<String>();
		playerNames.add("Wolverine");
		playerNames.add("Captain America");
		playerNames.add("Black Widow");
		playerNames.add("Hulk");
		playerNames.add("Iron Man");
		
		GameManager manager = new GameManager(playerNames);
		manager.changeRotateDirectionAndResetPositions("CounterClockwise");
		manager.rotateCounterClockwise();
		manager.rotateCounterClockwise();
		
		comparePlayerPositions(manager.getPlayers(), manager, 3, 2, 4);
	}
	
	@Test
	public void testGetPlayerPositionsOnRotateCounterClockwiseMany() {
		ArrayList<String> playerNames = new ArrayList<String>();
		playerNames.add("Wolverine");
		playerNames.add("Captain America");
		playerNames.add("Black Widow");
		playerNames.add("Hulk");
		playerNames.add("Iron Man");
		
		GameManager manager = new GameManager(playerNames);
		manager.changeRotateDirectionAndResetPositions("CounterClockwise");
		
		for (int i = 0; i < 10; i++) {
			manager.rotateCounterClockwise();
		}
		
		comparePlayerPositions(manager.getPlayers(), manager, 0, 4, 1);
	}
	
	public void comparePlayerPositions(ArrayList<Player> players, GameManager manager, int currIndex, int nextIndex,
			int previousIndex) {
		assertEquals(players.get(currIndex), manager.getCurrentPlayer());
		assertEquals(players.get(nextIndex), manager.getNextPlayer());
		assertEquals(players.get(previousIndex), manager.getPreviousPlayer());
	}
}
