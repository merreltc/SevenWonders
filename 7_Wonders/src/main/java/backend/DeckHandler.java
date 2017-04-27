package backend;

import java.util.Collections;

import dataStructures.Card;
import dataStructures.Deck;

public class DeckHandler {
	public static DeckHandler deckHandler = new DeckHandler();
	private static final int TOP_OF_DECK = 0;
	
	public void shuffleDeck(Deck deck){
		Collections.shuffle(deck.getCards());
	}

	public Card drawCard(Deck age1) {
		return age1.getCards().remove(TOP_OF_DECK);
	}
}
