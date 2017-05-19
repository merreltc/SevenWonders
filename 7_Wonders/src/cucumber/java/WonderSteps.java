import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import constants.GeneralEnums.CostType;
import constants.GeneralEnums.Side;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dataStructures.gameMaterials.Cost;
import dataStructures.gameMaterials.Effect;
import dataStructures.gameMaterials.Effect.EffectType;
import dataStructures.gameMaterials.EntityEffect;
import dataStructures.gameMaterials.Level;
import dataStructures.gameMaterials.Level.Frequency;
import dataStructures.gameMaterials.Wonder;
import dataStructures.gameMaterials.Wonder.WonderType;
import dataStructures.playerData.Player;
import exceptions.CannotBuildWonderException;

public class WonderSteps {
	private Wonder wonder;
	private CannotBuildWonderException exception;
	private ArrayList<Level> levels;
	private Player player;

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
	
	@Given("^The player has the resources for that Wonder$")
	public void the_player_has_the_resources_for_that_Wonder() throws Throwable {
		giveResourcesToPlayer();
		
	}
	
	private void giveResourcesToPlayer(){
		
	}

	@Given("^A player with that Wonder$")
	public void a_player_with_that_Wonder() throws Throwable {
		this.player = new Player("Jane Doe", this.wonder);
	}

	@Given("^(\\d+) expected Levels$")
	public void expected_Levels(int numLevels) throws Throwable {
		this.levels = createLevels(numLevels);
	}

	private ArrayList<Level> createLevels(int numLevels) {
		ArrayList<Level> result = new ArrayList<Level>();
		for (int i = 1; i <= numLevels; i++) {
			HashMap<Frequency, HashSet<Effect>> effects = new HashMap<Frequency, HashSet<Effect>>();
			HashSet<Effect> effect = new HashSet<Effect>();
			effect.add(new Effect(EffectType.NONE));
			effects.put(Frequency.DEFAULT, effect);

			Level temp = new Level(i, new Cost(CostType.NONE, 1), effects);
			result.add(temp);
		}
		return result;
	}

	@When("^Building the wonder (\\d+) times$")
	public void the_builds_the_wonder(int numLevels) throws Throwable {
		for (int i = 0; i < numLevels; i++) {
			try {
				this.wonder.buildNextLevel();
			} catch (CannotBuildWonderException e) {
				this.exception = e;
			}
		}
	}

	@When("^Beginning the game$")
	public void beginning_the_game() throws Throwable {
		// does nothing
	}
	
	@Then("^The level is built$")
	public void the_level_is_built() throws Throwable {
		
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

	@Then("^The player begins with the appropriate resource$")
	public void the_player_begins_with_the_appropriate_resource() throws Throwable {
		EntityEffect effect = this.wonder.getResource();
		assertTrue(this.player.storageContainsEffect(effect));
	}

	@Then("^The player does not begin with any other resources$")
	public void the_player_does_not_begin_with_any_other_resources() throws Throwable {
		assertTrue(this.player.getAllEffects().size() == 1);
	}
}
