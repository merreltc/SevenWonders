package dataStructureTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import backend.factories.LevelFactory;
import backend.handlers.PlayerChipHandler;
import backend.handlers.SetUpDeckHandler;
import constants.GeneralEnums.CostType;
import constants.GeneralEnums.Good;
import constants.GeneralEnums.RawResource;
import constants.GeneralEnums.Science;
import constants.GeneralEnums.Side;
import dataStructures.gameMaterials.AbilityEffect;
import dataStructures.gameMaterials.Card;
import dataStructures.gameMaterials.Card.CardType;
import dataStructures.gameMaterials.Cost;
import dataStructures.gameMaterials.Deck.Age;
import dataStructures.gameMaterials.Effect;
import dataStructures.gameMaterials.Effect.Direction;
import dataStructures.gameMaterials.Effect.EffectType;
import dataStructures.gameMaterials.EntityEffect;
import dataStructures.gameMaterials.EntityEffect.EntityType;
import dataStructures.gameMaterials.Level.Frequency;
import dataStructures.gameMaterials.ValueEffect;
import dataStructures.gameMaterials.ValueEffect.AffectingEntity;
import dataStructures.gameMaterials.ValueEffect.Value;
import dataStructures.gameMaterials.Wonder;
import dataStructures.gameMaterials.Wonder.WonderType;
import dataStructures.playerData.Chip;
import dataStructures.playerData.Chip.ChipValue;
import exceptions.NoSuchLevelException;
import dataStructures.playerData.Player;
import testHelpers.LevelBuilderTestHelper;

public class PlayerTest {
	// BEGIN GENERATED CODE
	Wonder wonder;
	LevelBuilderTestHelper helper;

	@Before
	public void setUp() {
		this.helper = new LevelBuilderTestHelper();
	}

	@Test
	public void testDefaultPlayer() {
		Player player = createMockedPlayer();

		assertEquals(3, player.getCoinTotal());
		assertEquals(0, player.getConflictTotal());
		assertEquals("Jane Doe", player.getName());
	}

	@Test
	public void testDefaultPlayerCoins() {
		Player player = createMockedPlayer();

		assertEquals(3, (int) player.getCoins().get(ChipValue.ONE));
		assertEquals(0, (int) player.getCoins().get(ChipValue.THREE));
	}

	@Test
	public void testNamedPlayer() {

		Player player = createMockedPlayer();

		assertEquals("Jane Doe", player.getName());
	}

	@Test
	public void testToStringDefaultPlayer() {
		Player player = createMockedPlayer();

		assertEquals("Name: Jane Doe\nCoin Total: 3", player.toString());
	}

	@Test
	public void testToStringNamedPlayer() {

		Player player = createMockedPlayer();

		assertEquals("Name: Jane Doe\nCoin Total: 3", player.toString());
	}

	@Test
	public void testGetWonderSide() {
		Wonder wonder = EasyMock.partialMockBuilder(Wonder.class).withConstructor(Side.A, WonderType.COLOSSUS)
				.createMock();
		Player player = EasyMock.partialMockBuilder(Player.class).withConstructor("Jane Doe", wonder).createMock();
		assertEquals(Side.A, player.getSide());

		wonder = EasyMock.partialMockBuilder(Wonder.class).withConstructor(Side.B, WonderType.COLOSSUS).createMock();
		player = EasyMock.partialMockBuilder(Player.class).withConstructor("Jane Doe", wonder).createMock();
		assertEquals(Side.B, player.getSide());
	}

	@Test
	public void testToStringPlayerAfterAddCoins() {
		Player player = createMockedPlayer();
		PlayerChipHandler.addValue1(player, 2, Chip.ChipType.COIN);
		PlayerChipHandler.addValue3(player, 1, Chip.ChipType.COIN);

		assertEquals("Name: Jane Doe\nCoin Total: 8", player.toString());
	}

	@Test
	public void testGetDefaultCurrentHand() {
		Player player = createMockedPlayer();

		assertTrue(player.getCurrentHand().isEmpty());
	}

