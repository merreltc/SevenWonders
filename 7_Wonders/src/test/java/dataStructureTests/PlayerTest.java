package dataStructureTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import backend.SetUpDeckHandler;
import dataStructures.Card;
import dataStructures.Deck.Age;
import dataStructures.GeneralEnums.Good;
import dataStructures.GeneralEnums.Resource;
import dataStructures.Player;
import exceptions.InsufficientFundsException;

public class PlayerTest {

	@Test
	public void testDefaultPlayer() {
		Player player = new Player("Jane Doe");

		assertEquals(3, player.getCoinTotal());
		assertEquals(0, player.getConflictTotal());
		assertEquals("Jane Doe", player.getName());
	}

	@Test
	public void testDefaultPlayerCoins() {
		Player player = new Player("Jane Doe");

		assertEquals(3, player.getNumValue1Coins());
		assertEquals(0, player.getNumValue3Coins());
	}

	@Test
	public void testNamedPlayer() {
		String name = "Sandman";
		Player player = new Player(name);

		assertEquals("Sandman", player.getName());
	}

	@Test
	public void testToStringDefaultPlayer() {
		Player player = new Player("Jane Doe");

		assertEquals("Name: Jane Doe\nCoin Total: 3", player.toString());
	}

	@Test
	public void testToStringNamedPlayer() {
		String name = "Sandman";
		Player player = new Player(name);

		assertEquals("Name: Sandman\nCoin Total: 3", player.toString());
	}

	@Test
	public void testToStringPlayerAfterAddCoins() {
		String name = "Sandman";
		Player player = new Player(name);
		player.addValue1(2);
		player.addValue3(1);

		assertEquals("Name: Sandman\nCoin Total: 8", player.toString());
	}

	@Test
	public void testAddValue1Coins() {
		Player player = new Player("Jane Doe");

		player.addValue1(1);
		assertEquals(4, player.getCoinTotal());
		assertEquals(4, player.getNumValue1Coins());

		player.addValue1(3);
		assertEquals(7, player.getCoinTotal());
		assertEquals(7, player.getNumValue1Coins());
	}

