package dataStructures;

import java.util.HashMap;

import dataStructures.ValueEffect.AffectingEntity;
import dataStructures.ValueEffect.Value;

public class MultiValueEffect extends Effect {
	
	private HashMap<Enum, Integer> entitiesAndAmounts;
	private Value value;
	private AffectingEntity affectingEntity = AffectingEntity.NONE;
	
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

	public MultiValueEffect(EffectType effectType, Value value, AffectingEntity affectingEntity, Direction direction,
			HashMap<Enum, Integer> entitiesAndAmounts) {
		super(effectType);
		this.value = value;
		this.setDirection(direction);
		this.affectingEntity = affectingEntity;
		this.entitiesAndAmounts = entitiesAndAmounts;
	}
	
	public AffectingEntity getAffectingEntity() {
		return this.affectingEntity;
	}
	
	public HashMap<Enum, Integer> getEntities() {
		return this.entitiesAndAmounts;
	}

	public Value getValue() {
		return this.value;
	}
	
	public int getMultiValueAmount() {
		int amount = 0;
		for(Enum entity: this.entitiesAndAmounts.keySet()){
			amount += this.entitiesAndAmounts.get(entity);
		}
		
		return amount;
	}
}
