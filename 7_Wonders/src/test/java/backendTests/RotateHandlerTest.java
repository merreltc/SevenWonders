package backendTests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import backend.GameManager;
import backend.handlers.PlayerTurnHandler;
import backend.handlers.RotateHandler;
import backend.handlers.SetUpDeckHandler;
import backend.handlers.SetUpPlayerHandler;
import backend.handlers.TurnHandler;
import backend.handlers.RotateHandler.Rotation;
import constants.GeneralEnums.GameMode;
import dataStructures.GameBoard;
import dataStructures.gameMaterials.Card;
import dataStructures.gameMaterials.Deck;
import dataStructures.gameMaterials.Deck.Age;
import dataStructures.gameMaterials.Wonder.WonderType;
import dataStructures.playerData.Player;

public class RotateHandlerTest {
	private Deck testDeck;
	
	private SetUpPlayerHandler setUpPlayerHandler;
	private TurnHandler turnHandler;
	private PlayerTurnHandler playerTurnHandler;
	private SetUpDeckHandler setUpDeckHandler;

	@Before
	public void setUp() {
		this.setUpPlayerHandler = EasyMock.partialMockBuilder(SetUpPlayerHandler.class).withConstructor(GameMode.EASY)
				.createMock();
		this.turnHandler = EasyMock.partialMockBuilder(TurnHandler.class).withConstructor().createMock();
		this.playerTurnHandler = EasyMock.partialMockBuilder(PlayerTurnHandler.class).withConstructor().createMock();
		this.setUpDeckHandler = EasyMock.partialMockBuilder(SetUpDeckHandler.class).withConstructor().createMock();
		this.testDeck = EasyMock.createStrictMock(Deck.class);
	}
	
	@Test
	public void testChangeRotateDirectionAndResetPositionsMin() {
		ArrayList<Player> players = setUpPlayersWithNumPlayers(3);
		GameBoard board = new GameBoard(players, this.testDeck);
		RotateHandler rotateHandler = new RotateHandler(board);

		rotateHandler.changeRotateDirectionAndResetPositions(Rotation.CLOCKWISE);
		comparePlayerPositions(players, board, 0, 1, 2);

		rotateHandler.changeRotateDirectionAndResetPositions(Rotation.COUNTERCLOCKWISE);
		comparePlayerPositions(players, board, 0, 2, 1);
	}

	@Test
	public void testChangeRotateDirectionAndResetPositionsMax() {
		ArrayList<Player> players = setUpPlayersWithNumPlayers(7);
		GameBoard board = new GameBoard(players, this.testDeck);
		RotateHandler rotateHandler = new RotateHandler(board);

		rotateHandler.changeRotateDirectionAndResetPositions(Rotation.CLOCKWISE);
		comparePlayerPositions(players, board, 0, 1, 6);

		rotateHandler.changeRotateDirectionAndResetPositions(Rotation.COUNTERCLOCKWISE);
		comparePlayerPositions(players, board, 0, 6, 1);
	}

	@Test
	public void testChangeRotateDirectionAndResetPositionsAfterOppositeRotate() {
		ArrayList<Player> players = setUpPlayersWithNumPlayers(5);
		GameBoard board = new GameBoard(players, this.testDeck);
		RotateHandler rotateHandler = new RotateHandler(board);

		rotateHandler.rotateClockwise();
		rotateHandler.changeRotateDirectionAndResetPositions(Rotation.COUNTERCLOCKWISE);

		comparePlayerPositions(players, board, 0, 4, 1);

		rotateHandler.rotateCounterClockwise();
		rotateHandler.changeRotateDirectionAndResetPositions(Rotation.CLOCKWISE);

		comparePlayerPositions(players, board, 0, 1, 4);
	}

	@Test
	public void testGetPlayerPositionsOnRotateClockwiseMin() {
		ArrayList<Player> players = setUpPlayersWithNumPlayers(3);
		GameBoard board = new GameBoard(players, this.testDeck);
		RotateHandler rotateHandler = new RotateHandler(board);
		rotateHandler.rotateClockwise();

		comparePlayerPositions(players, board, 1, 2, 0);
	}

