package backendTests;
import org.easymock.EasyMock;
import org.junit.Test;

import backend.factories.WonderFactory;

public class WonderFactoryTest {

	@Test
	public void removeAtIndexValid() {
		WonderFactory factory = EasyMock.partialMockBuilder(WonderFactory.class)
				.addMockedMethod("getRandomIndex")
				.createMock();

		EasyMock.expect(factory.getRandomIndex()).andReturn(0);
		EasyMock.replay(factory);
		
		factory.getWonder();
		EasyMock.verify(factory);
	}
}