package dataStructureTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import backend.factories.LevelFactory;
import constants.GeneralEnums.Side;
import constants.WonderInfo;
import dataStructures.gameMaterials.AbilityEffect;
import dataStructures.gameMaterials.Effect;
import dataStructures.gameMaterials.Effect.EffectType;
import dataStructures.gameMaterials.EntityEffect;
import dataStructures.gameMaterials.Level;
import dataStructures.gameMaterials.Level.Frequency;
import dataStructures.gameMaterials.ValueEffect;
import dataStructures.gameMaterials.Wonder;
import dataStructures.gameMaterials.Wonder.WonderType;
import testHelpers.LevelBuilderTestHelper;

public class WonderTest {
	// BEGIN GENERATED CODE
	Wonder wonder;
	LevelBuilderTestHelper helper;

	@Before
	public void setUp() {
		this.helper = new LevelBuilderTestHelper();
	}

	@Test
	public void testEqualsTrue() {
		Wonder wonder1 = new Wonder(Side.A, WonderType.COLOSSUS);
		assertTrue(wonder1.equals(wonder1));

		Wonder wonder2 = new Wonder(Side.A, WonderType.COLOSSUS);
		Wonder wonder3 = new Wonder(Side.A, WonderType.COLOSSUS);
		assertTrue(wonder2.equals(wonder3));
	}

	@Test
	public void testEqualsFalse() {
		Wonder wonder1 = new Wonder(Side.A, WonderType.COLOSSUS);
		Wonder wonder2 = new Wonder(Side.A, WonderType.STATUE);
		assertFalse(wonder1.equals(wonder2));

		wonder1 = new Wonder(Side.B, WonderType.COLOSSUS);
		wonder2 = new Wonder(Side.B, WonderType.STATUE);
		assertFalse(wonder1.equals(wonder2));

		wonder1 = new Wonder(Side.B, WonderType.COLOSSUS);
		wonder2 = new Wonder(Side.A, WonderType.COLOSSUS);
		assertFalse(wonder1.equals(wonder2));

		wonder1 = new Wonder(Side.B, WonderType.COLOSSUS);
		wonder2 = new Wonder(Side.A, WonderType.COLOSSUS);
		assertFalse(wonder1.equals(wonder2));
	}

	@Test
	public void getLevels() {
		Wonder wonder = new Wonder(Side.A, WonderType.COLOSSUS);
		HashSet<Level> levels = new HashSet<Level>();
		Level level = EasyMock.createStrictMock(Level.class);
		levels.add(level);
		levels.add(level);
		wonder.setLevels(levels);

		assertEquals(levels, wonder.getLevels());
	}

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

	@Test
	public void testBuildNextLevelColossusSideA() {
		setWonder(Side.A, WonderType.COLOSSUS);
		LevelFactory factory = new LevelFactory(this.wonder);
		this.helper.setWonder(this.wonder);

		HashMap<Frequency, HashSet<Effect>> expected = this.helper.getExpectedLevel(1).getEffects();
		HashMap<Frequency, HashSet<Effect>> actual = this.wonder.buildNextLevel().getEffects();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(2).getEffects();
		actual = this.wonder.buildNextLevel().getEffects();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(3).getEffects();
		actual = this.wonder.buildNextLevel().getEffects();
		assertTrue(equalEffects(expected, actual));
	}

	@Test
	public void testBuildNextLevelColossusSideB() {
		setWonder(Side.B, WonderType.COLOSSUS);
		LevelFactory factory = new LevelFactory(this.wonder);
		this.helper.setWonder(this.wonder);

		HashMap<Frequency, HashSet<Effect>> expected = this.helper.getExpectedLevel(1).getEffects();
		HashMap<Frequency, HashSet<Effect>> actual = this.wonder.buildNextLevel().getEffects();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(2).getEffects();
		actual = this.wonder.buildNextLevel().getEffects();
		assertTrue(equalEffects(expected, actual));
	}

	@Test
	public void testBuildNextLevelLighthouseSideA() {
		setWonder(Side.A, WonderType.LIGHTHOUSE);
		LevelFactory factory = new LevelFactory(this.wonder);
		this.helper.setWonder(this.wonder);

		HashMap<Frequency, HashSet<Effect>> expected = this.helper.getExpectedLevel(1).getEffects();
		HashMap<Frequency, HashSet<Effect>> actual = this.wonder.buildNextLevel().getEffects();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(2).getEffects();
		actual = this.wonder.buildNextLevel().getEffects();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(3).getEffects();
		actual = this.wonder.buildNextLevel().getEffects();
		assertTrue(equalEffects(expected, actual));
	}

