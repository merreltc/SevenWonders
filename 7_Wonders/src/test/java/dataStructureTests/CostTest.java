package dataStructureTests;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import dataStructures.Cost;
import dataStructures.Cost.CostType;
import dataStructures.Cost.ResourceType;

public class CostTest {

	@Test
	public void testDefaultCost() {
		Cost cost = new Cost();
		
		assertEquals(CostType.NONE, cost.getType());
		assertEquals(0, cost.getResourceCost().size());
		assertEquals(0, cost.getCoinCost());
	}
	
	@Test
	public void testValueCost() {
		Cost cost = new Cost(CostType.COIN, 1);
		
		assertEquals(CostType.COIN, cost.getType());
		assertEquals(0, cost.getResourceCost().size());
		assertEquals(1, cost.getCoinCost());
	}
	
	@Test
	public void testSingleResourceCost() {
		HashMap<ResourceType, Integer> expectedResourceCost = new HashMap<ResourceType, Integer>();
		HashMap<ResourceType, Integer> actualResourceCost = new HashMap<ResourceType, Integer>();
		
		expectedResourceCost.put(ResourceType.LOOM, 2);
		actualResourceCost.put(ResourceType.LOOM, 2);
		
		Cost cost = new Cost(CostType.RESOURCE, actualResourceCost);
		
		assertEquals(CostType.RESOURCE, cost.getType());
		assertEquals(expectedResourceCost.get(ResourceType.LOOM), cost.getResourceCost().get(ResourceType.LOOM));
		assertEquals(0, cost.getCoinCost());
	}
	
	@Test
	public void testMultiResourceCost() {
		HashMap<ResourceType, Integer> expectedResourceCost = new HashMap<ResourceType, Integer>();
		HashMap<ResourceType, Integer> actualResourceCost = new HashMap<ResourceType, Integer>();
		
		expectedResourceCost.put(ResourceType.ORE, 1);
		expectedResourceCost.put(ResourceType.LUMBER, 3);
		expectedResourceCost.put(ResourceType.LOOM, 3);
		
		actualResourceCost.put(ResourceType.ORE, 1);
		actualResourceCost.put(ResourceType.LUMBER, 3);
		actualResourceCost.put(ResourceType.LOOM, 3);
		
		Cost cost = new Cost(CostType.RESOURCE, actualResourceCost);
		
		assertEquals(CostType.RESOURCE, cost.getType());
		assertEquals(expectedResourceCost.get(ResourceType.ORE), cost.getResourceCost().get(ResourceType.ORE));
		assertEquals(expectedResourceCost.get(ResourceType.LUMBER), cost.getResourceCost().get(ResourceType.LUMBER));
		assertEquals(expectedResourceCost.get(ResourceType.LOOM), cost.getResourceCost().get(ResourceType.LOOM));
		assertEquals(0, cost.getCoinCost());
	}
}
