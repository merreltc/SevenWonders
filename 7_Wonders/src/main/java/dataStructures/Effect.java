package dataStructures;

import dataStructures.Effect.EffectType;

public class Effect {
	private EffectType effectType = EffectType.NONE;
	
	public enum EffectType {
		NONE, ENTITY
	}
	
	public enum Direction {
		SELF
	}
	
	public enum AffectedEntityType {
		NONE
	}
	
	public Effect() {
		
	}

	public Effect(EffectType effectType) {
		this.effectType = effectType;
	}

	public EffectType getEffectType() {
		return this.effectType;
	}

	public Direction getDirection() {
		return Direction.SELF;
	}

	public AffectedEntityType getAffectedEntityType() {
		return AffectedEntityType.NONE;
	}

}
