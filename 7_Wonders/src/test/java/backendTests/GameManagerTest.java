package backendTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.easymock.EasyMock;
import org.junit.Test;

import backend.GameManager;
import backend.PlayerTurnHandler;
import backend.RotateHandler.Direction;
import backend.SetUpDeckHandler;
import backend.SetUpHandler;
import backend.TurnHandler;
import dataStructures.Card;
import dataStructures.Deck;
import dataStructures.Deck.Age;
import dataStructures.GameBoard;
import dataStructures.GeneralEnums.Good;
import dataStructures.GeneralEnums.RawResource;
import dataStructures.GeneralEnums.Resource;
import dataStructures.Player;
import dataStructures.Wonder;
import dataStructures.Wonder.WonderType;
import guiDataStructures.PlayerInformationHolder;

public class GameManagerTest {
	
	private ArrayList<Wonder> wonders;
	
	@Test
	public void testSetUpGameBoardMinPlayers() {
		ArrayList<String> playerNames = new ArrayList<String>(Arrays.asList("Wolverine", "Captain America", "Black Widow"));
		ArrayList<WonderType> wonders = new ArrayList<WonderType>(Arrays.asList( WonderType.COLOSSUS, WonderType.LIGHTHOUSE, WonderType.TEMPLE));
		
		GameManager manager = new GameManager(compileHolderObjects(playerNames, wonders));

		assertEquals(3, manager.getNumPlayers());
	}

	@Test
	public void testSetUpGameBoardMaxPlayers() {
		
		ArrayList<String> playerNames = new ArrayList<String>(Arrays.asList("Wolverine", "Captain America", "Black Widow", "Hulk", "Iron Man", "Spider Man", "Thor"));
		ArrayList<WonderType> wonders = new ArrayList<WonderType>(Arrays.asList( WonderType.COLOSSUS, WonderType.LIGHTHOUSE, WonderType.TEMPLE, WonderType.STATUE, WonderType.MAUSOLEUM
				, WonderType.GARDENS, WonderType.PYRAMIDS));
		
		GameManager manager = new GameManager(compileHolderObjects(playerNames, wonders));

		assertEquals(7, manager.getNumPlayers());
	}

	@Test
	public void testSetUpGameBoardCreateDeck3Players() {
		SetUpDeckHandler setUpDeckHandler = EasyMock.createStrictMock(SetUpDeckHandler.class);
		Deck deck = null;

		EasyMock.expect(setUpDeckHandler.createDeck(Age.AGE1, 3)).andReturn(deck);

		EasyMock.replay(setUpDeckHandler);
		
		ArrayList<String> playerNames = new ArrayList<String>(Arrays.asList("Wolverine", "Captain America", "Black Widow"));
		ArrayList<WonderType> wonders = new ArrayList<WonderType>(Arrays.asList( WonderType.COLOSSUS, WonderType.LIGHTHOUSE, WonderType.TEMPLE));
		
		GameManager manager = new GameManager(compileHolderObjects(playerNames, wonders));
		new GameManager(compileHolderObjects(playerNames, wonders), new SetUpHandler(), setUpDeckHandler, new TurnHandler(), new PlayerTurnHandler());

		EasyMock.verify(setUpDeckHandler);
	}

	@Test
	public void testSetUpGameBoardCreateDeck7Players() {
		SetUpDeckHandler setUpDeckHandler = EasyMock.createStrictMock(SetUpDeckHandler.class);
		Deck deck = null;

		EasyMock.expect(setUpDeckHandler.createDeck(Age.AGE1, 7)).andReturn(deck);

		EasyMock.replay(setUpDeckHandler);
		ArrayList<String> playerNames = new ArrayList<String>(Arrays.asList("Wolverine", "Captain America", "Black Widow", "Hulk", "Iron Man", "Spider Man", "Thor"));
		ArrayList<WonderType> wonders = new ArrayList<WonderType>(Arrays.asList( WonderType.COLOSSUS, WonderType.LIGHTHOUSE, WonderType.TEMPLE, WonderType.STATUE, WonderType.MAUSOLEUM
				, WonderType.GARDENS, WonderType.PYRAMIDS));

		new GameManager(compileHolderObjects(playerNames, wonders), new SetUpHandler(), setUpDeckHandler, new TurnHandler(), new PlayerTurnHandler());

		EasyMock.verify(setUpDeckHandler);
	}
	
