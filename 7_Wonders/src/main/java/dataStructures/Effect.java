package dataStructures;

public class Effect {
	private EffectType effectType = EffectType.NONE;
	private Resource resource;
	
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
		RESOURCE
	}
	
	public enum Resource {
		LUMBER, CLAY, ORE, STONE
	}
	
	public Effect() {
		
	}

	public Effect(EffectType effectType) {
		this.effectType = effectType;
	}

	public Effect(EffectType effectType, Entity entity, Resource resource, int entityAmount) {
		this.effectType = effectType;
		this.resource = resource;
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
		return Entity.RESOURCE;
	}

	public int getEntityAmount() {
		return 1;
	}

	public Resource getResource() {
		return this.resource;
	}

}
