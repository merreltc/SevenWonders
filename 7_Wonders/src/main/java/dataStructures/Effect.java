package dataStructures;

public class Effect {
	private EffectType effectType = EffectType.NONE;
	private Resource resource;
	private Entity entity;
	private Good good;
	
	public enum EffectType {
		NONE, ENTITY
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

	public Effect(EffectType effectType, Entity entity,  Good good, int entityAmount) {
		this.effectType = effectType;
		this.entity = entity;
		this.good = good;
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

}
