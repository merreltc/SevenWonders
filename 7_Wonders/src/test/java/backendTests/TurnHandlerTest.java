package backendTests;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import backend.GameManager;
import backend.handlers.PlayerTurnHandler;
import backend.handlers.SetUpDeckHandler;
import backend.handlers.SetUpPlayerHandler;
import backend.handlers.TurnHandler;
import constants.GeneralEnums.GameMode;
import constants.GeneralEnums.Side;
import dataStructures.gameMaterials.Card;
import dataStructures.gameMaterials.Deck;
import dataStructures.gameMaterials.Deck.Age;
import dataStructures.gameMaterials.Wonder.WonderType;
import dataStructures.gameMaterials.Effect;
import dataStructures.gameMaterials.EntityEffect;
import dataStructures.gameMaterials.Wonder;
import dataStructures.playerData.Chip.ChipValue;
import dataStructures.playerData.Player;
import backend.handlers.DeckHandler;
import backend.handlers.RotateHandler;
import backend.handlers.RotateHandler.Rotation;
import dataStructures.GameBoard;
import dataStructures.Handlers;

public class TurnHandlerTest {
	// BEGIN GENERATED CODE
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
		new TurnHandler(EasyMock.mock(Handlers.class)).dealInitialTurnCards(players, deck);

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
		new TurnHandler(EasyMock.mock(Handlers.class)).dealInitialTurnCards(players, deck);

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
		new TurnHandler(EasyMock.mock(Handlers.class)).dealInitialTurnCards(players, deck);

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
	public void endAge1ThreePlayersMiddleWithMostWarTokens() {
		ArrayList<Player> players = setUpPlayersByNum(3);
		// array order: middle, left, right player
		int[] playerShields = { 5, 2, 2 };
		int[] numCalls = { 3, 3, 4 };

		expectAndReplayAddNumShields(players, playerShields, numCalls);

		TurnHandler turnHandler = new TurnHandler(EasyMock.mock(Handlers.class));
		turnHandler.performEndAgeBattles(players, Age.AGE1);

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

		TurnHandler turnHandler = new TurnHandler(EasyMock.mock(Handlers.class));
		turnHandler.performEndAgeBattles(players, Age.AGE1);

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

		TurnHandler turnHandler = new TurnHandler(EasyMock.mock(Handlers.class));
		turnHandler.performEndAgeBattles(players, Age.AGE1);

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

		TurnHandler turnHandler = new TurnHandler(EasyMock.mock(Handlers.class));
		turnHandler.performEndAgeBattles(players, Age.AGE2);
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

		TurnHandler turnHandler = new TurnHandler(EasyMock.mock(Handlers.class));
		turnHandler.performEndAgeBattles(players, Age.AGE2);

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

		TurnHandler turnHandler = new TurnHandler(EasyMock.mock(Handlers.class));
		turnHandler.performEndAgeBattles(players, Age.AGE2);

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

		TurnHandler turnHandler = new TurnHandler(EasyMock.mock(Handlers.class));
		turnHandler.performEndAgeBattles(players, Age.AGE3);
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

		TurnHandler turnHandler = new TurnHandler(EasyMock.mock(Handlers.class));
		turnHandler.performEndAgeBattles(players, Age.AGE3);

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

		TurnHandler turnHandler = new TurnHandler(EasyMock.mock(Handlers.class));
		turnHandler.performEndAgeBattles(players, Age.AGE3);

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
		SetUpPlayerHandler setUpPlayer = EasyMock.partialMockBuilder(SetUpPlayerHandler.class)
				.withConstructor(GameMode.EASY).createMock();
		PlayerTurnHandler playerTurnHandler = EasyMock.partialMockBuilder(PlayerTurnHandler.class).withConstructor()
				.createMock();
		SetUpDeckHandler setUpDeckHandler = EasyMock.partialMockBuilder(SetUpDeckHandler.class).withConstructor()
				.createMock();
		Handlers handlers = new Handlers(setUpPlayer);
		handlers.setSetUpDeckHandler(setUpDeckHandler);
		TurnHandler turnHandler = EasyMock.partialMockBuilder(TurnHandler.class).withConstructor(handlers)
				.addMockedMethod("switchDeck").addMockedMethod("dealInitialTurnCards").createMock();
		handlers.setTurnHandler(turnHandler);
		handlers.setPlayerTurnHandler(playerTurnHandler);

		GameManager manager = new GameManager(playerNames, GameMode.EASY);
		ArrayList<Card> cards2 = setUpDeckHandler.createCards(Age.AGE2, 3);
		Deck deck2 = new Deck(Age.AGE2, cards2);
		ArrayList<Card> cardsBefore = new ArrayList<Card>(deck2.getCards());

		turnHandler.setGameBoard(manager.getGameBoard());
		EasyMock.expect(turnHandler.switchDeck(Age.AGE2)).andReturn(deck2);
		turnHandler.dealInitialTurnCards(manager.getPlayers(), deck2);

		EasyMock.replay(turnHandler);

		turnHandler.endAge(Age.AGE2);

		assertFalse(cardsBefore.toString().equals(manager.getDeck().getCards().toString()));
		EasyMock.verify(turnHandler);
	}

