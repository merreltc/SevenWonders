import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.HashSet;

import backend.handlers.PlayerTurnHandler;
import constants.GeneralEnums.CostType;
import constants.GeneralEnums.Good;
import constants.GeneralEnums.RawResource;
import constants.GeneralEnums.Resource;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dataStructures.gameMaterials.AbilityEffect;
import dataStructures.gameMaterials.Card;
import dataStructures.gameMaterials.Card.CardType;
import dataStructures.gameMaterials.Cost;
import dataStructures.gameMaterials.Effect;
import dataStructures.gameMaterials.EntityEffect;
import dataStructures.gameMaterials.EntityEffect.EntityType;
import dataStructures.gameMaterials.Level;
import dataStructures.gameMaterials.Level.Frequency;
import dataStructures.gameMaterials.ValueEffect;
import dataStructures.playerData.Player;
import exceptions.CannotBuildWonderException;
import exceptions.InsufficientFundsException;

public class PlayerBuildWonderSteps {
	private BuildWonderSteps wonderSteps;
	private Player player;
	private int priority;

	public PlayerBuildWonderSteps(BuildWonderSteps wonderSteps) {
		this.wonderSteps = wonderSteps;
	}

	@Given("^A player with that Wonder$")
	public void a_player_with_that_Wonder() throws Throwable {
		this.player = new Player("Jane Doe", this.wonderSteps.wonder);
	}

	@Given("^The player has the resources for that Wonder$")
	public void the_player_has_the_resources_for_that_Wonder() throws Throwable {
		giveResourcesToPlayer();
	}

	@Given("^The player does not have the resources for that Wonder$")
	public void the_player_does_not_have_the_resources_for_that_Wonder() throws Throwable {
		giveResourcesToPlayerInvalid();
	}

	@When("^Beginning the game$")
	public void beginning_the_game() throws Throwable {
		// does nothing
	}

	@When("^The player tries to build the wonder (\\d+) times$")
	public void the_player_tries_to_build_the_wonder_times(int numLevels) throws Throwable {
		this.wonderSteps.index = numLevels - 1;
		this.priority = numLevels;

		for (int i = 0; i < numLevels; i++) {
			try {
				tryToBuild();
			} catch (CannotBuildWonderException e) {
				this.wonderSteps.wonderException = e;
				this.wonderSteps.exceptionBehavior = this.wonderSteps.NO_WONDERS;
			}
		}
	}

	@Then("^The player begins with the appropriate resource$")
	public void the_player_begins_with_the_appropriate_resource() throws Throwable {
		EntityEffect effect = this.player.getWonder().getResource();
		assertTrue(this.player.storageContainsEffect(effect));
	}

	@Then("^The player does not begin with any other resources$")
	public void the_player_does_not_begin_with_any_other_resources() throws Throwable {
		assertTrue(this.player.getAllEffects().size() == 1);
	}

	@Then("^The player receives the effect of that level$")
	public void the_player_receives_the_effect_of_that_level() throws Throwable {
		HashSet<Level> expectedLevels = this.wonderSteps.expectedLevels;
		HashSet<Effect> actualEffects = this.player.getAllEffects();

		assertTrue(expectedEqualsActual(expectedLevels, actualEffects));
	}

	@Then("^The player does not receive the effect of that level$")
	public void the_player_does_not_receive_the_effect_of_that_level() throws Throwable {
		HashSet<Level> expectedLevels = this.wonderSteps.expectedLevels;
		HashSet<Effect> actualEffects = this.player.getAllEffects();

		assertFalse(expectedEqualsActual(expectedLevels, actualEffects));
	}

	private boolean expectedEqualsActual(HashSet<Level> expectedLevels, HashSet<Effect> actualEffects) {
		for (Level expected : expectedLevels) {
			if (actualContainsExpectedEffect(expected.getEffects(), actualEffects)) {
				continue;
			} else {
				return false;
			}
		}
		return true;
	}

	private boolean actualContainsExpectedEffect(HashMap<Frequency, HashSet<Effect>> expectedMap,
			HashSet<Effect> actualEffects) {
		for (Frequency frequency : expectedMap.keySet()) {
			boolean contains = getActualContainsEffect(expectedMap, actualEffects, frequency);
			if (contains) {
				continue;
			}
			return false;
		}
		return true;
	}

