package backendTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.easymock.EasyMock;
import org.junit.Assert;
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
import dataStructures.Handlers;
import dataStructures.gameMaterials.Card;
import dataStructures.gameMaterials.Deck;
import dataStructures.gameMaterials.Deck.Age;
import dataStructures.gameMaterials.Wonder;
import dataStructures.playerData.Player;
import dataStructures.playerData.Chip.ChipValue;

public class TurnHandlerTest {
	private SetUpPlayerHandler setUpPlayerHandler;

	@Before
	public void setUp() {
		this.setUpPlayerHandler = EasyMock.partialMockBuilder(SetUpPlayerHandler.class).withConstructor(GameMode.EASY)
				.createMock();
	}

	@Test
	public void testDealInitialCards3Players() {
		ArrayList<String> playerNames = setUpNamesByNum(3);
		GameManager manager = new GameManager(playerNames, setUpHandlers());

		Deck deck = manager.getDeck();
		int numPlayers = 3;
		int expectedDeckSize = deck.getNumCards() - (7 * numPlayers);

		ArrayList<Player> players = manager.getPlayers();
		new TurnHandler().dealInitialTurnCards(players, deck);

		for (Player player : players) {
			assertEquals(7, player.getCurrentHand().size());
		}

		assertEquals(expectedDeckSize, deck.getCards().size());
	}

	@Test
	public void testDealInitialCards7Players() {
		ArrayList<String> playerNames = setUpNamesByNum(7);
		GameManager manager = new GameManager(playerNames, setUpHandlers());

		Deck deck = manager.getGameBoard().getDeck();
		int numPlayers = 7;
		int expectedDeckSize = deck.getNumCards() - (7 * numPlayers);

		ArrayList<Player> players = manager.getPlayers();
		new TurnHandler().dealInitialTurnCards(players, deck);

		for (Player player : players) {
			assertEquals(7, player.getCurrentHand().size());
		}

		assertEquals(expectedDeckSize, deck.getCards().size());
	}

	@Test
	public void testDealInitialCards5PlayersNotSame() {
		ArrayList<String> playerNames = setUpNamesByNum(5);
		GameManager manager = new GameManager(playerNames, setUpHandlers());

		Deck deck = manager.getGameBoard().getDeck();

		int expectedDeckSize = deck.getNumCards() - 35;
		ArrayList<Player> players = manager.getPlayers();
		new TurnHandler().dealInitialTurnCards(players, deck);

		for (Player player : players) {
			assertEquals(7, player.getCurrentHand().size());
		}

		Player captain = players.get(1);
		Player hulk = players.get(3);

		ArrayList<Card> chand = captain.getCurrentHand();
		ArrayList<Card> hhand = hulk.getCurrentHand();
		Assert.assertNotEquals(chand.get(0), hhand.get(0));
		Assert.assertNotEquals(chand.get(1), hhand.get(1));
		Assert.assertNotEquals(chand.get(2), hhand.get(2));
		Assert.assertNotEquals(chand.get(3), hhand.get(3));
		Assert.assertNotEquals(chand.get(4), hhand.get(4));

		assertEquals(expectedDeckSize, deck.getCards().size());
	}

	@Test
	public void testGetNumPlayersUntilPass3Players() {
		ArrayList<String> playerNames = setUpNamesByNum(3);
		GameManager manager = new GameManager(playerNames, setUpHandlers());

		Deck deck = manager.getGameBoard().getDeck();
		ArrayList<Player> players = manager.getPlayers();
		TurnHandler turnHandler = new TurnHandler();
		turnHandler.dealInitialTurnCards(players, deck);

		assertEquals(2, turnHandler.getNumPlayersUntilPass());
	}

	@Test
	public void testSetNumPlayersUntilPass() {
		TurnHandler turnHandler = new TurnHandler();
		turnHandler.setNumPlayersUntilPass(2);

		assertEquals(2, turnHandler.getNumPlayersUntilPass());
	}