	@Test
	public void testGetDefaultDirection(){
		ArrayList<String> playerNames = new ArrayList<String>(Arrays.asList("Wolverine", "Captain America", "Black Widow"));
		ArrayList<WonderType> wonders = new ArrayList<WonderType>(Arrays.asList( WonderType.COLOSSUS, WonderType.LIGHTHOUSE, WonderType.TEMPLE));
		
		GameManager manager = new GameManager(compileHolderObjects(playerNames, wonders));
		assertEquals(Direction.CLOCKWISE, manager.getDirection());
	}

	@Test
	public void testTrade() {
		ArrayList<String> playerNames = new ArrayList<String>(Arrays.asList("Wolverine", "Captain America", "Black Widow"));
		ArrayList<WonderType> wonders = new ArrayList<WonderType>(Arrays.asList( WonderType.COLOSSUS, WonderType.LIGHTHOUSE, WonderType.TEMPLE));
		
		GameManager manager = new GameManager(compileHolderObjects(playerNames, wonders));

		manager.trade(manager.getPlayer(0), manager.getPlayer(1), 3);

		assertEquals(0, manager.getPlayerCoinTotal(0));
		assertEquals(6, manager.getPlayerCoinTotal(1));
	}

	@Test
	public void testGetCurrentPositionsOnStartMin() {
		ArrayList<String> playerNames = new ArrayList<String>(Arrays.asList("Wolverine", "Captain America", "Black Widow"));
		ArrayList<WonderType> wonders = new ArrayList<WonderType>(Arrays.asList( WonderType.COLOSSUS, WonderType.LIGHTHOUSE, WonderType.TEMPLE));
		
		GameManager manager = new GameManager(compileHolderObjects(playerNames, wonders));

		comparePlayerPositions(manager.getPlayers(), manager, 0, 1, 2);
	}

	@Test
	public void testGetPlayerPositionsOnStartMax() {
		ArrayList<String> playerNames = new ArrayList<String>(Arrays.asList("Wolverine", "Captain America", "Black Widow", "Hulk", "Iron Man", "Spider Man", "Thor"));
		ArrayList<WonderType> wonders = new ArrayList<WonderType>(Arrays.asList( WonderType.COLOSSUS, WonderType.LIGHTHOUSE, WonderType.TEMPLE, WonderType.STATUE, WonderType.MAUSOLEUM
				, WonderType.GARDENS, WonderType.PYRAMIDS));
		
		GameManager manager = new GameManager(compileHolderObjects(playerNames, wonders));

		comparePlayerPositions(manager.getPlayers(), manager, 0, 1, 6);
	}

	@Test
	public void testGetPlayerPositionsOnRotateClockwiseMin() {
		ArrayList<String> playerNames = new ArrayList<String>(Arrays.asList("Wolverine", "Captain America", "Black Widow"));
		ArrayList<WonderType> wonders = new ArrayList<WonderType>(Arrays.asList( WonderType.COLOSSUS, WonderType.LIGHTHOUSE, WonderType.TEMPLE));
		
		GameManager manager = new GameManager(compileHolderObjects(playerNames, wonders));
		manager.rotateClockwise();

		comparePlayerPositions(manager.getPlayers(), manager, 1, 2, 0);
	}

