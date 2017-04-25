package dataStructureTests;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Test;

import dataStructures.Cost;
import dataStructures.Effect;
import dataStructures.Level;

public class LevelTest {

	@Test
	public void testBasicLevel() {
		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);
		
		int priority = 1;
		Level level = new Level(priority, cost, effect);
		assertEquals(1, level.getPriority());
	}

}
