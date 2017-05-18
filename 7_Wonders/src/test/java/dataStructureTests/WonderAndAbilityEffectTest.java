package dataStructureTests;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import constants.GeneralEnums.RawResource;
import dataStructures.gameMaterials.Effect;
import dataStructures.gameMaterials.EntityEffect;
import dataStructures.gameMaterials.Effect.Direction;
import dataStructures.gameMaterials.Effect.EffectType;
import dataStructures.gameMaterials.EntityEffect.EntityType;
import dataStructures.gameMaterials.Level.Frequency;

public class WonderAndAbilityEffectTest {

	@Test
	public void testEntityEffectFrequency() {
		HashMap<Enum, Integer> resourcesAndAmount = new HashMap<Enum, Integer>();
		resourcesAndAmount.put(RawResource.LUMBER, 1);
		Effect effect = new EntityEffect(EffectType.ENTITY, EntityType.RESOURCE, resourcesAndAmount, Frequency.EVERYTURN);

		assertEquals(EffectType.ENTITY, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(EntityType.RESOURCE, ((EntityEffect) effect).getEntityType());
		assertEquals(resourcesAndAmount, ((EntityEffect) effect).getEntities());
		assertEquals(Frequency.EVERYTURN, ((EntityEffect) effect).getFrequency());
	}

}
