package backendTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import backend.GameManager;
import backend.handlers.PlayerChipHandler;
import backend.handlers.PlayerTurnHandler;
import backend.handlers.RotateHandler.Rotation;
import backend.handlers.SetUpDeckHandler;
import backend.handlers.SetUpPlayerHandler;
import backend.handlers.TurnHandler;
import constants.GeneralEnums.GameMode;
import constants.GeneralEnums.Good;
import constants.GeneralEnums.RawResource;
import dataStructures.GameBoard;
import dataStructures.Handlers;
import dataStructures.gameMaterials.Card;
import dataStructures.gameMaterials.Deck;
import dataStructures.gameMaterials.Deck.Age;
import dataStructures.playerData.Chip;
import dataStructures.playerData.Player;
import dataStructures.playerData.Chip.ChipType;
import dataStructures.playerData.Chip.ChipValue;

public class GameManagerTest {
	private SetUpPlayerHandler setUpPlayerHandler;
	private TurnHandler turnHandler;
	private PlayerTurnHandler playerTurnHandler;
	private SetUpDeckHandler setUpDeckHandler;

	@Before
	public void setUp() {
		this.setUpPlayerHandler = EasyMock.partialMockBuilder(SetUpPlayerHandler.class).withConstructor(GameMode.EASY)
				.createMock();
		this.turnHandler = EasyMock.partialMockBuilder(TurnHandler.class).withConstructor().createMock();
		this.playerTurnHandler = EasyMock.partialMockBuilder(PlayerTurnHandler.class).withConstructor().createMock();
		this.setUpDeckHandler = EasyMock.partialMockBuilder(SetUpDeckHandler.class).withConstructor().createMock();
	}

	@Test
	public void testSetUpGameBoardMinPlayers() {
		ArrayList<String> playerNames = setUpArrayByNum(3);
		GameManager manager = new GameManager(playerNames, GameMode.EASY);
		assertEquals(3, manager.getNumPlayers());
	}

	@Test
	public void testSetUpGameBoardMaxPlayers() {
		ArrayList<String> playerNames = setUpArrayByNum(7);
		GameManager manager = new GameManager(playerNames, GameMode.EASY);
		assertEquals(7, manager.getNumPlayers());
	}

	@Test
	public void testSetUpGameBoardCreateDeck3Players() {
		SetUpDeckHandler setUpDeckHandler = EasyMock.createStrictMock(SetUpDeckHandler.class);
		Deck deck = null;
		EasyMock.expect(setUpDeckHandler.createDeck(Age.AGE1, 3)).andReturn(deck);
		PlayerTurnHandler playerTurnHandler = EasyMock.partialMockBuilder(PlayerTurnHandler.class)
				.addMockedMethod("setGameBoard").createMock();
		
		playerTurnHandler.setGameBoard(EasyMock.isA(GameBoard.class));
		EasyMock.replay(setUpDeckHandler, playerTurnHandler);
		ArrayList<String> playerNames = setUpArrayByNum(3);

		Handlers handlers = new Handlers(this.setUpPlayerHandler);
		handlers.setSetUpDeckHandler(setUpDeckHandler);
		handlers.setTurnHandler(this.turnHandler);
		handlers.setPlayerTurnHandler(playerTurnHandler);

		new GameManager(playerNames, handlers);

		EasyMock.verify(setUpDeckHandler, playerTurnHandler);
	}

	@Test
	public void testSetUpGameBoardCreateDeck7Players() {
		SetUpDeckHandler setUpDeckHandler = EasyMock.createStrictMock(SetUpDeckHandler.class);
		Deck deck = null;
		EasyMock.expect(setUpDeckHandler.createDeck(Age.AGE1, 7)).andReturn(deck);
		EasyMock.replay(setUpDeckHandler);

		ArrayList<String> playerNames = setUpArrayByNum(7);

		Handlers handlers = new Handlers(this.setUpPlayerHandler);
		handlers.setSetUpDeckHandler(setUpDeckHandler);
		handlers.setTurnHandler(this.turnHandler);
		handlers.setPlayerTurnHandler(this.playerTurnHandler);

		new GameManager(playerNames, handlers);

		EasyMock.verify(setUpDeckHandler);
	}

	@Test
	public void getDeck() {
		ArrayList<String> playerNames = setUpArrayByNum(3);
		GameManager manager = new GameManager(playerNames, GameMode.EASY);
		boolean isDeck = (manager.getDeck() instanceof Deck);
		assertTrue(isDeck);
	}

	@Test
	public void testGetDefaultDirection() {
		ArrayList<String> playerNames = setUpArrayByNum(3);
		GameManager manager = new GameManager(playerNames, GameMode.EASY);
		assertEquals(Rotation.CLOCKWISE, manager.getDirection());
	}

	@Test
	public void testTrade() {
		ArrayList<String> playerNames = setUpArrayByNum(3);
		GameManager manager = new GameManager(playerNames, GameMode.EASY);
		manager.trade(manager.getPlayer(0), manager.getPlayer(1), 3);

		assertEquals(0, manager.getPlayerCoinTotal(0));
		assertEquals(6, manager.getPlayerCoinTotal(1));
	}

	@Test
	public void testGetCurrentPositionsOnStartMin() {
		ArrayList<String> playerNames = setUpArrayByNum(3);
		GameManager manager = new GameManager(playerNames, GameMode.EASY);
		comparePlayerPositions(manager.getPlayers(), manager, 0, 1, 2);
	}

