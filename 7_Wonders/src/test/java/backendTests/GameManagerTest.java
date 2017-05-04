package backendTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import backend.GameManager;
import backend.RotateHandler.Direction;
import backend.SetUpDeckHandler;
import backend.SetUpHandler;
import backend.TurnHandler;
import dataStructures.Card;
import dataStructures.Deck;
import dataStructures.GameBoard;
import dataStructures.Player;
import dataStructures.Wonder;
import junit.framework.Assert;
import dataStructures.Deck.Age;
import dataStructures.Wonder.WonderType;

public class GameManagerTest {
	
	private ArrayList<Wonder> wonders;
	
	@Test
	public void testSetUpGameBoardMinPlayers() {
		HashMap<String, WonderType> playerNamesAndWonders = new HashMap<String, WonderType>();
		playerNamesAndWonders.put("Wolverine", WonderType.COLOSSUS);
		playerNamesAndWonders.put("Captain America", WonderType.LIGHTHOUSE);
		playerNamesAndWonders.put("Black Widow", WonderType.TEMPLE);
		
		GameManager manager = new GameManager(playerNamesAndWonders);

		assertEquals(3, manager.getNumPlayers());
	}

	@Test
	public void testSetUpGameBoardMaxPlayers() {
		HashMap<String, WonderType> playerNamesAndWonders = new HashMap<String, WonderType>();
		playerNamesAndWonders.put("Wolverine", WonderType.COLOSSUS);
		playerNamesAndWonders.put("Captain America", WonderType.LIGHTHOUSE);
		playerNamesAndWonders.put("Black Widow", WonderType.TEMPLE);
		playerNamesAndWonders.put("Hulk", WonderType.STATUE);
		playerNamesAndWonders.put("Iron Man", WonderType.MAUSOLEUM);
		playerNamesAndWonders.put("Spider Man", WonderType.GARDENS);
		playerNamesAndWonders.put("Thor", WonderType.PYRAMIDS);
		
		GameManager manager = new GameManager(playerNamesAndWonders);

		assertEquals(7, manager.getNumPlayers());
	}

	@Test
	public void testSetUpGameBoardCreateDeck3Players() {
		SetUpDeckHandler setUpDeckHandler = EasyMock.createStrictMock(SetUpDeckHandler.class);
		Deck deck = null;

		EasyMock.expect(setUpDeckHandler.createDeck(Age.AGE1, 3)).andReturn(deck);

		EasyMock.replay(setUpDeckHandler);
		
		HashMap<String, WonderType> playerNamesAndWonders = new HashMap<String, WonderType>();
		playerNamesAndWonders.put("Wolverine", WonderType.COLOSSUS);
		playerNamesAndWonders.put("Captain America", WonderType.LIGHTHOUSE);
		playerNamesAndWonders.put("Black Widow", WonderType.TEMPLE);

		new GameManager(playerNamesAndWonders, new SetUpHandler(), setUpDeckHandler, new TurnHandler());

		EasyMock.verify(setUpDeckHandler);
	}

	@Test
	public void testSetUpGameBoardCreateDeck7Players() {
		SetUpDeckHandler setUpDeckHandler = EasyMock.createStrictMock(SetUpDeckHandler.class);
		Deck deck = null;

		EasyMock.expect(setUpDeckHandler.createDeck(Age.AGE1, 7)).andReturn(deck);

		EasyMock.replay(setUpDeckHandler);
		HashMap<String, WonderType> playerNamesAndWonders = new HashMap<String, WonderType>();
		playerNamesAndWonders.put("Wolverine", WonderType.COLOSSUS);
		playerNamesAndWonders.put("Captain America", WonderType.LIGHTHOUSE);
		playerNamesAndWonders.put("Black Widow", WonderType.TEMPLE);
		playerNamesAndWonders.put("Hulk", WonderType.STATUE);
		playerNamesAndWonders.put("Iron Man", WonderType.MAUSOLEUM);
		playerNamesAndWonders.put("Spider Man", WonderType.GARDENS);
		playerNamesAndWonders.put("Thor", WonderType.PYRAMIDS);

		new GameManager(playerNamesAndWonders, new SetUpHandler(), setUpDeckHandler, new TurnHandler());

		EasyMock.verify(setUpDeckHandler);
	}

