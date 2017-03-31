package backendTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.easymock.EasyMock;
import org.junit.Test;

import backend.GameManager;
import dataStructures.GameBoard;

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
}