	@Test
	public void testSetCurrectHand() {
		Player player = createMockedPlayer();
		ArrayList<Card> deckCards = new SetUpDeckHandler().createCards(Age.AGE1, 3);

		ArrayList<Card> playerCards = new ArrayList<Card>();
		playerCards.add(deckCards.get(0));
		playerCards.add(deckCards.get(1));
		playerCards.add(deckCards.get(2));

		player.setCurrentHand(playerCards);

		assertEquals(playerCards, player.getCurrentHand());
		assertEquals(playerCards.get(0), player.getCurrentHand().get(0));
		assertEquals(playerCards.get(1), player.getCurrentHand().get(1));
		assertEquals(playerCards.get(2), player.getCurrentHand().get(2));
	}

	@Test
	public void testGetDefaultCardStoragePileHand() {
		Player player = createMockedPlayer();

		assertTrue(player.getAllCards().isEmpty());
		assertEquals(ArrayList.class, player.getAllCards().getClass());
	}

	@Test
	public void testGetDefaultEffectStoragePileHand() {
		Player player = createMockedPlayer();

		assertTrue(player.getAllEffects().isEmpty());
		assertEquals(HashSet.class, player.getAllEffects().getClass());
	}

	@Test
	public void testSetCardStoragePileHand() {
		Player player = createMockedPlayer();
		ArrayList<Card> deckCards = new SetUpDeckHandler().createCards(Age.AGE1, 3);

		ArrayList<Card> playerCards = new ArrayList<Card>();
		playerCards.add(deckCards.get(0));
		playerCards.add(deckCards.get(1));
		playerCards.add(deckCards.get(2));

		player.setStoragePile(playerCards);

		assertEquals(playerCards, player.getAllCards());
		assertEquals(playerCards.get(0), player.getAllCards().get(0));
		assertEquals(playerCards.get(1), player.getAllCards().get(1));
		assertEquals(playerCards.get(2), player.getAllCards().get(2));
	}

	@Test
	public void testAddWonderEffect() {
		Player player = createMockedPlayer();
		HashSet<Effect> effects = new HashSet<Effect>();
		HashMap<Frequency, HashSet<Effect>> effectsMap = new HashMap<Frequency, HashSet<Effect>>();

		Effect effect1 = EasyMock.createStrictMock(Effect.class);
		effects.add(effect1);
		effectsMap.put(Frequency.DEFAULT, effects);

		Effect effect2 = EasyMock.createStrictMock(Effect.class);
		effects.add(effect2);
		effectsMap.put(Frequency.DEFAULT, effects);

		player.addWonderEffectToStoragePile(effectsMap);

		assertEquals(effects, player.getAllEffects());
		assertTrue(player.getAllEffects().contains(effect1));
		assertTrue(player.getAllEffects().contains(effect2));
	}

	@Test
	public void testStoragePileContainsResource() {
		Player player = createMockedPlayer();
		ArrayList<Card> deckCards = new SetUpDeckHandler().createCards(Age.AGE1, 3);

		ArrayList<Card> playerCards = new ArrayList<Card>();
		playerCards.add(deckCards.get(0));
		playerCards.add(deckCards.get(1));
		playerCards.add(deckCards.get(2));

		player.setStoragePile(playerCards);

		assertTrue(player.storagePileContainsEntity(RawResource.LUMBER));
	}

	@Test
	public void testStoragePileContainsCardByName() {
		Player player = createMockedPlayer();
		Card card = EasyMock.mock(Card.class);
		EntityEffect effect = EasyMock.mock(EntityEffect.class);
		EasyMock.expect(card.getName()).andReturn("Loom");
		EasyMock.expect(card.getEffectType()).andReturn(EffectType.ENTITY);
		EasyMock.expect(card.getEffect()).andReturn(effect);
		EasyMock.expect(effect.getEntityType()).andReturn(EntityType.RESOURCE);
		EasyMock.expect(card.getEffect()).andReturn(effect);

		EasyMock.replay(card, effect);

		ArrayList<Card> storage = new ArrayList<Card>();
		storage.add(card);
		player.setStoragePile(storage);
		Assert.assertTrue(player.storagePileContainsCardByName("Loom"));

		EasyMock.verify(card, effect);
	}