	@Test
	public void testGetPlayerPositionsOnRotateClockwiseMax() {
		ArrayList<String> playerNames = new ArrayList<String>(Arrays.asList("Wolverine", "Captain America", "Black Widow", "Hulk", "Iron Man", "Spider Man", "Thor"));
		ArrayList<WonderType> wonders = new ArrayList<WonderType>(Arrays.asList( WonderType.COLOSSUS, WonderType.LIGHTHOUSE, WonderType.TEMPLE, WonderType.STATUE, WonderType.MAUSOLEUM
				, WonderType.GARDENS, WonderType.PYRAMIDS));
		
		GameManager manager = new GameManager(compileHolderObjects(playerNames, wonders));

		manager.rotateClockwise();

		comparePlayerPositions(manager.getPlayers(), manager, 1, 2, 0);
	}

	@Test
	public void testGetPlayerPositionsOnRotateClockwiseTwice() {
		ArrayList<String> playerNames = new ArrayList<String>(Arrays.asList("Wolverine", "Captain America", "Black Widow", "Hulk", "Iron Man"));
		ArrayList<WonderType> wonders = new ArrayList<WonderType>(Arrays.asList( WonderType.COLOSSUS, WonderType.LIGHTHOUSE, WonderType.TEMPLE, WonderType.STATUE, WonderType.MAUSOLEUM
				));
		
		GameManager manager = new GameManager(compileHolderObjects(playerNames, wonders));
		manager.rotateClockwise();
		manager.rotateClockwise();

		comparePlayerPositions(manager.getPlayers(), manager, 2, 3, 1);
	}

	@Test
	public void testGetPlayerPositionsOnRotateClockwiseMany() {
		ArrayList<String> playerNames = new ArrayList<String>(Arrays.asList("Wolverine", "Captain America", "Black Widow", "Hulk", "Iron Man"));
		ArrayList<WonderType> wonders = new ArrayList<WonderType>(Arrays.asList( WonderType.COLOSSUS, WonderType.LIGHTHOUSE, WonderType.TEMPLE, WonderType.STATUE, WonderType.MAUSOLEUM
				));
		
		GameManager manager = new GameManager(compileHolderObjects(playerNames, wonders));

		for (int i = 0; i < 10; i++) {
			manager.rotateClockwise();
		}

		comparePlayerPositions(manager.getPlayers(), manager, 0, 1, 4);
	}

	@Test
	public void testChangeRotateDirectionAndResetPositionsMin() {
		ArrayList<String> playerNames = new ArrayList<String>(Arrays.asList("Wolverine", "Captain America", "Black Widow", "Hulk", "Iron Man", "Spider Man", "Thor"));
		ArrayList<WonderType> wonders = new ArrayList<WonderType>(Arrays.asList( WonderType.COLOSSUS, WonderType.LIGHTHOUSE, WonderType.TEMPLE, WonderType.STATUE, WonderType.MAUSOLEUM
				, WonderType.GARDENS, WonderType.PYRAMIDS));
		
		GameManager manager = new GameManager(compileHolderObjects(playerNames, wonders));

		manager.changeRotateDirectionAndResetPositions(Direction.CLOCKWISE);
		comparePlayerPositions(manager.getPlayers(), manager, 0, 1, 6);

		manager.changeRotateDirectionAndResetPositions(Direction.COUNTERCLOCKWISE);
		comparePlayerPositions(manager.getPlayers(), manager, 0, 6, 1);
	}

	@Test
	public void testChangeRotateDirectionAndResetPositionsMax() {
		ArrayList<String> playerNames = new ArrayList<String>(Arrays.asList("Wolverine", "Captain America", "Black Widow"));
		ArrayList<WonderType> wonders = new ArrayList<WonderType>(Arrays.asList( WonderType.COLOSSUS, WonderType.LIGHTHOUSE, WonderType.TEMPLE));
		
		GameManager manager = new GameManager(compileHolderObjects(playerNames, wonders));

		manager.changeRotateDirectionAndResetPositions(Direction.CLOCKWISE);
		comparePlayerPositions(manager.getPlayers(), manager, 0, 1, 2);

		manager.changeRotateDirectionAndResetPositions(Direction.COUNTERCLOCKWISE);
		comparePlayerPositions(manager.getPlayers(), manager, 0, 2, 1);
	}

