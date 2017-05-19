package testHelpers;

import java.util.ArrayList;
import java.util.HashMap;

import constants.GeneralEnums.Good;
import constants.GeneralEnums.RawResource;
import constants.GeneralEnums.Science;
import dataStructures.gameMaterials.AbilityEffect;
import dataStructures.gameMaterials.AbilityEffect.Ability;
import dataStructures.gameMaterials.Effect;
import dataStructures.gameMaterials.EntityEffect;
import dataStructures.gameMaterials.EntityEffect.EntityType;
import dataStructures.gameMaterials.Level.Frequency;
import dataStructures.gameMaterials.ValueEffect;
import dataStructures.gameMaterials.ValueEffect.AffectingEntity;
import dataStructures.gameMaterials.ValueEffect.Value;
import dataStructures.gameMaterials.Wonder;

public class WonderEffectsGenerator {
	Wonder wonder;

	public WonderEffectsGenerator(Wonder wonder) {
		this.wonder = wonder;
	}

	public HashMap<Frequency, ArrayList<Effect>> getExpectedEffects(int priority) {
		HashMap<Frequency, ArrayList<Effect>> effects = null;

		switch (this.wonder.getSide()) {
		case A:
			effects = getSideAEffects(priority);
			break;
		case B:
			effects = getSideBEffects(priority);
			break;
		default:
			throw new IllegalArgumentException("Invalid Side");
		}

		return effects;
	}

	private HashMap<Frequency, ArrayList<Effect>> getSideAEffects(int priority) {
		HashMap<Frequency, ArrayList<Effect>> effectsAndFrequency = new HashMap<Frequency, ArrayList<Effect>>();

		switch (priority) {
		case 1:
			getSideAPriority1Effect(effectsAndFrequency);
			break;
		case 2:
			getSideAPriority2Effect(effectsAndFrequency);
			break;
		case 3:
			getSideAPriority3Effect(effectsAndFrequency);
			break;
		default:
			throw new IllegalArgumentException("Invalid Priority");
		}
		return effectsAndFrequency;
	}

	private HashMap<Frequency, ArrayList<Effect>> getSideBEffects(int priority) {
		HashMap<Frequency, ArrayList<Effect>> effectsAndFrequency = new HashMap<Frequency, ArrayList<Effect>>();

		switch (priority) {
		case 1:
			getSideBPriority1Effect(effectsAndFrequency);
			break;
		case 2:
			getSideBPriority2Effect(effectsAndFrequency);
			break;
		case 3:
			getSideBPriority3Effect(effectsAndFrequency);
			break;
		case 4:
			getSideBPriority4Effect(effectsAndFrequency);
		default:
			throw new IllegalArgumentException("Invalid Priority");
		}
		return effectsAndFrequency;
	}

	private void getSideAPriority1Effect(HashMap<Frequency, ArrayList<Effect>> effectsAndFrequency) {
		ArrayList<Effect> effects = new ArrayList<Effect>();
		Effect effect;
		effect = new ValueEffect(Value.VICTORYPOINTS, AffectingEntity.NONE, 3);
		effects.add(effect);
		effectsAndFrequency.put(Frequency.ENDOFGAME, effects);
	}

