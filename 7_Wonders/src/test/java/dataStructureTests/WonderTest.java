package dataStructureTests;

import static org.junit.Assert.*;

import org.junit.Test;

import dataStructures.Wonder;

public class WonderTest {

	@Test
	public void testBasicsSideA() {
		Wonder wonder = new Wonder('A', "The Lighthouse of Alexandria");
		
		assertEquals('A', wonder.getSide());
		assertEquals(3, wonder.getNumLevels());
		assertEquals("The Lighthouse of Alexandria", wonder.getName());
	}
	
	@Test
	public void testBasicsSideB2Levels() {
		Wonder wonder = new Wonder('B', "Colossus of Rhodes", 2);
		
		assertEquals('B', wonder.getSide());
		assertEquals(2, wonder.getNumLevels());
		assertEquals("Colossus of Rhodes", wonder.getName());
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
	public void testInvalidNumLevelsNeg1SideA() {
		Wonder wonder = new Wonder('A', -1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevelsNeg1SideB() {
		Wonder wonder = new Wonder('B', -1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels1SideA() {
		Wonder wonder = new Wonder('A', 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels1SideB() {
		Wonder wonder = new Wonder('B', 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels5SideA() {
		Wonder wonder = new Wonder('A', 5);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels5SideB() {
		Wonder wonder = new Wonder('B', 5);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels2SideA() {
		Wonder wonder = new Wonder('A', 2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels4SideA() {
		Wonder wonder = new Wonder('A', 4);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels6SideB() {
		Wonder wonder = new Wonder('B', 6);
	}
	

}