	@Test
	public void testGetPlayerPositionsOnStartMax() {
		ArrayList<String> playerNames = setUpArrayByNum(7);
		GameManager manager = new GameManager(playerNames, GameMode.EASY);
		comparePlayerPositions(manager.getPlayers(), manager, 0, 1, 6);
	}

	@Test
	public void testGetPlayerPositionsOnRotateClockwiseMin() {
		ArrayList<String> playerNames = setUpArrayByNum(3);
		GameManager manager = new GameManager(playerNames, GameMode.EASY);
		manager.rotateClockwise();
		comparePlayerPositions(manager.getPlayers(), manager, 1, 2, 0);
	}

	@Test
	public void testGetPlayerPositionsOnRotateClockwiseMax() {
		ArrayList<String> playerNames = setUpArrayByNum(7);
		GameManager manager = new GameManager(playerNames, GameMode.EASY);

		manager.rotateClockwise();
		comparePlayerPositions(manager.getPlayers(), manager, 1, 2, 0);
	}

	@Test
	public void testGetPlayerPositionsOnRotateClockwiseTwice() {
		ArrayList<String> playerNames = setUpArrayByNum(5);
		GameManager manager = new GameManager(playerNames, GameMode.EASY);
		manager.rotateClockwise();
		manager.rotateClockwise();

		comparePlayerPositions(manager.getPlayers(), manager, 2, 3, 1);
	}

	@Test
	public void testGetPlayerPositionsOnRotateClockwiseMany() {
		ArrayList<String> playerNames = setUpArrayByNum(5);
		GameManager manager = new GameManager(playerNames, GameMode.EASY);

		for (int i = 0; i < 10; i++) {
			manager.rotateClockwise();
		}

		comparePlayerPositions(manager.getPlayers(), manager, 0, 1, 4);
	}

	@Test
	public void testChangeRotateDirectionAndResetPositionsMax() {
		ArrayList<String> playerNames = setUpArrayByNum(7);
		GameManager manager = new GameManager(playerNames, GameMode.EASY);

		manager.changeRotateDirectionAndResetPositions(Rotation.CLOCKWISE);
		comparePlayerPositions(manager.getPlayers(), manager, 0, 1, 6);

		assertEquals(Rotation.CLOCKWISE, manager.getDirection());

		manager.changeRotateDirectionAndResetPositions(Rotation.COUNTERCLOCKWISE);
		comparePlayerPositions(manager.getPlayers(), manager, 0, 6, 1);

		assertEquals(Rotation.COUNTERCLOCKWISE, manager.getDirection());
	}

	@Test
	public void testChangeRotateDirectionAndResetPositionsMin() {
		ArrayList<String> playerNames = setUpArrayByNum(3);
		GameManager manager = new GameManager(playerNames, GameMode.EASY);

		manager.changeRotateDirectionAndResetPositions(Rotation.CLOCKWISE);
		comparePlayerPositions(manager.getPlayers(), manager, 0, 1, 2);

		manager.changeRotateDirectionAndResetPositions(Rotation.COUNTERCLOCKWISE);
		comparePlayerPositions(manager.getPlayers(), manager, 0, 2, 1);
	}

	@Test
	public void testGetPlayerPositionsOnRotateCounterClockwiseMin() {
		ArrayList<String> playerNames = setUpArrayByNum(3);
		GameManager manager = new GameManager(playerNames, GameMode.EASY);
		manager.changeRotateDirectionAndResetPositions(Rotation.COUNTERCLOCKWISE);
		manager.rotateCounterClockwise();

		comparePlayerPositions(manager.getPlayers(), manager, 2, 1, 0);
	}

	@Test
	public void testGetPlayerPositionsOnRotateCounterClockwiseMax() {
		ArrayList<String> playerNames = setUpArrayByNum(7);
		GameManager manager = new GameManager(playerNames, GameMode.EASY);

		manager.changeRotateDirectionAndResetPositions(Rotation.COUNTERCLOCKWISE);
		manager.rotateCounterClockwise();
		comparePlayerPositions(manager.getPlayers(), manager, 6, 5, 0);
	}

	@Test
	public void testGetPlayerPositionsOnRotateCounterClockwiseTwice() {
		ArrayList<String> playerNames = setUpArrayByNum(5);
		GameManager manager = new GameManager(playerNames, GameMode.EASY);
		manager.changeRotateDirectionAndResetPositions(Rotation.COUNTERCLOCKWISE);
		manager.rotateCounterClockwise();
		manager.rotateCounterClockwise();

		comparePlayerPositions(manager.getPlayers(), manager, 3, 2, 4);
	}

	@Test
	public void testGetPlayerPositionsOnRotateCounterClockwiseMany() {
		ArrayList<String> playerNames = setUpArrayByNum(5);
		GameManager manager = new GameManager(playerNames, GameMode.EASY);
		manager.changeRotateDirectionAndResetPositions(Rotation.COUNTERCLOCKWISE);

		for (int i = 0; i < 10; i++) {
			manager.rotateCounterClockwise();
		}

		comparePlayerPositions(manager.getPlayers(), manager, 0, 4, 1);
	}

