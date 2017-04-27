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
	public void testDistributeInitialCards3Players() {
		ArrayList<String> playerNames = new ArrayList<String>();
		playerNames.add("Wolverine");
		playerNames.add("Captain America");
		playerNames.add("Black Widow");
		GameManager manager = new GameManager(playerNames);
		
		Deck deck = manager.getGameBoard().getDeck();
		int expectedDeckSize = deck.getNumCards() - 9;
		ArrayList<Player> players = manager.getPlayers();
		TurnHandler.turnHandler.distributeInitialCards(players, manager.getNumPlayers(), deck);
		
		for (Player player: players){
			assertEquals(3, player.getCurrentHand().size());
		}
		
		assertEquals(expectedDeckSize, deck.getCards().size());
	}

}
