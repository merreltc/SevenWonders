package dataStructures;

import dataStructures.Effect.AffectingEntity;
import dataStructures.Effect.EffectType;
import dataStructures.Effect.Value;

public class Effect {
	private EffectType effectType = EffectType.NONE;
	private Resource resource;
	private Entity entity;
	private Good good;
	private int valueAmount;

	public enum EffectType {
		NONE, ENTITY, VALUE
	}

	public enum Direction {
		SELF
	}

	public enum AffectingEntity {
		NONE
	}

	public enum Entity {
		RESOURCE, MANUFACTUREDGOOD
	}

	public enum Resource {
		LUMBER, CLAY, ORE, STONE
	}

	public enum Good {
		LOOM, GLASS, PRESS
	}

	public enum Value {
		VICTORYPOINTS, MILITARY
	}

	public enum ValueType {
		VICTORYPOINT, CONFLICTTOKEN
	}

	public Effect() {

	}

	public Effect(EffectType effectType) {
		this.effectType = effectType;
	}

	public Effect(EffectType effectType, Entity entity, Resource resource, int entityAmount) {
		this.effectType = effectType;
		this.entity = entity;
		this.resource = resource;
	}

	public Effect(EffectType effectType, Entity entity, Good good, int entityAmount) {
		this.effectType = effectType;
		this.entity = entity;
		this.good = good;
	}

	public Effect(EffectType effectType, Value value, AffectingEntity affectedEntity, int valueAmount) {
		this.effectType = effectType;

		if (valueAmount <= 0) {
			throw new IllegalArgumentException();
		} else {
			this.valueAmount = valueAmount;
		}
	}

	public EffectType getEffectType() {
		return this.effectType;
	}

	public Direction getDirection() {
		return Direction.SELF;
	}

	public AffectingEntity getAffectingEntity() {
		return AffectingEntity.NONE;
	}

	public Entity getEntity() {
		return this.entity;
	}

	public int getEntityAmount() {
		return 1;
	}

	public Resource getResource() {
		return this.resource;
	}

	public Good getGood() {
		return this.good;
	}

	public int getValueAmount() {
		return this.valueAmount;
	}

	public Value getValue() {
		return Value.VICTORYPOINTS;
	}

	public ValueType getValueType() {
		return ValueType.VICTORYPOINT;
	}

}