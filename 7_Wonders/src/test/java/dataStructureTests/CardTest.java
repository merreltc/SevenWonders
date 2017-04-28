package dataStructureTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.easymock.EasyMock;
import org.junit.Test;

import dataStructures.Card;
import dataStructures.Card.CardType;
import dataStructures.Effect.EffectType;
import dataStructures.EntityEffect.Resource;
import dataStructures.Cost;
import dataStructures.Cost.CostType;
import dataStructures.Effect;

public class CardTest {

	@Test
	public void testRawMaterialCard() {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(4);

		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);

		HashMap<Enum, Integer> expected = new HashMap<Enum, Integer>();
		expected.put(Resource.LUMBER, 2);

		EasyMock.expect(cost.getType()).andReturn(CostType.NONE);

		HashMap<Enum, Integer> expectedEntities = new HashMap<Enum, Integer>();
		expectedEntities.put(Resource.LUMBER, 2);

		EasyMock.expect(effect.getEffectType()).andReturn(Effect.EffectType.ENTITY);

		EasyMock.replay(cost, effect);

		Card card = new Card("Lumber Yard", frequency, CardType.RAWMATERIAL, cost, effect, "None", "None");

		assertEquals("Lumber Yard", card.getName());
		assertEquals(frequency, card.getFrequencyByNumPlayers());
		assertEquals(CardType.RAWMATERIAL, card.getCardType());
		assertEquals(CostType.NONE, card.getCostType());
		assertEquals(Effect.EffectType.ENTITY, card.getEffectType());

