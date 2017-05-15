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
		MultiValueEffect effect = (MultiValueEffect) obj;

		if (this.getValue() == effect.getValue() && this.getDirection() == effect.getDirection()
				&& this.getAffectingEntity() == effect.getAffectingEntity()
				&& this.valuesAndAmounts.toString().equals(effect.getValues().toString())) {
			return true;
		}

		return false;
	}
}
