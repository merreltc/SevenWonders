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
	
	@Override
	public String toString() {
		return "Ability: " + this.ability;
	}

	@Override
	public boolean equals(Object obj) {
		if(((Effect)obj).getEffectType() != EffectType.ABILITY) {
			return false;
		}

		AbilityEffect temp = (AbilityEffect) obj;
		return temp.getAbility() == this.ability;
	}
}
