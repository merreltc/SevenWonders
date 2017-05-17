package backendTests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import backend.GameManager;
import backend.handlers.PlayerTurnHandler;
import backend.handlers.SetUpDeckHandler;
import backend.handlers.SetUpPlayerHandler;
import backend.handlers.TurnHandler;
import dataStructures.gameMaterials.Card;
import dataStructures.gameMaterials.Deck;
import dataStructures.gameMaterials.Deck.Age;
import dataStructures.gameMaterials.Wonder.WonderType;
import dataStructures.playerData.Player;

public class TurnHandlerTest {

	@Test
	public void testDealInitialCards3Players() {
		ArrayList<String> playerNames = new ArrayList<String>(
				Arrays.asList("Wolverine", "Captain America", "Black Widow"));

		GameManager manager = new GameManager(playerNames, new SetUpPlayerHandler(), new SetUpDeckHandler(),
				new TurnHandler(), new PlayerTurnHandler());

		Deck deck = manager.getGameBoard().getDeck();
		int expectedDeckSize = deck.getNumCards() - 21;
		ArrayList<Player> players = manager.getPlayers();
		new TurnHandler().dealInitialTurnCards(players, manager.getNumPlayers(), deck);

		for (Player player : players) {
			assertEquals(7, player.getCurrentHand().size());
		}

		assertEquals(expectedDeckSize, deck.getCards().size());
	}

	@Test
	public void testDealInitialCards7Players() {
		ArrayList<String> playerNames = new ArrayList<String>(
				Arrays.asList("Wolverine", "Captain America", "Black Widow", "Hulk", "Iron Man", "Spider Man", "Thor"));

		GameManager manager = new GameManager(playerNames, new SetUpPlayerHandler(), new SetUpDeckHandler(),
				new TurnHandler(), new PlayerTurnHandler());

		Deck deck = manager.getGameBoard().getDeck();

		int expectedDeckSize = deck.getNumCards() - 49;
		ArrayList<Player> players = manager.getPlayers();
		new TurnHandler().dealInitialTurnCards(players, manager.getNumPlayers(), deck);

		for (Player player : players) {
			assertEquals(7, player.getCurrentHand().size());
		}

		assertEquals(expectedDeckSize, deck.getCards().size());
	}

	@Test
	public void testDealInitialCards5PlayersNotSame() {
		ArrayList<String> playerNames = new ArrayList<String>(
				Arrays.asList("Wolverine", "Captain America", "Black Widow", "Hulk", "Iron Man"));

		GameManager manager = new GameManager(playerNames, new SetUpPlayerHandler(), new SetUpDeckHandler(),
				new TurnHandler(), new PlayerTurnHandler());

		Deck deck = manager.getGameBoard().getDeck();

		int expectedDeckSize = deck.getNumCards() - 35;
		ArrayList<Player> players = manager.getPlayers();
		new TurnHandler().dealInitialTurnCards(players, manager.getNumPlayers(), deck);

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
		ArrayList<String> playerNames = new ArrayList<String>(
				Arrays.asList("Wolverine", "Captain America", "Black Widow"));

		GameManager manager = new GameManager(playerNames, new SetUpPlayerHandler(), new SetUpDeckHandler(),
				new TurnHandler(), new PlayerTurnHandler());

		Deck deck = manager.getGameBoard().getDeck();
		ArrayList<Player> players = manager.getPlayers();
		TurnHandler turnHandler = new TurnHandler();
		turnHandler.dealInitialTurnCards(players, manager.getNumPlayers(), deck);

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
		ArrayList<String> playerNames = new ArrayList<String>(
				Arrays.asList("Wolverine", "Captain America", "Black Widow"));

		GameManager manager = new GameManager(playerNames, new SetUpPlayerHandler(), new SetUpDeckHandler(),
				new TurnHandler(), new PlayerTurnHandler());

		Deck deck = manager.getGameBoard().getDeck();
		ArrayList<Player> players = manager.getPlayers();
		TurnHandler turnHandler = new TurnHandler();

		turnHandler.dealInitialTurnCards(players, manager.getNumPlayers(), deck);

		assertEquals(5, turnHandler.getNumTurnsTilEndOfAge());
	}

