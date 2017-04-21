package dataStructures;

import java.util.HashMap;

public class MultiValueEffect extends ValueEffect {
	private HashMap<Enum, Integer> entitiesAndAmounts;

	public MultiValueEffect(EffectType effectType, Value value,
			AffectingEntity affectingEntity, Direction direction,
			HashMap<Enum, Integer> entitiesAndAmounts) {
		super(effectType, value, affectingEntity, direction);
		this.entitiesAndAmounts = entitiesAndAmounts;
	}
	
	public int getMultiValueAmount() {
		int amount = 0;
		for(Enum entity: this.entitiesAndAmounts.keySet()){
			amount += this.entitiesAndAmounts.get(entity);
		}
		
		return amount;
	}
}
