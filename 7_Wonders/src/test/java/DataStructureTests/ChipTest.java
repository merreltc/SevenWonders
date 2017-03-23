package DataStructureTests;

import static org.junit.Assert.*;

import org.junit.Test;

import dataStructures.Chip;
import dataStructures.Coin;

public class ChipTest {

	@Test
	public void testDefaultCoin() {
		Chip coin = new Coin();
		
		assertEquals(1, coin.getValue());
	}
	
	@Test
	public void testNonDefaultCoin() {
		Chip coinValue1 = new Coin(1);
		assertEquals(1, coinValue1.getValue());
		
		Chip coinValue3 = new Coin(3);
		assertEquals(3, coinValue3.getValue());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidCoinNeg1() {
		Chip coinValueNeg1 = new Coin(-1);
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidCoin0() {
		Chip coinValue0 = new Coin(0);
		fail();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidCoin2() {
		Chip coinValue2 = new Coin(2);
		fail();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidCoin4() {
		Chip coinValue4 = new Coin(4);
		fail();
	}
}
