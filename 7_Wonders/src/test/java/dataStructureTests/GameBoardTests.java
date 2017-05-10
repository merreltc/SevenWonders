package dataStructureTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import backend.SetUpDeckHandler;
import dataStructures.Card;
import dataStructures.Deck;
import dataStructures.GameBoard;
import dataStructures.Player;
import dataStructures.Wonder.WonderType;
import exceptions.InsufficientFundsException;
import dataStructures.Deck.Age;

public class GameBoardTests {

	@Test
	public void testGameBoardGMinPlayers() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Jane Doe", WonderType.COLOSSUS));
		players.add(new Player("Jane Doe", WonderType.LIGHTHOUSE));
		players.add(new Player("Jane Doe", WonderType.PYRAMIDS));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);

		assertEquals(3, board.getNumPlayers());
	}

	@Test
	public void testGameBoardMaxPlayers() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Jane Doe", WonderType.COLOSSUS));
		players.add(new Player("Jane Doe", WonderType.LIGHTHOUSE));
		players.add(new Player("Jane Doe", WonderType.PYRAMIDS));
		players.add(new Player("Jane Doe", WonderType.TEMPLE));
		players.add(new Player("Jane Doe", WonderType.MAUSOLEUM));
		players.add(new Player("Jane Doe", WonderType.STATUE));
		players.add(new Player("Jane Doe", WonderType.GARDENS));


		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);

		assertEquals(7, board.getNumPlayers());
	}

	@Test
	public void testGameBoardGetPlayers() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Jane Doe", WonderType.COLOSSUS));
		players.add(new Player("Jane Doe", WonderType.LIGHTHOUSE));
		players.add(new Player("Jane Doe", WonderType.PYRAMIDS));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);

		assertEquals(players, board.getPlayers());
	}

	@Test
	public void testGameBoardGetPlayerByIndex() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);

		for (int i = 0; i < players.size(); i++) {
			assertEquals(players.get(i), board.getPlayer(i));
		}
	}

	@Test
	public void testGameBoardGetPlayerCoinTotalOnStart() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);

		for (int i = 0; i < players.size(); i++) {
			assertEquals(3, board.getPlayerCoinTotal(i));
		}
	}

	@Test
	public void testGetPlayerPositionsOnStartMin() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		
		comparePlayerPositions(players, board, 0, 1, 2);
	}

	@Test
	public void testGetPlayerPositionsOnStartMax() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));
		players.add(new Player("Hulk", WonderType.TEMPLE));
		players.add(new Player("Iron Man", WonderType.MAUSOLEUM));
		players.add(new Player("Spider Man", WonderType.STATUE));
		players.add(new Player("Thor", WonderType.GARDENS));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		
		comparePlayerPositions(players, board, 0, 1, 6);
	}

	public void comparePlayerPositions(ArrayList<Player> players, GameBoard board, int currIndex, int nextIndex,
			int previousIndex) {
		assertEquals(players.get(currIndex), board.getCurrentPlayer());
		assertEquals(players.get(nextIndex), board.getNextPlayer());
		assertEquals(players.get(previousIndex), board.getPreviousPlayer());
	}
	
	@Test
	public void testSetCurrentPlayerValid() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));
		players.add(new Player("Hulk", WonderType.TEMPLE));
		players.add(new Player("Iron Man", WonderType.MAUSOLEUM));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		board.setCurrentPlayer(0);
		
		assertEquals(players.get(0), board.getCurrentPlayer());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetCurrentPlayerInvalidNeg1() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));
		players.add(new Player("Hulk", WonderType.TEMPLE));
		players.add(new Player("Iron Man", WonderType.MAUSOLEUM));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		board.setCurrentPlayer(-1);
		fail();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetCurrentPlayerInvalidMaxPlus1() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));
		players.add(new Player("Hulk", WonderType.TEMPLE));
		players.add(new Player("Iron Man", WonderType.MAUSOLEUM));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		board.setCurrentPlayer(5);
		fail();
	}
	
	@Test
	public void testSetNextPlayerValid() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));
		players.add(new Player("Hulk", WonderType.TEMPLE));
		players.add(new Player("Iron Man", WonderType.MAUSOLEUM));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		board.setNextPlayer(0);
		
		assertEquals(players.get(0), board.getNextPlayer());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetNextPlayerInvalidNeg1() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));
		players.add(new Player("Hulk", WonderType.TEMPLE));
		players.add(new Player("Iron Man", WonderType.MAUSOLEUM));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		board.setNextPlayer(-1);
		fail();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetNextPlayerInvalidMaxPlus1() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));
		players.add(new Player("Hulk", WonderType.TEMPLE));
		players.add(new Player("Iron Man", WonderType.MAUSOLEUM));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		board.setNextPlayer(5);
		fail();
	}
	
	@Test
	public void testSetPreviousPlayerValid() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));
		players.add(new Player("Hulk", WonderType.TEMPLE));
		players.add(new Player("Iron Man", WonderType.MAUSOLEUM));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		board.setPreviousPlayer(0);
		
		assertEquals(players.get(0), board.getPreviousPlayer());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetPreviousPlayerInvalidNeg1() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));
		players.add(new Player("Hulk", WonderType.TEMPLE));
		players.add(new Player("Iron Man", WonderType.MAUSOLEUM));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		board.setPreviousPlayer(-1);
		fail();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetPreviousPlayerInvalidMaxPlus1() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));
		players.add(new Player("Hulk", WonderType.TEMPLE));
		players.add(new Player("Iron Man", WonderType.MAUSOLEUM));
		
		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		board.setPreviousPlayer(5);
		fail();
	}
	
	@Test
	public void testGetPlayerPositionIndexes(){
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));
		players.add(new Player("Hulk", WonderType.TEMPLE));
		players.add(new Player("Iron Man", WonderType.MAUSOLEUM));
		
		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		
		assertEquals(0, board.getCurrentPlayerIndex());
		assertEquals(1, board.getNextPlayerIndex());
		assertEquals(4, board.getPreviousPlayerIndex());
	}

	@Test
	public void testGetDeck() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));
		players.add(new Player("Hulk", WonderType.TEMPLE));
		players.add(new Player("Iron Man", WonderType.MAUSOLEUM));
		
		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		assertEquals(deck, board.getDeck());
	}
	
	@Test
	public void testGetDefaultDiscardPile(){
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));
		players.add(new Player("Hulk", WonderType.TEMPLE));
		players.add(new Player("Iron Man", WonderType.MAUSOLEUM));
		
		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		assertTrue(board.getDiscardPile().isEmpty());
		assertEquals(ArrayList.class, board.getDiscardPile().getClass());
	}
	
	@Test
	public void testAddToDiscardPile(){
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));
		players.add(new Player("Hulk", WonderType.TEMPLE));
		players.add(new Player("Iron Man", WonderType.MAUSOLEUM));
		
		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		Card toTest = deck.getCard(0);
		board.addToDiscardPile(players.get(0),toTest);
		
		assertEquals(1, players.get(0).getNumValue3Coins());
		assertFalse(board.getDiscardPile().isEmpty());
		assertEquals(toTest, board.getDiscardPile().get(0));
	}
	
	@Test
	public void testAddToDiscardPile3Value1CoinsRecieved(){
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));
		players.add(new Player("Hulk", WonderType.TEMPLE));
		players.add(new Player("Iron Man", WonderType.MAUSOLEUM));
		
		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		players.get(1).addValue1(36);
		players.get(1).addValue1(36);
		board.makeChangeForValue3Coins(players.get(1), 24);
		Card toTest = deck.getCard(0);
		board.addToDiscardPile(players.get(0),toTest);
		
		assertEquals(6, players.get(0).getNumValue1Coins());
		assertFalse(board.getDiscardPile().isEmpty());
		assertEquals(toTest, board.getDiscardPile().get(0));
	}
	
	@Test
	public void testGetTotalValue1CoinsLeft3Players(){
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		assertEquals(37, board.getTotalValue1CoinsInBank());
	}
	
	@Test
	public void testGetTotalValue1CoinsLeft7Players(){
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));
		players.add(new Player("Hulk", WonderType.TEMPLE));
		players.add(new Player("Iron Man", WonderType.MAUSOLEUM));
		players.add(new Player("Spider Man", WonderType.STATUE));
		players.add(new Player("Thor", WonderType.GARDENS));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 7);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		assertEquals(25, board.getTotalValue1CoinsInBank());
	}
	
	@Test
	public void testGetTotalValue3CoinsLeft3Players(){
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		assertEquals(24, board.getTotalValue3CoinsInBank());
	}
	
	@Test
	public void testMakeChangeForValue1Coins(){
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		Player active = players.get(0);
		active.addValue3(1);
		assertTrue(board.makeChangeForValue1Coins(active, 3));
		assertEquals(6, active.getNumValue1Coins());
		assertEquals(0, active.getNumValue3Coins());
		assertEquals(34, board.getTotalValue1CoinsInBank());
		assertEquals(25, board.getTotalValue3CoinsInBank());
	}
	
	@Test
	public void testMakeChangeForValue1CoinsNotEnoughInBank(){
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		Player active = players.get(0);
		active.addValue3(14);
		
		try{
			board.makeChangeForValue1Coins(active, 42);
		}catch (InsufficientFundsException e){
			assertEquals("Not enough value 1 coins left in bank", e.getMessage());
		}
	}
	
	@Test
	public void testMakeChangeForValue3Coins(){
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		Player active = players.get(0);
		assertTrue(board.makeChangeForValue3Coins(active, 1));
		assertEquals(0, active.getNumValue1Coins());
		assertEquals(1, active.getNumValue3Coins());
		assertEquals(40, board.getTotalValue1CoinsInBank());
		assertEquals(23, board.getTotalValue3CoinsInBank());
	}
	
	@Test
	public void testMakeChangeForValue3CoinsNotEnoughInBank(){
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		Player active = players.get(0);
		active.addValue1(40);
		active.addValue1(40);
		
		try{
			board.makeChangeForValue3Coins(active, 25);
		}catch (InsufficientFundsException e){
			assertEquals("Not enough value 3 coins left in bank", e.getMessage());
		}
	}
	
	@Test
	public void testGiveNumCoins5(){
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		Player active = players.get(0);
		
		board.giveNumCoins(active, 5);
		
		assertEquals(8, active.getCoinTotal());
		assertEquals(23, board.getTotalValue3CoinsInBank());
		assertEquals(35, board.getTotalValue1CoinsInBank());
	}
}
