package backendTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import backend.SetUpHandler;
import dataStructures.Player;
import dataStructures.Wonder;
import dataStructures.Wonder.WonderType;

public class SetUpHandlerTest {

	@Test
	public void testValidPlayerNum() {
		assertTrue(new SetUpHandler().validatePlayerNum(3));
		assertTrue(new SetUpHandler().validatePlayerNum(7));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidPlayerNum2() {
		new SetUpHandler().validatePlayerNum(2);
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidPlayerNum8() {
		new SetUpHandler().validatePlayerNum(8);
		fail();
	}

	@Test
	public void testInvalidPlayerNum2ErrorMessage() {
		try {
			new SetUpHandler().validatePlayerNum(2);
		} catch (IllegalArgumentException error) {
			String message = "Cannot play with 2 players";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testInvalidPlayerNum8ErrorMessage() {
		try {
			new SetUpHandler().validatePlayerNum(8);
		} catch (IllegalArgumentException error) {
			String message = "Cannot play with 8 players";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testCreatePlayersMin() {
		HashMap<String, WonderType> playerNamesAndWonders = new HashMap<String, WonderType>();
		playerNamesAndWonders.put("Wolverine", WonderType.COLOSSUS);
		playerNamesAndWonders.put("Captain America", WonderType.LIGHTHOUSE);
		playerNamesAndWonders.put("Black Widow", WonderType.TEMPLE);

		verifyPlayersAndWonders(playerNamesAndWonders);
	}

	@Test
	public void testCreatePlayersMax() {
		HashMap<String, WonderType> playerNamesAndWonders = new HashMap<String, WonderType>();
		playerNamesAndWonders.put("Wolverine", WonderType.COLOSSUS);
		playerNamesAndWonders.put("Captain America", WonderType.LIGHTHOUSE);
		playerNamesAndWonders.put("Black Widow", WonderType.TEMPLE);
		playerNamesAndWonders.put("Hulk", WonderType.STATUE);
		playerNamesAndWonders.put("Iron Man", WonderType.MAUSOLEUM);
		playerNamesAndWonders.put("Spider Man", WonderType.GARDENS);
		playerNamesAndWonders.put("Thor", WonderType.PYRAMIDS);

		verifyPlayersAndWonders(playerNamesAndWonders);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreatePlayersZero() {
		HashMap<String, WonderType> playerNamesAndWonders = new HashMap<String, WonderType>();

		SetUpHandler setUpHandler = new SetUpHandler();
		setUpHandler.setUpAndReturnPlayers(playerNamesAndWonders);
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreatePlayersTooMany() {
		HashMap<String, WonderType> playerNamesAndWonders = new HashMap<String, WonderType>();
		playerNamesAndWonders.put("Wolverine", WonderType.COLOSSUS);
		playerNamesAndWonders.put("Captain America", WonderType.LIGHTHOUSE);
		playerNamesAndWonders.put("Black Widow", WonderType.TEMPLE);
		playerNamesAndWonders.put("Hulk", WonderType.STATUE);
		playerNamesAndWonders.put("Iron Man", WonderType.MAUSOLEUM);
		playerNamesAndWonders.put("Spider Man", WonderType.GARDENS);
		playerNamesAndWonders.put("Thor", WonderType.PYRAMIDS);
		playerNamesAndWonders.put("Ultron", WonderType.COLOSSUS);

		SetUpHandler setUpHandler = new SetUpHandler();
		setUpHandler.setUpAndReturnPlayers(playerNamesAndWonders);
		fail();
	}

	private void verifyPlayersAndWonders(HashMap<String, WonderType> playerNamesAndWonders) {
		Set<String> names = playerNamesAndWonders.keySet();
		SetUpHandler setUpHandler = new SetUpHandler();
		ArrayList<Player> players = setUpHandler.createPlayers(playerNamesAndWonders);

		for (Player player : players) {
			String name = player.getName();
			assertTrue(names.contains(name));
			assertEquals(playerNamesAndWonders.get(name), player.getWonder().getType());
		}
	}	
}