	@Test
	public void testEndCurrentPlayerTurn() {
		ArrayList<String> playerNames = setUpArrayByNum(3);
		setUpPlayerHandler = EasyMock.partialMockBuilder(SetUpPlayerHandler.class).withConstructor(GameMode.EASY)
				.createMock();

		PlayerTurnHandler playerTurnHandler = EasyMock.partialMockBuilder(PlayerTurnHandler.class).withConstructor()
				.createMock();
		SetUpDeckHandler setUpDeckHandler = EasyMock.partialMockBuilder(SetUpDeckHandler.class).withConstructor()
				.createMock();

		Handlers handlers = new Handlers(this.setUpPlayerHandler);
		handlers.setSetUpDeckHandler(setUpDeckHandler);
		TurnHandler turnHandler = new TurnHandler(EasyMock.mock(Handlers.class));
		handlers.setTurnHandler(turnHandler);
		handlers.setPlayerTurnHandler(playerTurnHandler);

		GameManager manager = new GameManager(playerNames, handlers);
		Player expectedNewCurrentPlayer = manager.getNextPlayer();
		Player expectedNewPreviousPlayer = manager.getCurrentPlayer();

		manager.dealInitialTurnCards();
		turnHandler.setGameBoard(manager.getGameBoard());

		assertEquals("", turnHandler.endCurrentPlayerTurn(handlers));
		assertEquals(expectedNewCurrentPlayer, manager.getCurrentPlayer());
		assertEquals(expectedNewPreviousPlayer, manager.getPreviousPlayer());

	}

	@Test
	public void testEndCurrentPlayersTurn3TimesAndTradeHands() {
		ArrayList<String> playerNames = setUpArrayByNum(3);
		setUpPlayerHandler = EasyMock.partialMockBuilder(SetUpPlayerHandler.class).withConstructor(GameMode.EASY)
				.createMock();

		PlayerTurnHandler playerTurnHandler = EasyMock.partialMockBuilder(PlayerTurnHandler.class).withConstructor()
				.createMock();
		SetUpDeckHandler setUpDeckHandler = EasyMock.partialMockBuilder(SetUpDeckHandler.class).withConstructor()
				.createMock();

		Handlers handlers = new Handlers(this.setUpPlayerHandler);
		handlers.setSetUpDeckHandler(setUpDeckHandler);

		TurnHandler turnHandler = new TurnHandler(EasyMock.mock(Handlers.class));
		handlers.setTurnHandler(turnHandler);
		handlers.setPlayerTurnHandler(playerTurnHandler);

		GameManager manager = new GameManager(playerNames, handlers);
		manager.dealInitialTurnCards();
		turnHandler.setGameBoard(manager.getGameBoard());
		Player expectedNewCurrentPlayer = manager.getCurrentPlayer();
		ArrayList<Card> expectedNewCurrentHand = manager.getPreviousPlayer().getCurrentHand();

		assertEquals("", manager.endCurrentPlayerTurn());
		assertEquals("", manager.endCurrentPlayerTurn());
		assertEquals("End of current rotation.  Switching Player hands.", manager.endCurrentPlayerTurn());

		assertEquals(expectedNewCurrentPlayer, manager.getCurrentPlayer());
		assertEquals(expectedNewCurrentHand, manager.getCurrentPlayer().getCurrentHand());
	}

