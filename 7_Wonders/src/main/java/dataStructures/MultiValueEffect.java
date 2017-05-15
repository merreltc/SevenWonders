package dataStructures;

import java.util.HashMap;

public class MultiValueEffect extends ValueEffect {
	private HashMap<Enum, Integer> valuesAndAmounts;

	public MultiValueEffect(EffectType effectType, Value value, AffectingEntity affectingEntity, Direction direction,
			HashMap<Enum, Integer> entitiesAndAmounts) {
		super(effectType, value, affectingEntity, direction);
		this.valuesAndAmounts = entitiesAndAmounts;
	}

	public HashMap<Enum, Integer> getValues() {
		return this.valuesAndAmounts;
	}

	public int getMultiValueAmount() {
		int amount = 0;
		for (Enum entity : this.valuesAndAmounts.keySet()) {
			amount += this.valuesAndAmounts.get(entity);
		}

		return amount;
	}

	@Override
	public boolean equals(Object obj) {
		Effect effect = (Effect) obj;
		if(effect.getEffectType() != EffectType.MULTIVALUE){
			return false;
		}
		
		MultiValueEffect multi = (MultiValueEffect) effect;

		if (this.getValue() == multi.getValue() && this.getDirection() == multi.getDirection()
				&& this.getAffectingEntity() == multi.getAffectingEntity()
				&& this.valuesAndAmounts.toString().equals(multi.getValues().toString())) {
			return true;
		}

		return false;
	}
}
