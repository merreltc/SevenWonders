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
	public void testInvalidCoin() {
		Chip coinValueNeg1 = new Coin(-1);
		fail();
	}

}
