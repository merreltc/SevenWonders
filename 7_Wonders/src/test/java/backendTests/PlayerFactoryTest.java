package backendTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.easymock.EasyMock;
import org.junit.Test;

import backend.PlayerFactory;
import dataStructures.Player;

public class PlayerFactoryTest {

	@Test
	public void testGetPlayer() {
		Player player = EasyMock.createStrictMock(Player.class);
		replayExpectedGetPlayer(1, player);

		PlayerFactory factory = new PlayerFactory();
		verifyActualGetPlayer(1, player, factory);
	}

	@Test
	public void testGetPlayerMin() {
		Player player = EasyMock.createStrictMock(Player.class);
		replayExpectedGetPlayer(3, player);

		PlayerFactory factory = new PlayerFactory();
		verifyActualGetPlayer(3, player, factory);
	}

	@Test
	public void testGetPlayerMax() {
		Player player = EasyMock.createStrictMock(Player.class);
		replayExpectedGetPlayer(7, player);

		PlayerFactory factory = new PlayerFactory();
		verifyActualGetPlayer(7, player, factory);
	}

	private void replayExpectedGetPlayer(int num, Player player) {
		for (int i = 1; i <= num; i++) {
			EasyMock.expect(player.getName()).andReturn("Player " + i);
		}
		EasyMock.replay(player);
	}

	private void verifyActualGetPlayer(int num, Player player, PlayerFactory factory) {
		for (int i = 1; i <= num; i++) {
			assertTrue(factory.getPlayer("Player " + i).equals(player));
		}
		EasyMock.verify(player);
	}
}