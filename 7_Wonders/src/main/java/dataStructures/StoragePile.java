package dataStructures;

import java.util.ArrayList;

import dataStructures.Effect.EffectType;
import dataStructures.EntityEffect.EntityType;
import dataStructures.ValueEffect.Value;

public class StoragePile {
	private ArrayList<Card> commercePile = new ArrayList<Card>();
	private ArrayList<Card> sciencePile = new ArrayList<Card>();
	private ArrayList<Card> endGamePile = new ArrayList<Card>();
	private ArrayList<Card> immediateEffectPile = new ArrayList<Card>();
	private ArrayList<Card> entireStorage = new ArrayList<Card>();

	public void addCard(Card card) {
		EffectType effectType = card.getEffectType();

		switch (effectType) {

		case VALUE:
			ValueEffect value = (ValueEffect) card.getEffect();
			
			if (value.getValue() != Value.GUILD) {
				value.getDirection();
			}
			this.addToEndGamePile(card);
			break;
		default:
			EntityEffect entity = (EntityEffect) card.getEffect();

			if (entity.getEntityType() == EntityType.SCIENCE) {
				this.addToSciencePile(card);
			} else {
				this.addToCommercePile(card);
			}
			break;
		}
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
