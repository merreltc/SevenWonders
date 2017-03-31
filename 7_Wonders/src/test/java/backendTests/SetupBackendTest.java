package backendTests;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.easymock.EasyMock;
import org.junit.Test;

import backend.SetUpHandler;
import dataStructures.GameBoard;
import dataStructures.Player;

public class SetupBackendTest {

	@Test
	public void testValidPlayerNum() {
		assertTrue(SetUpHandler.validatePlayerNum(3));
		assertTrue(SetUpHandler.validatePlayerNum(7));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidPlayerNum2() {
		SetUpHandler.validatePlayerNum(2);
		fail();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidPlayerNum8() {
		SetUpHandler.validatePlayerNum(8);
		fail();
	}
	
	@Test
	public void testInvalidPlayerNum2ErrorMessage() {	
		try{
			SetUpHandler.validatePlayerNum(2);
		} catch (IllegalArgumentException error){
			String message = "Cannot play with 2 players";
			assertEquals(message, error.getMessage());
		}
	}
	
	@Test
	public void testInvalidPlayerNum8ErrorMessage() {
		try{
			SetUpHandler.validatePlayerNum(8);
		} catch (IllegalArgumentException error){
			String message = "Cannot play with 8 players";
			assertEquals(message, error.getMessage());
		}
	}
	
	@Test
	public void testSetUpReturnsPlayerNamesMin() {
		ArrayList<String> playerNames = new ArrayList<String>();
		playerNames.add("Wolverine");
		playerNames.add("Captain America");
		playerNames.add("Black Widow");
		
		SetUpHandler.setUpHandler = EasyMock.mock(SetUpHandler.class);
		ArrayList<Player> players = (ArrayList<Player>) EasyMock.mock(ArrayList.class);
		EasyMock.expect(SetUpHandler.setUpHandler.setUpAndReturnPlayers(playerNames)).andReturn(players);
		
		EasyMock.replay(players, SetUpHandler.setUpHandler);
		SetUpHandler.setUpHandler.setUpAndReturnPlayers(playerNames);
		
		EasyMock.verify(players, SetUpHandler.setUpHandler);
	}
	
	@Test
	public void testSetUpReturnsPlayerNamesMax() {
		ArrayList<String> playerNames = new ArrayList<String>();
		playerNames.add("Wolverine");
		playerNames.add("Captain America");
		playerNames.add("Black Widow");
		playerNames.add("Hulk");
		playerNames.add("Iron Man");
		playerNames.add("Spider Man");
		playerNames.add("Thor");
		assertEquals(playerNames, SetUpHandler.setUpAndReturnPlayers(playerNames));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetUpReturnsPlayerNamesZero() {
		ArrayList<String> playerNames = new ArrayList<String>();
		assertEquals(playerNames, SetUpHandler.setUpAndReturnPlayers(playerNames));
		fail();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetUpReturnsPlayerNamesTooMany() {
		ArrayList<String> playerNames = new ArrayList<String>();
		playerNames.add("Wolverine");
		playerNames.add("Captain America");
		playerNames.add("Black Widow");
		playerNames.add("Hulk");
		playerNames.add("Iron Man");
		playerNames.add("Spider Man");
		playerNames.add("Thor");
		playerNames.add("Ultron");
		assertEquals(playerNames, SetUpHandler.setUpAndReturnPlayers(playerNames));
		fail();
	}


}
