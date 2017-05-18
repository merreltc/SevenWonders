package dataStructures.gameMaterials;

public class Effect {
	private EffectType effectType = EffectType.NONE;
	private Direction direction = Direction.SELF;

	public enum EffectType {
		NONE, ENTITY, VALUE, MULTIVALUE, ABILITY
	}

	public enum Direction {
		SELF, RIGHT, LEFT, ALL, NEIGHBORS
	}

	public Effect(EffectType effectType) {
		this.effectType = effectType;
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

}