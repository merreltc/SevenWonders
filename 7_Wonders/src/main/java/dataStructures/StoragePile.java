package dataStructures;

import java.util.ArrayList;

import dataStructures.Effect.EffectType;

public class StoragePile{
	private ArrayList<Card> commercePile = new ArrayList<Card>();
	private ArrayList<Card> sciencePile = new ArrayList<Card>();
	private ArrayList<Card> endGamePile = new ArrayList<Card>();
	private ArrayList<Card> immediateEffectPile = new ArrayList<Card>();
	private ArrayList<Card> entireStorage = new ArrayList<Card>();

	public void addCard(Card card) {
		card.getEffectType();
		
		EntityEffect effect = (EntityEffect) card.getEffect();
		effect.getEntityType();
		this.addToCommercePile(card);
	}
	
	public ArrayList<Card> getCommercePile() {
		return this.commercePile;
	}

	public ArrayList<Card> getSciencePile() {
		return this.sciencePile;
	}

	public ArrayList<Card> getEndGamePile() {
		return this.endGamePile;
	}

	public ArrayList<Card> getImmediateEffectPile() {
		return this.immediateEffectPile;
	}

	public void addToCommercePile(Card card) {
		this.commercePile.add(card);
		this.entireStorage.add(card);
	}

	public void addToSciencePile(Card card) {
		this.sciencePile.add(card);
		this.entireStorage.add(card);
	}

	public void addToEndGamePile(Card card) {
		this.endGamePile.add(card);
		this.entireStorage.add(card);
	}

	public void addToImmediateEffectPile(Card card) {
		this.immediateEffectPile.add(card);
		this.entireStorage.add(card);
	}

	public ArrayList<Card> getEntireStoragePile() {
		return this.entireStorage;
	}
}
