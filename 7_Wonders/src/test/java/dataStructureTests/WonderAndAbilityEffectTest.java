package dataStructureTests;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

import constants.GeneralEnums.RawResource;
import dataStructures.gameMaterials.Effect;
import dataStructures.gameMaterials.Effect.Direction;
import dataStructures.gameMaterials.Effect.EffectType;
import dataStructures.gameMaterials.EntityEffect;
import dataStructures.gameMaterials.EntityEffect.EntityType;
import dataStructures.gameMaterials.Level.Frequency;
import dataStructures.gameMaterials.ValueEffect;
import dataStructures.gameMaterials.ValueEffect.AffectingEntity;
import dataStructures.gameMaterials.ValueEffect.Value;

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

}
