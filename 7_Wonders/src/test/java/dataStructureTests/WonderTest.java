package dataStructureTests;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import dataStructures.Wonder;
import dataStructures.Wonder.WonderType;

public class WonderTest {

	@Test
	public void testBasicsSideA() {
		HashMap<WonderType, String> wonders = new HashMap<WonderType, String>();
		wonders.put(WonderType.COLOSSUS, "The Colossus of Rhodes");
		wonders.put(WonderType.LIGHTHOUSE, "The Lighthouse of Alexandria");
		wonders.put(WonderType.TEMPLE, "The Temple of Artemis in Ephesus");
		wonders.put(WonderType.GARDENS, "The Hanging Gardens of Babylon");
		wonders.put(WonderType.STATUE, "The Statue of Zeus in Olympia");
		wonders.put(WonderType.MAUSOLEUM, "The Mausoleum of Halicarnassus");
		wonders.put(WonderType.PYRAMIDS, "The Pyramids of Giza");
		
		for(WonderType type : wonders.keySet()) {
			Wonder wonder = new Wonder('A', type);
			verifyWonderSideA(wonder, type, wonders.get(type));
		}
		
	}
	
	private void verifyWonderSideA(Wonder wonder, WonderType type, String name) {
		assertEquals('A', wonder.getSide());
		assertEquals(3, wonder.getNumLevels());
		assertEquals(type, wonder.getType());
		assertEquals(name, wonder.getName());
	}
	
	@Test
	public void testBasicsSideB2Levels() {
		Wonder wonder = new Wonder('B', WonderType.COLOSSUS, 2);
		
		assertEquals('B', wonder.getSide());
		assertEquals(2, wonder.getNumLevels());
		assertEquals("The Colossus of Rhodes", wonder.getName());
	}
	
	@Test
	public void testBasicsSideB3Levels() {
		Wonder wonder = new Wonder('B', WonderType.TEMPLE, 3);
		
		assertEquals('B', wonder.getSide());
		assertEquals(3, wonder.getNumLevels());
		assertEquals("The Temple of Artemis in Ephesus", wonder.getName());
	}
	
	@Test
	public void testBasicsSideB4Levels() {
		Wonder wonder = new Wonder('B', WonderType.PYRAMIDS, 4);
		
		assertEquals('B', wonder.getSide());
		assertEquals(4, wonder.getNumLevels());
		assertEquals("The Pyramids of Giza", wonder.getName());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevelsNeg1SideA() {
		Wonder wonder = new Wonder('A', WonderType.MAUSOLEUM, -1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevelsNeg1SideB() {
		Wonder wonder = new Wonder('B', WonderType.MAUSOLEUM, -1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels1SideA() {
		Wonder wonder = new Wonder('A', WonderType.MAUSOLEUM, 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels1SideB() {
		Wonder wonder = new Wonder('B', WonderType.MAUSOLEUM, 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels5SideA() {
		Wonder wonder = new Wonder('A', WonderType.MAUSOLEUM, 5);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels5SideB() {
		Wonder wonder = new Wonder('B', WonderType.MAUSOLEUM, 5);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels2SideA() {
		Wonder wonder = new Wonder('A', WonderType.MAUSOLEUM, 2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels4SideA() {
		Wonder wonder = new Wonder('A', WonderType.MAUSOLEUM, 4);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels6SideB() {
		Wonder wonder = new Wonder('B', WonderType.MAUSOLEUM, 6);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels1SideBColossus() {
		Wonder wonder = new Wonder('B', WonderType.COLOSSUS, 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels3SideBColossus() {
		Wonder wonder = new Wonder('B', WonderType.COLOSSUS, 3);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels2SideBLighthouse() {
		Wonder wonder = new Wonder('B', WonderType.LIGHTHOUSE, 2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels4SideBLighthouse() {
		Wonder wonder = new Wonder('B', WonderType.LIGHTHOUSE, 4);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels2SideBTemple() {
		Wonder wonder = new Wonder('B', WonderType.TEMPLE, 2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels4SideBTemple() {
		Wonder wonder = new Wonder('B', WonderType.TEMPLE, 4);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels2SideBGardens() {
		Wonder wonder = new Wonder('B', WonderType.GARDENS, 2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels4SideBGardens() {
		Wonder wonder = new Wonder('B', WonderType.GARDENS, 4);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels2SideBStatue() {
		Wonder wonder = new Wonder('B', WonderType.STATUE, 2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels4SideBStatue() {
		Wonder wonder = new Wonder('B', WonderType.STATUE, 4);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels2SideBMausoleum() {
		Wonder wonder = new Wonder('B', WonderType.MAUSOLEUM, 2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels4SideBMausoleum() {
		Wonder wonder = new Wonder('B', WonderType.MAUSOLEUM, 4);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels3SideBPyramids() {
		Wonder wonder = new Wonder('B', WonderType.PYRAMIDS, 3);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels5SideBPyramids() {
		Wonder wonder = new Wonder('B', WonderType.PYRAMIDS, 5);
	}
}
