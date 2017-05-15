package dataStructureTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;

import org.easymock.EasyMock;
import org.junit.Test;

import backend.SetUpDeckHandler;
import dataStructures.Card;
import dataStructures.Chip;
import dataStructures.Chip.ChipType;
import dataStructures.Deck.Age;
import dataStructures.GeneralEnums.*;
import dataStructures.Player;
import dataStructures.Wonder;
import dataStructures.Wonder.WonderType;
import exceptions.InsufficientFundsException;
import junit.framework.Assert;

public class PlayerTest {

	@Test
	public void testDefaultPlayer() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		assertEquals(3, player.getCoinTotal());
		assertEquals(0, player.getConflictTotal());
		assertEquals("Jane Doe", player.getName());
	}

	@Test
	public void testDefaultPlayerCoins() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		assertEquals(3, player.getNumValue1Coins());
		assertEquals(0, player.getNumValue3Coins());
	}

	@Test
	public void testNamedPlayer() {
		String name = "Sandman";
		Player player = new Player(name, WonderType.COLOSSUS);

		assertEquals("Sandman", player.getName());
	}

	@Test
	public void testToStringDefaultPlayer() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		assertEquals("Name: Jane Doe\nCoin Total: 3", player.toString());
	}

	@Test
	public void testToStringNamedPlayer() {
		String name = "Sandman";
		Player player = new Player(name, WonderType.COLOSSUS);

		assertEquals("Name: Sandman\nCoin Total: 3", player.toString());
	}

	@Test
	public void testToStringPlayerAfterAddCoins() {
		String name = "Sandman";
		Player player = new Player(name, WonderType.COLOSSUS);
		player.addValue1(2, Chip.ChipType.COIN);
		player.addValue3(1, Chip.ChipType.COIN);

		assertEquals("Name: Sandman\nCoin Total: 8", player.toString());
	}

	@Test
	public void testAddValue1Coins() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		player.addValue1(1, Chip.ChipType.COIN);
		assertEquals(4, player.getCoinTotal());
		assertEquals(4, player.getNumValue1Coins());

		player.addValue1(3, Chip.ChipType.COIN);
		assertEquals(7, player.getCoinTotal());
		assertEquals(7, player.getNumValue1Coins());
	}

	@Test
	public void testAddValue3Coins() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		player.addValue3(1, Chip.ChipType.COIN);
		assertEquals(6, player.getCoinTotal());
		assertEquals(1, player.getNumValue3Coins());

		player.addValue3(3, Chip.ChipType.COIN);
		assertEquals(15, player.getCoinTotal());
		assertEquals(4, player.getNumValue3Coins());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddInvalidNumValue1CoinsNeg1() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		player.addValue1(-1, Chip.ChipType.COIN);
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddInvalidNumValue1Coins47() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		player.addValue1(47, Chip.ChipType.COIN);
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddInvalidNumValue3CoinsNeg1() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		player.addValue3(-1, Chip.ChipType.COIN);
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddInvalidNumValue3Coins25() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		player.addValue3(25, Chip.ChipType.COIN);
		fail();
	}

	@Test
	public void testRemoveValidNumValue1Coins() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		player.removeValue1(1);
		assertEquals(2, player.getCoinTotal());
		assertEquals(2, player.getNumValue1Coins());

		player.removeValue1(2);
		assertEquals(0, player.getCoinTotal());
		assertEquals(0, player.getNumValue1Coins());
	}

	@Test
	public void testRemoveValidNumValue3Coins() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		player.addValue3(1, Chip.ChipType.COIN);
		player.removeValue3(1);
		assertEquals(3, player.getCoinTotal());
		assertEquals(0, player.getNumValue3Coins());
	}

	@Test
	public void testMultiAddAndRemoveCoins() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		player.addValue1(5, Chip.ChipType.COIN);
		player.addValue3(3, Chip.ChipType.COIN);

		player.removeValue1(2);
		player.removeValue3(2);

		assertEquals(9, player.getCoinTotal());
		assertEquals(6, player.getNumValue1Coins());
		assertEquals(1, player.getNumValue3Coins());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidRemoveNumValue1CoinsNeg1() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		player.removeValue1(-1);
		fail();
	}

	@Test(expected = InsufficientFundsException.class)
	public void testInsufficientFundsForRemoveValue1Coins() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		player.removeValue1(4);
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidRemoveNumValue3CoinsNeg1() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		player.removeValue3(-1);
		fail();
	}

	@Test(expected = InsufficientFundsException.class)
	public void testInsufficientFundsForRemoveValue3Coins() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		player.removeValue3(1);
		fail();
	}

	@Test
	public void testAddInvalidNumValue1CoinsNeg1ErrorMessage() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		try {
			player.addValue1(-1, Chip.ChipType.COIN);
		} catch (IllegalArgumentException error) {
			String message = "Cannot add -1 value 1 chips";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testAddInvalidNumValue1Coins47ErrorMessage() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		try {
			player.addValue1(47, Chip.ChipType.COIN);
		} catch (IllegalArgumentException error) {
			String message = "Cannot add 47 value 1 chips";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testAddInvalidNumValue3CoinsNeg1ErrorMessage() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		try {
			player.addValue3(-1, Chip.ChipType.COIN);
		} catch (IllegalArgumentException error) {
			String message = "Cannot add -1 value 3 chips";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testInvalidRemoveNumValue1CoinsNeg1ErrorMessage() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		try {
			player.removeValue1(-1);
		} catch (IllegalArgumentException error) {
			String message = "Cannot remove -1 value 1 coins";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testInvalidRemoveNumValue1CoinsNeg2ErrorMessage() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		try {
			player.removeValue1(-2);
		} catch (IllegalArgumentException error) {
			String message = "Cannot remove -2 value 1 coins";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testInvalidRemoveNumValue3CoinsNeg1ErrorMessage() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		try {
			player.removeValue3(-1);
		} catch (IllegalArgumentException error) {
			String message = "Cannot remove -1 value 3 coins";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testInvalidRemoveNumValue3CoinsNeg2ErrorMessage() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		try {
			player.removeValue3(-2);
		} catch (IllegalArgumentException error) {
			String message = "Cannot remove -2 value 3 coins";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testInsufficientFundsForRemove4Value1CoinsErrorMessage() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		try {
			player.removeValue1(4);
		} catch (InsufficientFundsException error) {
			String message = "Player does not have 4 value 1 coin(s)";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testInsufficientFundsForRemove5Value1CoinsErrorMessage() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		try {
			player.removeValue1(5);
		} catch (InsufficientFundsException error) {
			String message = "Player does not have 5 value 1 coin(s)";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testInsufficientFundsForRemove1Value3CoinsErrorMessage() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

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
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		try {
			player.removeValue3(2);
			fail();
		} catch (InsufficientFundsException error) {
			String message = "Player does not have 2 value 3 coin(s)";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testRemoveTotalCoins() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
		player.removeTotalCoins(2);

		assertEquals(1, player.getNumValue1Coins());
		assertEquals(1, player.getCoinTotal());
	}

	@Test
	public void testRemoveTotalCoinsEnoughValue1NotEnoughValue3() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
		player.removeTotalCoins(3);

		assertEquals(0, player.getNumValue1Coins());
		assertEquals(0, player.getCoinTotal());
	}
	
	@Test
	public void testRemove3Value5Coins(){
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
		player.addValue5(5, ChipType.COIN);
		player.removeValue5(3);
		Assert.assertEquals(2,player.getNumValue5Coins());
		Assert.assertEquals(13,player.getCoinTotal());
	}
	
	@Test
	public void testRemove2Value5Coins(){
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
		player.addValue5(5, ChipType.COIN);
		player.removeValue5(2);
		Assert.assertEquals(3,player.getNumValue5Coins());
		Assert.assertEquals(18,player.getCoinTotal());
	}
	
	@Test
	public void testRemoveInvalidAmountOfValue5Coins(){
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
		player.addValue5(5, ChipType.COIN);
		try {
			player.removeValue5(7);
			fail();
		}catch (Exception e){
			Assert.assertEquals("Player does not have 7 value 5 coin(s)", e.getMessage());
		}
	}

	@Test
	public void testGetDefaultCurrentHand() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		assertTrue(player.getCurrentHand().isEmpty());
	}

	@Test
	public void testSetCurrectHand() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
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
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		assertTrue(player.getStoragePile().isEmpty());
		assertEquals(ArrayList.class, player.getStoragePile().getClass());
	}

	@Test
	public void testSetStoragePileHand() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
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
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
		ArrayList<Card> deckCards = new SetUpDeckHandler().createCards(Age.AGE1, 3);

		ArrayList<Card> playerCards = new ArrayList<Card>();
		playerCards.add(deckCards.get(0));
		playerCards.add(deckCards.get(1));
		playerCards.add(deckCards.get(2));

		player.setStoragePile(playerCards);

		assertTrue(player.storagePileContainsEntity(RawResource.LUMBER));
	}

	@Test
	public void testDefaultCurrentTrades() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		assertTrue(player.getCurrentTrades().isEmpty());
		assertEquals(HashMap.class, player.getCurrentTrades().getClass());
	}

	@Test
	public void testAddTraded() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		player.addTradedValue(RawResource.LUMBER);
		assertFalse(player.getCurrentTrades().isEmpty());
		assertTrue(player.getCurrentTrades().containsKey(RawResource.LUMBER));
		assertEquals(1, (int) player.getCurrentTrades().get(RawResource.LUMBER));
	}

	@Test
	public void testAddMultipleSameTraded() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		player.addTradedValue(RawResource.LUMBER);
		player.addTradedValue(RawResource.LUMBER);
		assertFalse(player.getCurrentTrades().isEmpty());
		assertTrue(player.getCurrentTrades().containsKey(RawResource.LUMBER));
		assertEquals(2, (int) player.getCurrentTrades().get(RawResource.LUMBER));
	}

	@Test
	public void testAddMultipleDifferentTraded() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		player.addTradedValue(RawResource.LUMBER);
		player.addTradedValue(Good.LOOM);
		assertFalse(player.getCurrentTrades().isEmpty());
		assertTrue(player.getCurrentTrades().containsKey(RawResource.LUMBER));
		assertEquals(1, (int) player.getCurrentTrades().get(RawResource.LUMBER));
		assertEquals(1, (int) player.getCurrentTrades().get(Good.LOOM));
	}

	@Test
	public void testRemoveCurrentTrades() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		player.addTradedValue(RawResource.LUMBER);
		player.addTradedValue(Good.LOOM);
		player.removeCurrentTrades();
		assertTrue(player.getCurrentTrades().isEmpty());
		assertEquals(HashMap.class, player.getCurrentTrades().getClass());
	}

	@Test
	public void testStoragePileContainsGood() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
		ArrayList<Card> deckCards = new SetUpDeckHandler().createCards(Age.AGE1, 3);

		ArrayList<Card> playerCards = new ArrayList<Card>();
		playerCards.add(deckCards.get(6));
		playerCards.add(deckCards.get(7));
		playerCards.add(deckCards.get(8));

		player.setStoragePile(playerCards);

		assertTrue(player.storagePileContainsEntity(Good.GLASS));
	}

	@Test
	public void testAddToStoragePile() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
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
	public void testRemoveFromCurrentHand() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
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

	@Test
	public void testCreatePlayerWithWonder() {
		Wonder wonder = EasyMock.mock(Wonder.class);
		EasyMock.expect(wonder.getType()).andReturn(WonderType.STATUE);
		EasyMock.expect(wonder.getName()).andReturn("The Statue of Zeus in Olympia");
		EasyMock.replay(wonder);
		Player player = new Player("Jane Doe", wonder.getType());
		assertEquals("The Statue of Zeus in Olympia", player.getWonder().getName());
	}

	@Test
	public void testCreatePlayerWithWonder2() {
		Wonder wonder = EasyMock.mock(Wonder.class);
		EasyMock.expect(wonder.getType()).andReturn(WonderType.LIGHTHOUSE);
		EasyMock.expect(wonder.getName()).andReturn("The Lighthouse of Alexandria");
		EasyMock.replay(wonder);
		Player player = new Player("Jane Doe", wonder.getType());
		assertEquals("The Lighthouse of Alexandria", player.getWonder().getName());
	}

	@Test
	public void testGetDefaultNumShields() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
		assertEquals(0, player.getNumShields());
	}

	@Test
	public void testAdd2Shields() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
		player.addNumShields(2);
		assertEquals(2, player.getNumShields());
	}

	@Test
	public void testGetDefaultNumVictoryPoints() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
		assertEquals(0, player.getNumVictoryPoints());
	}

	@Test
	public void testAdd2VictoryPoints() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
		player.addNumVictoryPoints(2);
		assertEquals(2, player.getNumVictoryPoints());
	}

	@Test
	public void testDefaultGetNumConflictTokens() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
		assertEquals(0, player.getNumValue1ConflictTokens());
	}

	@Test
	public void testAdd1Value1ConflictTokens() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
		player.addValue1(1, Chip.ChipType.CONFLICTTOKEN);
		assertEquals(1, player.getNumValue1ConflictTokens());
		assertEquals(1, player.getConflictTotal());
	}

	@Test
	public void testAddValue3ConflictTokens() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
		player.addValue3(1, Chip.ChipType.CONFLICTTOKEN);
		assertEquals(1, player.getNumValue3ConflictTokens());
		assertEquals(0, player.getNumValue3Coins());
		assertEquals(3, player.getConflictTotal());
	}

	@Test
	public void testAddValueNegConflictTokens() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
		player.addValueNeg1(1, Chip.ChipType.CONFLICTTOKEN);
		assertEquals(1, player.getNumValueNeg1ConflictTokens());
		assertEquals(0, player.getNumValue3ConflictTokens());
		assertEquals(-1, player.getConflictTotal());
	}

	@Test
	public void testAddInvalidNeg1CoinsErrorMessage() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
		try {
			player.addValueNeg1(1, Chip.ChipType.COIN);
			fail();
		} catch (IllegalArgumentException error) {
			assertEquals("Cannot have a negative 1 coin value", error.getMessage());
		}
	}
	
	@Test
	public void testAddValue5Coins(){
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
		player.addValue5(3, Chip.ChipType.COIN);
		Assert.assertEquals(3, player.getNumValue5Coins());
		Assert.assertEquals(18, player.getCoinTotal());
	}
	
	@Test
	public void testAddValue5Coins2(){
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
		player.addValue5(5, Chip.ChipType.COIN);
		Assert.assertEquals(5, player.getNumValue5Coins());
		Assert.assertEquals(28, player.getCoinTotal());
	}
	
	@Test
	public void testAddValue5CoinsOverMax(){
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
		try {
			player.addValue5(21, Chip.ChipType.COIN);
			fail();
		} catch (IllegalArgumentException error) {
			assertEquals("Cannot add 21 value 5 chips", error.getMessage());
		}
	}

	@Test
	public void testAddValue5ConflictTokens(){
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
		player.addValue5(3, Chip.ChipType.CONFLICTTOKEN);
		Assert.assertEquals(3, player.getNumValue5ConflictTokens());
		Assert.assertEquals(15, player.getConflictTotal());
	}
	
	@Test
	public void testAddValue5ConflictTokens2(){
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
		player.addValue5(5, Chip.ChipType.CONFLICTTOKEN);
		Assert.assertEquals(5, player.getNumValue5ConflictTokens());
		Assert.assertEquals(25, player.getConflictTotal());
	}
}