	@Test
	public void testEndCurrentPlayersTurn3TimesAndTradeHands5Players() {
		ArrayList<String> playerNames = setUpArrayByNum(5);
		setUpPlayerHandler = EasyMock.partialMockBuilder(SetUpPlayerHandler.class).withConstructor(GameMode.EASY)
				.createMock();

		PlayerTurnHandler playerTurnHandler = EasyMock.partialMockBuilder(PlayerTurnHandler.class).withConstructor()
				.createMock();
		SetUpDeckHandler setUpDeckHandler = EasyMock.partialMockBuilder(SetUpDeckHandler.class).withConstructor()
				.createMock();

		Handlers handlers = new Handlers(this.setUpPlayerHandler);
		handlers.setSetUpDeckHandler(setUpDeckHandler);

		TurnHandler turnHandler = new TurnHandler(EasyMock.mock(Handlers.class));
		handlers.setTurnHandler(turnHandler);
		handlers.setPlayerTurnHandler(playerTurnHandler);

		GameManager manager = new GameManager(playerNames, handlers);
		manager.dealInitialTurnCards();
		turnHandler.setGameBoard(manager.getGameBoard());

		assertEquals("", turnHandler.endCurrentPlayerTurn(handlers));
		assertEquals("", turnHandler.endCurrentPlayerTurn(handlers));
		assertEquals("", turnHandler.endCurrentPlayerTurn(handlers));
		assertEquals("", turnHandler.endCurrentPlayerTurn(handlers));
		assertEquals("End of current rotation.  Switching Player hands.", turnHandler.endCurrentPlayerTurn(handlers));
	}

	@Test
	public void testEndPlayerTurnEndsCurrentAge() {
		ArrayList<Player> players = new ArrayList<Player>();
		Player player1 = EasyMock.partialMockBuilder(Player.class)
				.withConstructor("Jane Doe", new Wonder(Side.A, WonderType.COLOSSUS)).addMockedMethod("getNumShields")
				.createMock();
		Player player2 = EasyMock.partialMockBuilder(Player.class)
				.withConstructor("John Doe", new Wonder(Side.A, WonderType.GARDENS)).addMockedMethod("getNumShields")
				.createMock();
		Player player3 = EasyMock.partialMockBuilder(Player.class)
				.withConstructor("James Doe", new Wonder(Side.A, WonderType.MAUSOLEUM)).addMockedMethod("getNumShields")
				.createMock();
		players.add(player1);
		players.add(player2);
		players.add(player3);

		ArrayList<Card> cards2 = new SetUpDeckHandler().createCards(Age.AGE2, 3);
		Deck deck2 = new Deck(Age.AGE2, cards2);
		GameBoard board = new GameBoard(players, deck2);
		Handlers handlers = setUpHandlers();
		TurnHandler turnHandler = new TurnHandler(handlers);
		turnHandler.setGameBoard(board);
		turnHandler.dealInitialTurnCards(players, deck2);
		handlers.setTurnHandler(turnHandler);
		handlers.setRotateHandler(new RotateHandler(board));

		EasyMock.expect(player1.getNumShields()).andReturn(4);
		EasyMock.expect(player2.getNumShields()).andReturn(2);
		EasyMock.expect(player3.getNumShields()).andReturn(3);
		EasyMock.expect(player1.getNumShields()).andReturn(4);
		EasyMock.expect(player2.getNumShields()).andReturn(2);
		EasyMock.expect(player3.getNumShields()).andReturn(3);
		EasyMock.expect(player1.getNumShields()).andReturn(4);
		EasyMock.expect(player2.getNumShields()).andReturn(2);
		EasyMock.expect(player3.getNumShields()).andReturn(3);
		EasyMock.expect(player3.getNumShields()).andReturn(3);

		EasyMock.replay(player1, player2, player3);
		;
		ArrayList<Card> previousCurrentCards = players.get(0).getCurrentHand();

		for (int numCalls = 0; numCalls < 17; numCalls++) {
			turnHandler.endCurrentPlayerTurn(handlers);
		}

		assertEquals("This is the end of the Age.  Finalizing Points.", turnHandler.endCurrentPlayerTurn(handlers));
		assertFalse(players.get(0).getCurrentHand().equals(previousCurrentCards));
		assertEquals(Age.AGE3, board.getDeck().getAge());
		assertEquals(6, player1.getConflictTotal());
		assertEquals(-2, player2.getConflictTotal());
		assertEquals(2, player3.getConflictTotal());
		EasyMock.verify(player1, player2, player3);
	}

