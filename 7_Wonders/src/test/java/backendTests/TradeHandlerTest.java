package backendTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import backend.handlers.SetUpDeckHandler;
import backend.handlers.TradeHandler;
import constants.GeneralEnums.Good;
import constants.GeneralEnums.RawResource;
import dataStructures.GameBoard;
import dataStructures.gameMaterials.Card;
import dataStructures.gameMaterials.Deck;
import dataStructures.gameMaterials.Deck.Age;
import dataStructures.gameMaterials.Wonder;
import dataStructures.playerData.Chip;
import dataStructures.playerData.Player;
import exceptions.InsufficientFundsException;
import exceptions.InvalidTradeException;

public class TradeHandlerTest {
	private Player player1, player2;
	private Wonder wonder;
	private Deck testDeck;

	@Before
	public void setUp() {
		this.wonder = EasyMock.createStrictMock(Wonder.class);
		this.player1 = new Player("Jane Doe", this.wonder);
		this.player2 = new Player("Jane Doe", this.wonder);
		this.testDeck = EasyMock.partialMockBuilder(Deck.class).createMock();
	}

	@Test
	public void testSingleTradeValue1Coins() {
		TradeHandler.tradeFromToValue1(this.player1, this.player2, 1);
		assertEquals(4, this.player2.getCoinTotal());
		assertEquals(4, this.player2.getNumValue1Coins());
		assertEquals(2, this.player1.getCoinTotal());
		assertEquals(2, this.player1.getNumValue1Coins());
	}

	@Test
	public void testMultiTradesValue1Coins() {
		TradeHandler.tradeFromToValue1(this.player1, this.player2, 2);
		TradeHandler.tradeFromToValue1(this.player1, this.player2, 1);
		assertEquals(6, this.player2.getCoinTotal());
		assertEquals(6, this.player2.getNumValue1Coins());
		assertEquals(0, this.player1.getCoinTotal());
		assertEquals(0, this.player1.getNumValue1Coins());

	}

	@Test(expected = InsufficientFundsException.class)
	public void testInvalidTradeValue1Coin() {
		TradeHandler.tradeFromToValue1(this.player1, this.player2, 4);
		fail();
	}

	@Test
	public void testSingleTradeValue3Coins() {
		this.player1.addValue3(1, Chip.ChipType.COIN);
		TradeHandler.tradeFromToValue3(this.player1, this.player2, 1);
		assertEquals(6, this.player2.getCoinTotal());
		assertEquals(1, this.player2.getNumValue3Coins());
		assertEquals(3, this.player1.getCoinTotal());
		assertEquals(0, this.player1.getNumValue3Coins());
	}

	@Test
	public void testMultiTradesValue3Coins() {
		this.player1.addValue3(3, Chip.ChipType.COIN);
		TradeHandler.tradeFromToValue3(this.player1, this.player2, 2);
		TradeHandler.tradeFromToValue3(this.player1, this.player2, 1);
		assertEquals(12, this.player2.getCoinTotal());
		assertEquals(3, this.player2.getNumValue3Coins());
		assertEquals(3, this.player1.getCoinTotal());
		assertEquals(0, this.player1.getNumValue3Coins());
	}

	@Test(expected = InsufficientFundsException.class)
	public void testInvalidTradeValue3Coin() {
		TradeHandler.tradeFromToValue3(this.player1, this.player2, 1);
		fail();
	}

	@Test
	public void testMultiTrades() {
		this.player1.addValue3(3, Chip.ChipType.COIN);
		TradeHandler.tradeFromToValue3(this.player1, this.player2, 2);
		TradeHandler.tradeFromToValue3(this.player2, this.player1, 1);
		TradeHandler.tradeFromToValue1(this.player2, this.player1, 3);

		assertEquals(12, this.player1.getCoinTotal());
		assertEquals(6, this.player1.getNumValue1Coins());
		assertEquals(2, this.player1.getNumValue3Coins());
		assertEquals(3, this.player2.getCoinTotal());
		assertEquals(0, this.player2.getNumValue1Coins());
		assertEquals(1, this.player2.getNumValue3Coins());
	}

