package dataStructureTests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import constants.WonderInfo;
import dataStructures.gameMaterials.Wonder.WonderType;

public class WonderInfoTest {
	WonderInfo wonderInfo;
	
	@Before
	public void setUp() {
		this.wonderInfo = new WonderInfo();
	}

	@Test
	public void testGetWonderNameByTypeValid() {
		String name = this.wonderInfo.getNameByType(WonderType.COLOSSUS);
		assertEquals("The Colossus of Rhodes", name);
		name = this.wonderInfo.getNameByType(WonderType.GARDENS);
		assertEquals("The Hanging Gardens of Babylon", name);
		name = this.wonderInfo.getNameByType(WonderType.LIGHTHOUSE);
		assertEquals("The Lighthouse of Alexandria", name);
		name = this.wonderInfo.getNameByType(WonderType.MAUSOLEUM);
		assertEquals("The Mausoleum of Halicarnassus", name);
		name = this.wonderInfo.getNameByType(WonderType.PYRAMIDS);
		assertEquals("The Pyramids of Giza", name);
		name = this.wonderInfo.getNameByType(WonderType.STATUE);
		assertEquals("The Statue of Zeus in Olympia", name);
		name = this.wonderInfo.getNameByType(WonderType.TEMPLE);
		assertEquals("The Temple of Artemis in Ephesus", name);
	}

	@Test
	public void testGetShortNameByTypeValid() {
		String name = this.wonderInfo.getShortNameByType(WonderType.COLOSSUS);
		assertEquals("Colossus", name);
		name = this.wonderInfo.getShortNameByType(WonderType.GARDENS);
		assertEquals("Hanging Gardens", name);
		name = this.wonderInfo.getShortNameByType(WonderType.LIGHTHOUSE);
		assertEquals("Lighthouse", name);
		name = this.wonderInfo.getShortNameByType(WonderType.MAUSOLEUM);
		assertEquals("Mausoleum", name);
		name = this.wonderInfo.getShortNameByType(WonderType.PYRAMIDS);
		assertEquals("Pyramids", name);
		name = this.wonderInfo.getShortNameByType(WonderType.STATUE);
		assertEquals("Statue of Zeus", name);
		name = this.wonderInfo.getShortNameByType(WonderType.TEMPLE);
		assertEquals("Temple of Artemis", name);
	}
}