	private void getSideBPriority1Effect(HashMap<Frequency, ArrayList<Effect>> effectsAndFrequency) {
		Effect effect;
		ArrayList<Effect> effects = new ArrayList<Effect>();

		switch (this.wonder.getType()) {
		case COLOSSUS:
			effect = new ValueEffect(Value.MILITARY, AffectingEntity.NONE, 1);
			effects.add(effect);
			effect = new ValueEffect(Value.COMMERCE, AffectingEntity.NONE, 3);
			effects.add(effect);
			effectsAndFrequency.put(Frequency.ONCEIMMEDIATE, effects);

			effect = new ValueEffect(Value.VICTORYPOINTS, AffectingEntity.NONE, 3);
			effects = new ArrayList<Effect>();
			effects.add(effect);
			effectsAndFrequency.put(Frequency.ENDOFGAME, effects);
			break;
		case LIGHTHOUSE:
			addAllRawResource(effects, 1);
			effectsAndFrequency.put(Frequency.EVERYTURN, effects);
			break;
		case TEMPLE:
			effect = new ValueEffect(Value.COMMERCE, AffectingEntity.NONE, 4);
			effects.add(effect);
			effectsAndFrequency.put(Frequency.ONCEIMMEDIATE, effects);

			effects = new ArrayList<Effect>();
			effect = new ValueEffect(Value.VICTORYPOINTS, AffectingEntity.NONE, 2);
			effects.add(effect);
			effectsAndFrequency.put(Frequency.ENDOFGAME, effects);
			break;
		case GARDENS:
			effect = new ValueEffect(Value.VICTORYPOINTS, AffectingEntity.NONE, 3);
			effects.add(effect);
			effectsAndFrequency.put(Frequency.ENDOFGAME, effects);
			break;
		case STATUE:
			effect = new ValueEffect(Value.COMMERCE, AffectingEntity.RAWRESOURCES, 1);
			effects.add(effect);
			effectsAndFrequency.put(Frequency.EVERYTURN, effects);
			break;
		case MAUSOLEUM:
			effect = new AbilityEffect(Ability.FREEBUILDFROMDISCARD);
			effects.add(effect);
			effectsAndFrequency.put(Frequency.ENDOFTURN, effects);

			effects = new ArrayList<Effect>();
			effect = new ValueEffect(Value.VICTORYPOINTS, AffectingEntity.NONE, 2);
			effects.add(effect);
			effectsAndFrequency.put(Frequency.ENDOFGAME, effects);
			break;
		case PYRAMIDS:
			effect = new ValueEffect(Value.VICTORYPOINTS, AffectingEntity.NONE, 3);
			effects.add(effect);
			effectsAndFrequency.put(Frequency.ENDOFGAME, effects);
			break;
		default:
			throw new IllegalArgumentException("Invalid Wonder Type");
		}
	}

	private void getSideAPriority2Effect(HashMap<Frequency, ArrayList<Effect>> effectsAndFrequency) {
		ArrayList<Effect> effects = new ArrayList<Effect>();
		Effect effect;

		switch (this.wonder.getType()) {
		case COLOSSUS:
			effect = new ValueEffect(Value.MILITARY, AffectingEntity.NONE, 2);
			effects.add(effect);
			effectsAndFrequency.put(Frequency.ONCEIMMEDIATE, effects);
			break;
		case LIGHTHOUSE:
			addAllRawResource(effects, 1);
			effectsAndFrequency.put(Frequency.EVERYTURN, effects);
			break;
		case TEMPLE:
			effect = new ValueEffect(Value.COMMERCE, AffectingEntity.NONE, 9);
			effects.add(effect);
			effectsAndFrequency.put(Frequency.ONCEIMMEDIATE, effects);
			break;
		case GARDENS:
			addAllScience(effects, 1);
			effectsAndFrequency.put(Frequency.ENDOFGAME, effects);
			break;
		case STATUE:
			effect = new AbilityEffect(Ability.FREEBUILD);
			effects.add(effect);
			effectsAndFrequency.put(Frequency.ONCEAGE, effects);
			break;
		case MAUSOLEUM:
			effect = new AbilityEffect(Ability.FREEBUILDFROMDISCARD);
			effects.add(effect);
			effectsAndFrequency.put(Frequency.ENDOFTURN, effects);
			break;
		case PYRAMIDS:
			effect = new ValueEffect(Value.VICTORYPOINTS, AffectingEntity.NONE, 5);
			effects.add(effect);
			effectsAndFrequency.put(Frequency.ENDOFGAME, effects);
			break;
		default:
			throw new IllegalArgumentException("Invalid Wonder Type");
		}
	}