	@Test
	public void testTradeFromTo() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);
		resetPlayer1And2(players);
		this.player1.addValue3(2, Chip.ChipType.COIN);

		GameBoard board = new GameBoard(players, this.testDeck);
		TradeHandler tradeHandler = new TradeHandler(board);
		tradeHandler.tradeCoinsFromTo(this.player1, this.player2, 4);

		assertEquals(5, this.player1.getCoinTotal());
		assertEquals(2, this.player1.getNumValue1Coins());
		assertEquals(1, this.player1.getNumValue3Coins());
		assertEquals(7, this.player2.getCoinTotal());
		assertEquals(4, this.player2.getNumValue1Coins());
		assertEquals(1, this.player2.getNumValue3Coins());
	}

	@Test
	public void testTradeFromToMultiValue1SingleValue3() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);
		resetPlayer1And2(players);
		this.player1.addValue3(2, Chip.ChipType.COIN);

		GameBoard board = new GameBoard(players, this.testDeck);
		TradeHandler tradeHandler = new TradeHandler(board);
		tradeHandler.tradeCoinsFromTo(this.player1, this.player2, 5);

		assertEquals(4, this.player1.getCoinTotal());
		assertEquals(1, this.player1.getNumValue1Coins());
		assertEquals(1, this.player1.getNumValue3Coins());

		assertEquals(8, this.player2.getCoinTotal());
		assertEquals(5, this.player2.getNumValue1Coins());
		assertEquals(1, this.player2.getNumValue3Coins());
	}

	@Test
	public void testTradeFromToSingleValue1MultiValue3() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);
		resetPlayer1And2(players);
		this.player1.addValue3(2, Chip.ChipType.COIN);

		GameBoard board = new GameBoard(players, this.testDeck);
		TradeHandler tradeHandler = new TradeHandler(board);
		tradeHandler.tradeCoinsFromTo(this.player1, this.player2, 7);

		assertEquals(2, this.player1.getCoinTotal());
		assertEquals(2, this.player1.getNumValue1Coins());
		assertEquals(0, this.player1.getNumValue3Coins());

		assertEquals(10, this.player2.getCoinTotal());
		assertEquals(4, this.player2.getNumValue1Coins());
		assertEquals(2, this.player2.getNumValue3Coins());
	}

	@Test
	public void testTradeFromToMultiValue1MultiValue3() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);
		resetPlayer1And2(players);
		this.player1.addValue3(2, Chip.ChipType.COIN);

		GameBoard board = new GameBoard(players, this.testDeck);
		TradeHandler tradeHandler = new TradeHandler(board);
		tradeHandler.tradeCoinsFromTo(this.player1, this.player2, 8);

		assertEquals(1, this.player1.getCoinTotal());
		assertEquals(1, this.player1.getNumValue1Coins());
		assertEquals(0, this.player1.getNumValue3Coins());

		assertEquals(11, this.player2.getCoinTotal());
		assertEquals(5, this.player2.getNumValue1Coins());
		assertEquals(2, this.player2.getNumValue3Coins());
	}

	@Test
	public void testTradeFromToSufficientValue1NoValue3() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);
		resetPlayer1And2(players);

		GameBoard board = new GameBoard(players, this.testDeck);
		TradeHandler tradeHandler = new TradeHandler(board);
		tradeHandler.tradeCoinsFromTo(this.player1, this.player2, 3);

		assertEquals(0, this.player1.getCoinTotal());
		assertEquals(0, this.player1.getNumValue1Coins());
		assertEquals(0, this.player1.getNumValue3Coins());

		assertEquals(6, this.player2.getCoinTotal());
		assertEquals(6, this.player2.getNumValue1Coins());
		assertEquals(0, this.player2.getNumValue3Coins());
	}

	@Test
	public void testTradeFromToGreaterValue1ThanValue3() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);
		resetPlayer1And2(players);
		this.player1.addValue3(1, Chip.ChipType.COIN);

		GameBoard board = new GameBoard(players, this.testDeck);
		TradeHandler tradeHandler = new TradeHandler(board);
		tradeHandler.tradeCoinsFromTo(this.player1, this.player2, 6);

		assertEquals(0, this.player1.getCoinTotal());
		assertEquals(0, this.player1.getNumValue1Coins());
		assertEquals(0, this.player1.getNumValue3Coins());

		assertEquals(9, this.player2.getCoinTotal());
		assertEquals(6, this.player2.getNumValue1Coins());
		assertEquals(1, this.player2.getNumValue3Coins());
	}

	@Test
	public void testTradeToNextPlayer() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(5);
		resetTestDeck(5);

		GameBoard board = new GameBoard(players, this.testDeck);
		TradeHandler tradeHandler = new TradeHandler(board);
		tradeHandler.tradeCoinsFromTo(board.getCurrentPlayer(), board.getNextPlayer(), 3);

		assertEquals(0, board.getPlayerCoinTotal(board.getCurrentPlayerIndex()));
		assertEquals(6, board.getPlayerCoinTotal(board.getNextPlayerIndex()));
	}

	@Test
	public void testTradeToPreviousPlayer() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(5);
		resetTestDeck(5);

		GameBoard board = new GameBoard(players, this.testDeck);
		TradeHandler tradeHandler = new TradeHandler(board);
		tradeHandler.tradeCoinsFromTo(board.getCurrentPlayer(), board.getPreviousPlayer(), 3);

		assertEquals(0, board.getPlayerCoinTotal(board.getCurrentPlayerIndex()));
		assertEquals(6, board.getPlayerCoinTotal(board.getPreviousPlayerIndex()));
	}

	@Test(expected = InvalidTradeException.class)
	public void testTradeToInvalidPlayer() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(5);
		resetTestDeck(5);

		GameBoard board = new GameBoard(players, this.testDeck);
		TradeHandler tradeHandler = new TradeHandler(board);
		tradeHandler.tradeCoinsFromTo(board.getCurrentPlayer(), board.getPlayer(2), 3);

		fail();
	}

	@Test
	public void testValidTrade2CoinsForSingleLumberResource() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);
		resetTestDeck(3);

		GameBoard board = new GameBoard(players, this.testDeck);
		TradeHandler tradeHandler = new TradeHandler(board);

		Player current = board.getCurrentPlayer();
		Player next = board.getNextPlayer();
		ArrayList<Card> storage = new ArrayList<Card>();
		storage.add(this.testDeck.getCard(0));
		storage.add(this.testDeck.getCard(1));

		next.setStoragePile(storage);
		tradeHandler.tradeFromToForEntity(current, next, RawResource.LUMBER, false);

		assertEquals(0, current.getNumValue3Coins());
		assertEquals(1, (int) current.getCurrentTrades().get(RawResource.LUMBER));
		assertEquals(5, next.getCoinTotal());
	}

	@Test(expected = InvalidTradeException.class)
	public void testInvalidTrade2CoinsForSingleLumberResource() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);
		resetTestDeck(3);

		GameBoard board = new GameBoard(players, this.testDeck);
		TradeHandler tradeHandler = new TradeHandler(board);

		Player current = board.getCurrentPlayer();
		Player next = board.getNextPlayer();
		ArrayList<Card> storage = new ArrayList<Card>();
		storage.add(this.testDeck.getCard(0));
		storage.add(this.testDeck.getCard(1));

		next.setStoragePile(storage);

		tradeHandler.tradeFromToForEntity(current, next, RawResource.ORE, false);
		fail();
	}

	@Test
	public void testValidTrade2CoinsForSingleLoomGood() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);
		resetTestDeck(3);

		GameBoard board = new GameBoard(players, this.testDeck);
		TradeHandler tradeHandler = new TradeHandler(board);

		Player current = board.getCurrentPlayer();
		Player next = board.getNextPlayer();
		ArrayList<Card> storage = new ArrayList<Card>();
		storage.add(this.testDeck.getCard(7));
		storage.add(this.testDeck.getCard(8));

		next.setStoragePile(storage);
		tradeHandler.tradeFromToForEntity(current, next, Good.GLASS, false);

		assertEquals(0, current.getNumValue3Coins());
		assertEquals(1, (int) current.getCurrentTrades().get(Good.GLASS));
		assertEquals(5, next.getCoinTotal());
		assertTrue(next.getStoragePile().contains(this.testDeck.getCard(7)));
		assertTrue(next.getStoragePile().contains(this.testDeck.getCard(8)));
	}

	@Test
	public void testValidTrade1CoinHasEastTradingPost() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);
		resetTestDeck(3);

		GameBoard board = new GameBoard(players, this.testDeck);
		TradeHandler tradeHandler = new TradeHandler(board);

		Player current = board.getPlayer(0);
		Player right = board.getPlayer(1);
		ArrayList<Card> storage = new ArrayList<Card>();
		storage.add(this.testDeck.getCard(12)); // east trading post
		current.setStoragePile(storage);

		ArrayList<Card> rStorage = new ArrayList<Card>();
		rStorage.add(this.testDeck.getCard(0));
		right.setStoragePile(rStorage);

		tradeHandler.tradeFromToForEntity(current, right, RawResource.LUMBER, true);

		assertEquals(2, current.getNumValue1Coins());
		assertEquals(1, (int) current.getCurrentTrades().get(RawResource.LUMBER));
		assertEquals(4, right.getCoinTotal());
	}

	@Test(expected = InvalidTradeException.class)
	public void testInvalidTrade2CoinsForSingleOreResource() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);
		resetTestDeck(3);
		GameBoard board = new GameBoard(players, this.testDeck);
		TradeHandler tradeHandler = new TradeHandler(board);

		Player current = board.getCurrentPlayer();
		Player next = board.getNextPlayer();
		ArrayList<Card> storage = new ArrayList<Card>();
		storage.add(this.testDeck.getCard(0));
		storage.add(this.testDeck.getCard(1));

		next.setStoragePile(storage);

		tradeHandler.tradeFromToForEntity(current, next, RawResource.ORE, false);
		fail();
	}

	@Test
	public void testValidTrade2CoinsForSingleLumberExactResource() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);
		resetTestDeck(3);
		GameBoard board = new GameBoard(players, this.testDeck);
		TradeHandler tradeHandler = new TradeHandler(board);

		Player current = board.getCurrentPlayer();
		Player next = board.getNextPlayer();
		ArrayList<Card> storage = new ArrayList<Card>();
		storage.add(this.testDeck.getCard(0));

		next.setStoragePile(storage);

		tradeHandler.tradeFromToForEntity(current, next, RawResource.LUMBER, false);

		assertEquals(0, current.getNumValue3Coins());
		assertEquals(1, (int) current.getCurrentTrades().get(RawResource.LUMBER));
		assertEquals(5, next.getCoinTotal());
	}
	
	private ArrayList<Player> setUpArrayWithNumPlayers(int num) {
		ArrayList<Player> result = new ArrayList<Player>();
		for (int i = 0; i < num; i++) {
			Player temp = new Player("Jane Doe", this.wonder);
			result.add(temp);
		}
		return result;
	}

	private void resetPlayer1And2(ArrayList<Player> players) {
		this.player1 = players.get(0);
		this.player2 = players.get(1);
	}

	private void resetTestDeck(int numPlayers) {
		this.testDeck = new SetUpDeckHandler().createDeck(Age.AGE1, numPlayers);
	}
}