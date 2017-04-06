package dataStructureTests;

import static org.junit.Assert.*;

import org.junit.Test;

import dataStructures.Effect;
import dataStructures.Effect.Direction;
import dataStructures.Effect.EffectType;
import dataStructures.Effect.AffectedEntityType;
import dataStructures.Effect.ScienceType;

public class EffectTest {

	@Test
	public void testDefaultEffect() {
		Effect effect = new Effect();
		
		assertEquals(EffectType.NONE, effect.getEffectType());
		assertEquals(0, effect.getResources().size());
		assertEquals(ScienceType.NONE, effect.getScienceType());
		assertEquals(0, effect.getConflictToken());
		
		// Victory points can be stand-alone or attributed to an EffectedType
		assertEquals(0, effect.getVictoryPoints());
		assertEquals(0, effect.getCoinTotal());
		assertEquals(AffectedEntityType.NONE, effect.getAffectedEntityType());
		
		assertEquals(0, effect.getCommerceResources().size());
		assertEquals(0, effect.getCommerceValue());
		assertEquals(Direction.NONE, effect.getCommerceDirection());
	}

}
