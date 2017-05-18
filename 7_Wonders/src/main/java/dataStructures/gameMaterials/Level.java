package dataStructures.gameMaterials;

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
	private Effect effect;
	private HashMap<Effect, Frequency> effects;
	private Frequency frequency;

	public enum Frequency {
		ENDOFTURN, EVERYTURN, SIXTHTURN, ONCEIMMEDIATE, ONCEAGE, ENDOFGAME, DEFAULT
	}

	public Level(int priority, Cost cost, Effect effect, Frequency frequency) {
		this.priority = priority;
		this.cost = cost;
		this.effect = effect;
		this.frequency = frequency;
	}

	public Level(int priority, Cost cost, HashMap<Effect, Frequency> effects) {
		this.priority = priority;
		this.cost = cost;
		this.effects = effects;
	}

	@Override
	public String toString() {
		return "Priority: " + this.priority + ", Cost: " + this.cost + ", Effect: " + this.effect + ", Frequency: "
				+ this.frequency + ", Effects: " + this.effects;
	}

	@Override
	public boolean equals(Object obj) {
		Level temp = (Level) obj;
		boolean equalPriority = this.priority == temp.priority;
		boolean equalCost = this.getCost() == temp.getCost();
		boolean equalEffect = this.effect == temp.getEffect();
		boolean equalEffects = this.effects == temp.getEffects();
		boolean equalFrequency = this.frequency == temp.getFrequency();
		return equalPriority && equalCost && equalEffect && equalEffects && equalFrequency;
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

	public Effect getEffect() {
		return this.effect;
	}

	public EffectType getEffectType() {
		return this.effect.getEffectType();
	}

	public Direction getEffectDirection() {
		return this.effect.getDirection();
	}

	public EntityType getEntityType() {
		return ((EntityEffect) this.effect).getEntityType();
	}

	public HashMap<Enum, Integer> getEntities() {
		return ((EntityEffect) this.effect).getEntities();
	}

	public Value getEffectValue() {
		return ((ValueEffect) this.effect).getValue();
	}

	public ValueType getEffectValueType() {
		return ((ValueEffect) this.effect).getValueType();
	}

	public AffectingEntity getAffectingEntity() {
		return ((ValueEffect) this.effect).getAffectingEntity();
	}

	public HashMap<Enum, Integer> getValues() {
		return ((MultiValueEffect) this.effect).getValues();
	}

	public HashMap<Effect, Frequency> getEffects() {
		return this.effects;
	}

	public Frequency getFrequency() {
		return this.frequency;
	}
}
