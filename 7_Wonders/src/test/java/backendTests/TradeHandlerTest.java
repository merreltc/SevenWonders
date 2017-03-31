package backendTests;

import static org.junit.Assert.*;

import org.junit.Test;

import backend.TradeHandler;
import dataStructures.Player;
import exceptions.InsufficientFundsException;

public class TradeHandlerTest {
	@Test
	public void testSingleTradeValue1Coins() {
		Player player1 = new Player();
		Player player2 = new Player();

		TradeHandler.tradeFromToValue1(player1, player2, 1);

		assertEquals(4, player2.getCoinTotal());
		assertEquals(4, player2.getNumValue1Coins());
		assertEquals(2, player1.getCoinTotal());
		assertEquals(2, player1.getNumValue1Coins());

	}

	@Test
	public void testMultiTradesValue1Coins() {
		Player player1 = new Player();
		Player player2 = new Player();

		TradeHandler.tradeFromToValue1(player1, player2, 2);
		TradeHandler.tradeFromToValue1(player1, player2, 1);

		assertEquals(6, player2.getCoinTotal());
		assertEquals(6, player2.getNumValue1Coins());
		assertEquals(0, player1.getCoinTotal());
		assertEquals(0, player1.getNumValue1Coins());

	}

	@Test(expected = InsufficientFundsException.class)
	public void testInvalidTradeValue1Coin() {
		Player player1 = new Player();
		Player player2 = new Player();

		TradeHandler.tradeFromToValue1(player1, player2, 4);
		fail();
	}

	@Test
	public void testSingleTradeValue3Coins() {
		Player player1 = new Player();
		Player player2 = new Player();

		player1.addValue3(1);
		TradeHandler.tradeFromToValue3(player1, player2, 1);

		assertEquals(6, player2.getCoinTotal());
		assertEquals(1, player2.getNumValue3Coins());
		assertEquals(3, player1.getCoinTotal());
		assertEquals(0, player1.getNumValue3Coins());
	}

	@Test
	public void testMultiTradesValue3Coins() {
		Player player1 = new Player();
		Player player2 = new Player();

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
		Player player1 = new Player();
		Player player2 = new Player();

		TradeHandler.tradeFromToValue3(player1, player2, 1);
		fail();
	}

	@Test
	public void testMultiTrades() {
		Player player1 = new Player();
		Player player2 = new Player();

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
		Player player1 = new Player();
		Player player2 = new Player();

		player1.addValue3(2);

		TradeHandler.tradeFromTo(player1, player2, 4);

		assertEquals(5, player1.getCoinTotal());
		assertEquals(2, player1.getNumValue1Coins());
		assertEquals(1, player1.getNumValue3Coins());

		assertEquals(7, player2.getCoinTotal());
		assertEquals(4, player2.getNumValue1Coins());
		assertEquals(1, player2.getNumValue3Coins());
	}
	private static int playerNum;

	@Test
	public void testTradeFromToMultiValue1SingleValue3() {
		Player player1 = new Player();
		Player player2 = new Player();

		player1.addValue3(2);

		TradeHandler.tradeFromTo(player1, player2, 5);

		assertEquals(4, player1.getCoinTotal());
		assertEquals(1, player1.getNumValue1Coins());
		assertEquals(1, player1.getNumValue3Coins());

		assertEquals(8, player2.getCoinTotal());
		assertEquals(5, player2.getNumValue1Coins());
		assertEquals(1, player2.getNumValue3Coins());
	}

	@Test
	public void testTradeFromToSingleValue1MultiValue3() {
		Player player1 = new Player();
		Player player2 = new Player();

		player1.addValue3(2);

		TradeHandler.tradeFromTo(player1, player2, 7);

		assertEquals(2, player1.getCoinTotal());
		assertEquals(2, player1.getNumValue1Coins());
		assertEquals(0, player1.getNumValue3Coins());

		assertEquals(10, player2.getCoinTotal());
		assertEquals(4, player2.getNumValue1Coins());
		assertEquals(2, player2.getNumValue3Coins());
	}

	@Test
	public void testTradeFromToMultiValue1MultiValue3() {
		Player player1 = new Player();
		Player player2 = new Player();

		player1.addValue3(2);

		TradeHandler.tradeFromTo(player1, player2, 8);

		assertEquals(1, player1.getCoinTotal());
		assertEquals(1, player1.getNumValue1Coins());
		assertEquals(0, player1.getNumValue3Coins());

		assertEquals(11, player2.getCoinTotal());
		assertEquals(5, player2.getNumValue1Coins());
		assertEquals(2, player2.getNumValue3Coins());
	}

	@Test
	public void testTradeFromToSufficientValue1NoValue3() {
		Player player1 = new Player();
		Player player2 = new Player();

		TradeHandler.tradeFromTo(player1, player2, 3);

		assertEquals(0, player1.getCoinTotal());
		assertEquals(0, player1.getNumValue1Coins());
		assertEquals(0, player1.getNumValue3Coins());

		assertEquals(6, player2.getCoinTotal());
		assertEquals(6, player2.getNumValue1Coins());
		assertEquals(0, player2.getNumValue3Coins());
	}

	@Test
	public void testTradeFromToGreaterValue1ThanValue3() {
		Player player1 = new Player();
		Player player2 = new Player();

		player1.addValue3(1);
		TradeHandler.tradeFromTo(player1, player2, 6);

		assertEquals(0, player1.getCoinTotal());
		assertEquals(0, player1.getNumValue1Coins());
		assertEquals(0, player1.getNumValue3Coins());

		assertEquals(9, player2.getCoinTotal());
		assertEquals(6, player2.getNumValue1Coins());
		assertEquals(1, player2.getNumValue3Coins());
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