	public void comparePlayerPositions(ArrayList<Player> players, GameManager manager, int currIndex, int nextIndex,
			int previousIndex) {
		assertEquals(players.get(currIndex), manager.getCurrentPlayer());
		assertEquals(players.get(nextIndex), manager.getNextPlayer());
		assertEquals(players.get(previousIndex), manager.getPreviousPlayer());
	}

	@Test
	public void testDealInitialCards() {
		TurnHandler turnHandler = EasyMock.createMock(TurnHandler.class);

		ArrayList<String> playerNames = setUpArrayByNum(5);
		Handlers handlers = new Handlers(this.setUpPlayerHandler);
		handlers.setSetUpDeckHandler(this.setUpDeckHandler);
		handlers.setTurnHandler(turnHandler);
		handlers.setPlayerTurnHandler(this.playerTurnHandler);

		GameManager manager = new GameManager(playerNames, handlers);

		turnHandler.dealInitialTurnCards(manager.getPlayers(), manager.getGameBoard().getDeck());

		EasyMock.replay(turnHandler);
		ArrayList<Card> cards = new ArrayList<Card>(manager.getGameBoard().getDeck().getCards());
		manager.dealInitialTurnCards();

		assertFalse(cards.toString().equals(manager.getDeck().getCards().toString()));
		EasyMock.verify(turnHandler);
	}

	@Test
	public void testBuildStructure() {
		PlayerTurnHandler playerTurnHandler = EasyMock.partialMockBuilder(PlayerTurnHandler.class)
				.addMockedMethod("buildStructure").createMock();
		ArrayList<String> playerNames = setUpArrayByNum(5);

		Handlers handlers = new Handlers(this.setUpPlayerHandler);
		handlers.setSetUpDeckHandler(this.setUpDeckHandler);
		handlers.setTurnHandler(this.turnHandler);
		handlers.setPlayerTurnHandler(playerTurnHandler);

		GameManager manager = new GameManager(playerNames, handlers);

		manager.dealInitialTurnCards();
		Card card = manager.getCurrentPlayer().getCurrentHand().get(0);
		playerTurnHandler.buildStructure(manager.getCurrentPlayer(), card);

		EasyMock.replay(playerTurnHandler);

		manager.buildStructure(card);

		EasyMock.verify(playerTurnHandler);
	}

	@Test
	public void testBuildStructureNonMockedForMutationCoverage() {
		ArrayList<String> playerNames = setUpArrayByNum(5);
		GameManager manager = new GameManager(playerNames, GameMode.EASY);

		Card card = manager.getDeck().getCard(0);
		manager.buildStructure(card);

		assertTrue(manager.getCurrentPlayer().getStoragePile().contains(card));
	}

	@Test
	public void testTradeFromToForResource() {
		ArrayList<String> playerNames = setUpArrayByNum(3);
		Handlers handlers = new Handlers(this.setUpPlayerHandler);
		handlers.setSetUpDeckHandler(this.setUpDeckHandler);
		handlers.setTurnHandler(this.turnHandler);
		handlers.setPlayerTurnHandler(this.playerTurnHandler);

		GameManager manager = new GameManager(playerNames, handlers);

		ArrayList<Card> storage = new ArrayList<Card>();
		Deck deck = manager.getGameBoard().getDeck();
		storage.add(deck.getCard(0));
		storage.add(deck.getCard(1));

		manager.getNextPlayer().setStoragePile(storage);
		manager.tradeForEntity(manager.getCurrentPlayer(), manager.getNextPlayer(), RawResource.LUMBER);

		Player current = manager.getCurrentPlayer();
		Player next = manager.getNextPlayer();

		assertEquals(1, (int) current.getCurrentTrades().get(RawResource.LUMBER));
		assertEquals(5, next.getCoinTotal());
	}

	@Test
	public void testTradeFromToForGood() {
		ArrayList<String> playerNames = setUpArrayByNum(3);
		Handlers handlers = new Handlers(this.setUpPlayerHandler);
		handlers.setSetUpDeckHandler(this.setUpDeckHandler);
		handlers.setTurnHandler(this.turnHandler);
		handlers.setPlayerTurnHandler(this.playerTurnHandler);

		GameManager manager = new GameManager(playerNames, handlers);

		ArrayList<Card> storage = new ArrayList<Card>();
		Deck deck = manager.getGameBoard().getDeck();
		storage.add(deck.getCard(7));
		storage.add(deck.getCard(8));

		manager.getNextPlayer().setStoragePile(storage);
		manager.tradeForEntity(manager.getCurrentPlayer(), manager.getNextPlayer(), Good.GLASS);

		Player current = manager.getCurrentPlayer();
		Player next = manager.getNextPlayer();

		assertEquals(1, (int) current.getCurrentTrades().get(Good.GLASS));
		assertEquals(5, next.getCoinTotal());
	}

