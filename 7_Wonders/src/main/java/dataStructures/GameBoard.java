package dataStructures;

import java.util.ArrayList;

import exceptions.InsufficientFundsException;

public class GameBoard {
	private ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<Card> discardPile = new ArrayList<Card>();
	private Deck currentDeck;

	private int numPlayers;
	private int totalValue1CoinsInBank = 46;
	private int totalValue3CoinsInBank = 24;

	private int currentPlayerIndex;
	private int nextPlayerIndex;
	private int previousPlayerIndex;

	public GameBoard(ArrayList<Player> players, Deck deck) {
		this.numPlayers = players.size();
		this.totalValue1CoinsInBank -= 3 * this.numPlayers;
		this.currentPlayerIndex = 0;
		this.nextPlayerIndex = 1;
		this.previousPlayerIndex = this.numPlayers - 1;
		this.players = players;
		this.currentDeck = deck;
	}

	public int getNumPlayers() {
		return this.numPlayers;
	}

	public ArrayList<Player> getPlayers() {
		return this.players;
	}

	public Player getPlayer(int index) {
		return this.players.get(index);
	}

	public int getPlayerCoinTotal(int index) {
		return this.getPlayer(index).getCoinTotal();
	}

	public Player getCurrentPlayer() {
		return this.getPlayer(this.currentPlayerIndex);
	}

	public Player getNextPlayer() {
		return this.getPlayer(this.nextPlayerIndex);
	}

	public Player getPreviousPlayer() {
		return this.getPlayer(this.previousPlayerIndex);
	}

	public void setCurrentPlayer(int index) {
		validatePlayerIndex(index);
		this.currentPlayerIndex = index;
	}

	public void setNextPlayer(int index) {
		validatePlayerIndex(index);
		this.nextPlayerIndex = index;
	}

	public void setPreviousPlayer(int index) {
		validatePlayerIndex(index);
		this.previousPlayerIndex = index;
	}

	private void validatePlayerIndex(int index) {
		if (index <= -1 || index >= this.numPlayers) {
			throw new IllegalArgumentException("Invalid player index: " + index);
		}
	}

	public void addToDiscardPile(Player active, Card toTest) {
		this.discardPile.add(toTest);

		if (this.totalValue3CoinsInBank-- > 0) {
			active.addValue3(1);
		}else {
			this.totalValue1CoinsInBank--;
			active.addValue1(3);
		}
	}

	public boolean makeChangeForValue1Coins(Player active, int numCoinsWanted) {
		if (numCoinsWanted > this.totalValue1CoinsInBank) {
			throw new InsufficientFundsException("Not enough value 1 coins left in bank");
		}

		int numValue3CoinsToRemove = numCoinsWanted / 3;
		active.removeValue3(numValue3CoinsToRemove);
		this.totalValue3CoinsInBank += numValue3CoinsToRemove;
		this.totalValue1CoinsInBank -= numCoinsWanted;
		active.addValue1(numCoinsWanted);

		return true;
	}

	public boolean makeChangeForValue3Coins(Player active, int numCoinsWanted) {
		if (numCoinsWanted > this.totalValue3CoinsInBank) {
			throw new InsufficientFundsException("Not enough value 3 coins left in bank");
		}
		int numValue1CoinsToRemove = numCoinsWanted * 3;
		active.removeValue1(numValue1CoinsToRemove);
		this.totalValue3CoinsInBank -= numCoinsWanted;
		this.totalValue1CoinsInBank += numValue1CoinsToRemove;
		active.addValue3(numCoinsWanted);
		return true;
	}

	public int getCurrentPlayerIndex() {
		return this.currentPlayerIndex;
	}

	public int getNextPlayerIndex() {
		return this.nextPlayerIndex;
	}

	public int getPreviousPlayerIndex() {
		return this.previousPlayerIndex;
	}

	public Deck getDeck() {
		return this.currentDeck;
	}

	public ArrayList<Card> getDiscardPile() {
		return this.discardPile;
	}

	public int getTotalValue1CoinsInBank() {
		return this.totalValue1CoinsInBank;
	}

	public int getTotalValue3CoinsInBank() {
		return this.totalValue3CoinsInBank;
	}
}
