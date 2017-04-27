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
	
	@Test
	public void testBasicsSideB2Levels() {
		Wonder wonder = new Wonder('B');
		
		assertEquals('B', wonder.getSide());
		assertEquals(2, wonder.getNumLevels());
	}
}
