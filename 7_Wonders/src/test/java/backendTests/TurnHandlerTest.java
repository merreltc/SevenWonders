package backendTests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import backend.GameManager;
import backend.TurnHandler;
import dataStructures.Deck;
import dataStructures.Player;

public class TurnHandlerTest {

	@Test
	public void testDealInitialCards3Players() {
		ArrayList<String> playerNames = new ArrayList<String>();
		playerNames.add("Wolverine");
		playerNames.add("Captain America");
		playerNames.add("Black Widow");
		GameManager manager = new GameManager(playerNames);
		
		Deck deck = manager.getGameBoard().getDeck();
		int expectedDeckSize = deck.getNumCards() - 9;
		ArrayList<Player> players = manager.getPlayers();
		TurnHandler.turnHandler.dealInitialCards(players, manager.getNumPlayers(), deck);
		
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
		GameManager manager = new GameManager(playerNames);
		
		Deck deck = manager.getGameBoard().getDeck();
		System.out.println(deck.getNumCards());
		int expectedDeckSize = deck.getNumCards() - 49;
		ArrayList<Player> players = manager.getPlayers();
		TurnHandler.turnHandler.dealInitialCards(players, manager.getNumPlayers(), deck);
		
		for (Player player: players){
			assertEquals(7, player.getCurrentHand().size());
		}
		
		assertEquals(expectedDeckSize, deck.getCards().size());
	}

}
