package backendTests;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.easymock.EasyMock;
import org.junit.Test;

import backend.SetUpHandler;
import dataStructures.Player;

public class SetupBackendTest {

	@Test
	public void testValidPlayerNum() {
		assertTrue(SetUpHandler.setUpHandler.validatePlayerNum(3));
		assertTrue(SetUpHandler.setUpHandler.validatePlayerNum(7));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidPlayerNum2() {
		SetUpHandler.setUpHandler.validatePlayerNum(2);
		fail();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidPlayerNum8() {
		SetUpHandler.setUpHandler.validatePlayerNum(8);
		fail();
	}
	
	@Test
	public void testInvalidPlayerNum2ErrorMessage() {	
		try{
			SetUpHandler.setUpHandler.validatePlayerNum(2);
		} catch (IllegalArgumentException error){
			String message = "Cannot play with 2 players";
			assertEquals(message, error.getMessage());
		}
	}
	
	@Test
	public void testInvalidPlayerNum8ErrorMessage() {
		try{
			SetUpHandler.setUpHandler.validatePlayerNum(8);
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
		
		SetUpHandler.setUpHandler = EasyMock.mock(SetUpHandler.class);
		ArrayList<Player> players = (ArrayList<Player>) EasyMock.mock(ArrayList.class);
		EasyMock.expect(SetUpHandler.setUpHandler.setUpAndReturnPlayers(playerNames)).andReturn(players);
		
		EasyMock.replay(players, SetUpHandler.setUpHandler);
		SetUpHandler.setUpHandler.setUpAndReturnPlayers(playerNames);
		
		EasyMock.verify(players, SetUpHandler.setUpHandler);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetUpReturnsPlayerNamesZero() {
		ArrayList<String> playerNames = new ArrayList<String>();
		
		SetUpHandler.setUpHandler = EasyMock.mock(SetUpHandler.class);
		ArrayList<Player> players = (ArrayList<Player>) EasyMock.mock(ArrayList.class);
		EasyMock.expect(SetUpHandler.setUpHandler.setUpAndReturnPlayers(playerNames)).andThrow(new IllegalArgumentException());
		EasyMock.expect(SetUpHandler.setUpHandler.validatePlayerNum(0)).andThrow(new IllegalArgumentException());
		
		EasyMock.replay(players, SetUpHandler.setUpHandler);
		SetUpHandler.setUpHandler.setUpAndReturnPlayers(playerNames);
		
		EasyMock.verify(players, SetUpHandler.setUpHandler);
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
		
		SetUpHandler.setUpHandler = EasyMock.mock(SetUpHandler.class);
		ArrayList<Player> players = (ArrayList<Player>) EasyMock.mock(ArrayList.class);
		EasyMock.expect(SetUpHandler.setUpHandler.setUpAndReturnPlayers(playerNames)).andThrow(new IllegalArgumentException());
		EasyMock.expect(SetUpHandler.setUpHandler.validatePlayerNum(7)).andThrow(new IllegalArgumentException());
		
		EasyMock.replay(players, SetUpHandler.setUpHandler);
		SetUpHandler.setUpHandler.setUpAndReturnPlayers(playerNames);
		
		EasyMock.verify(players, SetUpHandler.setUpHandler);
	}
	
	@Test
	public void testCreatePlayersMinNumPlayers() {
		ArrayList<String> playerNames = new ArrayList<String>();
		playerNames.add("Wolverine");
		playerNames.add("Captain America");
		playerNames.add("Black Widow");
		
		SetUpHandler.setUpHandler = EasyMock.mock(SetUpHandler.class);
		ArrayList<Player> players = (ArrayList<Player>) EasyMock.mock(ArrayList.class);
		EasyMock.expect(SetUpHandler.setUpHandler.setUpAndReturnPlayers(playerNames)).andReturn(players);
		EasyMock.expect(SetUpHandler.setUpHandler.validatePlayerNum(3)).andReturn(true);
		EasyMock.expect(SetUpHandler.setUpHandler.createPlayers(playerNames)).andReturn(players);
		
		EasyMock.replay(players, SetUpHandler.setUpHandler);
		SetUpHandler.setUpHandler.setUpAndReturnPlayers(playerNames);
		
		EasyMock.verify(players, SetUpHandler.setUpHandler);
	}


}
