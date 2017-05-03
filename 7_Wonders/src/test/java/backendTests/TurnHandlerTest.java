package backendTests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import backend.GameManager;
import backend.SetUpDeckHandler;
import backend.SetUpHandler;
import backend.TurnHandler;
import dataStructures.Card;
import dataStructures.Deck;
import dataStructures.Player;

public class TurnHandlerTest {

	@Test
	public void testDealInitialCards3Players() {
		ArrayList<String> playerNames = new ArrayList<String>();
		playerNames.add("Wolverine");
		playerNames.add("Captain America");
		playerNames.add("Black Widow");
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
		ArrayList<String> playerNames = new ArrayList<String>();
		playerNames.add("Wolverine");
		playerNames.add("Captain America");
		playerNames.add("Black Widow");
		playerNames.add("Hulk");
		playerNames.add("Iron Man");
		playerNames.add("Spider Man");
		playerNames.add("Thor");
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
		ArrayList<String> playerNames = new ArrayList<String>();
		playerNames.add("Wolverine");
		playerNames.add("Captain America");
		playerNames.add("Black Widow");
		playerNames.add("Hulk");
		playerNames.add("Iron Man");

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
