package backendTests;
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
		fail();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidPlayerNum8() {
		SetUpHandler setup = new SetUpHandler();
		
		setup.setPlayerNum(8);
		fail();
	}
	
	@Test
	public void testInvalidPlayerNum2ErrorMessage() {
		SetUpHandler setup = new SetUpHandler();
		
		try{
		setup.setPlayerNum(2);
		} catch (IllegalArgumentException error){
			String message = "Cannot play with 2 players";
			assertEquals(message, error.getMessage());
		}
	}
	
	@Test
	public void testInvalidPlayerNum8ErrorMessage() {
		SetUpHandler setup = new SetUpHandler();
		
		try{
		setup.setPlayerNum(8);
		} catch (IllegalArgumentException error){
			String message = "Cannot play with 8 players";
			assertEquals(message, error.getMessage());
		}
	}
}
