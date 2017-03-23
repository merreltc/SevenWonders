package DataStructureTests;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {

	@Test
	public void testDefaultPlayer() {
		Player player = new Player();
		
		assertEquals(3, player.getCoinTotal());
		assertEquals(0, player.getConflictTotal());
		assertEquals("Jane Doe", player.getName());
	}

}
