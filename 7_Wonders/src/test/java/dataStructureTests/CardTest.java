package dataStructureTests;

import static org.junit.Assert.*;

import org.junit.Test;

public class CardTest {

	@Test
	public void testDefaultCard() {
		Card card = new Card();
		
		assertEquals("Default Card", card.getName());
		assertEquals(3, card.getMinNumPlayers());
		assertEquals(Cost.NONE, card.getCostType());
		assertEquals(Effect.NONE, card.getEffectType());
	}

}