	@Test
	public void testEndPlayerTurnEndsCurrentAgeTo3() {
		setUpPlayerHandler = EasyMock.partialMockBuilder(SetUpPlayerHandler.class).withConstructor(GameMode.EASY)
				.createMock();

		PlayerTurnHandler playerTurnHandler = EasyMock.partialMockBuilder(PlayerTurnHandler.class).withConstructor()
				.createMock();
		SetUpDeckHandler setUpDeckHandler = EasyMock.partialMockBuilder(SetUpDeckHandler.class).withConstructor()
				.createMock();

		ArrayList<String> playerNames = setUpArrayByNum(3);

		ArrayList<Card> cards2 = setUpDeckHandler.createCards(Age.AGE2, 3);
		Deck deck2 = new Deck(Age.AGE2, cards2);
		ArrayList<Card> cards3 = setUpDeckHandler.createCards(Age.AGE3, 3);
		Deck deck3 = new Deck(Age.AGE3, cards3);

		Handlers handlers = new Handlers(this.setUpPlayerHandler);
		handlers.setSetUpDeckHandler(setUpDeckHandler);
		TurnHandler turnHandler = new TurnHandler(handlers);
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
	public void testRotateClockWise() {
		RotateHandler rotate = EasyMock.mock(RotateHandler.class);
		rotate.rotateClockwise();
		EasyMock.replay(rotate);

		Handlers handlers = new Handlers(GameMode.EASY);
		handlers.setRotateHandler(rotate);

		TurnHandler turnHandler = new TurnHandler(handlers);

		turnHandler.rotate(Rotation.CLOCKWISE);

		EasyMock.verify(rotate);
	}

	@Test
	public void testRotateCounterClockWise() {
		RotateHandler rotate = EasyMock.mock(RotateHandler.class);
		rotate.rotateCounterClockwise();
		EasyMock.replay(rotate);

		Handlers handlers = new Handlers(GameMode.EASY);
		handlers.setRotateHandler(rotate);

		TurnHandler turnHandler = new TurnHandler(handlers);

		turnHandler.rotate(Rotation.COUNTERCLOCKWISE);

		EasyMock.verify(rotate);
	}

	@Test
	public void testEndGame() {
		Wonder wonder = new Wonder(Side.A, WonderType.COLOSSUS);
		Deck deck = EasyMock.mock(Deck.class);
		Player player1 = new Player("Player1", wonder);
		player1.addNumVictoryPoints(6);

		Player player2 = new Player("Player2", wonder);
		player2.addNumVictoryPoints(4);

		Player player3 = new Player("Player3", wonder);
		player3.addNumVictoryPoints(2);

		ArrayList<Player> players = new ArrayList<Player>(Arrays.asList(player1, player2, player3));

		GameBoard board = EasyMock.mock(GameBoard.class);
		EasyMock.expect(board.getAge()).andReturn(Age.AGE3);
		for (int i = 0; i < 6; i++) {
			EasyMock.expect(board.getPlayers()).andReturn(players);
		}

		EasyMock.replay(deck, board);

		GameBoard board2 = new GameBoard(players, deck);

		TurnHandler turnHandler = new TurnHandler(EasyMock.mock(Handlers.class));
		turnHandler.setGameBoard(board);
		String result = turnHandler.switchAge();

		String expected = "Player1 : 7\nPlayer2 : 5\nPlayer3 : 3\nPlayer1 Wins!";

		assertEquals(expected, result);

		EasyMock.verify(board);

	}

	@Test
	public void testIndexOfMaxPlayer() {
		ArrayList<Integer> scores = new ArrayList<Integer>(Arrays.asList(20, 35, 16));
		ArrayList<Player> players = new ArrayList<Player>();
		Player player1 = EasyMock.mock(Player.class);
		players.add(player1);
		players.add(player1);
		players.add(player1);
		EasyMock.replay(player1);

		TurnHandler turnHandler = new TurnHandler(EasyMock.mock(Handlers.class));

		int index = turnHandler.indexOfMaxScore(scores, players);
		assertEquals(1, index);
	}

	@Test
	public void testSwitchDeckToAgeThree() {
		Wonder wonder = new Wonder(Side.A, WonderType.COLOSSUS);
		Player player = new Player("Jane Doe", wonder);
		Handlers handlers = EasyMock.mock(Handlers.class);
		SetUpDeckHandler deckHandler = EasyMock.mock(SetUpDeckHandler.class);
		Deck deck = EasyMock.mock(Deck.class);
		EasyMock.expect(handlers.getSetUpDeckHandler()).andReturn(deckHandler);
		EasyMock.expect(deckHandler.createDeck(Age.AGE2, 3)).andReturn(deck);
		GameBoard gameBoard = new GameBoard(new ArrayList<Player>(Arrays.asList(player, player, player)), deck);

		EasyMock.replay(handlers, deckHandler, deck);

		TurnHandler turnHandler = new TurnHandler(handlers);
		turnHandler.setGameBoard(gameBoard);

		assertEquals(deck, turnHandler.switchDeck(Age.AGE1));

		EasyMock.verify(handlers, deckHandler, deck);
	}

	@Test
	public void testIndexOfMaxPlayerTie() {
		ArrayList<Integer> scores = new ArrayList<Integer>(Arrays.asList(35, 35, 35));
		ArrayList<Player> players = new ArrayList<Player>();
		Player player1 = EasyMock.mock(Player.class);
		Player player2 = EasyMock.mock(Player.class);
		Player player3 = EasyMock.mock(Player.class);
		EasyMock.expect(player1.getCoinTotal()).andReturn(10);
		EasyMock.expect(player2.getCoinTotal()).andReturn(13);
		EasyMock.expect(player3.getCoinTotal()).andReturn(11);
		EasyMock.expect(player1.getCoinTotal()).andReturn(10);
		EasyMock.expect(player2.getCoinTotal()).andReturn(13);
		EasyMock.expect(player3.getCoinTotal()).andReturn(11);

		players.add(player1);
		players.add(player3);
		players.add(player2);
		EasyMock.replay(player1, player2);

		TurnHandler turnHandler = new TurnHandler(EasyMock.mock(Handlers.class));

		int index = turnHandler.indexOfMaxScore(scores, players);
		assertEquals(2, index);
	}

	private ArrayList<Player> setUpPlayersByNum(int num) {
		ArrayList<Player> result = new ArrayList<Player>();
		Wonder wonder = new Wonder(Side.A, WonderType.COLOSSUS);
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
			callExpectedNumShields(player, playerShields[i], numCalls[i]);
			EasyMock.replay(player);
			i++;
		}
	}

	private void callExpectedNumShields(Player player, int playerShields, int numCalls) {

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
		handlers.setTurnHandler(new TurnHandler(handlers));
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
	// END GENERATED CODE
}