package dataStructureTests;

import static org.junit.Assert.*;

import org.junit.Test;

import dataStructures.Wonder;

public class WonderTest {

	@Test
	public void testNumLevelsSideA() {
		Wonder wonder = new Wonder('A');
		
		assertEquals(3, wonder.getNumLevels());
	}
}
