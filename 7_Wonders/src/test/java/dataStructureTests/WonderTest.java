package dataStructureTests;

import static org.junit.Assert.*;

import org.junit.Test;

import dataStructures.Wonder;

public class WonderTest {

	@Test
	public void testBasicsSideA() {
		Wonder wonder = new Wonder('A');
		
		assertEquals('A', wonder.getSide());
		assertEquals(3, wonder.getNumLevels());
	}
}