	@Test
	public void testStoragePileContainsCardByNameFails() {
		Player player = createMockedPlayer();
		Card card = EasyMock.mock(Card.class);
		EntityEffect effect = EasyMock.mock(EntityEffect.class);
		EasyMock.expect(card.getName()).andReturn("Loom");
		EasyMock.expect(card.getEffectType()).andReturn(EffectType.ENTITY);
		EasyMock.expect(card.getEffect()).andReturn(effect);
		EasyMock.expect(effect.getEntityType()).andReturn(EntityType.RESOURCE);
		EasyMock.expect(card.getEffect()).andReturn(effect);

		EasyMock.replay(card, effect);

		ArrayList<Card> storage = new ArrayList<Card>();
		storage.add(card);
		player.setStoragePile(storage);
		Assert.assertFalse(player.storagePileContainsCardByName("Press"));

		EasyMock.verify(card, effect);
	}

	@Test
	public void testDefaultCurrentTrades() {
		Player player = createMockedPlayer();

		assertTrue(player.getCurrentTrades().isEmpty());
		assertEquals(HashMap.class, player.getCurrentTrades().getClass());
	}

	@Test
	public void testAddTraded() {
		Player player = createMockedPlayer();

		player.addTradedValue(RawResource.LUMBER);
		assertFalse(player.getCurrentTrades().isEmpty());
		assertTrue(player.getCurrentTrades().containsKey(RawResource.LUMBER));
		assertEquals(1, (int) player.getCurrentTrades().get(RawResource.LUMBER));
	}

	@Test
	public void testAddMultipleSameTraded() {
		Player player = createMockedPlayer();

		player.addTradedValue(RawResource.LUMBER);
		player.addTradedValue(RawResource.LUMBER);
		assertFalse(player.getCurrentTrades().isEmpty());
		assertTrue(player.getCurrentTrades().containsKey(RawResource.LUMBER));
		assertEquals(2, (int) player.getCurrentTrades().get(RawResource.LUMBER));
	}

	@Test
	public void testAddMultipleDifferentTraded() {
		Player player = createMockedPlayer();

		player.addTradedValue(RawResource.LUMBER);
		player.addTradedValue(Good.LOOM);
		assertFalse(player.getCurrentTrades().isEmpty());
		assertTrue(player.getCurrentTrades().containsKey(RawResource.LUMBER));
		assertEquals(1, (int) player.getCurrentTrades().get(RawResource.LUMBER));
		assertEquals(1, (int) player.getCurrentTrades().get(Good.LOOM));
	}

	@Test
	public void testRemoveCurrentTrades() {
		Player player = createMockedPlayer();

		player.addTradedValue(RawResource.LUMBER);
		player.addTradedValue(Good.LOOM);
		player.removeCurrentTrades();
		assertTrue(player.getCurrentTrades().isEmpty());
		assertEquals(HashMap.class, player.getCurrentTrades().getClass());
	}

	@Test
	public void testStoragePileContainsGood() {
		Player player = createMockedPlayer();
		ArrayList<Card> deckCards = new SetUpDeckHandler().createCards(Age.AGE1, 3);

		ArrayList<Card> playerCards = new ArrayList<Card>();
		playerCards.add(deckCards.get(6));
		playerCards.add(deckCards.get(7));
		playerCards.add(deckCards.get(8));

		player.setStoragePile(playerCards);

		assertTrue(player.storagePileContainsEntity(Good.GLASS));
	}

	@Test
	public void testAddToStoragePile() {
		Player player = createMockedPlayer();
		ArrayList<Card> deckCards = new SetUpDeckHandler().createCards(Age.AGE1, 3);

		ArrayList<Card> playerCards = new ArrayList<Card>();
		playerCards.add(deckCards.get(0));
		playerCards.add(deckCards.get(1));
		playerCards.add(deckCards.get(2));

		player.setStoragePile(playerCards);

		player.addCardToStoragePile(deckCards.get(3));
		assertEquals(4, player.getAllCards().size());
		assertEquals(deckCards.get(3), player.getAllCards().get(3));
	}

