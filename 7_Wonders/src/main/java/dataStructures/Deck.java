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
}
