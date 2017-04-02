package dataStructureTests;

import static org.junit.Assert.*;

import org.junit.Test;

import backend.TradeHandler;
import dataStructures.Player;
import exceptions.InsufficientFundsException;

public class PlayerTest {

	@Test
	public void testDefaultPlayer() {
		Player player = new Player();

		assertEquals(3, player.getCoinTotal());
		assertEquals(0, player.getConflictTotal());
		assertEquals("Jane Doe", player.getName());
	}

	@Test
	public void testDefaultPlayerCoins() {
		Player player = new Player();

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
		Player player = new Player();
		
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
		Player player = new Player();

		player.addValue1(1);
		assertEquals(4, player.getCoinTotal());
		assertEquals(4, player.getNumValue1Coins());

		player.addValue1(3);
		assertEquals(7, player.getCoinTotal());
		assertEquals(7, player.getNumValue1Coins());
	}

	@Test
	public void testAddValue3Coins() {
		Player player = new Player();

		player.addValue3(1);
		assertEquals(6, player.getCoinTotal());
		assertEquals(1, player.getNumValue3Coins());

		player.addValue3(3);
		assertEquals(15, player.getCoinTotal());
		assertEquals(4, player.getNumValue3Coins());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddInvalidNumValue1CoinsNeg1() {
		Player player = new Player();

		player.addValue1(-1);
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddInvalidNumValue1Coins47() {
		Player player = new Player();

		player.addValue1(47);
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddInvalidNumValue3CoinsNeg1() {
		Player player = new Player();

		player.addValue3(-1);
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddInvalidNumValue3Coins25() {
		Player player = new Player();

		player.addValue3(25);
		fail();
	}

	@Test
	public void testRemoveValidNumValue1Coins() {
		Player player = new Player();

		player.removeValue1(1);
		assertEquals(2, player.getCoinTotal());
		assertEquals(2, player.getNumValue1Coins());

		player.removeValue1(2);
		assertEquals(0, player.getCoinTotal());
		assertEquals(0, player.getNumValue1Coins());
	}

	@Test
	public void testRemoveValidNumValue3Coins() {
		Player player = new Player();

		player.addValue3(1);
		player.removeValue3(1);
		assertEquals(3, player.getCoinTotal());
		assertEquals(0, player.getNumValue3Coins());
	}

	@Test
	public void testMultiAddAndRemoveCoins() {
		Player player = new Player();

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
		Player player = new Player();

		player.removeValue1(-1);
		fail();
	}

	@Test(expected = InsufficientFundsException.class)
	public void testInsufficientFundsForRemoveValue1Coins() {
		Player player = new Player();

		player.removeValue1(4);
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidRemoveNumValue3CoinsNeg1() {
		Player player = new Player();

		player.removeValue3(-1);
		fail();
	}

	@Test(expected = InsufficientFundsException.class)
	public void testInsufficientFundsForRemoveValue3Coins() {
		Player player = new Player();

		player.removeValue3(1);
		fail();
	}
	
	@Test
	public void testAddInvalidNumValue1CoinsNeg1ErrorMessage() {
		Player player = new Player();

		try {
			player.addValue1(-1);
		} catch (IllegalArgumentException error) {
			String message = "Cannot add -1 value 1 coins";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testAddInvalidNumValue1Coins47ErrorMessage() {
		Player player = new Player();

		try {
			player.addValue1(47);
		} catch (IllegalArgumentException error) {
			String message = "Cannot add 47 value 1 coins";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testAddInvalidNumValue3CoinsNeg1ErrorMessage() {
		Player player = new Player();

		try {
			player.addValue3(-1);
		} catch (IllegalArgumentException error) {
			String message = "Cannot add -1 value 3 coins";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testInvalidRemoveNumValue1CoinsNeg1ErrorMessage() {
		Player player = new Player();

		try {
			player.removeValue1(-1);
		} catch (IllegalArgumentException error) {
			String message = "Cannot remove -1 value 1 coins";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testInvalidRemoveNumValue1CoinsNeg2ErrorMessage() {
		Player player = new Player();

		try {
			player.removeValue1(-2);
		} catch (IllegalArgumentException error) {
			String message = "Cannot remove -2 value 1 coins";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testInvalidRemoveNumValue3CoinsNeg1ErrorMessage() {
		Player player = new Player();

		try {
			player.removeValue3(-1);
		} catch (IllegalArgumentException error) {
			String message = "Cannot remove -1 value 3 coins";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testInvalidRemoveNumValue3CoinsNeg2ErrorMessage() {
		Player player = new Player();

		try {
			player.removeValue3(-2);
		} catch (IllegalArgumentException error) {
			String message = "Cannot remove -2 value 3 coins";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testInsufficientFundsForRemove4Value1CoinsErrorMessage() {
		Player player = new Player();

		try {
			player.removeValue1(4);
		} catch (InsufficientFundsException error) {
			String message = "Player does not have 4 value 1 coin(s)";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testInsufficientFundsForRemove5Value1CoinsErrorMessage() {
		Player player = new Player();

		try {
			player.removeValue1(5);
		} catch (InsufficientFundsException error) {
			String message = "Player does not have 5 value 1 coin(s)";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testInsufficientFundsForRemove1Value3CoinsErrorMessage() {
		Player player = new Player();

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
		Player player = new Player();

		try {
			player.removeValue3(2);
			fail();
		} catch (InsufficientFundsException error) {
			String message = "Player does not have 2 value 3 coin(s)";
			assertEquals(message, error.getMessage());
		}
	}

	
}
