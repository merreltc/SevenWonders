package backendTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import backend.handlers.PlayerChipHandler;
import backend.handlers.SetUpDeckHandler;
import backend.handlers.TradeHandler;
import constants.GeneralEnums.GameMode;
import constants.GeneralEnums.Good;
import constants.GeneralEnums.RawResource;
import constants.GeneralEnums.Side;
import dataStructures.GameBoard;
import dataStructures.Handlers;
import dataStructures.gameMaterials.Card;
import dataStructures.gameMaterials.Deck;
import dataStructures.gameMaterials.Deck.Age;
import dataStructures.gameMaterials.Wonder;
import dataStructures.gameMaterials.Wonder.WonderType;
import dataStructures.playerData.Chip;
import dataStructures.playerData.Chip.ChipValue;
import dataStructures.playerData.Player;
import exceptions.InsufficientFundsException;
import exceptions.InvalidTradeException;

public class TradeHandlerTest {
	// BEGIN GENERATED CODE
	private Player player1, player2;
	private Wonder wonder;
	private Deck testDeck;

	@Before
	public void setUp() {
		this.wonder = new Wonder(Side.A, WonderType.COLOSSUS);
		this.player1 = new Player("Jane Doe", this.wonder);
		this.player2 = new Player("John Doe", this.wonder);
		this.testDeck = EasyMock.partialMockBuilder(Deck.class).createMock();
	}

	@Test
	public void testSingleTradeValue1Coins() {
		TradeHandler.tradeFromToValue1(player1, player2, 1);
		assertEquals(4, player2.getCoinTotal());
		assertEquals(4, (int) player2.getCoins().get(ChipValue.ONE));
		assertEquals(2, player1.getCoinTotal());
		assertEquals(2, (int) player1.getCoins().get(ChipValue.ONE));

	}

	@Test
	public void testMultiTradesValue1Coins() {
		TradeHandler.tradeFromToValue1(player1, player2, 2);
		TradeHandler.tradeFromToValue1(player1, player2, 1);

		assertEquals(6, player2.getCoinTotal());
		assertEquals(6, (int) player2.getCoins().get(ChipValue.ONE));
		assertEquals(0, player1.getCoinTotal());
		assertEquals(0, (int) player1.getCoins().get(ChipValue.ONE));

	}

	@Test(expected = InsufficientFundsException.class)
	public void testInvalidTradeValue1Coin() {
		TradeHandler.tradeFromToValue1(player1, player2, 4);
		fail();
	}

	@Test
	public void testSingleTradeValue3Coins() {
		PlayerChipHandler.addValue3(player1, 1, Chip.ChipType.COIN);
		TradeHandler.tradeFromToValue3(player1, player2, 1);

		assertEquals(6, player2.getCoinTotal());
		assertEquals(1, (int) player2.getCoins().get(ChipValue.THREE));
		assertEquals(3, player1.getCoinTotal());
		assertEquals(0, (int) player1.getCoins().get(ChipValue.THREE));
	}

	@Test
	public void testMultiTradesValue3Coins() {
		PlayerChipHandler.addValue3(player1, 3, Chip.ChipType.COIN);
		TradeHandler.tradeFromToValue3(player1, player2, 2);
		TradeHandler.tradeFromToValue3(player1, player2, 1);

		assertEquals(12, player2.getCoinTotal());
		assertEquals(3, (int) player2.getCoins().get(ChipValue.THREE));
		assertEquals(3, player1.getCoinTotal());
		assertEquals(0, (int) player1.getCoins().get(ChipValue.THREE));
	}

	@Test(expected = InsufficientFundsException.class)
	public void testInvalidTradeValue3Coin() {
		TradeHandler.tradeFromToValue3(player1, player2, 1);
		fail();
	}

	@Test
	public void testMultiTrades() {
		PlayerChipHandler.addValue3(player1, 3, Chip.ChipType.COIN);

		TradeHandler.tradeFromToValue3(player1, player2, 2);
		TradeHandler.tradeFromToValue3(player2, player1, 1);
		TradeHandler.tradeFromToValue1(player2, player1, 3);

		assertEquals(12, player1.getCoinTotal());
		assertEquals(6, (int) player1.getCoins().get(ChipValue.ONE));
		assertEquals(2, (int) player1.getCoins().get(ChipValue.THREE));

		assertEquals(3, player2.getCoinTotal());
		assertEquals(0, (int) player2.getCoins().get(ChipValue.ONE));
		assertEquals(1, (int) player2.getCoins().get(ChipValue.THREE));
	}

