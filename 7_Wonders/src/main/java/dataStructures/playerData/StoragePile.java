package dataStructures.playerData;

import java.util.ArrayList;

import dataStructures.gameMaterials.Card;
import dataStructures.gameMaterials.Effect.Direction;
import dataStructures.gameMaterials.Effect.EffectType;
import dataStructures.gameMaterials.EntityEffect;
import dataStructures.gameMaterials.EntityEffect.EntityType;
import dataStructures.gameMaterials.ValueEffect;
import dataStructures.gameMaterials.ValueEffect.Value;


public class StoragePile {
	private ArrayList<Card> commercePile = new ArrayList<Card>();
	private ArrayList<Card> sciencePile = new ArrayList<Card>();
	private ArrayList<Card> endGamePile = new ArrayList<Card>();
	private ArrayList<Card> immediateEffectPile = new ArrayList<Card>();
	private ArrayList<Card> entireStorage = new ArrayList<Card>();

	public void addCard(Card card) {
		EffectType effectType = card.getEffectType();

		if(effectType == EffectType.MULTIVALUE){
			this.addToEndGamePile(card);
		}else if (effectType == EffectType.VALUE){
			determineValueEffectPile(card);
		}else{
			determineEntityEffectPile(card);
		}
	}

	private void determineEntityEffectPile(Card card) {
		EntityEffect entity = (EntityEffect) card.getEffect();

		if (entity.getEntityType() == EntityType.SCIENCE) {
			this.addToSciencePile(card);
		} else {
			this.addToCommercePile(card);
		}
	}

	private void determineValueEffectPile(Card card) {
		ValueEffect value = (ValueEffect) card.getEffect();

		Value actualValue = value.getValue();
		Direction direction = value.getDirection();
		if (actualValue == Value.GUILD || direction == Direction.ALL) {
			this.addToEndGamePile(card);
		} else if (direction == Direction.SELF) {
			this.addToImmediateEffectPile(card);
		} else {
			this.addToCommercePile(card);
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
