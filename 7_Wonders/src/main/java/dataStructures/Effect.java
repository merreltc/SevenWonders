package dataStructures;

public class Effect {
	
	public enum EffectType {
		NONE
	}
	
	public enum Direction {
		SELF
	}
	
	public enum AffectedEntityType {
		NONE
	}

	public EffectType getEffectType() {
		return EffectType.NONE;
	}

	public Direction getDirection() {
		return Direction.SELF;
	}

	public AffectedEntityType getAffectedEntityType() {
		return AffectedEntityType.NONE;
	}

}