	@Test
	public void testGetPlayerPositionsOnRotateCounterClockwiseMin() {
		ArrayList<String> playerNames = new ArrayList<String>(Arrays.asList("Wolverine", "Captain America", "Black Widow"));
		ArrayList<WonderType> wonders = new ArrayList<WonderType>(Arrays.asList( WonderType.COLOSSUS, WonderType.LIGHTHOUSE, WonderType.TEMPLE));
		
		GameManager manager = new GameManager(compileHolderObjects(playerNames, wonders));
		manager.changeRotateDirectionAndResetPositions(Direction.COUNTERCLOCKWISE);
		manager.rotateCounterClockwise();

		comparePlayerPositions(manager.getPlayers(), manager, 2, 1, 0);
	}

	@Test
	public void testGetPlayerPositionsOnRotateCounterClockwiseMax() {
		ArrayList<String> playerNames = new ArrayList<String>(Arrays.asList("Wolverine", "Captain America", "Black Widow", "Hulk", "Iron Man", "Spider Man", "Thor"));
		ArrayList<WonderType> wonders = new ArrayList<WonderType>(Arrays.asList( WonderType.COLOSSUS, WonderType.LIGHTHOUSE, WonderType.TEMPLE, WonderType.STATUE, WonderType.MAUSOLEUM
				, WonderType.GARDENS, WonderType.PYRAMIDS));
		
		GameManager manager = new GameManager(compileHolderObjects(playerNames, wonders));
		manager.changeRotateDirectionAndResetPositions(Direction.COUNTERCLOCKWISE);
		manager.rotateCounterClockwise();

		comparePlayerPositions(manager.getPlayers(), manager, 6, 5, 0);
	}

	@Test
	public void testGetPlayerPositionsOnRotateCounterClockwiseTwice() {
		ArrayList<String> playerNames = new ArrayList<String>(Arrays.asList("Wolverine", "Captain America", "Black Widow", "Hulk", "Iron Man"));
		ArrayList<WonderType> wonders = new ArrayList<WonderType>(Arrays.asList( WonderType.COLOSSUS, WonderType.LIGHTHOUSE, WonderType.TEMPLE, WonderType.STATUE, WonderType.MAUSOLEUM
				));
		
		GameManager manager = new GameManager(compileHolderObjects(playerNames, wonders));
		manager.changeRotateDirectionAndResetPositions(Direction.COUNTERCLOCKWISE);
		manager.rotateCounterClockwise();
		manager.rotateCounterClockwise();

		comparePlayerPositions(manager.getPlayers(), manager, 3, 2, 4);
	}

	@Test
	public void testGetPlayerPositionsOnRotateCounterClockwiseMany() {
		ArrayList<String> playerNames = new ArrayList<String>(Arrays.asList("Wolverine", "Captain America", "Black Widow", "Hulk", "Iron Man"));
		ArrayList<WonderType> wonders = new ArrayList<WonderType>(Arrays.asList( WonderType.COLOSSUS, WonderType.LIGHTHOUSE, WonderType.TEMPLE, WonderType.STATUE, WonderType.MAUSOLEUM
				));
		
		GameManager manager = new GameManager(compileHolderObjects(playerNames, wonders));
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
		
		ArrayList<String> playerNames = new ArrayList<String>(Arrays.asList("Wolverine", "Captain America", "Black Widow", "Hulk", "Iron Man"));
		ArrayList<WonderType> wonders = new ArrayList<WonderType>(Arrays.asList( WonderType.COLOSSUS, WonderType.LIGHTHOUSE, WonderType.TEMPLE, WonderType.STATUE, WonderType.MAUSOLEUM
				));

		GameManager manager = new GameManager(compileHolderObjects(playerNames, wonders), new SetUpHandler(), new SetUpDeckHandler(), turnHandler, new PlayerTurnHandler());
		turnHandler.dealInitialTurnCards(manager.getPlayers(), manager.getNumPlayers(),
				manager.getGameBoard().getDeck());

		EasyMock.replay(turnHandler);

		manager.dealInitialTurnCards();

		EasyMock.verify(turnHandler);
	}
	
