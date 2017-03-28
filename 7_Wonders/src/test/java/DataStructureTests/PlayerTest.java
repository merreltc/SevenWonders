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

		try{
		player.addValue1(-1);
		}catch(IllegalArgumentException error){
			String message = "Cannot add -1 value 1 coins";
			assertEquals(message, error.getMessage());
		}
	}
	
	@Test
	public void testAddInvalidNumValue1Coins47ErrorMessage() {
		Player player = new Player();

		try{
		player.addValue1(47);
		}catch(IllegalArgumentException error){
			String message = "Cannot add 47 value 1 coins";
			assertEquals(message, error.getMessage());
		}
	}
	
	@Test
	public void testAddInvalidNumValue3CoinsNeg1ErrorMessage() {
		Player player = new Player();

		try{
		player.addValue3(-1);
		}catch(IllegalArgumentException error){
			String message = "Cannot add -1 value 3 coins";
			assertEquals(message, error.getMessage());
		}
	}
	
	@Test
	public void testInvalidRemoveNumValue1CoinsNeg1ErrorMessage() {
		Player player = new Player();
		
		try{
		player.removeValue1(-1);
		}catch (IllegalArgumentException error){
			String message = "Cannot remove -1 value 1 coins";
			assertEquals(message, error.getMessage());
		}
	}
	
	@Test
	public void testInvalidRemoveNumValue1CoinsNeg2ErrorMessage() {
		Player player = new Player();
		
		try{
		player.removeValue1(-2);
		}catch (IllegalArgumentException error){
			String message = "Cannot remove -2 value 1 coins";
			assertEquals(message, error.getMessage());
		}
	}
	
	@Test
	public void testInvalidRemoveNumValue3CoinsNeg1ErrorMessage() {
		Player player = new Player();
		
		try{
		player.removeValue3(-1);
		}catch (IllegalArgumentException error){
			String message = "Cannot remove -1 value 3 coins";
			assertEquals(message, error.getMessage());
		}
	}
	
	@Test
	public void testInvalidRemoveNumValue3CoinsNeg2ErrorMessage() {
		Player player = new Player();
		
		try{
		player.removeValue3(-2);
		}catch (IllegalArgumentException error){
			String message = "Cannot remove -2 value 3 coins";
			assertEquals(message, error.getMessage());
		}
	}
}
