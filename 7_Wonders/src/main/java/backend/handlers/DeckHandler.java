package backend.handlers;

import java.util.Collections;

import dataStructures.gameMaterials.Card;
import dataStructures.gameMaterials.Deck;

public final class DeckHandler {
	private static final int TOP_OF_DECK = 0;
	
	public static void shuffleDeck(Deck deck){
		Collections.shuffle(deck.getCards());
	}

	public static Card drawCard(Deck age1) {
		return age1.removeAtIndex(TOP_OF_DECK);
	}
}
