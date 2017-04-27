package backend;

import java.util.ArrayList;

import dataStructures.Card;
import dataStructures.Deck;
import dataStructures.Player;

public class TurnHandler {
	public static TurnHandler turnHandler = new TurnHandler();

	public void distributeInitialCards(ArrayList<Player> players, int numPlayers, Deck deck) {
		for (Player player: players) {
			ArrayList<Card> currentHand = new ArrayList<Card>();
			
			for(int i = 0; i < numPlayers; i++){
				Card toAdd = DeckHandler.deckHandler.drawCard(deck);
				currentHand.add(toAdd);
			}
			
			player.setCurrentHand(currentHand);
		}
	}
}
