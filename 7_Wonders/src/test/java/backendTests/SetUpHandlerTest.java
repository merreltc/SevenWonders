package backendTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

import org.junit.Test;

import backend.GameManager;
import backend.SetUpHandler;
import dataStructures.Player;
import dataStructures.Wonder.WonderType;
import guiDataStructures.PlayerInformationHolder;

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
		ArrayList<String> playerNames = new ArrayList<String>(
				Arrays.asList("Wolverine", "Captain America", "Black Widow"));
		ArrayList<WonderType> wonders = new ArrayList<WonderType>(
				Arrays.asList(WonderType.COLOSSUS, WonderType.LIGHTHOUSE, WonderType.TEMPLE));

		verifyPlayersAndWonders(playerNames, wonders);
	}

	@Test
	public void testCreatePlayersMax() {
		ArrayList<String> playerNames = new ArrayList<String>(
				Arrays.asList("Wolverine", "Captain America", "Black Widow", "Hulk", "Iron Man", "Spider Man", "Thor"));
		ArrayList<WonderType> wonders = new ArrayList<WonderType>(
				Arrays.asList(WonderType.COLOSSUS, WonderType.LIGHTHOUSE, WonderType.TEMPLE, WonderType.STATUE,
						WonderType.MAUSOLEUM, WonderType.GARDENS, WonderType.PYRAMIDS));

		verifyPlayersAndWonders(playerNames, wonders);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreatePlayersZero() {
		ArrayList<String> playerNames = new ArrayList<String>();
		ArrayList<WonderType> wonders = new ArrayList<WonderType>();

		GameManager manager = new GameManager(compileHolderObjects(playerNames, wonders));

		SetUpHandler setUpHandler = new SetUpHandler();
		setUpHandler.setUpAndReturnPlayers(compileHolderObjects(playerNames, wonders));
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreatePlayersTooMany() {
		ArrayList<String> playerNames = new ArrayList<String>(
				Arrays.asList("Wolverine", "Captain America", "Black Widow", "Hulk", "Iron Man", "Spider Man", "Thor", "Ultron"));
		ArrayList<WonderType> wonders = new ArrayList<WonderType>(
				Arrays.asList(WonderType.COLOSSUS, WonderType.LIGHTHOUSE, WonderType.TEMPLE, WonderType.STATUE,
						WonderType.MAUSOLEUM, WonderType.GARDENS, WonderType.PYRAMIDS, WonderType.PYRAMIDS));

		SetUpHandler setUpHandler = new SetUpHandler();
		setUpHandler.setUpAndReturnPlayers(compileHolderObjects(playerNames, wonders));
		fail();
	}

	private void verifyPlayersAndWonders(ArrayList<String> playerNames, ArrayList<WonderType> wonders) {
		SetUpHandler setUpHandler = new SetUpHandler();
		ArrayList<Player> players = setUpHandler.createPlayers(compileHolderObjects(playerNames, wonders));

		for (int i = 0; i < playerNames.size(); i++) {
			String name = players.get(i).getName();
			assertTrue(playerNames.contains(name));
			assertEquals(wonders.get(i), players.get(i).getWonder().getType());
		}
	}
	
	private ArrayList<PlayerInformationHolder> compileHolderObjects(ArrayList<String> playerNames, ArrayList<WonderType> wonders){
		ArrayList<PlayerInformationHolder> holders = new ArrayList<PlayerInformationHolder>();
		
		for (int i = 0; i < playerNames.size(); i++){
			PlayerInformationHolder currentHolder = new PlayerInformationHolder(playerNames.get(i), wonders.get(i), 'a');
			holders.add(currentHolder);
		}
		return holders;
	}
}