	@Test
	public void testRemoveFromCurrentHand() {
		Player player = createMockedPlayer();
		ArrayList<Card> deckCards = new SetUpDeckHandler().createCards(Age.AGE1, 3);

		ArrayList<Card> playerCards = new ArrayList<Card>();
		playerCards.add(deckCards.get(0));
		playerCards.add(deckCards.get(1));
		playerCards.add(deckCards.get(2));

		player.setCurrentHand(playerCards);

		player.removeFromCurrentHand(deckCards.get(1));
		assertEquals(2, player.getCurrentHand().size());
		assertFalse(player.getCurrentHand().contains(deckCards.get(1)));
	}

	@Test
	public void testCreatePlayerWithWonder() {
		Wonder wonder = EasyMock.mock(Wonder.class);
		EasyMock.expect(wonder.getType()).andReturn(WonderType.STATUE);
		EasyMock.expect(wonder.getName()).andReturn("The Statue of Zeus in Olympia");
		EasyMock.replay(wonder);

		Player player = EasyMock.partialMockBuilder(Player.class).addMockedMethod("addWonderResourceToPile")
				.withConstructor("Jane Doe", wonder).createMock();
		assertEquals("The Statue of Zeus in Olympia", player.getWonder().getName());
	}

	@Test
	public void testCreatePlayerWithWonder2() {
		Wonder wonder = EasyMock.mock(Wonder.class);
		EasyMock.expect(wonder.getType()).andReturn(WonderType.LIGHTHOUSE);
		EasyMock.expect(wonder.getName()).andReturn("The Lighthouse of Alexandria");
		EasyMock.replay(wonder);
		Player player = EasyMock.partialMockBuilder(Player.class).addMockedMethod("addWonderResourceToPile")
				.withConstructor("Jane Doe", wonder).createMock();
		assertEquals("The Lighthouse of Alexandria", player.getWonder().getName());
	}

	@Test
	public void testGetDefaultNumShields() {
		Player player = createMockedPlayer();
		assertEquals(0, player.getNumShields());
	}

	@Test
	public void testAdd2Shields() {
		Player player = createMockedPlayer();
		player.addNumShields(2);
		assertEquals(2, player.getNumShields());
	}

	@Test
	public void testGetDefaultNumVictoryPoints() {
		Player player = createMockedPlayer();
		assertEquals(0, player.getNumVictoryPoints());
	}

	@Test
	public void testAdd2VictoryPoints() {
		Player player = createMockedPlayer();
		player.addNumVictoryPoints(2);
		assertEquals(2, player.getNumVictoryPoints());
	}

	@Test
	public void testGetFirstCardFromEndGame() {
		Player player = createMockedPlayer();
		Card card = createWorkersGuild();
		player.addCardToStoragePile(card);

		Assert.assertEquals(card, player.getCardFromEndGame(0));
	}

	@Test
	public void testGetTwoGuildCardsFromEndGame() {
		Player player = createMockedPlayer();
		Card card = createWorkersGuild();
		player.addCardToStoragePile(card);
		Card card2 = createCraftsmenGuild();
		player.addCardToStoragePile(card2);

		Assert.assertEquals(card, player.getCardFromEndGame(0));
		Assert.assertEquals(card2, player.getCardFromEndGame(1));
	}

	@Test
	public void testGetGuildCardAfterRegularCardFromEndGame() {
		Player player = createMockedPlayer();
		Card card = createCourthouseCard();
		player.addCardToStoragePile(card);
		Card card2 = createCraftsmenGuild();
		player.addCardToStoragePile(card2);

		Assert.assertEquals(card2, player.getCardFromEndGame(0));
	}

