package dataStructureTests;

import static org.junit.Assert.*;

import org.junit.Test;

import dataStructures.Chip;
import dataStructures.Coin;
import dataStructures.ConflictToken;

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
		new Coin(-1);
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidCoin0() {
		new Coin(0);
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidCoin2() {
		new Coin(2);
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidCoin4() {
		new Coin(4);
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidCoin5() {
		new Coin(5);
		fail();
	}

	@Test
	public void testDefaultConflictToken() {
		Chip conflictToken = new ConflictToken();

		assertEquals(1, conflictToken.getValue());
	}

	@Test
	public void testNonDefaultConflictTokens() {
		Chip conTokenNeg1 = new ConflictToken(-1);
		assertEquals(-1, conTokenNeg1.getValue());

		Chip conTokenValue1 = new ConflictToken(1);
		assertEquals(1, conTokenValue1.getValue());

		Chip conTokenValue3 = new ConflictToken(3);
		assertEquals(3, conTokenValue3.getValue());

		Chip conTokenValue5 = new ConflictToken(5);
		assertEquals(5, conTokenValue5.getValue());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidConflictTokenNeg2() {
		new ConflictToken(-2);
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidConflictToken0() {
		new ConflictToken(0);
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidConflictToken2() {
		new ConflictToken(2);
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidConflictToken4() {
		new ConflictToken(4);
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidConflictToken6() {
		new ConflictToken(6);
		fail();
	}

	@Test
	public void testInvalidCoinNeg1ErrorMessage() {
		try {
			new Coin(-1);
		} catch (IllegalArgumentException error) {
			String message = "Cannot have a coin whose's value is -1";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testInvalidCoin0ErrorMessage() {
		try {
			new Coin(0);
		} catch (IllegalArgumentException error) {
			String message = "Cannot have a coin whose's value is 0";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testInvalidCoin2ErrorMessage() {
		try {
			new Coin(2);
		} catch (IllegalArgumentException error) {
			String message = "Cannot have a coin whose's value is 2";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testInvalidCoin4ErrorMessage() {
		try {
			new Coin(4);
		} catch (IllegalArgumentException error) {
			String message = "Cannot have a coin whose's value is 4";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testInvalidConflictTokenNeg2ErrorMessage() {
		try {
			new ConflictToken(-2);
		} catch (IllegalArgumentException error) {
			String message = "Cannot have a conflict token whose's value is -2";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testInvalidConflictToken0ErrorMessage() {
		try {
			new ConflictToken(0);
		} catch (IllegalArgumentException error) {
			String message = "Cannot have a conflict token whose's value is 0";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testInvalidConflictToken2ErrorMessage() {
		try {
			new ConflictToken(2);
		} catch (IllegalArgumentException error) {
			String message = "Cannot have a conflict token whose's value is 2";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testInvalidConflictToken4ErrorMessage() {
		try {
			new ConflictToken();
		} catch (IllegalArgumentException error) {
			String message = "Cannot have a conflict token whose's value is 4";
			assertEquals(message, error.getMessage());
		}
	}

	@Test
	public void testInvalidConflictToken6ErrorMessage() {
		try {
			new ConflictToken(6);
		} catch (IllegalArgumentException error) {
			String message = "Cannot have a conflict token whose's value is 6";
			assertEquals(message, error.getMessage());
		}
	}
	
	@Test
	public void testInvalidConflictToken7ErrorMessage() {
		try {
			new ConflictToken(7);
		} catch (IllegalArgumentException error) {
			String message = "Cannot have a conflict token whose's value is 7";
			assertEquals(message, error.getMessage());
		}
	}
}
