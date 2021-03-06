package backendTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import backend.handlers.SetUpPlayerHandler;
import constants.GeneralEnums.GameMode;
import dataStructures.playerData.Player;

public class SetUpPlayerHandlerTest {

	@Test
	public void testValidPlayerNum() {
		assertTrue(new SetUpPlayerHandler(GameMode.EASY).validatePlayerNum(3));
		assertTrue(new SetUpPlayerHandler(GameMode.EASY).validatePlayerNum(7));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidPlayerNum2() {
		new SetUpPlayerHandler(GameMode.EASY).validatePlayerNum(2);
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidPlayerNum8() {
		new SetUpPlayerHandler(GameMode.EASY).validatePlayerNum(8);
		fail();
	}

	@Test
	public void testInvalidPlayerNum2ErrorMessage() {
		try {
			new SetUpPlayerHandler(GameMode.EASY).validatePlayerNum(2);
		} catch (IllegalArgumentException error) {
			String message = "Cannot play with 2 players";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testInvalidPlayerNum8ErrorMessage() {
		try {
			new SetUpPlayerHandler(GameMode.EASY).validatePlayerNum(8);
		} catch (IllegalArgumentException error) {
			String message = "Cannot play with 8 players";
			assertEquals(message, error.getMessage());
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreatePlayersZero() {
		ArrayList<String> playerNames = new ArrayList<String>();
		SetUpPlayerHandler setUpHandler = new SetUpPlayerHandler(GameMode.EASY);
		setUpHandler.setUpAndReturnPlayers(playerNames);
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreatePlayersTooMany() {
		ArrayList<String> playerNames = new ArrayList<String>(Arrays.asList("Wolverine", "Captain America",
				"Black Widow", "Hulk", "Iron Man", "Spider Man", "Thor", "Ultron"));

		SetUpPlayerHandler setUpHandler = new SetUpPlayerHandler(GameMode.EASY);
		setUpHandler.setUpAndReturnPlayers(playerNames);
		fail();
	}

	@Test
	public void testSetUpReturnsPlayerNamesMin() {
		ArrayList<String> playerNames = new ArrayList<String>(
				Arrays.asList("Wolverine", "Captain America", "Black Widow"));

		SetUpPlayerHandler setUpHandler = new SetUpPlayerHandler(GameMode.EASY);
		ArrayList<Player> players = setUpHandler.setUpAndReturnPlayers(playerNames);

		for (int i = 0; i < players.size(); i++) {
			assertEquals(playerNames.get(i), players.get(i).getName());
		}
	}

	@Test
	public void testSetUpReturnsPlayerNamesMax() {
		ArrayList<String> playerNames = new ArrayList<String>(
				Arrays.asList("Wolverine", "Captain America", "Black Widow", "Hulk", "Iron Man", "Spider Man", "Thor"));

		SetUpPlayerHandler setUpHandler = new SetUpPlayerHandler(GameMode.EASY);
		ArrayList<Player> players = setUpHandler.setUpAndReturnPlayers(playerNames);

		for (int i = 0; i < players.size(); i++) {
			assertEquals(playerNames.get(i), players.get(i).getName());
		}
	}

	@Test
	public void testCreatePlayersMinNumPlayers() {
		ArrayList<String> playerNames = new ArrayList<String>(
				Arrays.asList("Wolverine", "Captain America", "Black Widow"));

		SetUpPlayerHandler setUpHandler = new SetUpPlayerHandler(GameMode.EASY);
		ArrayList<Player> players = setUpHandler.createPlayers(playerNames);

		for (int i = 0; i < players.size(); i++) {
			assertEquals(playerNames.get(i), players.get(i).getName());
		}
	}

	@Test
	public void testCreateNamedPlayersMin() {
		ArrayList<String> playerNames = new ArrayList<String>(
				Arrays.asList("Wolverine", "Captain America", "Black Widow"));

		SetUpPlayerHandler setUpHandler = new SetUpPlayerHandler(GameMode.EASY);
		ArrayList<Player> players = setUpHandler.createPlayers(playerNames);

		for (int i = 0; i < players.size(); i++) {
			assertEquals(playerNames.get(i), players.get(i).getName());
		}
	}

	@Test
	public void testCreateNamedPlayersMax() {
		ArrayList<String> playerNames = new ArrayList<String>(
				Arrays.asList("Wolverine", "Captain America", "Black Widow", "Hulk", "Iron Man", "Spider Man", "Thor"));

		SetUpPlayerHandler setUpHandler = new SetUpPlayerHandler(GameMode.EASY);
		ArrayList<Player> players = setUpHandler.createPlayers(playerNames);

		for (int i = 0; i < players.size(); i++) {
			assertEquals(playerNames.get(i), players.get(i).getName());
		}
	}
}
