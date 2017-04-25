package dataStructures;

import java.util.HashMap;

import dataStructures.Cost.CostType;
import dataStructures.Effect.Direction;
import dataStructures.Effect.EffectType;
import dataStructures.EntityEffect.EntityType;

public class Level {
	private int priority;
	private Cost cost;
	private Effect effect;

	public Level(int priority, Cost cost, Effect effect) {
		this.priority = priority;
		this.cost = cost;
		this.effect = effect;
	}

	public int getPriority() {
		return this.priority;
	}
	
	public CostType getCostType() {
		return this.cost.getType();
	}

	public HashMap<Enum, Integer> getCost() {
		return this.cost.getCost();
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
}