	@Test
	public void testDefaultGetNumTurnsTilEndOfAge() {
		ArrayList<String> playerNames = setUpNamesByNum(3);
		GameManager manager = new GameManager(playerNames, setUpHandlers());

		Deck deck = manager.getGameBoard().getDeck();
		ArrayList<Player> players = manager.getPlayers();
		TurnHandler turnHandler = new TurnHandler();

		turnHandler.dealInitialTurnCards(players, deck);

		assertEquals(5, turnHandler.getNumTurnsTilEndOfAge());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetNumPlayersUntilPass6Players() {
		ArrayList<Player> players = setUpPlayersByNum(5);
		Deck deck = EasyMock.mock(Deck.class);
		expectAndReplayDeckCalls(deck);

		TurnHandler turnHandler = new TurnHandler();
		turnHandler.dealInitialTurnCards(players, deck);

		EasyMock.verify(deck);
		assertEquals(5, turnHandler.getNumTurnsTilEndOfAge());
	}

	private void expectAndReplayDeckCalls(Deck deck) {
		Card card = EasyMock.createStrictMock(Card.class);
		for (int i = 0; i < 35; i++) {
			EasyMock.expect(deck.removeAtIndex(0)).andReturn(card);
		}
		EasyMock.replay(deck);
	}

	@Test
	public void testSetNumTurnsTilEndOfAge() {
		TurnHandler turnHandler = new TurnHandler();
		turnHandler.setNumTurnsTilEndOfAge(5);
		assertEquals(5, turnHandler.getNumTurnsTilEndOfAge());
	}

	@Test
	public void endAge1ThreePlayersMiddleWithMostWarTokens() {
		ArrayList<Player> players = setUpPlayersByNum(3);
		// array order: middle, left, right player
		int[] playerShields = { 5, 2, 2 };
		int[] numCalls = { 3, 3, 4 };
		expectAndReplayAddNumShields(players, playerShields, numCalls);

		TurnHandler turnHandler = new TurnHandler();
		turnHandler.endAge(players, Age.AGE1);

		verifyPlayers(players);

		Player middle = players.get(0);
		Player left = players.get(1);
		Player right = players.get(2);

		Assert.assertEquals(1, (int) left.getConflictTokens().get(ChipValue.NEG1));
		Assert.assertEquals(2, (int) middle.getConflictTokens().get(ChipValue.ONE));
		Assert.assertEquals(1, (int) right.getConflictTokens().get(ChipValue.NEG1));
	}

	@Test
	public void endAge1ThreePlayersRightWithMostWarTokens() {
		ArrayList<Player> players = setUpPlayersByNum(3);
		// array order: middle, left, right player
		int[] playerShields = { 2, 2, 5 };
		int[] numCalls = { 3, 4, 3 };
		expectAndReplayAddNumShields(players, playerShields, numCalls);

		TurnHandler turnHandler = new TurnHandler();
		turnHandler.endAge(players, Age.AGE1);

		verifyPlayers(players);

		Player middle = players.get(0);
		Player left = players.get(1);
		Player right = players.get(2);

		Assert.assertEquals(1, (int) left.getConflictTokens().get(ChipValue.NEG1));
		Assert.assertEquals(1, (int) middle.getConflictTokens().get(ChipValue.NEG1));
		Assert.assertEquals(2, (int) right.getConflictTokens().get(ChipValue.ONE));

		Assert.assertEquals(0, (int) left.getConflictTokens().get(ChipValue.ONE));
		Assert.assertEquals(0, (int) middle.getConflictTokens().get(ChipValue.ONE));
		Assert.assertEquals(0, (int) right.getConflictTokens().get(ChipValue.NEG1));
	}

	@Test
	public void endAge17PlayersAssortedWarTokens() {
		ArrayList<Player> players = setUpPlayersByNum(7);
		int[] playerShields = { 2, 3, 5, 5, 7, 6, 3 };
		int[] numCalls = { 3, 4, 4, 4, 3, 2, 2 };
		expectAndReplayAddNumShields(players, playerShields, numCalls);

		TurnHandler turnHandler = new TurnHandler();
		turnHandler.endAge(players, Age.AGE1);

		verifyPlayers(players);

		Assert.assertEquals(2, (int) players.get(0).getConflictTokens().get(ChipValue.NEG1));
		Assert.assertEquals(1, (int) players.get(1).getConflictTokens().get(ChipValue.NEG1));
		Assert.assertEquals(1, (int) players.get(1).getConflictTokens().get(ChipValue.ONE));
		Assert.assertEquals(1, (int) players.get(2).getConflictTokens().get(ChipValue.ONE));
		Assert.assertEquals(1, (int) players.get(3).getConflictTokens().get(ChipValue.NEG1));
		Assert.assertEquals(2, (int) players.get(4).getConflictTokens().get(ChipValue.ONE));
		Assert.assertEquals(1, (int) players.get(5).getConflictTokens().get(ChipValue.NEG1));
		Assert.assertEquals(1, (int) players.get(5).getConflictTokens().get(ChipValue.ONE));
		Assert.assertEquals(1, (int) players.get(6).getConflictTokens().get(ChipValue.NEG1));
		Assert.assertEquals(1, (int) players.get(6).getConflictTokens().get(ChipValue.ONE));

	}

	@Test
	public void endAge2ThreePlayersMiddleWithMostWarTokens() {
		ArrayList<Player> players = setUpPlayersByNum(3);
		// array order: middle, left, right player
		int[] playerShields = { 5, 2, 2 };
		int[] numCalls = { 3, 3, 4 };
		expectAndReplayAddNumShields(players, playerShields, numCalls);

		TurnHandler turnHandler = new TurnHandler();
		turnHandler.endAge(players, Age.AGE2);

		verifyPlayers(players);

		Player middle = players.get(0);
		Player left = players.get(1);
		Player right = players.get(2);

		Assert.assertEquals(1, (int) left.getConflictTokens().get(ChipValue.NEG1));
		Assert.assertEquals(2, (int) middle.getConflictTokens().get(ChipValue.THREE));
		Assert.assertEquals(1, (int) right.getConflictTokens().get(ChipValue.NEG1));

	}

	@Test
	public void endAge2ThreePlayersRightWithMostWarTokens() {
		ArrayList<Player> players = setUpPlayersByNum(3);
		// array order: middle, left, right player
		int[] playerShields = { 2, 2, 5 };
		int[] numCalls = { 3, 4, 3 };
		expectAndReplayAddNumShields(players, playerShields, numCalls);

		TurnHandler turnHandler = new TurnHandler();
		turnHandler.endAge(players, Age.AGE2);

		verifyPlayers(players);

		Player middle = players.get(0);
		Player left = players.get(1);
		Player right = players.get(2);

		Assert.assertEquals(1, (int) left.getConflictTokens().get(ChipValue.NEG1));
		Assert.assertEquals(1, (int) middle.getConflictTokens().get(ChipValue.NEG1));
		Assert.assertEquals(2, (int) right.getConflictTokens().get(ChipValue.THREE));

		Assert.assertEquals(0, (int) left.getConflictTokens().get(ChipValue.THREE));
		Assert.assertEquals(0, (int) middle.getConflictTokens().get(ChipValue.THREE));
		Assert.assertEquals(0, (int) right.getConflictTokens().get(ChipValue.NEG1));
	}

	@Test
	public void endAge27PlayersAssortedWarTokens() {
		ArrayList<Player> players = setUpPlayersByNum(7);
		int[] playerShields = { 2, 3, 5, 5, 7, 6, 3 };
		int[] numCalls = { 3, 4, 4, 4, 3, 2, 2 };
		expectAndReplayAddNumShields(players, playerShields, numCalls);

		TurnHandler turnHandler = new TurnHandler();
		turnHandler.endAge(players, Age.AGE2);

		verifyPlayers(players);

		Assert.assertEquals(2, (int) players.get(0).getConflictTokens().get(ChipValue.NEG1));
		Assert.assertEquals(1, (int) players.get(1).getConflictTokens().get(ChipValue.NEG1));
		Assert.assertEquals(1, (int) players.get(1).getConflictTokens().get(ChipValue.THREE));
		Assert.assertEquals(1, (int) players.get(2).getConflictTokens().get(ChipValue.THREE));
		Assert.assertEquals(1, (int) players.get(3).getConflictTokens().get(ChipValue.NEG1));
		Assert.assertEquals(2, (int) players.get(4).getConflictTokens().get(ChipValue.THREE));
		Assert.assertEquals(1, (int) players.get(5).getConflictTokens().get(ChipValue.NEG1));
		Assert.assertEquals(1, (int) players.get(5).getConflictTokens().get(ChipValue.THREE));
		Assert.assertEquals(1, (int) players.get(6).getConflictTokens().get(ChipValue.NEG1));
		Assert.assertEquals(1, (int) players.get(6).getConflictTokens().get(ChipValue.THREE));

	}

	@Test
	public void endAge3ThreePlayersMiddleWithMostWarTokens() {
		ArrayList<Player> players = setUpPlayersByNum(3);
		// array order: middle, left, right player
		int[] playerShields = { 5, 2, 2 };
		int[] numCalls = { 3, 3, 4 };
		expectAndReplayAddNumShields(players, playerShields, numCalls);

		TurnHandler turnHandler = new TurnHandler();
		turnHandler.endAge(players, Age.AGE3);

		verifyPlayers(players);

		Player middle = players.get(0);
		Player left = players.get(1);
		Player right = players.get(2);

		Assert.assertEquals(1, (int) right.getConflictTokens().get(ChipValue.NEG1));
		Assert.assertEquals(2, (int) middle.getConflictTokens().get(ChipValue.FIVE));
		Assert.assertEquals(1, (int) right.getConflictTokens().get(ChipValue.NEG1));
	}

	@Test
	public void endAge3ThreePlayersRightWithMostWarTokens() {
		ArrayList<Player> players = setUpPlayersByNum(3);
		// array order: middle, left, right player
		int[] playerShields = { 2, 2, 5 };
		int[] numCalls = { 4, 5, 3 };
		expectAndReplayAddNumShields(players, playerShields, numCalls);

		TurnHandler turnHandler = new TurnHandler();
		turnHandler.endAge(players, Age.AGE3);

		Player middle = players.get(0);
		Player left = players.get(1);
		Player right = players.get(2);

		Assert.assertEquals(1, (int) left.getConflictTokens().get(ChipValue.NEG1));
		Assert.assertEquals(1, (int) middle.getConflictTokens().get(ChipValue.NEG1));
		Assert.assertEquals(2, (int) right.getConflictTokens().get(ChipValue.FIVE));

		Assert.assertEquals(0, (int) left.getConflictTokens().get(ChipValue.FIVE));
		Assert.assertEquals(0, (int) middle.getConflictTokens().get(ChipValue.FIVE));
		Assert.assertEquals(0, (int) right.getConflictTokens().get(ChipValue.NEG1));
	}

	@Test
	public void endAge37PlayersAssortedWarTokens() {
		ArrayList<Player> players = setUpPlayersByNum(7);
		int[] playerShields = { 2, 3, 5, 5, 7, 6, 3 };
		int[] numCalls = { 3, 4, 4, 4, 3, 2, 2 };
		expectAndReplayAddNumShields(players, playerShields, numCalls);

		TurnHandler turnHandler = new TurnHandler();
		turnHandler.endAge(players, Age.AGE3);

		verifyPlayers(players);

		Assert.assertEquals(2, (int) players.get(0).getConflictTokens().get(ChipValue.NEG1));
		Assert.assertEquals(1, (int) players.get(1).getConflictTokens().get(ChipValue.NEG1));
		Assert.assertEquals(1, (int) players.get(1).getConflictTokens().get(ChipValue.FIVE));
		Assert.assertEquals(1, (int) players.get(2).getConflictTokens().get(ChipValue.FIVE));
		Assert.assertEquals(1, (int) players.get(3).getConflictTokens().get(ChipValue.NEG1));
		Assert.assertEquals(2, (int) players.get(4).getConflictTokens().get(ChipValue.FIVE));
		Assert.assertEquals(1, (int) players.get(5).getConflictTokens().get(ChipValue.NEG1));
		Assert.assertEquals(1, (int) players.get(5).getConflictTokens().get(ChipValue.FIVE));
		Assert.assertEquals(1, (int) players.get(6).getConflictTokens().get(ChipValue.NEG1));
		Assert.assertEquals(1, (int) players.get(6).getConflictTokens().get(ChipValue.FIVE));

	}
	
	@Test
	public void testEndAgeShufflesDeck() {
		ArrayList<String> playerNames = setUpArrayByNum(3);
		TurnHandler turnHandler = new TurnHandler();
		SetUpPlayerHandler setUpPlayer = EasyMock.partialMockBuilder(SetUpPlayerHandler.class).withConstructor(GameMode.EASY)
				.createMock();
		PlayerTurnHandler playerTurnHandler = EasyMock.partialMockBuilder(PlayerTurnHandler.class).withConstructor().createMock();
		SetUpDeckHandler setUpDeckHandler = EasyMock.partialMockBuilder(SetUpDeckHandler.class).withConstructor().createMock();
		Handlers handlers = new Handlers(setUpPlayer);
		handlers.setSetUpDeckHandler(setUpDeckHandler);
		handlers.setTurnHandler(turnHandler);
		handlers.setPlayerTurnHandler(playerTurnHandler);
		GameManager manager = new GameManager(playerNames, GameMode.EASY);
		ArrayList<Card> cards2 = setUpDeckHandler.createCards(Age.AGE2, 3);
		Deck deck2 = new Deck(Age.AGE2, cards2);
		ArrayList<Card> cardsBefore = new ArrayList<Card>(deck2.getCards());
		turnHandler.dealInitialTurnCards(manager.getPlayers(), deck2);

		turnHandler.handlers = handlers;
		turnHandler.setGameBoard(manager.getGameBoard());
		turnHandler.endAge(Age.AGE2);

		assertFalse(cardsBefore.toString().equals(manager.getDeck().getCards().toString()));

	}
	
	@Test
	public void testEndCurrentPlayerTurn() {
		ArrayList<String> playerNames = setUpArrayByNum(3);
		setUpPlayerHandler = EasyMock.partialMockBuilder(SetUpPlayerHandler.class).withConstructor(GameMode.EASY)
				.createMock();
	
		PlayerTurnHandler playerTurnHandler = EasyMock.partialMockBuilder(PlayerTurnHandler.class).withConstructor().createMock();
		SetUpDeckHandler setUpDeckHandler = EasyMock.partialMockBuilder(SetUpDeckHandler.class).withConstructor().createMock();
		
		TurnHandler turnHandler = new TurnHandler();
		Handlers handlers = new Handlers(this.setUpPlayerHandler);
		handlers.setSetUpDeckHandler(setUpDeckHandler);
		handlers.setTurnHandler(turnHandler);
		handlers.setPlayerTurnHandler(playerTurnHandler);

		GameManager manager = new GameManager(playerNames, handlers);
		manager.dealInitialTurnCards();

		assertEquals("", manager.endCurrentPlayerTurn());

	}

	@Test
	public void testEndCurrentPlayerTurnNonMockedForMutationCoverage() {
		ArrayList<String> playerNames = setUpArrayByNum(3);
		GameManager manager = new GameManager(playerNames, GameMode.EASY);

		Player expectedNewCurrentPlayer = manager.getNextPlayer();
		Player expectedNewPreviousPlayer = manager.getCurrentPlayer();

		manager.dealInitialTurnCards();
		assertEquals("", manager.endCurrentPlayerTurn());
		assertEquals(expectedNewCurrentPlayer, manager.getCurrentPlayer());
		assertEquals(expectedNewPreviousPlayer, manager.getPreviousPlayer());
	}

	@Test
	public void testEndCurrentPlayersTurn3TimesAndTradeHands() {
		ArrayList<String> playerNames = setUpArrayByNum(3);
		setUpPlayerHandler = EasyMock.partialMockBuilder(SetUpPlayerHandler.class).withConstructor(GameMode.EASY)
				.createMock();
	
		PlayerTurnHandler playerTurnHandler = EasyMock.partialMockBuilder(PlayerTurnHandler.class).withConstructor().createMock();
		SetUpDeckHandler setUpDeckHandler = EasyMock.partialMockBuilder(SetUpDeckHandler.class).withConstructor().createMock();
		
		TurnHandler turnHandler = new TurnHandler();
		Handlers handlers = new Handlers(this.setUpPlayerHandler);
		handlers.setSetUpDeckHandler(setUpDeckHandler);
		handlers.setTurnHandler(turnHandler);
		handlers.setPlayerTurnHandler(playerTurnHandler);

		GameManager manager = new GameManager(playerNames, handlers);
		manager.dealInitialTurnCards();

		assertEquals("", manager.endCurrentPlayerTurn());
		assertEquals("", manager.endCurrentPlayerTurn());
		assertEquals("End of current rotation.  Switching Player hands.", manager.endCurrentPlayerTurn());
	}

	@Test
	public void testEndCurrentPlayersTurn3TimesAndTradeHands5Players() {
		ArrayList<String> playerNames = setUpArrayByNum(3);
		setUpPlayerHandler = EasyMock.partialMockBuilder(SetUpPlayerHandler.class).withConstructor(GameMode.EASY)
				.createMock();
	
		PlayerTurnHandler playerTurnHandler = EasyMock.partialMockBuilder(PlayerTurnHandler.class).withConstructor().createMock();
		SetUpDeckHandler setUpDeckHandler = EasyMock.partialMockBuilder(SetUpDeckHandler.class).withConstructor().createMock();
		
		TurnHandler turnHandler = new TurnHandler();
		Handlers handlers = new Handlers(this.setUpPlayerHandler);
		handlers.setSetUpDeckHandler(setUpDeckHandler);
		handlers.setTurnHandler(turnHandler);
		handlers.setPlayerTurnHandler(playerTurnHandler);

		GameManager manager = new GameManager(playerNames, handlers);
		manager.dealInitialTurnCards();
		turnHandler.setNumPlayersUntilPass(4);

		Player expectedNewCurrentPlayer = manager.getCurrentPlayer();
		ArrayList<Card> expectedNewCurrentHand = manager.getPreviousPlayer().getCurrentHand();

		assertEquals("", manager.endCurrentPlayerTurn());
		assertEquals("", manager.endCurrentPlayerTurn());
		assertEquals("", manager.endCurrentPlayerTurn());
		assertEquals("", manager.endCurrentPlayerTurn());
		assertEquals("End of current rotation.  Switching Player hands.", manager.endCurrentPlayerTurn());

	}

	@Test
	public void testEndPlayerTurnEndsCurrentAge() {
		ArrayList<String> playerNames = setUpArrayByNum(3);
		setUpPlayerHandler = EasyMock.partialMockBuilder(SetUpPlayerHandler.class).withConstructor(GameMode.EASY)
				.createMock();
	
		PlayerTurnHandler playerTurnHandler = EasyMock.partialMockBuilder(PlayerTurnHandler.class).withConstructor().createMock();
		SetUpDeckHandler setUpDeckHandler = EasyMock.partialMockBuilder(SetUpDeckHandler.class).withConstructor().createMock();
		
		TurnHandler turnHandler = new TurnHandler();

		ArrayList<Card> cards2 = setUpDeckHandler.createCards(Age.AGE2, 3);
		Deck deck2 = new Deck(Age.AGE2, cards2);

		Handlers handlers = new Handlers(this.setUpPlayerHandler);
		handlers.setSetUpDeckHandler(setUpDeckHandler);
		handlers.setTurnHandler(turnHandler);
		handlers.setPlayerTurnHandler(playerTurnHandler);
		GameManager manager = new GameManager(playerNames, handlers);
		manager.dealInitialTurnCards();

		ArrayList<Card> previousCurrentCards = manager.getCurrentPlayer().getCurrentHand();

		assertEquals(7, manager.getCurrentPlayer().getCurrentHand().size());


		turnHandler.dealInitialTurnCards(manager.getPlayers(), deck2);
		turnHandler.endAge(manager.getPlayers(), Age.AGE1);
		//turnHandler.rotateCounterClockwise();

		for (int numCalls = 0; numCalls < 17; numCalls++) {
			manager.endCurrentPlayerTurn();
		}

		assertEquals("This is the end of the Age.  Finalizing Points.", manager.endCurrentPlayerTurn());
		assertEquals(7, manager.getCurrentPlayer().getCurrentHand().size());
		assertEquals(Rotation.COUNTERCLOCKWISE, manager.getDirection());
		assertFalse(manager.getCurrentPlayer().getCurrentHand().equals(previousCurrentCards));
		assertEquals(Age.AGE2, manager.getGameBoard().getDeck().getAge());
	}

	@Test
	public void testEndPlayerTurnEndsCurrentAgeTo3() {
		setUpPlayerHandler = EasyMock.partialMockBuilder(SetUpPlayerHandler.class).withConstructor(GameMode.EASY)
				.createMock();
	
		PlayerTurnHandler playerTurnHandler = EasyMock.partialMockBuilder(PlayerTurnHandler.class).withConstructor().createMock();
		SetUpDeckHandler setUpDeckHandler = EasyMock.partialMockBuilder(SetUpDeckHandler.class).withConstructor().createMock();
		
		ArrayList<String> playerNames = setUpArrayByNum(3);
		TurnHandler turnHandler = new TurnHandler();

		ArrayList<Card> cards2 = setUpDeckHandler.createCards(Age.AGE2, 3);
		Deck deck2 = new Deck(Age.AGE2, cards2);
		ArrayList<Card> cards3 = setUpDeckHandler.createCards(Age.AGE3, 3);
		Deck deck3 = new Deck(Age.AGE3, cards3);

		Handlers handlers = new Handlers(this.setUpPlayerHandler);
		handlers.setSetUpDeckHandler(setUpDeckHandler);
		handlers.setTurnHandler(turnHandler);
		handlers.setPlayerTurnHandler(playerTurnHandler);

		GameManager manager = new GameManager(playerNames, handlers);
		manager.getGameBoard().setDeck(deck2);
		manager.dealInitialTurnCards();
		manager.changeRotateDirectionAndResetPositions(Rotation.COUNTERCLOCKWISE);
		
		turnHandler.setGameBoard(manager.getGameBoard());

		ArrayList<Card> previousCurrentCards = manager.getCurrentPlayer().getCurrentHand();

		assertEquals(7, manager.getCurrentPlayer().getCurrentHand().size());

		turnHandler.dealInitialTurnCards(manager.getPlayers(), deck3);

		for (int numCalls = 0; numCalls < 17; numCalls++) {
			manager.endCurrentPlayerTurn();
		}

		assertEquals("This is the end of the Age.  Finalizing Points.", manager.endCurrentPlayerTurn());
		assertEquals(7, manager.getCurrentPlayer().getCurrentHand().size());
		assertEquals(Rotation.CLOCKWISE, manager.getDirection());
		assertFalse(manager.getCurrentPlayer().getCurrentHand().equals(previousCurrentCards));
		assertEquals(Age.AGE3, manager.getGameBoard().getDeck().getAge());
		assertTrue(manager.getGameBoard().getDeck().getCards().isEmpty());

	}
	
	@Test
	public void testRotateClockWise(){
		RotateHandler rotate = EasyMock.mock(RotateHandler.class);
		rotate.rotateClockwise();
		EasyMock.replay(rotate);
		
		Handlers handlers = new Handlers(GameMode.EASY);
		handlers.setRotateHandler(rotate);
		
		TurnHandler turnHandler = new TurnHandler();
		turnHandler.handlers = handlers;
		
		turnHandler.rotate(Rotation.CLOCKWISE);
		
		EasyMock.verify(rotate);
	}

	@Test
	public void testRotateCounterClockWise(){
		RotateHandler rotate = EasyMock.mock(RotateHandler.class);
		rotate.rotateCounterClockwise();
		EasyMock.replay(rotate);
		
		Handlers handlers = new Handlers(GameMode.EASY);
		handlers.setRotateHandler(rotate);
		
		TurnHandler turnHandler = new TurnHandler();
		turnHandler.handlers = handlers;
		
		turnHandler.rotate(Rotation.COUNTERCLOCKWISE);
		
		EasyMock.verify(rotate);
	}

	private ArrayList<Player> setUpPlayersByNum(int num) {
		ArrayList<Player> result = new ArrayList<Player>();
		Wonder wonder = EasyMock.createStrictMock(Wonder.class);
		for (int i = 0; i < num; i++) {
			Player temp = EasyMock.partialMockBuilder(Player.class).withConstructor("Jane Doe", wonder)
					.addMockedMethod("getNumShields").createMock();
			result.add(temp);
		}
		return result;
	}

	private ArrayList<String> setUpNamesByNum(int num) {
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < num; i++) {
			result.add("Jane Doe");
		}
		return result;
	}

	private void expectAndReplayAddNumShields(ArrayList<Player> mockedPlayers, int[] playerShields, int[] numCalls) {
		int i = 0;
		for (Player player : mockedPlayers) {
			callExpected(player, playerShields[i], numCalls[i]);
			EasyMock.replay(player);
			i++;
		}
	}

	private void callExpected(Player player, int playerShields, int numCalls) {
		for (int i = 0; i < numCalls; i++) {
			EasyMock.expect(player.getNumShields()).andReturn(playerShields);
		}
	}

	private void verifyPlayers(ArrayList<Player> mockedPlayers) {
		for (Player player : mockedPlayers) {
			EasyMock.verify(player);
		}
	}

	private Handlers setUpHandlers() {
		Handlers handlers = new Handlers(this.setUpPlayerHandler);
		handlers.setSetUpDeckHandler(new SetUpDeckHandler());
		handlers.setTurnHandler(new TurnHandler());
		handlers.setPlayerTurnHandler(new PlayerTurnHandler());
		return handlers;
	}
	
	private ArrayList<String> setUpArrayByNum(int num) {
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < num; i++) {
			result.add("Jane Doe " + i);
		}

		return result;
	}
}