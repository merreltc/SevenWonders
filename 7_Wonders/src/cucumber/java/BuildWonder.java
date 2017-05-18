import constants.GeneralEnums.Side;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dataStructures.gameMaterials.Wonder;
import dataStructures.gameMaterials.Wonder.WonderType;
import exceptions.CannotBuiltWonderException;
import static org.junit.Assert.*;

public class BuildWonder {
	private Wonder wonder;
	private CannotBuiltWonderException exception;

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

	@When("^Building the wonder (\\d+) times$")
	public void the_builds_the_wonder(int times) throws Throwable {
		for (int i = 0; i < times; i++) {
			try {
				this.wonder.buildNextLevel();
			} catch (CannotBuiltWonderException e) {
				this.exception = e;
			}
		}
	}

	@Then("^The player cannot build that level$")
	public void the_player_cannot_build_that_level() throws Throwable {
		System.out.println("Exception " + this.exception);
		assertEquals("Player has built max number of levels.", this.exception.getMessage());
	}
}