	private boolean getActualContainsEffect(HashMap<Frequency, HashSet<Effect>> expectedMap,
			HashSet<Effect> actualEffects, Frequency frequency) {
		for (Effect expected : expectedMap.get(frequency)) {
			if (contains(expected, actualEffects)) {
				continue;
			}
			return false;
		}
		return true;
	}

	public boolean contains(Effect expected, HashSet<Effect> actualLevels) {
		for (Effect actual : actualLevels) {
			if (equalEffects(expected, actual)){
				return true;
			}
			continue;
		}
		return false;
	}

	public boolean equalEffects(Effect expected, Effect actual) {
		if (expected.getEffectType().equals(actual.getEffectType())) {
			switch (actual.getEffectType()) {
			case ABILITY:
				return ((AbilityEffect) expected).equals(((AbilityEffect) actual));
			case VALUE:
				return ((ValueEffect) expected).equals(((ValueEffect) actual));
			case ENTITY:
				return ((EntityEffect) expected).equals(((EntityEffect) actual));
			default:
				throw new IllegalArgumentException("Invalid Effect Type");
			}
		}
		return false;
	}

	private void tryToBuild() {
		PlayerTurnHandler handler = new PlayerTurnHandler();
		try {
			HashMap<Enum, Integer> costs = this.player.getWonder().getLevelFactory().getNextLevel(priority).getCosts();
			System.out.println("Costs: " + costs);
			handler.validateLevelCosts(this.player, costs);
			this.player.buildNextLevel();
		} catch (InsufficientFundsException e) {
			this.wonderSteps.fundsException = e;
			this.wonderSteps.exceptionBehavior = this.wonderSteps.NO_FUNDS;
		}
	}

	private void giveResourcesToPlayerInvalid() {
		Card[] cards = getInvalidCards();
		addAllToStoragePile(cards);
	}

	private Card[] getInvalidCards() {
		int[] amountsColossus = { 1, 1, 1, 1 };
		return getCards(amountsColossus, Resource.LUMBER, Resource.CLAY, Resource.ORE, Resource.STONE);
	}

	private void giveResourcesToPlayer() {
		Card[] cards = getValidAmountCards();
		addAllToStoragePile(cards);
	}

	private Card[] getValidAmountCards() {
		switch (this.player.getWonder().getType()) {
		case COLOSSUS:
			int[] amountsColossus = { 3, 4, 3, 2 };
			return getCards(amountsColossus, Resource.STONE, Resource.ORE, Resource.CLAY, Resource.LUMBER);
		case LIGHTHOUSE:
			int[] amountsLighthouse = { 3, 2, 2, 2, 2 };
			return getCards(amountsLighthouse, Resource.STONE, Resource.ORE, Resource.CLAY, Resource.LUMBER,
					Resource.GLASS);
		case TEMPLE:
			int[] amountsTemple = { 2, 2, 2, 1, 1 };
			return getCards(amountsTemple, Resource.STONE, Resource.LUMBER, Resource.PRESS, Resource.GLASS,
					Resource.LOOM);
		case GARDENS:
			int[] amountsGardens = { 4, 3, 1, 1, 1 };
			return getCards(amountsGardens, Resource.CLAY, Resource.LUMBER, Resource.PRESS, Resource.GLASS,
					Resource.LOOM);
		case STATUE:
			int[] amountsStatue = { 2, 2, 2, 1 };
			return getCards(amountsStatue, Resource.STONE, Resource.LUMBER, Resource.ORE, Resource.LOOM);
		case MAUSOLEUM:
			int[] amountsMausoleum = { 3, 3, 2, 1, 1 };
			return getCards(amountsMausoleum, Resource.ORE, Resource.CLAY, Resource.LOOM, Resource.GLASS,
					Resource.PRESS);
		case PYRAMIDS:
			int[] amountsPyramids = { 4, 3, 3, 1 };
			return getCards(amountsPyramids, Resource.STONE, Resource.CLAY, Resource.LUMBER, Resource.PRESS);
		default:
			throw new IllegalArgumentException("Invalid wonder type");
		}
	}

