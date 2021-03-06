package dataStructureTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Test;

import constants.GeneralEnums.CostType;
import constants.GeneralEnums.Good;
import constants.GeneralEnums.RawResource;
import dataStructures.gameMaterials.Cost;

public class CostTest {
	// BEGIN GENERATED CODE
	@Test
	public void testValueCost() {
		Cost cost = new Cost(CostType.COIN, 1);

		assertEquals(CostType.COIN, cost.getType());
		assertEquals(1, cost.getCost().size());
		assertEquals(1, (int) cost.getCost().get(CostType.COIN));
	}

	@Test
	public void test2LoomCost() {
		HashMap<Enum, Integer> expectedCost = new HashMap<Enum, Integer>();
		HashMap<Enum, Integer> actualCost = new HashMap<Enum, Integer>();

		expectedCost.put(Good.LOOM, 2);
		actualCost.put(Good.LOOM, 2);

		Cost cost = new Cost(CostType.GOOD, actualCost);

		assertEquals(CostType.GOOD, cost.getType());
		assertEquals(expectedCost.get(Good.LOOM), cost.getCost().get(Good.LOOM));
	}

	@Test
	public void testOreLumberCost() {
		HashMap<Enum, Integer> expectedCost = new HashMap<Enum, Integer>();
		HashMap<Enum, Integer> actualCost = new HashMap<Enum, Integer>();

		expectedCost.put(RawResource.ORE, 1);
		expectedCost.put(RawResource.LUMBER, 3);

		actualCost.put(RawResource.ORE, 1);
		actualCost.put(RawResource.LUMBER, 3);

		Cost cost = new Cost(CostType.RESOURCE, actualCost);

		assertEquals(CostType.RESOURCE, cost.getType());
		assertEquals(expectedCost.get(RawResource.ORE), cost.getCost().get(RawResource.ORE));
		assertEquals(expectedCost.get(RawResource.LUMBER), cost.getCost().get(RawResource.LUMBER));
	}

	@Test
	public void test3Wood1GlassCost() {
		HashMap<Enum, Integer> expectedCost = new HashMap<Enum, Integer>();
		HashMap<Enum, Integer> actualCost = new HashMap<Enum, Integer>();

		expectedCost.put(RawResource.STONE, 3);
		expectedCost.put(Good.GLASS, 1);

		actualCost.put(RawResource.STONE, 3);
		actualCost.put(Good.GLASS, 1);

		Cost cost = new Cost(CostType.MULTITYPE, actualCost);

		assertEquals(CostType.MULTITYPE, cost.getType());
		assertEquals(expectedCost.get(RawResource.STONE), cost.getCost().get(RawResource.STONE));
		assertEquals(expectedCost.get(Good.GLASS), cost.getCost().get(Good.GLASS));
	}

	@Test
	public void test1OfEachCost() {
		HashMap<Enum, Integer> expectedCost = new HashMap<Enum, Integer>();
		HashMap<Enum, Integer> actualCost = new HashMap<Enum, Integer>();

		expectedCost.put(RawResource.STONE, 1);
		expectedCost.put(RawResource.ORE, 1);
		expectedCost.put(RawResource.CLAY, 1);
		expectedCost.put(RawResource.LUMBER, 1);
		expectedCost.put(Good.LOOM, 1);
		expectedCost.put(Good.PRESS, 1);
		expectedCost.put(Good.GLASS, 1);

		actualCost.put(RawResource.STONE, 1);
		actualCost.put(RawResource.ORE, 1);
		actualCost.put(RawResource.CLAY, 1);
		actualCost.put(RawResource.LUMBER, 1);
		actualCost.put(Good.LOOM, 1);
		actualCost.put(Good.PRESS, 1);
		actualCost.put(Good.GLASS, 1);

		Cost cost = new Cost(CostType.MULTITYPE, actualCost);

		assertEquals(CostType.MULTITYPE, cost.getType());
		assertEquals(expectedCost.get(RawResource.STONE), cost.getCost().get(RawResource.STONE));
		assertEquals(expectedCost.get(RawResource.ORE), cost.getCost().get(RawResource.ORE));
		assertEquals(expectedCost.get(RawResource.CLAY), cost.getCost().get(RawResource.CLAY));
		assertEquals(expectedCost.get(RawResource.LUMBER), cost.getCost().get(RawResource.LUMBER));
		assertEquals(expectedCost.get(Good.LOOM), cost.getCost().get(Good.LOOM));
		assertEquals(expectedCost.get(Good.PRESS), cost.getCost().get(Good.PRESS));
		assertEquals(expectedCost.get(Good.GLASS), cost.getCost().get(Good.GLASS));
	}

	@Test
	public void testValidTwoCostsEqualNoneType() {
		Cost cost = new Cost(CostType.NONE, 0);
		Cost cost2 = new Cost(CostType.NONE, 0);

		assertTrue(cost.equals(cost2));
	}

	@Test
	public void testValidTwoCostsEqualCoinType() {
		Cost cost = new Cost(CostType.COIN, 6);
		Cost cost2 = new Cost(CostType.COIN, 6);

		assertTrue(cost.equals(cost2));
	}

	@Test
	public void testInvalidTwoCostsEqualCoinType() {
		Cost cost = new Cost(CostType.COIN, 6);
		Cost cost2 = new Cost(CostType.COIN, 9);

		assertFalse(cost.equals(cost2));
	}

	@Test
	public void testValidTwoCostsEqualResourceType() {
		HashMap<Enum, Integer> cost1 = new HashMap<Enum, Integer>();
		cost1.put(RawResource.STONE, 3);
		cost1.put(Good.GLASS, 1);

		HashMap<Enum, Integer> cost2Info = new HashMap<Enum, Integer>();
		cost2Info.put(RawResource.STONE, 3);
		cost2Info.put(Good.GLASS, 1);

		Cost cost = new Cost(CostType.COIN, cost1);
		Cost cost2 = new Cost(CostType.COIN, cost2Info);

		assertTrue(cost.equals(cost2));
	}

	@Test
	public void testInvalidTwoCostsEqualResourceType() {
		HashMap<Enum, Integer> cost1 = new HashMap<Enum, Integer>();
		cost1.put(RawResource.STONE, 9);
		cost1.put(Good.GLASS, 1);

		HashMap<Enum, Integer> cost2Info = new HashMap<Enum, Integer>();
		cost2Info.put(RawResource.STONE, 3);
		cost2Info.put(Good.GLASS, 1);

		Cost cost = new Cost(CostType.COIN, cost1);
		Cost cost2 = new Cost(CostType.COIN, cost2Info);

		assertFalse(cost.equals(cost2));
	}

	@Test
	public void testInvalidTwoCostsEqual() {
		HashMap<Enum, Integer> cost1 = new HashMap<Enum, Integer>();
		cost1.put(RawResource.STONE, 9);
		cost1.put(Good.GLASS, 1);

		HashMap<Enum, Integer> cost2Info = new HashMap<Enum, Integer>();
		cost2Info.put(RawResource.LUMBER, 3);
		cost2Info.put(Good.LOOM, 1);

		Cost cost = new Cost(CostType.COIN, cost1);
		Cost cost2 = new Cost(CostType.COIN, cost2Info);

		assertFalse(cost.equals(cost2));
	}

	@Test
	public void testInvlidTwoCostsEqualNoneCostType() {
		Cost cost = new Cost(CostType.NONE, 0);
		Cost cost2 = new Cost(CostType.COIN, 0);

		assertFalse(cost.equals(cost2));
	}
	// END GENERATED CODE
}
