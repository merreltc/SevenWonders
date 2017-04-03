package dataStructureTests;

import static org.junit.Assert.*;

import org.junit.Test;

import dataStructures.Card;
import dataStructures.Card.Cost;
import dataStructures.Card.Effect;

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
