package dataStructures.gameMaterials;

import java.util.ArrayList;
import java.util.HashMap;

import constants.GeneralEnums.CostType;
import dataStructures.gameMaterials.Effect.Direction;
import dataStructures.gameMaterials.Effect.EffectType;
import dataStructures.gameMaterials.EntityEffect.EntityType;
import dataStructures.gameMaterials.ValueEffect.AffectingEntity;
import dataStructures.gameMaterials.ValueEffect.Value;
import dataStructures.gameMaterials.ValueEffect.ValueType;

public class Level {
	private int priority;
	private Cost cost;
	private HashMap<Frequency, ArrayList<Effect>> effects;

	public enum Frequency {
		ENDOFTURN, EVERYTURN, SIXTHTURN, ONCEIMMEDIATE, ONCEAGE, ENDOFGAME, DEFAULT
	}

	public Level(int priority, Cost cost, Effect effect, Frequency frequency) {
		this.priority = priority;
		this.cost = cost;
		this.effects = new HashMap<Frequency, ArrayList<Effect>>();
		addEffect(effect, frequency);
	}

	public Level(int priority, Cost cost, HashMap<Frequency, ArrayList<Effect>> effects) {
		this.priority = priority;
		this.cost = cost;
		this.effects = effects;
	}

	private void addEffect(Effect effect, Frequency frequency) {
		ArrayList<Effect> toAdd;
		if (this.effects.containsKey(frequency)) {
			toAdd = this.effects.get(frequency);
		} else {
			toAdd = new ArrayList<Effect>();
		}
		toAdd.add(effect);
		this.effects.put(frequency, toAdd);
	}

	@Override
	public String toString() {
		return "Priority: " + this.priority + ", Cost: " + this.cost + ", Effects: " + this.effects;
	}

	@Override
	public boolean equals(Object obj) {
		Level temp = (Level) obj;
		boolean equalPriority = this.priority == temp.priority;
		boolean equalCost = this.getCost().equals(temp.getCost());
		boolean equalEffects = this.effects.equals(temp.getEffects());
		return equalPriority && equalCost && equalEffects;
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

	public HashMap<Frequency, ArrayList<Effect>> getEffects() {
		return this.effects;
	}
}