	@Test
	public void testValidTradeForDiscountEastTradingPost() {
		ArrayList<String> playerNames = setUpArrayByNum(3);
		Handlers handlers = new Handlers(this.setUpPlayerHandler);
		handlers.setSetUpDeckHandler(this.setUpDeckHandler);
		handlers.setTurnHandler(this.turnHandler);
		handlers.setPlayerTurnHandler(this.playerTurnHandler);

		GameManager manager = new GameManager(playerNames, handlers);

		Deck deck = manager.getGameBoard().getDeck();
		Player current = manager.getCurrentPlayer();
		Player right = manager.getNextPlayer();

		ArrayList<Card> storage = new ArrayList<Card>();
		storage.add(deck.getCard(12)); // east trading post
		current.setStoragePile(storage);

		ArrayList<Card> storage2 = new ArrayList<Card>();
		storage2.add(deck.getCard(0)); // lumber
		right.setStoragePile(storage2);

		manager.tradeForEntity(current, right, RawResource.LUMBER);
		assertTrue(current.getCurrentTrades().containsKey(RawResource.LUMBER));
		assertEquals(4, right.getCoinTotal());
	}

	@Test
	public void testValidTradeForDiscountEastTradingPostCounter() {
		ArrayList<String> playerNames = setUpArrayByNum(3);
		Handlers handlers = new Handlers(this.setUpPlayerHandler);
		handlers.setSetUpDeckHandler(this.setUpDeckHandler);
		handlers.setTurnHandler(this.turnHandler);
		handlers.setPlayerTurnHandler(this.playerTurnHandler);

		GameManager manager = new GameManager(playerNames, handlers);

		manager.changeRotateDirectionAndResetPositions(Rotation.COUNTERCLOCKWISE);

		Deck deck = manager.getGameBoard().getDeck();
		Player current = manager.getCurrentPlayer();
		Player right = manager.getPreviousPlayer();

		ArrayList<Card> storage = new ArrayList<Card>();
		storage.add(deck.getCard(12)); // east trading post
		current.setStoragePile(storage);

		ArrayList<Card> storage2 = new ArrayList<Card>();
		storage2.add(deck.getCard(0)); // lumber
		right.setStoragePile(storage2);

		manager.tradeForEntity(current, right, RawResource.LUMBER);
		assertTrue(current.getCurrentTrades().containsKey(RawResource.LUMBER));
		assertEquals(4, right.getCoinTotal());
	}

	@Test
	public void testValidTradeForDiscountWestTradingPostCounter() {
		ArrayList<String> playerNames = setUpArrayByNum(3);
		Handlers handlers = new Handlers(this.setUpPlayerHandler);
		handlers.setSetUpDeckHandler(this.setUpDeckHandler);
		handlers.setTurnHandler(this.turnHandler);
		handlers.setPlayerTurnHandler(this.playerTurnHandler);

		GameManager manager = new GameManager(playerNames, handlers);
		manager.changeRotateDirectionAndResetPositions(Rotation.COUNTERCLOCKWISE);
		Deck deck = manager.getGameBoard().getDeck();
		Player current = manager.getCurrentPlayer();
		Player left = manager.getNextPlayer();
		ArrayList<Card> storage = new ArrayList<Card>();
		storage.add(deck.getCard(13)); // west trading post
		current.setStoragePile(storage);
		ArrayList<Card> storage2 = new ArrayList<Card>();
		storage2.add(deck.getCard(0)); // lumber
		left.setStoragePile(storage2);
		manager.tradeForEntity(current, left, RawResource.LUMBER);
		assertTrue(current.getCurrentTrades().containsKey(RawResource.LUMBER));
		assertEquals(4, left.getCoinTotal());
	}

	@Test
	public void testValidTradeForDiscountWestTradingPost() {
		ArrayList<String> playerNames = setUpArrayByNum(3);
		Handlers handlers = new Handlers(this.setUpPlayerHandler);
		handlers.setSetUpDeckHandler(this.setUpDeckHandler);
		handlers.setTurnHandler(this.turnHandler);
		handlers.setPlayerTurnHandler(this.playerTurnHandler);

		GameManager manager = new GameManager(playerNames, handlers);

		Deck deck = manager.getGameBoard().getDeck();
		Player current = manager.getCurrentPlayer();
		Player left = manager.getPreviousPlayer();
		ArrayList<Card> storage = new ArrayList<Card>();
		storage.add(deck.getCard(13)); // west trading post
		current.setStoragePile(storage);

		ArrayList<Card> storage2 = new ArrayList<Card>();
		storage2.add(deck.getCard(0)); // lumber
		left.setStoragePile(storage2);

		manager.tradeForEntity(current, left, RawResource.LUMBER);
		assertTrue(current.getCurrentTrades().containsKey(RawResource.LUMBER));
		assertEquals(4, left.getCoinTotal());
	}

	@Test
	public void testValidTradeForDiscountMarketPlace() {
		ArrayList<String> playerNames = setUpArrayByNum(3);

		Handlers handlers = new Handlers(this.setUpPlayerHandler);
		handlers.setSetUpDeckHandler(this.setUpDeckHandler);
		handlers.setTurnHandler(this.turnHandler);
		handlers.setPlayerTurnHandler(this.playerTurnHandler);

		GameManager manager = new GameManager(playerNames, handlers);

		Deck deck = manager.getGameBoard().getDeck();
		Player current = manager.getCurrentPlayer();
		Player right = manager.getNextPlayer();
		ArrayList<Card> storage = new ArrayList<Card>();
		storage.add(deck.getCard(14)); // marketplace
		current.setStoragePile(storage);
		ArrayList<Card> storage2 = new ArrayList<Card>();
		storage2.add(deck.getCard(0)); // lumber
		right.setStoragePile(storage2);
		manager.tradeForEntity(current, right, RawResource.LUMBER);
		assertTrue(current.getCurrentTrades().containsKey(RawResource.LUMBER));
		assertEquals(4, right.getCoinTotal());
	}

