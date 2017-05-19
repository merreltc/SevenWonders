package backendTests;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import backend.factories.LevelBuilder;
import constants.GeneralEnums.CostType;
import constants.GeneralEnums.Side;
import dataStructures.gameMaterials.Cost;
import dataStructures.gameMaterials.Effect;
import dataStructures.gameMaterials.Level;
import dataStructures.gameMaterials.Level.Frequency;
import dataStructures.gameMaterials.Wonder;
import dataStructures.gameMaterials.Wonder.WonderType;
import testHelpers.LevelBuilderTestHelper;

public class LevelBuilderTest {
	Wonder wonder;
	LevelBuilderTestHelper helper;

	@Before
	public void setUp() {
		this.helper = new LevelBuilderTestHelper();
	}

	@Test
	public void testBuildColossusSideA() {
		setWonder(Side.A, WonderType.COLOSSUS);
		LevelBuilder builder = new LevelBuilder(this.wonder);
		this.helper.setWonder(this.wonder);

		Level expected = this.helper.getExpectedLevel(1);
		Level actual = builder.buildLevel(1);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(2);
		actual = builder.buildLevel(2);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(3);
		actual = builder.buildLevel(3);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void testBuildColossusSideB() {
		setWonder(Side.B, WonderType.COLOSSUS);
		LevelBuilder builder = new LevelBuilder(this.wonder);
		this.helper.setWonder(this.wonder);

		Level expected = this.helper.getExpectedLevel(1);
		Level actual = builder.buildLevel(1);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(2);
		actual = builder.buildLevel(2);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void testBuildLighthouseSideA() {
		setWonder(Side.A, WonderType.LIGHTHOUSE);
		LevelBuilder builder = new LevelBuilder(this.wonder);
		this.helper.setWonder(this.wonder);

		Level expected = this.helper.getExpectedLevel(1);
		Level actual = builder.buildLevel(1);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(2);
		actual = builder.buildLevel(2);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(3);
		actual = builder.buildLevel(3);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void testBuildLighthouseSideB() {
		setWonder(Side.B, WonderType.LIGHTHOUSE);
		LevelBuilder builder = new LevelBuilder(this.wonder);
		this.helper.setWonder(this.wonder);

		Level expected = this.helper.getExpectedLevel(1);
		Level actual = builder.buildLevel(1);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(2);
		actual = builder.buildLevel(2);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(3);
		actual = builder.buildLevel(3);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void testBuildTempleSideA() {
		setWonder(Side.A, WonderType.TEMPLE);
		LevelBuilder builder = new LevelBuilder(this.wonder);
		this.helper.setWonder(this.wonder);

		Level expected = this.helper.getExpectedLevel(1);
		Level actual = builder.buildLevel(1);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(2);
		actual = builder.buildLevel(2);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(3);
		actual = builder.buildLevel(3);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void testBuildTempleSideB() {
		setWonder(Side.B, WonderType.TEMPLE);
		LevelBuilder builder = new LevelBuilder(this.wonder);
		this.helper.setWonder(this.wonder);

		Level expected = this.helper.getExpectedLevel(1);
		Level actual = builder.buildLevel(1);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(2);
		actual = builder.buildLevel(2);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(3);
		actual = builder.buildLevel(3);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void testBuildStatueSideA() {
		setWonder(Side.A, WonderType.STATUE);
		LevelBuilder builder = new LevelBuilder(this.wonder);
		this.helper.setWonder(this.wonder);

		Level expected = this.helper.getExpectedLevel(1);
		Level actual = builder.buildLevel(1);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(2);
		actual = builder.buildLevel(2);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(3);
		actual = builder.buildLevel(3);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void testBuildStatueSideB() {
		setWonder(Side.B, WonderType.STATUE);
		LevelBuilder builder = new LevelBuilder(this.wonder);
		this.helper.setWonder(this.wonder);

		Level expected = this.helper.getExpectedLevel(1);
		Level actual = builder.buildLevel(1);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(2);
		actual = builder.buildLevel(2);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(3);
		actual = builder.buildLevel(3);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void testBuildGardensSideA() {
		setWonder(Side.A, WonderType.GARDENS);
		LevelBuilder builder = new LevelBuilder(this.wonder);
		this.helper.setWonder(this.wonder);

		Level expected = this.helper.getExpectedLevel(1);
		Level actual = builder.buildLevel(1);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(2);
		actual = builder.buildLevel(2);
		System.out.println("Gard:A");
		System.out.println("EX " + expected);
		System.out.println("AC " + actual);
		System.out.println("Equal? " + expected.equals(actual));
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(3);
		actual = builder.buildLevel(3);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void testBuildGardensSideB() {
		setWonder(Side.B, WonderType.GARDENS);
		LevelBuilder builder = new LevelBuilder(this.wonder);
		this.helper.setWonder(this.wonder);

		Level expected = this.helper.getExpectedLevel(1);
		Level actual = builder.buildLevel(1);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(2);
		actual = builder.buildLevel(2);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(3);
		actual = builder.buildLevel(3);
		System.out.println("Gard:B");
		System.out.println("EX " + expected);
		System.out.println("AC " + actual);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void testBuildMausoleumSideA() {
		setWonder(Side.A, WonderType.MAUSOLEUM);
		LevelBuilder builder = new LevelBuilder(this.wonder);
		this.helper.setWonder(this.wonder);

		Level expected = this.helper.getExpectedLevel(1);
		Level actual = builder.buildLevel(1);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(2);
		actual = builder.buildLevel(2);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(3);
		actual = builder.buildLevel(3);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void testBuildMausoleumSideB() {
		setWonder(Side.B, WonderType.MAUSOLEUM);
		LevelBuilder builder = new LevelBuilder(this.wonder);
		this.helper.setWonder(this.wonder);

		Level expected = this.helper.getExpectedLevel(1);
		Level actual = builder.buildLevel(1);

		assertTrue(expected.equals(actual));
		expected = this.helper.getExpectedLevel(2);
		actual = builder.buildLevel(2);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(3);
		actual = builder.buildLevel(3);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void testBuildPyramidsSideA() {
		setWonder(Side.A, WonderType.PYRAMIDS);
		LevelBuilder builder = new LevelBuilder(this.wonder);
		this.helper.setWonder(this.wonder);

		Level expected = this.helper.getExpectedLevel(1);
		Level actual = builder.buildLevel(1);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(2);
		actual = builder.buildLevel(2);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(3);
		actual = builder.buildLevel(3);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void testBuildPyramidsSideB() {
		setWonder(Side.B, WonderType.PYRAMIDS);
		LevelBuilder builder = new LevelBuilder(this.wonder);
		this.helper.setWonder(this.wonder);

		Level expected = this.helper.getExpectedLevel(1);
		Level actual = builder.buildLevel(1);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(2);
		actual = builder.buildLevel(2);
		assertTrue(expected.equals(actual));

		expected = this.helper.getExpectedLevel(3);
		actual = builder.buildLevel(3);
		assertTrue(expected.equals(actual));
		
		expected = this.helper.getExpectedLevel(4);
		actual = builder.buildLevel(4);
		assertTrue(expected.equals(actual));
	}

	private void setWonder(Side side, WonderType type) {
		this.wonder = new Wonder(side, type);
	}

}
