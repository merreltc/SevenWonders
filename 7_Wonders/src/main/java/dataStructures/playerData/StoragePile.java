package dataStructures.playerData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import dataStructures.gameMaterials.AbilityEffect;
import dataStructures.gameMaterials.Card;
import dataStructures.gameMaterials.Effect;
import dataStructures.gameMaterials.Effect.Direction;
import dataStructures.gameMaterials.Effect.EffectType;
import dataStructures.gameMaterials.EntityEffect;
import dataStructures.gameMaterials.EntityEffect.EntityType;
import dataStructures.gameMaterials.Level.Frequency;
import dataStructures.gameMaterials.MultiValueEffect;
import dataStructures.gameMaterials.ValueEffect;
import dataStructures.gameMaterials.ValueEffect.Value;

public class StoragePile {
	private ArrayList<Card> commercePile = new ArrayList<Card>();
	private ArrayList<Card> sciencePile = new ArrayList<Card>();
	private ArrayList<Card> endGamePile = new ArrayList<Card>();
	private ArrayList<Card> immediateEffectPile = new ArrayList<Card>();
	private ArrayList<Card> allCardStorage = new ArrayList<Card>();

	private HashMap<Frequency, HashSet<Effect>> wonderPile = new HashMap<Frequency, HashSet<Effect>>();
	private HashSet<Effect> entireEffectStorage = new HashSet<Effect>();
	private HashSet<Effect> temporaryDiscardPile = new HashSet<Effect>();

	public void addCard(Card card) {
		EffectType effectType = card.getEffectType();
		switch (effectType) {

		case MULTIVALUE:
			this.addToEndGamePile(card);
			break;
		case VALUE:
			determineValueEffectPile(card);
			break;
		default:
			determineEntityEffectPile(card);
			break;
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

	public HashMap<Frequency, HashSet<Effect>> getWonderPile() {
		return this.wonderPile;
	}

	public void addToCommercePile(Card card) {
		this.commercePile.add(card);
		this.allCardStorage.add(card);
		this.entireEffectStorage.add(card.getEffect());
	}

	public void addToSciencePile(Card card) {
		this.sciencePile.add(card);
		this.allCardStorage.add(card);
		this.entireEffectStorage.add(card.getEffect());
	}

	public void addToEndGamePile(Card card) {
		this.endGamePile.add(card);
		this.allCardStorage.add(card);
		this.entireEffectStorage.add(card.getEffect());
	}

	public void addToImmediateEffectPile(Card card) {
		this.immediateEffectPile.add(card);
		this.allCardStorage.add(card);
		this.entireEffectStorage.add(card.getEffect());
	}

	public void addToWonderPile(HashMap<Frequency, HashSet<Effect>> effects) {
		HashSet<Effect> curr;

		for (Frequency frequency : effects.keySet()) {
			HashSet<Effect> effectSet = effects.get(frequency);
			curr = getEffectsFromMap(frequency, effectSet);
			this.wonderPile.put(frequency, curr);
			this.entireEffectStorage.addAll(effectSet);
		}
	}

	private HashSet<Effect> getEffectsFromMap(Frequency frequency, HashSet<Effect> effects) {
		HashSet<Effect> curr;
		if (this.wonderPile.containsKey(frequency)) {
			curr = this.wonderPile.get(frequency);
			curr.addAll(effects);
		} else {
			curr = effects;
		}
		return curr;
	}

	public ArrayList<Card> getAllCardStoragePile() {
		return this.allCardStorage;
	}

	public HashSet<Effect> getEntireEffectStorage() {
		return this.entireEffectStorage;
	}

	public boolean containsCard(String name) {
		for (Card storage : this.getAllCardStoragePile()) {
			if (storage.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	public boolean containsEffect(Effect effect) {
		for (Effect curr : this.getEntireEffectStorage()) {
			if (effectsEqualTopLevel(effect, curr)) {
				return compareEffectsBottomLevel(effect, curr);
			}
		}

		return false;
	}

	private boolean effectsEqualTopLevel(Effect effect, Effect curr) {
		return (effect.getEffectType() == curr.getEffectType()) && (effect.getDirection() == curr.getDirection());
	}

	private boolean compareEffectsBottomLevel(Effect actual, Effect compare) {
		EffectType type = actual.getEffectType();

		switch (type) {
		case ENTITY:
			return ((EntityEffect) actual).equals((EntityEffect) compare);
		case VALUE:
			return ((ValueEffect) actual).equals((ValueEffect) compare);
		case MULTIVALUE:
			return ((MultiValueEffect) actual).equals((MultiValueEffect) compare);
		case ABILITY:
			return ((AbilityEffect) actual).equals((AbilityEffect) compare);
		case NONE:
			return true;
		default:
			throw new IllegalArgumentException("Invalid EffectType");
		}
	}

	public void clearTemporaryWonderEffects() {
		HashSet<Effect> onceEffects = this.wonderPile.remove(Frequency.ONCEIMMEDIATE);
		HashSet<Effect> endTurnEffects = this.wonderPile.remove(Frequency.ENDOFTURN);
		if (onceEffects != null)
			this.entireEffectStorage.removeAll(onceEffects);

		if (endTurnEffects != null)
			this.entireEffectStorage.removeAll(endTurnEffects);
	}

	public void temporaryDiscard(Effect effect) {
		this.wonderPile.get(Frequency.ONCEAGE).remove(effect);
		this.entireEffectStorage.remove(effect);
		this.temporaryDiscardPile.add(effect);
	}

	public void restoreTemporaryDiscards() {
		this.wonderPile.get(Frequency.ONCEAGE).addAll(this.temporaryDiscardPile);
		this.entireEffectStorage.addAll(this.temporaryDiscardPile);
		this.temporaryDiscardPile.clear();
	}
}