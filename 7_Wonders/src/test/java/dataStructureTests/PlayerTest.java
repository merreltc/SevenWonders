package dataStructureTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import backend.handlers.PlayerChipHandler;
import backend.handlers.SetUpDeckHandler;
import constants.GeneralEnums.CostType;
import constants.GeneralEnums.Good;
import constants.GeneralEnums.RawResource;
import constants.GeneralEnums.Science;
import constants.GeneralEnums.Side;
import dataStructures.gameMaterials.Card;
import dataStructures.gameMaterials.Card.CardType;
import dataStructures.gameMaterials.Cost;
import dataStructures.gameMaterials.Deck.Age;
import dataStructures.gameMaterials.Effect;
import dataStructures.gameMaterials.Effect.Direction;
import dataStructures.gameMaterials.Effect.EffectType;
import dataStructures.gameMaterials.EntityEffect;
import dataStructures.gameMaterials.EntityEffect.EntityType;
import dataStructures.gameMaterials.ValueEffect;
import dataStructures.gameMaterials.ValueEffect.AffectingEntity;
import dataStructures.gameMaterials.ValueEffect.Value;
import dataStructures.gameMaterials.Wonder;
import dataStructures.gameMaterials.Wonder.WonderType;
import dataStructures.playerData.Chip;
import dataStructures.playerData.Chip.ChipValue;
import dataStructures.playerData.Player;

