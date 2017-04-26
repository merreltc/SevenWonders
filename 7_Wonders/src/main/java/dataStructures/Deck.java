package dataStructures;

import java.util.ArrayList;

public class Deck {
	private ArrayList<Card> cards = new ArrayList<Card>();
	
	public enum Age {
		AGE1
	}

	public Deck(Age age, ArrayList<Card> cards) {
		this.cards = cards;
	}

	public Age getAge() {
		return Age.AGE1;
	}

	public ArrayList<Card> getCards() {
		return this.cards;
	}

	public int getNumCards() {
		return this.cards.size();
	}

	public Card getCard(int index) {
		if(this.cards.size() <= index){
			throw new IndexOutOfBoundsException("There are not enough cards in the deck to get card: " + index);
		}
		
		return this.cards.get(index);
	}
}
