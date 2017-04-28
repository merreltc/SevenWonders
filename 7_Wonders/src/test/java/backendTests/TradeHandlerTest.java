package backendTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import backend.SetUpDeckHandler;
import backend.TradeHandler;
import dataStructures.Card;
import dataStructures.Deck;
import dataStructures.GameBoard;
import dataStructures.Player;
import dataStructures.Deck.Age;
import exceptions.InsufficientFundsException;
import exceptions.InvalidTradeException;

public class TradeHandlerTest {
	@Test
	public void testSingleTradeValue1Coins() {
		Player player1 =new Player("Jane Doe");
		Player player2 =new Player("Jane Doe");

		TradeHandler.tradeFromToValue1(player1, player2, 1);

		assertEquals(4, player2.getCoinTotal());
		assertEquals(4, player2.getNumValue1Coins());
		assertEquals(2, player1.getCoinTotal());
		assertEquals(2, player1.getNumValue1Coins());

	}

	@Test
	public void testMultiTradesValue1Coins() {
		Player player1 =new Player("Jane Doe");
		Player player2 =new Player("Jane Doe");

		TradeHandler.tradeFromToValue1(player1, player2, 2);
		TradeHandler.tradeFromToValue1(player1, player2, 1);

		assertEquals(6, player2.getCoinTotal());
		assertEquals(6, player2.getNumValue1Coins());
		assertEquals(0, player1.getCoinTotal());
		assertEquals(0, player1.getNumValue1Coins());

	}

	@Test(expected = InsufficientFundsException.class)
	public void testInvalidTradeValue1Coin() {
		Player player1 =new Player("Jane Doe");
		Player player2 =new Player("Jane Doe");

		TradeHandler.tradeFromToValue1(player1, player2, 4);
		fail();
	}

	@Test
	public void testSingleTradeValue3Coins() {
		Player player1 =new Player("Jane Doe");
		Player player2 =new Player("Jane Doe");

		player1.addValue3(1);
		TradeHandler.tradeFromToValue3(player1, player2, 1);

		assertEquals(6, player2.getCoinTotal());
		assertEquals(1, player2.getNumValue3Coins());
		assertEquals(3, player1.getCoinTotal());
		assertEquals(0, player1.getNumValue3Coins());
	}

	@Test
	public void testMultiTradesValue3Coins() {
		Player player1 =new Player("Jane Doe");
		Player player2 =new Player("Jane Doe");

		player1.addValue3(3);
		TradeHandler.tradeFromToValue3(player1, player2, 2);
		TradeHandler.tradeFromToValue3(player1, player2, 1);

		assertEquals(12, player2.getCoinTotal());
		assertEquals(3, player2.getNumValue3Coins());
		assertEquals(3, player1.getCoinTotal());
		assertEquals(0, player1.getNumValue3Coins());
	}

	@Test(expected = InsufficientFundsException.class)
	public void testInvalidTradeValue3Coin() {
		Player player1 =new Player("Jane Doe");
		Player player2 =new Player("Jane Doe");

		TradeHandler.tradeFromToValue3(player1, player2, 1);
		fail();
	}

	@Test
	public void testMultiTrades() {
		Player player1 =new Player("Jane Doe");
		Player player2 =new Player("Jane Doe");

		player1.addValue3(3);
		
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
		Player player1 =new Player("Jane Doe");
		Player player2 =new Player("Jane Doe");

		player1.addValue3(2);

		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		players.add(new Player("Jane Doe"));
		
		ArrayList<Card> cards = SetUpDeckHandler.setUpDeckHandler.createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		TradeHandler tradeHandler = new TradeHandler(board);
		
		tradeHandler.tradeFromTo(player1, player2, 4);

		assertEquals(5, player1.getCoinTotal());
		assertEquals(2, player1.getNumValue1Coins());
		assertEquals(1, player1.getNumValue3Coins());

		assertEquals(7, player2.getCoinTotal());
		assertEquals(4, player2.getNumValue1Coins());
		assertEquals(1, player2.getNumValue3Coins());
	}

	@Test
	public void testTradeFromToMultiValue1SingleValue3() {
		Player player1 =new Player("Jane Doe");
		Player player2 =new Player("Jane Doe");

		player1.addValue3(2);

		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		players.add(new Player("Jane Doe"));
		
		ArrayList<Card> cards = SetUpDeckHandler.setUpDeckHandler.createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		TradeHandler tradeHandler = new TradeHandler(board);
		
		tradeHandler.tradeFromTo(player1, player2, 5);

		assertEquals(4, player1.getCoinTotal());
		assertEquals(1, player1.getNumValue1Coins());
		assertEquals(1, player1.getNumValue3Coins());

		assertEquals(8, player2.getCoinTotal());
		assertEquals(5, player2.getNumValue1Coins());
		assertEquals(1, player2.getNumValue3Coins());
	}

