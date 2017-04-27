package backendTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.easymock.EasyMock;
import org.junit.Test;

import backend.DeckHandler;
import backend.SetUpDeckHandler;
import dataStructures.Card;
import dataStructures.Deck;
import dataStructures.Deck.Age;

public class DeckHandlerTest {

	@Test
	public void testShuffleDeck() {
		Collections collection = EasyMock.createStrictMock(Collections.class);
		ArrayList<Card> cards = SetUpDeckHandler.setUpDeckHandler.createDeck(Age.AGE1, 3);
		Collections.shuffle(cards);
		
		EasyMock.replay(collection);
		
		Deck age1 = new Deck(Age.AGE1, cards);
		DeckHandler.deckHandler.shuffleDeck(age1);
		
		EasyMock.verify(collection);
	}

}
