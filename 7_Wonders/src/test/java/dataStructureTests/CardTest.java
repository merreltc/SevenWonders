package dataStructureTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.easymock.EasyMock;
import org.junit.Test;

import constants.GeneralEnums.CostType;
import constants.GeneralEnums.Good;
import constants.GeneralEnums.RawResource;
import dataStructures.gameMaterials.Card;
import dataStructures.gameMaterials.Card.CardType;
import dataStructures.gameMaterials.Cost;
import dataStructures.gameMaterials.Effect;
import dataStructures.gameMaterials.Effect.EffectType;
import dataStructures.gameMaterials.EntityEffect;
import dataStructures.gameMaterials.EntityEffect.EntityType;
import dataStructures.gameMaterials.ValueEffect;
import dataStructures.gameMaterials.ValueEffect.AffectingEntity;
import dataStructures.gameMaterials.ValueEffect.Value;

public class CardTest {
	// BEGIN GENERATED CODE

	@Test
	public void testRawMaterialCardLumber() {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(4);

		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);

		EasyMock.expect(cost.getType()).andReturn(CostType.NONE);
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
	public void testRawMaterialCardClay() {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(5);

		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);

		EasyMock.expect(cost.getType()).andReturn(CostType.NONE);
		EasyMock.expect(effect.getEffectType()).andReturn(Effect.EffectType.ENTITY);

		EasyMock.replay(cost, effect);

		Card card = new Card("Clay Pool", frequency, CardType.RAWMATERIAL, cost, effect, "None", "None");

		assertEquals("Clay Pool", card.getName());
		assertEquals(frequency, card.getFrequencyByNumPlayers());
		assertEquals(CardType.RAWMATERIAL, card.getCardType());
		assertEquals(CostType.NONE, card.getCostType());
		assertEquals(Effect.EffectType.ENTITY, card.getEffectType());

