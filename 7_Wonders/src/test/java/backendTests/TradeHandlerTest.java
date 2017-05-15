package backendTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;

import backend.SetUpDeckHandler;
import backend.TradeHandler;
import dataStructures.Card;
import dataStructures.Chip;
import dataStructures.Deck;
import dataStructures.Deck.Age;
import dataStructures.GameBoard;
import dataStructures.GeneralEnums.*;
import dataStructures.Player;
import dataStructures.Wonder.WonderType;
import exceptions.InsufficientFundsException;
import exceptions.InvalidTradeException;

public class TradeHandlerTest {
	@Test
	public void testSingleTradeValue1Coins() {
		Player player1 = new Player("Jane Doe", WonderType.COLOSSUS);
		Player player2 = new Player("Jane Doe", WonderType.COLOSSUS);

		TradeHandler.tradeFromToValue1(player1, player2, 1);

		assertEquals(4, player2.getCoinTotal());
		assertEquals(4, player2.getNumValue1Coins());
		assertEquals(2, player1.getCoinTotal());
		assertEquals(2, player1.getNumValue1Coins());

	}

	@Test
	public void testMultiTradesValue1Coins() {
		Player player1 = new Player("Jane Doe", WonderType.COLOSSUS);
		Player player2 = new Player("Jane Doe", WonderType.COLOSSUS);

		TradeHandler.tradeFromToValue1(player1, player2, 2);
		TradeHandler.tradeFromToValue1(player1, player2, 1);

		assertEquals(6, player2.getCoinTotal());
		assertEquals(6, player2.getNumValue1Coins());
		assertEquals(0, player1.getCoinTotal());
		assertEquals(0, player1.getNumValue1Coins());

	}

	@Test(expected = InsufficientFundsException.class)
	public void testInvalidTradeValue1Coin() {
		Player player1 = new Player("Jane Doe", WonderType.COLOSSUS);
		Player player2 = new Player("Jane Doe", WonderType.COLOSSUS);

		TradeHandler.tradeFromToValue1(player1, player2, 4);
		fail();
	}

	@Test
	public void testSingleTradeValue3Coins() {
		Player player1 = new Player("Jane Doe", WonderType.COLOSSUS);
		Player player2 = new Player("Jane Doe", WonderType.COLOSSUS);

		player1.addValue3(1, Chip.ChipType.COIN);
		TradeHandler.tradeFromToValue3(player1, player2, 1);

		assertEquals(6, player2.getCoinTotal());
		assertEquals(1, player2.getNumValue3Coins());
		assertEquals(3, player1.getCoinTotal());
		assertEquals(0, player1.getNumValue3Coins());
	}

	@Test
	public void testMultiTradesValue3Coins() {
		Player player1 = new Player("Jane Doe", WonderType.COLOSSUS);
		Player player2 = new Player("Jane Doe", WonderType.COLOSSUS);

		player1.addValue3(3, Chip.ChipType.COIN);
		TradeHandler.tradeFromToValue3(player1, player2, 2);
		TradeHandler.tradeFromToValue3(player1, player2, 1);

		assertEquals(12, player2.getCoinTotal());
		assertEquals(3, player2.getNumValue3Coins());
		assertEquals(3, player1.getCoinTotal());
		assertEquals(0, player1.getNumValue3Coins());
	}

	@Test(expected = InsufficientFundsException.class)
	public void testInvalidTradeValue3Coin() {
		Player player1 = new Player("Jane Doe", WonderType.COLOSSUS);
		Player player2 = new Player("Jane Doe", WonderType.COLOSSUS);

		TradeHandler.tradeFromToValue3(player1, player2, 1);
		fail();
	}

	@Test
	public void testMultiTrades() {
		Player player1 = new Player("Jane Doe", WonderType.COLOSSUS);
		Player player2 = new Player("Jane Doe", WonderType.COLOSSUS);

		player1.addValue3(3, Chip.ChipType.COIN);

		TradeHandler.tradeFromToValue3(player1, player2, 2);
		TradeHandler.tradeFromToValue3(player2, player1, 1);
		TradeHandler.tradeFromToValue1(player2, player1, 3);

		assertEquals(12, player1.getCoinTotal());
		assertEquals(6, player1.getNumValue1Coins());
		assertEquals(2, player1.getNumValue3Coins());

		assertEquals(3, player2.getCoinTotal());
		assertEquals(0, player2.getNumValue1Coins());
		assertEquals(1, player2.getNumValue3Coins());
	}