	@Test
	public void testGetNumPlayersUntilPass6Players() {
		ArrayList<Player> players = EasyMock.partialMockBuilder(ArrayList.class).addMockedMethod("size").createMock();
		Deck deck = EasyMock.mock(Deck.class);
		
		EasyMock.expect(deck.getCards()).andReturn(new ArrayList<Card>());
		EasyMock.expect(players.size()).andReturn(5);

		EasyMock.replay(players, deck);
		TurnHandler turnHandler = new TurnHandler();
		turnHandler.dealInitialTurnCards(players, 5, deck);

		EasyMock.verify(players, deck);
		assertEquals(5, turnHandler.getNumTurnsTilEndOfAge());
	}

	@Test
	public void testSetNumTurnsTilEndOfAge() {
		TurnHandler turnHandler = new TurnHandler();

		turnHandler.setNumTurnsTilEndOfAge(5);

		assertEquals(5, turnHandler.getNumTurnsTilEndOfAge());
	}

	@Test
	public void endAge1ThreePlayersMiddleWithMostWarTokens() {
		Player middlePlayer = new Player("Jane Doe", WonderType.COLOSSUS);
		middlePlayer.addNumShields(5);

		Player leftNeighborPlayers = new Player("Jane Doe", WonderType.COLOSSUS);
		leftNeighborPlayers.addNumShields(2);

		Player rightNeighborPlayers = new Player("Jane Doe", WonderType.COLOSSUS);
		rightNeighborPlayers.addNumShields(2);

		ArrayList<Player> players = new ArrayList<Player>();
		players.add(leftNeighborPlayers);
		players.add(middlePlayer);
		players.add(rightNeighborPlayers);

		TurnHandler turnHandler = new TurnHandler();
		turnHandler.endAge(players, Age.AGE1);

		Assert.assertEquals(1, players.get(0).getNumValueNeg1ConflictTokens());
		Assert.assertEquals(2, players.get(1).getNumValue1ConflictTokens());
		Assert.assertEquals(1, players.get(2).getNumValueNeg1ConflictTokens());
	}

	@Test
	public void endAge1ThreePlayersRightWithMostWarTokens() {
		Player middlePlayer = new Player("Jane Doe", WonderType.COLOSSUS);
		middlePlayer.addNumShields(2);

		Player leftNeighborPlayers = new Player("Jane Doe", WonderType.COLOSSUS);
		leftNeighborPlayers.addNumShields(2);

		Player rightNeighborPlayers = new Player("Jane Doe", WonderType.COLOSSUS);
		rightNeighborPlayers.addNumShields(5);

		ArrayList<Player> players = new ArrayList<Player>();
		players.add(leftNeighborPlayers);
		players.add(middlePlayer);
		players.add(rightNeighborPlayers);

		TurnHandler turnHandler = new TurnHandler();
		turnHandler.endAge(players, Age.AGE1);

		Assert.assertEquals(1, players.get(0).getNumValueNeg1ConflictTokens());
		Assert.assertEquals(1, players.get(1).getNumValueNeg1ConflictTokens());
		Assert.assertEquals(2, players.get(2).getNumValue1ConflictTokens());

		Assert.assertEquals(0, players.get(0).getNumValue1ConflictTokens());
		Assert.assertEquals(0, players.get(1).getNumValue1ConflictTokens());
		Assert.assertEquals(0, players.get(2).getNumValueNeg1ConflictTokens());
	}

