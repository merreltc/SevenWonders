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

}