	@Test
	public void testGetPlayerPositionsOnRotateClockwiseMax() {
		ArrayList<Player> players = setUpPlayersWithNumPlayers(7);
		GameBoard board = new GameBoard(players, this.testDeck);
		RotateHandler rotateHandler = new RotateHandler(board);
		rotateHandler.rotateClockwise();

		comparePlayerPositions(players, board, 1, 2, 0);
	}

	@Test
	public void testGetPlayerPositionsOnRotateClockwiseTwice() {
		ArrayList<Player> players = setUpPlayersWithNumPlayers(5);
		GameBoard board = new GameBoard(players, this.testDeck);
		RotateHandler rotateHandler = new RotateHandler(board);
		rotateHandler.rotateClockwise();
		rotateHandler.rotateClockwise();

		comparePlayerPositions(players, board, 2, 3, 1);
	}

	@Test
	public void testGetPlayerPositionsOnRotateClockwiseMany() {
		ArrayList<Player> players = setUpPlayersWithNumPlayers(5);
		GameBoard board = new GameBoard(players, this.testDeck);
		RotateHandler rotateHandler = new RotateHandler(board);

		for (int i = 0; i < 10; i++) {
			rotateHandler.rotateClockwise();
		}

		comparePlayerPositions(players, board, 0, 1, 4);
	}

	@Test
	public void testGetPlayerPositionsOnRotateCounterClockwiseMin() {
		ArrayList<Player> players = setUpPlayersWithNumPlayers(3);
		GameBoard board = new GameBoard(players, this.testDeck);
		RotateHandler rotateHandler = new RotateHandler(board);

		rotateHandler.changeRotateDirectionAndResetPositions(Rotation.COUNTERCLOCKWISE);
		rotateHandler.rotateCounterClockwise();

		comparePlayerPositions(players, board, 2, 1, 0);
	}

	@Test
	public void testGetPlayerPositionsOnRotateCounterClockwiseMax() {
		ArrayList<Player> players = setUpPlayersWithNumPlayers(7);
		GameBoard board = new GameBoard(players, this.testDeck);
		RotateHandler rotateHandler = new RotateHandler(board);

		rotateHandler.changeRotateDirectionAndResetPositions(Rotation.COUNTERCLOCKWISE);
		rotateHandler.rotateCounterClockwise();

		comparePlayerPositions(players, board, 6, 5, 0);
	}

	@Test
	public void testGetPlayerPositionsOnRotateCounterClockwiseTwice() {
		ArrayList<Player> players = setUpPlayersWithNumPlayers(5);
		GameBoard board = new GameBoard(players, this.testDeck);
		RotateHandler rotateHandler = new RotateHandler(board);

		rotateHandler.changeRotateDirectionAndResetPositions(Rotation.COUNTERCLOCKWISE);
		rotateHandler.rotateCounterClockwise();
		rotateHandler.rotateCounterClockwise();

		comparePlayerPositions(players, board, 3, 2, 4);
	}

	@Test
	public void testGetPlayerPositionsOnRotateCounterClockwiseMany() {
		ArrayList<Player> players = setUpPlayersWithNumPlayers(5);
		GameBoard board = new GameBoard(players, this.testDeck);
		RotateHandler rotateHandler = new RotateHandler(board);

		rotateHandler.changeRotateDirectionAndResetPositions(Rotation.COUNTERCLOCKWISE);

		for (int i = 0; i < 10; i++) {
			rotateHandler.rotateCounterClockwise();
		}

		comparePlayerPositions(players, board, 0, 4, 1);
	}

	public void comparePlayerPositions(ArrayList<Player> players, GameBoard board, int currIndex, int nextIndex,
			int previousIndex) {
		assertEquals(players.get(currIndex), board.getCurrentPlayer());
		assertEquals(players.get(nextIndex), board.getNextPlayer());
		assertEquals(players.get(previousIndex), board.getPreviousPlayer());
	}