	private void getSideBPriority2Effect(HashMap<Frequency, ArrayList<Effect>> effectsAndFrequency) {
		Effect effect;
		ArrayList<Effect> effects = new ArrayList<Effect>();

		switch (this.wonder.getType()) {
		case COLOSSUS:
			effect = new ValueEffect(Value.MILITARY, AffectingEntity.NONE, 1);
			effects.add(effect);
			effect = new ValueEffect(Value.COMMERCE, AffectingEntity.NONE, 4);
			effects.add(effect);
			effectsAndFrequency.put(Frequency.ONCEIMMEDIATE, effects);

			effect = new ValueEffect(Value.VICTORYPOINTS, AffectingEntity.NONE, 4);
			effects = new ArrayList<Effect>();
			effects.add(effect);
			effectsAndFrequency.put(Frequency.ENDOFGAME, effects);
			break;
		case LIGHTHOUSE:
			addAllManufacturedGoods(effects, 1);
			effectsAndFrequency.put(Frequency.EVERYTURN, effects);
			break;
		case TEMPLE:
			effect = new ValueEffect(Value.COMMERCE, AffectingEntity.NONE, 4);
			effects.add(effect);
			effectsAndFrequency.put(Frequency.ONCEIMMEDIATE, effects);

			effects = new ArrayList<Effect>();
			effect = new ValueEffect(Value.VICTORYPOINTS, AffectingEntity.NONE, 3);
			effects.add(effect);
			effectsAndFrequency.put(Frequency.ENDOFGAME, effects);
			break;
		case GARDENS:
			effect = new AbilityEffect(Ability.PLAYSEVENTH);
			effects.add(effect);
			effectsAndFrequency.put(Frequency.SIXTHTURN, effects);
			break;
		case STATUE:
			effect = new ValueEffect(Value.VICTORYPOINTS, AffectingEntity.NONE, 5);
			effects.add(effect);
			effectsAndFrequency.put(Frequency.ENDOFGAME, effects);
			break;
		case MAUSOLEUM:
			effect = new AbilityEffect(Ability.FREEBUILDFROMDISCARD);
			effects.add(effect);
			effectsAndFrequency.put(Frequency.ENDOFTURN, effects);

			effects = new ArrayList<Effect>();
			effect = new ValueEffect(Value.VICTORYPOINTS, AffectingEntity.NONE, 1);
			effects.add(effect);
			effectsAndFrequency.put(Frequency.ENDOFGAME, effects);
			break;
		case PYRAMIDS:
			effect = new ValueEffect(Value.VICTORYPOINTS, AffectingEntity.NONE, 5);
			effects.add(effect);
			effectsAndFrequency.put(Frequency.ENDOFGAME, effects);
			break;
		default:
			throw new IllegalArgumentException("Invalid Wonder Type");
		}
	}

	private void getSideAPriority3Effect(HashMap<Frequency, ArrayList<Effect>> effectsAndFrequency) {
		ArrayList<Effect> effects = new ArrayList<Effect>();
		Effect effect;
		effect = new ValueEffect(Value.VICTORYPOINTS, AffectingEntity.NONE, 7);
		effects.add(effect);
		effectsAndFrequency.put(Frequency.ENDOFGAME, effects);
	}

