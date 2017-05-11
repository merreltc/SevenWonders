package backendTests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import backend.GameManager;
import backend.PlayerTurnHandler;
import backend.SetUpDeckHandler;
import backend.SetUpHandler;
import backend.TurnHandler;
import dataStructures.Card;
import dataStructures.Deck;
import dataStructures.Player;
import dataStructures.Wonder;
import dataStructures.Wonder.WonderType;

public class TurnHandlerTest {

	@Test
	public void testDealInitialCards3Players() {
		ArrayList<String> playerNames = new ArrayList<String>(
				Arrays.asList("Wolverine", "Captain America", "Black Widow"));
		ArrayList<WonderType> wonders = new ArrayList<WonderType>(
				Arrays.asList(WonderType.COLOSSUS, WonderType.LIGHTHOUSE, WonderType.TEMPLE));

		GameManager manager = new GameManager(playerNames, wonders, new SetUpHandler(), new SetUpDeckHandler(),
				new TurnHandler(), new PlayerTurnHandler());

		Deck deck = manager.getGameBoard().getDeck();
		int expectedDeckSize = deck.getNumCards() - 21;
		ArrayList<Player> players = manager.getPlayers();
		new TurnHandler().dealInitialTurnCards(players, manager.getNumPlayers(), deck);

		for (Player player : players) {
			assertEquals(7, player.getCurrentHand().size());
		}

		assertEquals(expectedDeckSize, deck.getCards().size());
	}

	@Test
	public void testDealInitialCards7Players() {
		ArrayList<String> playerNames = new ArrayList<String>(
				Arrays.asList("Wolverine", "Captain America", "Black Widow", "Hulk", "Iron Man", "Spider Man", "Thor"));
		ArrayList<WonderType> wonders = new ArrayList<WonderType>(
				Arrays.asList(WonderType.COLOSSUS, WonderType.LIGHTHOUSE, WonderType.TEMPLE, WonderType.STATUE,
						WonderType.MAUSOLEUM, WonderType.GARDENS, WonderType.PYRAMIDS));

		GameManager manager = new GameManager(playerNames, wonders, new SetUpHandler(), new SetUpDeckHandler(),
				new TurnHandler(), new PlayerTurnHandler());

		Deck deck = manager.getGameBoard().getDeck();

		int expectedDeckSize = deck.getNumCards() - 49;
		ArrayList<Player> players = manager.getPlayers();
		new TurnHandler().dealInitialTurnCards(players, manager.getNumPlayers(), deck);

		for (Player player : players) {
			assertEquals(7, player.getCurrentHand().size());
		}

		assertEquals(expectedDeckSize, deck.getCards().size());
	}

	@Test
	public void testDealInitialCards5PlayersNotSame() {
		ArrayList<String> playerNames = new ArrayList<String>(
				Arrays.asList("Wolverine", "Captain America", "Black Widow", "Hulk", "Iron Man"));
		ArrayList<WonderType> wonders = new ArrayList<WonderType>(Arrays.asList(WonderType.COLOSSUS,
				WonderType.LIGHTHOUSE, WonderType.TEMPLE, WonderType.STATUE, WonderType.MAUSOLEUM));

		GameManager manager = new GameManager(playerNames, wonders, new SetUpHandler(), new SetUpDeckHandler(),
				new TurnHandler(), new PlayerTurnHandler());

		Deck deck = manager.getGameBoard().getDeck();

		int expectedDeckSize = deck.getNumCards() - 35;
		ArrayList<Player> players = manager.getPlayers();
		new TurnHandler().dealInitialTurnCards(players, manager.getNumPlayers(), deck);

		for (Player player : players) {
			assertEquals(7, player.getCurrentHand().size());
		}

		Player captain = players.get(1);
		Player hulk = players.get(3);

		ArrayList<Card> chand = captain.getCurrentHand();
		ArrayList<Card> hhand = hulk.getCurrentHand();
		Assert.assertNotEquals(chand.get(0), hhand.get(0));
		Assert.assertNotEquals(chand.get(1), hhand.get(1));
		Assert.assertNotEquals(chand.get(2), hhand.get(2));
		Assert.assertNotEquals(chand.get(3), hhand.get(3));
		Assert.assertNotEquals(chand.get(4), hhand.get(4));

		assertEquals(expectedDeckSize, deck.getCards().size());
	}

	@Test
	public void testGetNumPlayersUntilPass3Players() {
		ArrayList<String> playerNames = new ArrayList<String>(
				Arrays.asList("Wolverine", "Captain America", "Black Widow"));
		ArrayList<WonderType> wonders = new ArrayList<WonderType>(
				Arrays.asList(WonderType.COLOSSUS, WonderType.LIGHTHOUSE, WonderType.TEMPLE));

		GameManager manager = new GameManager(playerNames, wonders, new SetUpHandler(), new SetUpDeckHandler(),
				new TurnHandler(), new PlayerTurnHandler());

		Deck deck = manager.getGameBoard().getDeck();
		ArrayList<Player> players = manager.getPlayers();
		TurnHandler turnHandler = new TurnHandler();
		turnHandler.dealInitialTurnCards(players, manager.getNumPlayers(), deck);

		assertEquals(2, turnHandler.getNumPlayersUntilPass());
	}

	@Test
	public void testSetNumPlayersUntilPass() {
		TurnHandler turnHandler = new TurnHandler();
		
		turnHandler.setNumPlayersUntilPass(2);
		
		assertEquals(2, turnHandler.getNumPlayersUntilPass());
	}
	
	@Test
	public void testDefaultGetNumTurnsTilEndOfAge(){
		ArrayList<String> playerNames = new ArrayList<String>(
				Arrays.asList("Wolverine", "Captain America", "Black Widow"));
		ArrayList<WonderType> wonders = new ArrayList<WonderType>(
				Arrays.asList(WonderType.COLOSSUS, WonderType.LIGHTHOUSE, WonderType.TEMPLE));

		GameManager manager = new GameManager(playerNames, wonders, new SetUpHandler(), new SetUpDeckHandler(),
				new TurnHandler(), new PlayerTurnHandler());
		
		Deck deck = manager.getGameBoard().getDeck();
		ArrayList<Player> players = manager.getPlayers();
		TurnHandler turnHandler = new TurnHandler();
		
		turnHandler.dealInitialTurnCards(players, manager.getNumPlayers(), deck);

		assertEquals(1, turnHandler.getNumTurnsTilEndOfAge());
	}
	
	@Test
	public void testGetNumPlayersUntilPass6Players() {
		ArrayList<Player> players = EasyMock.partialMockBuilder(ArrayList.class).addMockedMethod("size").createMock();
		Deck deck = EasyMock.mock(Deck.class);
		
		EasyMock.expect(players.size()).andReturn(5);
		
		EasyMock.replay(players);
		TurnHandler turnHandler = new TurnHandler();
		turnHandler.dealInitialTurnCards(players, 5, deck);

		EasyMock.verify(players);
		assertEquals(3, turnHandler.getNumTurnsTilEndOfAge());
	}
	
	@Test
	public void testSetNumTurnsTilEndOfAge() {
		TurnHandler turnHandler = new TurnHandler();
		
		turnHandler.setNumTurnsTilEndOfAge(5);
		
		assertEquals(5, turnHandler.getNumTurnsTilEndOfAge());
	}
}