	@Test
	public void testAddValue3Coins() {
		Player player = new Player("Jane Doe");

		player.addValue3(1);
		assertEquals(6, player.getCoinTotal());
		assertEquals(1, player.getNumValue3Coins());

		player.addValue3(3);
		assertEquals(15, player.getCoinTotal());
		assertEquals(4, player.getNumValue3Coins());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddInvalidNumValue1CoinsNeg1() {
		Player player = new Player("Jane Doe");

		player.addValue1(-1);
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddInvalidNumValue1Coins47() {
		Player player = new Player("Jane Doe");

		player.addValue1(47);
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddInvalidNumValue3CoinsNeg1() {
		Player player = new Player("Jane Doe");

		player.addValue3(-1);
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddInvalidNumValue3Coins25() {
		Player player = new Player("Jane Doe");

		player.addValue3(25);
		fail();
	}

	@Test
	public void testRemoveValidNumValue1Coins() {
		Player player = new Player("Jane Doe");

		player.removeValue1(1);
		assertEquals(2, player.getCoinTotal());
		assertEquals(2, player.getNumValue1Coins());

		player.removeValue1(2);
		assertEquals(0, player.getCoinTotal());
		assertEquals(0, player.getNumValue1Coins());
	}

	@Test
	public void testRemoveValidNumValue3Coins() {
		Player player = new Player("Jane Doe");

		player.addValue3(1);
		player.removeValue3(1);
		assertEquals(3, player.getCoinTotal());
		assertEquals(0, player.getNumValue3Coins());
	}

	@Test
	public void testMultiAddAndRemoveCoins() {
		Player player = new Player("Jane Doe");

		player.addValue1(5);
		player.addValue3(3);

		player.removeValue1(2);
		player.removeValue3(2);

		assertEquals(9, player.getCoinTotal());
		assertEquals(6, player.getNumValue1Coins());
		assertEquals(1, player.getNumValue3Coins());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidRemoveNumValue1CoinsNeg1() {
		Player player = new Player("Jane Doe");

		player.removeValue1(-1);
		fail();
	}

	@Test(expected = InsufficientFundsException.class)
	public void testInsufficientFundsForRemoveValue1Coins() {
		Player player = new Player("Jane Doe");

		player.removeValue1(4);
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidRemoveNumValue3CoinsNeg1() {
		Player player = new Player("Jane Doe");

		player.removeValue3(-1);
		fail();
	}

	@Test(expected = InsufficientFundsException.class)
	public void testInsufficientFundsForRemoveValue3Coins() {
		Player player = new Player("Jane Doe");

		player.removeValue3(1);
		fail();
	}

	@Test
	public void testAddInvalidNumValue1CoinsNeg1ErrorMessage() {
		Player player = new Player("Jane Doe");

		try {
			player.addValue1(-1);
		} catch (IllegalArgumentException error) {
			String message = "Cannot add -1 value 1 coins";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testAddInvalidNumValue1Coins47ErrorMessage() {
		Player player = new Player("Jane Doe");

		try {
			player.addValue1(47);
		} catch (IllegalArgumentException error) {
			String message = "Cannot add 47 value 1 coins";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testAddInvalidNumValue3CoinsNeg1ErrorMessage() {
		Player player = new Player("Jane Doe");

		try {
			player.addValue3(-1);
		} catch (IllegalArgumentException error) {
			String message = "Cannot add -1 value 3 coins";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testInvalidRemoveNumValue1CoinsNeg1ErrorMessage() {
		Player player = new Player("Jane Doe");

		try {
			player.removeValue1(-1);
		} catch (IllegalArgumentException error) {
			String message = "Cannot remove -1 value 1 coins";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testInvalidRemoveNumValue1CoinsNeg2ErrorMessage() {
		Player player = new Player("Jane Doe");

		try {
			player.removeValue1(-2);
		} catch (IllegalArgumentException error) {
			String message = "Cannot remove -2 value 1 coins";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testInvalidRemoveNumValue3CoinsNeg1ErrorMessage() {
		Player player = new Player("Jane Doe");

		try {
			player.removeValue3(-1);
		} catch (IllegalArgumentException error) {
			String message = "Cannot remove -1 value 3 coins";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testInvalidRemoveNumValue3CoinsNeg2ErrorMessage() {
		Player player = new Player("Jane Doe");

		try {
			player.removeValue3(-2);
		} catch (IllegalArgumentException error) {
			String message = "Cannot remove -2 value 3 coins";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testInsufficientFundsForRemove4Value1CoinsErrorMessage() {
		Player player = new Player("Jane Doe");

		try {
			player.removeValue1(4);
		} catch (InsufficientFundsException error) {
			String message = "Player does not have 4 value 1 coin(s)";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testInsufficientFundsForRemove5Value1CoinsErrorMessage() {
		Player player = new Player("Jane Doe");

		try {
			player.removeValue1(5);
		} catch (InsufficientFundsException error) {
			String message = "Player does not have 5 value 1 coin(s)";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testInsufficientFundsForRemove1Value3CoinsErrorMessage() {
		Player player = new Player("Jane Doe");

		try {
			player.removeValue3(1);
			fail();
		} catch (InsufficientFundsException error) {
			String message = "Player does not have 1 value 3 coin(s)";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testInsufficientFundsForRemove2Value3CoinsErrorMessage() {
		Player player = new Player("Jane Doe");

		try {
			player.removeValue3(2);
			fail();
		} catch (InsufficientFundsException error) {
			String message = "Player does not have 2 value 3 coin(s)";
			assertEquals(message, error.getMessage());
		}
	}
	
	@Test
	public void testRemoveTotalCoins(){
		Player player = new Player("Jane Doe");
		player.removeTotalCoins(2);
		
		assertEquals(1, player.getNumValue1Coins());
		assertEquals(1, player.getCoinTotal());
	}

	@Test
	public void testGetDefaultCurrentHand() {
		Player player = new Player("Jane Doe");
		
		assertTrue(player.getCurrentHand().isEmpty());
	}
	
	@Test
	public void testSetCurrectHand() {
		Player player = new Player("Jane Doe");
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
	public void testGetDefaultStoragePileHand() {
		Player player = new Player("Jane Doe");
		
		assertTrue(player.getStoragePile().isEmpty());
		assertEquals(ArrayList.class, player.getStoragePile().getClass());
	}
	
	@Test
	public void testSetStoragePileHand() {
		Player player = new Player("Jane Doe");
		ArrayList<Card> deckCards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		
		ArrayList<Card> playerCards = new ArrayList<Card>();
		playerCards.add(deckCards.get(0));
		playerCards.add(deckCards.get(1));
		playerCards.add(deckCards.get(2));
		
		player.setStoragePile(playerCards);
		
		assertEquals(playerCards, player.getStoragePile());
		assertEquals(playerCards.get(0), player.getStoragePile().get(0));
		assertEquals(playerCards.get(1), player.getStoragePile().get(1));
		assertEquals(playerCards.get(2), player.getStoragePile().get(2));
	}
	
	@Test
	public void testStoragePileContainsResource() {
		Player player = new Player("Jane Doe");
		ArrayList<Card> deckCards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		
		ArrayList<Card> playerCards = new ArrayList<Card>();
		playerCards.add(deckCards.get(0));
		playerCards.add(deckCards.get(1));
		playerCards.add(deckCards.get(2));
		
		player.setStoragePile(playerCards);
		
		assertTrue(player.storagePileContainsEntity(Resource.LUMBER));
	}
	
	@Test
	public void testDefaultCurrentTrades() {
		Player player = new Player("Jane Doe");
		
		assertTrue(player.getCurrentTrades().isEmpty());
		assertEquals(HashMap.class, player.getCurrentTrades().getClass());
	}
	
	@Test
	public void testAddTraded() {
		Player player = new Player("Jane Doe");
		
		player.addTradedValue(Resource.LUMBER);
		assertFalse(player.getCurrentTrades().isEmpty());
		assertTrue(player.getCurrentTrades().containsKey(Resource.LUMBER));
		assertEquals(1, (int) player.getCurrentTrades().get(Resource.LUMBER));
	}
	
	@Test
	public void testAddMultipleSameTraded() {
		Player player = new Player("Jane Doe");
		
		player.addTradedValue(Resource.LUMBER);
		player.addTradedValue(Resource.LUMBER);
		assertFalse(player.getCurrentTrades().isEmpty());
		assertTrue(player.getCurrentTrades().containsKey(Resource.LUMBER));
		assertEquals(2, (int) player.getCurrentTrades().get(Resource.LUMBER));
	}
	
	@Test
	public void testAddMultipleDifferentTraded() {
		Player player = new Player("Jane Doe");
		
		player.addTradedValue(Resource.LUMBER);
		player.addTradedValue(Good.LOOM);
		assertFalse(player.getCurrentTrades().isEmpty());
		assertTrue(player.getCurrentTrades().containsKey(Resource.LUMBER));
		assertEquals(1, (int) player.getCurrentTrades().get(Resource.LUMBER));
		assertEquals(1, (int) player.getCurrentTrades().get(Good.LOOM));
	}
	
	@Test
	public void testRemoveCurrentTrades() {
		Player player = new Player("Jane Doe");
		
		player.addTradedValue(Resource.LUMBER);
		player.addTradedValue(Good.LOOM);
		player.removeCurrentTrades();
		assertTrue(player.getCurrentTrades().isEmpty());
		assertEquals(HashMap.class, player.getCurrentTrades().getClass());
	}
	
	@Test
	public void testStoragePileContainsGood() {
		Player player = new Player("Jane Doe");
		ArrayList<Card> deckCards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		
		ArrayList<Card> playerCards = new ArrayList<Card>();
		playerCards.add(deckCards.get(6));
		playerCards.add(deckCards.get(7));
		playerCards.add(deckCards.get(8));
		
		player.setStoragePile(playerCards);
		
		assertTrue(player.storagePileContainsEntity(Good.GLASS));
	}
	
	@Test
	public void testAddToStoragePile(){
		Player player = new Player("Jane Doe");
		ArrayList<Card> deckCards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		
		ArrayList<Card> playerCards = new ArrayList<Card>();
		playerCards.add(deckCards.get(0));
		playerCards.add(deckCards.get(1));
		playerCards.add(deckCards.get(2));
		
		player.setStoragePile(playerCards);
		
		player.addToStoragePile(deckCards.get(3));
		assertEquals(4, player.getStoragePile().size());
		assertEquals(deckCards.get(3), player.getStoragePile().get(3));
	}
	
	@Test
	public void testRemoveFromCurrentHand(){
		Player player = new Player("Jane Doe");
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

}
