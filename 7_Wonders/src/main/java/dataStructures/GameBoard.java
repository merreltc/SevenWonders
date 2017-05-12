package dataStructures;

import java.util.ArrayList;

public class GameBoard {
	private ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<Card> discardPile = new ArrayList<Card>();
	private Deck currentDeck;

	private int numPlayers;

	private int currentPlayerIndex;
	private int nextPlayerIndex;
	private int previousPlayerIndex;

	public GameBoard(ArrayList<Player> players, Deck deck) {
		this.numPlayers = players.size();
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

	public void addToDiscardPile(Card toTest) {
		this.discardPile.add(toTest);
	}
}
