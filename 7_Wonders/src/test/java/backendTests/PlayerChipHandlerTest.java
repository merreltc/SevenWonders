package backendTests;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import backend.handlers.PlayerChipHandler;
import dataStructures.gameMaterials.Wonder;
import dataStructures.playerData.Chip;
import dataStructures.playerData.Player;
import dataStructures.playerData.Chip.ChipType;
import dataStructures.playerData.Chip.ChipValue;
import exceptions.InsufficientFundsException;

public class PlayerChipHandlerTest {

	@Test
	public void testAddValue1Coins() {
		Player player = createMockedPlayer();

		PlayerChipHandler.addValue1(player, 1, Chip.ChipType.COIN);
		assertEquals(4, player.getCoinTotal());
		assertEquals(4, (int) player.getCoins().get(ChipValue.ONE));

		PlayerChipHandler.addValue1(player, 3, Chip.ChipType.COIN);
		assertEquals(7, player.getCoinTotal());
		assertEquals(7, (int) player.getCoins().get(ChipValue.ONE));
	}

	@Test
	public void testAddValue3Coins() {
		Player player = createMockedPlayer();

		PlayerChipHandler.addValue3(player, 1, Chip.ChipType.COIN);
		assertEquals(6, player.getCoinTotal());
		assertEquals(1, (int) player.getCoins().get(ChipValue.THREE));

		PlayerChipHandler.addValue3(player, 3, Chip.ChipType.COIN);
		assertEquals(15, player.getCoinTotal());
		assertEquals(4, (int) player.getCoins().get(ChipValue.THREE));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddInvalidNumValue1CoinsNeg1() {
		Player player = createMockedPlayer();

		PlayerChipHandler.addValue1(player, -1, Chip.ChipType.COIN);
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddInvalidNumValue1Coins47() {
		Player player = createMockedPlayer();

		PlayerChipHandler.addValue1(player, 47, Chip.ChipType.COIN);
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddInvalidNumValue3CoinsNeg1() {
		Player player = createMockedPlayer();

		PlayerChipHandler.addValue3(player, -1, Chip.ChipType.COIN);
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddInvalidNumValue3Coins25() {
		Player player = createMockedPlayer();

		PlayerChipHandler.addValue3(player, 25, Chip.ChipType.COIN);
		fail();
	}

	@Test
	public void testRemoveValidNumValue1Coins() {
		Player player = createMockedPlayer();

		PlayerChipHandler.removeValue1(player, 1, ChipType.COIN);
		assertEquals(2, player.getCoinTotal());
		assertEquals(2, (int) player.getCoins().get(ChipValue.ONE));

		PlayerChipHandler.removeValue1(player, 2, ChipType.COIN);
		assertEquals(0, player.getCoinTotal());
		assertEquals(0, (int) player.getCoins().get(ChipValue.ONE));
	}

	@Test
	public void testRemoveValidNumValue3Coins() {
		Player player = createMockedPlayer();

		PlayerChipHandler.addValue3(player, 1, Chip.ChipType.COIN);
		PlayerChipHandler.removeValue3(player, 1, ChipType.COIN);
		assertEquals(3, player.getCoinTotal());
		assertEquals(0, (int) player.getCoins().get(ChipValue.THREE));
	}

	@Test
	public void testMultiAddAndRemoveCoins() {
		Player player = createMockedPlayer();

		PlayerChipHandler.addValue1(player, 5, Chip.ChipType.COIN);
		PlayerChipHandler.addValue3(player, 3, Chip.ChipType.COIN);

		PlayerChipHandler.removeValue1(player, 2, ChipType.COIN);
		PlayerChipHandler.removeValue3(player, 2, ChipType.COIN);

		assertEquals(9, player.getCoinTotal());
		assertEquals(6, (int) player.getCoins().get(ChipValue.ONE));
		assertEquals(1, (int) player.getCoins().get(ChipValue.THREE));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidRemoveNumValue1CoinsNeg1() {
		Player player = createMockedPlayer();

		PlayerChipHandler.removeValue1(player, -1, ChipType.COIN);
		fail();
	}

	@Test(expected = InsufficientFundsException.class)
	public void testInsufficientFundsForRemoveValue1Coins() {
		Player player = createMockedPlayer();

		PlayerChipHandler.removeValue1(player, 4, ChipType.COIN);
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidRemoveNumValue3CoinsNeg1() {
		Player player = createMockedPlayer();

		PlayerChipHandler.removeValue3(player, -1, ChipType.COIN);
		fail();
	}

	@Test(expected = InsufficientFundsException.class)
	public void testInsufficientFundsForRemoveValue3Coins() {
		Player player = createMockedPlayer();

		PlayerChipHandler.removeValue3(player, 1, ChipType.COIN);
		fail();
	}

	@Test
	public void testAddInvalidNumValue1CoinsNeg1ErrorMessage() {
		Player player = createMockedPlayer();

		try {
			PlayerChipHandler.addValue1(player, -1, Chip.ChipType.COIN);
		} catch (IllegalArgumentException error) {
			String message = "Cannot add -1 value 1 chip(s)";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testAddInvalidNumValue1Coins47ErrorMessage() {
		Player player = createMockedPlayer();

		try {
			PlayerChipHandler.addValue1(player, 47, Chip.ChipType.COIN);
		} catch (IllegalArgumentException error) {
			String message = "Cannot add 47 value 1 chip(s)";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testRemove2Value1ConflictTokens() {
		Player player = createMockedPlayer();
		PlayerChipHandler.addValue1(player, 5, ChipType.CONFLICTTOKEN);
		PlayerChipHandler.removeValue1(player, 2, ChipType.CONFLICTTOKEN);
		assertEquals(3, (int) player.getConflictTokens().get(ChipValue.ONE));
		assertEquals(3, player.getConflictTotal());
	}

	@Test
	public void testRemove3Value1ConflictTokens() {
		Player player = createMockedPlayer();
		PlayerChipHandler.addValue1(player, 5, ChipType.CONFLICTTOKEN);
		PlayerChipHandler.removeValue1(player, 3, ChipType.CONFLICTTOKEN);
		assertEquals(2, (int) player.getConflictTokens().get(ChipValue.ONE));
		assertEquals(2, player.getConflictTotal());
	}

	@Test
	public void testRemoveAllValue1ConflictTokens() {
		Player player = createMockedPlayer();
		PlayerChipHandler.addValue1(player, 5, ChipType.CONFLICTTOKEN);
		PlayerChipHandler.removeValue1(player, 5, ChipType.CONFLICTTOKEN);
		assertEquals(0, (int) player.getConflictTokens().get(ChipValue.ONE));
		assertEquals(0, player.getConflictTotal());
	}

	@Test
	public void testRemoveInvalidNumberOfValue1ConflictTokens() {
		Player player = createMockedPlayer();
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
		Player player = createMockedPlayer();

		try {
			PlayerChipHandler.addValue3(player, -1, Chip.ChipType.COIN);
		} catch (IllegalArgumentException error) {
			String message = "Cannot add -1 value 3 chip(s)";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testInvalidRemoveNumValue1CoinsNeg1ErrorMessage() {
		Player player = createMockedPlayer();

		try {
			PlayerChipHandler.removeValue1(player, -1, ChipType.COIN);
		} catch (IllegalArgumentException error) {
			String message = "Cannot remove -1 value 1 chip(s)";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testInvalidRemoveNumValue1CoinsNeg2ErrorMessage() {
		Player player = createMockedPlayer();

		try {
			PlayerChipHandler.removeValue1(player, -2, ChipType.COIN);
		} catch (IllegalArgumentException error) {
			String message = "Cannot remove -2 value 1 chip(s)";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testInvalidRemoveNumValue3CoinsNeg1ErrorMessage() {
		Player player = createMockedPlayer();

		try {
			PlayerChipHandler.removeValue3(player, -1, ChipType.COIN);
		} catch (IllegalArgumentException error) {
			String message = "Cannot remove -1 value 3 chip(s)";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testInvalidRemoveNumValue3CoinsNeg2ErrorMessage() {
		Player player = createMockedPlayer();

		try {
			PlayerChipHandler.removeValue3(player, -2, ChipType.COIN);
		} catch (IllegalArgumentException error) {
			String message = "Cannot remove -2 value 3 chip(s)";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testInsufficientFundsForRemove4Value1CoinsErrorMessage() {
		Player player = createMockedPlayer();

		try {
			PlayerChipHandler.removeValue1(player, 4, ChipType.COIN);
		} catch (InsufficientFundsException error) {
			String message = "Player does not have 4 value 1 chip(s)";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testInsufficientFundsForRemove5Value1CoinsErrorMessage() {
		Player player = createMockedPlayer();

		try {
			PlayerChipHandler.removeValue1(player, 5, ChipType.COIN);
		} catch (InsufficientFundsException error) {
			String message = "Player does not have 5 value 1 chip(s)";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testInsufficientFundsForRemove1Value3CoinsErrorMessage() {
		Player player = createMockedPlayer();

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
		Player player = createMockedPlayer();

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
		Player player = createMockedPlayer();
		PlayerChipHandler.addValue3(player, 5, ChipType.CONFLICTTOKEN);
		PlayerChipHandler.removeValue3(player, 2, ChipType.CONFLICTTOKEN);
		assertEquals(3, (int) player.getConflictTokens().get(ChipValue.THREE));
		assertEquals(9, player.getConflictTotal());
	}

	@Test
	public void testRemove3Value3ConflictTokens() {
		Player player = createMockedPlayer();
		PlayerChipHandler.addValue3(player, 5, ChipType.CONFLICTTOKEN);
		PlayerChipHandler.removeValue3(player, 3, ChipType.CONFLICTTOKEN);
		assertEquals(2, (int) player.getConflictTokens().get(ChipValue.THREE));
		assertEquals(6, player.getConflictTotal());
	}

	@Test
	public void testRemoveAllValue3ConflictTokens() {
		Player player = createMockedPlayer();
		PlayerChipHandler.addValue3(player, 5, ChipType.CONFLICTTOKEN);
		PlayerChipHandler.removeValue3(player, 5, ChipType.CONFLICTTOKEN);
		assertEquals(0, (int) player.getConflictTokens().get(ChipValue.THREE));
		assertEquals(0, player.getConflictTotal());
	}

	@Test
	public void testRemoveInvalidAmountOfValue3ConflictTokens() {
		Player player = createMockedPlayer();
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
		Player player = createMockedPlayer();
		PlayerChipHandler.removeTotalCoins(player, 2);

		assertEquals(1, (int) player.getCoins().get(ChipValue.ONE));
		assertEquals(1, player.getCoinTotal());
	}

	@Test
	public void testRemoveTotalCoinsEnoughValue1NotEnoughValue3() {
		Player player = createMockedPlayer();
		PlayerChipHandler.removeTotalCoins(player, 3);

		assertEquals(0, (int) player.getCoins().get(ChipValue.ONE));
		assertEquals(0, player.getCoinTotal());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRemove2Value5Coins() {
		Player player = createMockedPlayer();
		PlayerChipHandler.removeValue5(player, 5, ChipType.COIN);
		fail();
	}

	public void testRemove3Value5ConflictTokens() {
		Player player = createMockedPlayer();
		PlayerChipHandler.addValue5(player, 5, ChipType.CONFLICTTOKEN);
		PlayerChipHandler.removeValue5(player, 3, ChipType.CONFLICTTOKEN);
		Assert.assertEquals(2, (int) player.getConflictTokens().get(ChipValue.FIVE));
		Assert.assertEquals(10, player.getConflictTotal());
	}

	public void testRemove2Value5ConflictTokens() {
		Player player = createMockedPlayer();
		PlayerChipHandler.addValue5(player, 5, ChipType.CONFLICTTOKEN);
		PlayerChipHandler.removeValue5(player, 3, ChipType.CONFLICTTOKEN);
		assertEquals(3, (int) player.getConflictTokens().get(ChipValue.FIVE));
		assertEquals(15, player.getConflictTotal());
	}

	@Test
	public void testRemoveInvalidAmountOfValue5ConflictTokens() {
		Player player = createMockedPlayer();
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
		Player player = createMockedPlayer();
		PlayerChipHandler.addValue5(player, 5, ChipType.CONFLICTTOKEN);
		PlayerChipHandler.removeValue5(player, 5, ChipType.CONFLICTTOKEN);
		Assert.assertEquals(0, (int) player.getConflictTokens().get(ChipValue.FIVE));
		Assert.assertEquals(0, player.getConflictTotal());
	}
	@Test
	public void testAdd1Value1ConflictTokens() {
		Player player = createMockedPlayer();
		PlayerChipHandler.addValue1(player, 1, Chip.ChipType.CONFLICTTOKEN);
		assertEquals(1, (int) player.getConflictTokens().get(ChipValue.ONE));
		assertEquals(1, player.getConflictTotal());
	}

	@Test
	public void testAddValue3ConflictTokens() {
		Player player = createMockedPlayer();
		PlayerChipHandler.addValue3(player, 1, Chip.ChipType.CONFLICTTOKEN);
		assertEquals(1, (int) player.getConflictTokens().get(ChipValue.THREE));
		assertEquals(0, (int) player.getCoins().get(ChipValue.THREE));
		assertEquals(3, player.getConflictTotal());
	}

	@Test
	public void testAddValueNegConflictTokens() {
		Player player = createMockedPlayer();
		PlayerChipHandler.addValueNeg1(player, 1, Chip.ChipType.CONFLICTTOKEN);
		assertEquals(1, (int) player.getConflictTokens().get(ChipValue.NEG1));
		assertEquals(0, (int) player.getConflictTokens().get(ChipValue.THREE));
		assertEquals(-1, player.getConflictTotal());
	}

	@Test
	public void testAddInvalidNeg1CoinsErrorMessage() {
		Player player = createMockedPlayer();
		try {
			PlayerChipHandler.addValueNeg1(player, 1, Chip.ChipType.COIN);
			fail();
		} catch (IllegalArgumentException error) {
			assertEquals("Cannot have a negative 1 coin value", error.getMessage());
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddValue5Coins() {
		Player player = createMockedPlayer();
		PlayerChipHandler.addValue5(player, 3, Chip.ChipType.COIN);
		fail();
	}

	@Test
	public void testAddValue5ConflictTokens() {
		Player player = createMockedPlayer();
		PlayerChipHandler.addValue5(player, 3, Chip.ChipType.CONFLICTTOKEN);
		assertEquals(3, (int) player.getConflictTokens().get(ChipValue.FIVE));
		assertEquals(15, player.getConflictTotal());
	}

	@Test
	public void testAddValue5ConflictTokens2(){
		Player player = createMockedPlayer();
		PlayerChipHandler.addValue5(player, 5, Chip.ChipType.CONFLICTTOKEN);
		assertEquals(5, (int) player.getConflictTokens().get(ChipValue.FIVE));
		assertEquals(25, player.getConflictTotal());
	}
	
	private Player createMockedPlayer() {
		Wonder wonder = EasyMock.createStrictMock(Wonder.class);
		return EasyMock.partialMockBuilder(Player.class).withConstructor("Jane Doe", wonder).createMock();
	}
}
