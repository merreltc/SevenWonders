package dataStructures;

import java.util.HashMap;

public class MultiValueEffect extends ValueEffect {
	private HashMap<Enum, Integer> valuesAndAmounts;

	public MultiValueEffect(EffectType effectType, Value value,
			AffectingEntity affectingEntity, Direction direction,
			HashMap<Enum, Integer> entitiesAndAmounts) {
		super(effectType, value, affectingEntity, direction);
		this.valuesAndAmounts = entitiesAndAmounts;
	}
	
	public HashMap<Enum, Integer> getValues() {
		return this.valuesAndAmounts;
	}
	
	public int getMultiValueAmount() {
		int amount = 0;
		for(Enum entity: this.valuesAndAmounts.keySet()){
			amount += this.valuesAndAmounts.get(entity);
		}
		
		return amount;
	}
}
