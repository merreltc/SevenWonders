package backendTests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

import backend.GameManager;
import backend.SetUpDeckHandler;
import backend.SetUpHandler;
import backend.TurnHandler;
import dataStructures.Card;
import dataStructures.Deck;
import dataStructures.Player;
import dataStructures.Wonder;

public class TurnHandlerTest {

	@Test
	public void testDealInitialCards3Players() {
		HashMap<String, Wonder.WonderType> playerNames = new HashMap<String, Wonder.WonderType>();
		playerNames.put("Wolverine", Wonder.WonderType.COLOSSUS);
		playerNames.put("Captain America",Wonder.WonderType.LIGHTHOUSE);
		playerNames.put("Black Widow",Wonder.WonderType.TEMPLE);
		GameManager manager = new GameManager(playerNames, new SetUpHandler(), new SetUpDeckHandler(), new TurnHandler());
		
		Deck deck = manager.getGameBoard().getDeck();
		int expectedDeckSize = deck.getNumCards() - 9;
		ArrayList<Player> players = manager.getPlayers();
		new TurnHandler().dealInitialTurnCards(players, manager.getNumPlayers(), deck);
		
		for (Player player: players){
			assertEquals(3, player.getCurrentHand().size());
		}
		
		assertEquals(expectedDeckSize, deck.getCards().size());
	}
	
	@Test
	public void testDealInitialCards7Players() {
		HashMap<String, Wonder.WonderType> playerNames = new HashMap<String, Wonder.WonderType>();
		playerNames.put("Wolverine", Wonder.WonderType.COLOSSUS);
		playerNames.put("Captain America",Wonder.WonderType.LIGHTHOUSE);
		playerNames.put("Black Widow",Wonder.WonderType.TEMPLE);
		playerNames.put("Hulk", Wonder.WonderType.STATUE);
		playerNames.put("Iron Man",Wonder.WonderType.PYRAMIDS);
		playerNames.put("Spider Man", Wonder.WonderType.GARDENS);
		playerNames.put("Thor", Wonder.WonderType.MAUSOLEUM);
		GameManager manager = new GameManager(playerNames, new SetUpHandler(), new SetUpDeckHandler(), new TurnHandler());
		
		Deck deck = manager.getGameBoard().getDeck();
		
		int expectedDeckSize = deck.getNumCards() - 49;
		ArrayList<Player> players = manager.getPlayers();
		new TurnHandler().dealInitialTurnCards(players, manager.getNumPlayers(), deck);
		
		for (Player player: players){
			assertEquals(7, player.getCurrentHand().size());
		}
		
		assertEquals(expectedDeckSize, deck.getCards().size());
	}
	
	@Test
	public void testDealInitialCards5PlayersNotSame() {
		HashMap<String, Wonder.WonderType> playerNames = new HashMap<String, Wonder.WonderType>();
		playerNames.put("Wolverine", Wonder.WonderType.COLOSSUS);
		playerNames.put("Captain America",Wonder.WonderType.LIGHTHOUSE);
		playerNames.put("Black Widow",Wonder.WonderType.TEMPLE);
		playerNames.put("Hulk", Wonder.WonderType.STATUE);
		playerNames.put("Iron Man",Wonder.WonderType.PYRAMIDS);

		GameManager manager = new GameManager(playerNames, new SetUpHandler(), new SetUpDeckHandler(), new TurnHandler());
		
		Deck deck = manager.getGameBoard().getDeck();
		
		int expectedDeckSize = deck.getNumCards() - 25;
		ArrayList<Player> players = manager.getPlayers();
		new TurnHandler().dealInitialTurnCards(players, manager.getNumPlayers(), deck);
		
		for (Player player: players){
			assertEquals(5, player.getCurrentHand().size());
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

}
