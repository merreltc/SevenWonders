package backendTests;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import backend.SetUpHandler;
import dataStructures.Player;
import dataStructures.Wonder;

public class SetupBackendTest {

	@Test
	public void testValidPlayerNum() {
		assertTrue(new SetUpHandler().validatePlayerNum(3));
		assertTrue(new SetUpHandler().validatePlayerNum(7));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidPlayerNum2() {
		new SetUpHandler().validatePlayerNum(2);
		fail();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidPlayerNum8() {
		new SetUpHandler().validatePlayerNum(8);
		fail();
	}
	
	@Test
	public void testInvalidPlayerNum2ErrorMessage() {	
		try{
			new SetUpHandler().validatePlayerNum(2);
		} catch (IllegalArgumentException error){
			String message = "Cannot play with 2 players";
			assertEquals(message, error.getMessage());
		}
	}
	
	@Test
	public void testInvalidPlayerNum8ErrorMessage() {
		try{
			new SetUpHandler().validatePlayerNum(8);
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
		
		SetUpHandler setUpHandler = new SetUpHandler();
		ArrayList<Player> players = setUpHandler.setUpAndReturnPlayers(playerNames);
		
		for (int i = 0; i<players.size(); i++){
			assertEquals(playerNames.get(i), players.get(i).getName());
		}
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
		
		SetUpHandler setUpHandler = new SetUpHandler();
		ArrayList<Player> players = setUpHandler.setUpAndReturnPlayers(playerNames);
		
		for (int i = 0; i<players.size(); i++){
			assertEquals(playerNames.get(i), players.get(i).getName());
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetUpReturnsPlayerNamesZero() {
		ArrayList<String> playerNames = new ArrayList<String>();
		
		SetUpHandler setUpHandler = new SetUpHandler();
		setUpHandler.setUpAndReturnPlayers(playerNames);
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
		
		SetUpHandler setUpHandler = new SetUpHandler();
		setUpHandler.setUpAndReturnPlayers(playerNames);
		fail();
	}
	
	@Test
	public void testCreatePlayersMinNumPlayers() {
		ArrayList<String> playerNames = new ArrayList<String>();
		playerNames.add("Wolverine");
		playerNames.add("Captain America");
		playerNames.add("Black Widow");
		
		SetUpHandler setUpHandler = new SetUpHandler();
		ArrayList<Player> players = setUpHandler.createPlayers(playerNames);
		
		for (int i = 0; i<players.size(); i++){
			assertEquals(playerNames.get(i), players.get(i).getName());
		}
	}
	
	@Test
	public void testCreateNamedPlayersMin() {
		ArrayList<String> playerNames = new ArrayList<String>();
		playerNames.add("Wolverine");
		playerNames.add("Captain America");
		playerNames.add("Black Widow");
		
		SetUpHandler setUpHandler = new SetUpHandler();
		ArrayList<Player> players = setUpHandler.createPlayers(playerNames);
		
		for (int i = 0; i<players.size(); i++){
			assertEquals(playerNames.get(i), players.get(i).getName());
		}
	}
	
	@Test
	public void testCreateNamedPlayersMax() {
		ArrayList<String> playerNames = new ArrayList<String>();
		playerNames.add("Wolverine");
		playerNames.add("Captain America");
		playerNames.add("Black Widow");
		playerNames.add("Hulk");
		playerNames.add("Iron Man");
		playerNames.add("Spider Man");
		playerNames.add("Thor");
		
		SetUpHandler setUpHandler = new SetUpHandler();
		ArrayList<Player> players = setUpHandler.createPlayers(playerNames);
		
		for (int i = 0; i<players.size(); i++){
			assertEquals(playerNames.get(i), players.get(i).getName());
		}
	}

	@Test
	public void testCreatePlayerArrayWithWonders(){
		Wonder wonder = EasyMock.mock(Wonder.class);
		EasyMock.replay(wonder);
		ArrayList<Wonder> wonders = new ArrayList<Wonder>(Arrays.asList(wonder,wonder,wonder,wonder));
		ArrayList<String> playerNames = new ArrayList<String>(Arrays.asList("Player1","Player2","Player3","Player4"));
		SetUpHandler setUpHandler = new SetUpHandler();
		ArrayList<Player> players = setUpHandler.createPlayers(playerNames,wonders);
	}

}
