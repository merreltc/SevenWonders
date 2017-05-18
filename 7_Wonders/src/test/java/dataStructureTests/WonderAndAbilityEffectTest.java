package dataStructureTests;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

import constants.GeneralEnums.RawResource;
import dataStructures.gameMaterials.AbilityEffect;
import dataStructures.gameMaterials.AbilityEffect.Ability;
import dataStructures.gameMaterials.Effect;
import dataStructures.gameMaterials.Effect.Direction;
import dataStructures.gameMaterials.Effect.EffectType;
import dataStructures.gameMaterials.EntityEffect;
import dataStructures.gameMaterials.EntityEffect.EntityType;
import dataStructures.gameMaterials.Level.Frequency;
import dataStructures.gameMaterials.MultiValueEffect;
import dataStructures.gameMaterials.ValueEffect;
import dataStructures.gameMaterials.ValueEffect.AffectingEntity;
import dataStructures.gameMaterials.ValueEffect.Value;
import dataStructures.gameMaterials.ValueEffect.ValueType;

public class WonderAndAbilityEffectTest {

	@Test
	public void testEntityEffectFrequency() {
		HashMap<Enum, Integer> resourcesAndAmount = new HashMap<Enum, Integer>();
		resourcesAndAmount.put(RawResource.LUMBER, 1);
		Effect effect = new EntityEffect(EffectType.ENTITY, EntityType.RESOURCE, resourcesAndAmount,
				Frequency.EVERYTURN);
		assertEquals(Frequency.EVERYTURN, ((EntityEffect) effect).getFrequency());
	}

	@Test
	public void testValueEffectFrequency() {
		Effect effect = new ValueEffect(EffectType.VALUE, Value.VICTORYPOINTS, AffectingEntity.NONE, 2,
				Frequency.EVERYTURN);
		assertEquals(Frequency.EVERYTURN, ((ValueEffect) effect).getFrequency());

		effect = new ValueEffect(EffectType.VALUE, Value.VICTORYPOINTS, AffectingEntity.NONE, Direction.SELF,
				Frequency.EVERYTURN);
		assertEquals(Frequency.EVERYTURN, ((ValueEffect) effect).getFrequency());

		effect = new ValueEffect(EffectType.VALUE, Value.VICTORYPOINTS, AffectingEntity.NONE, Direction.SELF, 2,
				Frequency.EVERYTURN);
		assertEquals(Frequency.EVERYTURN, ((ValueEffect) effect).getFrequency());

		HashMap<Enum, Integer> affectingEntities = new HashMap<Enum, Integer>();
		affectingEntities.put(AffectingEntity.RAWRESOURCES, 1);
		affectingEntities.put(AffectingEntity.MANUFACTUREDGOODS, 1);
		affectingEntities.put(AffectingEntity.GUILD, 1);
		effect = new ValueEffect(EffectType.VALUE, Value.VICTORYPOINTS, affectingEntities, Frequency.EVERYTURN);
		assertEquals(Frequency.EVERYTURN, ((ValueEffect) effect).getFrequency());
	}

	@Test
	public void testMultiValueEffectFrequency() {
		HashMap<Enum, Integer> valuesAndAmounts = new HashMap<Enum, Integer>();
		valuesAndAmounts.put(ValueType.CONFLICTTOKEN, -1);
		valuesAndAmounts.put(ValueType.VICTORYPOINT, 1);
		Effect effect = new MultiValueEffect(EffectType.MULTIVALUE, Value.GUILD, AffectingEntity.NONE,
				Direction.NEIGHBORS, valuesAndAmounts, Frequency.EVERYTURN);

		assertEquals(Frequency.EVERYTURN, ((MultiValueEffect) effect).getFrequency());
	}
	//
	// effectType": "ABILITY",
	// "ability": "PLAYSEVENTH",
	// "frequency": "SIXTHTURN"

	// "effectType": "ABILITY",
	// "ability": "FREEBUILD",
	// "frequency": "ONCEAGE"

	// "effectType": "ABILITY",
	// "ability":"COPYONENEIGHBORGUILD",
	// "frequency": "ENDOFGAME"

	// "effectType": "ABILITY",
	// "ability": "FREEBUILDFROMDISCARD",
	// "frequency": "ENDOFTURN"
	@Test
	public void testAbilityEffect() {
		AbilityEffect effect = new AbilityEffect(EffectType.ABILITY, Ability.PLAYSEVENTH, Frequency.SIXTHTURN);
		assertEquals(EffectType.ABILITY, effect.getEffectType());
		assertEquals(Ability.PLAYSEVENTH, effect.getAbility());
		assertEquals(Frequency.SIXTHTURN, effect.getFrequency());
	}

	@Test
	public void testAllFrequencies() {
		Effect effect = new ValueEffect(EffectType.VALUE, Value.VICTORYPOINTS, AffectingEntity.NONE, 2,
				Frequency.ENDOFTURN);
		assertEquals(Frequency.ENDOFTURN, ((ValueEffect) effect).getFrequency());

		effect = new ValueEffect(EffectType.VALUE, Value.VICTORYPOINTS, AffectingEntity.NONE, 2, Frequency.EVERYTURN);
		assertEquals(Frequency.EVERYTURN, ((ValueEffect) effect).getFrequency());

		effect = new ValueEffect(EffectType.VALUE, Value.VICTORYPOINTS, AffectingEntity.NONE, 2, Frequency.SIXTHTURN);
		assertEquals(Frequency.SIXTHTURN, ((ValueEffect) effect).getFrequency());

		effect = new ValueEffect(EffectType.VALUE, Value.VICTORYPOINTS, AffectingEntity.NONE, 2,
				Frequency.ONCEIMMEDIATE);
		assertEquals(Frequency.ONCEIMMEDIATE, ((ValueEffect) effect).getFrequency());

		effect = new ValueEffect(EffectType.VALUE, Value.VICTORYPOINTS, AffectingEntity.NONE, 2, Frequency.ONCEAGE);
		assertEquals(Frequency.ONCEAGE, ((ValueEffect) effect).getFrequency());

		effect = new ValueEffect(EffectType.VALUE, Value.VICTORYPOINTS, AffectingEntity.NONE, 2, Frequency.ENDOFGAME);
		assertEquals(Frequency.ENDOFGAME, ((ValueEffect) effect).getFrequency());
	}

}