	@Test
	public void testValidTradeForDiscountMarketPlaceLeft() {
		ArrayList<String> playerNames = setUpArrayByNum(3);

		GameManager manager = new GameManager(playerNames, setUpHandlers());

		Deck deck = manager.getGameBoard().getDeck();
		Player current = manager.getCurrentPlayer();
		Player left = manager.getPreviousPlayer();
		ArrayList<Card> storage = new ArrayList<Card>();
		storage.add(deck.getCard(14)); // marketplace
		current.setStoragePile(storage);
		ArrayList<Card> storage2 = new ArrayList<Card>();
		storage2.add(deck.getCard(0)); // lumber
		left.setStoragePile(storage2);
		manager.tradeForEntity(current, left, RawResource.LUMBER);
		assertTrue(current.getCurrentTrades().containsKey(RawResource.LUMBER));
		assertEquals(4, left.getCoinTotal());
	}

	@Test
	public void testValidTradeForDiscountEastTradingPostRotate() {
		ArrayList<String> playerNames = setUpArrayByNum(3);
		Handlers handlers = new Handlers(this.setUpPlayerHandler);
		handlers.setSetUpDeckHandler(this.setUpDeckHandler);
		handlers.setTurnHandler(this.turnHandler);
		handlers.setPlayerTurnHandler(this.playerTurnHandler);

		GameManager manager = new GameManager(playerNames, handlers);

		manager.rotateClockwise();
		manager.rotateClockwise();
		Deck deck = manager.getGameBoard().getDeck();
		Player current = manager.getCurrentPlayer();
		Player right = manager.getNextPlayer();
		ArrayList<Card> storage = new ArrayList<Card>();
		storage.add(deck.getCard(12)); // east trading post
		current.setStoragePile(storage);
		ArrayList<Card> storage2 = new ArrayList<Card>();
		storage2.add(deck.getCard(0)); // lumber
		right.setStoragePile(storage2);
		manager.tradeForEntity(current, right, RawResource.LUMBER);
		assertTrue(current.getCurrentTrades().containsKey(RawResource.LUMBER));
		assertEquals(4, right.getCoinTotal());
	}

	@Test
	public void testValidTradeForDiscountWestTradingPostRotate() {
		ArrayList<String> playerNames = setUpArrayByNum(3);
		Handlers handlers = new Handlers(this.setUpPlayerHandler);
		handlers.setSetUpDeckHandler(this.setUpDeckHandler);
		handlers.setTurnHandler(this.turnHandler);
		handlers.setPlayerTurnHandler(this.playerTurnHandler);

		GameManager manager = new GameManager(playerNames, handlers);

		manager.rotateClockwise();
		Deck deck = manager.getGameBoard().getDeck();
		Player current = manager.getCurrentPlayer();
		Player left = manager.getPreviousPlayer();

		ArrayList<Card> storage = new ArrayList<Card>();
		storage.add(deck.getCard(13)); // west trading post
		current.setStoragePile(storage);

		ArrayList<Card> storage2 = new ArrayList<Card>();
		storage2.add(deck.getCard(0)); // lumber
		left.setStoragePile(storage2);

		manager.tradeForEntity(current, left, RawResource.LUMBER);
		assertTrue(current.getCurrentTrades().containsKey(RawResource.LUMBER));
		assertEquals(4, left.getCoinTotal());
	}

	@Test
	public void testNoDiscountEastTradingPostRegularTrade() {
		ArrayList<String> playerNames = setUpArrayByNum(3);

		GameManager manager = new GameManager(playerNames, setUpHandlers());

		Deck deck = manager.getDeck();
		Player current = manager.getCurrentPlayer();
		Player left = manager.getPreviousPlayer();

		ArrayList<Card> storage = new ArrayList<Card>();
		storage.add(deck.getCard(12)); // east trading post
		current.setStoragePile(storage);

		ArrayList<Card> storage2 = new ArrayList<Card>();
		storage2.add(deck.getCard(0)); // lumber
		left.setStoragePile(storage2);

		manager.tradeForEntity(current, left, RawResource.LUMBER);
		assertTrue(current.getCurrentTrades().containsKey(RawResource.LUMBER));
		assertEquals(5, left.getCoinTotal());
	}

	@Test
	public void testNoDiscountWestTradingPostRegularTrade() {
		ArrayList<String> playerNames = setUpArrayByNum(3);

		GameManager manager = new GameManager(playerNames, setUpHandlers());

		Deck deck = manager.getGameBoard().getDeck();
		Player current = manager.getCurrentPlayer();
		Player right = manager.getNextPlayer();
		ArrayList<Card> storage = new ArrayList<Card>();
		storage.add(deck.getCard(13)); // west trading post
		current.setStoragePile(storage);
		ArrayList<Card> storage2 = new ArrayList<Card>();
		storage2.add(deck.getCard(0)); // lumber
		right.setStoragePile(storage2);
		manager.tradeForEntity(current, right, RawResource.LUMBER);

		assertTrue(current.getCurrentTrades().containsKey(RawResource.LUMBER));
		assertEquals(5, right.getCoinTotal());
	}