	@Test
	public void testBuildStructure() {
		PlayerTurnHandler playerTurnHandler = EasyMock.createMock(PlayerTurnHandler.class);

		ArrayList<String> playerNames = new ArrayList<String>(Arrays.asList("Wolverine", "Captain America", "Black Widow", "Hulk", "Iron Man"));
		ArrayList<WonderType> wonders = new ArrayList<WonderType>(Arrays.asList( WonderType.COLOSSUS, WonderType.LIGHTHOUSE, WonderType.TEMPLE, WonderType.STATUE, WonderType.MAUSOLEUM
				));

		GameManager manager = new GameManager(compileHolderObjects(playerNames, wonders), new SetUpHandler(), new SetUpDeckHandler(), new TurnHandler(), playerTurnHandler);
		manager.dealInitialTurnCards();
		Card card = manager.getCurrentPlayer().getCurrentHand().get(0);
		playerTurnHandler.buildStructure(manager.getCurrentPlayer(), card);

		EasyMock.replay(playerTurnHandler);

		manager.buildStructure(card);

		EasyMock.verify(playerTurnHandler);
	}
	
	@Test
	public void testTradeFromToForResource() {
		ArrayList<String> playerNames = new ArrayList<String>(Arrays.asList("Wolverine", "Captain America", "Black Widow"));
		ArrayList<WonderType> wonders = new ArrayList<WonderType>(Arrays.asList( WonderType.COLOSSUS, WonderType.LIGHTHOUSE, WonderType.TEMPLE));

		GameManager manager = new GameManager(compileHolderObjects(playerNames, wonders), new SetUpHandler(), new SetUpDeckHandler(), new TurnHandler(), new PlayerTurnHandler());
		
		ArrayList<Card> storage = new ArrayList<Card>();
		Deck deck = manager.getGameBoard().getDeck();
		storage.add(deck.getCard(0));
		storage.add(deck.getCard(1));
		
		manager.getNextPlayer().setStoragePile(storage);
		manager.tradeForEntity(manager.getCurrentPlayer(), manager.getNextPlayer(), RawResource.LUMBER);
		
		Player current = manager.getCurrentPlayer();
		Player next = manager.getNextPlayer();
		
		assertEquals(1, (int) current.getCurrentTrades().get(RawResource.LUMBER));
		assertEquals(6, next.getCoinTotal());
	}
	
	@Test
	public void testTradeFromToForGood() {
		ArrayList<String> playerNames = new ArrayList<String>(Arrays.asList("Wolverine", "Captain America", "Black Widow"));
		ArrayList<WonderType> wonders = new ArrayList<WonderType>(Arrays.asList( WonderType.COLOSSUS, WonderType.LIGHTHOUSE, WonderType.TEMPLE));

		GameManager manager = new GameManager(compileHolderObjects(playerNames, wonders), new SetUpHandler(), new SetUpDeckHandler(), new TurnHandler(), new PlayerTurnHandler());
		
		ArrayList<Card> storage = new ArrayList<Card>();
		Deck deck = manager.getGameBoard().getDeck();
		storage.add(deck.getCard(7));
		storage.add(deck.getCard(8));
		
		manager.getNextPlayer().setStoragePile(storage);
		manager.tradeForEntity(manager.getCurrentPlayer(), manager.getNextPlayer(), Good.GLASS);
		
		Player current = manager.getCurrentPlayer();
		Player next = manager.getNextPlayer();
		
		assertEquals(1, (int) current.getCurrentTrades().get(Good.GLASS));
		assertEquals(6, next.getCoinTotal());
	}
	
