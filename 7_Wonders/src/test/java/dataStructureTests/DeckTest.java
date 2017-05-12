package dataStructureTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.easymock.EasyMock;
import org.junit.Test;

import dataStructures.Card;
import dataStructures.Cost;
import dataStructures.Deck;
import dataStructures.Effect;
import dataStructures.Card.CardType;
import dataStructures.Deck.Age;

public class DeckTest {

	@Test
	public void testDeckConstructor() {
		ArrayList<Card> cards = new ArrayList<Card>();
		initiateCards(cards);
		
		Deck deck = new Deck(Age.AGE1, cards);
		
		assertEquals(Age.AGE1, deck.getAge());
		assertEquals(cards, deck.getCards());
	}
	
	@Test
	public void testGetNumCardsSmall() {
		ArrayList<Card> cards = new ArrayList<Card>();
		initiateCards(cards);
		
		Deck deck = new Deck(Age.AGE1, cards);
		
		assertEquals(2, deck.getNumCards());
	}
	
	@Test
	public void testGetNumCardsLarger() {
		ArrayList<Card> cards = new ArrayList<Card>();
		initiateCards(cards);
		initiateCards(cards);
		initiateCards(cards);
		initiateCards(cards);
		initiateCards(cards);
		initiateCards(cards);
		initiateCards(cards);
		initiateCards(cards);
		
		Deck deck = new Deck(Age.AGE1, cards);
		
		assertEquals(16, deck.getNumCards());
	}
	
	@Test
	public void testGetCardValidIndex0() {
		ArrayList<Card> cards = new ArrayList<Card>();
		initiateCards(cards);
		
		Deck deck = new Deck(Age.AGE1, cards);
		
		assertEquals(cards.get(0), deck.getCard(0));
	}
	
	@Test
	public void testGetCardValidIndex6() {
		ArrayList<Card> cards = new ArrayList<Card>();
		initiateCards(cards);
		initiateCards(cards);
		initiateCards(cards);
		initiateCards(cards);
		
		Deck deck = new Deck(Age.AGE1, cards);
		
		assertEquals(cards.get(6), deck.getCard(6));
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testInvalidGetCardEmptyCardList() {
		ArrayList<Card> cards = new ArrayList<Card>();
		
		Deck deck = new Deck(Age.AGE1, cards);
		
		deck.getCard(0);
		fail();
	}
	
	@Test
	public void testInvalidGetCardEmptyCardListMessage() {
		ArrayList<Card> cards = new ArrayList<Card>();
		
		Deck deck = new Deck(Age.AGE1, cards);
		
		try{
			deck.getCard(0);
		}catch(IndexOutOfBoundsException ex){
			String message = "There are not enough cards in the deck to get card: 0";
			assertEquals(message, ex.getMessage());
		}
	}
	
	@Test
	public void testInvalidGetCardListMessage() {
		ArrayList<Card> cards = new ArrayList<Card>();
		initiateCards(cards);
		
		Deck deck = new Deck(Age.AGE1, cards);
		
		try{
			deck.getCard(3);
		}catch(IndexOutOfBoundsException ex){
			String message = "There are not enough cards in the deck to get card: 3";
			assertEquals(message, ex.getMessage());
		}
	}

	private void initiateCards(ArrayList<Card> cards) {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(6);
		frequency.add(7);

		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);

		Card forumCard = new Card("Forum", frequency, CardType.RAWMATERIAL, cost, effect, "Trading Post", "None");
		
		ArrayList<Integer> frequencyS = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(7);

		Cost costS = EasyMock.createStrictMock(Cost.class);
		Effect effectS = EasyMock.createStrictMock(Effect.class);

		Card statueCard = new Card("Statue", frequencyS, CardType.RAWMATERIAL, costS, effectS, "Theater", "Gardens");
		
		cards.add(forumCard);
		cards.add(statueCard);
	}
	
	@Test
	public void testGetNumCardsSmallAge2() {
		ArrayList<Card> cards = new ArrayList<Card>();
		initiateCards(cards);
		
		Deck deck = new Deck(Age.AGE2, cards);
		
		assertEquals(Age.AGE2, deck.getAge());
		assertEquals(2, deck.getNumCards());
	}

}