	@Test
	public void testTradeFromTo() {
		Player player1 = new Player("Jane Doe", WonderType.COLOSSUS);
		Player player2 = new Player("Jane Doe", WonderType.COLOSSUS);

		player1.addValue3(2, Chip.ChipType.COIN);

		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		players.add(new Player("Jane Doe", WonderType.COLOSSUS));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		TradeHandler tradeHandler = new TradeHandler(board);

		tradeHandler.tradeCoinsFromTo(player1, player2, 4);

		assertEquals(5, player1.getCoinTotal());
		assertEquals(2, player1.getNumValue1Coins());
		assertEquals(1, player1.getNumValue3Coins());

		assertEquals(7, player2.getCoinTotal());
		assertEquals(4, player2.getNumValue1Coins());
		assertEquals(1, player2.getNumValue3Coins());
	}

	@Test
	public void testTradeFromToMultiValue1SingleValue3() {
		Player player1 = new Player("Jane Doe", WonderType.COLOSSUS);
		Player player2 = new Player("Jane Doe", WonderType.COLOSSUS);

		player1.addValue3(2, Chip.ChipType.COIN);

		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		players.add(new Player("Jane Doe", WonderType.COLOSSUS));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		TradeHandler tradeHandler = new TradeHandler(board);

		tradeHandler.tradeCoinsFromTo(player1, player2, 5);

		assertEquals(4, player1.getCoinTotal());
		assertEquals(1, player1.getNumValue1Coins());
		assertEquals(1, player1.getNumValue3Coins());

		assertEquals(8, player2.getCoinTotal());
		assertEquals(5, player2.getNumValue1Coins());
		assertEquals(1, player2.getNumValue3Coins());
	}

	@Test
	public void testTradeFromToSingleValue1MultiValue3() {
		Player player1 = new Player("Jane Doe", WonderType.COLOSSUS);
		Player player2 = new Player("Jane Doe", WonderType.COLOSSUS);

		player1.addValue3(2, Chip.ChipType.COIN);

		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		players.add(new Player("Jane Doe", WonderType.COLOSSUS));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		TradeHandler tradeHandler = new TradeHandler(board);

		tradeHandler.tradeCoinsFromTo(player1, player2, 7);

		assertEquals(2, player1.getCoinTotal());
		assertEquals(2, player1.getNumValue1Coins());
		assertEquals(0, player1.getNumValue3Coins());

		assertEquals(10, player2.getCoinTotal());
		assertEquals(4, player2.getNumValue1Coins());
		assertEquals(2, player2.getNumValue3Coins());
	}

	@Test
	public void testTradeFromToMultiValue1MultiValue3() {
		Player player1 = new Player("Jane Doe", WonderType.COLOSSUS);
		Player player2 = new Player("Jane Doe", WonderType.COLOSSUS);

		player1.addValue3(2, Chip.ChipType.COIN);

		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		players.add(new Player("Jane Doe", WonderType.COLOSSUS));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		TradeHandler tradeHandler = new TradeHandler(board);
		
		tradeHandler.tradeCoinsFromTo(player1, player2, 8);

		assertEquals(1, player1.getCoinTotal());
		assertEquals(1, player1.getNumValue1Coins());
		assertEquals(0, player1.getNumValue3Coins());

		assertEquals(11, player2.getCoinTotal());
		assertEquals(5, player2.getNumValue1Coins());
		assertEquals(2, player2.getNumValue3Coins());
	}

	@Test
	public void testTradeFromToSufficientValue1NoValue3() {
		Player player1 = new Player("Jane Doe", WonderType.COLOSSUS);
		Player player2 = new Player("Jane Doe", WonderType.COLOSSUS);

		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		players.add(new Player("Jane Doe", WonderType.COLOSSUS));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		TradeHandler tradeHandler = new TradeHandler(board);

		tradeHandler.tradeCoinsFromTo(player1, player2, 3);

		assertEquals(0, player1.getCoinTotal());
		assertEquals(0, player1.getNumValue1Coins());
		assertEquals(0, player1.getNumValue3Coins());

		assertEquals(6, player2.getCoinTotal());
		assertEquals(6, player2.getNumValue1Coins());
		assertEquals(0, player2.getNumValue3Coins());
	}

