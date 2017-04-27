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
		Wonder wonder = new Wonder('B', 2);
		
		assertEquals('B', wonder.getSide());
		assertEquals(2, wonder.getNumLevels());
	}
	
	@Test
	public void testBasicsSideB3Levels() {
		Wonder wonder = new Wonder('B', 3);
		
		assertEquals('B', wonder.getSide());
		assertEquals(3, wonder.getNumLevels());
	}
	
	@Test
	public void testBasicsSideB4Levels() {
		Wonder wonder = new Wonder('B', 4);
		
		assertEquals('B', wonder.getSide());
		assertEquals(4, wonder.getNumLevels());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevelsNeg1() {
		Wonder wonder = new Wonder('B', -1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels1() {
		Wonder wonder = new Wonder('B', 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels5() {
		Wonder wonder = new Wonder('B', 5);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels6() {
		Wonder wonder = new Wonder('B', 6);
	}
}
