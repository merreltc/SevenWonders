package backend;

import java.util.Collections;

import dataStructures.Card;
import dataStructures.Deck;

public final class DeckHandler {
	private static final int TOP_OF_DECK = 0;
	
	public static void shuffleDeck(Deck deck){
		Collections.shuffle(deck.getCards());
	}

	public static Card drawCard(Deck age1) {
		return age1.getCards().remove(TOP_OF_DECK);
	}
}