	@Test
	public void testTradeFromToForGoodEmptyTradesAfterTurnEnds() {
		ArrayList<String> playerNames = setUpArrayByNum(3);

		GameManager manager = new GameManager(playerNames, setUpHandlers());

		ArrayList<Card> storage = new ArrayList<Card>();
		Deck deck = manager.getGameBoard().getDeck();
		storage.add(deck.getCard(7));
		storage.add(deck.getCard(8));

		manager.getNextPlayer().setStoragePile(storage);
		manager.tradeForEntity(manager.getCurrentPlayer(), manager.getNextPlayer(), Good.GLASS);
		manager.endCurrentPlayerTurn();
		Player previous = manager.getPreviousPlayer();

		assertTrue(previous.getCurrentTrades().isEmpty());
	}

	@Test
	public void testMakeChangeForValue1Coins() {
		ArrayList<String> playerNames = setUpArrayByNum(3);

		GameManager manager = new GameManager(playerNames, setUpHandlers());
		PlayerChipHandler.addValue3(manager.getCurrentPlayer(), 1, ChipType.COIN);

		assertTrue(manager.makeChangeForValue1Coins(3));
		assertEquals(6, (int) manager.getCurrentPlayer().getCoins().get(ChipValue.ONE));
		assertEquals(0, (int) manager.getCurrentPlayer().getCoins().get(ChipValue.THREE));
		assertEquals(34, manager.getGameBoard().getTotalValue1CoinsInBank());
		assertEquals(25, manager.getGameBoard().getTotalValue3CoinsInBank());
	}

	@Test
	public void testMakeChangeForValue3Coins() {
		ArrayList<String> playerNames = setUpArrayByNum(3);

		GameManager manager = new GameManager(playerNames, setUpHandlers());
		GameBoard board = manager.getGameBoard();

		assertTrue(board.makeChangeForValue3Coins(manager.getCurrentPlayer(), 1));
		assertEquals(0, (int) manager.getCurrentPlayer().getCoins().get(ChipValue.ONE));
		assertEquals(1, (int) manager.getCurrentPlayer().getCoins().get(ChipValue.THREE));
		assertEquals(40, board.getTotalValue1CoinsInBank());
		assertEquals(23, board.getTotalValue3CoinsInBank());
	}

	@Test
	public void testDiscardSelectedCard() {
		ArrayList<String> playerNames = setUpArrayByNum(3);
		PlayerTurnHandler playerTurnHandler = EasyMock.partialMockBuilder(PlayerTurnHandler.class)
				.addMockedMethod("discardSelectedCard").createMock();

		Handlers handlers = new Handlers(this.setUpPlayerHandler);
		handlers.setSetUpDeckHandler(this.setUpDeckHandler);
		handlers.setTurnHandler(this.turnHandler);
		handlers.setPlayerTurnHandler(playerTurnHandler);

		GameManager manager = new GameManager(playerNames, handlers);

		playerTurnHandler.discardSelectedCard(manager.getCurrentPlayer(), manager.getDeck().getCard(0));

		EasyMock.replay(playerTurnHandler);
		this.turnHandler.dealInitialTurnCards(manager.getPlayers(), manager.getDeck());
		manager.discardSelectedCard(manager.getCurrentPlayer().getCurrentHand().get(0));

		EasyMock.verify(playerTurnHandler);
	}

	@Test
	public void testEndCurrentPlayerTurn() {
		ArrayList<String> playerNames = setUpArrayByNum(3);
		TurnHandler turnHandler = EasyMock.mock(TurnHandler.class);
		Handlers handlers = new Handlers(this.setUpPlayerHandler);
		handlers.setSetUpDeckHandler(this.setUpDeckHandler);
		handlers.setTurnHandler(turnHandler);
		handlers.setPlayerTurnHandler(this.playerTurnHandler);

		GameManager manager = new GameManager(playerNames, handlers);

		EasyMock.expect(turnHandler.getNumPlayersUntilPass()).andReturn(2);
		turnHandler.setNumPlayersUntilPass(1);

		EasyMock.replay(turnHandler);
		Player expectedNewCurrentPlayer = manager.getNextPlayer();
		Player expectedNewPreviousPlayer = manager.getCurrentPlayer();

		assertEquals("", manager.endCurrentPlayerTurn());
		assertEquals(expectedNewCurrentPlayer, manager.getCurrentPlayer());
		assertEquals(expectedNewPreviousPlayer, manager.getPreviousPlayer());

		EasyMock.verify(turnHandler);
	}

	@Test
	public void testEndCurrentPlayerTurnNonMockedForMutationCoverage() {
		ArrayList<String> playerNames = setUpArrayByNum(3);
		GameManager manager = new GameManager(playerNames, GameMode.EASY);

		Player expectedNewCurrentPlayer = manager.getNextPlayer();
		Player expectedNewPreviousPlayer = manager.getCurrentPlayer();

		manager.dealInitialTurnCards();
		assertEquals("", manager.endCurrentPlayerTurn());
		assertEquals(expectedNewCurrentPlayer, manager.getCurrentPlayer());
		assertEquals(expectedNewPreviousPlayer, manager.getPreviousPlayer());
	}