	@Test
	public void testGetTooManyGuildCardsFromEndGame() {
		Player player = createMockedPlayer();
		Card card = createCourthouseCard();
		player.addCardToStoragePile(card);
		Card card2 = createCraftsmenGuild();
		player.addCardToStoragePile(card2);

		Assert.assertEquals(card2, player.getCardFromEndGame(0));
		try {
			player.getCardFromEndGame(1);
			fail();
		} catch (Exception e) {
			Assert.assertEquals("End of End Game pile reached", e.getMessage());
		}
	}

	@Test
	public void testGetGuildCardFromEmptyEndGame() {
		Player player = createMockedPlayer();

		try {
			player.getCardFromEndGame(0);
			fail();
		} catch (Exception e) {
			Assert.assertEquals("End of End Game pile reached", e.getMessage());
		}
	}

	@Test
	public void testGetAmountOfScienceOneOfEach() {
		Player player = createMockedPlayer();

		Card card1 = createApocathery();
		player.addCardToStoragePile(createApocathery());
		player.addCardToStoragePile(createScriptorium());
		player.addCardToStoragePile(createWorkshop());

		int[] amounts = player.getNumberOfEachScience();

		Assert.assertEquals(1, amounts[0]);
		Assert.assertEquals(1, amounts[1]);
		Assert.assertEquals(1, amounts[2]);
	}

	@Test
	public void testGetAmountOfScienceOneOfTwoAndTwoOfOne() {
		Player player = createMockedPlayer();
		Card card1 = createApocathery();
		player.addCardToStoragePile(createApocathery());
		player.addCardToStoragePile(createScriptorium());
		player.addCardToStoragePile(createWorkshop());
		player.addCardToStoragePile(createWorkshop());

		int[] amounts = player.getNumberOfEachScience();

		Assert.assertEquals(1, amounts[0]);
		Assert.assertEquals(1, amounts[1]);
		Assert.assertEquals(2, amounts[2]);
	}

	@Test
	public void testGetAmountOfScienceEmpty() {
		Player player = createMockedPlayer();
		int[] amounts = player.getNumberOfEachScience();

		Assert.assertEquals(0, amounts[0]);
		Assert.assertEquals(0, amounts[1]);
		Assert.assertEquals(0, amounts[2]);
	}

	private Card createWorkersGuild() {
		HashMap<Enum, Integer> costs = new HashMap<Enum, Integer>();
		costs.put(RawResource.LUMBER, 1);
		costs.put(RawResource.CLAY, 1);
		costs.put(RawResource.ORE, 2);
		costs.put(RawResource.STONE, 1);
		Cost cost = new Cost(CostType.RESOURCE, costs);
		Effect effect = new ValueEffect(Value.GUILD, AffectingEntity.RAWRESOURCES, Direction.NEIGHBORS, 1);
		Card card = new Card("Workers Guild", CardType.GUILD, cost, effect);
		return card;
	}

	private Card createCraftsmenGuild() {
		HashMap<Enum, Integer> costs = new HashMap<Enum, Integer>();
		costs.put(RawResource.ORE, 2);
		costs.put(RawResource.STONE, 2);
		Cost cost = new Cost(CostType.RESOURCE, costs);
		Effect effect = new ValueEffect(Value.GUILD, AffectingEntity.MANUFACTUREDGOODS, Direction.NEIGHBORS, 2);
		Card card = new Card("Craftsmens Guild", CardType.GUILD, cost, effect);
		return card;
	}

	private Card createCourthouseCard() {
		HashMap<Enum, Integer> costs = new HashMap<Enum, Integer>();
		costs.put(Good.LOOM, 1);
		costs.put(RawResource.CLAY, 2);
		Cost cost = new Cost(CostType.MULTITYPE, costs);
		Effect effect = new ValueEffect(Value.VICTORYPOINTS, AffectingEntity.NONE, 4);
		Card card = new Card("Courthouse", CardType.SCIENTIFICSTRUCTURE, cost, effect);
		return card;
	}