	@Test
	public void testRotateCurrentHandsClockwise() {
		ArrayList<String> playerNames = setUpNamesWithNumPlayers(5);		
		GameManager manager = new GameManager(playerNames, this.setUpPlayerHandler,
				this.setUpDeckHandler, this.turnHandler, this.playerTurnHandler);
		
		manager.dealInitialTurnCards();
		ArrayList<Player> players = manager.getPlayers();
		ArrayList<ArrayList<Card>> expectedHands = new ArrayList<ArrayList<Card>>();

		expectedHands.add(players.get(4).getCurrentHand());
		expectedHands.add(players.get(0).getCurrentHand());
		expectedHands.add(players.get(1).getCurrentHand());
		expectedHands.add(players.get(2).getCurrentHand());
		expectedHands.add(players.get(3).getCurrentHand());

		RotateHandler rotateHandler = new RotateHandler(manager.getGameBoard());
		rotateHandler.rotateCurrentHands(players, Rotation.CLOCKWISE);

		for (int i = 0; i < 5; i++) {
			assertEquals(expectedHands.get(i), players.get(i).getCurrentHand());
		}
	}

	@Test
	public void testRotateCurrentHandsClockwise3Players() {
		ArrayList<String> playerNames = setUpNamesWithNumPlayers(3);
		GameManager manager = new GameManager(playerNames, this.setUpPlayerHandler,
				this.setUpDeckHandler, this.turnHandler, this.playerTurnHandler);
		manager.dealInitialTurnCards();
		ArrayList<Player> players = manager.getPlayers();
		ArrayList<ArrayList<Card>> expectedHands = new ArrayList<ArrayList<Card>>();

		expectedHands.add(players.get(2).getCurrentHand());
		expectedHands.add(players.get(0).getCurrentHand());
		expectedHands.add(players.get(1).getCurrentHand());

		RotateHandler rotateHandler = new RotateHandler(manager.getGameBoard());
		rotateHandler.rotateCurrentHands(players, Rotation.CLOCKWISE);

		for (int i = 0; i < 3; i++) {
			assertEquals(expectedHands.get(i), players.get(i).getCurrentHand());
		}
	}

	@Test
	public void testRotateCurrentHandsCounterClockwise() {
		ArrayList<String> playerNames = setUpNamesWithNumPlayers(5);
		GameManager manager = new GameManager(playerNames, this.setUpPlayerHandler,
				this.setUpDeckHandler, this.turnHandler, this.playerTurnHandler);
		manager.dealInitialTurnCards();
		ArrayList<Player> players = manager.getPlayers();
		ArrayList<ArrayList<Card>> expectedHands = new ArrayList<ArrayList<Card>>();

		expectedHands.add(players.get(1).getCurrentHand());
		expectedHands.add(players.get(2).getCurrentHand());
		expectedHands.add(players.get(3).getCurrentHand());
		expectedHands.add(players.get(4).getCurrentHand());
		expectedHands.add(players.get(0).getCurrentHand());

		RotateHandler rotateHandler = new RotateHandler(manager.getGameBoard());
		rotateHandler.rotateCurrentHands(players, Rotation.COUNTERCLOCKWISE);

		for (int i = 0; i < 5; i++) {
			assertEquals(expectedHands.get(i), players.get(i).getCurrentHand());
		}
	}

	@Test
	public void testRotateCurrentHandsCounterClockwise3Players() {
		ArrayList<String> playerNames = setUpNamesWithNumPlayers(3);
		GameManager manager = new GameManager(playerNames, this.setUpPlayerHandler,
				this.setUpDeckHandler, this.turnHandler, this.playerTurnHandler);
		manager.dealInitialTurnCards();
		
		ArrayList<Player> players = manager.getPlayers();
		ArrayList<ArrayList<Card>> expectedHands = new ArrayList<ArrayList<Card>>();

		expectedHands.add(players.get(1).getCurrentHand());
		expectedHands.add(players.get(2).getCurrentHand());
		expectedHands.add(players.get(0).getCurrentHand());

		RotateHandler rotateHandler = new RotateHandler(manager.getGameBoard());
		rotateHandler.rotateCurrentHands(players, Rotation.COUNTERCLOCKWISE);

		for (int i = 0; i < 3; i++) {
			assertEquals(expectedHands.get(i), players.get(i).getCurrentHand());
		}
	}
	
	private ArrayList<Player> setUpPlayersWithNumPlayers(int num) {
		ArrayList<Player> result = new ArrayList<Player>();
		for (int i = 0; i < num; i++) {
			Player temp = EasyMock.createStrictMock(Player.class);
			result.add(temp);
		}
		return result;
	}
	
	private ArrayList<String> setUpNamesWithNumPlayers(int num) {
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < num; i++) {
			result.add("Jane Doe");
		}
		return result;
	}
}
