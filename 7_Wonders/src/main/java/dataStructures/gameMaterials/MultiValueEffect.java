package dataStructures.gameMaterials;

import java.util.HashMap;

import dataStructures.gameMaterials.Level.Frequency;

public class MultiValueEffect extends ValueEffect {
	private HashMap<Enum, Integer> valuesAndAmounts;

	public MultiValueEffect(Value value, AffectingEntity affectingEntity, Direction direction,
			HashMap<Enum, Integer> entitiesAndAmounts) {
		super(value, affectingEntity, direction);
		this.valuesAndAmounts = entitiesAndAmounts;
		this.setEffectType(EffectType.MULTIVALUE);
	}

	public HashMap<Enum, Integer> getValues() {
		return this.valuesAndAmounts;
	}

	@Override
	public boolean equals(Object obj) {
		Effect effect = (Effect) obj;
		if (effect.getEffectType() != EffectType.MULTIVALUE) {
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
