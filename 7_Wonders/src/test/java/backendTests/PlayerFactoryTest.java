package backendTests;

import org.easymock.EasyMock;
import org.junit.Test;

import backend.PlayerFactory;
import dataStructures.Player;

public class PlayerFactoryTest {

	@Test
	public void testGetPlayer() {
		Player player = EasyMock.mock(Player.class);
		PlayerFactory factory = EasyMock.partialMockBuilder(PlayerFactory.class).addMockedMethod("getPlayer").createMock();
		
		EasyMock.expect(factory.getPlayer("Name")).andReturn(player);
		EasyMock.replay(player, factory);
		
		factory.getPlayer("Name");
		EasyMock.verify(player, factory);
	}

}