	private Card createApocathery() {
		HashMap<Enum, Integer> costs = new HashMap<Enum, Integer>();
		costs.put(Good.LOOM, 1);
		Cost cost = new Cost(CostType.GOOD, costs);
		HashMap<Enum, Integer> entitiesAndAmounts = new HashMap<Enum, Integer>();
		entitiesAndAmounts.put(Science.PROTRACTOR, 1);
		Effect effect = new EntityEffect(EntityType.SCIENCE, entitiesAndAmounts);
		Card card = new Card("Apothecary", CardType.SCIENTIFICSTRUCTURE, cost, effect);
		return card;
	}

	private Card createWorkshop() {
		HashMap<Enum, Integer> costs = new HashMap<Enum, Integer>();
		costs.put(Good.GLASS, 1);
		Cost cost = new Cost(CostType.GOOD, costs);
		HashMap<Enum, Integer> entitiesAndAmounts = new HashMap<Enum, Integer>();
		entitiesAndAmounts.put(Science.WHEEL, 1);
		Effect effect = new EntityEffect(EntityType.SCIENCE, entitiesAndAmounts);
		Card card = new Card("Workshop", CardType.SCIENTIFICSTRUCTURE, cost, effect);
		return card;
	}

	private Card createScriptorium() {
		HashMap<Enum, Integer> costs = new HashMap<Enum, Integer>();
		costs.put(Good.PRESS, 1);
		Cost cost = new Cost(CostType.GOOD, costs);
		HashMap<Enum, Integer> entitiesAndAmounts = new HashMap<Enum, Integer>();
		entitiesAndAmounts.put(Science.TABLET, 1);
		Effect effect = new EntityEffect(EntityType.SCIENCE, entitiesAndAmounts);
		Card card = new Card("Scriptorium", CardType.SCIENTIFICSTRUCTURE, cost, effect);
		return card;
	}

	@Test
	public void testBuildNextLevelColossusSideA() {
		setWonder(Side.A, WonderType.COLOSSUS);
		Player player = createPlayerWithWonder();
		this.helper.setWonder(this.wonder);

		HashMap<Frequency, HashSet<Effect>> expected = this.helper.getExpectedLevel(1).getEffects();
		HashMap<Frequency, HashSet<Effect>> actual = player.buildNextLevel();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(2).getEffects();
		actual = player.buildNextLevel();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(3).getEffects();
		actual = player.buildNextLevel();
		assertTrue(equalEffects(expected, actual));
	}

	@Test
	public void testBuildNextLevelColossusSideB() {
		setWonder(Side.B, WonderType.COLOSSUS);
		Player player = createPlayerWithWonder();
		this.helper.setWonder(this.wonder);

		HashMap<Frequency, HashSet<Effect>> expected = this.helper.getExpectedLevel(1).getEffects();
		HashMap<Frequency, HashSet<Effect>> actual = player.buildNextLevel();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(2).getEffects();
		actual = player.buildNextLevel();
		assertTrue(equalEffects(expected, actual));
	}

	@Test
	public void testBuildNextLevelLighthouseSideA() {
		setWonder(Side.A, WonderType.LIGHTHOUSE);
		Player player = createPlayerWithWonder();
		this.helper.setWonder(this.wonder);

		HashMap<Frequency, HashSet<Effect>> expected = this.helper.getExpectedLevel(1).getEffects();
		HashMap<Frequency, HashSet<Effect>> actual = player.buildNextLevel();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(2).getEffects();
		actual = player.buildNextLevel();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(3).getEffects();
		actual = player.buildNextLevel();
		assertTrue(equalEffects(expected, actual));
	}

	@Test
	public void testBuildNextLevelLighthouseSideB() {
		setWonder(Side.B, WonderType.LIGHTHOUSE);
		Player player = createPlayerWithWonder();
		this.helper.setWonder(this.wonder);

		HashMap<Frequency, HashSet<Effect>> expected = this.helper.getExpectedLevel(1).getEffects();
		HashMap<Frequency, HashSet<Effect>> actual = player.buildNextLevel();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(2).getEffects();
		actual = player.buildNextLevel();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(3).getEffects();
		actual = player.buildNextLevel();
		assertTrue(equalEffects(expected, actual));
	}

