package dataStructureTests;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import dataStructures.Cost;
import dataStructures.Cost.CostType;
import dataStructures.Cost.Good;
import dataStructures.Cost.Resource;

public class CostTest {

	@Test
	public void testDefaultCost() {
		Cost cost = new Cost();
		
		assertEquals(CostType.NONE, cost.getType());
		assertEquals(0, cost.getCost().size());
		assertEquals(0, cost.getCoinCost());
	}
	
	@Test
	public void testValueCost() {
		Cost cost = new Cost(CostType.COIN, 1);
		
		assertEquals(CostType.COIN, cost.getType());
		assertEquals(0, cost.getCost().size());
		assertEquals(1, cost.getCoinCost());
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
		assertEquals(0, cost.getCoinCost());
	}
	
	@Test
	public void testOreLumberCost() {
		HashMap<Enum, Integer> expectedCost = new HashMap<Enum, Integer>();
		HashMap<Enum, Integer> actualCost = new HashMap<Enum, Integer>();
		
		expectedCost.put(Resource.ORE, 1);
		expectedCost.put(Resource.LUMBER, 3);
		
		actualCost.put(Resource.ORE, 1);
		actualCost.put(Resource.LUMBER, 3);
		
		Cost cost = new Cost(CostType.RESOURCE, actualCost);
		
		assertEquals(CostType.RESOURCE, cost.getType());
		assertEquals(expectedCost.get(Resource.ORE), cost.getCost().get(Resource.ORE));
		assertEquals(expectedCost.get(Resource.LUMBER), cost.getCost().get(Resource.LUMBER));
		assertEquals(0, cost.getCoinCost());
	}
	
	@Test
	public void test3Wood1GlassCost() {
		HashMap<Enum, Integer> expectedCost = new HashMap<Enum, Integer>();
		HashMap<Enum, Integer> actualCost = new HashMap<Enum, Integer>();
		
		expectedCost.put(Resource.STONE, 3);
		expectedCost.put(Good.GLASS, 1);
		
		actualCost.put(Resource.STONE, 3);
		actualCost.put(Good.GLASS, 1);
		
		Cost cost = new Cost(CostType.MULTITYPE, actualCost);
		
		assertEquals(CostType.MULTITYPE, cost.getType());
		assertEquals(expectedCost.get(Resource.STONE), cost.getCost().get(Resource.STONE));
		assertEquals(expectedCost.get(Good.GLASS), cost.getCost().get(Good.GLASS));
		assertEquals(0, cost.getCoinCost());
	}
	
	@Test
	public void test1OfEachCost() {
		HashMap<Enum, Integer> expectedCost = new HashMap<Enum, Integer>();
		HashMap<Enum, Integer> actualCost = new HashMap<Enum, Integer>();
		
		expectedCost.put(Resource.STONE, 1);
		expectedCost.put(Good.GLASS, 1);
		
		actualCost.put(Resource.STONE, 1);
		actualCost.put(Good.GLASS, 1);
		
		Cost cost = new Cost(CostType.MULTITYPE, actualCost);
		
		assertEquals(CostType.MULTITYPE, cost.getType());
		assertEquals(expectedCost.get(Resource.STONE), cost.getCost().get(Resource.STONE));
		assertEquals(expectedCost.get(Good.GLASS), cost.getCost().get(Good.GLASS));
		assertEquals(0, cost.getCoinCost());
	}
}