	@Test
	public void testTradeFromToGreaterValue1ThanValue3() {
		Player player1 = new Player("Jane Doe", WonderType.COLOSSUS);
		Player player2 = new Player("Jane Doe", WonderType.COLOSSUS);

		player1.addValue3(1, Chip.ChipType.COIN);

		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		players.add(new Player("Jane Doe", WonderType.COLOSSUS));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		TradeHandler tradeHandler = new TradeHandler(board);

		tradeHandler.tradeCoinsFromTo(player1, player2, 6);

		assertEquals(0, player1.getCoinTotal());
		assertEquals(0, player1.getNumValue1Coins());
		assertEquals(0, player1.getNumValue3Coins());

		assertEquals(9, player2.getCoinTotal());
		assertEquals(6, player2.getNumValue1Coins());
		assertEquals(1, player2.getNumValue3Coins());
	}

	@Test
	public void testTradeToNextPlayer() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.TEMPLE));
		players.add(new Player("Hulk", WonderType.PYRAMIDS));
		players.add(new Player("Iron Man", WonderType.STATUE));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		TradeHandler tradeHandler = new TradeHandler(board);

		tradeHandler.tradeCoinsFromTo(board.getCurrentPlayer(), board.getNextPlayer(), 3);

		assertEquals(0, board.getPlayerCoinTotal(board.getCurrentPlayerIndex()));
		assertEquals(6, board.getPlayerCoinTotal(board.getNextPlayerIndex()));
	}

	@Test
	public void testTradeToPreviousPlayer() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.TEMPLE));
		players.add(new Player("Hulk", WonderType.PYRAMIDS));
		players.add(new Player("Iron Man", WonderType.STATUE));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		TradeHandler tradeHandler = new TradeHandler(board);
	
		tradeHandler.tradeCoinsFromTo(board.getCurrentPlayer(), board.getPreviousPlayer(), 3);

		assertEquals(0, board.getPlayerCoinTotal(board.getCurrentPlayerIndex()));
		assertEquals(6, board.getPlayerCoinTotal(board.getPreviousPlayerIndex()));
	}

	@Test(expected = InvalidTradeException.class)
	public void testTradeToInvalidPlayer() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.TEMPLE));
		players.add(new Player("Hulk", WonderType.PYRAMIDS));
		players.add(new Player("Iron Man", WonderType.STATUE));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		TradeHandler tradeHandler = new TradeHandler(board);

		tradeHandler.tradeCoinsFromTo(board.getCurrentPlayer(), board.getPlayer(2), 3);
		fail();
	}
	
	@Test
	public void testValidTrade2CoinsForSingleLumberResource(){
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.TEMPLE));

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
		
		assertEquals(0, current.getNumValue3Coins());
		assertEquals(1, (int) current.getCurrentTrades().get(RawResource.LUMBER));
		assertEquals(5, next.getCoinTotal());
	}
	
	@Test(expected = InvalidTradeException.class)
	public void testInvalidTrade2CoinsForSingleLumberResource(){
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.TEMPLE));

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
	public void testValidTrade2CoinsForSingleLoomGood(){
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.TEMPLE));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);
		
		GameBoard board = new GameBoard(players, deck);
		TradeHandler tradeHandler = new TradeHandler(board);
		
		Player current = board.getCurrentPlayer();
		Player next = board.getNextPlayer();
		ArrayList<Card> storage = new ArrayList<Card>();
		storage.add(deck.getCard(7));
		storage.add(deck.getCard(8));
		
		next.setStoragePile(storage);
		
		tradeHandler.tradeFromToForEntity(current, next, Good.GLASS, false);
		
		assertEquals(0, current.getNumValue3Coins());
		assertEquals(1, (int) current.getCurrentTrades().get(Good.GLASS));
		assertEquals(5, next.getCoinTotal());
	}
	
	@Test
	public void testValidTrade1CoinHasEastTradingPost(){
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.TEMPLE));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);
		
		GameBoard board = new GameBoard(players, deck);
		TradeHandler tradeHandler = new TradeHandler(board);
		
		Player current = board.getPlayer(0);
		Player right = board.getPlayer(1);
		ArrayList<Card> storage = new ArrayList<Card>();
		storage.add(deck.getCard(12)); //east trading post
		current.setStoragePile(storage);
		

		ArrayList<Card> rStorage = new ArrayList<Card>();
		rStorage.add(deck.getCard(0));
		right.setStoragePile(rStorage);
		
		tradeHandler.tradeFromToForEntity(current, right, RawResource.LUMBER, true);
		
		assertEquals(2, current.getNumValue1Coins());
		assertEquals(1, (int) current.getCurrentTrades().get(RawResource.LUMBER));
		assertEquals(4, right.getCoinTotal());
	}
}
