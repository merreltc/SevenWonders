package dataStructures;

import java.util.ArrayList;

public class StoragePile{
	private ArrayList<Card> commercePile = new ArrayList<Card>();
	private ArrayList<Card> sciencePile = new ArrayList<Card>();

	public ArrayList<Card> getCommercePile() {
		return this.commercePile;
	}

	public ArrayList<Card> getSciencePile() {
		return this.sciencePile;
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

	public void addToSciencePile(Card card) {
		this.sciencePile.add(card);
	}
}
