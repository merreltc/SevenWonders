package backendTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import backend.handlers.PlayerTurnHandler;
import backend.handlers.SetUpDeckHandler;
import constants.GeneralEnums.CostType;
import constants.GeneralEnums.RawResource;
import constants.GeneralEnums.Resource;
import constants.GeneralEnums.Side;
import dataStructures.GameBoard;
import dataStructures.gameMaterials.Card;
import dataStructures.gameMaterials.Deck;
import dataStructures.gameMaterials.Deck.Age;
import dataStructures.gameMaterials.Effect.EffectType;
import dataStructures.gameMaterials.EntityEffect;
import dataStructures.gameMaterials.ValueEffect;
import dataStructures.gameMaterials.ValueEffect.ValueType;
import dataStructures.gameMaterials.Wonder.WonderType;
import dataStructures.gameMaterials.Wonder;
import dataStructures.playerData.Player;
import dataStructures.playerData.Chip.ChipValue;
import exceptions.InsufficientFundsException;
import testHelpers.SetUpDeckTestHelper;

public class PlayerTurnHandlerTest {
	private Deck age1Deck, age2Deck;
	private GameBoard gameBoard;

	@Before
	public void setUp() {
		// this.gameBoard =
		// EasyMock.partialMockBuilder(GameBoard.class).createMock();
		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		this.age1Deck = new Deck(Age.AGE1, cards);
		ArrayList<Card> cards2 = new SetUpDeckHandler().createCards(Age.AGE2, 3);
		this.age2Deck = new Deck(Age.AGE2, cards2);
	}