	@Test
	public void testBuildNextLevelTempleSideA() {
		setWonder(Side.A, WonderType.TEMPLE);
		Player player = createPlayerWithWonder();
		this.helper.setWonder(this.wonder);

		HashMap<Frequency, HashSet<Effect>> expected = this.helper.getExpectedLevel(1).getEffects();
		HashMap<Frequency, HashSet<Effect>> actual = player.buildNextLevel();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(2).getEffects();
		actual = player.buildNextLevel();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(3).getEffects();
		actual = player.buildNextLevel();
		assertTrue(equalEffects(expected, actual));
	}

	@Test
	public void testBuildNextLevelTempleSideB() {
		setWonder(Side.B, WonderType.TEMPLE);
		Player player = createPlayerWithWonder();
		this.helper.setWonder(this.wonder);

		HashMap<Frequency, HashSet<Effect>> expected = this.helper.getExpectedLevel(1).getEffects();
		HashMap<Frequency, HashSet<Effect>> actual = player.buildNextLevel();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(2).getEffects();
		actual = player.buildNextLevel();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(3).getEffects();
		actual = player.buildNextLevel();
		assertTrue(equalEffects(expected, actual));
	}

	@Test
	public void testBuildNextLevelStatueSideA() {
		setWonder(Side.A, WonderType.STATUE);
		Player player = createPlayerWithWonder();
		this.helper.setWonder(this.wonder);

		HashMap<Frequency, HashSet<Effect>> expected = this.helper.getExpectedLevel(1).getEffects();
		HashMap<Frequency, HashSet<Effect>> actual = player.buildNextLevel();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(2).getEffects();
		actual = player.buildNextLevel();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(3).getEffects();
		actual = player.buildNextLevel();
		assertTrue(equalEffects(expected, actual));
	}

	@Test
	public void testBuildNextLevelStatueSideB() {
		setWonder(Side.B, WonderType.STATUE);
		Player player = createPlayerWithWonder();
		this.helper.setWonder(this.wonder);

		HashMap<Frequency, HashSet<Effect>> expected = this.helper.getExpectedLevel(1).getEffects();
		HashMap<Frequency, HashSet<Effect>> actual = player.buildNextLevel();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(2).getEffects();
		actual = player.buildNextLevel();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(3).getEffects();
		actual = player.buildNextLevel();
		assertTrue(equalEffects(expected, actual));
	}

	@Test
	public void testBuildNextLevelGardensSideA() {
		setWonder(Side.A, WonderType.GARDENS);
		Player player = createPlayerWithWonder();
		this.helper.setWonder(this.wonder);

		HashMap<Frequency, HashSet<Effect>> expected = this.helper.getExpectedLevel(1).getEffects();
		HashMap<Frequency, HashSet<Effect>> actual = player.buildNextLevel();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(2).getEffects();
		actual = player.buildNextLevel();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(3).getEffects();
		actual = player.buildNextLevel();
		assertTrue(equalEffects(expected, actual));
	}

	@Test
	public void testBuildNextLevelGardensSideB() {
		setWonder(Side.B, WonderType.GARDENS);
		Player player = createPlayerWithWonder();
		this.helper.setWonder(this.wonder);

		HashMap<Frequency, HashSet<Effect>> expected = this.helper.getExpectedLevel(1).getEffects();
		HashMap<Frequency, HashSet<Effect>> actual = player.buildNextLevel();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(2).getEffects();
		actual = player.buildNextLevel();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(3).getEffects();
		actual = player.buildNextLevel();
		assertTrue(equalEffects(expected, actual));
	}

	@Test
	public void testBuildNextLevelMausoleumSideA() {
		setWonder(Side.A, WonderType.MAUSOLEUM);
		Player player = createPlayerWithWonder();
		this.helper.setWonder(this.wonder);

		HashMap<Frequency, HashSet<Effect>> expected = this.helper.getExpectedLevel(1).getEffects();
		HashMap<Frequency, HashSet<Effect>> actual = player.buildNextLevel();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(2).getEffects();
		actual = player.buildNextLevel();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(3).getEffects();
		actual = player.buildNextLevel();
		assertTrue(equalEffects(expected, actual));
	}

