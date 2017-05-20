package dataStructureTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.easymock.EasyMock;
import org.junit.Test;

import backend.handlers.PlayerChipHandler;
import backend.handlers.SetUpDeckHandler;
import constants.GeneralEnums.Side;
import dataStructures.GameBoard;
import dataStructures.gameMaterials.Card;
import dataStructures.gameMaterials.Deck;
import dataStructures.gameMaterials.Deck.Age;
import dataStructures.gameMaterials.Wonder.WonderType;
import dataStructures.gameMaterials.Wonder;
import dataStructures.playerData.Chip;
import dataStructures.playerData.Player;
import dataStructures.playerData.Chip.ChipType;
import dataStructures.playerData.Chip.ChipValue;
import exceptions.InsufficientFundsException;
import junit.framework.Assert;

public class GameBoardTests {
	private Deck age1Deck, age2Deck;

	@Test
	public void testGameBoardGMinPlayers() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);
		this.age1Deck = createDeck(Age.AGE1, 3);
		GameBoard board = new GameBoard(players, this.age1Deck);
		assertEquals(3, board.getNumPlayers());
	}

	@Test
	public void testGameBoardMaxPlayers() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(7);
		this.age1Deck = createDeck(Age.AGE1, 7);
		GameBoard board = new GameBoard(players, this.age1Deck);
		assertEquals(7, board.getNumPlayers());
	}

	@Test
	public void testGameBoardGetPlayers() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);
		this.age1Deck = createDeck(Age.AGE1, 3);
		GameBoard board = new GameBoard(players, this.age1Deck);
		assertEquals(players, board.getPlayers());
	}

	@Test
	public void testGameBoardGetPlayerByIndex() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);
		this.age1Deck = createDeck(Age.AGE1, 3);
		GameBoard board = new GameBoard(players, age1Deck);

		for (int i = 0; i < players.size(); i++) {
			assertEquals(players.get(i), board.getPlayer(i));
		}
	}

	@Test
	public void testGameBoardGetPlayerCoinTotalOnStart() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);
		this.age1Deck = createDeck(Age.AGE1, 3);
		GameBoard board = new GameBoard(players, this.age1Deck);

		for (int i = 0; i < players.size(); i++) {
			assertEquals(3, board.getPlayerCoinTotal(i));
		}
	}

	@Test
	public void testGetPlayerPositionsOnStartMin() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);
		this.age1Deck = createDeck(Age.AGE1, 3);
		GameBoard board = new GameBoard(players, this.age1Deck);
		comparePlayerPositions(players, board, 0, 1, 2);
	}

	@Test
	public void testGetPlayerPositionsOnStartMax() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(7);
		this.age1Deck = createDeck(Age.AGE1, 7);
		GameBoard board = new GameBoard(players, this.age1Deck);
		comparePlayerPositions(players, board, 0, 1, 6);
	}

	private void comparePlayerPositions(ArrayList<Player> players, GameBoard board, int currIndex, int nextIndex,
			int previousIndex) {
		assertEquals(players.get(currIndex), board.getCurrentPlayer());
		assertEquals(players.get(nextIndex), board.getNextPlayer());
		assertEquals(players.get(previousIndex), board.getPreviousPlayer());
	}

	@Test
	public void testSetCurrentPlayerValid() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(5);
		this.age1Deck = createDeck(Age.AGE1, 5);
		GameBoard board = new GameBoard(players, this.age1Deck);
		board.setCurrentPlayer(0);
		assertEquals(players.get(0), board.getCurrentPlayer());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetCurrentPlayerInvalidNeg1() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(5);
		this.age1Deck = createDeck(Age.AGE1, 5);
		GameBoard board = new GameBoard(players, this.age1Deck);
		board.setCurrentPlayer(-1);
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetCurrentPlayerInvalidMaxPlus1() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(5);
		this.age1Deck = createDeck(Age.AGE1, 5);
		GameBoard board = new GameBoard(players, this.age1Deck);
		board.setCurrentPlayer(5);
		fail();
	}

	@Test
	public void testSetNextPlayerValid() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(5);
		this.age1Deck = createDeck(Age.AGE1, 5);
		GameBoard board = new GameBoard(players, this.age1Deck);
		board.setNextPlayer(0);
		assertEquals(players.get(0), board.getNextPlayer());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetNextPlayerInvalidNeg1() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(5);
		this.age1Deck = createDeck(Age.AGE1, 5);
		GameBoard board = new GameBoard(players, this.age1Deck);
		board.setNextPlayer(-1);
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetNextPlayerInvalidMaxPlus1() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(5);
		this.age1Deck = createDeck(Age.AGE1, 5);
		GameBoard board = new GameBoard(players, this.age1Deck);
		board.setNextPlayer(5);
		fail();
	}

	@Test
	public void testSetPreviousPlayerValid() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(5);
		this.age1Deck = createDeck(Age.AGE1, 5);
		GameBoard board = new GameBoard(players, this.age1Deck);
		board.setPreviousPlayer(0);
		assertEquals(players.get(0), board.getPreviousPlayer());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetPreviousPlayerInvalidNeg1() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(5);
		this.age1Deck = createDeck(Age.AGE1, 5);
		GameBoard board = new GameBoard(players, this.age1Deck);
		board.setPreviousPlayer(-1);
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetPreviousPlayerInvalidMaxPlus1() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(5);
		this.age1Deck = createDeck(Age.AGE1, 5);
		GameBoard board = new GameBoard(players, this.age1Deck);
		board.setPreviousPlayer(5);
		fail();
	}

	@Test
	public void testGetPlayerPositionIndexes() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(5);
		this.age1Deck = createDeck(Age.AGE1, 5);
		GameBoard board = new GameBoard(players, this.age1Deck);

		assertEquals(0, board.getCurrentPlayerIndex());
		assertEquals(1, board.getNextPlayerIndex());
		assertEquals(4, board.getPreviousPlayerIndex());
	}

	@Test
	public void testGetDeck() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(5);
		this.age1Deck = createDeck(Age.AGE1, 5);
		GameBoard board = new GameBoard(players, this.age1Deck);
		assertEquals(this.age1Deck, board.getDeck());
	}

	@Test
	public void testGetDefaultDiscardPile() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(5);
		this.age1Deck = createDeck(Age.AGE1, 5);
		GameBoard board = new GameBoard(players, this.age1Deck);
		assertTrue(board.getDiscardPile().isEmpty());
		assertEquals(ArrayList.class, board.getDiscardPile().getClass());
	}

	@Test
	public void testAddToDiscardPile() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(5);
		this.age1Deck = createDeck(Age.AGE1, 5);
		GameBoard board = new GameBoard(players, this.age1Deck);
		Card toTest = this.age1Deck.getCard(0);
		board.addToDiscardPile(players.get(0), toTest);

		assertEquals(1, (int) players.get(0).getCoins().get(ChipValue.THREE));
		assertFalse(board.getDiscardPile().isEmpty());
		assertEquals(toTest, board.getDiscardPile().get(0));
		assertEquals(23, board.getTotalValue3CoinsInBank());
	}

	@Test
	public void testAddToDiscardPile3Value1CoinsRecieved() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(5);
		this.age1Deck = createDeck(Age.AGE1, 5);
		GameBoard board = new GameBoard(players, this.age1Deck);
		PlayerChipHandler.addValue1(players.get(1), 36, Chip.ChipType.COIN);
		PlayerChipHandler.addValue1(players.get(1), 36, Chip.ChipType.COIN);
		board.makeChangeForValue3Coins(players.get(1), 24);
		Card toTest = this.age1Deck.getCard(0);
		board.addToDiscardPile(players.get(0), toTest);

		assertEquals(6, (int) players.get(0).getCoins().get(ChipValue.ONE));
		assertFalse(board.getDiscardPile().isEmpty());
		assertEquals(toTest, board.getDiscardPile().get(0));
		assertEquals(100, board.getTotalValue1CoinsInBank());
	}

	@Test
	public void testGetTotalValue1CoinsLeft3Players() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);
		this.age1Deck = createDeck(Age.AGE1, 3);
		GameBoard board = new GameBoard(players, this.age1Deck);
		assertEquals(37, board.getTotalValue1CoinsInBank());
	}

	@Test
	public void testGetTotalValue1CoinsLeft7Players() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(7);
		this.age1Deck = createDeck(Age.AGE1, 7);
		GameBoard board = new GameBoard(players, this.age1Deck);
		assertEquals(25, board.getTotalValue1CoinsInBank());
	}

	@Test
	public void testGetTotalValue3CoinsLeft3Players() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);
		this.age1Deck = createDeck(Age.AGE1, 3);
		GameBoard board = new GameBoard(players, this.age1Deck);
		assertEquals(24, board.getTotalValue3CoinsInBank());
	}

	@Test
	public void testMakeChangeForValue1Coins() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);
		this.age1Deck = createDeck(Age.AGE1, 3);
		GameBoard board = new GameBoard(players, this.age1Deck);
		Player active = players.get(0);
		PlayerChipHandler.addValue3(active, 1, Chip.ChipType.COIN);
		assertTrue(board.makeChangeForValue1Coins(active, 3));
		assertEquals(6, (int) active.getCoins().get(ChipValue.ONE));
		assertEquals(0, (int) active.getCoins().get(ChipValue.THREE));
		assertEquals(34, board.getTotalValue1CoinsInBank());
		assertEquals(25, board.getTotalValue3CoinsInBank());
	}

	@Test
	public void testMakeChangeForValue1CoinsNotEnoughInBank() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);
		this.age1Deck = createDeck(Age.AGE1, 3);
		GameBoard board = new GameBoard(players, this.age1Deck);
		Player active = players.get(0);
		PlayerChipHandler.addValue3(active, 14, Chip.ChipType.COIN);

		try {
			board.makeChangeForValue1Coins(active, 42);
		} catch (InsufficientFundsException e) {
			assertEquals("Not enough value 1 coins left in bank", e.getMessage());
		}
	}
	
	@Test
	public void testMakeChangeForValue1CoinsExactlyEnoughInBank() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);
		this.age1Deck = createDeck(Age.AGE1, 3);
		GameBoard board = new GameBoard(players, this.age1Deck);
		Player active = players.get(0);
		PlayerChipHandler.addValue3(active, 12, Chip.ChipType.COIN);
		board.giveNumCoins(players.get(1), 73);
		board.makeChangeForValue1Coins(active, 36);
		assertEquals(39, (int) active.getCoins().get(ChipValue.ONE));
		
	}

	@Test
	public void testMakeChangeForValue3Coins() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);
		this.age1Deck = createDeck(Age.AGE1, 3);
		GameBoard board = new GameBoard(players, this.age1Deck);
		Player active = players.get(0);

		assertTrue(board.makeChangeForValue3Coins(active, 1));
		assertEquals(0, (int) active.getCoins().get(ChipValue.ONE));
		assertEquals(1, (int) active.getCoins().get(ChipValue.THREE));
		assertEquals(40, board.getTotalValue1CoinsInBank());
		assertEquals(23, board.getTotalValue3CoinsInBank());
	}

	@Test
	public void testMakeChangeForValue3CoinsNotEnoughInBank() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);
		this.age1Deck = createDeck(Age.AGE1, 3);
		GameBoard board = new GameBoard(players, this.age1Deck);
		Player active = players.get(0);
		PlayerChipHandler.addValue1(active, 40, Chip.ChipType.COIN);
		PlayerChipHandler.addValue1(active, 40, Chip.ChipType.COIN);

		try {
			board.makeChangeForValue3Coins(active, 25);
		} catch (InsufficientFundsException e) {
			assertEquals("Not enough value 3 coins left in bank", e.getMessage());
		}
	}

	@Test
	public void testGiveNumCoins5() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);
		this.age1Deck = createDeck(Age.AGE1, 3);
		GameBoard board = new GameBoard(players, this.age1Deck);
		Player active = players.get(0);

		board.giveNumCoins(active, 5);

		assertEquals(8, active.getCoinTotal());
		assertEquals(23, board.getTotalValue3CoinsInBank());
		assertEquals(35, board.getTotalValue1CoinsInBank());
	}

	@Test
	public void testGiveNumCoins10() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);
		this.age1Deck = createDeck(Age.AGE1, 3);
		GameBoard board = new GameBoard(players, this.age1Deck);
		Player active = players.get(0);

		board.giveNumCoins(active, 10);

		assertEquals(13, active.getCoinTotal());
		assertEquals(21, board.getTotalValue3CoinsInBank());
		assertEquals(36, board.getTotalValue1CoinsInBank());
	}

	@Test
	public void testGiveNumCoins10NotEnoughValue3Coins() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);
		this.age1Deck = createDeck(Age.AGE1, 3);
		GameBoard board = new GameBoard(players, this.age1Deck);
		Player active = players.get(0);

		board.giveNumCoins(players.get(1), 72);
		board.giveNumCoins(active, 10);

		assertEquals(13, active.getCoinTotal());
		assertEquals(0, board.getTotalValue3CoinsInBank());
		assertEquals(27, board.getTotalValue1CoinsInBank());
	}
	
	@Test
	public void testGiveNumCoins20NotEnoughValue3Coins() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);
		this.age1Deck = createDeck(Age.AGE1, 3);
		GameBoard board = new GameBoard(players, this.age1Deck);
		Player active = players.get(0);

		board.giveNumCoins(players.get(1), 69);
		board.giveNumCoins(active, 10);

		assertEquals(13, active.getCoinTotal());
		assertEquals(0, board.getTotalValue3CoinsInBank());
		assertEquals(30, board.getTotalValue1CoinsInBank());
	}
	
	@Test
	public void testGiveNumCoinsExactlyEnoughValue3Coins() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);
		this.age1Deck = createDeck(Age.AGE1, 3);
		GameBoard board = new GameBoard(players, this.age1Deck);
		Player active = players.get(0);

		board.giveNumCoins(players.get(1), 69);
		board.giveNumCoins(active, 3);

		assertEquals(6, active.getCoinTotal());
		assertEquals(0, board.getTotalValue3CoinsInBank());
		assertEquals(37, board.getTotalValue1CoinsInBank());
	}

	@Test
	public void testEndAgeDiscard() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(3);
		this.age1Deck = createDeck(Age.AGE1, 3);
		Card toDiscard = EasyMock.mock(Card.class);

		GameBoard board = new GameBoard(players, this.age1Deck);

		board.discardEndOfAgeCard(toDiscard);
		assertTrue(board.getDiscardPile().contains(toDiscard));
	}

	@Test
	public void testSetDeck() {
		ArrayList<Player> players = setUpArrayWithNumPlayers(0);
		this.age1Deck = createDeck(Age.AGE1, 0);
		this.age2Deck = createDeck(Age.AGE2, 0);
		GameBoard board = new GameBoard(players, this.age1Deck);

		assertEquals(this.age1Deck, board.getDeck());
		board.setDeck(this.age2Deck);
		assertNotEquals(this.age1Deck, board.getDeck());
		assertEquals(this.age2Deck, board.getDeck());
	}
	
	private ArrayList<Player> setUpArrayWithNumPlayers(int num) {
		Wonder wonder = new Wonder(Side.A, WonderType.COLOSSUS);
		ArrayList<Player> result = new ArrayList<Player>();
		for (int i = 0; i < num; i++) {
			Player temp = EasyMock.partialMockBuilder(Player.class).withConstructor("Jane Doe", wonder).createMock();
			result.add(temp);
		}
		return result;
	}

	private Deck createDeck(Age age, int numPlayers) {
		return new SetUpDeckHandler().createDeck(age, numPlayers);
	}
}