	@Test
	public void testTrade() {
		HashMap<String, WonderType> playerNamesAndWonders = new HashMap<String, WonderType>();
		playerNamesAndWonders.put("Wolverine", WonderType.COLOSSUS);
		playerNamesAndWonders.put("Captain America", WonderType.LIGHTHOUSE);
		playerNamesAndWonders.put("Black Widow", WonderType.TEMPLE);
		GameManager manager = new GameManager(playerNamesAndWonders);

		manager.trade(manager.getPlayer(0), manager.getPlayer(1), 3);

		assertEquals(0, manager.getPlayerCoinTotal(0));
		assertEquals(6, manager.getPlayerCoinTotal(1));
	}

	@Test
	public void testGetCurrentPositionsOnStartMin() {
		HashMap<String, WonderType> playerNamesAndWonders = new HashMap<String, WonderType>();
		playerNamesAndWonders.put("Wolverine", WonderType.COLOSSUS);
		playerNamesAndWonders.put("Captain America", WonderType.LIGHTHOUSE);
		playerNamesAndWonders.put("Black Widow", WonderType.TEMPLE);
		GameManager manager = new GameManager(playerNamesAndWonders);

		comparePlayerPositions(manager.getPlayers(), manager, 0, 1, 2);
	}

	@Test
	public void testGetPlayerPositionsOnStartMax() {
		HashMap<String, WonderType> playerNamesAndWonders = new HashMap<String, WonderType>();
		playerNamesAndWonders.put("Wolverine", WonderType.COLOSSUS);
		playerNamesAndWonders.put("Captain America", WonderType.LIGHTHOUSE);
		playerNamesAndWonders.put("Black Widow", WonderType.TEMPLE);
		playerNamesAndWonders.put("Hulk", WonderType.STATUE);
		playerNamesAndWonders.put("Iron Man", WonderType.MAUSOLEUM);
		playerNamesAndWonders.put("Spider Man", WonderType.GARDENS);
		playerNamesAndWonders.put("Thor", WonderType.PYRAMIDS);

		GameManager manager = new GameManager(playerNamesAndWonders);

		comparePlayerPositions(manager.getPlayers(), manager, 0, 1, 6);
	}

	@Test
	public void testGetPlayerPositionsOnRotateClockwiseMin() {
		HashMap<String, WonderType> playerNamesAndWonders = new HashMap<String, WonderType>();
		playerNamesAndWonders.put("Wolverine", WonderType.COLOSSUS);
		playerNamesAndWonders.put("Captain America", WonderType.LIGHTHOUSE);
		playerNamesAndWonders.put("Black Widow", WonderType.TEMPLE);

		GameManager manager = new GameManager(playerNamesAndWonders);
		manager.rotateClockwise();

		comparePlayerPositions(manager.getPlayers(), manager, 1, 2, 0);
	}

	@Test
	public void testGetPlayerPositionsOnRotateClockwiseMax() {
		HashMap<String, WonderType> playerNamesAndWonders = new HashMap<String, WonderType>();
		playerNamesAndWonders.put("Wolverine", WonderType.COLOSSUS);
		playerNamesAndWonders.put("Captain America", WonderType.LIGHTHOUSE);
		playerNamesAndWonders.put("Black Widow", WonderType.TEMPLE);
		playerNamesAndWonders.put("Hulk", WonderType.STATUE);
		playerNamesAndWonders.put("Iron Man", WonderType.MAUSOLEUM);
		playerNamesAndWonders.put("Spider Man", WonderType.GARDENS);
		playerNamesAndWonders.put("Thor", WonderType.PYRAMIDS);
		
		GameManager manager = new GameManager(playerNamesAndWonders);

		manager.rotateClockwise();

		comparePlayerPositions(manager.getPlayers(), manager, 1, 2, 0);
	}

