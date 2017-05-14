package dataStructureTests;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import dataStructures.GeneralEnums.Resource;
import dataStructures.Wonder;
import dataStructures.Wonder.WonderType;

public class WonderTest {
	
	@Test
	public void testResource() {
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
			Resource resource = Wonder.getResourceByType(type);
			assertEquals(resource, wonder.getResource());
		}
	}

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
		new Wonder('A', WonderType.MAUSOLEUM, -1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevelsNeg1SideB() {
		new Wonder('B', WonderType.MAUSOLEUM, -1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels1SideA() {
		new Wonder('A', WonderType.MAUSOLEUM, 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels1SideB() {
		new Wonder('B', WonderType.MAUSOLEUM, 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels5SideA() {
		new Wonder('A', WonderType.MAUSOLEUM, 5);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels5SideB() {
		new Wonder('B', WonderType.MAUSOLEUM, 5);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels2SideA() {
		new Wonder('A', WonderType.MAUSOLEUM, 2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels4SideA() {
		new Wonder('A', WonderType.MAUSOLEUM, 4);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels6SideB() {
		new Wonder('B', WonderType.MAUSOLEUM, 6);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels1SideBColossus() {
		new Wonder('B', WonderType.COLOSSUS, 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels3SideBColossus() {
		new Wonder('B', WonderType.COLOSSUS, 3);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels2SideBLighthouse() {
		new Wonder('B', WonderType.LIGHTHOUSE, 2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels4SideBLighthouse() {
		new Wonder('B', WonderType.LIGHTHOUSE, 4);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels2SideBTemple() {
		new Wonder('B', WonderType.TEMPLE, 2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels4SideBTemple() {
		new Wonder('B', WonderType.TEMPLE, 4);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels2SideBGardens() {
		new Wonder('B', WonderType.GARDENS, 2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels4SideBGardens() {
		new Wonder('B', WonderType.GARDENS, 4);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels2SideBStatue() {
		new Wonder('B', WonderType.STATUE, 2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels4SideBStatue() {
		new Wonder('B', WonderType.STATUE, 4);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels2SideBMausoleum() {
		new Wonder('B', WonderType.MAUSOLEUM, 2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels4SideBMausoleum() {
		new Wonder('B', WonderType.MAUSOLEUM, 4);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels3SideBPyramids() {
		new Wonder('B', WonderType.PYRAMIDS, 3);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNumLevels5SideBPyramids() {
		new Wonder('B', WonderType.PYRAMIDS, 5);
	}
	
	@Test
	public void testGetWonderNameByTypeValid() {
		String name = Wonder.getNameByType(WonderType.COLOSSUS);
		assertEquals("The Colossus of Rhodes", name);
		name = Wonder.getNameByType(WonderType.GARDENS);
		assertEquals("The Hanging Gardens of Babylon", name);
		name = Wonder.getNameByType(WonderType.LIGHTHOUSE);
		assertEquals("The Lighthouse of Alexandria", name);
		name = Wonder.getNameByType(WonderType.MAUSOLEUM);
		assertEquals("The Mausoleum of Halicarnassus", name);
		name = Wonder.getNameByType(WonderType.PYRAMIDS);
		assertEquals("The Pyramids of Giza", name);
		name = Wonder.getNameByType(WonderType.STATUE);
		assertEquals("The Statue of Zeus in Olympia", name);
		name = Wonder.getNameByType(WonderType.TEMPLE);
		assertEquals("The Temple of Artemis in Ephesus", name);		
	}
	
	@Test
	public void testGetShortNameByTypeValid() {
		String name = Wonder.getShortNameByType(WonderType.COLOSSUS);
		assertEquals("Colossus", name);
		name = Wonder.getShortNameByType(WonderType.GARDENS);
		assertEquals("Hanging Gardens", name);
		name = Wonder.getShortNameByType(WonderType.LIGHTHOUSE);
		assertEquals("Lighthouse", name);
		name = Wonder.getShortNameByType(WonderType.MAUSOLEUM);
		assertEquals("Mausoleum", name);
		name = Wonder.getShortNameByType(WonderType.PYRAMIDS);
		assertEquals("Pyramids", name);
		name = Wonder.getShortNameByType(WonderType.STATUE);
		assertEquals("Statue of Zeus", name);
		name = Wonder.getShortNameByType(WonderType.TEMPLE);
		assertEquals("Temple of Artemis", name);		
	}
}