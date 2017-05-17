package backendTests;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import backend.handlers.PlayerChipHandler;
import dataStructures.gameMaterials.Wonder.WonderType;
import dataStructures.playerData.Chip;
import dataStructures.playerData.Player;
import dataStructures.playerData.Chip.ChipType;
import exceptions.InsufficientFundsException;

public class PlayerChipHandlerTest {

	@Test
	public void testAddValue1Coins() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		PlayerChipHandler.addValue1(player, 1, Chip.ChipType.COIN);
		assertEquals(4, player.getCoinTotal());
		assertEquals(4, player.getNumValue1Coins());

		PlayerChipHandler.addValue1(player, 3, Chip.ChipType.COIN);
		assertEquals(7, player.getCoinTotal());
		assertEquals(7, player.getNumValue1Coins());
	}

	@Test
	public void testAddValue3Coins() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		PlayerChipHandler.addValue3(player, 1, Chip.ChipType.COIN);
		assertEquals(6, player.getCoinTotal());
		assertEquals(1, player.getNumValue3Coins());

		PlayerChipHandler.addValue3(player, 3, Chip.ChipType.COIN);
		assertEquals(15, player.getCoinTotal());
		assertEquals(4, player.getNumValue3Coins());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddInvalidNumValue1CoinsNeg1() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		PlayerChipHandler.addValue1(player, -1, Chip.ChipType.COIN);
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddInvalidNumValue1Coins47() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		PlayerChipHandler.addValue1(player, 47, Chip.ChipType.COIN);
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddInvalidNumValue3CoinsNeg1() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		PlayerChipHandler.addValue3(player, -1, Chip.ChipType.COIN);
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddInvalidNumValue3Coins25() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		PlayerChipHandler.addValue3(player, 25, Chip.ChipType.COIN);
		fail();
	}

	@Test
	public void testRemoveValidNumValue1Coins() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		PlayerChipHandler.removeValue1(player, 1, ChipType.COIN);
		assertEquals(2, player.getCoinTotal());
		assertEquals(2, player.getNumValue1Coins());

		PlayerChipHandler.removeValue1(player, 2, ChipType.COIN);
		assertEquals(0, player.getCoinTotal());
		assertEquals(0, player.getNumValue1Coins());
	}

	@Test
	public void testRemoveValidNumValue3Coins() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		PlayerChipHandler.addValue3(player, 1, Chip.ChipType.COIN);
		PlayerChipHandler.removeValue3(player, 1, ChipType.COIN);
		assertEquals(3, player.getCoinTotal());
		assertEquals(0, player.getNumValue3Coins());
	}

	@Test
	public void testMultiAddAndRemoveCoins() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		PlayerChipHandler.addValue1(player, 5, Chip.ChipType.COIN);
		PlayerChipHandler.addValue3(player, 3, Chip.ChipType.COIN);

		PlayerChipHandler.removeValue1(player, 2, ChipType.COIN);
		PlayerChipHandler.removeValue3(player, 2, ChipType.COIN);

		assertEquals(9, player.getCoinTotal());
		assertEquals(6, player.getNumValue1Coins());
		assertEquals(1, player.getNumValue3Coins());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidRemoveNumValue1CoinsNeg1() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		PlayerChipHandler.removeValue1(player, -1, ChipType.COIN);
		fail();
	}

	@Test(expected = InsufficientFundsException.class)
	public void testInsufficientFundsForRemoveValue1Coins() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		PlayerChipHandler.removeValue1(player, 4, ChipType.COIN);
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidRemoveNumValue3CoinsNeg1() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		PlayerChipHandler.removeValue3(player, -1, ChipType.COIN);
		fail();
	}

	@Test(expected = InsufficientFundsException.class)
	public void testInsufficientFundsForRemoveValue3Coins() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		PlayerChipHandler.removeValue3(player, 1, ChipType.COIN);
		fail();
	}

	@Test
	public void testAddInvalidNumValue1CoinsNeg1ErrorMessage() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		try {
			PlayerChipHandler.addValue1(player, -1, Chip.ChipType.COIN);
		} catch (IllegalArgumentException error) {
			String message = "Cannot add -1 value 1 chip(s)";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testAddInvalidNumValue1Coins47ErrorMessage() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		try {
			PlayerChipHandler.addValue1(player, 47, Chip.ChipType.COIN);
		} catch (IllegalArgumentException error) {
			String message = "Cannot add 47 value 1 chip(s)";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testRemove2Value1ConflictTokens() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
		PlayerChipHandler.addValue1(player, 5, ChipType.CONFLICTTOKEN);
		PlayerChipHandler.removeValue1(player, 2, ChipType.CONFLICTTOKEN);
		assertEquals(3, player.getNumValue1ConflictTokens());
		assertEquals(3, player.getConflictTotal());
	}

	@Test
	public void testRemove3Value1ConflictTokens() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
		PlayerChipHandler.addValue1(player, 5, ChipType.CONFLICTTOKEN);
		PlayerChipHandler.removeValue1(player, 3, ChipType.CONFLICTTOKEN);
		assertEquals(2, player.getNumValue1ConflictTokens());
		assertEquals(2, player.getConflictTotal());
	}

	@Test
	public void testRemoveAllValue1ConflictTokens() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
		PlayerChipHandler.addValue1(player, 5, ChipType.CONFLICTTOKEN);
		PlayerChipHandler.removeValue1(player, 5, ChipType.CONFLICTTOKEN);
		assertEquals(0, player.getNumValue1ConflictTokens());
		assertEquals(0, player.getConflictTotal());
	}

	@Test
	public void testRemoveInvalidNumberOfValue1ConflictTokens() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
		PlayerChipHandler.addValue1(player, 5, ChipType.CONFLICTTOKEN);
		try {
			PlayerChipHandler.removeValue1(player, 6, ChipType.CONFLICTTOKEN);
			fail();
		} catch (Exception e) {
			assertEquals("Player does not have 6 value 1 chip(s)", e.getMessage());
		}

	}

	@Test
	public void testAddInvalidNumValue3CoinsNeg1ErrorMessage() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		try {
			PlayerChipHandler.addValue3(player, -1, Chip.ChipType.COIN);
		} catch (IllegalArgumentException error) {
			String message = "Cannot add -1 value 3 chip(s)";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testInvalidRemoveNumValue1CoinsNeg1ErrorMessage() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		try {
			PlayerChipHandler.removeValue1(player, -1, ChipType.COIN);
		} catch (IllegalArgumentException error) {
			String message = "Cannot remove -1 value 1 chip(s)";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testInvalidRemoveNumValue1CoinsNeg2ErrorMessage() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		try {
			PlayerChipHandler.removeValue1(player, -2, ChipType.COIN);
		} catch (IllegalArgumentException error) {
			String message = "Cannot remove -2 value 1 chip(s)";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testInvalidRemoveNumValue3CoinsNeg1ErrorMessage() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		try {
			PlayerChipHandler.removeValue3(player, -1, ChipType.COIN);
		} catch (IllegalArgumentException error) {
			String message = "Cannot remove -1 value 3 chip(s)";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testInvalidRemoveNumValue3CoinsNeg2ErrorMessage() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		try {
			PlayerChipHandler.removeValue3(player, -2, ChipType.COIN);
		} catch (IllegalArgumentException error) {
			String message = "Cannot remove -2 value 3 chip(s)";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testInsufficientFundsForRemove4Value1CoinsErrorMessage() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		try {
			PlayerChipHandler.removeValue1(player, 4, ChipType.COIN);
		} catch (InsufficientFundsException error) {
			String message = "Player does not have 4 value 1 chip(s)";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testInsufficientFundsForRemove5Value1CoinsErrorMessage() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		try {
			PlayerChipHandler.removeValue1(player, 5, ChipType.COIN);
		} catch (InsufficientFundsException error) {
			String message = "Player does not have 5 value 1 chip(s)";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testInsufficientFundsForRemove1Value3CoinsErrorMessage() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		try {
			PlayerChipHandler.removeValue3(player, 1, ChipType.COIN);
			fail();
		} catch (InsufficientFundsException error) {
			String message = "Player does not have 1 value 3 chip(s)";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testInsufficientFundsForRemove2Value3CoinsErrorMessage() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);

		try {
			PlayerChipHandler.removeValue3(player, 2, ChipType.COIN);
			fail();
		} catch (InsufficientFundsException error) {
			String message = "Player does not have 2 value 3 chip(s)";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testRemove2Value3ConflictTokens() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
		PlayerChipHandler.addValue3(player, 5, ChipType.CONFLICTTOKEN);
		PlayerChipHandler.removeValue3(player, 2, ChipType.CONFLICTTOKEN);
		assertEquals(3, player.getNumValue3ConflictTokens());
		assertEquals(9, player.getConflictTotal());
	}

	@Test
	public void testRemove3Value3ConflictTokens() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
		PlayerChipHandler.addValue3(player, 5, ChipType.CONFLICTTOKEN);
		PlayerChipHandler.removeValue3(player, 3, ChipType.CONFLICTTOKEN);
		assertEquals(2, player.getNumValue3ConflictTokens());
		assertEquals(6, player.getConflictTotal());
	}

	@Test
	public void testRemoveAllValue3ConflictTokens() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
		PlayerChipHandler.addValue3(player, 5, ChipType.CONFLICTTOKEN);
		PlayerChipHandler.removeValue3(player, 5, ChipType.CONFLICTTOKEN);
		assertEquals(0, player.getNumValue3ConflictTokens());
		assertEquals(0, player.getConflictTotal());
	}

	@Test
	public void testRemoveInvalidAmountOfValue3ConflictTokens() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
		PlayerChipHandler.addValue3(player, 5, ChipType.CONFLICTTOKEN);
		try {
			PlayerChipHandler.removeValue3(player, 6, ChipType.CONFLICTTOKEN);
			fail();
		} catch (Exception e) {
			Assert.assertEquals("Player does not have 6 value 3 chip(s)", e.getMessage());
		}
	}

	@Test
	public void testRemoveTotalCoins() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
		PlayerChipHandler.removeTotalCoins(player, 2);

		assertEquals(1, player.getNumValue1Coins());
		assertEquals(1, player.getCoinTotal());
	}

	@Test
	public void testRemoveTotalCoinsEnoughValue1NotEnoughValue3() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
		PlayerChipHandler.removeTotalCoins(player, 3);

		assertEquals(0, player.getNumValue1Coins());
		assertEquals(0, player.getCoinTotal());
	}

	@Test
	public void testRemove3Value5Coins() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
		PlayerChipHandler.addValue5(player, 5, ChipType.COIN);
		PlayerChipHandler.removeValue5(player, 3, ChipType.COIN);
		Assert.assertEquals(2, player.getNumValue5Coins());
		Assert.assertEquals(13, player.getCoinTotal());
	}

	@Test
	public void testRemove2Value5Coins() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
		PlayerChipHandler.addValue5(player, 5, ChipType.COIN);
		PlayerChipHandler.removeValue5(player, 2, ChipType.COIN);
		Assert.assertEquals(3, player.getNumValue5Coins());
		Assert.assertEquals(18, player.getCoinTotal());
	}

	@Test
	public void testRemoveInvalidAmountOfValue5Coins() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
		PlayerChipHandler.addValue5(player, 5, ChipType.COIN);
		try {
			PlayerChipHandler.removeValue5(player, 7, ChipType.COIN);
			fail();
		} catch (Exception e) {
			Assert.assertEquals("Player does not have 7 value 5 chip(s)", e.getMessage());
		}
	}

	public void testRemove3Value5ConflictTokens() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
		PlayerChipHandler.addValue5(player, 5, ChipType.CONFLICTTOKEN);
		PlayerChipHandler.removeValue5(player, 3, ChipType.CONFLICTTOKEN);
		Assert.assertEquals(2, player.getNumValue5ConflictTokens());
		Assert.assertEquals(10, player.getConflictTotal());
	}

	public void testRemove2Value5ConflictTokens() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
		PlayerChipHandler.addValue5(player, 5, ChipType.CONFLICTTOKEN);
		PlayerChipHandler.removeValue5(player, 3, ChipType.CONFLICTTOKEN);
		assertEquals(3, player.getNumValue5ConflictTokens());
		assertEquals(15, player.getConflictTotal());
	}

	@Test
	public void testRemoveInvalidAmountOfValue5ConflictTokens() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
		PlayerChipHandler.addValue5(player, 5, ChipType.CONFLICTTOKEN);
		try {
			PlayerChipHandler.removeValue5(player, 7, ChipType.CONFLICTTOKEN);
			fail();
		} catch (Exception e) {
			Assert.assertEquals("Player does not have 7 value 5 chip(s)", e.getMessage());
		}
	}

	@Test
	public void testRemoveAllValue5ConflictTokens() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
		PlayerChipHandler.addValue5(player, 5, ChipType.CONFLICTTOKEN);
		PlayerChipHandler.removeValue5(player, 5, ChipType.CONFLICTTOKEN);
		Assert.assertEquals(0, player.getNumValue5ConflictTokens());
		Assert.assertEquals(0, player.getConflictTotal());
	}
	@Test
	public void testAdd1Value1ConflictTokens() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
		PlayerChipHandler.addValue1(player, 1, Chip.ChipType.CONFLICTTOKEN);
		assertEquals(1, player.getNumValue1ConflictTokens());
		assertEquals(1, player.getConflictTotal());
	}

	@Test
	public void testAddValue3ConflictTokens() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
		PlayerChipHandler.addValue3(player, 1, Chip.ChipType.CONFLICTTOKEN);
		assertEquals(1, player.getNumValue3ConflictTokens());
		assertEquals(0, player.getNumValue3Coins());
		assertEquals(3, player.getConflictTotal());
	}

	@Test
	public void testAddValueNegConflictTokens() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
		PlayerChipHandler.addValueNeg1(player, 1, Chip.ChipType.CONFLICTTOKEN);
		assertEquals(1, player.getNumValueNeg1ConflictTokens());
		assertEquals(0, player.getNumValue3ConflictTokens());
		assertEquals(-1, player.getConflictTotal());
	}

	@Test
	public void testAddInvalidNeg1CoinsErrorMessage() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
		try {
			PlayerChipHandler.addValueNeg1(player, 1, Chip.ChipType.COIN);
			fail();
		} catch (IllegalArgumentException error) {
			assertEquals("Cannot have a negative 1 coin value", error.getMessage());
		}
	}

	@Test
	public void testAddValue5Coins() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
		PlayerChipHandler.addValue5(player, 3, Chip.ChipType.COIN);
		Assert.assertEquals(3, player.getNumValue5Coins());
		Assert.assertEquals(18, player.getCoinTotal());
	}

	@Test
	public void testAddValue5Coins2() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
		PlayerChipHandler.addValue5(player, 5, Chip.ChipType.COIN);
		Assert.assertEquals(5, player.getNumValue5Coins());
		Assert.assertEquals(28, player.getCoinTotal());
	}

	@Test
	public void testAddValue5CoinsOverMax() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
		try {
			PlayerChipHandler.addValue5(player, 21, Chip.ChipType.COIN);
			fail();
		} catch (IllegalArgumentException error) {
			assertEquals("Cannot add 21 value 5 chip(s)", error.getMessage());
		}
	}

	@Test
	public void testAddValue5ConflictTokens() {
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
		PlayerChipHandler.addValue5(player, 3, Chip.ChipType.CONFLICTTOKEN);
		assertEquals(3, player.getNumValue5ConflictTokens());
		assertEquals(15, player.getConflictTotal());
	}

	@Test
	public void testAddValue5ConflictTokens2(){
		Player player = new Player("Jane Doe", WonderType.COLOSSUS);
		PlayerChipHandler.addValue5(player, 5, Chip.ChipType.CONFLICTTOKEN);
		assertEquals(5, player.getNumValue5ConflictTokens());
		assertEquals(25, player.getConflictTotal());
	}
}
