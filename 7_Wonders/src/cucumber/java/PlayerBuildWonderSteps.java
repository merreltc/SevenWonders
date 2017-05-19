import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import org.easymock.EasyMock;

import backend.handlers.PlayerTurnHandler;
import constants.GeneralEnums.CostType;
import constants.GeneralEnums.Good;
import constants.GeneralEnums.RawResource;
import cucumber.api.PendingException;
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
	private Level level;

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
				ArrayList<Player> players = new ArrayList<Player>(Arrays.asList(this.player));
				Deck deck = EasyMock.mock(Deck.class);
				GameBoard board = new GameBoard(players, deck);
				EasyMock.replay(deck);

				PlayerTurnHandler handler = new PlayerTurnHandler();
				handler.setGameBoard(board);
				tryBuildThroughHandler(handler);

				EasyMock.verify(deck);
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
		HashMap<Frequency, HashSet<Effect>> levelMap = this.level.getEffects();
		HashSet<Effect> playerEffects = this.player.getAllEffects();

		for (Frequency frequency : levelMap.keySet()) {
			playerDoesHaveEffects(levelMap, playerEffects, frequency);
		}
	}

	@Then("^The player does not receive the effect of that level$")
	public void the_player_does_not_receive_the_effect_of_that_level() throws Throwable {
		HashMap<Frequency, HashSet<Effect>> levelMap = this.level.getEffects();
		HashSet<Effect> playerEffects = this.player.getAllEffects();

		for (Frequency frequency : levelMap.keySet()) {
			playerDoesNotHaveEffects(levelMap, playerEffects, frequency);
		}
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

	private void tryBuildThroughHandler(PlayerTurnHandler handler) {
		try {
			this.level = handler.buildWonderLevel(this.player);
			System.err.println(this.level);
		} catch (InsufficientFundsException e) {
			this.wonderSteps.fundsException = e;
			this.wonderSteps.exceptionBehavior = BuildWonderSteps.ExceptionBehavior.NOFUNDS;
		}
	}

	private void giveResourcesToPlayerInvalid() {
		switch (this.wonderSteps.wonder.getType()) {
		case COLOSSUS:
			this.player.addCardToStoragePile(this.buildLumberCard(1));
			this.player.addCardToStoragePile(this.buildClayCard(1));
			this.player.addCardToStoragePile(this.buildOreCard(1));
			break;
		case LIGHTHOUSE:
			this.player.addCardToStoragePile(this.buildStoneCard(1));
			this.player.addCardToStoragePile(this.buildGlassCard(1));
			this.player.addCardToStoragePile(this.buildClayCard(1));
			break;
		case TEMPLE:
			this.player.addCardToStoragePile(this.buildOreCard(5));
			break;
		case GARDENS:
			this.player.addCardToStoragePile(this.buildClayCard(1));
			this.player.addCardToStoragePile(this.buildGlassCard(1));
			break;
		case STATUE:
			this.player.addCardToStoragePile(this.buildLumberCard(1));
			this.player.addCardToStoragePile(this.buildGlassCard(5));
			break;
		case MAUSOLEUM:
			this.player.addCardToStoragePile(this.buildClayCard(1));
			break;
		case PYRAMIDS:
			this.player.addCardToStoragePile(this.buildStoneCard(1));
			this.player.addCardToStoragePile(this.buildLumberCard(1));
			this.player.addCardToStoragePile(this.buildGlassCard(5));
			break;
		default:
			throw new IllegalArgumentException("Invalid wonder type");
		}
	}

	private void giveResourcesToPlayer() {
		switch (this.wonderSteps.wonder.getType()) {
		case COLOSSUS:
			this.player.addCardToStoragePile(this.buildLumberCard(5));
			this.player.addCardToStoragePile(this.buildClayCard(5));
			this.player.addCardToStoragePile(this.buildOreCard(5));
			break;
		case LIGHTHOUSE:
			this.player.addCardToStoragePile(this.buildStoneCard(5));
			this.player.addCardToStoragePile(this.buildOreCard(5));
			this.player.addCardToStoragePile(this.buildGlassCard(5));
			this.player.addCardToStoragePile(this.buildLumberCard(5));
			this.player.addCardToStoragePile(this.buildClayCard(5));
			break;
		case TEMPLE:
			this.player.addCardToStoragePile(this.buildStoneCard(5));
			this.player.addCardToStoragePile(this.buildLumberCard(5));
			this.player.addCardToStoragePile(this.buildPressCard(5));
			this.player.addCardToStoragePile(this.buildGlassCard(5));
			break;
		case GARDENS:
			this.player.addCardToStoragePile(this.buildClayCard(5));
			this.player.addCardToStoragePile(this.buildLumberCard(5));
			this.player.addCardToStoragePile(this.buildClayCard(5));
			this.player.addCardToStoragePile(this.buildPressCard(5));
			this.player.addCardToStoragePile(this.buildGlassCard(5));
			this.player.addCardToStoragePile(this.buildLoomCard(5));
			break;
		case STATUE:
			this.player.addCardToStoragePile(this.buildLumberCard(5));
			this.player.addCardToStoragePile(this.buildStoneCard(5));
			this.player.addCardToStoragePile(this.buildOreCard(5));
			this.player.addCardToStoragePile(this.buildLoomCard(5));
			break;
		case MAUSOLEUM:
			this.player.addCardToStoragePile(this.buildClayCard(5));
			this.player.addCardToStoragePile(this.buildOreCard(5));
			this.player.addCardToStoragePile(this.buildLoomCard(5));
			this.player.addCardToStoragePile(this.buildGlassCard(5));
			this.player.addCardToStoragePile(this.buildPressCard(5));
			break;
		case PYRAMIDS:
			this.player.addCardToStoragePile(this.buildStoneCard(5));
			this.player.addCardToStoragePile(this.buildLumberCard(5));
			this.player.addCardToStoragePile(this.buildClayCard(5));
			this.player.addCardToStoragePile(this.buildPressCard(5));
			break;
		default:
			throw new IllegalArgumentException("Invalid wonder type");
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