	@Test
	public void testGetPlayerPositionsOnRotateClockwiseTwice() {
		HashMap<String, WonderType> playerNamesAndWonders = new HashMap<String, WonderType>();
		playerNamesAndWonders.put("Wolverine", WonderType.COLOSSUS);
		playerNamesAndWonders.put("Captain America", WonderType.LIGHTHOUSE);
		playerNamesAndWonders.put("Black Widow", WonderType.TEMPLE);
		playerNamesAndWonders.put("Hulk", WonderType.STATUE);
		playerNamesAndWonders.put("Iron Man", WonderType.MAUSOLEUM);

		GameManager manager = new GameManager(playerNamesAndWonders);
		manager.rotateClockwise();
		manager.rotateClockwise();

		comparePlayerPositions(manager.getPlayers(), manager, 2, 3, 1);
	}

	@Test
	public void testGetPlayerPositionsOnRotateClockwiseMany() {
		HashMap<String, WonderType> playerNamesAndWonders = new HashMap<String, WonderType>();
		playerNamesAndWonders.put("Wolverine", WonderType.COLOSSUS);
		playerNamesAndWonders.put("Captain America", WonderType.LIGHTHOUSE);
		playerNamesAndWonders.put("Black Widow", WonderType.TEMPLE);
		playerNamesAndWonders.put("Hulk", WonderType.STATUE);
		playerNamesAndWonders.put("Iron Man", WonderType.MAUSOLEUM);

		GameManager manager = new GameManager(playerNamesAndWonders);

		for (int i = 0; i < 10; i++) {
			manager.rotateClockwise();
		}

		comparePlayerPositions(manager.getPlayers(), manager, 0, 1, 4);
	}

	@Test
	public void testChangeRotateDirectionAndResetPositionsMin() {
		HashMap<String, WonderType> playerNamesAndWonders = new HashMap<String, WonderType>();
		playerNamesAndWonders.put("Wolverine", WonderType.COLOSSUS);
		playerNamesAndWonders.put("Captain America", WonderType.LIGHTHOUSE);
		playerNamesAndWonders.put("Black Widow", WonderType.TEMPLE);
		playerNamesAndWonders.put("Hulk", WonderType.STATUE);
		playerNamesAndWonders.put("Iron Man", WonderType.MAUSOLEUM);
		playerNamesAndWonders.put("Spider Man", WonderType.GARDENS);
		playerNamesAndWonders.put("Thor", WonderType.PYRAMIDS);

		GameManager manager = new GameManager(playerNamesAndWonders);

		manager.changeRotateDirectionAndResetPositions(Direction.CLOCKWISE);
		comparePlayerPositions(manager.getPlayers(), manager, 0, 1, 6);

		manager.changeRotateDirectionAndResetPositions(Direction.COUNTERCLOCKWISE);
		comparePlayerPositions(manager.getPlayers(), manager, 0, 6, 1);
	}

	@Test
	public void testChangeRotateDirectionAndResetPositionsMax() {
		HashMap<String, WonderType> playerNamesAndWonders = new HashMap<String, WonderType>();
		playerNamesAndWonders.put("Wolverine", WonderType.COLOSSUS);
		playerNamesAndWonders.put("Captain America", WonderType.LIGHTHOUSE);
		playerNamesAndWonders.put("Black Widow", WonderType.TEMPLE);

		GameManager manager = new GameManager(playerNamesAndWonders);

		manager.changeRotateDirectionAndResetPositions(Direction.CLOCKWISE);
		comparePlayerPositions(manager.getPlayers(), manager, 0, 1, 2);

		manager.changeRotateDirectionAndResetPositions(Direction.COUNTERCLOCKWISE);
		comparePlayerPositions(manager.getPlayers(), manager, 0, 2, 1);
	}

	@Test
	public void testGetPlayerPositionsOnRotateCounterClockwiseMin() {
		HashMap<String, WonderType> playerNamesAndWonders = new HashMap<String, WonderType>();
		playerNamesAndWonders.put("Wolverine", WonderType.COLOSSUS);
		playerNamesAndWonders.put("Captain America", WonderType.LIGHTHOUSE);
		playerNamesAndWonders.put("Black Widow", WonderType.TEMPLE);

		GameManager manager = new GameManager(playerNamesAndWonders);
		manager.changeRotateDirectionAndResetPositions(Direction.COUNTERCLOCKWISE);
		manager.rotateCounterClockwise();

		comparePlayerPositions(manager.getPlayers(), manager, 2, 1, 0);
	}

