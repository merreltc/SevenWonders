package dataStructures.gameMaterials;

import java.util.ArrayList;

import utils.Translate;
import utils.TranslateWithTemplate;

public class Deck {
	private ArrayList<Card> cards = new ArrayList<Card>();
	private Age age;

	public enum Age {
		AGE1, AGE2, AGE3
	}

	public Deck(Age age, ArrayList<Card> cards) {
		this.cards = cards;
		this.age = age;
	}

	public Age getAge() {
		return this.age;
	}

	public ArrayList<Card> getCards() {
		return this.cards;
	}

	public int getNumCards() {
		return this.cards.size();
	}

	public Card getCard(int index) {
		if (this.cards.size() <= index) {
			String msg = TranslateWithTemplate.prepareStringTemplateWithIntArg(index, "notEnoughCards",
					Translate.getNewResourceBundle());
			throw new IndexOutOfBoundsException(msg);
		}

		return this.cards.get(index);
	}
	
	public Card removeAtIndex(int index) {
		return this.cards.remove(index);
	}
}
