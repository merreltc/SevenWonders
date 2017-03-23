import static org.junit.Assert.*;

import org.junit.Test;

import backend.SetUpHandler;

public class SetupBackendTest {

	@Test
	public void testValidPlayerNum() {
		SetUpHandler setup = new SetUpHandler();
		
		setup.setPlayerNum(3);
		assertEquals(3, setup.getPlayerNum());
		setup.setPlayerNum(7);
		assertEquals(7, setup.getPlayerNum());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidPlayerNum2() {
		SetUpHandler setup = new SetUpHandler();
		
		setup.setPlayerNum(2);
		assertEquals(2, setup.getPlayerNum());
	}

}