	private ArrayList<PlayerInformationHolder> compileHolderObjects(ArrayList<String> playerNames, ArrayList<WonderType> wonders){
		ArrayList<PlayerInformationHolder> holders = new ArrayList<PlayerInformationHolder>();
		
		for (int i = 0; i < playerNames.size(); i++){
			PlayerInformationHolder currentHolder = new PlayerInformationHolder(playerNames.get(i), wonders.get(i), 'a');
			holders.add(currentHolder);
		}
		return holders;
	}

	@Test
	public void testMakeChangeForValue1Coins() {
		ArrayList<String> playerNames = new ArrayList<String>(
				Arrays.asList("Wolverine", "Captain America", "Black Widow"));
		ArrayList<WonderType> wonders = new ArrayList<WonderType>(
				Arrays.asList(WonderType.COLOSSUS, WonderType.LIGHTHOUSE, WonderType.TEMPLE));

		GameManager manager = new GameManager(compileHolderObjects(playerNames, wonders), new SetUpHandler(), new SetUpDeckHandler(),
				new TurnHandler(), new PlayerTurnHandler());
		GameBoard board = manager.getGameBoard();
		manager.getCurrentPlayer().addValue3(1);

		assertTrue(manager.makeChangeForValue1Coins(3));
		assertEquals(6, manager.getCurrentPlayer().getNumValue1Coins());
		assertEquals(0, manager.getCurrentPlayer().getNumValue3Coins());
		assertEquals(34, manager.getGameBoard().getTotalValue1CoinsInBank());
		assertEquals(25, manager.getGameBoard().getTotalValue3CoinsInBank());
	}

	@Test
	public void testMakeChangeForValue3Coins() {
		ArrayList<String> playerNames = new ArrayList<String>(
				Arrays.asList("Wolverine", "Captain America", "Black Widow"));
		ArrayList<WonderType> wonders = new ArrayList<WonderType>(
				Arrays.asList(WonderType.COLOSSUS, WonderType.LIGHTHOUSE, WonderType.TEMPLE));

		GameManager manager = new GameManager(compileHolderObjects(playerNames, wonders), new SetUpHandler(), new SetUpDeckHandler(),
				new TurnHandler(), new PlayerTurnHandler());
		GameBoard board = manager.getGameBoard();

		assertTrue(board.makeChangeForValue3Coins(manager.getCurrentPlayer(), 1));
		assertEquals(0, manager.getCurrentPlayer().getNumValue1Coins());
		assertEquals(1, manager.getCurrentPlayer().getNumValue3Coins());
		assertEquals(40, board.getTotalValue1CoinsInBank());
		assertEquals(23, board.getTotalValue3CoinsInBank());
	}

	@Test
	public void testDiscardSelectedCard() {
		ArrayList<String> playerNames = new ArrayList<String>(
				Arrays.asList("Wolverine", "Captain America", "Black Widow"));
		ArrayList<WonderType> wonders = new ArrayList<WonderType>(
				Arrays.asList(WonderType.COLOSSUS, WonderType.LIGHTHOUSE, WonderType.TEMPLE));

		PlayerTurnHandler playerTurnHandler = EasyMock.mock(PlayerTurnHandler.class);

		GameManager manager = new GameManager(compileHolderObjects(playerNames, wonders), new SetUpHandler(), new SetUpDeckHandler(),
				new TurnHandler(), playerTurnHandler);
		playerTurnHandler.discardSelectedCard(manager.getCurrentPlayer(), manager.getGameBoard().getDeck().getCard(0),
				manager.getGameBoard());

		EasyMock.replay(playerTurnHandler);

		manager.dealInitialTurnCards();
		manager.discardSelectedCard(manager.getCurrentPlayer().getCurrentHand().get(0));

		EasyMock.verify(playerTurnHandler);
	}

