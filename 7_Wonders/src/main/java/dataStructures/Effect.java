package dataStructures;

import java.util.HashMap;

import dataStructures.ValueEffect.AffectingEntity;
import dataStructures.ValueEffect.Value;

public class Effect {
	private EffectType effectType = EffectType.NONE;
	private Direction direction = Direction.SELF;
	
	private HashMap<Enum, Integer> entitiesAndAmounts;
	private Value value;
	private int valueAmount;
	private AffectingEntity affectingEntity = AffectingEntity.NONE;
	
	public enum EffectType {
		NONE, ENTITY, VALUE, MULTIVALUE
	}

	public enum Direction {
		SELF, RIGHT, LEFT, ALL, NEIGHBORS
	}
	
	public enum Value {
		VICTORYPOINTS, MILITARY, COMMERCE, GUILD
	}

	public enum ValueType {
		VICTORYPOINT, CONFLICTTOKEN, COIN
	}
	
	public enum AffectingEntity {
		NONE, RAWRESOURCES, MANUFACTUREDGOODS, COMMERCIALSTRUCTURES, 
		SCIENTIFICSTRUCTURES, MILITARYSTRUCTURES, CIVILIANSTRUCTURES, GUILD, WONDERLEVEL
	}

	public Effect(EffectType effectType) {
		this.effectType = effectType;
	}
	
	public Effect(EffectType effectType, Value value, AffectingEntity affectingEntity, Direction direction, HashMap<Enum, Integer> entitiesAndAmounts) {
		this.effectType = effectType;
		this.value = value;
		this.direction = direction;
		this.affectingEntity = affectingEntity;
		this.entitiesAndAmounts = entitiesAndAmounts;
	}

	public EffectType getEffectType() {
		return this.effectType;
	}

	public Direction getDirection() {
		return this.direction;
	}
	
	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public HashMap<Enum, Integer> getEntities() {
		return this.entitiesAndAmounts;
	}

	public int getMultiValueAmount() {
		int amount = 0;
		for(Enum entity: this.entitiesAndAmounts.keySet()){
			amount += this.entitiesAndAmounts.get(entity);
		}
		
		return amount;
	}
}