package backendTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.easymock.EasyMock;
import org.junit.Test;

import backend.factories.WonderFactory;
import constants.GeneralEnums.GameMode;
import constants.GeneralEnums.Side;
import dataStructures.gameMaterials.Wonder;
import exceptions.NoMoreWondersException;

public class WonderFactoryTest {

	@Test
	public void testGetWonderOnce() {
		WonderFactory factory = EasyMock.partialMockBuilder(WonderFactory.class).addMockedMethod("getRandomIndex")
				.addMockedMethod("removeAtIndex").withConstructor(GameMode.EASY).createMock();

		expectAndReplayIndexAndRemove(1, factory);

		factory.getWonder();
		EasyMock.verify(factory);
	}

	@Test
	public void testGetWonderUntilEmpty() {
		WonderFactory factory = EasyMock.partialMockBuilder(WonderFactory.class).addMockedMethod("getRandomIndex")
				.addMockedMethod("removeAtIndex").withConstructor(GameMode.EASY).createMock();

		expectAndReplayIndexAndRemove(7, factory);
		verifyUntilEmpty(factory);
	}

	@Test
	public void testGetWonderAfterEmpty() {
		WonderFactory factory = EasyMock.partialMockBuilder(WonderFactory.class).addMockedMethod("getRandomIndex")
				.addMockedMethod("removeAtIndex").withConstructor(GameMode.EASY).createMock();

		expectAndReplayIndexAndRemove(7, factory);
		verifyAfterEmpty(factory);
	}

	@Test
	public void testEasyMode() {
		WonderFactory factory = EasyMock.partialMockBuilder(WonderFactory.class).addMockedMethod("getRandomIndex")
				.withConstructor(GameMode.EASY).createMock();

		expectAndReplayIndexOnly(7, factory);
		verifySide(factory, GameMode.EASY);
	}

	private void expectAndReplayIndexAndRemove(int numTimesToCall, WonderFactory factory) {
		Wonder wonder = EasyMock.partialMockBuilder(Wonder.class).createMock();

		for (int i = 0; i < numTimesToCall; i++) {
			EasyMock.expect(factory.getRandomIndex()).andReturn(0);
			EasyMock.expect(factory.removeAtIndex(0)).andReturn(wonder);
		}

		EasyMock.replay(factory);
	}

	private void expectAndReplayIndexOnly(int numTimesToCall, WonderFactory factory) {
		for (int i = 0; i < numTimesToCall; i++) {
			EasyMock.expect(factory.getRandomIndex()).andReturn(0);
		}

		EasyMock.replay(factory);
	}

	private void verifyUntilEmpty(WonderFactory factory) {
		while (!factory.isOutOfWonders()) {
			factory.getWonder();
		}
		EasyMock.verify(factory);
	}

	private void verifyAfterEmpty(WonderFactory factory) {
		verifyUntilEmpty(factory);

		try {
			factory.getWonder();
			fail();
		} catch (NoMoreWondersException e) {
			System.err.println(e.getMessage());
		}

		EasyMock.verify(factory);
	}

	private void verifySide(WonderFactory factory, GameMode mode) {
		while (!factory.isOutOfWonders()) {
			Wonder temp = factory.getWonder();
			assertEquals(Side.A, temp.getSide());
		}
	}
}