	@Test
	public void testEndCurrentPlayerTurn() {
		ArrayList<String> playerNames = new ArrayList<String>(
				Arrays.asList("Wolverine", "Captain America", "Black Widow"));
		ArrayList<WonderType> wonders = new ArrayList<WonderType>(
				Arrays.asList(WonderType.COLOSSUS, WonderType.LIGHTHOUSE, WonderType.TEMPLE));

		TurnHandler turnHandler = EasyMock.mock(TurnHandler.class);
		GameManager manager = new GameManager(compileHolderObjects(playerNames, wonders), new SetUpHandler(), new SetUpDeckHandler(),
				turnHandler, new PlayerTurnHandler());

		EasyMock.expect(turnHandler.getNumPlayersUntilPass()).andReturn(2);
		turnHandler.setNumPlayersUntilPass(1);

		EasyMock.replay(turnHandler);
		Player expectedNewCurrentPlayer = manager.getNextPlayer();
		Player expectedNewPreviousPlayer = manager.getCurrentPlayer();

		assertEquals("", manager.endCurrentPlayerTurn());
		assertEquals(expectedNewCurrentPlayer, manager.getCurrentPlayer());
		assertEquals(expectedNewPreviousPlayer, manager.getPreviousPlayer());

		EasyMock.verify(turnHandler);
	}

	@Test
	public void testEndCurrentPlayersTurn3TimesAndTradeHands() {
		ArrayList<String> playerNames = new ArrayList<String>(
				Arrays.asList("Wolverine", "Captain America", "Black Widow"));
		ArrayList<WonderType> wonders = new ArrayList<WonderType>(
				Arrays.asList(WonderType.COLOSSUS, WonderType.LIGHTHOUSE, WonderType.TEMPLE));

		TurnHandler turnHandler = EasyMock.partialMockBuilder(TurnHandler.class)
				.addMockedMethod("getNumPlayersUntilPass").createMock();
		GameManager manager = new GameManager(compileHolderObjects(playerNames, wonders), new SetUpHandler(), new SetUpDeckHandler(),
				turnHandler, new PlayerTurnHandler());
		manager.dealInitialTurnCards();

		EasyMock.expect(turnHandler.getNumPlayersUntilPass()).andReturn(2);
		EasyMock.expect(turnHandler.getNumPlayersUntilPass()).andReturn(1);
		EasyMock.expect(turnHandler.getNumPlayersUntilPass()).andReturn(0);
		turnHandler.setNumPlayersUntilPass(2);

		EasyMock.replay(turnHandler);
		Player expectedNewCurrentPlayer = manager.getCurrentPlayer();
		ArrayList<Card> expectedNewCurrentHand = manager.getPreviousPlayer().getCurrentHand();

		assertEquals("", manager.endCurrentPlayerTurn());
		assertEquals("", manager.endCurrentPlayerTurn());
		assertEquals("End of current rotation.  Switching Player hands.", manager.endCurrentPlayerTurn());

		assertEquals(expectedNewCurrentPlayer, manager.getCurrentPlayer());
		assertEquals(expectedNewCurrentHand, manager.getCurrentPlayer().getCurrentHand());

		EasyMock.verify(turnHandler);
	}

	@Test
	public void testEndPlayerTurnEndsCurrentAge() {
		ArrayList<String> playerNames = new ArrayList<String>(
				Arrays.asList("Wolverine", "Captain America", "Black Widow"));
		ArrayList<WonderType> wonders = new ArrayList<WonderType>(
				Arrays.asList(WonderType.COLOSSUS, WonderType.LIGHTHOUSE, WonderType.TEMPLE));

		TurnHandler turnHandler = EasyMock.partialMockBuilder(TurnHandler.class)
				.addMockedMethod("getNumPlayersUntilPass").addMockedMethod("setNumPlayersUntilPass").createMock();

		ArrayList<Card> cards2 = new SetUpDeckHandler().createCards(Age.AGE2, 3);
		Deck deck2 = new Deck(Age.AGE2, cards2);

		GameManager manager = new GameManager(compileHolderObjects(playerNames, wonders), new SetUpHandler(), new SetUpDeckHandler(),
				turnHandler, new PlayerTurnHandler());
		manager.dealInitialTurnCards();

		ArrayList<Card> previousCurrentCards = manager.getCurrentPlayer().getCurrentHand();

		assertEquals(7, manager.getCurrentPlayer().getCurrentHand().size());

		for (int calls = 4; calls >= 0; calls--) {
			mockExpectTurnHandlerCalls(turnHandler, calls);
		}

		mockExpectTurnHandlerCalls(turnHandler, 4);

		turnHandler.dealInitialTurnCards(manager.getPlayers(), 3, deck2);

		EasyMock.replay(turnHandler);

		for (int numCalls = 0; numCalls < 17; numCalls++) {
			manager.endCurrentPlayerTurn();
		}

		assertEquals("This is the end of the Age.  Finalizing Points", manager.endCurrentPlayerTurn());
		assertEquals(7, manager.getCurrentPlayer().getCurrentHand().size());
		assertFalse(manager.getCurrentPlayer().getCurrentHand().equals(previousCurrentCards));
		assertEquals(Age.AGE2, manager.getGameBoard().getDeck().getAge());

		EasyMock.verify(turnHandler);
	}
	
