package dataStructures.gameMaterials;

import java.util.HashMap;
import java.util.HashSet;

import constants.GeneralEnums.CostType;
import dataStructures.gameMaterials.Effect.EffectType;

public class Level {
	private int priority;
	private Cost cost;
	private HashMap<Frequency, HashSet<Effect>> effects;

	public enum Frequency {
		ENDOFTURN, EVERYTURN, SIXTHTURN, ONCEIMMEDIATE, ONCEAGE, ENDOFGAME, DEFAULT
	}

	public Level(int priority, Cost cost, HashMap<Frequency, HashSet<Effect>> effects) {
		this.priority = priority;
		this.cost = cost;
		this.effects = effects;
	}

	@Override
	public String toString() {
		return "Priority: " + this.priority + ", Cost: " + this.cost + ", Effects: " + this.effects;
	}

	@Override
	public boolean equals(Object obj) {
		System.out.println("New Test");
		Level temp = (Level) obj;
		boolean equalPriority = this.priority == temp.priority;
		boolean equalCost = this.getCost().equals(temp.getCost());
		boolean equalEffects = equalEffects(temp.getEffects());
		System.out.println(
				"equalPriority? " + equalPriority + ", equalCost? " + equalCost + ", equalEffects? " + equalEffects);
		System.out.println("Levels Eq? " + (equalPriority && equalCost && equalEffects));
		return equalPriority && equalCost && equalEffects;
	}

	private boolean equalEffects(HashMap<Frequency, HashSet<Effect>> other) {
		for (Frequency frequency : this.effects.keySet()) {
			if (!other.containsKey(frequency)) {
				return false;
			} else if (!compareEffects(frequency, other.get(frequency))) {
				return false;
			} else {
				continue;
			}
		}
		return true;
	}

	private boolean compareEffects(Frequency frequency, HashSet<Effect> other) {
		HashSet<Effect> thisEffects = this.effects.get(frequency);
		for (Effect thisEffect : thisEffects) {
			if (otherContainsEffect(other, thisEffect)) {
				continue;
			} else {
				return false;
			}
		}
		return true;
	}

	private boolean otherContainsEffect(HashSet<Effect> other, Effect thisEffect) {
		for (Effect otherEffect : other) {
			EffectType type = thisEffect.getEffectType();
			switch (type) {
			case ABILITY:
				if (((AbilityEffect) thisEffect).equals((AbilityEffect) otherEffect)) {
					return true;
				}
				break;
			case VALUE:
				if (((ValueEffect) thisEffect).equals((ValueEffect) otherEffect)) {
					return true;
				}
				break;
			case ENTITY:
				if (((EntityEffect) thisEffect).equals((EntityEffect) otherEffect)) {
					return true;
				}
				break;
			default:
				throw new IllegalArgumentException("Invalid Effect Type");
			}
		}
		return false;
	}

	public int getPriority() {
		return this.priority;
	}

	public Cost getCost() {
		return this.cost;
	}

	public CostType getCostType() {
		return this.cost.getType();
	}

	public HashMap<Enum, Integer> getCosts() {
		return this.cost.getCost();
	}

	public HashMap<Frequency, HashSet<Effect>> getEffects() {
		return this.effects;
	}
}
