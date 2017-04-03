package dataStructureTests;

import static org.junit.Assert.*;

import org.junit.Test;

import dataStructures.Card.Cost;

public class CostTest {

	@Test
	public void testDefaultCost() {
		Cost cost = new Cost();
		
		assertEquals(Cost.NONE, cost.getType());
		assertEquals(0, cost.getResourceCost().size());
		assertEquals(0, cost.getCoinCost());
	}

}