	@Test
	public void testEndPlayerTurnEndsCurrentAgeTo3() {
		ArrayList<String> playerNames = new ArrayList<String>(
				Arrays.asList("Wolverine", "Captain America", "Black Widow"));
		ArrayList<WonderType> wonders = new ArrayList<WonderType>(
				Arrays.asList(WonderType.COLOSSUS, WonderType.LIGHTHOUSE, WonderType.TEMPLE));

		TurnHandler turnHandler = EasyMock.partialMockBuilder(TurnHandler.class)
				.addMockedMethod("getNumPlayersUntilPass").addMockedMethod("setNumPlayersUntilPass").createMock();

		ArrayList<Card> cards2 = new SetUpDeckHandler().createCards(Age.AGE2, 3);
		Deck deck2 = new Deck(Age.AGE2, cards2);
		ArrayList<Card> cards3 = new SetUpDeckHandler().createCards(Age.AGE3, 3);
		Deck deck3 = new Deck(Age.AGE3, cards3);

		GameManager manager = new GameManager(compileHolderObjects(playerNames, wonders), new SetUpHandler(), new SetUpDeckHandler(),
				turnHandler, new PlayerTurnHandler());
		manager.getGameBoard().setDeck(deck2);
		manager.dealInitialTurnCards();

		ArrayList<Card> previousCurrentCards = manager.getCurrentPlayer().getCurrentHand();

		assertEquals(7, manager.getCurrentPlayer().getCurrentHand().size());

		for (int calls = 4; calls >= 0; calls--) {
			mockExpectTurnHandlerCalls(turnHandler, calls);
		}

		mockExpectTurnHandlerCalls(turnHandler, 4);

		turnHandler.dealInitialTurnCards(manager.getPlayers(), 3, deck3);

		EasyMock.replay(turnHandler);

		for (int numCalls = 0; numCalls < 17; numCalls++) {
			manager.endCurrentPlayerTurn();
		}

		assertEquals("This is the end of the Age.  Finalizing Points", manager.endCurrentPlayerTurn());
		assertEquals(7, manager.getCurrentPlayer().getCurrentHand().size());
		assertFalse(manager.getCurrentPlayer().getCurrentHand().equals(previousCurrentCards));
		assertEquals(Age.AGE3, manager.getGameBoard().getDeck().getAge());

		EasyMock.verify(turnHandler);
	}

	private void mockExpectTurnHandlerCalls(TurnHandler turnHandler, int numTurns) {
		EasyMock.expect(turnHandler.getNumPlayersUntilPass()).andReturn(2);
		turnHandler.setNumPlayersUntilPass(1);
		EasyMock.expect(turnHandler.getNumPlayersUntilPass()).andReturn(1);
		turnHandler.setNumPlayersUntilPass(0);
		EasyMock.expect(turnHandler.getNumPlayersUntilPass()).andReturn(0);
		turnHandler.setNumPlayersUntilPass(2);
	}
}
