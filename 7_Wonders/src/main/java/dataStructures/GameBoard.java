package dataStructures;

import java.util.ArrayList;
import java.util.ResourceBundle;

import backend.handlers.PlayerChipHandler;
import dataStructures.gameMaterials.Card;
import dataStructures.gameMaterials.Deck;
import dataStructures.gameMaterials.Deck.Age;
import dataStructures.playerData.Chip.ChipType;
import dataStructures.playerData.Player;
import exceptions.InsufficientFundsException;
import utils.Translate;
import utils.TranslateWithTemplate;

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
			String msg = TranslateWithTemplate.prepareStringTemplateWithIntArg(index, "invalidPlayerIndex",
					Translate.getNewResourceBundle());
			throw new IllegalArgumentException(msg);
		}
	}

	public void addToDiscardPile(Player active, Card toTest) {
		this.discardPile.add(toTest);

		if (this.totalValue3CoinsInBank-- > 0) {
			PlayerChipHandler.addValue3(active, 1, ChipType.COIN);
		} else {
			this.totalValue1CoinsInBank -= 3;
			PlayerChipHandler.addValue1(active, 3, ChipType.COIN);
		}
	}

	public boolean makeChangeForValue1Coins(Player active, int numCoinsWanted) {
		if (numCoinsWanted > this.totalValue1CoinsInBank) {
			String msg = Translate.prepareStringWithNoArgs("notEnough1Coins", Translate.getNewResourceBundle());
			throw new InsufficientFundsException(msg);
		}

		int numValue3CoinsToRemove = numCoinsWanted / 3;

		PlayerChipHandler.removeValue3(active, numValue3CoinsToRemove, ChipType.COIN);
		this.totalValue3CoinsInBank += numValue3CoinsToRemove;
		this.totalValue1CoinsInBank -= numCoinsWanted;
		PlayerChipHandler.addValue1(active, numCoinsWanted, ChipType.COIN);

		return true;
	}

	public boolean makeChangeForValue3Coins(Player active, int numCoinsWanted) {
		if (numCoinsWanted > this.totalValue3CoinsInBank) {
			String msg = Translate.prepareStringWithNoArgs("notEnough3Coins", Translate.getNewResourceBundle());
			throw new InsufficientFundsException(msg);
		}

		int numValue1CoinsToRemove = numCoinsWanted * 3;
		PlayerChipHandler.removeValue1(active, numValue1CoinsToRemove, ChipType.COIN);
		this.totalValue3CoinsInBank -= numCoinsWanted;
		this.totalValue1CoinsInBank += numValue1CoinsToRemove;
		PlayerChipHandler.addValue3(active, numCoinsWanted, ChipType.COIN);

		return true;
	}

	public void giveNumCoins(Player player, int numCoinsToGet) {
		int numValue3 = numCoinsToGet / 3;
		int numValue1 = numCoinsToGet % 3;

		if (numValue3 > this.totalValue3CoinsInBank) {
			int numValue3Left = numValue3 - this.totalValue3CoinsInBank;
			numValue3 = this.totalValue3CoinsInBank;
			this.totalValue3CoinsInBank = 0;
			numValue1 += numValue3Left * 3;
		} else {
			this.totalValue3CoinsInBank -= numValue3;
		}
		this.totalValue1CoinsInBank -= numValue1;

		PlayerChipHandler.addValue3(player, numValue3, ChipType.COIN);
		PlayerChipHandler.addValue1(player, numValue1, ChipType.COIN);
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

	public void discardEndOfAgeCard(Card toDiscard) {
		this.discardPile.add(toDiscard);
	}

	public void setDeck(Deck deck) {
		this.currentDeck = deck;
	}

	public Age getAge() {
		return this.currentDeck.getAge();
	}
}