	@Test
	public void testBuildNextLevelLighthouseSideB() {
		setWonder(Side.B, WonderType.LIGHTHOUSE);
		LevelFactory factory = new LevelFactory(this.wonder);
		this.helper.setWonder(this.wonder);

		HashMap<Frequency, HashSet<Effect>> expected = this.helper.getExpectedLevel(1).getEffects();
		HashMap<Frequency, HashSet<Effect>> actual = this.wonder.buildNextLevel().getEffects();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(2).getEffects();
		actual = this.wonder.buildNextLevel().getEffects();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(3).getEffects();
		actual = this.wonder.buildNextLevel().getEffects();
		assertTrue(equalEffects(expected, actual));
	}

	@Test
	public void testBuildNextLevelTempleSideA() {
		setWonder(Side.A, WonderType.TEMPLE);
		LevelFactory factory = new LevelFactory(this.wonder);
		this.helper.setWonder(this.wonder);

		HashMap<Frequency, HashSet<Effect>> expected = this.helper.getExpectedLevel(1).getEffects();
		HashMap<Frequency, HashSet<Effect>> actual = this.wonder.buildNextLevel().getEffects();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(2).getEffects();
		actual = this.wonder.buildNextLevel().getEffects();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(3).getEffects();
		actual = this.wonder.buildNextLevel().getEffects();
		assertTrue(equalEffects(expected, actual));
	}

	@Test
	public void testBuildNextLevelTempleSideB() {
		setWonder(Side.B, WonderType.TEMPLE);
		LevelFactory factory = new LevelFactory(this.wonder);
		this.helper.setWonder(this.wonder);

		HashMap<Frequency, HashSet<Effect>> expected = this.helper.getExpectedLevel(1).getEffects();
		HashMap<Frequency, HashSet<Effect>> actual = this.wonder.buildNextLevel().getEffects();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(2).getEffects();
		actual = this.wonder.buildNextLevel().getEffects();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(3).getEffects();
		actual = this.wonder.buildNextLevel().getEffects();
		assertTrue(equalEffects(expected, actual));
	}

	@Test
	public void testBuildNextLevelStatueSideA() {
		setWonder(Side.A, WonderType.STATUE);
		LevelFactory factory = new LevelFactory(this.wonder);
		this.helper.setWonder(this.wonder);

		HashMap<Frequency, HashSet<Effect>> expected = this.helper.getExpectedLevel(1).getEffects();
		HashMap<Frequency, HashSet<Effect>> actual = this.wonder.buildNextLevel().getEffects();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(2).getEffects();
		actual = this.wonder.buildNextLevel().getEffects();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(3).getEffects();
		actual = this.wonder.buildNextLevel().getEffects();
		assertTrue(equalEffects(expected, actual));
	}

	@Test
	public void testBuildNextLevelStatueSideB() {
		setWonder(Side.B, WonderType.STATUE);
		LevelFactory factory = new LevelFactory(this.wonder);
		this.helper.setWonder(this.wonder);

		HashMap<Frequency, HashSet<Effect>> expected = this.helper.getExpectedLevel(1).getEffects();
		HashMap<Frequency, HashSet<Effect>> actual = this.wonder.buildNextLevel().getEffects();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(2).getEffects();
		actual = this.wonder.buildNextLevel().getEffects();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(3).getEffects();
		actual = this.wonder.buildNextLevel().getEffects();
		assertTrue(equalEffects(expected, actual));
	}

	@Test
	public void testBuildNextLevelGardensSideA() {
		setWonder(Side.A, WonderType.GARDENS);
		LevelFactory factory = new LevelFactory(this.wonder);
		this.helper.setWonder(this.wonder);

		HashMap<Frequency, HashSet<Effect>> expected = this.helper.getExpectedLevel(1).getEffects();
		HashMap<Frequency, HashSet<Effect>> actual = this.wonder.buildNextLevel().getEffects();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(2).getEffects();
		actual = this.wonder.buildNextLevel().getEffects();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(3).getEffects();
		actual = this.wonder.buildNextLevel().getEffects();
		assertTrue(equalEffects(expected, actual));
	}

	@Test
	public void testBuildNextLevelGardensSideB() {
		setWonder(Side.B, WonderType.GARDENS);
		LevelFactory factory = new LevelFactory(this.wonder);
		this.helper.setWonder(this.wonder);

		HashMap<Frequency, HashSet<Effect>> expected = this.helper.getExpectedLevel(1).getEffects();
		HashMap<Frequency, HashSet<Effect>> actual = this.wonder.buildNextLevel().getEffects();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(2).getEffects();
		actual = this.wonder.buildNextLevel().getEffects();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(3).getEffects();
		actual = this.wonder.buildNextLevel().getEffects();
		assertTrue(equalEffects(expected, actual));
	}