	@Test
	public void testGetPlayerPositionsOnRotateCounterClockwiseMax() {
		HashMap<String, WonderType> playerNamesAndWonders = new HashMap<String, WonderType>();
		playerNamesAndWonders.put("Wolverine", WonderType.COLOSSUS);
		playerNamesAndWonders.put("Captain America", WonderType.LIGHTHOUSE);
		playerNamesAndWonders.put("Black Widow", WonderType.TEMPLE);
		playerNamesAndWonders.put("Hulk", WonderType.STATUE);
		playerNamesAndWonders.put("Iron Man", WonderType.MAUSOLEUM);
		playerNamesAndWonders.put("Spider Man", WonderType.GARDENS);
		playerNamesAndWonders.put("Thor", WonderType.PYRAMIDS);

		GameManager manager = new GameManager(playerNamesAndWonders);
		manager.changeRotateDirectionAndResetPositions(Direction.COUNTERCLOCKWISE);
		manager.rotateCounterClockwise();

		comparePlayerPositions(manager.getPlayers(), manager, 6, 5, 0);
	}

	@Test
	public void testGetPlayerPositionsOnRotateCounterClockwiseTwice() {
		HashMap<String, WonderType> playerNamesAndWonders = new HashMap<String, WonderType>();
		playerNamesAndWonders.put("Wolverine", WonderType.COLOSSUS);
		playerNamesAndWonders.put("Captain America", WonderType.LIGHTHOUSE);
		playerNamesAndWonders.put("Black Widow", WonderType.TEMPLE);
		playerNamesAndWonders.put("Hulk", WonderType.STATUE);
		playerNamesAndWonders.put("Iron Man", WonderType.MAUSOLEUM);

		GameManager manager = new GameManager(playerNamesAndWonders);
		manager.changeRotateDirectionAndResetPositions(Direction.COUNTERCLOCKWISE);
		manager.rotateCounterClockwise();
		manager.rotateCounterClockwise();

		comparePlayerPositions(manager.getPlayers(), manager, 3, 2, 4);
	}

	@Test
	public void testGetPlayerPositionsOnRotateCounterClockwiseMany() {
		HashMap<String, WonderType> playerNamesAndWonders = new HashMap<String, WonderType>();
		playerNamesAndWonders.put("Wolverine", WonderType.COLOSSUS);
		playerNamesAndWonders.put("Captain America", WonderType.LIGHTHOUSE);
		playerNamesAndWonders.put("Black Widow", WonderType.TEMPLE);
		playerNamesAndWonders.put("Hulk", WonderType.STATUE);
		playerNamesAndWonders.put("Iron Man", WonderType.MAUSOLEUM);

		GameManager manager = new GameManager(playerNamesAndWonders);
		manager.changeRotateDirectionAndResetPositions(Direction.COUNTERCLOCKWISE);

		for (int i = 0; i < 10; i++) {
			manager.rotateCounterClockwise();
		}

		comparePlayerPositions(manager.getPlayers(), manager, 0, 4, 1);
	}

	public void comparePlayerPositions(ArrayList<Player> players, GameManager manager, int currIndex, int nextIndex,
			int previousIndex) {
		assertEquals(players.get(currIndex), manager.getCurrentPlayer());
		assertEquals(players.get(nextIndex), manager.getNextPlayer());
		assertEquals(players.get(previousIndex), manager.getPreviousPlayer());
	}

	@Test
	public void testDealInitialCards() {
		TurnHandler turnHandler = EasyMock.createMock(TurnHandler.class);

		HashMap<String, WonderType> playerNamesAndWonders = new HashMap<String, WonderType>();
		playerNamesAndWonders.put("Wolverine", WonderType.COLOSSUS);
		playerNamesAndWonders.put("Captain America", WonderType.LIGHTHOUSE);
		playerNamesAndWonders.put("Black Widow", WonderType.TEMPLE);

		GameManager manager = new GameManager(playerNamesAndWonders, new SetUpHandler(), new SetUpDeckHandler(), turnHandler);
		turnHandler.dealInitialTurnCards(manager.getPlayers(), manager.getNumPlayers(),
				manager.getGameBoard().getDeck());

		EasyMock.replay(turnHandler);

		manager.dealInitialTurnCards();

		EasyMock.verify(turnHandler);
	}
}
