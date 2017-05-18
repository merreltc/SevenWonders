package dataStructures.gameMaterials;

import dataStructures.gameMaterials.Level.Frequency;

public class AbilityEffect extends Effect {
	private Ability ability;
	private Frequency frequency;
	
	public enum Ability {
		PLAYSEVENTH, FREEBUILD, FREEBUILDFROMDISCARD, COPYONENEIGHBORGUILD
	}
	
	public AbilityEffect(EffectType effectType, Ability ability, Frequency frequency) {
		super(effectType);
		this.ability = ability;
		this.frequency = frequency;
	}

	public Ability getAbility() {
		return this.ability;
	}

	public Frequency getFrequency() {
		return this.frequency;
	}

	
}
