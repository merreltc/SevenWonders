package backendTests;

import static org.junit.Assert.assertTrue;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import backend.factories.LevelFactory;
import constants.GeneralEnums.Side;
import dataStructures.gameMaterials.Level;
import dataStructures.gameMaterials.Wonder;
import dataStructures.gameMaterials.Wonder.WonderType;
import testHelpers.LevelBuilderTestHelper;

public class LevelFactoryTest {
	// BEGIN GENERATED CODE
	Wonder wonder;
	LevelBuilderTestHelper helper;

	@Before
	public void setUp() {
		this.helper = new LevelBuilderTestHelper();
	}

	@Test
	public void testGetNextLevelColossusSideA() {
		setWonder(Side.A, WonderType.COLOSSUS);
		LevelFactory factory = new LevelFactory(this.wonder);
		this.helper.setWonder(this.wonder);

		Level expected = this.helper.getExpectedLevel(1);
		Level actual = factory.getNextLevel(1);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(2);
		actual = factory.getNextLevel(2);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(3);
		actual = factory.getNextLevel(3);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void testGetNextLevelColossusSideB() {
		setWonder(Side.B, WonderType.COLOSSUS);
		LevelFactory factory = new LevelFactory(this.wonder);
		this.helper.setWonder(this.wonder);

		Level expected = this.helper.getExpectedLevel(1);
		Level actual = factory.getNextLevel(1);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(2);
		actual = factory.getNextLevel(2);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void testGetNextLevelLighthouseSideA() {
		setWonder(Side.A, WonderType.LIGHTHOUSE);
		LevelFactory factory = new LevelFactory(this.wonder);
		this.helper.setWonder(this.wonder);

		Level expected = this.helper.getExpectedLevel(1);
		Level actual = factory.getNextLevel(1);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(2);
		actual = factory.getNextLevel(2);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(3);
		actual = factory.getNextLevel(3);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void testGetNextLevelLighthouseSideB() {
		setWonder(Side.B, WonderType.LIGHTHOUSE);
		LevelFactory factory = new LevelFactory(this.wonder);
		this.helper.setWonder(this.wonder);

		Level expected = this.helper.getExpectedLevel(1);
		Level actual = factory.getNextLevel(1);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(2);
		actual = factory.getNextLevel(2);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(3);
		actual = factory.getNextLevel(3);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void testGetNextLevelTempleSideA() {
		setWonder(Side.A, WonderType.TEMPLE);
		LevelFactory factory = new LevelFactory(this.wonder);
		this.helper.setWonder(this.wonder);

		Level expected = this.helper.getExpectedLevel(1);
		Level actual = factory.getNextLevel(1);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(2);
		actual = factory.getNextLevel(2);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(3);
		actual = factory.getNextLevel(3);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void testGetNextLevelTempleSideB() {
		setWonder(Side.B, WonderType.TEMPLE);
		LevelFactory factory = new LevelFactory(this.wonder);
		this.helper.setWonder(this.wonder);

		Level expected = this.helper.getExpectedLevel(1);
		Level actual = factory.getNextLevel(1);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(2);
		actual = factory.getNextLevel(2);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(3);
		actual = factory.getNextLevel(3);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void testGetNextLevelStatueSideA() {
		setWonder(Side.A, WonderType.STATUE);
		LevelFactory factory = new LevelFactory(this.wonder);
		this.helper.setWonder(this.wonder);

		Level expected = this.helper.getExpectedLevel(1);
		Level actual = factory.getNextLevel(1);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(2);
		actual = factory.getNextLevel(2);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(3);
		actual = factory.getNextLevel(3);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void testGetNextLevelStatueSideB() {
		setWonder(Side.B, WonderType.STATUE);
		LevelFactory factory = new LevelFactory(this.wonder);
		this.helper.setWonder(this.wonder);

		Level expected = this.helper.getExpectedLevel(1);
		Level actual = factory.getNextLevel(1);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(2);
		actual = factory.getNextLevel(2);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(3);
		actual = factory.getNextLevel(3);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void testGetNextLevelGardensSideA() {
		setWonder(Side.A, WonderType.GARDENS);
		LevelFactory factory = new LevelFactory(this.wonder);
		this.helper.setWonder(this.wonder);

		Level expected = this.helper.getExpectedLevel(1);
		Level actual = factory.getNextLevel(1);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(2);
		actual = factory.getNextLevel(2);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(3);
		actual = factory.getNextLevel(3);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void testGetNextLevelGardensSideB() {
		setWonder(Side.B, WonderType.GARDENS);
		LevelFactory factory = new LevelFactory(this.wonder);
		this.helper.setWonder(this.wonder);

		Level expected = this.helper.getExpectedLevel(1);
		Level actual = factory.getNextLevel(1);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(2);
		actual = factory.getNextLevel(2);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(3);
		actual = factory.getNextLevel(3);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void testGetNextLevelMausoleumSideA() {
		setWonder(Side.A, WonderType.MAUSOLEUM);
		LevelFactory factory = new LevelFactory(this.wonder);
		this.helper.setWonder(this.wonder);

		Level expected = this.helper.getExpectedLevel(1);
		Level actual = factory.getNextLevel(1);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(2);
		actual = factory.getNextLevel(2);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(3);
		actual = factory.getNextLevel(3);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void testGetNextLevelMausoleumSideB() {
		setWonder(Side.B, WonderType.MAUSOLEUM);
		LevelFactory factory = new LevelFactory(this.wonder);
		this.helper.setWonder(this.wonder);

		Level expected = this.helper.getExpectedLevel(1);
		Level actual = factory.getNextLevel(1);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(2);
		actual = factory.getNextLevel(2);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(3);
		actual = factory.getNextLevel(3);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void testGetNextLevelPyramidsSideA() {
		setWonder(Side.A, WonderType.PYRAMIDS);
		LevelFactory factory = new LevelFactory(this.wonder);
		this.helper.setWonder(this.wonder);

		Level expected = this.helper.getExpectedLevel(1);
		Level actual = factory.getNextLevel(1);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(2);
		actual = factory.getNextLevel(2);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(3);
		actual = factory.getNextLevel(3);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void testGetNextLevelPyramidsideB() {
		setWonder(Side.B, WonderType.PYRAMIDS);
		LevelFactory factory = new LevelFactory(this.wonder);
		this.helper.setWonder(this.wonder);

		Level expected = this.helper.getExpectedLevel(1);
		Level actual = factory.getNextLevel(1);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(2);
		actual = factory.getNextLevel(2);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(3);
		actual = factory.getNextLevel(3);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(4);
		actual = factory.getNextLevel(4);
		assertTrue(expected.equals(actual));
	}

	private void setWonder(Side side, WonderType type) {
		this.wonder = new Wonder(side, type);
	}
	// END GENERATED CODE
}
