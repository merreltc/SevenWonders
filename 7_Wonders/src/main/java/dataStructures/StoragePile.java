package dataStructures;

import java.util.ArrayList;

public class StoragePile{
	private ArrayList<Card> commercePile = new ArrayList<Card>();

	public ArrayList<Card> getCommercePile() {
		return this.commercePile;
	}

	public ArrayList<Card> getSciencePile() {
		return new ArrayList<Card>();
	}

	public ArrayList<Card> getEndGamePile() {
		return new ArrayList<Card>();
	}

	public ArrayList<Card> getImmediateEffectPile() {
		return new ArrayList<Card>();
	}

	public void addToCommercePile(Card card) {
		this.commercePile.add(card);
	}
}
