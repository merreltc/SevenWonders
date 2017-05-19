package backendTests;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import backend.factories.LevelFactory;
import dataStructures.gameMaterials.Wonder;

public class LevelFactoryTest {
	Wonder wonder;

	@Before
	public void setUp() {
		this.wonder = EasyMock.partialMockBuilder(Wonder.class).createMock();
	}

	@Test
	public void testGetLevel() {
		LevelFactory factory = new LevelFactory(wonder);
	}

}
