package backendTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import backend.SetUpHandler;
import dataStructures.Player;
import dataStructures.Wonder.WonderType;

public class SetupBackendTest {

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
	public void testSetUpReturnsPlayerNamesMin() {
		ArrayList<String> playerNames = new ArrayList<String>(
				Arrays.asList("Wolverine", "Captain America", "Black Widow"));
		ArrayList<WonderType> wonders = new ArrayList<WonderType>(
				Arrays.asList(WonderType.COLOSSUS, WonderType.LIGHTHOUSE, WonderType.TEMPLE));

		SetUpHandler setUpHandler = new SetUpHandler();
		ArrayList<Player> players = setUpHandler.setUpAndReturnPlayers(playerNames, wonders);

		for (int i = 0; i < players.size(); i++) {
			assertEquals(playerNames.get(i), players.get(i).getName());
		}
	}

	@Test
	public void testSetUpReturnsPlayerNamesMax() {
		ArrayList<String> playerNames = new ArrayList<String>(
				Arrays.asList("Wolverine", "Captain America", "Black Widow", "Hulk", "Iron Man", "Spider Man", "Thor"));
		ArrayList<WonderType> wonders = new ArrayList<WonderType>(
				Arrays.asList(WonderType.COLOSSUS, WonderType.LIGHTHOUSE, WonderType.TEMPLE, WonderType.STATUE,
						WonderType.MAUSOLEUM, WonderType.GARDENS, WonderType.PYRAMIDS));

		SetUpHandler setUpHandler = new SetUpHandler();
		ArrayList<Player> players = setUpHandler.setUpAndReturnPlayers(playerNames, wonders);

		for (int i = 0; i < players.size(); i++) {
			assertEquals(playerNames.get(i), players.get(i).getName());
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetUpReturnsPlayerNamesZero() {
		ArrayList<String> playerNames = new ArrayList<String>();
		ArrayList<WonderType> wonders = new ArrayList<WonderType>();

		SetUpHandler setUpHandler = new SetUpHandler();
		setUpHandler.setUpAndReturnPlayers(playerNames, wonders);
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetUpReturnsPlayerNamesTooMany() {
		ArrayList<String> playerNames = new ArrayList<String>(
				Arrays.asList("Wolverine", "Captain America", "Black Widow", "Hulk", "Iron Man", "Spider Man", "Thor", "Thor"));
		ArrayList<WonderType> wonders = new ArrayList<WonderType>(
				Arrays.asList(WonderType.COLOSSUS, WonderType.LIGHTHOUSE, WonderType.TEMPLE, WonderType.STATUE,
						WonderType.MAUSOLEUM, WonderType.GARDENS, WonderType.PYRAMIDS, WonderType.PYRAMIDS));

		SetUpHandler setUpHandler = new SetUpHandler();
		setUpHandler.setUpAndReturnPlayers(playerNames, wonders);
		fail();
	}

	@Test
	public void testCreatePlayersMinNumPlayers() {
		ArrayList<String> playerNames = new ArrayList<String>(
				Arrays.asList("Wolverine", "Captain America", "Black Widow"));
		ArrayList<WonderType> wonders = new ArrayList<WonderType>(
				Arrays.asList(WonderType.COLOSSUS, WonderType.LIGHTHOUSE, WonderType.TEMPLE));

		SetUpHandler setUpHandler = new SetUpHandler();
		ArrayList<Player> players = setUpHandler.createPlayers(playerNames, wonders);

		for (int i = 0; i < players.size(); i++) {
			assertEquals(playerNames.get(i), players.get(i).getName());
		}
	}

	@Test
	public void testCreateNamedPlayersMin() {
		ArrayList<String> playerNames = new ArrayList<String>(
				Arrays.asList("Wolverine", "Captain America", "Black Widow"));
		ArrayList<WonderType> wonders = new ArrayList<WonderType>(
				Arrays.asList(WonderType.COLOSSUS, WonderType.LIGHTHOUSE, WonderType.TEMPLE));

		SetUpHandler setUpHandler = new SetUpHandler();
		ArrayList<Player> players = setUpHandler.createPlayers(playerNames, wonders);

		for (int i = 0; i < players.size(); i++) {
			assertEquals(playerNames.get(i), players.get(i).getName());
		}
	}

	@Test
	public void testCreateNamedPlayersMax() {
		ArrayList<String> playerNames = new ArrayList<String>(
				Arrays.asList("Wolverine", "Captain America", "Black Widow", "Hulk", "Iron Man", "Spider Man", "Thor"));
		ArrayList<WonderType> wonders = new ArrayList<WonderType>(
				Arrays.asList(WonderType.COLOSSUS, WonderType.LIGHTHOUSE, WonderType.TEMPLE, WonderType.STATUE,
						WonderType.MAUSOLEUM, WonderType.GARDENS, WonderType.PYRAMIDS));

		SetUpHandler setUpHandler = new SetUpHandler();
		ArrayList<Player> players = setUpHandler.createPlayers(playerNames, wonders);

		for (int i = 0; i < players.size(); i++) {
			assertEquals(playerNames.get(i), players.get(i).getName());
		}
	}
}
