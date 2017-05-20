import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ResourceBundle;

import javax.swing.text.html.parser.Entity;

import org.easymock.EasyMock;

import backend.handlers.PlayerTurnHandler;
import constants.GeneralEnums.CostType;
import constants.GeneralEnums.Good;
import constants.GeneralEnums.RawResource;
import constants.GeneralEnums.Resource;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dataStructures.GameBoard;
import dataStructures.gameMaterials.Card;
import dataStructures.gameMaterials.Card.CardType;
import dataStructures.gameMaterials.Cost;
import dataStructures.gameMaterials.Deck;
import dataStructures.gameMaterials.Effect;
import dataStructures.gameMaterials.EntityEffect;
import dataStructures.gameMaterials.EntityEffect.EntityType;
import dataStructures.gameMaterials.Level;
import dataStructures.gameMaterials.Level.Frequency;
import dataStructures.playerData.Player;
import exceptions.CannotBuildWonderException;
import exceptions.InsufficientFundsException;

public class PlayerBuildWonderSteps {
	private BuildWonderSteps wonderSteps;
	private Player player;

	public PlayerBuildWonderSteps(BuildWonderSteps wonderSteps) {
		this.wonderSteps = wonderSteps;
	}

	@Given("^The player does not have the resources for that Wonder$")
	public void the_player_does_not_have_the_resources_for_that_Wonder() throws Throwable {
		giveResourcesToPlayerInvalid();
	}

	@Given("^A player with that Wonder$")
	public void a_player_with_that_Wonder() throws Throwable {
		this.player = new Player("Jane Doe", this.wonderSteps.wonder);
	}

	@Given("^The player has the resources for that Wonder$")
	public void the_player_has_the_resources_for_that_Wonder() throws Throwable {
		giveResourcesToPlayer();
	}

	@When("^Beginning the game$")
	public void beginning_the_game() throws Throwable {
		// does nothing
	}

	@When("^The player tries to build the wonder (\\d+) times$")
	public void the_player_tries_to_build_the_wonder_times(int numLevels) throws Throwable {
		for (int i = 0; i < numLevels; i++) {
			try {
				tryToBuild();
			} catch (CannotBuildWonderException e) {
				this.wonderSteps.wonderException = e;
				this.wonderSteps.exceptionBehavior = BuildWonderSteps.ExceptionBehavior.NOWONDERS;
			}
		}
	}

	@Then("^The player begins with the appropriate resource$")
	public void the_player_begins_with_the_appropriate_resource() throws Throwable {
		EntityEffect effect = this.wonderSteps.wonder.getResource();
		assertTrue(this.player.storageContainsEffect(effect));
	}

	@Then("^The player does not begin with any other resources$")
	public void the_player_does_not_begin_with_any_other_resources() throws Throwable {
		assertTrue(this.player.getAllEffects().size() == 1);
	}

	@Then("^The player receives the effect of that level$")
	public void the_player_receives_the_effect_of_that_level() throws Throwable {
		HashMap<Frequency, HashSet<Effect>> levelMap = getLevelEffects();
		HashSet<Effect> playerEffects = this.player.getAllEffects();

		for (Frequency frequency : levelMap.keySet()) {
			playerDoesHaveEffects(levelMap, playerEffects, frequency);
		}
	}

	@Then("^The player does not receive the effect of that level$")
	public void the_player_does_not_receive_the_effect_of_that_level() throws Throwable {
		HashMap<Frequency, HashSet<Effect>> levelMap = getLevelEffects();
		HashSet<Effect> playerEffects = this.player.getAllEffects();

		for (Frequency frequency : levelMap.keySet()) {
			playerDoesNotHaveEffects(levelMap, playerEffects, frequency);
		}
	}

	public HashMap<Frequency, HashSet<Effect>> getLevelEffects() {
		HashMap<Frequency, HashSet<Effect>> result = new HashMap<Frequency, HashSet<Effect>>();
		for (Level level : this.wonderSteps.expectedLevels) {
			result.putAll(level.getEffects());
		}
		return result;
	}

	private void playerDoesHaveEffects(HashMap<Frequency, HashSet<Effect>> levelMap, HashSet<Effect> playerEffects,
			Frequency frequency) {
		HashSet<Effect> levelEffects = levelMap.get(frequency);
		for (Effect effect : levelEffects) {
			assertTrue(playerEffects.contains(effect));
		}
	}

	private void playerDoesNotHaveEffects(HashMap<Frequency, HashSet<Effect>> levelMap, HashSet<Effect> playerEffects,
			Frequency frequency) {
		HashSet<Effect> levelEffects = levelMap.get(frequency);
		for (Effect effect : levelEffects) {
			assertFalse(playerEffects.contains(effect));
		}
	}

