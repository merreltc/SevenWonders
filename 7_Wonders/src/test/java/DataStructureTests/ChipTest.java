package DataStructureTests;

import static org.junit.Assert.*;

import org.junit.Test;

public class ChipTest {

	@Test
	public void testDefaultCoin() {
		Chip coin = new Coin();
		
		assertEquals(1, coin.getValue());
	}

}
