package dataStructures.gameMaterials;

import dataStructures.gameMaterials.Level.Frequency;

public class AbilityEffect extends Effect {
	
	public enum Ability {
		PLAYSEVENTH
	}
	
	public AbilityEffect(EffectType effectType, Ability value, Frequency frequency) {
		super(effectType);
	}

	public Ability getAbility() {
		return Ability.PLAYSEVENTH;
	}

	public Frequency getFrequency() {
		return Frequency.SIXTHTURN;
	}

	
}
