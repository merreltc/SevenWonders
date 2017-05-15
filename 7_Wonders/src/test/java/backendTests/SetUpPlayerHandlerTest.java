package backendTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import backend.GameManager;
import backend.SetUpPlayerHandler;
import dataStructures.Player;
import dataStructures.Wonder.WonderType;

public class SetUpPlayerHandlerTest {

	@Test
	public void testValidPlayerNum() {
		assertTrue(new SetUpPlayerHandler().validatePlayerNum(3));
		assertTrue(new SetUpPlayerHandler().validatePlayerNum(7));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidPlayerNum2() {
		new SetUpPlayerHandler().validatePlayerNum(2);
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidPlayerNum8() {
		new SetUpPlayerHandler().validatePlayerNum(8);
		fail();
	}


	@Test
	public void testInvalidPlayerNum2ErrorMessage() {
		try {
			new SetUpPlayerHandler().validatePlayerNum(2);
		} catch (IllegalArgumentException error) {
			String message = "Cannot play with 2 players";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testInvalidPlayerNum8ErrorMessage() {
		try {
			new SetUpPlayerHandler().validatePlayerNum(8);
		} catch (IllegalArgumentException error) {
			String message = "Cannot play with 8 players";
			assertEquals(message, error.getMessage());
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreatePlayersZero() {
		ArrayList<String> playerNames = new ArrayList<String>();
		ArrayList<WonderType> wonders = new ArrayList<WonderType>();

		GameManager manager = new GameManager(playerNames);

		SetUpPlayerHandler setUpHandler = new SetUpPlayerHandler();
		setUpHandler.setUpAndReturnPlayers(playerNames);
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreatePlayersTooMany() {
		ArrayList<String> playerNames = new ArrayList<String>(
				Arrays.asList("Wolverine", "Captain America", "Black Widow", "Hulk", "Iron Man", "Spider Man", "Thor", "Ultron"));
		ArrayList<WonderType> wonders = new ArrayList<WonderType>(
				Arrays.asList(WonderType.COLOSSUS, WonderType.LIGHTHOUSE, WonderType.TEMPLE, WonderType.STATUE,
						WonderType.MAUSOLEUM, WonderType.GARDENS, WonderType.PYRAMIDS, WonderType.PYRAMIDS));

		SetUpPlayerHandler setUpHandler = new SetUpPlayerHandler();
		setUpHandler.setUpAndReturnPlayers(playerNames);
		fail();
	}

	private void verifyPlayersAndWonders(ArrayList<String> playerNames, ArrayList<WonderType> wonders) {
		SetUpPlayerHandler setUpHandler = new SetUpPlayerHandler();
		ArrayList<Player> players = setUpHandler.createPlayers(playerNames);

		for (int i = 0; i < playerNames.size(); i++) {
			String name = players.get(i).getName();
			assertTrue(playerNames.contains(name));

			assertEquals(wonders.get(i), players.get(i).getWonder().getType());
		}
	}
}
