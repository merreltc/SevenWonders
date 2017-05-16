package backendTests;
import org.easymock.EasyMock;
import org.junit.Test;

import backend.factories.WonderFactory;
import dataStructures.gameMaterials.Wonder.WonderType;

public class WonderFactoryTest {

	@Test
	public void testRemoveAtIndexValid() {
		WonderFactory factory = EasyMock.partialMockBuilder(WonderFactory.class)
				.addMockedMethod("getRandomIndex")
				.addMockedMethod("removeAtIndex")
				.createMock();

		EasyMock.expect(factory.getRandomIndex()).andReturn(0);
		EasyMock.expect(factory.removeAtIndex(0)).andReturn(WonderType.COLOSSUS);
		EasyMock.replay(factory);
		
		factory.getWonder();
		EasyMock.verify(factory);
	}
}