	@Test
	public void testBuildNextLevelMausoleumSideB() {
		setWonder(Side.B, WonderType.MAUSOLEUM);
		Player player = createPlayerWithWonder();
		this.helper.setWonder(this.wonder);

		HashMap<Frequency, HashSet<Effect>> expected = this.helper.getExpectedLevel(1).getEffects();
		HashMap<Frequency, HashSet<Effect>> actual = player.buildNextLevel();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(2).getEffects();
		actual = player.buildNextLevel();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(3).getEffects();
		actual = player.buildNextLevel();
		assertTrue(equalEffects(expected, actual));
	}

	@Test
	public void testBuildNextLevelPyramidsSideA() {
		setWonder(Side.A, WonderType.PYRAMIDS);
		Player player = createPlayerWithWonder();
		this.helper.setWonder(this.wonder);

		HashMap<Frequency, HashSet<Effect>> expected = this.helper.getExpectedLevel(1).getEffects();
		HashMap<Frequency, HashSet<Effect>> actual = player.buildNextLevel();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(2).getEffects();
		actual = player.buildNextLevel();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(3).getEffects();
		actual = player.buildNextLevel();
		assertTrue(equalEffects(expected, actual));
	}

	@Test
	public void testBuildNextLevelPyramidsSideB() {
		setWonder(Side.B, WonderType.PYRAMIDS);
		Player player = createPlayerWithWonder();
		this.helper.setWonder(this.wonder);

		HashMap<Frequency, HashSet<Effect>> expected = this.helper.getExpectedLevel(1).getEffects();
		HashMap<Frequency, HashSet<Effect>> actual = player.buildNextLevel();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(2).getEffects();
		actual = player.buildNextLevel();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(3).getEffects();
		actual = player.buildNextLevel();
		assertTrue(equalEffects(expected, actual));

		expected = this.helper.getExpectedLevel(4).getEffects();
		actual = player.buildNextLevel();
		assertTrue(equalEffects(expected, actual));
	}

	private void setWonder(Side side, WonderType type) {
		this.wonder = new Wonder(side, type);
	}

	private boolean equalEffects(HashMap<Frequency, HashSet<Effect>> expected,
			HashMap<Frequency, HashSet<Effect>> actual) {
		for (Frequency frequency : expected.keySet()) {
			if (!actual.containsKey(frequency)) {
				return false;
			} else if (!compareEffects(frequency, expected.get(frequency), actual.get(frequency))) {
				return false;
			} else {
				continue;
			}
		}
		return true;
	}

	private boolean compareEffects(Frequency frequency, HashSet<Effect> expected, HashSet<Effect> actual) {
		for (Effect effect : expected) {
			if (otherContainsEffect(actual, effect)) {
				continue;
			} else {
				return false;
			}
		}
		return true;
	}

	private boolean otherContainsEffect(HashSet<Effect> actual, Effect expectedEffect) {
		for (Effect otherEffect : actual) {
			EffectType type = expectedEffect.getEffectType();
			switch (type) {
			case ABILITY:
				if (((AbilityEffect) expectedEffect).equals((AbilityEffect) otherEffect)) {
					return true;
				}
				break;
			case VALUE:
				if (((ValueEffect) expectedEffect).equals((ValueEffect) otherEffect)) {
					return true;
				}
				break;
			case ENTITY:
				if (((EntityEffect) expectedEffect).equals((EntityEffect) otherEffect)) {
					return true;
				}
				break;
			default:
				throw new IllegalArgumentException("Invalid Effect Type");
			}
		}
		return false;
	}

	private Player createMockedPlayer() {
		Wonder wonder = EasyMock.createStrictMock(Wonder.class);
		return EasyMock.partialMockBuilder(Player.class).addMockedMethod("addWonderResourceToPile")
				.withConstructor("Jane Doe", wonder).createMock();
	}

	private Player createPlayerWithWonder() {
		return new Player("Jane Doe", this.wonder);
	}

	// END GENERATED CODE
}