	@Test
	public void testTradeFromToSingleValue1MultiValue3() {
		Player player1 =new Player("Jane Doe");
		Player player2 =new Player("Jane Doe");

		player1.addValue3(2);

		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		players.add(new Player("Jane Doe"));
		
		ArrayList<Card> cards = SetUpDeckHandler.setUpDeckHandler.createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		TradeHandler tradeHandler = new TradeHandler(board);
		
		tradeHandler.tradeFromTo(player1, player2, 7);

		assertEquals(2, player1.getCoinTotal());
		assertEquals(2, player1.getNumValue1Coins());
		assertEquals(0, player1.getNumValue3Coins());

		assertEquals(10, player2.getCoinTotal());
		assertEquals(4, player2.getNumValue1Coins());
		assertEquals(2, player2.getNumValue3Coins());
	}

	@Test
	public void testTradeFromToMultiValue1MultiValue3() {
		Player player1 =new Player("Jane Doe");
		Player player2 =new Player("Jane Doe");

		player1.addValue3(2);

		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		players.add(new Player("Jane Doe"));
		
		ArrayList<Card> cards = SetUpDeckHandler.setUpDeckHandler.createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		TradeHandler tradeHandler = new TradeHandler(board);
		
		tradeHandler.tradeFromTo(player1, player2, 8);

		assertEquals(1, player1.getCoinTotal());
		assertEquals(1, player1.getNumValue1Coins());
		assertEquals(0, player1.getNumValue3Coins());

		assertEquals(11, player2.getCoinTotal());
		assertEquals(5, player2.getNumValue1Coins());
		assertEquals(2, player2.getNumValue3Coins());
	}

	@Test
	public void testTradeFromToSufficientValue1NoValue3() {
		Player player1 =new Player("Jane Doe");
		Player player2 =new Player("Jane Doe");
		
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		players.add(new Player("Jane Doe"));
		
		ArrayList<Card> cards = SetUpDeckHandler.setUpDeckHandler.createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		TradeHandler tradeHandler = new TradeHandler(board);

		tradeHandler.tradeFromTo(player1, player2, 3);

		assertEquals(0, player1.getCoinTotal());
		assertEquals(0, player1.getNumValue1Coins());
		assertEquals(0, player1.getNumValue3Coins());

		assertEquals(6, player2.getCoinTotal());
		assertEquals(6, player2.getNumValue1Coins());
		assertEquals(0, player2.getNumValue3Coins());
	}

	@Test
	public void testTradeFromToGreaterValue1ThanValue3() {
		Player player1 =new Player("Jane Doe");
		Player player2 =new Player("Jane Doe");
		
		player1.addValue3(1);
		
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		players.add(new Player("Jane Doe"));
		
		ArrayList<Card> cards = SetUpDeckHandler.setUpDeckHandler.createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		TradeHandler tradeHandler = new TradeHandler(board);
		
		tradeHandler.tradeFromTo(player1, player2, 6);

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
		players.add(new Player("Wolverine"));
		players.add(new Player("Captain America"));
		players.add(new Player("Black Widow"));
		players.add(new Player("Hulk"));
		players.add(new Player("Iron Man"));

		ArrayList<Card> cards = SetUpDeckHandler.setUpDeckHandler.createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		TradeHandler tradeHandler = new TradeHandler(board);
		
		tradeHandler.tradeFromTo(board.getCurrentPlayer(), board.getNextPlayer(), 3);
		assertEquals(0, board.getPlayerCoinTotal(board.getCurrentPlayerIndex()));
		assertEquals(6, board.getPlayerCoinTotal(board.getNextPlayerIndex()));
	}
	

	@Test
	public void testTradeToPreviousPlayer() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine"));
		players.add(new Player("Captain America"));
		players.add(new Player("Black Widow"));
		players.add(new Player("Hulk"));
		players.add(new Player("Iron Man"));

		ArrayList<Card> cards = SetUpDeckHandler.setUpDeckHandler.createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		TradeHandler tradeHandler = new TradeHandler(board);
		
		tradeHandler.tradeFromTo(board.getCurrentPlayer(), board.getPreviousPlayer(), 3);
		assertEquals(0, board.getPlayerCoinTotal(board.getCurrentPlayerIndex()));
		assertEquals(6, board.getPlayerCoinTotal(board.getPreviousPlayerIndex()));
	}
	
	@Test(expected = InvalidTradeException.class)
	public void testTradeToInvalidPlayer() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine"));
		players.add(new Player("Captain America"));
		players.add(new Player("Black Widow"));
		players.add(new Player("Hulk"));
		players.add(new Player("Iron Man"));

		ArrayList<Card> cards = SetUpDeckHandler.setUpDeckHandler.createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		TradeHandler tradeHandler = new TradeHandler(board);
		
		tradeHandler.tradeFromTo(board.getCurrentPlayer(), board.getPlayer(2), 3);
		fail();
	}
}
