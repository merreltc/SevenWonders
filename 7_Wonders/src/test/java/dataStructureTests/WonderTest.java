package dataStructureTests;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

import constants.GeneralEnums.Side;
import constants.WonderInfo;
import dataStructures.gameMaterials.EntityEffect;
import dataStructures.gameMaterials.Wonder;
import dataStructures.gameMaterials.Wonder.WonderType;

public class WonderTest {

	@Test
	public void testResource() {
		WonderInfo info = new WonderInfo();
		HashMap<WonderType, String> wonders = new HashMap<WonderType, String>();
		wonders.put(WonderType.COLOSSUS, "The Colossus of Rhodes");
		wonders.put(WonderType.LIGHTHOUSE, "The Lighthouse of Alexandria");
		wonders.put(WonderType.TEMPLE, "The Temple of Artemis in Ephesus");
		wonders.put(WonderType.GARDENS, "The Hanging Gardens of Babylon");
		wonders.put(WonderType.STATUE, "The Statue of Zeus in Olympia");
		wonders.put(WonderType.MAUSOLEUM, "The Mausoleum of Halicarnassus");
		wonders.put(WonderType.PYRAMIDS, "The Pyramids of Giza");

		for (WonderType type : wonders.keySet()) {
			Wonder wonder = new Wonder(Side.A, type);
			EntityEffect resource = info.getResourceByType(type);
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

		for (WonderType type : wonders.keySet()) {
			Wonder wonder = new Wonder(Side.A, type);
			verifyWonderSideA(wonder, type, wonders.get(type));
		}
	}

	private void verifyWonderSideA(Wonder wonder, WonderType type, String name) {
		assertEquals(Side.A, wonder.getSide());
		assertEquals(3, wonder.getNumLevels());
		assertEquals(type, wonder.getType());
		assertEquals(name, wonder.getName());
	}

	@Test
	public void testBasicsSideB2Levels() {
		Wonder wonder = new Wonder(Side.B, WonderType.COLOSSUS);

		assertEquals(Side.B, wonder.getSide());
		assertEquals(2, wonder.getNumLevels());
		assertEquals("The Colossus of Rhodes", wonder.getName());
	}

	@Test
	public void testBasicsSideB3Levels() {
		Wonder wonder = new Wonder(Side.B, WonderType.TEMPLE);

		assertEquals(Side.B, wonder.getSide());
		assertEquals(3, wonder.getNumLevels());
		assertEquals("The Temple of Artemis in Ephesus", wonder.getName());
	}

	@Test
	public void testBasicsSideB4Levels() {
		Wonder wonder = new Wonder(Side.B, WonderType.PYRAMIDS);

		assertEquals(Side.B, wonder.getSide());
		assertEquals(4, wonder.getNumLevels());
		assertEquals("The Pyramids of Giza", wonder.getName());
	}
}