	@Test
	public void endAge17PlayersAssortedWarTokens() {
		ArrayList<Player> players = new ArrayList<Player>();
		Player player1 = new Player("Jane Doe", WonderType.COLOSSUS);
		player1.addNumShields(2);
		players.add(player1);

		Player player2 = new Player("Jane Doe", WonderType.COLOSSUS);
		player2.addNumShields(3);
		players.add(player2);

		Player player3 = new Player("Jane Doe", WonderType.COLOSSUS);
		player3.addNumShields(5);
		players.add(player3);

		Player player4 = new Player("Jane Doe", WonderType.COLOSSUS);
		player4.addNumShields(5);
		players.add(player4);

		Player player5 = new Player("Jane Doe", WonderType.COLOSSUS);
		player5.addNumShields(7);
		players.add(player5);

		Player player6 = new Player("Jane Doe", WonderType.COLOSSUS);
		player6.addNumShields(6);
		players.add(player6);

		Player player7 = new Player("Jane Doe", WonderType.COLOSSUS);
		player7.addNumShields(3);
		players.add(player7);

		TurnHandler turnHandler = new TurnHandler();
		turnHandler.endAge(players, Age.AGE1);

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
		Player middlePlayer = new Player("Jane Doe", WonderType.COLOSSUS);
		middlePlayer.addNumShields(5);

		Player leftNeighborPlayers = new Player("Jane Doe", WonderType.COLOSSUS);
		leftNeighborPlayers.addNumShields(2);

		Player rightNeighborPlayers = new Player("Jane Doe", WonderType.COLOSSUS);
		rightNeighborPlayers.addNumShields(2);

		ArrayList<Player> players = new ArrayList<Player>();
		players.add(leftNeighborPlayers);
		players.add(middlePlayer);
		players.add(rightNeighborPlayers);

		TurnHandler turnHandler = new TurnHandler();
		turnHandler.endAge(players, Age.AGE2);

		Assert.assertEquals(1, players.get(0).getNumValueNeg1ConflictTokens());
		Assert.assertEquals(2, players.get(1).getNumValue3ConflictTokens());
		Assert.assertEquals(1, players.get(2).getNumValueNeg1ConflictTokens());
	}

	@Test
	public void endAge2ThreePlayersRightWithMostWarTokens() {
		Player middlePlayer = new Player("Jane Doe", WonderType.COLOSSUS);
		middlePlayer.addNumShields(2);

		Player leftNeighborPlayers = new Player("Jane Doe", WonderType.COLOSSUS);
		leftNeighborPlayers.addNumShields(2);

		Player rightNeighborPlayers = new Player("Jane Doe", WonderType.COLOSSUS);
		rightNeighborPlayers.addNumShields(5);

		ArrayList<Player> players = new ArrayList<Player>();
		players.add(leftNeighborPlayers);
		players.add(middlePlayer);
		players.add(rightNeighborPlayers);

		TurnHandler turnHandler = new TurnHandler();
		turnHandler.endAge(players, Age.AGE2);

		Assert.assertEquals(1, players.get(0).getNumValueNeg1ConflictTokens());
		Assert.assertEquals(1, players.get(1).getNumValueNeg1ConflictTokens());
		Assert.assertEquals(2, players.get(2).getNumValue3ConflictTokens());

		Assert.assertEquals(0, players.get(0).getNumValue1ConflictTokens());
		Assert.assertEquals(0, players.get(1).getNumValue1ConflictTokens());
		Assert.assertEquals(0, players.get(2).getNumValueNeg1ConflictTokens());
	}

