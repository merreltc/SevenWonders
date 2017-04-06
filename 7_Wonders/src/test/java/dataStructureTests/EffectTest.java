package dataStructureTests;

import static org.junit.Assert.*;

import org.junit.Test;

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
		assertEquals(EffectedType.NONE, effect.getEffectedType());
		
		assertEquals(0, effect.getCommerceResource().size());
		assertEquals(0, effect.getCommerceValue());
		assertEquals(Direction.NONE, effect.getCommerceDirection());
	}

}
