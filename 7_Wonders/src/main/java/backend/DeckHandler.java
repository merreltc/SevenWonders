package backend;

import java.util.Collections;

import dataStructures.Deck;

public class DeckHandler {
	public static DeckHandler deckHandler = new DeckHandler();
	
	public void shuffleDeck(Deck deck){
		Collections.shuffle(deck.getCards());
	}
}