	@Test
	public void testBuildStructureNoCost() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);

		GameBoard board = new GameBoard(players, this.age1Deck);
		Player current = board.getCurrentPlayer();
		ArrayList<Card> currentHand = new ArrayList<Card>();

		currentHand.add(this.age1Deck.getCard(0));
		current.setCurrentHand(currentHand);

		PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler();
		playerTurnHandler.setGameBoard(board);
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0));

		assertEquals(1, current.getAllCards().size());
		assertEquals(0, current.getCurrentHand().size());
		assertFalse(current.getCurrentHand().contains(this.age1Deck.getCard(0)));
		assertTrue(current.getAllCards().contains(this.age1Deck.getCard(0)));
	}

	@Test
	public void testBuildStructureCoinCost() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);

		GameBoard board = new GameBoard(players, this.age1Deck);
		Player current = board.getCurrentPlayer();
		ArrayList<Card> currentHand = new ArrayList<Card>();

		currentHand.add(this.age1Deck.getCard(4));
		current.setCurrentHand(currentHand);

		PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler();
		playerTurnHandler.setGameBoard(board);
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0));

		assertEquals(1, current.getAllCards().size());
		assertEquals(0, current.getCurrentHand().size());
		assertEquals(0, (int) current.getCoins().get(ChipValue.THREE));
		assertFalse(current.getCurrentHand().contains(this.age1Deck.getCard(4)));
		assertTrue(current.getAllCards().contains(this.age1Deck.getCard(4)));
		assertEquals(2, current.getCoinTotal());
	}

	@Test
	public void testValidBuildStructureResourceCost() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);

		GameBoard board = new GameBoard(players, this.age1Deck);
		Player current = board.getCurrentPlayer();
		ArrayList<Card> currentHand = new ArrayList<Card>();

		currentHand.add(this.age1Deck.getCard(1)); // Stone pit
		currentHand.add(this.age1Deck.getCard(9)); // baths
		current.setCurrentHand(currentHand);

		PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler();
		playerTurnHandler.setGameBoard(board);
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0));
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0));

		assertEquals(2, current.getAllCards().size());
		assertEquals(0, current.getCurrentHand().size());
		assertFalse(current.getCurrentHand().contains(this.age1Deck.getCard(9)));
		assertTrue(current.getAllCards().contains(this.age1Deck.getCard(9)));
	}

	@Test(expected = InsufficientFundsException.class)
	public void testInvalidBuildStructureResourceCost() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);

		GameBoard board = new GameBoard(players, this.age1Deck);
		Player current = board.getCurrentPlayer();
		ArrayList<Card> currentHand = new ArrayList<Card>();

		currentHand.add(this.age1Deck.getCard(0)); // lumber yard
		currentHand.add(this.age1Deck.getCard(9)); // baths
		current.setCurrentHand(currentHand);

		PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler();
		playerTurnHandler.setGameBoard(board);
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0));
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0));
		fail();
	}

	@Test
	public void testValidBuildStructureEntityCost() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);

		GameBoard board = new GameBoard(players, this.age1Deck);
		Player current = board.getCurrentPlayer();
		ArrayList<Card> currentHand = new ArrayList<Card>();

		currentHand.add(this.age1Deck.getCard(6)); // loom
		currentHand.add(this.age1Deck.getCard(18)); // apothecary
		current.setCurrentHand(currentHand);

		PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler();
		playerTurnHandler.setGameBoard(board);
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0));
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0));

		assertEquals(2, current.getAllCards().size());
		assertEquals(0, current.getCurrentHand().size());
		assertFalse(current.getCurrentHand().contains(this.age1Deck.getCard(6)));
		assertTrue(current.getAllCards().contains(this.age1Deck.getCard(6)));
		assertFalse(current.getCurrentHand().contains(this.age1Deck.getCard(18)));
		assertTrue(current.getAllCards().contains(this.age1Deck.getCard(18)));
	}

	@Test(expected = InsufficientFundsException.class)
	public void testInvalidBuildStructureGoodCost() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);

		GameBoard board = new GameBoard(players, this.age1Deck);
		Player current = board.getCurrentPlayer();
		ArrayList<Card> currentHand = new ArrayList<Card>();

		currentHand.add(this.age1Deck.getCard(0)); // lumber yard
		currentHand.add(this.age1Deck.getCard(18)); // baths
		current.setCurrentHand(currentHand);

		PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler();
		playerTurnHandler.setGameBoard(board);
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0));
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0));
		fail();
	}

	@Test
	public void testValidBuildStructure2ResourceCost() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);
		ArrayList<Card> cards2 = new SetUpDeckHandler().createCards(Age.AGE2, 3);
		Deck age2Deck = new Deck(Age.AGE2, cards2);

		GameBoard board = new GameBoard(players, age2Deck);
		Player current = board.getCurrentPlayer();
		ArrayList<Card> currentHand = new ArrayList<Card>();

		currentHand.add(age2Deck.getCard(0)); // sawmill
		currentHand.add(age2Deck.getCard(3)); // foundry
		currentHand.add(age2Deck.getCard(9)); // statue
		current.setCurrentHand(currentHand);

		PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler();
		playerTurnHandler.setGameBoard(board);
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0));
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0));
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0));

		assertEquals(3, current.getAllCards().size());
		assertEquals(0, current.getCurrentHand().size());
		assertFalse(current.getCurrentHand().contains(age2Deck.getCard(0)));
		assertTrue(current.getAllCards().contains(age2Deck.getCard(0)));
		assertFalse(current.getCurrentHand().contains(age2Deck.getCard(3)));
		assertTrue(current.getAllCards().contains(age2Deck.getCard(3)));
		assertFalse(current.getCurrentHand().contains(age2Deck.getCard(9)));
		assertTrue(current.getAllCards().contains(age2Deck.getCard(9)));
	}

	@Test(expected = InsufficientFundsException.class)
	public void testInalidBuildStructure2ResourceCost() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);

		GameBoard board = new GameBoard(players, this.age2Deck);
		Player current = board.getCurrentPlayer();
		ArrayList<Card> currentHand = new ArrayList<Card>();

		currentHand.add(this.age2Deck.getCard(1)); // quarry
		currentHand.add(this.age2Deck.getCard(7)); // aqueduct
		current.setCurrentHand(currentHand);

		PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler();
		playerTurnHandler.setGameBoard(board);
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0));
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0));
		fail();
	}

	@Test
	public void testValidBuildStructureMultiEntityCost() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);

		GameBoard board = new GameBoard(players, this.age2Deck);
		Player current = board.getCurrentPlayer();
		ArrayList<Card> currentHand = new ArrayList<Card>();

		currentHand.add(this.age2Deck.getCard(3)); // foundry
		currentHand.add(this.age2Deck.getCard(5)); // glassworks
		currentHand.add(this.age2Deck.getCard(16)); // Dispensary
		current.setCurrentHand(currentHand);

		PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler();
		playerTurnHandler.setGameBoard(board);
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0));
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0));
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0));

		assertEquals(3, current.getAllCards().size());
		assertEquals(0, current.getCurrentHand().size());
		assertFalse(current.getCurrentHand().contains(this.age2Deck.getCard(3)));
		assertTrue(current.getAllCards().contains(this.age2Deck.getCard(3)));
		assertFalse(current.getCurrentHand().contains(this.age2Deck.getCard(5)));
		assertTrue(current.getAllCards().contains(this.age2Deck.getCard(5)));
		assertFalse(current.getCurrentHand().contains(this.age2Deck.getCard(16)));
		assertTrue(current.getAllCards().contains(this.age2Deck.getCard(16)));
	}

	@Test(expected = InsufficientFundsException.class)
	public void testInvalidBuildStructureMultiEntityCost() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);

		GameBoard board = new GameBoard(players, this.age2Deck);
		Player current = board.getCurrentPlayer();
		ArrayList<Card> currentHand = new ArrayList<Card>();

		currentHand.add(this.age2Deck.getCard(3)); // foundry
		currentHand.add(this.age2Deck.getCard(6)); // press
		currentHand.add(this.age2Deck.getCard(18)); // Dispensary
		current.setCurrentHand(currentHand);

		PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler();
		playerTurnHandler.setGameBoard(board);
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0));
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0));
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0));
		fail();
	}

	@Test
	public void testDiscardSelectedCard() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);

		GameBoard board = new GameBoard(players, this.age2Deck);
		Player current = board.getCurrentPlayer();
		ArrayList<Card> currentHand = new ArrayList<Card>();

		Card discarded = this.age2Deck.getCard(3);
		currentHand.add(discarded); // foundry
		currentHand.add(this.age2Deck.getCard(6)); // press
		currentHand.add(this.age2Deck.getCard(18)); // Dispensary
		current.setCurrentHand(currentHand);

		PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler();
		playerTurnHandler.setGameBoard(board);
		playerTurnHandler.discardSelectedCard(current, discarded);

		assertEquals(1, board.getDiscardPile().size());
		assertEquals(6, current.getCoinTotal());
		assertTrue(board.getDiscardPile().contains(discarded));
		assertEquals(2, current.getCurrentHand().size());
		assertFalse(current.getCurrentHand().contains(discarded));
	}

	@Test
	public void testBuildStructureAddOneShield() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);

		GameBoard board = new GameBoard(players, this.age1Deck);
		Player current = board.getCurrentPlayer();
		ArrayList<Card> currentHand = new ArrayList<Card>();

		Card lumber = this.age1Deck.getCard(0);
		Card stockade = this.age1Deck.getCard(15);
		currentHand.add(lumber); // lumber yard
		currentHand.add(stockade); // stockade

		current.setCurrentHand(currentHand);

		PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler();
		playerTurnHandler.setGameBoard(board);
		playerTurnHandler.buildStructure(current, lumber);
		playerTurnHandler.buildStructure(current, stockade);

		assertEquals(1, current.getNumShields());
	}

	@Test
	public void testBuildStructureAddTwoShields() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);

		GameBoard board = new GameBoard(players, this.age1Deck);
		Player current = board.getCurrentPlayer();
		ArrayList<Card> currentHand = new ArrayList<Card>();

		Card stone = this.age1Deck.getCard(1);
		Card quarry = this.age2Deck.getCard(1);
		Card walls = this.age2Deck.getCard(13);

		currentHand.add(quarry);
		currentHand.add(stone);
		currentHand.add(walls);

		current.setCurrentHand(currentHand);

		PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler();
		playerTurnHandler.setGameBoard(board);
		playerTurnHandler.buildStructure(current, quarry);
		playerTurnHandler.buildStructure(current, stone);
		playerTurnHandler.buildStructure(current, walls);

		assertEquals(2, current.getNumShields());
	}

	@Test
	public void testBuildStructureAddTwoShieldsAlreadyUsedEntity() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Jane Doe", EasyMock.mock(Wonder.class)));
		players.add(new Player("John Doe", EasyMock.mock(Wonder.class)));
		players.add(new Player("James Doe", EasyMock.mock(Wonder.class)));
		ArrayList<Card> age1Deck = SetUpDeckTestHelper.createAge1Cards(3);
		ArrayList<Card> age3Deck = SetUpDeckTestHelper.createAge3Cards(3);
		
		GameBoard board = new GameBoard(players, EasyMock.createMock(Deck.class));
		Player current = players.get(0);
		ArrayList<Card> currentHand = new ArrayList<Card>();

		Card claypit = age1Deck.get(4);
		Card ore = age1Deck.get(3);
		Card claypool = age1Deck.get(2);
		Card wood = age1Deck.get(0);
		Card stone = age1Deck.get(1);
		Card toBuild = age3Deck.get(0); // guild
		ArrayList<Card> storagePile = new ArrayList<Card>();
		storagePile.add(claypit);
		storagePile.add(ore);
		storagePile.add(claypool);
		storagePile.add(wood);
		storagePile.add(stone);
		currentHand.add(toBuild);

		current.setCurrentHand(currentHand);
		current.setStoragePile(storagePile);
		PlayerTurnHandler playerTurnHandler = EasyMock.partialMockBuilder(PlayerTurnHandler.class)
				.addMockedMethod("chooseWhichEntity").createMock();
		EasyMock.expect(playerTurnHandler.chooseWhichEntity(EasyMock.isA(HashMap.class), EasyMock.isA(String.class)))
				.andReturn("ORE");
		EasyMock.replay(playerTurnHandler);
		playerTurnHandler.setGameBoard(board);
		playerTurnHandler.buildStructure(current, toBuild);

		assertTrue(current.storagePileContainsCardByName("Workers Guild"));

		EasyMock.verify(playerTurnHandler);
	}

	@Test(expected = InsufficientFundsException.class)
	public void testInvalidBuildStructureAddTwoShieldsAlreadyUsedEntity() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);
		ArrayList<Card> age1Deck = SetUpDeckTestHelper.createAge1Cards(3);
		ArrayList<Card> age3Deck = SetUpDeckTestHelper.createAge3Cards(3);

		GameBoard board = new GameBoard(players, new Deck(Age.AGE3, age3Deck));
		Player current = board.getCurrentPlayer();
		ArrayList<Card> currentHand = new ArrayList<Card>();

		Card claypit = age1Deck.get(4);
		Card ore = age1Deck.get(3);
		Card wood = age1Deck.get(0);
		Card stone = age1Deck.get(1);
		Card toBuild = age3Deck.get(0); // guild
		ArrayList<Card> storagePile = new ArrayList<Card>();
		storagePile.add(claypit);
		storagePile.add(ore);
		storagePile.add(wood);
		storagePile.add(stone);
		currentHand.add(toBuild);

		current.setCurrentHand(currentHand);
		current.setStoragePile(storagePile);

		PlayerTurnHandler playerTurnHandler = EasyMock.partialMockBuilder(PlayerTurnHandler.class)
				.addMockedMethod("chooseWhichEntity").createMock();
		EasyMock.expect(playerTurnHandler.chooseWhichEntity(EasyMock.isA(HashMap.class), EasyMock.isA(String.class)))
				.andReturn("ORE");
		EasyMock.replay(playerTurnHandler);
		playerTurnHandler.setGameBoard(board);
		playerTurnHandler.buildStructure(current, toBuild);
		fail();
	}
	
	@Test(expected = InsufficientFundsException.class)
	public void testInvalidBuildStructureAddTwoShieldsAlreadyChoseWrongEntity() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);
		ArrayList<Card> age1Deck = SetUpDeckTestHelper.createAge1Cards(3);
		ArrayList<Card> age3Deck = SetUpDeckTestHelper.createAge3Cards(3);

		GameBoard board = new GameBoard(players, new Deck(Age.AGE3, age3Deck));
		Player current = board.getCurrentPlayer();
		ArrayList<Card> currentHand = new ArrayList<Card>();

		Card claypit = age1Deck.get(4);
		Card ore = age1Deck.get(3);
		Card wood = age1Deck.get(5);
		Card stone = age1Deck.get(1);
		Card toBuild = age3Deck.get(1); // guild
		ArrayList<Card> storagePile = new ArrayList<Card>();
		storagePile.add(claypit);
		storagePile.add(ore);
		storagePile.add(wood);
		storagePile.add(stone);
		currentHand.add(toBuild);

		current.setCurrentHand(currentHand);
		current.setStoragePile(storagePile);

		PlayerTurnHandler playerTurnHandler = EasyMock.partialMockBuilder(PlayerTurnHandler.class)
				.addMockedMethod("chooseWhichEntity").createMock();
		EasyMock.expect(playerTurnHandler.chooseWhichEntity(EasyMock.isA(HashMap.class), EasyMock.isA(String.class)))
				.andReturn("CLAY");
		EasyMock.expect(playerTurnHandler.chooseWhichEntity(EasyMock.isA(HashMap.class), EasyMock.isA(String.class)))
		.andReturn("LOOM");
		EasyMock.replay(playerTurnHandler);
		playerTurnHandler.setGameBoard(board);
		playerTurnHandler.buildStructure(current, toBuild);
		fail();
	}

	@Test(expected = InsufficientFundsException.class)
	public void testBuildStructureNotEnoughResourcesCanOnlyUseOneOrOther() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(5);

		GameBoard board = new GameBoard(players, this.age1Deck);
		Player current = board.getCurrentPlayer();
		ArrayList<Card> currentHand = new ArrayList<Card>();

		Card ore = this.age1Deck.getCard(7);
		Card forest = this.age1Deck.getCard(11);
		Card statue = this.age2Deck.getCard(16);

		currentHand.add(forest);
		currentHand.add(ore);
		currentHand.add(statue);

		current.setCurrentHand(currentHand);

		PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler();
		playerTurnHandler.setGameBoard(board);
		playerTurnHandler.buildStructure(current, ore);
		playerTurnHandler.buildStructure(current, forest);
		playerTurnHandler.buildStructure(current, statue);
		fail();
	}

	@Test
	public void testBuildStructureAddTwoVictoryPoints() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);

		GameBoard board = new GameBoard(players, this.age1Deck);
		Player current = board.getCurrentPlayer();
		ArrayList<Card> currentHand = new ArrayList<Card>();

		Card stone = this.age1Deck.getCard(1);
		Card baths = this.age1Deck.getCard(9);

		currentHand.add(stone);
		currentHand.add(baths);

		current.setCurrentHand(currentHand);

		PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler();
		playerTurnHandler.setGameBoard(board);
		playerTurnHandler.buildStructure(current, stone);
		playerTurnHandler.buildStructure(current, baths);

		assertEquals(3, current.getNumVictoryPoints());
	}

	@Test
	public void testBuildStructureAddCommerce() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);

		GameBoard board = new GameBoard(players, this.age1Deck);

		Player current = board.getCurrentPlayer();
		ArrayList<Card> currentHand = new ArrayList<Card>();

		Card east = this.age1Deck.getCard(12); // east trading post

		currentHand.add(east);

		current.setCurrentHand(currentHand);

		PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler();
		playerTurnHandler.setGameBoard(board);
		playerTurnHandler.buildStructure(current, east);

		assertEquals(0, current.getNumVictoryPoints());
		assertEquals(0, current.getNumShields());
		assertTrue(current.storagePileContainsCardByName("East Trading Post"));
	}

	@Test
	public void testBuildStructureAddCommerceCoinAdd() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(4);
		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 4);
		this.age1Deck = new Deck(Age.AGE1, cards);
		GameBoard board = new GameBoard(players, this.age1Deck);

		Player current = board.getCurrentPlayer();
		ArrayList<Card> currentHand = new ArrayList<Card>();

		Card tavern = this.age1Deck.getCard(16); // tavern
		currentHand.add(tavern);

		current.setCurrentHand(currentHand);

		PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler();
		playerTurnHandler.setGameBoard(board);
		playerTurnHandler.buildStructure(current, tavern);

		assertEquals(0, current.getNumVictoryPoints());
		assertEquals(0, current.getNumShields());
		assertEquals(8, current.getCoinTotal());
		assertEquals(1, (int) current.getCoins().get(ChipValue.THREE));
		assertTrue(current.storagePileContainsCardByName("Tavern"));
	}

	@Test
	public void testBuildStructureAddTwoVictoryPointsMockedVersion() {
		Player player = EasyMock.mock(Player.class);
		Card cardToBuild = EasyMock.mock(Card.class);
		HashMap<Enum, Integer> cost = new HashMap<Enum, Integer>();
		HashMap<Enum, Integer> entities = new HashMap<Enum, Integer>();

		cost.put(Resource.STONE, 1);
		entities.put(Resource.STONE, 1);

		ArrayList<Card> storage = new ArrayList<Card>();
		Card cardInStorage = EasyMock.mock(Card.class);
		storage.add(cardInStorage);
		String previousStructureName = "None";
		EasyMock.expect(cardToBuild.getName()).andReturn("Baths");
		EasyMock.expect(player.storagePileContainsCardByName("Baths")).andReturn(false);
		EasyMock.expect(cardToBuild.getPreviousStructureName()).andReturn(previousStructureName);
		EasyMock.expect(cardToBuild.getCostType()).andReturn(CostType.RESOURCE);
		EasyMock.expect(cardToBuild.getCostType()).andReturn(CostType.RESOURCE);
		EasyMock.expect(cardToBuild.getEffectType()).andReturn(EffectType.VALUE);
		EasyMock.expect(cardToBuild.getCost()).andReturn(cost);
		EasyMock.expect(player.getAllCards()).andReturn(storage);
		EasyMock.expect(player.getAllCards()).andReturn(storage);
		EntityEffect entityEffect = EasyMock.mock(EntityEffect.class);

		for (Card sCards : storage) {
			EasyMock.expect(sCards.getName()).andReturn("Brick Yard");
			EasyMock.expect(sCards.getEffectType()).andReturn(EffectType.ENTITY);

			EasyMock.expect(sCards.getEffect()).andReturn(entityEffect);
			EasyMock.expect(entityEffect.getEntities()).andReturn(entities);
			EasyMock.expect(entityEffect.getEntities()).andReturn(entities);
			EasyMock.expect(entityEffect.getEntities()).andReturn(entities);
		}

		ValueEffect effect = EasyMock.mock(ValueEffect.class);
		EasyMock.expect(cardToBuild.getEffect()).andReturn(effect);

		EasyMock.expect(effect.getValueType()).andReturn(ValueType.VICTORYPOINT);
		EasyMock.expect(effect.getValueAmount()).andReturn(Integer.valueOf(3));
		
		EasyMock.expect(effect.getValueType()).andReturn(ValueType.VICTORYPOINT);
		
		player.addNumVictoryPoints(3);
		player.addCardToStoragePile(cardToBuild);
		player.removeFromCurrentHand(cardToBuild);
		EasyMock.expect(player.getCurrentTrades()).andReturn(new HashMap<Enum, Integer>());
		EasyMock.replay(player, cardToBuild, cardInStorage, entityEffect, effect);

		PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler();
		playerTurnHandler.setGameBoard(this.gameBoard);

		playerTurnHandler.buildStructure(player, cardToBuild);

		EasyMock.verify(player, cardToBuild, cardInStorage, entityEffect, effect);
	}

	@Test
	public void testInvalidBuildStructureAlreadyHaveStructure() {
		Player player = EasyMock.mock(Player.class);
		Card cardToBuild = EasyMock.mock(Card.class);
		HashMap<Enum, Integer> cost = new HashMap<Enum, Integer>();
		HashMap<Enum, Integer> entities = new HashMap<Enum, Integer>();

		cost.put(Resource.STONE, 1);
		entities.put(Resource.STONE, 1);

		ArrayList<Card> storage = new ArrayList<Card>();
		Card cardInStorage = EasyMock.mock(Card.class);
		storage.add(cardInStorage);
		String previousStructureName = "None";
		EasyMock.expect(cardToBuild.getName()).andReturn("Baths");
		EasyMock.expect(cardToBuild.getPreviousStructureName()).andReturn(previousStructureName);
		EasyMock.expect(cardToBuild.getCostType()).andReturn(CostType.RESOURCE);
		EasyMock.expect(cardToBuild.getCostType()).andReturn(CostType.RESOURCE);
		EasyMock.expect(cardToBuild.getEffectType()).andReturn(EffectType.VALUE);
		EasyMock.expect(cardToBuild.getCost()).andReturn(cost);
		EasyMock.expect(player.getAllCards()).andReturn(storage);
		EasyMock.expect(player.getAllCards()).andReturn(storage);
		EntityEffect entityEffect = EasyMock.mock(EntityEffect.class);

		for (Card sCards : storage) {
			EasyMock.expect(sCards.getName()).andReturn("Brick Yard");
			EasyMock.expect(sCards.getEffectType()).andReturn(EffectType.ENTITY);

			EasyMock.expect(sCards.getEffect()).andReturn(entityEffect);
			EasyMock.expect(entityEffect.getEntities()).andReturn(entities);
			EasyMock.expect(entityEffect.getEntities()).andReturn(entities);
			EasyMock.expect(entityEffect.getEntities()).andReturn(entities);
		}
		
		ValueEffect effect = EasyMock.mock(ValueEffect.class);
		EasyMock.expect(cardToBuild.getEffect()).andReturn(effect);

		EasyMock.expect(effect.getValueType()).andReturn(ValueType.VICTORYPOINT);
		EasyMock.expect(effect.getValueAmount()).andReturn(Integer.valueOf(3));
		EasyMock.expect(player.storagePileContainsCardByName("Baths")).andReturn(false);
		
		EasyMock.expect(effect.getValueType()).andReturn(ValueType.VICTORYPOINT);
		
		player.addNumVictoryPoints(3);
		player.addCardToStoragePile(cardToBuild);
		player.removeFromCurrentHand(cardToBuild);
		EasyMock.expect(player.getCurrentTrades()).andReturn(new HashMap<Enum, Integer>());
		EasyMock.expect(player.storagePileContainsCardByName("Baths")).andReturn(true);
		EasyMock.expect(cardToBuild.getName()).andReturn("Baths");

		EasyMock.replay(player, cardToBuild, cardInStorage, entityEffect, effect);

		PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler();
		playerTurnHandler.setGameBoard(this.gameBoard);
		playerTurnHandler.buildStructure(player, cardToBuild);

		try {
			playerTurnHandler.buildStructure(player, cardToBuild);
			fail();
		} catch (IllegalArgumentException error) {
			assertEquals("Cannot build Structure: Player already has the same Structure", error.getMessage());
		}

		EasyMock.verify(player, cardToBuild, cardInStorage, entityEffect, effect);
	}

	@Test
	public void testBuildPreviousStructureFree() {
		Player player = EasyMock.mock(Player.class);

		ArrayList<Card> storage = new ArrayList<Card>();
		Card storaged = EasyMock.mock(Card.class);
		storage.add(storaged);

		Card cardToBuild = EasyMock.mock(Card.class);
		String previousStructureName = "Trading Post";
		EasyMock.expect(cardToBuild.getPreviousStructureName()).andReturn(previousStructureName);
		EasyMock.expect(player.getAllCards()).andReturn(storage);

		EasyMock.expect(storaged.getName()).andReturn("East Trading Post");
		player.addCardToStoragePile(cardToBuild);
		player.removeFromCurrentHand(cardToBuild);
		EasyMock.expect(player.storagePileContainsCardByName("Trading Post")).andReturn(false);
		EasyMock.expect(cardToBuild.getName()).andReturn("Trading Post");
		EasyMock.replay(player, storaged, cardToBuild);

		PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler();
		playerTurnHandler.setGameBoard(this.gameBoard);
		playerTurnHandler.buildStructure(player, cardToBuild);

		EasyMock.verify(player, storaged, cardToBuild);
	}

	private ArrayList<Player> setUpArrayWithNumPlayers(int num) {
		Wonder wonder = new Wonder(Side.A, WonderType.COLOSSUS);
		ArrayList<Player> result = new ArrayList<Player>();
		for (int i = 0; i < num; i++) {
			Player temp = EasyMock.partialMockBuilder(Player.class).withConstructor("Jane Doe", wonder).createMock();
			result.add(temp);
		}
		return result;
	}

	private Deck createDeck(Age age, int numPlayers) {
		return new SetUpDeckHandler().createDeck(age, numPlayers);
	}

	@Test
	public void testValidBuildStructureResourceInTradesCost() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);
		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);

		Player current = board.getCurrentPlayer();
		current.addTradedValue(RawResource.STONE);
		ArrayList<Card> currentHand = new ArrayList<Card>();
		currentHand.add(deck.getCard(9)); // baths
		current.setCurrentHand(currentHand);

		PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler();
		playerTurnHandler.setGameBoard(board);
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0));

		assertEquals(1, current.getAllCards().size());
		assertEquals(0, current.getCurrentHand().size());
		assertFalse(current.getCurrentHand().contains(deck.getCard(9)));
		assertTrue(current.getAllCards().contains(deck.getCard(9)));
	}
}