package backendTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.easymock.EasyMock;
import org.junit.Test;

import backend.GameManager;
import dataStructures.GameBoard;

public class GameManagerTest {

	@Test
	public void testGameManagerGetMinPlayers() {
		ArrayList<String> playerNames = new ArrayList<String>();
		playerNames.add("Wolverine");
		playerNames.add("Captain America");
		playerNames.add("Black Widow");
		GameManager manager = new GameManager(playerNames);

		assertEquals(3, manager.getNumPlayers());
	}

	@Test
	public void testGameManagerGetMaxPlayers() {
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
	public void testSetUpGameBoardMinPlayers() {
		GameManager manager = new GameManager(3);
		
		assertEquals(3, manager.getNumPlayers());
	}
	
	@Test
	public void testSetUpGameBoardMaxPlayers() {
		GameManager manager = new GameManager(7);
		
		assertEquals(7, manager.getNumPlayers());
	}
	
	@Test
	public void testGetPlayers() {
		GameManager manager = new GameManager(7);
		
		assertEquals(7, manager.getPlayers().size());
	}
	
	@Test
	public void testTrade(){
		GameManager manager = new GameManager(7);
		
		manager.trade(manager.getPlayer(0), manager.getPlayer(1), 3);
		
		assertEquals(0, manager.getPlayerCoinTotal(0));
		assertEquals(6, manager.getPlayerCoinTotal(1));
	}
}
