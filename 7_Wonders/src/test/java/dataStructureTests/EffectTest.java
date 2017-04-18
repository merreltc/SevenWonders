package dataStructureTests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dataStructures.Effect;
import dataStructures.Effect.AffectedEntityType;
import dataStructures.Effect.Direction;
import dataStructures.Effect.EffectType;

public class EffectTest {

	@Test
	public void testDefaultEffect() {
		Effect effect = new Effect();
		
		assertEquals(EffectType.NONE, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(AffectedEntityType.NONE, effect.getAffectedEntityType());
	}

}