public class PlayerTest {

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
		assertEquals(ArrayList.class, player.getAllEffects().getClass());
	}

	@Test
	public void testSetStoragePileHand() {
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

		player.addToStoragePile(deckCards.get(3));
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

		Player player = new Player("Jane Doe", wonder);
		assertEquals("The Statue of Zeus in Olympia", player.getWonder().getName());
	}

	@Test
	public void testCreatePlayerWithWonder2() {
		Wonder wonder = EasyMock.mock(Wonder.class);
		EasyMock.expect(wonder.getType()).andReturn(WonderType.LIGHTHOUSE);
		EasyMock.expect(wonder.getName()).andReturn("The Lighthouse of Alexandria");
		EasyMock.replay(wonder);
		Player player = new Player("Jane Doe", wonder);
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

	private Player createMockedPlayer() {
		Wonder wonder = EasyMock.createStrictMock(Wonder.class);
		return EasyMock.partialMockBuilder(Player.class).withConstructor("Jane Doe", wonder).createMock();
	}

	@Test
	public void testGetFirstCardFromEndGame() {
		Player player = createMockedPlayer();
		Card card = createWorkersGuild();
		player.addToStoragePile(card);

		Assert.assertEquals(card, player.getCardFromEndGame(0));
	}

	@Test
	public void testGetTwoGuildCardsFromEndGame() {
		Player player = createMockedPlayer();
		Card card = createWorkersGuild();
		player.addToStoragePile(card);
		Card card2 = createCraftsmenGuild();
		player.addToStoragePile(card2);

		Assert.assertEquals(card, player.getCardFromEndGame(0));
		Assert.assertEquals(card2, player.getCardFromEndGame(1));
	}

	@Test
	public void testGetGuildCardAfterRegularCardFromEndGame() {
		Player player = createMockedPlayer();
		Card card = createCourthouseCard();
		player.addToStoragePile(card);
		Card card2 = createCraftsmenGuild();
		player.addToStoragePile(card2);

		Assert.assertEquals(card2, player.getCardFromEndGame(0));
	}

	@Test
	public void testGetTooManyGuildCardsFromEndGame() {
		Player player = createMockedPlayer();
		Card card = createCourthouseCard();
		player.addToStoragePile(card);
		Card card2 = createCraftsmenGuild();
		player.addToStoragePile(card2);

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
		player.addToStoragePile(createApocathery());
		player.addToStoragePile(createScriptorium());
		player.addToStoragePile(createWorkshop());

		int[] amounts = player.getNumberOfEachScience();

		Assert.assertEquals(1, amounts[0]);
		Assert.assertEquals(1, amounts[1]);
		Assert.assertEquals(1, amounts[2]);
	}

	@Test
	public void testGetAmountOfScienceOneOfTwoAndTwoOfOne() {
		Player player = createMockedPlayer();
		Card card1 = createApocathery();
		player.addToStoragePile(createApocathery());
		player.addToStoragePile(createScriptorium());
		player.addToStoragePile(createWorkshop());
		player.addToStoragePile(createWorkshop());

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
		Effect effect = new ValueEffect(EffectType.VALUE, Value.GUILD, AffectingEntity.RAWRESOURCES,
				Direction.NEIGHBORS, 1);
		Card card = new Card("Workers Guild", CardType.GUILD, cost, effect);
		return card;
	}

	private Card createCraftsmenGuild() {
		HashMap<Enum, Integer> costs = new HashMap<Enum, Integer>();
		costs.put(RawResource.ORE, 2);
		costs.put(RawResource.STONE, 2);
		Cost cost = new Cost(CostType.RESOURCE, costs);
		Effect effect = new ValueEffect(EffectType.VALUE, Value.GUILD, AffectingEntity.MANUFACTUREDGOODS,
				Direction.NEIGHBORS, 2);
		Card card = new Card("Craftsmens Guild", CardType.GUILD, cost, effect);
		return card;
	}

	private Card createCourthouseCard() {
		HashMap<Enum, Integer> costs = new HashMap<Enum, Integer>();
		costs.put(Good.LOOM, 1);
		costs.put(RawResource.CLAY, 2);
		Cost cost = new Cost(CostType.MULTITYPE, costs);
		Effect effect = new ValueEffect(EffectType.VALUE, Value.VICTORYPOINTS, AffectingEntity.NONE, 4);
		Card card = new Card("Courthouse", CardType.SCIENTIFICSTRUCTURE, cost, effect);
		return card;
	}

	private Card createApocathery() {
		HashMap<Enum, Integer> costs = new HashMap<Enum, Integer>();
		costs.put(Good.LOOM, 1);
		Cost cost = new Cost(CostType.GOOD, costs);
		HashMap<Enum, Integer> entitiesAndAmounts = new HashMap<Enum, Integer>();
		entitiesAndAmounts.put(Science.PROTRACTOR, 1);
		Effect effect = new EntityEffect(EffectType.ENTITY, EntityType.SCIENCE, entitiesAndAmounts);
		Card card = new Card("Apothecary", CardType.SCIENTIFICSTRUCTURE, cost, effect);
		return card;
	}

	private Card createWorkshop() {
		HashMap<Enum, Integer> costs = new HashMap<Enum, Integer>();
		costs.put(Good.GLASS, 1);
		Cost cost = new Cost(CostType.GOOD, costs);
		HashMap<Enum, Integer> entitiesAndAmounts = new HashMap<Enum, Integer>();
		entitiesAndAmounts.put(Science.WHEEL, 1);
		Effect effect = new EntityEffect(EffectType.ENTITY, EntityType.SCIENCE, entitiesAndAmounts);
		Card card = new Card("Workshop", CardType.SCIENTIFICSTRUCTURE, cost, effect);
		return card;
	}

	private Card createScriptorium() {
		HashMap<Enum, Integer> costs = new HashMap<Enum, Integer>();
		costs.put(Good.PRESS, 1);
		Cost cost = new Cost(CostType.GOOD, costs);
		HashMap<Enum, Integer> entitiesAndAmounts = new HashMap<Enum, Integer>();
		entitiesAndAmounts.put(Science.TABLET, 1);
		Effect effect = new EntityEffect(EffectType.ENTITY, EntityType.SCIENCE, entitiesAndAmounts);
		Card card = new Card("Scriptorium", CardType.SCIENTIFICSTRUCTURE, cost, effect);
		return card;
	}
}