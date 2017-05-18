import static org.junit.Assert.*;

import java.util.ArrayList;

import constants.GeneralEnums.CostType;
import constants.GeneralEnums.Side;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dataStructures.gameMaterials.Cost;
import dataStructures.gameMaterials.Effect;
import dataStructures.gameMaterials.Effect.EffectType;
import dataStructures.gameMaterials.Level;
import dataStructures.gameMaterials.Level.Frequency;
import dataStructures.gameMaterials.Wonder;
import dataStructures.gameMaterials.Wonder.WonderType;
import exceptions.CannotBuiltWonderException;

public class BuildWonder {
	private Wonder wonder;
	private CannotBuiltWonderException exception;
	private ArrayList<Level> levels;

	@Given("^A Wonder (Colossus||Lighthouse||Temple||Statue||Mausoleum||Gardens||Pyramids) on Side (.)$")
	public void a_player_with_a_Wonder_on_Side(String wonderName, char side) throws Throwable {
		WonderType type = getWonderType(wonderName);
		Side newSide = getSide(side);
		this.wonder = new Wonder(newSide, type);
	}

	public Side getSide(char side) {
		if (side == 'A') {
			return Side.A;
		} else {
			return Side.B;
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

	@Given("^(\\d+) expected Levels$")
	public void expected_Levels(int numLevels) throws Throwable {
		this.levels = createLevels(numLevels);
	}

	private ArrayList<Level> createLevels(int numLevels) {
		ArrayList<Level> result = new ArrayList<Level>();
		for (int i = 1; i <= numLevels; i++) {
			Level temp = new Level(i, new Cost(CostType.NONE, 1), new Effect(EffectType.NONE), Frequency.DEFAULT);
			result.add(temp);
		}
		return result;
	}

	@When("^Building the wonder (\\d+) times$")
	public void the_builds_the_wonder(int numLevels) throws Throwable {
		for (int i = 0; i < numLevels; i++) {
			try {
				this.wonder.buildNextLevel();
			} catch (CannotBuiltWonderException e) {
				this.exception = e;
			}
		}
	}

	@Then("^The level cannot be built$")
	public void the_player_cannot_build_that_level() throws Throwable {
		System.out.println("Exception " + this.exception);
		assertEquals("Player has built max number of levels.", this.exception.getMessage());
	}

	@Then("^The number of levels built equals (\\d+)$")
	public void the_number_of_levels_built_equals(int numLevels) throws Throwable {
		assertEquals(numLevels, this.wonder.getNumBuiltLevels());
	}

//	@Then("^The levels in Wonder match$")
//	public void the_levels_in_Wonder_match() throws Throwable {
//		for (int i = 0; i < this.levels.size(); i++) {
//			Level expected = this.levels.get(i);
//			Level actual = this.wonder.getLevel(i);
//			assertTrue(expected.equals(actual));
//		}
//	}
}
