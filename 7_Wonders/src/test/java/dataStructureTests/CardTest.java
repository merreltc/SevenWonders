package dataStructureTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import dataStructures.Card;
import dataStructures.Card.CardType;
import dataStructures.Card.Cost;
import dataStructures.Card.Effect;
import dataStructures.Card.ResourceType;

public class CardTest {

	@Test
	public void testRawMaterialCard() {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(4);
		
		Card card = new Card("Lumber Yard", frequency, CardType.RAWMATERIAL, Cost.NONE, Effect.RESOURCE);
		
		assertEquals("Lumber Yard", card.getName());
		assertEquals(frequency, card.getFrequencyByNumPlayers());
		assertEquals(CardType.RAWMATERIAL, card.getCardType());
		assertEquals(Cost.NONE, card.getCostType());
		assertEquals(Effect.RESOURCE, card.getEffectType());
	}

	@Test
	public void testManufacturedGoodCard() {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(6);
		
		Card card = new Card("Loom", frequency, CardType.MANUFACTUREDGOOD, Cost.NONE, Effect.RESOURCE);
		
		assertEquals("Loom", card.getName());
		assertEquals(frequency, card.getFrequencyByNumPlayers());
		assertEquals(CardType.MANUFACTUREDGOOD, card.getCardType());
		assertEquals(Cost.NONE, card.getCostType());
		assertEquals(Effect.RESOURCE, card.getEffectType());
	}

	@Test
	public void testCommercialStructureCard() {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(4);
		
		Card card = new Card("Haven", frequency, CardType.COMMERCIALSTRUCTURE, Cost.RESOURCE, Effect.MULTI);

		assertEquals("Haven", card.getName());
		assertEquals(frequency, card.getFrequencyByNumPlayers());
		assertEquals(CardType.COMMERCIALSTRUCTURE, card.getCardType());
		assertEquals(Cost.RESOURCE, card.getCostType());
		assertEquals(Effect.MULTI, card.getEffectType());
	}

	@Test
	public void testCommercialStructureCardCommercialEffect() {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(7);
		
		Card card = new Card("East Trading Post", frequency, CardType.COMMERCIALSTRUCTURE, Cost.RESOURCE, Effect.COMMERCIAL);

		assertEquals("East Trading Post", card.getName());
		assertEquals(frequency, card.getFrequencyByNumPlayers());
		assertEquals(CardType.COMMERCIALSTRUCTURE, card.getCardType());
		assertEquals(Cost.RESOURCE, card.getCostType());
		assertEquals(Effect.COMMERCIAL, card.getEffectType());
	}

	@Test
	public void testScientificStructureCard() {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(5);
		
		Card card = new Card("Apothecary", frequency, CardType.SCIENTIFICSTRUCTURE, Cost.RESOURCE, Effect.SCIENCE);

		assertEquals("Apothecary", card.getName());
		assertEquals(frequency, card.getFrequencyByNumPlayers());
		assertEquals(CardType.SCIENTIFICSTRUCTURE, card.getCardType());
		assertEquals(Cost.RESOURCE, card.getCostType());
		assertEquals(Effect.SCIENCE, card.getEffectType());
	}

	@Test
	public void testCivilianStructureCard() {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(4);
		
		Card card = new Card("Haven", frequency, CardType.CIVILIANSTRUCTURE, Cost.RESOURCE, Effect.VICTORYPOINTS);

		assertEquals("Haven", card.getName());
		assertEquals(frequency, card.getFrequencyByNumPlayers());
		assertEquals(CardType.CIVILIANSTRUCTURE, card.getCardType());
		assertEquals(Cost.RESOURCE, card.getCostType());
		assertEquals(Effect.VICTORYPOINTS, card.getEffectType());
	}

	@Test
	public void testMilitaryStructureCard() {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(7);
		
		Card card = new Card("Stockade", frequency, CardType.MILITARYSTRUCTURE, Cost.RESOURCE, Effect.CONFLICTTOKENS);

		assertEquals("Stockade", card.getName());
		assertEquals(frequency, card.getFrequencyByNumPlayers());
		assertEquals(CardType.MILITARYSTRUCTURE, card.getCardType());
		assertEquals(Cost.RESOURCE, card.getCostType());
		assertEquals(Effect.CONFLICTTOKENS, card.getEffectType());
	}

	@Test
	public void testGuildCard() {
		Card card = new Card("Workers Guild", CardType.GUILD, Cost.RESOURCE, Effect.VICTORYPOINTS);

		assertEquals("Workers Guild", card.getName());
		assertEquals(CardType.GUILD, card.getCardType());
		assertEquals(Cost.RESOURCE, card.getCostType());
		assertEquals(Effect.VICTORYPOINTS, card.getEffectType());
	}

	@Test
	public void testCoinCostAndMinNumPlayersCard() {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(6);
		
		Card card = new Card("Tree Farm", frequency, CardType.RAWMATERIAL, Cost.COIN, Effect.RESOURCE);

		assertEquals("Tree Farm", card.getName());
		assertEquals(frequency, card.getFrequencyByNumPlayers());
		assertEquals(CardType.RAWMATERIAL, card.getCardType());
		assertEquals(Cost.COIN, card.getCostType());
		assertEquals(Effect.RESOURCE, card.getEffectType());
	}

