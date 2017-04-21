package dataStructures;

import java.util.HashMap;

public class Effect {
	private EffectType effectType = EffectType.NONE;
	
	private Resource resource;
	private Entity entity;
	private Good good;
	private Science science;
	private int entityAmount = 1;
	private HashMap<Enum, Integer> entitiesAndAmounts;
	
	private Value value;
	private Direction direction = Direction.SELF;
	private AffectingEntity affectingEntity = AffectingEntity.NONE;

	private int valueAmount;
	
	public enum EffectType {
		NONE, ENTITY, VALUE
	}

	public enum Direction {
		SELF, RIGHT, LEFT, ALL, NEIGHBORS
	}

	public enum AffectingEntity {
		NONE, RAWRESOURCES, MANUFACTUREDGOODS, COMMERCIALSTRUCTURES, 
		SCIENTIFICSTRUCTURES, MILITARYSTRUCTURES, CIVILIANSTRUCTURES, GUILD, WONDERLEVEL
	}

	public enum Entity {
		RESOURCE, MANUFACTUREDGOOD, SCIENCE
	}

	public enum Resource {
		LUMBER, CLAY, ORE, STONE
	}

	public enum Good {
		LOOM, GLASS, PRESS
	}

	public enum Science {
		PROTRACTOR, WHEEL, TABLET		
	}
	
	public enum Value {
		VICTORYPOINTS, MILITARY, COMMERCE, GUILD
	}

	public enum ValueType {
		VICTORYPOINT, CONFLICTTOKEN, COIN
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
		this.entityAmount = entityAmount;
	}
	
	public Effect(EffectType effectType, Entity entity,  Good good, int entityAmount) {
		this.effectType = effectType;
		this.entity = entity;
		this.good = good;
	}
	
	public Effect(EffectType effectType, Entity entity,  Science science, int entityAmount) {
		this.effectType = effectType;
		this.entity = entity;
		this.science = science;
	}	

	public Effect(EffectType effectType, Entity entity, HashMap<Enum, Integer> entitiesAndAmounts) {
		this.effectType = effectType;
		this.entity = entity;
		this.entitiesAndAmounts = entitiesAndAmounts;
	}

	public Effect(EffectType effectType, Value value, AffectingEntity affectingEntity, int valueAmount) {
		this.effectType = effectType;
		this.value = value;
		this.affectingEntity = affectingEntity;

		validateValueAmount(value, valueAmount);
		this.valueAmount = valueAmount;
	}

	public Effect(EffectType effectType, Value value, AffectingEntity affectingEntity, Direction direction,
			int valueAmount) {
		this.effectType = effectType;
		this.value = value;
		this.direction = direction;
		this.affectingEntity = affectingEntity;
		this.valueAmount = valueAmount;
	}
	
	private void validateValueAmount(Value value, int valueAmount) {
		switch (value){
		case VICTORYPOINTS:
			if (valueAmount <= 0 || valueAmount >= 9){
				throw new IllegalArgumentException("Cannot have valueAmount of " + valueAmount);
			}
			break;
		default:
			if (valueAmount <= -2 || valueAmount == 0 || valueAmount >= 4){
				throw new IllegalArgumentException("Cannot have valueAmount of " + valueAmount);
			}
			break;
		}
	}

	public EffectType getEffectType() {
		return this.effectType;
	}

	public Direction getDirection() {
		return this.direction;
	}

	public AffectingEntity getAffectingEntity() {
		return this.affectingEntity;
	}

	public Entity getEntity() {
		return this.entity;
	}

	public int getEntityAmount() {
		return this.entityAmount;
	}

	public Resource getResource() {
		return this.resource;
	}

	public Good getGood() {
		return this.good;
	}
	
	public Science getScience() {
		return this.science;
	}

	public HashMap<Enum, Integer> getEntities() {
		return this.entitiesAndAmounts;
	}

	public int getValueAmount() {
		return this.valueAmount;
	}

	public Value getValue() {
		return this.value;
	}

	public ValueType getValueType() {
		switch (this.value) {
		case VICTORYPOINTS:
			return ValueType.VICTORYPOINT;
		case COMMERCE:
			return ValueType.COIN;
		case GUILD:
			return ValueType.VICTORYPOINT;
		default:
			return ValueType.CONFLICTTOKEN;
		}
	}
}