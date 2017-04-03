package dataStructureTests;

import static org.junit.Assert.*;

import org.junit.Test;

import dataStructures.Cost;
import dataStructures.Cost.CostType;

public class CostTest {

	@Test
	public void testDefaultCost() {
		Cost cost = new Cost();
		
		assertEquals(CostType.NONE, cost.getType());
		assertEquals(0, cost.getResourceCost().size());
		assertEquals(0, cost.getCoinCost());
	}

}
