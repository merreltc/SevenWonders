import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import constants.GeneralEnums.Side;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dataStructures.gameMaterials.Effect;
import dataStructures.gameMaterials.Level;
import dataStructures.gameMaterials.Wonder;
import dataStructures.gameMaterials.Wonder.WonderType;
import exceptions.CannotBuildWonderException;
import exceptions.InsufficientFundsException;
import testHelpers.LevelBuilderTestHelper;

public class BuildWonderSteps {
	LevelBuilderTestHelper helper;
	ArrayList<Level> expectedLevels;
	Wonder wonder;

	CannotBuildWonderException wonderException;
	InsufficientFundsException fundsException;
	ExceptionBehavior exceptionBehavior;

	public enum ExceptionBehavior {
		NOWONDERS, NOFUNDS
	}

	@Before
	public void set_up() {
		this.helper = new LevelBuilderTestHelper();
		this.expectedLevels = new ArrayList<Level>();
	}

	@Given("^A Wonder (Colossus||Lighthouse||Temple||Statue||Mausoleum||Gardens||Pyramids) on Side (.)$")
	public void a_Wonder_on_Side(String wonderName, char side) throws Throwable {
		WonderType type = getWonderType(wonderName);
		Side newSide = getSide(side);
		this.wonder = new Wonder(newSide, type);

		helper.setWonder(this.wonder);
	}

	@Given("^(\\d+) expected Levels$")
	public void expected_Levels(int numLevels) throws Throwable {
		this.expectedLevels = createLevels(numLevels);
		System.err.println(this.expectedLevels);
	}

	@When("^Building the wonder (\\d+) times$")
	public void the_builds_the_wonder(int numLevels) throws Throwable {
		for (int i = 0; i < numLevels; i++) {
			tryBuildThroughWonder();
		}
	}

	@Then("^The number of levels built equals (\\d+)$")
	public void the_number_of_levels_built_equals(int numLevels) throws Throwable {
		assertEquals(numLevels, this.wonder.getNumBuiltLevels());
	}

	@Then("^The level is built$")
	public void the_wonder_level_is_built() throws Throwable {
		assertEquals(this.expectedLevels, this.wonder.getLevels());
	}

	@Then("^The level cannot be built$")
	public void the_level_cannot_be_built() throws Throwable {
		switch (exceptionBehavior) {
		case NOWONDERS:
			assertEquals("Player has built max number of levels.", this.wonderException.getMessage());
			break;
		case NOFUNDS:
			assertEquals("Cannot build Structure: Player doesn't not have the required items",
					this.fundsException.getMessage());
			break;
		default:
			throw new IllegalArgumentException("Invalid exception behavior type");
		}
	}

	public WonderType getWonderType(String wonder) {
		switch (wonder) {
		case "Colossus":
			return WonderType.COLOSSUS;
		case "Lighthouse":
			return WonderType.LIGHTHOUSE;
		case "Temple":
			return WonderType.TEMPLE;
		case "Gardens":
			return WonderType.GARDENS;
		case "Statue":
			return WonderType.STATUE;
		case "Mausoleum":
			return WonderType.MAUSOLEUM;
		case "Pyramids":
			return WonderType.PYRAMIDS;
		default:
			throw new IllegalArgumentException("Invalid wonder type");
		}
	}

	public Side getSide(char side) {
		if (side == 'A') {
			return Side.A;
		} else {
			return Side.B;
		}
	}

	private ArrayList<Level> createLevels(int numLevels) {
		ArrayList<Level> result = new ArrayList<Level>();
		for (int i = 1; i <= numLevels; i++) {
			Level level = this.helper.getExpectedLevel(i);
			result.add(level);
		}

		return result;
	}

	private void tryBuildThroughWonder() {
		try {
			this.wonder.buildNextLevel();
		} catch (CannotBuildWonderException e) {
			this.wonderException = e;
			this.exceptionBehavior = ExceptionBehavior.NOWONDERS;
		}
	}
}