		EasyMock.verify(cost, effect);
	}

	@Test
	public void testManufacturedGoodCard() {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(6);

		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);

		HashMap<Enum, Integer> expected = new HashMap<Enum, Integer>();
		expected.put(Resource.LUMBER, 2);

		EasyMock.expect(cost.getType()).andReturn(CostType.NONE);
		EasyMock.expect(effect.getEffectType()).andReturn(Effect.EffectType.ENTITY);

		EasyMock.replay(cost, effect);

		Card card = new Card("Loom", frequency, CardType.MANUFACTUREDGOOD, cost, effect, "None", "None");

		assertEquals("Loom", card.getName());
		assertEquals(frequency, card.getFrequencyByNumPlayers());
		assertEquals(CardType.MANUFACTUREDGOOD, card.getCardType());
		assertEquals(CostType.NONE, card.getCostType());
		assertEquals(EffectType.ENTITY, card.getEffectType());

		EasyMock.verify(cost, effect);
	}

	@Test
	public void testCommercialStructureCard() {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(4);

		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);

		HashMap<Enum, Integer> expected = new HashMap<Enum, Integer>();
		expected.put(Resource.LUMBER, 2);

		EasyMock.expect(cost.getType()).andReturn(CostType.RESOURCE);
		EasyMock.expect(effect.getEffectType()).andReturn(Effect.EffectType.MULTIVALUE);

		EasyMock.replay(cost, effect);
		Card card = new Card("Haven", frequency, CardType.COMMERCIALSTRUCTURE, cost, effect, "None", "None");

		assertEquals("Haven", card.getName());
		assertEquals(frequency, card.getFrequencyByNumPlayers());
		assertEquals(CardType.COMMERCIALSTRUCTURE, card.getCardType());
		assertEquals(CostType.RESOURCE, card.getCostType());
		assertEquals(Effect.EffectType.MULTIVALUE, card.getEffectType());

		EasyMock.verify(cost, effect);
	}

	@Test
	public void testCommercialStructureCardCommercialEffect() {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(7);

		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);

		HashMap<Enum, Integer> expected = new HashMap<Enum, Integer>();
		expected.put(Resource.LUMBER, 2);

		EasyMock.expect(cost.getType()).andReturn(CostType.RESOURCE);
		EasyMock.expect(effect.getEffectType()).andReturn(Effect.EffectType.VALUE);

		EasyMock.replay(cost, effect);

		Card card = new Card("East Trading Post", frequency, CardType.COMMERCIALSTRUCTURE, cost, effect, "None", "None");

		assertEquals("East Trading Post", card.getName());
		assertEquals(frequency, card.getFrequencyByNumPlayers());
		assertEquals(CardType.COMMERCIALSTRUCTURE, card.getCardType());
		assertEquals(CostType.RESOURCE, card.getCostType());
		assertEquals(Effect.EffectType.VALUE, card.getEffectType());

		EasyMock.verify(cost, effect);
	}

	@Test
	public void testScientificStructureCard() {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(5);

		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);

		HashMap<Enum, Integer> expected = new HashMap<Enum, Integer>();
		expected.put(Resource.LUMBER, 2);

		EasyMock.expect(cost.getType()).andReturn(CostType.RESOURCE);
		EasyMock.expect(effect.getEffectType()).andReturn(Effect.EffectType.ENTITY);

		EasyMock.replay(cost, effect);

		Card card = new Card("Apothecary", frequency, CardType.SCIENTIFICSTRUCTURE, cost, effect, "None", "None");

		assertEquals("Apothecary", card.getName());
		assertEquals(frequency, card.getFrequencyByNumPlayers());
		assertEquals(CardType.SCIENTIFICSTRUCTURE, card.getCardType());
		assertEquals(CostType.RESOURCE, card.getCostType());
		assertEquals(Effect.EffectType.ENTITY, card.getEffectType());

		EasyMock.verify(cost, effect);
	}

	@Test
	public void testCivilianStructureCard() {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(4);

		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);

		HashMap<Enum, Integer> expected = new HashMap<Enum, Integer>();
		expected.put(Resource.LUMBER, 2);

		EasyMock.expect(cost.getType()).andReturn(CostType.RESOURCE);
		EasyMock.expect(effect.getEffectType()).andReturn(Effect.EffectType.VALUE);

		EasyMock.replay(cost, effect);

		Card card = new Card("Haven", frequency, CardType.CIVILIANSTRUCTURE, cost, effect, "None", "None");

		assertEquals("Haven", card.getName());
		assertEquals(frequency, card.getFrequencyByNumPlayers());
		assertEquals(CardType.CIVILIANSTRUCTURE, card.getCardType());
		assertEquals(CostType.RESOURCE, card.getCostType());
		assertEquals(Effect.EffectType.VALUE, card.getEffectType());

		EasyMock.verify(cost, effect);
	}

	@Test
	public void testMilitaryStructureCard() {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(7);

		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);

		HashMap<Enum, Integer> expected = new HashMap<Enum, Integer>();
		expected.put(Resource.LUMBER, 2);

		EasyMock.expect(cost.getType()).andReturn(CostType.RESOURCE);
		EasyMock.expect(effect.getEffectType()).andReturn(Effect.EffectType.VALUE);

		EasyMock.replay(cost, effect);

		Card card = new Card("Stockade", frequency, CardType.MILITARYSTRUCTURE, cost, effect, "None", "None");

		assertEquals("Stockade", card.getName());
		assertEquals(frequency, card.getFrequencyByNumPlayers());
		assertEquals(CardType.MILITARYSTRUCTURE, card.getCardType());
		assertEquals(CostType.RESOURCE, card.getCostType());
		assertEquals(Effect.EffectType.VALUE, card.getEffectType());

		EasyMock.verify(cost, effect);
	}

	@Test
	public void testGuildCard() {
		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);

		HashMap<Enum, Integer> expectedCost = new HashMap<Enum, Integer>();
		expectedCost.put(Resource.LUMBER, 2);

		EasyMock.expect(cost.getType()).andReturn(CostType.RESOURCE);
		EasyMock.expect(effect.getEffectType()).andReturn(Effect.EffectType.VALUE);

		EasyMock.replay(cost, effect);

		Card card = new Card("Workers Guild", CardType.GUILD, cost, effect);

		assertEquals("Workers Guild", card.getName());
		assertEquals(CardType.GUILD, card.getCardType());
		assertEquals(CostType.RESOURCE, card.getCostType());
		assertEquals(Effect.EffectType.VALUE, card.getEffectType());

		EasyMock.verify(cost, effect);
	}

	@Test
	public void testGetEffect() {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(6);

		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);

		HashMap<Enum, Integer> expected = new HashMap<Enum, Integer>();
		expected.put(Resource.LUMBER, 2);

		EasyMock.replay(cost, effect);

		Card card = new Card("Tree Farm", frequency, CardType.RAWMATERIAL, cost, effect, "None", "None");

		assertEquals(effect, card.getEffect());

		EasyMock.verify(cost, effect);
	}

	@Test
	public void testGetCost() {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(6);

		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);

		HashMap<Enum, Integer> expected = new HashMap<Enum, Integer>();
		expected.put(Resource.LUMBER, 2);

		EasyMock.expect(cost.getCost()).andReturn(expected);
		EasyMock.replay(cost);

		Card card = new Card("Tree Farm", frequency, CardType.RAWMATERIAL, cost, effect, "None", "None");

		assertEquals(expected, card.getCost());

		EasyMock.verify(cost);
	}

	// Start of previous structure tests
	@Test
	public void testGetPreviousStructureNone() {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(6);

		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);

		Card card = new Card("Tree Farm", frequency, CardType.RAWMATERIAL, cost, effect, "None", "None");

		assertEquals("None", card.getPreviousStructureName());
	}

	@Test
	public void testGetPreviousStructureOfStatue() {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(7);

		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);

		Card card = new Card("Statue", frequency, CardType.RAWMATERIAL, cost, effect, "Theater", "None");

		assertEquals("Theater", card.getPreviousStructureName());
	}

	@Test
	public void testGetPreviousStructureOfForum() {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(6);
		frequency.add(7);

		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);

		Card card = new Card("Forum", frequency, CardType.RAWMATERIAL, cost, effect, "Trading Post", "None");

		assertEquals("Trading Post", card.getPreviousStructureName());
	}

	@Test
	public void testGetNextStructureOfArena() {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(5);
		frequency.add(7);

		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);

		Card card = new Card("Arena", frequency, CardType.RAWMATERIAL, cost, effect, "Dispensary", "None");

		assertEquals("Dispensary", card.getPreviousStructureName());
		assertEquals("None", card.getNextStructureName());
	}

	@Test
	public void testGetNextStructureOfStatue() {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(7);

		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);

		Card card = new Card("Statue", frequency, CardType.RAWMATERIAL, cost, effect, "Theater", "Gardens");

		assertEquals("Theater", card.getPreviousStructureName());
		assertEquals("Gardens", card.getNextStructureName());
	}

	@Test
	public void testGetNextStructureOfForum() {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(6);
		frequency.add(7);

		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);

		Card card = new Card("Forum", frequency, CardType.RAWMATERIAL, cost, effect, "Trading Post", "Haven");

		assertEquals("Trading Post", card.getPreviousStructureName());
		assertEquals("Haven", card.getNextStructureName());
	}
	
	@Test
	public void testToStringEastTrading(){
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(6);
		frequency.add(7);

		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);

		EasyMock.expect(cost.getType()).andReturn(CostType.RESOURCE);
		EasyMock.expect(effect.getEffectType()).andReturn(Effect.EffectType.VALUE);

		EasyMock.replay(cost, effect);

		Card card = new Card("East Trading Post", frequency, CardType.COMMERCIALSTRUCTURE, cost, effect, "None", "None");
		String expected = "name: East Trading Post" + System.lineSeparator()
						+ "minFrequencyByNumPlayers: 3" + System.lineSeparator()
						+ "costType: RESOURCE" + System.lineSeparator()
						+ "effectType: VALUE";
		
		assertEquals(expected, card.toString());
		
		EasyMock.verify(cost, effect);
	}
}
