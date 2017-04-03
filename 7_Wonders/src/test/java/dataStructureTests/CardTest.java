package dataStructureTests;

import static org.junit.Assert.*;

import org.junit.Test;

import dataStructures.Card;
import dataStructures.Card.CardType;
import dataStructures.Card.Cost;
import dataStructures.Card.Effect;

public class CardTest {

	@Test
	public void testDefaultCard() {
		Card card = new Card();
		
		assertEquals("Default Card", card.getName());
		assertEquals(3, card.getMinNumPlayers());
		assertEquals(CardType.DEFAULT, card.getCardType());
		assertEquals(Cost.NONE, card.getCostType());
		assertEquals(Effect.NONE, card.getEffectType());
	}
	
	@Test
	public void testRawMaterialCard() {
		Card card = new Card("Lumber Yard", 3, CardType.RAWMATERIAL, Cost.NONE, Effect.RESOURCE);
		
		assertEquals("Lumber Yard", card.getName());
		assertEquals(3, card.getMinNumPlayers());
		assertEquals(CardType.RAWMATERIAL, card.getCardType());
		assertEquals(Cost.NONE, card.getCostType());
		assertEquals(Effect.RESOURCE, card.getEffectType());
	}
	
	@Test
	public void testManufacturedGoodCard() {
		Card card = new Card("Loom", 3, CardType.MANUFACTUREDGOOD, Cost.NONE, Effect.RESOURCE);
		
		assertEquals("Loom", card.getName());
		assertEquals(3, card.getMinNumPlayers());
		assertEquals(CardType.MANUFACTUREDGOOD, card.getCardType());
		assertEquals(Cost.NONE, card.getCostType());
		assertEquals(Effect.RESOURCE, card.getEffectType());
	}

}
