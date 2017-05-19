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

	private void setWonder(Side side, WonderType type) {
		this.wonder = new Wonder(side, type);
	}

}
