package dataStructureTests;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import dataStructures.Card;
import dataStructures.Card.CardType;
import dataStructures.Card.Cost;
import dataStructures.Card.Effect;
import dataStructures.Card.ResourceType;

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

	@Test
	public void testCommercialStructureCard() {
		Card card = new Card("Haven", 3, CardType.COMMERCIALSTRUCTURE, Cost.RESOURCE, Effect.MULTI);

		assertEquals("Haven", card.getName());
		assertEquals(3, card.getMinNumPlayers());
		assertEquals(CardType.COMMERCIALSTRUCTURE, card.getCardType());
		assertEquals(Cost.RESOURCE, card.getCostType());
		assertEquals(Effect.MULTI, card.getEffectType());
	}

	@Test
	public void testCommercialStructureCardCommercialEffect() {
		Card card = new Card("East Trading Post", 3, CardType.COMMERCIALSTRUCTURE, Cost.RESOURCE, Effect.COMMERCIAL);

		assertEquals("East Trading Post", card.getName());
		assertEquals(3, card.getMinNumPlayers());
		assertEquals(CardType.COMMERCIALSTRUCTURE, card.getCardType());
		assertEquals(Cost.RESOURCE, card.getCostType());
		assertEquals(Effect.COMMERCIAL, card.getEffectType());
	}

	@Test
	public void testScientificStructureCard() {
		Card card = new Card("Apothecary", 3, CardType.SCIENTIFICSTRUCTURE, Cost.RESOURCE, Effect.SCIENCE);

		assertEquals("Apothecary", card.getName());
		assertEquals(3, card.getMinNumPlayers());
		assertEquals(CardType.SCIENTIFICSTRUCTURE, card.getCardType());
		assertEquals(Cost.RESOURCE, card.getCostType());
		assertEquals(Effect.SCIENCE, card.getEffectType());
	}

	@Test
	public void testCivilianStructureCard() {
		Card card = new Card("Haven", 3, CardType.CIVILIANSTRUCTURE, Cost.RESOURCE, Effect.VICTORYPOINTS);

		assertEquals("Haven", card.getName());
		assertEquals(3, card.getMinNumPlayers());
		assertEquals(CardType.CIVILIANSTRUCTURE, card.getCardType());
		assertEquals(Cost.RESOURCE, card.getCostType());
		assertEquals(Effect.VICTORYPOINTS, card.getEffectType());
	}

	@Test
	public void testMilitaryStructureCard() {
		Card card = new Card("Stockade", 3, CardType.MILITARYSTRUCTURE, Cost.RESOURCE, Effect.CONFLICTTOKENS);

		assertEquals("Stockade", card.getName());
		assertEquals(3, card.getMinNumPlayers());
		assertEquals(CardType.MILITARYSTRUCTURE, card.getCardType());
		assertEquals(Cost.RESOURCE, card.getCostType());
		assertEquals(Effect.CONFLICTTOKENS, card.getEffectType());
	}

	@Test
	public void testGuildCard() {
		Card card = new Card("Workers Guild", 3, CardType.GUILD, Cost.RESOURCE, Effect.VICTORYPOINTS);

		assertEquals("Workers Guild", card.getName());
		assertEquals(3, card.getMinNumPlayers());
		assertEquals(CardType.GUILD, card.getCardType());
		assertEquals(Cost.RESOURCE, card.getCostType());
		assertEquals(Effect.VICTORYPOINTS, card.getEffectType());
	}

	@Test
	public void testCoinCostAndMinNumPlayersCard() {
		Card card = new Card("Tree Farm", 6, CardType.RAWMATERIAL, Cost.COIN, Effect.RESOURCE);

		assertEquals("Tree Farm", card.getName());
		assertEquals(6, card.getMinNumPlayers());
		assertEquals(CardType.RAWMATERIAL, card.getCardType());
		assertEquals(Cost.COIN, card.getCostType());
		assertEquals(Effect.RESOURCE, card.getEffectType());
	}

	@Test
	public void testGetCoinCost() {
		Card card = new Card("Tree Farm", 6, CardType.RAWMATERIAL, Cost.COIN, Effect.RESOURCE);
		Card card2 = new Card("Lumber Yard", 3, CardType.RAWMATERIAL, Cost.NONE, Effect.RESOURCE);
		Card card3 = new Card("Haven", 3, CardType.CIVILIANSTRUCTURE, Cost.RESOURCE, Effect.VICTORYPOINTS);

		assertEquals(1, card.getCoinCost());
		assertEquals(0, card2.getCoinCost());
		assertEquals(0, card3.getCoinCost());
	}
	

	@Test
	public void testGetResourceCost() {
		Card card = new Card("Tree Farm", 6, CardType.RAWMATERIAL, Cost.COIN, Effect.RESOURCE);
		Card card2 = new Card("Lumber Yard", 3, CardType.RAWMATERIAL, Cost.NONE, Effect.RESOURCE);
		Card card3 = new Card("Haven", 3, CardType.CIVILIANSTRUCTURE, Cost.RESOURCE, Effect.VICTORYPOINTS);

		assertEquals(0, card.getResourceCost().size());
		assertEquals(0, card2.getResourceCost().size());
		
		HashMap<ResourceType, Integer> actualCost = card3.getResourceCost();
		HashMap<ResourceType, Integer> expectedCost = new HashMap<ResourceType, Integer>();
		
		expectedCost.put(ResourceType.LOOM, 1);
		expectedCost.put(ResourceType.ORE, 1);
		expectedCost.put(ResourceType.LUMBER, 1);
		
		assertEquals(expectedCost.get(ResourceType.LOOM), actualCost.get(ResourceType.LOOM));
		assertEquals(expectedCost.get(ResourceType.ORE), actualCost.get(ResourceType.ORE));
		assertEquals(expectedCost.get(ResourceType.LUMBER), actualCost.get(ResourceType.LUMBER));
	}
	
	@Test
	public void testGetEffectOneTypeResource() {
		Card card = new Card("Tree Farm", 6, CardType.RAWMATERIAL, Cost.COIN, Effect.RESOURCE);
	
		HashMap<ResourceType, Integer> actualEffect = card.getEffect();
		HashMap<ResourceType, Integer> expectedEffect = new HashMap<ResourceType, Integer>();
		
		expectedEffect.put(ResourceType.LUMBER, 2);
	
		assertEquals(expectedEffect.get(ResourceType.LUMBER), actualEffect.get(ResourceType.LUMBER));
	}

	//Start of previous structure tests
	@Test
	public void testGetPreviousStructureNone (){
		Card card = new Card("Tree Farm", 6, CardType.RAWMATERIAL, Cost.COIN, Effect.RESOURCE);
		
		assertEquals("None", card.getPreviousStructureName());
	}
}
