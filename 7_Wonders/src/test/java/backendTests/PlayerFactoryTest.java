package backendTests;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Test;

import backend.PlayerFactory;
import dataStructures.Player;
import exceptions.NoMorePlayersException;

public class PlayerFactoryTest {

	@Test
	public void testGetPlayer() {
		Player player = EasyMock.createStrictMock(Player.class);
		EasyMock.expect(player.getName()).andReturn("Jane Doe");
		EasyMock.replay(player);
		
		PlayerFactory factory = new PlayerFactory();
		assertTrue(factory.getPlayer("Jane Doe").equals(player));
		
		EasyMock.verify(player);
	}
}