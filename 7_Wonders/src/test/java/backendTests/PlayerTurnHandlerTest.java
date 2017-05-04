package backendTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import backend.PlayerTurnHandler;
import backend.SetUpDeckHandler;
import backend.TurnHandler;
import dataStructures.Card;
import dataStructures.Deck;
import dataStructures.GameBoard;
import dataStructures.Player;
import exceptions.InsufficientFundsException;
import dataStructures.Deck.Age;

public class PlayerTurnHandlerTest {

	@Test
	public void testBuildStructureNoCost(){
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine"));
		players.add(new Player("Captain America"));
		players.add(new Player("Black Widow"));
		
		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);
		
		GameBoard board = new GameBoard(players, deck);
		
		Player current = board.getCurrentPlayer();
		ArrayList<Card> currentHand = new ArrayList<Card>();
		
		currentHand.add(deck.getCard(0));
		current.setCurrentHand(currentHand);
		
		PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler();
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0));
		
		assertEquals(1, current.getStoragePile().size());
		assertEquals(0, current.getCurrentHand().size());
		assertFalse(current.getCurrentHand().contains(deck.getCard(0)));
		assertTrue(current.getStoragePile().contains(deck.getCard(0)));
	}
	
	@Test
	public void testBuildStructureCoinCost(){
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine"));
		players.add(new Player("Captain America"));
		players.add(new Player("Black Widow"));
		
		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);
		
		GameBoard board = new GameBoard(players, deck);
		
		Player current = board.getCurrentPlayer();
		ArrayList<Card> currentHand = new ArrayList<Card>();
		
		currentHand.add(deck.getCard(4));
		current.setCurrentHand(currentHand);
		
		PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler();
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0));
		
		assertEquals(1, current.getStoragePile().size());
		assertEquals(0, current.getCurrentHand().size());
		assertFalse(current.getCurrentHand().contains(deck.getCard(4)));
		assertTrue(current.getStoragePile().contains(deck.getCard(4)));
		assertEquals(0, current.getNumValue3Coins());
		assertEquals(2, current.getCoinTotal());
	}

	@Test
	public void testValidBuildStructureResourceCost(){
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine"));
		players.add(new Player("Captain America"));
		players.add(new Player("Black Widow"));
		
		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);
		
		GameBoard board = new GameBoard(players, deck);
		
		Player current = board.getCurrentPlayer();
		ArrayList<Card> currentHand = new ArrayList<Card>();
		
		currentHand.add(deck.getCard(1)); //Stone pit
		currentHand.add(deck.getCard(9)); //baths
		current.setCurrentHand(currentHand);
		
		PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler();
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0));
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0));
		
		assertEquals(2, current.getStoragePile().size());
		assertEquals(0, current.getCurrentHand().size());
		assertFalse(current.getCurrentHand().contains(deck.getCard(9)));
		assertTrue(current.getStoragePile().contains(deck.getCard(9)));
	}
	
	@Test(expected = InsufficientFundsException.class)
	public void testInvalidBuildStructureResourceCost(){
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine"));
		players.add(new Player("Captain America"));
		players.add(new Player("Black Widow"));
		
		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);
		
		GameBoard board = new GameBoard(players, deck);
		
		Player current = board.getCurrentPlayer();
		ArrayList<Card> currentHand = new ArrayList<Card>();
		
		currentHand.add(deck.getCard(0)); //lumber yard
		currentHand.add(deck.getCard(9)); //baths
		current.setCurrentHand(currentHand);
		
		PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler();
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0));
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0));
		fail();
	}
	
	@Test
	public void testValidBuildStructureEntityCost(){
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine"));
		players.add(new Player("Captain America"));
		players.add(new Player("Black Widow"));
		
		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);
		
		GameBoard board = new GameBoard(players, deck);
		
		Player current = board.getCurrentPlayer();
		ArrayList<Card> currentHand = new ArrayList<Card>();
		
		currentHand.add(deck.getCard(6)); //loom
		currentHand.add(deck.getCard(18)); //apothecary
		current.setCurrentHand(currentHand);
		
		PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler();
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0));
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0));
		
		assertEquals(2, current.getStoragePile().size());
		assertEquals(0, current.getCurrentHand().size());
		assertFalse(current.getCurrentHand().contains(deck.getCard(6)));
		assertTrue(current.getStoragePile().contains(deck.getCard(6)));
		assertFalse(current.getCurrentHand().contains(deck.getCard(18)));
		assertTrue(current.getStoragePile().contains(deck.getCard(18)));
	}
	
	@Test(expected = InsufficientFundsException.class)
	public void testInvalidBuildStructureGoodCost(){
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine"));
		players.add(new Player("Captain America"));
		players.add(new Player("Black Widow"));
		
		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);
		
		GameBoard board = new GameBoard(players, deck);
		
		Player current = board.getCurrentPlayer();
		ArrayList<Card> currentHand = new ArrayList<Card>();
		
		currentHand.add(deck.getCard(0)); //lumber yard
		currentHand.add(deck.getCard(18)); //baths
		current.setCurrentHand(currentHand);
		
		PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler();
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0));
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0));
		fail();
	}
}