	public Card[] getCards(int[] amounts, Resource... resources) {
		int numCards = resources.length;
		Card[] cards = new Card[numCards];
		for (int i = 0; i < numCards; i++) {
			switch (resources[i]) {
			case LUMBER:
				cards[i] = this.buildLumberCard(amounts[i]);
				break;
			case ORE:
				cards[i] = this.buildOreCard(amounts[i]);
				break;
			case CLAY:
				cards[i] = this.buildClayCard(amounts[i]);
				break;
			case STONE:
				cards[i] = this.buildStoneCard(amounts[i]);
				break;
			case GLASS:
				cards[i] = this.buildGlassCard(amounts[i]);
				break;
			case PRESS:
				cards[i] = this.buildPressCard(amounts[i]);
				break;
			case LOOM:
				cards[i] = this.buildLoomCard(amounts[i]);
				break;
			default:
				throw new IllegalArgumentException();
			}
		}
		return cards;
	}

	private void addAllToStoragePile(Card... cards) {
		for (int i = 0; i < cards.length; i++) {
			Card toAdd = cards[i];
			this.player.addCardToStoragePile(toAdd);
		}
	}

	private Card buildLumberCard(int num) {
		Cost cost = new Cost(CostType.NONE, 0);
		HashMap<Enum, Integer> entitiesAndAmounts = new HashMap<Enum, Integer>();
		entitiesAndAmounts.put(RawResource.LUMBER, num);
		Effect effect = new EntityEffect(EntityType.RESOURCE, entitiesAndAmounts);
		Card card = new Card("Lumber Yard", CardType.RAWMATERIAL, cost, effect);
		return card;
	}

	private Card buildStoneCard(int num) {
		Cost cost = new Cost(CostType.NONE, 0);
		HashMap<Enum, Integer> entitiesAndAmounts = new HashMap<Enum, Integer>();
		entitiesAndAmounts.put(RawResource.STONE, num);
		Effect effect = new EntityEffect(EntityType.RESOURCE, entitiesAndAmounts);
		Card card = new Card("Lumber Yard", CardType.RAWMATERIAL, cost, effect);
		return card;
	}

	private Card buildOreCard(int num) {
		Cost cost = new Cost(CostType.NONE, 0);
		HashMap<Enum, Integer> entitiesAndAmounts = new HashMap<Enum, Integer>();
		entitiesAndAmounts.put(RawResource.ORE, num);
		Effect effect = new EntityEffect(EntityType.RESOURCE, entitiesAndAmounts);
		Card card = new Card("Lumber Yard", CardType.RAWMATERIAL, cost, effect);
		return card;
	}

	private Card buildClayCard(int num) {
		Cost cost = new Cost(CostType.NONE, 0);
		HashMap<Enum, Integer> entitiesAndAmounts = new HashMap<Enum, Integer>();
		entitiesAndAmounts.put(RawResource.CLAY, num);
		Effect effect = new EntityEffect(EntityType.RESOURCE, entitiesAndAmounts);
		Card card = new Card("Lumber Yard", CardType.RAWMATERIAL, cost, effect);
		return card;
	}

	private Card buildGlassCard(int num) {
		Cost cost = new Cost(CostType.NONE, 0);
		HashMap<Enum, Integer> entitiesAndAmounts = new HashMap<Enum, Integer>();
		entitiesAndAmounts.put(Good.GLASS, num);
		Effect effect = new EntityEffect(EntityType.MANUFACTUREDGOOD, entitiesAndAmounts);
		Card card = new Card("Lumber Yard", CardType.MANUFACTUREDGOOD, cost, effect);
		return card;
	}

	private Card buildLoomCard(int num) {
		Cost cost = new Cost(CostType.NONE, 0);
		HashMap<Enum, Integer> entitiesAndAmounts = new HashMap<Enum, Integer>();
		entitiesAndAmounts.put(Good.LOOM, num);
		Effect effect = new EntityEffect(EntityType.MANUFACTUREDGOOD, entitiesAndAmounts);
		Card card = new Card("Lumber Yard", CardType.MANUFACTUREDGOOD, cost, effect);
		return card;
	}

	private Card buildPressCard(int num) {
		Cost cost = new Cost(CostType.NONE, 0);
		HashMap<Enum, Integer> entitiesAndAmounts = new HashMap<Enum, Integer>();
		entitiesAndAmounts.put(Good.PRESS, num);
		Effect effect = new EntityEffect(EntityType.MANUFACTUREDGOOD, entitiesAndAmounts);
		Card card = new Card("Lumber Yard", CardType.MANUFACTUREDGOOD, cost, effect);
		return card;
	}
}