	private void tryToBuild() {
		ArrayList<Player> players = new ArrayList<Player>(Arrays.asList(this.player));
		Deck deck = EasyMock.mock(Deck.class);
		GameBoard board = new GameBoard(players, deck);
		EasyMock.replay(deck);

		PlayerTurnHandler handler = new PlayerTurnHandler();
		handler.setGameBoard(board);
		tryBuildThroughHandler(handler);

		EasyMock.verify(deck);
	}

	private void tryBuildThroughHandler(PlayerTurnHandler handler) {
		try {
			handler.buildWonderLevel(this.player);
		} catch (InsufficientFundsException e) {
			this.wonderSteps.fundsException = e;
			this.wonderSteps.exceptionBehavior = BuildWonderSteps.ExceptionBehavior.NOFUNDS;
		}
	}

	private void giveResourcesToPlayerInvalid() {
		Card[] cards = getCards();
		addAllToStoragePile(cards);
	}

	private Card[] getCards() {
		switch (this.wonderSteps.wonder.getType()) {
		case COLOSSUS:
			int[] amountsColossus = { 1, 1, 1 };
			return getCards(amountsColossus, Resource.LUMBER, Resource.CLAY, Resource.ORE);
		case LIGHTHOUSE:
			int[] amountsLighthouse = { 1, 1, 1 };
			return getCards(amountsLighthouse, Resource.STONE, Resource.CLAY, Resource.GLASS);
		case TEMPLE:
			int[] amountsTemple = { 5 };
			return getCards(amountsTemple, Resource.ORE);
		case GARDENS:
			int[] amountsGardens = { 1, 1 };
			return getCards(amountsGardens, Resource.CLAY, Resource.GLASS);
		case STATUE:
			int[] amountsStatue = { 1, 5 };
			return getCards(amountsStatue, Resource.LUMBER, Resource.GLASS);
		case MAUSOLEUM:
			int[] amountsMausoleum = { 1 };
			return getCards(amountsMausoleum, Resource.CLAY);
		case PYRAMIDS:
			int[] amountsPyramids = { 1, 1, 5 };
			return getCards(amountsPyramids, Resource.STONE, Resource.LUMBER, Resource.GLASS);
		default:
			throw new IllegalArgumentException("Invalid wonder type");
		}
	}

	private void giveResourcesToPlayer() {
		Card[] cards = getSameAmountCards();
		addAllToStoragePile(cards);
	}

	private Card[] getSameAmountCards() {
		switch (this.wonderSteps.wonder.getType()) {
		case COLOSSUS:
			return getCards(3, Resource.LUMBER, Resource.CLAY, Resource.ORE);
		case LIGHTHOUSE:
			return getCards(5, Resource.STONE, Resource.ORE, Resource.LUMBER, Resource.CLAY, Resource.GLASS);
		case TEMPLE:
			return getCards(4, Resource.LUMBER, Resource.STONE, Resource.PRESS, Resource.GLASS);
		case GARDENS:
			return getCards(5, Resource.LUMBER, Resource.CLAY, Resource.PRESS, Resource.GLASS, Resource.LOOM);
		case STATUE:
			return getCards(4, Resource.LUMBER, Resource.STONE, Resource.ORE, Resource.LOOM);
		case MAUSOLEUM:
			return getCards(5, Resource.CLAY, Resource.ORE, Resource.LOOM, Resource.GLASS, Resource.PRESS);
		case PYRAMIDS:
			return getCards(4, Resource.LUMBER, Resource.CLAY, Resource.PRESS, Resource.STONE);
		default:
			throw new IllegalArgumentException("Invalid wonder type");
		}
	}

	public Card[] getCards(int numCards, Resource... resources) {
		Card[] cards = new Card[numCards];
		for (int i = 0; i < numCards; i++) {
			switch (resources[i]) {
			case LUMBER:
				cards[i] = this.buildLumberCard(5);
				break;
			case ORE:
				cards[i] = this.buildOreCard(5);
				break;
			case CLAY:
				cards[i] = this.buildClayCard(5);
				break;
			case STONE:
				cards[i] = this.buildStoneCard(5);
				break;
			case GLASS:
				cards[i] = this.buildGlassCard(5);
				break;
			case PRESS:
				cards[i] = this.buildPressCard(5);
				break;
			case LOOM:
				cards[i] = this.buildLoomCard(5);
				break;
			default:
				throw new IllegalArgumentException();
			}
		}
		return cards;
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
