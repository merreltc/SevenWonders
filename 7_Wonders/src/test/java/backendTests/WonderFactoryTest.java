package backendTests;

import static org.junit.Assert.fail;

import org.easymock.EasyMock;
import org.junit.Test;

import backend.factories.WonderFactory;
import dataStructures.gameMaterials.Wonder.WonderType;
import exceptions.NoMoreWondersException;

public class WonderFactoryTest {

	@Test
	public void testGetWonderOnce() {
		WonderFactory factory = EasyMock.partialMockBuilder(WonderFactory.class).addMockedMethod("getRandomIndex")
				.addMockedMethod("removeAtIndex").withConstructor().createMock();

		expectAndReplayGetWonderCalls(1, factory);

		factory.getWonder();
		EasyMock.verify(factory);
	}

	@Test
	public void testGetWonderUntilEmpty() {
		WonderFactory factory = EasyMock.partialMockBuilder(WonderFactory.class).addMockedMethod("getRandomIndex")
				.addMockedMethod("removeAtIndex").withConstructor().createMock();

		expectAndReplayGetWonderCalls(7, factory);
		verifyCallGetWonderUntilEmpty(factory);
	}

	@Test
	public void testGetWonderAfterEmpty() {
		WonderFactory factory = EasyMock.partialMockBuilder(WonderFactory.class).addMockedMethod("getRandomIndex")
				.addMockedMethod("removeAtIndex").withConstructor().createMock();

		expectAndReplayGetWonderCalls(7, factory);
		verifyCallGetWonderAfterEmpty(factory);
	}

	private void expectAndReplayGetWonderCalls(int numTimesToCall, WonderFactory factory) {
		for (int i = 0; i < numTimesToCall; i++) {
			EasyMock.expect(factory.getRandomIndex()).andReturn(0);
			EasyMock.expect(factory.removeAtIndex(0)).andReturn(WonderType.COLOSSUS);
		}
		EasyMock.replay(factory);
	}

	private void verifyCallGetWonderAfterEmpty(WonderFactory factory) {
		verifyCallGetWonderUntilEmpty(factory);

		try {
			factory.getWonder();
			fail();
		} catch (NoMoreWondersException e) {
			System.err.println(e.getMessage());
		}

		EasyMock.verify(factory);
	}

	private void verifyCallGetWonderUntilEmpty(WonderFactory factory) {
		while (!factory.isOutOfWonders()) {
			factory.getWonder();
		}
		EasyMock.verify(factory);
	}
}