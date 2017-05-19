package backendTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import backend.GameManager;
import backend.handlers.PlayerChipHandler;
import backend.handlers.PlayerTurnHandler;
import backend.handlers.RotateHandler.Rotation;
import backend.handlers.SetUpDeckHandler;
import backend.handlers.SetUpPlayerHandler;
import backend.handlers.TradeHandler;
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

	
//	@Test
//	public void testFormatFinalScoreNoTie() {
//		ArrayList<String> playerNames = new ArrayList<String>(Arrays.asList("Player1", "Player2", "Player3"));
//
//		GameManager gameManager = new GameManager(playerNames, GameMode.EASY);
//
//		ArrayList<Integer> scores = new ArrayList<Integer>(Arrays.asList(35, 26, 13));
//
//		Assert.assertEquals(gameManager.formatFinalScores(scores),
//				"Player1 : 35\nPlayer2 : 26\nPlayer3 : 13\nPlayer1 Wins!");
//	}
//
//	@Test
//	public void testFormatFinalScoreTie() {
//		ArrayList<String> playerNames = new ArrayList<String>(Arrays.asList("Player1", "Player2", "Player3"));
//
//		GameManager gameManager = new GameManager(playerNames, GameMode.EASY);
//		PlayerChipHandler.addValue1(gameManager.getPlayer(0), 10, ChipType.COIN);
//		PlayerChipHandler.addValue1(gameManager.getPlayer(1), 5, ChipType.COIN);
//
//		ArrayList<Integer> scores = new ArrayList<Integer>(Arrays.asList(35, 35, 13));
//
//		Assert.assertEquals(gameManager.formatFinalScores(scores),
//				"Player1 : 35\nPlayer2 : 35\nPlayer3 : 13\nPlayer1 Wins!");
//	}

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