	@Test
	public void testEndCurrentPlayersTurn3TimesAndTradeHands() {
		ArrayList<String> playerNames = setUpArrayByNum(3);
		TurnHandler turnHandler = EasyMock.partialMockBuilder(TurnHandler.class)
				.addMockedMethod("getNumPlayersUntilPass").createMock();
		Handlers handlers = new Handlers(this.setUpPlayerHandler);
		handlers.setSetUpDeckHandler(this.setUpDeckHandler);
		handlers.setTurnHandler(turnHandler);
		handlers.setPlayerTurnHandler(this.playerTurnHandler);

		GameManager manager = new GameManager(playerNames, handlers);
		manager.dealInitialTurnCards();

		EasyMock.expect(turnHandler.getNumPlayersUntilPass()).andReturn(2);
		EasyMock.expect(turnHandler.getNumPlayersUntilPass()).andReturn(1);
		EasyMock.expect(turnHandler.getNumPlayersUntilPass()).andReturn(0);
		turnHandler.setNumPlayersUntilPass(2);

		EasyMock.replay(turnHandler);
		Player expectedNewCurrentPlayer = manager.getCurrentPlayer();
		ArrayList<Card> expectedNewCurrentHand = manager.getPreviousPlayer().getCurrentHand();

		assertEquals("", manager.endCurrentPlayerTurn());
		assertEquals("", manager.endCurrentPlayerTurn());
		assertEquals("End of current rotation.  Switching Player hands.", manager.endCurrentPlayerTurn());

		assertEquals(expectedNewCurrentPlayer, manager.getCurrentPlayer());
		assertEquals(expectedNewCurrentHand, manager.getCurrentPlayer().getCurrentHand());

		EasyMock.verify(turnHandler);
	}

	@Test
	public void testEndCurrentPlayersTurn3TimesAndTradeHands5Players() {
		TurnHandler turnHandler = EasyMock.partialMockBuilder(TurnHandler.class)
				.addMockedMethod("getNumPlayersUntilPass").createMock();
		ArrayList<String> playerNames = setUpArrayByNum(5);
		Handlers handlers = new Handlers(this.setUpPlayerHandler);
		handlers.setSetUpDeckHandler(this.setUpDeckHandler);
		handlers.setTurnHandler(turnHandler);
		handlers.setPlayerTurnHandler(this.playerTurnHandler);

		GameManager manager = new GameManager(playerNames, handlers);
		manager.dealInitialTurnCards();
		EasyMock.expect(turnHandler.getNumPlayersUntilPass()).andReturn(4);
		EasyMock.expect(turnHandler.getNumPlayersUntilPass()).andReturn(3);
		EasyMock.expect(turnHandler.getNumPlayersUntilPass()).andReturn(2);
		EasyMock.expect(turnHandler.getNumPlayersUntilPass()).andReturn(1);
		EasyMock.expect(turnHandler.getNumPlayersUntilPass()).andReturn(0);
		turnHandler.setNumPlayersUntilPass(4);

		EasyMock.replay(turnHandler);
		Player expectedNewCurrentPlayer = manager.getCurrentPlayer();
		ArrayList<Card> expectedNewCurrentHand = manager.getPreviousPlayer().getCurrentHand();

		assertEquals("", manager.endCurrentPlayerTurn());
		assertEquals("", manager.endCurrentPlayerTurn());
		assertEquals("", manager.endCurrentPlayerTurn());
		assertEquals("", manager.endCurrentPlayerTurn());
		assertEquals("End of current rotation.  Switching Player hands.", manager.endCurrentPlayerTurn());

		assertEquals(expectedNewCurrentPlayer, manager.getCurrentPlayer());
		assertEquals(expectedNewCurrentHand, manager.getCurrentPlayer().getCurrentHand());

		EasyMock.verify(turnHandler);
	}

	@Test
	public void testEndPlayerTurnEndsCurrentAge() {
		ArrayList<String> playerNames = setUpArrayByNum(3);
		TurnHandler turnHandler = EasyMock.partialMockBuilder(TurnHandler.class).addMockedMethod("endAge")
				.addMockedMethod("getNumPlayersUntilPass").addMockedMethod("setNumPlayersUntilPass").createMock();

		ArrayList<Card> cards2 = this.setUpDeckHandler.createCards(Age.AGE2, 3);
		Deck deck2 = new Deck(Age.AGE2, cards2);

		Handlers handlers = new Handlers(this.setUpPlayerHandler);
		handlers.setSetUpDeckHandler(this.setUpDeckHandler);
		handlers.setTurnHandler(turnHandler);
		handlers.setPlayerTurnHandler(this.playerTurnHandler);
		GameManager manager = EasyMock.partialMockBuilder(GameManager.class).withConstructor(playerNames, handlers)
				.addMockedMethod("rotateCounterClockwise").createMock();
		manager.dealInitialTurnCards();

		ArrayList<Card> previousCurrentCards = manager.getCurrentPlayer().getCurrentHand();

		assertEquals(7, manager.getCurrentPlayer().getCurrentHand().size());

		for (int calls = 5; calls >= 0; calls--) {
			mockExpectTurnHandlerCalls(turnHandler);
		}

		turnHandler.dealInitialTurnCards(manager.getPlayers(), deck2);
		turnHandler.endAge(manager.getPlayers(), Age.AGE1);
		manager.rotateCounterClockwise();

		EasyMock.replay(turnHandler, manager);

		for (int numCalls = 0; numCalls < 17; numCalls++) {
			manager.endCurrentPlayerTurn();
		}

		assertEquals("This is the end of the Age.  Finalizing Points.", manager.endCurrentPlayerTurn());
		assertEquals(7, manager.getCurrentPlayer().getCurrentHand().size());
		assertEquals(Rotation.COUNTERCLOCKWISE, manager.getDirection());
		assertFalse(manager.getCurrentPlayer().getCurrentHand().equals(previousCurrentCards));
		assertEquals(Age.AGE2, manager.getGameBoard().getDeck().getAge());

		EasyMock.verify(turnHandler, manager);
	}