	@Test
	public void testGetCoinCost() {
		ArrayList<Integer> frequencyTree = new ArrayList<Integer>();
		frequencyTree.add(6);
		
		ArrayList<Integer> frequencyLumber = new ArrayList<Integer>();
		frequencyLumber.add(3);
		frequencyLumber.add(4);
		
		ArrayList<Integer> frequencyHaven = new ArrayList<Integer>();
		frequencyHaven.add(3);
		frequencyHaven.add(4);
		
		Card card = new Card("Tree Farm", frequencyTree, CardType.RAWMATERIAL, Cost.COIN, Effect.RESOURCE);
		Card card2 = new Card("Lumber Yard", frequencyLumber, CardType.RAWMATERIAL, Cost.NONE, Effect.RESOURCE);
		Card card3 = new Card("Haven", frequencyHaven, CardType.CIVILIANSTRUCTURE, Cost.RESOURCE, Effect.VICTORYPOINTS);

		assertEquals(1, card.getCoinCost());
		assertEquals(0, card2.getCoinCost());
		assertEquals(0, card3.getCoinCost());
	}
	

	@Test
	public void testGetResourceCost() {
		ArrayList<Integer> frequencyTree = new ArrayList<Integer>();
		frequencyTree.add(6);
		
		ArrayList<Integer> frequencyLumber = new ArrayList<Integer>();
		frequencyLumber.add(3);
		frequencyLumber.add(4);
		
		ArrayList<Integer> frequencyHaven = new ArrayList<Integer>();
		frequencyHaven.add(3);
		frequencyHaven.add(4);
		
		Card card = new Card("Tree Farm", frequencyTree, CardType.RAWMATERIAL, Cost.COIN, Effect.RESOURCE);
		Card card2 = new Card("Lumber Yard", frequencyLumber, CardType.RAWMATERIAL, Cost.NONE, Effect.RESOURCE);
		Card card3 = new Card("Haven", frequencyHaven, CardType.CIVILIANSTRUCTURE, Cost.RESOURCE, Effect.VICTORYPOINTS);

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
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(6);
		
		Card card = new Card("Tree Farm", frequency, CardType.RAWMATERIAL, Cost.COIN, Effect.RESOURCE);
	
		HashMap<ResourceType, Integer> actualEffect = card.getEffect();
		HashMap<ResourceType, Integer> expectedEffect = new HashMap<ResourceType, Integer>();
		
		expectedEffect.put(ResourceType.LUMBER, 2);
	
		assertEquals(expectedEffect.get(ResourceType.LUMBER), actualEffect.get(ResourceType.LUMBER));
	}

	//Start of previous structure tests
	@Test
	public void testGetPreviousStructureNone() {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(6);
		
		Card card = new Card("Tree Farm", frequency, CardType.RAWMATERIAL, Cost.COIN, Effect.RESOURCE);

		assertEquals("None", card.getPreviousStructureName());
	}
	
	@Test
	public void testGetPreviousStructureOfStatue() {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(7);
		
		Card card = new Card("Statue", frequency, CardType.RAWMATERIAL, Cost.NONE, Effect.VICTORYPOINTS, "Theater", "None");
		
		assertEquals("Theater", card.getPreviousStructureName());
	}
	
	@Test
	public void testGetPreviousStructureOfForum() {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(6);
		frequency.add(7);
		
		Card card = new Card("Forum", frequency, CardType.RAWMATERIAL, Cost.NONE, Effect.VICTORYPOINTS, "Trading Post", "None");
		
		assertEquals("Trading Post", card.getPreviousStructureName());
	}
	
	@Test
	public void testGetNextStructureOfArena() {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(5);
		frequency.add(7);
		
		Card card = new Card("Arena", frequency, CardType.RAWMATERIAL, Cost.NONE, Effect.VICTORYPOINTS, "Dispensary", "None");
		
		assertEquals("Dispensary", card.getPreviousStructureName());
		assertEquals("None", card.getNextStructureName());
	}
	
	@Test
	public void testGetNextStructureOfStatue() {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(7);
		
		Card card = new Card("Statue", frequency, CardType.RAWMATERIAL, Cost.NONE, Effect.VICTORYPOINTS, "Theater", "Gardens");
		
		assertEquals("Theater", card.getPreviousStructureName());
		assertEquals("Gardens", card.getNextStructureName());
	}
	
	@Test
	public void testGetNextStructureOfForum() {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(6);
		frequency.add(7);
		
		Card card = new Card("Forum", frequency, CardType.RAWMATERIAL, Cost.NONE, Effect.VICTORYPOINTS, "Trading Post", "Haven");
		
		assertEquals("Trading Post", card.getPreviousStructureName());
		assertEquals("Haven", card.getNextStructureName());
	}
}