		EasyMock.verify(cost, effect);
	}

	@Test
	public void testRawMaterialCardOre() {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(4);

		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);

		EasyMock.expect(cost.getType()).andReturn(CostType.NONE);

		EasyMock.expect(effect.getEffectType()).andReturn(Effect.EffectType.ENTITY);

		EasyMock.replay(cost, effect);

		Card card = new Card("Ore Vein", frequency, CardType.RAWMATERIAL, cost, effect, "None", "None");

		assertEquals("Ore Vein", card.getName());
		assertEquals(frequency, card.getFrequencyByNumPlayers());
		assertEquals(CardType.RAWMATERIAL, card.getCardType());
		assertEquals(CostType.NONE, card.getCostType());
		assertEquals(Effect.EffectType.ENTITY, card.getEffectType());

		EasyMock.verify(cost, effect);
	}

	@Test
	public void testRawMaterialCardStone() {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(5);

		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);

		HashMap<Enum, Integer> expected = new HashMap<Enum, Integer>();
		expected.put(RawResource.STONE, 1);

		EasyMock.expect(cost.getType()).andReturn(CostType.NONE);

		HashMap<Enum, Integer> expectedEntities = new HashMap<Enum, Integer>();
		expectedEntities.put(RawResource.STONE, 1);

		EasyMock.expect(effect.getEffectType()).andReturn(Effect.EffectType.ENTITY);

		EasyMock.replay(cost, effect);

		Card card = new Card("Stone Pit", frequency, CardType.RAWMATERIAL, cost, effect, "None", "None");

		assertEquals("Stone Pit", card.getName());
		assertEquals(frequency, card.getFrequencyByNumPlayers());
		assertEquals(CardType.RAWMATERIAL, card.getCardType());
		assertEquals(CostType.NONE, card.getCostType());
		assertEquals(Effect.EffectType.ENTITY, card.getEffectType());

		EasyMock.verify(cost, effect);
	}

	@Test
	public void testManufacturedGoodCardPress() {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(6);

		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);

		EasyMock.expect(cost.getType()).andReturn(CostType.NONE);
		EasyMock.expect(effect.getEffectType()).andReturn(Effect.EffectType.ENTITY);
		EasyMock.replay(cost, effect);

		Card card = new Card("Press", frequency, CardType.MANUFACTUREDGOOD, cost, effect, "None", "None");

		assertEquals("Press", card.getName());
		assertEquals(frequency, card.getFrequencyByNumPlayers());
		assertEquals(CardType.MANUFACTUREDGOOD, card.getCardType());
		assertEquals(CostType.NONE, card.getCostType());
		assertEquals(EffectType.ENTITY, card.getEffectType());

		EasyMock.verify(cost, effect);
	}

	@Test
	public void testManufacturedGoodCardGlass() {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(6);

		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);

		EasyMock.expect(cost.getType()).andReturn(CostType.NONE);
		EasyMock.expect(effect.getEffectType()).andReturn(Effect.EffectType.ENTITY);
		EasyMock.replay(cost, effect);

		Card card = new Card("Glass", frequency, CardType.MANUFACTUREDGOOD, cost, effect, "None", "None");

		assertEquals("Glass", card.getName());
		assertEquals(frequency, card.getFrequencyByNumPlayers());
		assertEquals(CardType.MANUFACTUREDGOOD, card.getCardType());
		assertEquals(CostType.NONE, card.getCostType());
		assertEquals(EffectType.ENTITY, card.getEffectType());

		EasyMock.verify(cost, effect);
	}

	@Test
	public void testManufacturedGoodCardLoom() {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(6);

		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);

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
	public void testCommercialStructureCardCommercialEffect() {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(7);

		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);

		HashMap<Enum, Integer> expected = new HashMap<Enum, Integer>();
		expected.put(RawResource.LUMBER, 2);

		EasyMock.expect(cost.getType()).andReturn(CostType.RESOURCE);
		EasyMock.expect(effect.getEffectType()).andReturn(Effect.EffectType.VALUE);

		EasyMock.replay(cost, effect);

		Card card = new Card("East Trading Post", frequency, CardType.COMMERCIALSTRUCTURE, cost, effect, "None",
				"None");

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
		expected.put(RawResource.LUMBER, 2);

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
		expected.put(RawResource.LUMBER, 2);

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
		expected.put(RawResource.LUMBER, 2);

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
		expectedCost.put(RawResource.LUMBER, 2);

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
		expected.put(RawResource.LUMBER, 2);

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
		expected.put(RawResource.LUMBER, 2);

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
	public void testToStringEastTrading() {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(6);
		frequency.add(7);

		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);

		EasyMock.expect(cost.getType()).andReturn(CostType.RESOURCE);
		EasyMock.expect(effect.getEffectType()).andReturn(Effect.EffectType.VALUE);

		EasyMock.replay(cost, effect);

		Card card = new Card("East Trading Post", frequency, CardType.COMMERCIALSTRUCTURE, cost, effect, "None",
				"None");
		String expected = "name: East Trading Post" + System.lineSeparator() + "minFrequencyByNumPlayers: 3"
				+ System.lineSeparator() + "costType: RESOURCE" + System.lineSeparator() + "effectType: VALUE";

		assertEquals(expected, card.toString());

		EasyMock.verify(cost, effect);
	}

	@Test
	public void testValidEqualsResource() {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(4);
		ArrayList<Integer> frequency2 = new ArrayList<Integer>();
		frequency2.add(3);
		frequency2.add(4);

		Cost cost = new Cost(CostType.NONE, 0);
		Effect effect = new EntityEffect(EntityType.RESOURCE, new HashMap<Enum, Integer>());
		Cost cost2 = new Cost(CostType.NONE, 0);
		Effect effect2 = new EntityEffect(EntityType.RESOURCE, new HashMap<Enum, Integer>());

		Card card = new Card("Lumber Yard", frequency, CardType.RAWMATERIAL, cost, effect, "None", "None");
		Card card2 = new Card("Lumber Yard", frequency2, CardType.RAWMATERIAL, cost2, effect2, "None", "None");

		assertTrue(card.equals(card2));
	}

	@Test
	public void testInvalidEqualsResourceEffect() {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(4);
		ArrayList<Integer> frequency2 = new ArrayList<Integer>();
		frequency2.add(3);
		frequency2.add(4);

		Cost cost = new Cost(CostType.NONE, 0);
		Effect effect = new EntityEffect(EntityType.MANUFACTUREDGOOD, new HashMap<Enum, Integer>());
		Cost cost2 = new Cost(CostType.NONE, 0);
		Effect effect2 = new EntityEffect(EntityType.RESOURCE, new HashMap<Enum, Integer>());

		Card card = new Card("Lumber Yard", frequency, CardType.RAWMATERIAL, cost, effect, "None", "None");
		Card card2 = new Card("Lumber Yard", frequency2, CardType.RAWMATERIAL, cost2, effect2, "None", "None");

		assertFalse(card.equals(card2));
	}

	@Test
	public void testInvalidEqualsResourceCost() {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(4);
		ArrayList<Integer> frequency2 = new ArrayList<Integer>();
		frequency2.add(3);
		frequency2.add(4);

		Cost cost = new Cost(CostType.NONE, 1);
		Effect effect = new EntityEffect(EntityType.RESOURCE, new HashMap<Enum, Integer>());
		Cost cost2 = new Cost(CostType.NONE, 0);
		Effect effect2 = new EntityEffect(EntityType.RESOURCE, new HashMap<Enum, Integer>());

		Card card = new Card("Lumber Yard", frequency, CardType.RAWMATERIAL, cost, effect, "None", "None");
		Card card2 = new Card("Lumber Yard", frequency2, CardType.RAWMATERIAL, cost2, effect2, "None", "None");

		assertFalse(card.equals(card2));
	}

	@Test
	public void testInvalidEqualsResourceFrequency() {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(4);
		ArrayList<Integer> frequency2 = new ArrayList<Integer>();
		frequency2.add(3);
		frequency2.add(7);

		Cost cost = new Cost(CostType.NONE, 0);
		Effect effect = new EntityEffect(EntityType.RESOURCE, new HashMap<Enum, Integer>());
		Cost cost2 = new Cost(CostType.NONE, 0);
		Effect effect2 = new EntityEffect(EntityType.RESOURCE, new HashMap<Enum, Integer>());

		Card card = new Card("Lumber Yard", frequency, CardType.RAWMATERIAL, cost, effect, "None", "None");
		Card card2 = new Card("Lumber Yard", frequency2, CardType.RAWMATERIAL, cost2, effect2, "None", "None");

		assertFalse(card.equals(card2));
	}

	@Test
	public void testInvalidEqualsResourceEffectType() {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(4);
		ArrayList<Integer> frequency2 = new ArrayList<Integer>();
		frequency2.add(3);
		frequency2.add(4);

		Cost cost = new Cost(CostType.NONE, 0);
		Effect effect = new EntityEffect(EntityType.RESOURCE, new HashMap<Enum, Integer>());
		Cost cost2 = new Cost(CostType.NONE, 0);
		Effect effect2 = new ValueEffect(Value.COMMERCE, AffectingEntity.NONE, 3);

		Card card = new Card("Lumber Yard", frequency, CardType.RAWMATERIAL, cost, effect, "None", "None");
		Card card2 = new Card("Lumber Yard", frequency2, CardType.RAWMATERIAL, cost2, effect2, "None", "None");

		assertFalse(card.equals(card2));
	}

	@Test
	public void testInvalidEqualsResourceEffectTypeValue() {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(4);
		ArrayList<Integer> frequency2 = new ArrayList<Integer>();
		frequency2.add(3);
		frequency2.add(4);

		Cost cost = new Cost(CostType.NONE, 0);
		Effect effect = new EntityEffect(EntityType.RESOURCE, new HashMap<Enum, Integer>());
		Cost cost2 = new Cost(CostType.NONE, 0);
		Effect effect2 = new ValueEffect(Value.COMMERCE, AffectingEntity.NONE, 3);

		Card card = new Card("Lumber Yard", frequency, CardType.RAWMATERIAL, cost, effect, "None", "None");
		Card card2 = new Card("Lumber Yard", frequency2, CardType.RAWMATERIAL, cost2, effect2, "None", "None");

		assertFalse(card2.equals(card));
	}

	@Test
	public void testInvalidEqualsResourceName() {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(4);
		ArrayList<Integer> frequency2 = new ArrayList<Integer>();
		frequency2.add(3);
		frequency2.add(4);

		Cost cost = new Cost(CostType.NONE, 0);
		Effect effect = new EntityEffect(EntityType.RESOURCE, new HashMap<Enum, Integer>());
		Cost cost2 = new Cost(CostType.NONE, 0);
		Effect effect2 = new EntityEffect(EntityType.RESOURCE, new HashMap<Enum, Integer>());

		Card card = new Card("Lumber Yard", frequency, CardType.RAWMATERIAL, cost, effect, "None", "None");
		Card card2 = new Card("Baths", frequency2, CardType.RAWMATERIAL, cost2, effect2, "None", "None");

		assertFalse(card.equals(card2));
	}

	@Test
	public void testInvalidEqualsResourcePrevious() {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(4);
		ArrayList<Integer> frequency2 = new ArrayList<Integer>();
		frequency2.add(3);
		frequency2.add(4);

		Cost cost = new Cost(CostType.NONE, 0);
		Effect effect = new EntityEffect(EntityType.RESOURCE, new HashMap<Enum, Integer>());
		Cost cost2 = new Cost(CostType.NONE, 0);
		Effect effect2 = new EntityEffect(EntityType.RESOURCE, new HashMap<Enum, Integer>());

		Card card = new Card("Lumber Yard", frequency, CardType.RAWMATERIAL, cost, effect, "None", "None");
		Card card2 = new Card("Lumber Yard", frequency2, CardType.RAWMATERIAL, cost2, effect2, "Baths", "None");

		assertFalse(card.equals(card2));
	}

	@Test
	public void testInvalidEqualsResourceNext() {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(4);
		ArrayList<Integer> frequency2 = new ArrayList<Integer>();
		frequency2.add(3);
		frequency2.add(4);

		Cost cost = new Cost(CostType.NONE, 0);
		Effect effect = new EntityEffect(EntityType.RESOURCE, new HashMap<Enum, Integer>());
		Cost cost2 = new Cost(CostType.NONE, 0);
		Effect effect2 = new EntityEffect(EntityType.RESOURCE, new HashMap<Enum, Integer>());

		Card card = new Card("Lumber Yard", frequency, CardType.RAWMATERIAL, cost, effect, "None", "None");
		Card card2 = new Card("Lumber Yard", frequency2, CardType.RAWMATERIAL, cost2, effect2, "None", "Baths");

		assertFalse(card.equals(card2));
	}

	@Test
	public void testInvalidEqualsCardType() {
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(4);
		ArrayList<Integer> frequency2 = new ArrayList<Integer>();
		frequency2.add(3);
		frequency2.add(4);

		Cost cost = new Cost(CostType.NONE, 0);
		Effect effect = new EntityEffect(EntityType.RESOURCE, new HashMap<Enum, Integer>());
		Cost cost2 = new Cost(CostType.NONE, 0);
		Effect effect2 = new EntityEffect(EntityType.RESOURCE, new HashMap<Enum, Integer>());

		Card card = new Card("Lumber Yard", frequency, CardType.CIVILIANSTRUCTURE, cost, effect, "None", "None");
		Card card2 = new Card("Lumber Yard", frequency2, CardType.RAWMATERIAL, cost2, effect2, "None", "None");

		assertFalse(card.equals(card2));
	}

	// END GENERATED CODE
}