	@Test
	public void testTradeFromTo() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);
		resetPlayer1And2(players);
		PlayerChipHandler.addValue3(player1, 2, Chip.ChipType.COIN);

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		TradeHandler tradeHandler = new TradeHandler(board);

		tradeHandler.tradeCoinsFromTo(player1, player2, 4);

		assertEquals(5, player1.getCoinTotal());
		assertEquals(2, (int) player1.getCoins().get(ChipValue.ONE));
		assertEquals(1, (int) player1.getCoins().get(ChipValue.THREE));

		assertEquals(7, player2.getCoinTotal());
		assertEquals(4, (int) player2.getCoins().get(ChipValue.ONE));
		assertEquals(1, (int) player2.getCoins().get(ChipValue.THREE));
	}

	@Test
	public void testTradeFromToMultiValue1SingleValue3() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);
		resetPlayer1And2(players);
		PlayerChipHandler.addValue3(player1, 2, Chip.ChipType.COIN);

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		TradeHandler tradeHandler = new TradeHandler(board);

		tradeHandler.tradeCoinsFromTo(player1, player2, 5);

		assertEquals(4, player1.getCoinTotal());
		assertEquals(1, (int) player1.getCoins().get(ChipValue.ONE));
		assertEquals(1, (int) player1.getCoins().get(ChipValue.THREE));

		assertEquals(8, player2.getCoinTotal());
		assertEquals(5, (int) player2.getCoins().get(ChipValue.ONE));
		assertEquals(1, (int) player2.getCoins().get(ChipValue.THREE));
	}

	@Test
	public void testTradeFromToSingleValue1MultiValue3() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);
		resetPlayer1And2(players);
		PlayerChipHandler.addValue3(player1, 2, Chip.ChipType.COIN);

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		TradeHandler tradeHandler = new TradeHandler(board);

		tradeHandler.tradeCoinsFromTo(player1, player2, 7);

		assertEquals(2, player1.getCoinTotal());
		assertEquals(2, (int) player1.getCoins().get(ChipValue.ONE));
		assertEquals(0, (int) player1.getCoins().get(ChipValue.THREE));

		assertEquals(10, player2.getCoinTotal());
		assertEquals(4, (int) player2.getCoins().get(ChipValue.ONE));
		assertEquals(2, (int) player2.getCoins().get(ChipValue.THREE));
	}

	@Test
	public void testTradeFromToMultiValue1MultiValue3() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);
		resetPlayer1And2(players);
		PlayerChipHandler.addValue3(player1, 2, Chip.ChipType.COIN);

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		TradeHandler tradeHandler = new TradeHandler(board);

		tradeHandler.tradeCoinsFromTo(player1, player2, 8);

		assertEquals(1, player1.getCoinTotal());
		assertEquals(1, (int) player1.getCoins().get(ChipValue.ONE));
		assertEquals(0, (int) player1.getCoins().get(ChipValue.THREE));

		assertEquals(11, player2.getCoinTotal());
		assertEquals(5, (int) player2.getCoins().get(ChipValue.ONE));
		assertEquals(2, (int) player2.getCoins().get(ChipValue.THREE));
	}

	@Test
	public void testTradeFromToSufficientValue1NoValue3() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);
		resetPlayer1And2(players);
		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		TradeHandler tradeHandler = new TradeHandler(board);

		tradeHandler.tradeCoinsFromTo(player1, player2, 3);

		assertEquals(0, player1.getCoinTotal());
		assertEquals(0, (int) player1.getCoins().get(ChipValue.ONE));
		assertEquals(0, (int) player1.getCoins().get(ChipValue.THREE));

		assertEquals(6, player2.getCoinTotal());
		assertEquals(6, (int) player2.getCoins().get(ChipValue.ONE));
		assertEquals(0, (int) player2.getCoins().get(ChipValue.THREE));
	}

	@Test
	public void testTradeFromToGreaterValue1ThanValue3() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);
		resetPlayer1And2(players);
		PlayerChipHandler.addValue3(player1, 1, Chip.ChipType.COIN);

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		TradeHandler tradeHandler = new TradeHandler(board);

		tradeHandler.tradeCoinsFromTo(player1, player2, 6);

		assertEquals(0, player1.getCoinTotal());
		assertEquals(0, (int) player1.getCoins().get(ChipValue.ONE));
		assertEquals(0, (int) player1.getCoins().get(ChipValue.THREE));

		assertEquals(9, player2.getCoinTotal());
		assertEquals(6, (int) player2.getCoins().get(ChipValue.ONE));
		assertEquals(1, (int) player2.getCoins().get(ChipValue.THREE));
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
		resetPlayer1And2(players);
		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		TradeHandler tradeHandler = new TradeHandler(board);

		Player current = board.getCurrentPlayer();
		Player next = board.getNextPlayer();
		ArrayList<Card> storage = new ArrayList<Card>();
		storage.add(deck.getCard(0));
		storage.add(deck.getCard(1));

		next.setStoragePile(storage);

		tradeHandler.tradeFromToForEntity(current, next, RawResource.LUMBER, false);

		assertEquals(0, (int) current.getCoins().get(ChipValue.THREE));
		assertEquals(1, (int) current.getCurrentTrades().get(RawResource.LUMBER));
		assertEquals(5, next.getCoinTotal());
	}

	@Test(expected = InvalidTradeException.class)
	public void testInvalidTrade2CoinsForSingleOreResource() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);
		resetPlayer1And2(players);
		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		TradeHandler tradeHandler = new TradeHandler(board);

		Player current = board.getCurrentPlayer();
		Player next = board.getNextPlayer();
		ArrayList<Card> storage = new ArrayList<Card>();
		storage.add(deck.getCard(0));
		storage.add(deck.getCard(1));

		next.setStoragePile(storage);

		tradeHandler.tradeFromToForEntity(current, next, RawResource.ORE, false);
		fail();
	}

	@Test
	public void testValidTrade1CoinHasEastTradingPost() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);
		resetPlayer1And2(players);
		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		TradeHandler tradeHandler = new TradeHandler(board);

		Player current = board.getPlayer(0);
		Player right = board.getPlayer(1);
		ArrayList<Card> storage = new ArrayList<Card>();
		storage.add(deck.getCard(12)); // east trading post
		current.setStoragePile(storage);

		ArrayList<Card> rStorage = new ArrayList<Card>();
		rStorage.add(deck.getCard(0));
		right.setStoragePile(rStorage);

		tradeHandler.tradeFromToForEntity(current, right, RawResource.LUMBER, true);

		assertEquals(2, (int) current.getCoins().get(ChipValue.ONE));
		assertEquals(1, (int) current.getCurrentTrades().get(RawResource.LUMBER));
		assertEquals(4, right.getCoinTotal());
	}

	@Test
	public void testValidTrade2CoinsForSingleLumberExactResource() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);
		resetPlayer1And2(players);
		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		TradeHandler tradeHandler = new TradeHandler(board);

		Player current = board.getCurrentPlayer();
		Player next = board.getNextPlayer();
		ArrayList<Card> storage = new ArrayList<Card>();
		storage.add(deck.getCard(0));

		next.setStoragePile(storage);

		tradeHandler.tradeFromToForEntity(current, next, RawResource.LUMBER, false);

		assertEquals(0, (int) current.getCoins().get(ChipValue.THREE));
		assertEquals(1, (int) current.getCurrentTrades().get(RawResource.LUMBER));
		assertEquals(5, next.getCoinTotal());
	}

	@Test
	public void testTradeFromToForGood() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(this.player1);
		players.add(this.player2);
		players.add(new Player("James Doe", this.wonder));

		ArrayList<Card> storagePile = new ArrayList<Card>();
		SetUpDeckHandler setUp = new SetUpDeckHandler();
		Deck deck = setUp.createDeck(Age.AGE1, 3);
		GameBoard board = new GameBoard(players, deck);
		Handlers handlers = new Handlers(GameMode.EASY);
		handlers.setSetUpDeckHandler(setUp);

		TradeHandler tradeHandler = new TradeHandler(board);
		ArrayList<String> names = new ArrayList<String>(Arrays.asList("Jane Doe", "James Doe", "James doe"));
		handlers.setTradeHandler(tradeHandler);

		ArrayList<Card> storage = new ArrayList<Card>();
		storage.add(deck.getCard(7));
		storage.add(deck.getCard(8));

		board.getNextPlayer().setStoragePile(storage);
		tradeHandler.tradeForEntity(board.getCurrentPlayer(), board.getNextPlayer(), Good.GLASS);

		Player current = board.getCurrentPlayer();
		Player next = board.getNextPlayer();

		assertEquals(1, (int) current.getCurrentTrades().get(Good.GLASS));
		assertEquals(5, next.getCoinTotal());
	}

	@Test
	public void testTradeFromToForResource() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(this.player1);
		players.add(this.player2);
		players.add(new Player("James Doe", this.wonder));

		ArrayList<Card> storage = new ArrayList<Card>();
		Deck deck = new SetUpDeckHandler().createDeck(Age.AGE1, 3);
		GameBoard board = new GameBoard(players, deck);
		storage.add(deck.getCard(0));
		storage.add(deck.getCard(1));

		player2.setStoragePile(storage);

		TradeHandler tradeHandler = new TradeHandler(board);

		tradeHandler.tradeForEntity(this.player1, this.player2, RawResource.LUMBER);

		assertEquals(1, (int) this.player1.getCurrentTrades().get(RawResource.LUMBER));
		assertEquals(5, this.player2.getCoinTotal());
	}

	@Test
	public void testValidTradeForDiscountEastTradingPost() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(this.player1);
		players.add(this.player2);
		players.add(new Player("James Doe", this.wonder));

		ArrayList<Card> storage = new ArrayList<Card>();
		Deck deck = new SetUpDeckHandler().createDeck(Age.AGE1, 3);
		GameBoard board = new GameBoard(players, deck);
		Player current = this.player1;
		Player right = this.player2;

		storage.add(deck.getCard(12)); // east trading post
		current.setStoragePile(storage);

		ArrayList<Card> storage2 = new ArrayList<Card>();
		storage2.add(deck.getCard(0)); // lumber
		right.setStoragePile(storage2);

		TradeHandler tradeHandler = new TradeHandler(board);
		tradeHandler.tradeForEntity(current, right, RawResource.LUMBER);
		assertTrue(current.getCurrentTrades().containsKey(RawResource.LUMBER));
		assertEquals(4, right.getCoinTotal());
	}

	@Test
	public void testValidTradeForDiscountEastTradingPostSwitchIndex() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(this.player1);
		players.add(this.player2);
		players.add(new Player("James Doe", this.wonder));

		ArrayList<Card> storage = new ArrayList<Card>();
		Deck deck = new SetUpDeckHandler().createDeck(Age.AGE1, 3);
		GameBoard board = new GameBoard(players, deck);
		Player current = players.get(2);
		Player right = this.player1;
		board.setNextPlayer(0);
		board.setCurrentPlayer(2);

		storage.add(deck.getCard(12)); // east trading post
		current.setStoragePile(storage);

		ArrayList<Card> storage2 = new ArrayList<Card>();
		storage2.add(deck.getCard(0)); // lumber
		right.setStoragePile(storage2);

		TradeHandler tradeHandler = new TradeHandler(board);
		tradeHandler.tradeForEntity(current, right, RawResource.LUMBER);
		assertTrue(current.getCurrentTrades().containsKey(RawResource.LUMBER));
		assertEquals(4, right.getCoinTotal());
	}

	@Test
	public void testValidTradeForDiscountWestTradingPost() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(this.player1);
		players.add(this.player2);
		players.add(new Player("James Doe", this.wonder));

		ArrayList<Card> storage = new ArrayList<Card>();
		Deck deck = new SetUpDeckHandler().createDeck(Age.AGE1, 3);
		GameBoard board = new GameBoard(players, deck);

		Player current = this.player1;
		Player left = players.get(2);
		storage.add(deck.getCard(13)); // west trading post
		current.setStoragePile(storage);

		ArrayList<Card> storage2 = new ArrayList<Card>();
		storage2.add(deck.getCard(0)); // lumber
		left.setStoragePile(storage2);

		TradeHandler tradeHandler = new TradeHandler(board);
		tradeHandler.tradeForEntity(current, left, RawResource.LUMBER);
		assertTrue(current.getCurrentTrades().containsKey(RawResource.LUMBER));
		assertEquals(4, left.getCoinTotal());
	}

	@Test
	public void testValidTradeForDiscountMarketPlace() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(this.player1);
		players.add(this.player2);
		players.add(new Player("James Doe", this.wonder));

		ArrayList<Card> storage = new ArrayList<Card>();
		Deck deck = new SetUpDeckHandler().createDeck(Age.AGE1, 3);
		GameBoard board = new GameBoard(players, deck);
		Player current = this.player1;
		Player right = this.player2;

		storage.add(deck.getCard(14)); // marketplace
		current.setStoragePile(storage);

		ArrayList<Card> storage2 = new ArrayList<Card>();
		storage2.add(deck.getCard(8)); // press
		right.setStoragePile(storage2);

		TradeHandler tradeHandler = new TradeHandler(board);
		tradeHandler.tradeForEntity(current, right, Good.PRESS);
		assertTrue(current.getCurrentTrades().containsKey(Good.PRESS));
		assertEquals(4, right.getCoinTotal());
	}

	@Test
	public void testValidTradeForDiscountMarketPlaceLeft() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(this.player1);
		players.add(this.player2);
		players.add(new Player("James Doe", this.wonder));

		ArrayList<Card> storage = new ArrayList<Card>();
		Deck deck = new SetUpDeckHandler().createDeck(Age.AGE1, 3);
		GameBoard board = new GameBoard(players, deck);
		Player current = this.player1;
		Player left = players.get(2);

		storage.add(deck.getCard(14)); // marketplace
		current.setStoragePile(storage);

		ArrayList<Card> storage2 = new ArrayList<Card>();
		storage2.add(deck.getCard(8)); // press
		left.setStoragePile(storage2);

		TradeHandler tradeHandler = new TradeHandler(board);
		tradeHandler.tradeForEntity(current, left, Good.PRESS);
		assertTrue(current.getCurrentTrades().containsKey(Good.PRESS));
		assertEquals(4, left.getCoinTotal());
	}

	@Test
	public void testNoDiscountEastTradingPostRegularTrade() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(this.player1);
		players.add(this.player2);
		players.add(new Player("James Doe", this.wonder));

		ArrayList<Card> storage = new ArrayList<Card>();
		Deck deck = new SetUpDeckHandler().createDeck(Age.AGE1, 3);
		GameBoard board = new GameBoard(players, deck);
		Player current = this.player1;
		Player left = players.get(2);

		storage.add(deck.getCard(12)); // east trading post
		current.setStoragePile(storage);

		ArrayList<Card> storage2 = new ArrayList<Card>();
		storage2.add(deck.getCard(0)); // lumber
		left.setStoragePile(storage2);

		TradeHandler tradeHandler = new TradeHandler(board);
		tradeHandler.tradeForEntity(current, left, RawResource.LUMBER);
		assertTrue(current.getCurrentTrades().containsKey(RawResource.LUMBER));
		assertEquals(5, left.getCoinTotal());
	}

	@Test
	public void testNoDiscountWestTradingPostRegularTrade() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(this.player1);
		players.add(this.player2);
		players.add(new Player("James Doe", this.wonder));

		ArrayList<Card> storage = new ArrayList<Card>();
		Deck deck = new SetUpDeckHandler().createDeck(Age.AGE1, 3);
		GameBoard board = new GameBoard(players, deck);
		Player current = this.player1;
		Player right = this.player2;

		storage.add(deck.getCard(13)); // west trading post
		current.setStoragePile(storage);
		ArrayList<Card> storage2 = new ArrayList<Card>();
		storage2.add(deck.getCard(0)); // lumber
		right.setStoragePile(storage2);

		TradeHandler tradeHandler = new TradeHandler(board);
		tradeHandler.tradeForEntity(current, right, RawResource.LUMBER);

		assertTrue(current.getCurrentTrades().containsKey(RawResource.LUMBER));
		assertEquals(5, right.getCoinTotal());
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

	// END GENERATED CODE
}