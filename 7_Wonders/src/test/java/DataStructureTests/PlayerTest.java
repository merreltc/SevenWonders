package DataStructureTests;

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
	public void testSingleTrade() {
		Player player1 = new Player();
		Player player2 = new Player();

		TradeHandler tradeHandler = new TradeHandler();
		tradeHandler.tradeFromTo(player1, player2, 1);

		assertEquals(4, player2.getCoinTotal());
		assertEquals(4, player2.getNumValue1Coins());
		assertEquals(2, player1.getCoinTotal());
		assertEquals(2, player1.getNumValue1Coins());

	}

	@Test
	public void testMultiTrades() {
		Player player1 = new Player();
		Player player2 = new Player();

		TradeHandler tradeHandler = new TradeHandler();
		tradeHandler.tradeFromTo(player1, player2, 2);
		tradeHandler.tradeFromTo(player1, player2, 1);

		assertEquals(6, player2.getCoinTotal());
		assertEquals(6, player2.getNumValue1Coins());
		assertEquals(0, player1.getCoinTotal());
		assertEquals(0, player1.getNumValue1Coins());

	}

	@Test(expected = InsufficientFundsException.class)
	public void testInvalidTrade() {
		Player player1 = new Player();
		Player player2 = new Player();
		
		TradeHandler tradeHandler = new TradeHandler();
		tradeHandler.tradeFromTo(player1, player2, 4);
		fail();
	}
}
