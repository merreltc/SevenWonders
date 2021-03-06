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
		Side[] sides = { Side.A, Side.A, Side.A, Side.A, Side.A, Side.A, Side.A };
		verifySide(factory, GameMode.EASY, sides);
	}

	@Test
	public void testNormalMode() {
		WonderFactory factory = EasyMock.partialMockBuilder(WonderFactory.class).addMockedMethod("getRandomIndex")
				.addMockedMethod("getSide").withConstructor(GameMode.NORMAL).createMock();

		Side[] sides = { Side.A, Side.B, Side.A, Side.A, Side.B, Side.A, Side.B };
		expectAndReplayIndexAndSide(7, factory, sides);
		verifySide(factory, GameMode.NORMAL, sides);
	}

	private void expectAndReplayIndexAndRemove(int numTimesToCall, WonderFactory factory) {
		Wonder wonder = EasyMock.partialMockBuilder(Wonder.class).createMock();

		for (int i = 0; i < numTimesToCall; i++) {
			EasyMock.expect(factory.getRandomIndex()).andReturn(0);
			EasyMock.expect(factory.removeAtIndex(0)).andReturn(wonder);
		}

		EasyMock.replay(factory);
	}

	private void expectAndReplayIndexAndSide(int numTimesToCall, WonderFactory factory, Side[] sides) {
		for (int i = 0; i < numTimesToCall; i++) {
			EasyMock.expect(factory.getRandomIndex()).andReturn(0);
			EasyMock.expect(factory.getSide()).andReturn(sides[i]);
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

	private void verifySide(WonderFactory factory, GameMode mode, Side[] sides) {
		int i = 0;
		while (!factory.isOutOfWonders()) {
			Wonder temp = factory.getWonder();
			assertEquals(sides[i], temp.getSide());
			i++;
		}
	}

	@Test
	public void testGetSideEasy() {
		WonderFactory factory = new WonderFactory(GameMode.EASY);
		assertEquals(Side.A, factory.getSide());
	}

	@Test
	public void testGetSideNormal() {
		WonderFactory factory1 = EasyMock.partialMockBuilder(WonderFactory.class).withConstructor(GameMode.NORMAL)
				.addMockedMethod("getRandomNum").createMock();
		WonderFactory factory2 = EasyMock.partialMockBuilder(WonderFactory.class).withConstructor(GameMode.NORMAL)
				.addMockedMethod("getRandomNum").createMock();

		EasyMock.expect(factory1.getRandomNum()).andReturn(0);
		EasyMock.expect(factory2.getRandomNum()).andReturn(1);
		EasyMock.replay(factory1, factory2);

		assertEquals(Side.A, factory1.generateSide(0));
		assertEquals(Side.B, factory2.generateSide(1));

		assertEquals(Side.A, factory1.getSide());
		assertEquals(Side.B, factory2.getSide());
		EasyMock.verify(factory1, factory2);
	}

	@Test
	public void testGenerateSide() {
		WonderFactory factory = new WonderFactory(GameMode.NORMAL);
		assertEquals(Side.A, factory.generateSide(0));
		assertEquals(Side.B, factory.generateSide(1));
	}
}