	@Test
	public void testBuildNextLevelMausoleumSideA() {
		setWonder(Side.A, WonderType.MAUSOLEUM);
		LevelFactory factory = new LevelFactory(this.wonder);
		this.helper.setWonder(this.wonder);

		HashMap<Frequency, HashSet<Effect>> expected = this.helper.getExpectedLevel(1).getEffects();
		HashMap<Frequency, HashSet<Effect>> actual = this.wonder.buildNextLevel().getEffects();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(2).getEffects();
		actual = this.wonder.buildNextLevel().getEffects();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(3).getEffects();
		actual = this.wonder.buildNextLevel().getEffects();
		assertTrue(equalEffects(expected, actual));
	}

	@Test
	public void testBuildNextLevelMausoleumSideB() {
		setWonder(Side.B, WonderType.MAUSOLEUM);
		LevelFactory factory = new LevelFactory(this.wonder);
		this.helper.setWonder(this.wonder);

		HashMap<Frequency, HashSet<Effect>> expected = this.helper.getExpectedLevel(1).getEffects();
		HashMap<Frequency, HashSet<Effect>> actual = this.wonder.buildNextLevel().getEffects();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(2).getEffects();
		actual = this.wonder.buildNextLevel().getEffects();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(3).getEffects();
		actual = this.wonder.buildNextLevel().getEffects();
		assertTrue(equalEffects(expected, actual));
	}

	@Test
	public void testBuildNextLevelPyramidsSideA() {
		setWonder(Side.A, WonderType.PYRAMIDS);
		LevelFactory factory = new LevelFactory(this.wonder);
		this.helper.setWonder(this.wonder);

		HashMap<Frequency, HashSet<Effect>> expected = this.helper.getExpectedLevel(1).getEffects();
		HashMap<Frequency, HashSet<Effect>> actual = this.wonder.buildNextLevel().getEffects();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(2).getEffects();
		actual = this.wonder.buildNextLevel().getEffects();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(3).getEffects();
		actual = this.wonder.buildNextLevel().getEffects();
		assertTrue(equalEffects(expected, actual));
	}

	@Test
	public void testBuildNextLevelPyramidsSideB() {
		setWonder(Side.B, WonderType.PYRAMIDS);
		LevelFactory factory = new LevelFactory(this.wonder);
		this.helper.setWonder(this.wonder);

		HashMap<Frequency, HashSet<Effect>> expected = this.helper.getExpectedLevel(1).getEffects();
		HashMap<Frequency, HashSet<Effect>> actual = this.wonder.buildNextLevel().getEffects();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(2).getEffects();
		actual = this.wonder.buildNextLevel().getEffects();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(3).getEffects();
		actual = this.wonder.buildNextLevel().getEffects();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(4).getEffects();
		actual = this.wonder.buildNextLevel().getEffects();
		assertTrue(equalEffects(expected, actual));
	}

	private void setWonder(Side side, WonderType type) {
		this.wonder = new Wonder(side, type);
	}

	private boolean equalEffects(HashMap<Frequency, HashSet<Effect>> expected,
			HashMap<Frequency, HashSet<Effect>> actual) {
		for (Frequency frequency : expected.keySet()) {
			if (!actual.containsKey(frequency)) {
				return false;
			} else if (!compareEffects(frequency, expected.get(frequency), actual.get(frequency))) {
				return false;
			} else {
				continue;
			}
		}
		return true;
	}

	private boolean compareEffects(Frequency frequency, HashSet<Effect> expected, HashSet<Effect> actual) {
		for (Effect effect : expected) {
			if (otherContainsEffect(actual, effect)) {
				continue;
			} else {
				return false;
			}
		}
		return true;
	}

	private boolean otherContainsEffect(HashSet<Effect> actual, Effect expectedEffect) {
		for (Effect otherEffect : actual) {
			EffectType type = expectedEffect.getEffectType();
			switch (type) {
			case ABILITY:
				if (((AbilityEffect) expectedEffect).equals((AbilityEffect) otherEffect)) {
					return true;
				}
				break;
			case VALUE:
				if (((ValueEffect) expectedEffect).equals((ValueEffect) otherEffect)) {
					return true;
				}
				break;
			case ENTITY:
				if (((EntityEffect) expectedEffect).equals((EntityEffect) otherEffect)) {
					return true;
				}
				break;
			default:
				throw new IllegalArgumentException("Invalid Effect Type");
			}
		}
		return false;
	}
	// END GENERATED CODE
}