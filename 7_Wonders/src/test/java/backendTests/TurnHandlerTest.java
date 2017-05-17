package backendTests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

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
import dataStructures.gameMaterials.Card;
import dataStructures.gameMaterials.Deck;
import dataStructures.gameMaterials.Deck.Age;
import dataStructures.gameMaterials.Wonder;
import dataStructures.playerData.Player;

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
		GameManager manager = new GameManager(playerNames, this.setUpPlayerHandler, new SetUpDeckHandler(),
				new TurnHandler(), new PlayerTurnHandler());

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
		GameManager manager = new GameManager(playerNames, this.setUpPlayerHandler, new SetUpDeckHandler(),
				new TurnHandler(), new PlayerTurnHandler());

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
		GameManager manager = new GameManager(playerNames, this.setUpPlayerHandler, new SetUpDeckHandler(),
				new TurnHandler(), new PlayerTurnHandler());

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
		GameManager manager = new GameManager(playerNames, this.setUpPlayerHandler, new SetUpDeckHandler(),
				new TurnHandler(), new PlayerTurnHandler());

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
		GameManager manager = new GameManager(playerNames, this.setUpPlayerHandler, new SetUpDeckHandler(),
				new TurnHandler(), new PlayerTurnHandler());

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
		Deck deck = EasyMock.partialMockBuilder(Deck.class).addMockedMethod("getCards").createMock();
		ArrayList<Card> cards = EasyMock.createStrictMock(ArrayList.class);
		Card card = EasyMock.createStrictMock(Card.class);
		expectAndReplayDeckCalls(deck, cards, card);

		TurnHandler turnHandler = new TurnHandler();
		turnHandler.dealInitialTurnCards(players, deck);

		EasyMock.verify(deck, cards, card);
		assertEquals(5, turnHandler.getNumTurnsTilEndOfAge());
	}

	private void expectAndReplayDeckCalls(Deck deck, ArrayList<Card> cards, Card card) {
		for (int i = 0; i < 35; i++) {
			EasyMock.expect(deck.getCards()).andReturn(cards);
			EasyMock.expect(cards.remove(0)).andReturn(card);
		}
		EasyMock.replay(deck, cards, card);
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

		Assert.assertEquals(1, left.getNumValueNeg1ConflictTokens());
		Assert.assertEquals(2, middle.getNumValue1ConflictTokens());
		Assert.assertEquals(1, right.getNumValueNeg1ConflictTokens());
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

		Assert.assertEquals(1, players.get(0).getNumValueNeg1ConflictTokens());
		Assert.assertEquals(1, players.get(1).getNumValueNeg1ConflictTokens());
		Assert.assertEquals(2, players.get(2).getNumValue1ConflictTokens());

		Assert.assertEquals(0, players.get(0).getNumValue1ConflictTokens());
		Assert.assertEquals(0, players.get(1).getNumValue1ConflictTokens());
		Assert.assertEquals(0, players.get(2).getNumValueNeg1ConflictTokens());
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

		Assert.assertEquals(2, players.get(0).getNumValueNeg1ConflictTokens());
		Assert.assertEquals(1, players.get(1).getNumValueNeg1ConflictTokens());
		Assert.assertEquals(1, players.get(1).getNumValue1ConflictTokens());
		Assert.assertEquals(1, players.get(2).getNumValue1ConflictTokens());
		Assert.assertEquals(1, players.get(3).getNumValueNeg1ConflictTokens());
		Assert.assertEquals(2, players.get(4).getNumValue1ConflictTokens());
		Assert.assertEquals(1, players.get(5).getNumValueNeg1ConflictTokens());
		Assert.assertEquals(1, players.get(5).getNumValue1ConflictTokens());
		Assert.assertEquals(1, players.get(6).getNumValueNeg1ConflictTokens());
		Assert.assertEquals(1, players.get(6).getNumValue1ConflictTokens());
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

		Assert.assertEquals(1, left.getNumValueNeg1ConflictTokens());
		Assert.assertEquals(2, middle.getNumValue3ConflictTokens());
		Assert.assertEquals(1, right.getNumValueNeg1ConflictTokens());
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

		Assert.assertEquals(1, left.getNumValueNeg1ConflictTokens());
		Assert.assertEquals(1, middle.getNumValueNeg1ConflictTokens());
		Assert.assertEquals(2, right.getNumValue3ConflictTokens());

		Assert.assertEquals(0, left.getNumValue1ConflictTokens());
		Assert.assertEquals(0, middle.getNumValue1ConflictTokens());
		Assert.assertEquals(0, right.getNumValueNeg1ConflictTokens());
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

		Assert.assertEquals(2, players.get(0).getNumValueNeg1ConflictTokens());
		Assert.assertEquals(1, players.get(1).getNumValueNeg1ConflictTokens());
		Assert.assertEquals(1, players.get(1).getNumValue3ConflictTokens());
		Assert.assertEquals(1, players.get(2).getNumValue3ConflictTokens());
		Assert.assertEquals(1, players.get(3).getNumValueNeg1ConflictTokens());
		Assert.assertEquals(2, players.get(4).getNumValue3ConflictTokens());
		Assert.assertEquals(1, players.get(5).getNumValueNeg1ConflictTokens());
		Assert.assertEquals(1, players.get(5).getNumValue3ConflictTokens());
		Assert.assertEquals(1, players.get(6).getNumValueNeg1ConflictTokens());
		Assert.assertEquals(1, players.get(6).getNumValue3ConflictTokens());
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

		Assert.assertEquals(1, left.getNumValueNeg1ConflictTokens());
		Assert.assertEquals(2, middle.getNumValue5ConflictTokens());
		Assert.assertEquals(1, right.getNumValueNeg1ConflictTokens());
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

		Assert.assertEquals(1, left.getNumValueNeg1ConflictTokens());
		Assert.assertEquals(1, middle.getNumValueNeg1ConflictTokens());
		Assert.assertEquals(2, right.getNumValue5ConflictTokens());

		Assert.assertEquals(0, left.getNumValue5ConflictTokens());
		Assert.assertEquals(0, middle.getNumValue5ConflictTokens());
		Assert.assertEquals(0, right.getNumValueNeg1ConflictTokens());
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

		Assert.assertEquals(2, players.get(0).getNumValueNeg1ConflictTokens());
		Assert.assertEquals(1, players.get(1).getNumValueNeg1ConflictTokens());
		Assert.assertEquals(1, players.get(1).getNumValue5ConflictTokens());
		Assert.assertEquals(1, players.get(2).getNumValue5ConflictTokens());
		Assert.assertEquals(1, players.get(3).getNumValueNeg1ConflictTokens());
		Assert.assertEquals(2, players.get(4).getNumValue5ConflictTokens());
		Assert.assertEquals(1, players.get(5).getNumValueNeg1ConflictTokens());
		Assert.assertEquals(1, players.get(5).getNumValue5ConflictTokens());
		Assert.assertEquals(1, players.get(6).getNumValueNeg1ConflictTokens());
		Assert.assertEquals(1, players.get(6).getNumValue5ConflictTokens());
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
		Wonder wonder = EasyMock.createStrictMock(Wonder.class);
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
}