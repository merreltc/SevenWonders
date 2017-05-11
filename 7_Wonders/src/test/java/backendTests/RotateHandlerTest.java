package backendTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import backend.GameManager;
import backend.PlayerTurnHandler;
import backend.RotateHandler;
import backend.SetUpDeckHandler;
import backend.SetUpHandler;
import backend.TurnHandler;
import backend.RotateHandler.Direction;
import dataStructures.Card;
import dataStructures.Deck;
import dataStructures.GameBoard;
import dataStructures.Player;
import dataStructures.Wonder.WonderType;
import dataStructures.Deck.Age;

public class RotateHandlerTest {

	@Test
	public void testChangeRotateDirectionAndResetPositionsMin() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		RotateHandler rotateHandler = new RotateHandler(board);
		
		rotateHandler.changeRotateDirectionAndResetPositions(Direction.CLOCKWISE);
		comparePlayerPositions(players, board, 0, 1, 2);

		rotateHandler.changeRotateDirectionAndResetPositions(Direction.COUNTERCLOCKWISE);
		comparePlayerPositions(players, board, 0, 2, 1);
	}

	@Test
	public void testChangeRotateDirectionAndResetPositionsMax() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));
		players.add(new Player("Hulk", WonderType.TEMPLE));
		players.add(new Player("Iron Man", WonderType.MAUSOLEUM));
		players.add(new Player("Spider Man", WonderType.STATUE));
		players.add(new Player("Thor", WonderType.GARDENS));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		RotateHandler rotateHandler = new RotateHandler(board);
		
		rotateHandler.changeRotateDirectionAndResetPositions(Direction.CLOCKWISE);
		comparePlayerPositions(players, board, 0, 1, 6);

		rotateHandler.changeRotateDirectionAndResetPositions(Direction.COUNTERCLOCKWISE);
		comparePlayerPositions(players, board, 0, 6, 1);
	}

	@Test
	public void testChangeRotateDirectionAndResetPositionsAfterOppositeRotate() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));
		players.add(new Player("Hulk", WonderType.TEMPLE));
		players.add(new Player("Iron Man", WonderType.MAUSOLEUM));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		RotateHandler rotateHandler = new RotateHandler(board);
		
		rotateHandler.rotateClockwise();
		rotateHandler.changeRotateDirectionAndResetPositions(Direction.COUNTERCLOCKWISE);
		
		comparePlayerPositions(players, board, 0, 4, 1);

		rotateHandler.rotateCounterClockwise();
		rotateHandler.changeRotateDirectionAndResetPositions(Direction.CLOCKWISE);
		
		comparePlayerPositions(players, board, 0, 1, 4);
	}
	
	@Test
	public void testGetPlayerPositionsOnRotateClockwiseMin() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		RotateHandler rotateHandler = new RotateHandler(board);
		
		rotateHandler.rotateClockwise();
		
		comparePlayerPositions(players, board, 1, 2, 0);
	}

	@Test
	public void testGetPlayerPositionsOnRotateClockwiseMax() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));
		players.add(new Player("Hulk", WonderType.TEMPLE));
		players.add(new Player("Iron Man", WonderType.MAUSOLEUM));
		players.add(new Player("Spider Man", WonderType.STATUE));
		players.add(new Player("Thor", WonderType.GARDENS));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		RotateHandler rotateHandler = new RotateHandler(board);
		rotateHandler.rotateClockwise();
		
		comparePlayerPositions(players, board, 1, 2, 0);
	}

	@Test
	public void testGetPlayerPositionsOnRotateClockwiseTwice() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));
		players.add(new Player("Hulk", WonderType.TEMPLE));
		players.add(new Player("Iron Man", WonderType.MAUSOLEUM));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		RotateHandler rotateHandler = new RotateHandler(board);
		rotateHandler.rotateClockwise();
		rotateHandler.rotateClockwise();

		comparePlayerPositions(players, board, 2, 3, 1);
	}

	@Test
	public void testGetPlayerPositionsOnRotateClockwiseMany() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));
		players.add(new Player("Hulk", WonderType.TEMPLE));
		players.add(new Player("Iron Man", WonderType.MAUSOLEUM));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		RotateHandler rotateHandler = new RotateHandler(board);

		for (int i = 0; i < 10; i++) {
			rotateHandler.rotateClockwise();
		}
		
		comparePlayerPositions(players, board, 0, 1, 4);
	}

	@Test
	public void testGetPlayerPositionsOnRotateCounterClockwiseMin() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));
		
		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		RotateHandler rotateHandler = new RotateHandler(board);
		
		rotateHandler.changeRotateDirectionAndResetPositions(Direction.COUNTERCLOCKWISE);
		rotateHandler.rotateCounterClockwise();
		
		comparePlayerPositions(players, board, 2, 1, 0);
	}

	@Test
	public void testGetPlayerPositionsOnRotateCounterClockwiseMax() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));
		players.add(new Player("Hulk", WonderType.TEMPLE));
		players.add(new Player("Iron Man", WonderType.MAUSOLEUM));
		players.add(new Player("Spider Man", WonderType.STATUE));
		players.add(new Player("Thor", WonderType.GARDENS));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		RotateHandler rotateHandler = new RotateHandler(board);
		
		rotateHandler.changeRotateDirectionAndResetPositions(Direction.COUNTERCLOCKWISE);
		rotateHandler.rotateCounterClockwise();

		comparePlayerPositions(players, board, 6, 5, 0);
	}

	@Test
	public void testGetPlayerPositionsOnRotateCounterClockwiseTwice() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));
		players.add(new Player("Hulk", WonderType.TEMPLE));
		players.add(new Player("Iron Man", WonderType.MAUSOLEUM));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		RotateHandler rotateHandler = new RotateHandler(board);
		
		rotateHandler.changeRotateDirectionAndResetPositions(Direction.COUNTERCLOCKWISE);
		rotateHandler.rotateCounterClockwise();
		rotateHandler.rotateCounterClockwise();

		comparePlayerPositions(players, board, 3, 2, 4);
	}

	@Test
	public void testGetPlayerPositionsOnRotateCounterClockwiseMany() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));
		players.add(new Player("Hulk", WonderType.TEMPLE));
		players.add(new Player("Iron Man", WonderType.MAUSOLEUM));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);
		RotateHandler rotateHandler = new RotateHandler(board);
		
		rotateHandler.changeRotateDirectionAndResetPositions(Direction.COUNTERCLOCKWISE);

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
		ArrayList<String> playerNames = new ArrayList<String>(Arrays.asList("Wolverine", "Captain America", "Black Widow", "Hulk", "Iron Man"));
		ArrayList<WonderType> wonders = new ArrayList<WonderType>(Arrays.asList( WonderType.COLOSSUS, WonderType.LIGHTHOUSE, WonderType.TEMPLE, WonderType.STATUE, WonderType.MAUSOLEUM
				));

		GameManager manager = new GameManager(playerNames, wonders, new SetUpHandler(), new SetUpDeckHandler(), new TurnHandler(), new PlayerTurnHandler());
		manager.dealInitialTurnCards();
		ArrayList<Player> players = manager.getPlayers();
		ArrayList<ArrayList<Card>> expectedHands = new ArrayList<ArrayList<Card>>();
		
		expectedHands.add(players.get(4).getCurrentHand());
		expectedHands.add(players.get(0).getCurrentHand());
		expectedHands.add(players.get(1).getCurrentHand());
		expectedHands.add(players.get(2).getCurrentHand());
		expectedHands.add(players.get(3).getCurrentHand());
		
		RotateHandler rotateHandler = new RotateHandler(manager.getGameBoard());
		rotateHandler.rotateCurrentHands(players);
		
		for(int i = 0; i< 5; i++){
			assertEquals(expectedHands.get(i), players.get(i).getCurrentHand());
		}
		
	}
}