	@Test
	public void endAge27PlayersAssortedWarTokens() {
		ArrayList<Player> players = new ArrayList<Player>();
		Player player1 = new Player("Jane Doe", WonderType.COLOSSUS);
		player1.addNumShields(2);
		players.add(player1);

		Player player2 = new Player("Jane Doe", WonderType.COLOSSUS);
		player2.addNumShields(3);
		players.add(player2);

		Player player3 = new Player("Jane Doe", WonderType.COLOSSUS);
		player3.addNumShields(5);
		players.add(player3);

		Player player4 = new Player("Jane Doe", WonderType.COLOSSUS);
		player4.addNumShields(5);
		players.add(player4);

		Player player5 = new Player("Jane Doe", WonderType.COLOSSUS);
		player5.addNumShields(7);
		players.add(player5);

		Player player6 = new Player("Jane Doe", WonderType.COLOSSUS);
		player6.addNumShields(6);
		players.add(player6);

		Player player7 = new Player("Jane Doe", WonderType.COLOSSUS);
		player7.addNumShields(3);
		players.add(player7);

		TurnHandler turnHandler = new TurnHandler();
		turnHandler.endAge(players, Age.AGE2);

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
		Player middlePlayer = new Player("Jane Doe", WonderType.COLOSSUS);
		middlePlayer.addNumShields(5);

		Player leftNeighborPlayers = new Player("Jane Doe", WonderType.COLOSSUS);
		leftNeighborPlayers.addNumShields(2);

		Player rightNeighborPlayers = new Player("Jane Doe", WonderType.COLOSSUS);
		rightNeighborPlayers.addNumShields(2);

		ArrayList<Player> players = new ArrayList<Player>();
		players.add(leftNeighborPlayers);
		players.add(middlePlayer);
		players.add(rightNeighborPlayers);

		TurnHandler turnHandler = new TurnHandler();
		turnHandler.endAge(players, Age.AGE3);

		Assert.assertEquals(1, players.get(0).getNumValueNeg1ConflictTokens());
		Assert.assertEquals(2, players.get(1).getNumValue5ConflictTokens());
		Assert.assertEquals(1, players.get(2).getNumValueNeg1ConflictTokens());
	}

	@Test
	public void endAge3ThreePlayersRightWithMostWarTokens() {
		Player middlePlayer = new Player("Jane Doe", WonderType.COLOSSUS);
		middlePlayer.addNumShields(2);

		Player leftNeighborPlayers = new Player("Jane Doe", WonderType.COLOSSUS);
		leftNeighborPlayers.addNumShields(2);

		Player rightNeighborPlayers = new Player("Jane Doe", WonderType.COLOSSUS);
		rightNeighborPlayers.addNumShields(5);

		ArrayList<Player> players = new ArrayList<Player>();
		players.add(leftNeighborPlayers);
		players.add(middlePlayer);
		players.add(rightNeighborPlayers);

		TurnHandler turnHandler = new TurnHandler();
		turnHandler.endAge(players, Age.AGE3);

		Assert.assertEquals(1, players.get(0).getNumValueNeg1ConflictTokens());
		Assert.assertEquals(1, players.get(1).getNumValueNeg1ConflictTokens());
		Assert.assertEquals(2, players.get(2).getNumValue5ConflictTokens());

		Assert.assertEquals(0, players.get(0).getNumValue5ConflictTokens());
		Assert.assertEquals(0, players.get(1).getNumValue5ConflictTokens());
		Assert.assertEquals(0, players.get(2).getNumValueNeg1ConflictTokens());
	}

	@Test
	public void endAge37PlayersAssortedWarTokens() {
		ArrayList<Player> players = new ArrayList<Player>();
		Player player1 = new Player("Jane Doe", WonderType.COLOSSUS);
		player1.addNumShields(2);
		players.add(player1);

		Player player2 = new Player("Jane Doe", WonderType.COLOSSUS);
		player2.addNumShields(3);
		players.add(player2);

		Player player3 = new Player("Jane Doe", WonderType.COLOSSUS);
		player3.addNumShields(5);
		players.add(player3);

		Player player4 = new Player("Jane Doe", WonderType.COLOSSUS);
		player4.addNumShields(5);
		players.add(player4);

		Player player5 = new Player("Jane Doe", WonderType.COLOSSUS);
		player5.addNumShields(7);
		players.add(player5);

		Player player6 = new Player("Jane Doe", WonderType.COLOSSUS);
		player6.addNumShields(6);
		players.add(player6);

		Player player7 = new Player("Jane Doe", WonderType.COLOSSUS);
		player7.addNumShields(3);
		players.add(player7);

		TurnHandler turnHandler = new TurnHandler();
		turnHandler.endAge(players, Age.AGE3);

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
}