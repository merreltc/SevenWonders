package dataStructures;

import java.util.HashMap;

public class Effect {
	
	public enum EffectType {
		NONE
		
	}
	
	public enum ScienceType {
		NONE
		
	}
	
	public enum AffectedEntityType {
		NONE
		
	}
	
	public enum Direction {
		NONE
		
	}

	public EffectType getEffectType() {
		return EffectType.NONE;
	}

	public HashMap<Object, Object> getResources() {
		return new HashMap<Object, Object>();
	}

	public ScienceType getScienceType() {
		return ScienceType.NONE;
	}

	public int getConflictToken() {
		return 0;
	}

	public int getVictoryPoints() {
		return 0;
	}

	public int getCoinTotal() {
		return 0;
	}

	public AffectedEntityType getAffectedEntityType() {
		return AffectedEntityType.NONE;
	}

	public HashMap<Object, Object> getCommerceResources() {
		return new HashMap<Object, Object>();
	}

	public int getCommerceValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Direction getCommerceDirection() {
		// TODO Auto-generated method stub
		return Direction.NONE;
	}

}