	private void getSideBPriority3Effect(HashMap<Frequency, ArrayList<Effect>> effectsAndFrequency) {
		Effect effect;
		ArrayList<Effect> effects = new ArrayList<Effect>();

		switch (this.wonder.getType()) {
		case LIGHTHOUSE:
			effect = new ValueEffect(Value.VICTORYPOINTS, AffectingEntity.NONE, 7);
			effects.add(effect);
			effectsAndFrequency.put(Frequency.ENDOFGAME, effects);
			break;
		case TEMPLE:
			effect = new ValueEffect(Value.COMMERCE, AffectingEntity.NONE, 4);
			effects.add(effect);
			effectsAndFrequency.put(Frequency.ONCEIMMEDIATE, effects);

			effects = new ArrayList<Effect>();
			effect = new ValueEffect(Value.VICTORYPOINTS, AffectingEntity.NONE, 5);
			effects.add(effect);
			effectsAndFrequency.put(Frequency.ENDOFGAME, effects);
			break;
		case GARDENS:
			addAllScience(effects, 1);
			effectsAndFrequency.put(Frequency.ENDOFGAME, effects);
			break;
		case STATUE:
			effect = new AbilityEffect(Ability.COPYONENEIGHBORGUILD);
			effects.add(effect);
			effectsAndFrequency.put(Frequency.ENDOFGAME, effects);
			break;
		case MAUSOLEUM:
			effect = new AbilityEffect(Ability.FREEBUILDFROMDISCARD);
			effects.add(effect);
			effectsAndFrequency.put(Frequency.ENDOFTURN, effects);
			break;
		case PYRAMIDS:
			effect = new ValueEffect(Value.VICTORYPOINTS, AffectingEntity.NONE, 5);
			effects.add(effect);
			effectsAndFrequency.put(Frequency.ENDOFGAME, effects);
			break;
		default:
			throw new IllegalArgumentException("Invalid Wonder Type");
		}
	}

	private void getSideBPriority4Effect(HashMap<Frequency, ArrayList<Effect>> effectsAndFrequency) {
		Effect effect;
		ArrayList<Effect> effects = new ArrayList<Effect>();
		
		switch (this.wonder.getType()) {
		case PYRAMIDS:
			effect = new ValueEffect(Value.VICTORYPOINTS, AffectingEntity.NONE, 7);
			effects.add(effect);
			effectsAndFrequency.put(Frequency.ENDOFGAME, effects);
			break;
		default:
			throw new IllegalArgumentException("Invalid Wonder Type");
		}
	}

	private void addAllRawResource(ArrayList<Effect> effects, int amount) {
		Effect effect;
		HashMap<Enum, Integer> resource = new HashMap<Enum, Integer>();
		EntityType entityType = EntityType.RESOURCE;

		resource.put(RawResource.CLAY, amount);
		effect = new EntityEffect(entityType, resource);
		effects.add(effect);

		resource.clear();
		resource.put(RawResource.ORE, amount);
		effect = new EntityEffect(entityType, resource);
		effects.add(effect);

		resource.clear();
		resource.put(RawResource.LUMBER, amount);
		effect = new EntityEffect(entityType, resource);
		effects.add(effect);

		resource.clear();
		resource.put(RawResource.STONE, amount);
		effect = new EntityEffect(entityType, resource);
		effects.add(effect);
	}

	private void addAllManufacturedGoods(ArrayList<Effect> effects, int amount) {
		Effect effect;
		EntityType entityType;
		HashMap<Enum, Integer> good = new HashMap<Enum, Integer>();
		entityType = EntityType.MANUFACTUREDGOOD;

		good.put(Good.LOOM, amount);
		effect = new EntityEffect(entityType, good);
		effects.add(effect);

		good.clear();
		good.put(Good.GLASS, amount);
		effect = new EntityEffect(entityType, good);
		effects.add(effect);

		good.clear();
		good.put(Good.PRESS, amount);
		effect = new EntityEffect(entityType, good);
		effects.add(effect);
	}

	private void addAllScience(ArrayList<Effect> effects, int amount) {
		Effect effect;
		EntityType entityType;
		HashMap<Enum, Integer> science = new HashMap<Enum, Integer>();
		entityType = EntityType.SCIENCE;

		science.put(Science.PROTRACTOR, amount);
		effect = new EntityEffect(entityType, science);
		effects.add(effect);

		science.clear();
		science.put(Science.TABLET, amount);
		effect = new EntityEffect(entityType, science);
		effects.add(effect);

		science.clear();
		science.put(Science.WHEEL, amount);
		effect = new EntityEffect(entityType, science);
		effects.add(effect);
	}
}