	@Test
	public void testEndPlayerTurnEndsCurrentAgeTo3() {
		ArrayList<String> playerNames = setUpArrayByNum(3);
		TurnHandler turnHandler = EasyMock.partialMockBuilder(TurnHandler.class)
				.addMockedMethod("getNumPlayersUntilPass").addMockedMethod("setNumPlayersUntilPass").createMock();

		ArrayList<Card> cards2 = this.setUpDeckHandler.createCards(Age.AGE2, 3);
		Deck deck2 = new Deck(Age.AGE2, cards2);
		ArrayList<Card> cards3 = this.setUpDeckHandler.createCards(Age.AGE3, 3);
		Deck deck3 = new Deck(Age.AGE3, cards3);

		Handlers handlers = new Handlers(this.setUpPlayerHandler);
		handlers.setSetUpDeckHandler(this.setUpDeckHandler);
		handlers.setTurnHandler(turnHandler);
		handlers.setPlayerTurnHandler(playerTurnHandler);

		GameManager manager = new GameManager(playerNames, handlers);
		manager.getGameBoard().setDeck(deck2);
		manager.dealInitialTurnCards();
		manager.changeRotateDirectionAndResetPositions(Rotation.COUNTERCLOCKWISE);

		ArrayList<Card> previousCurrentCards = manager.getCurrentPlayer().getCurrentHand();

		assertEquals(7, manager.getCurrentPlayer().getCurrentHand().size());

		for (int calls = 4; calls >= 0; calls--) {
			mockExpectTurnHandlerCalls(turnHandler);
		}

		mockExpectTurnHandlerCalls(turnHandler);

		turnHandler.dealInitialTurnCards(manager.getPlayers(), deck3);

		EasyMock.replay(turnHandler);

		for (int numCalls = 0; numCalls < 17; numCalls++) {
			manager.endCurrentPlayerTurn();
		}

		assertEquals("This is the end of the Age.  Finalizing Points.", manager.endCurrentPlayerTurn());
		assertEquals(7, manager.getCurrentPlayer().getCurrentHand().size());
		assertEquals(Rotation.CLOCKWISE, manager.getDirection());
		assertFalse(manager.getCurrentPlayer().getCurrentHand().equals(previousCurrentCards));
		assertEquals(Age.AGE3, manager.getGameBoard().getDeck().getAge());
		assertTrue(manager.getGameBoard().getDeck().getCards().isEmpty());

		EasyMock.verify(turnHandler);
	}

	@Test
	public void testEndAgeShufflesDeck() {
		ArrayList<String> playerNames = setUpArrayByNum(3);
		TurnHandler turnHandler = EasyMock.mock(TurnHandler.class);

		Handlers handlers = new Handlers(this.setUpPlayerHandler);
		handlers.setSetUpDeckHandler(this.setUpDeckHandler);
		handlers.setTurnHandler(turnHandler);
		handlers.setPlayerTurnHandler(this.playerTurnHandler);
		GameManager manager = EasyMock.partialMockBuilder(GameManager.class).withConstructor(playerNames, handlers)
				.addMockedMethod("switchDeck").createMock();
		ArrayList<Card> cards2 = this.setUpDeckHandler.createCards(Age.AGE2, 3);
		Deck deck2 = new Deck(Age.AGE2, cards2);
		ArrayList<Card> cardsBefore = new ArrayList<Card>(deck2.getCards());

		EasyMock.expect(manager.switchDeck(Age.AGE2)).andReturn(deck2);
		turnHandler.dealInitialTurnCards(manager.getPlayers(), deck2);
		EasyMock.replay(manager, turnHandler);

		manager.endAge(Age.AGE2);

		assertFalse(cardsBefore.toString().equals(manager.getDeck().getCards().toString()));

		EasyMock.verify(manager, turnHandler);

	}

	private void mockExpectTurnHandlerCalls(TurnHandler turnHandler) {
		EasyMock.expect(turnHandler.getNumPlayersUntilPass()).andReturn(2);
		turnHandler.setNumPlayersUntilPass(1);
		EasyMock.expect(turnHandler.getNumPlayersUntilPass()).andReturn(1);
		turnHandler.setNumPlayersUntilPass(0);
		EasyMock.expect(turnHandler.getNumPlayersUntilPass()).andReturn(0);
		turnHandler.setNumPlayersUntilPass(2);
	}

	private ArrayList<String> setUpArrayByNum(int num) {
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < num; i++) {
			result.add("Jane Doe " + i);
		}

		return result;
	}

	private Handlers setUpHandlers() {
		Handlers handlers = new Handlers(this.setUpPlayerHandler);
		handlers.setSetUpDeckHandler(this.setUpDeckHandler);
		handlers.setTurnHandler(this.turnHandler);
		handlers.setPlayerTurnHandler(this.playerTurnHandler);
		return handlers;
	}
}