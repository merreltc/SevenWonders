package dataStructures.gameMaterials;

import dataStructures.gameMaterials.Level.Frequency;

public class AbilityEffect extends Effect {
	private Ability ability;
	
	public enum Ability {
		PLAYSEVENTH, FREEBUILD, FREEBUILDFROMDISCARD, COPYONENEIGHBORGUILD
	}
	
	public AbilityEffect(Ability ability) {
		super(EffectType.ABILITY);
		this.ability = ability;
	}

	public Ability getAbility() {
		return this.ability;
	}
}
