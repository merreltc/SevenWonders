package dataStructures;

import java.util.ArrayList;
import java.util.HashMap;

import dataStructures.Effect.EffectType;
import exceptions.InsufficientFundsException;

public class Player {
	private String name = "Jane Doe";

	private int coinTotal = 3;
	private int numOfValue1Coins = 3;
	private int numOfValue3Coins = 0;
	private int conflictTotal = 0;
	private int numShields = 0;
	private int numVictoryPoints;

	private Wonder wonder;

	private ArrayList<Card> currentHand = new ArrayList<Card>();
	private ArrayList<Card> storagePile = new ArrayList<Card>();

	private HashMap<Enum, Integer> currentTrades = new HashMap<Enum, Integer>();

	public Player(String playerName, Wonder.WonderType wonder){
		this.name = playerName;
		this.wonder = new Wonder('A', wonder);
	}

	public void addValue1(int numCoinsToAdd) {
		validateNumCoinsToAdd(numCoinsToAdd, Chip.ChipType.ONE);

		this.coinTotal += numCoinsToAdd;
		this.numOfValue1Coins += numCoinsToAdd;
	}

	public void addValue3(int numCoinsToAdd) {
		validateNumCoinsToAdd(numCoinsToAdd, Chip.ChipType.THREE);

		this.coinTotal += 3 * numCoinsToAdd;
		this.numOfValue3Coins += numCoinsToAdd;
	}

	private void validateNumCoinsToAdd(int numCoins, Chip.ChipType type) {
		int max;
		String coinType;

		switch (type) {
		case ONE:
			max = 46;
			coinType = "1";
			break;
		case THREE:
			max = 24;
			coinType = "3";
			break;
		default:
			throw new IllegalArgumentException("Bad CoinType");
		}

		if (numCoins <= -1 || numCoins > max) {
			throw new IllegalArgumentException("Cannot add " + numCoins + " value " + coinType + " coins");
		}
	}

	public void removeValue1(int numCoinsToRemove) {
		validateNumCoinsToRemove(numCoinsToRemove, Chip.ChipType.ONE);

		this.coinTotal -= numCoinsToRemove;
		this.numOfValue1Coins -= numCoinsToRemove;
	}

	public void removeValue3(int numCoinsToRemove) {
		validateNumCoinsToRemove(numCoinsToRemove, Chip.ChipType.THREE);

		this.coinTotal -= 3 * numCoinsToRemove;
		this.numOfValue3Coins -= numCoinsToRemove;
	}

	private void validateNumCoinsToRemove(int numCoins, Chip.ChipType type) {
		if (numCoins <= -1) {
			String coinType;

			switch (type) {
			case ONE:
				coinType = "1";
				break;
			case THREE:
				coinType = "3";
				break;
			default:
				throw new IllegalArgumentException("Bad CoinType");
			}

			throw new IllegalArgumentException("Cannot remove " + numCoins + " value " + coinType + " coins");
		}

		int numCoinsToCheck = getNumOfCoinType(type);
		String coinType = coinTypeToString(type);

		if (numCoins > numCoinsToCheck) {
			throw new InsufficientFundsException(
					"Player does not have " + numCoins + " value " + coinType + " coin(s)");
		}
	}

	public String toString() {
		return "Name: " + this.name + "\nCoin Total: " + this.getCoinTotal();
	}

	private int getNumOfCoinType(Chip.ChipType type) {
		if (type == Chip.ChipType.ONE) {
			return this.numOfValue1Coins;
		}

		return this.numOfValue3Coins;
	}

	private String coinTypeToString(Chip.ChipType type) {
		switch (type) {
		case ONE:
			return "1";
		case THREE:
			return "3";
		default:
			throw new IllegalArgumentException("Bad Coin.CoinType");
		}
	}

	public String getName() {
		return this.name;
	}

	public int getCoinTotal() {
		return this.coinTotal;
	}

	public int getNumValue1Coins() {
		return this.numOfValue1Coins;
	}

	public int getNumValue3Coins() {
		return this.numOfValue3Coins;
	}

	public int getConflictTotal() {
		return this.conflictTotal;
	}

	public ArrayList<Card> getCurrentHand() {
		return this.currentHand;
	}

	public void setCurrentHand(ArrayList<Card> currentHand) {
		this.currentHand = currentHand;
	}

	public ArrayList<Card> getStoragePile() {
		return this.storagePile;
	}

	public void setStoragePile(ArrayList<Card> storagePile) {
		this.storagePile = storagePile;
	}

	public boolean storagePileContainsEntity(Enum entity) {
		for (Card storage : this.storagePile) {
			if (storage.getEffectType().equals(EffectType.ENTITY)) {
				EntityEffect effect = (EntityEffect) storage.getEffect();
				if (effect.getEntities().containsKey(entity)) {
					return true;
				}
			}
		}
		return false;
	}

	public HashMap<Enum, Integer> getCurrentTrades() {
		return this.currentTrades;
	}

	public void addTradedValue(Enum trade) {
		int value = 0;
		if (this.currentTrades.containsKey(trade)) {
			value = (int) this.currentTrades.get(trade);
		}
		this.currentTrades.put(trade, value + 1);
	}

	public void removeCurrentTrades() {
		this.currentTrades.clear();
	}

	public void addToStoragePile(Card card) {
		this.storagePile.add(card);
	}

	public void removeFromCurrentHand(Card card) {
		this.currentHand.remove(card);
	}

	public Wonder getWonder(){
		return wonder;
	}

	public void removeTotalCoins(int total) {
		int numValue3CoinsToRemove = total / 3;
		int numValue1CoinsToRemove = total % 3;

		if (numValue3CoinsToRemove > this.numOfValue3Coins) {
			numValue1CoinsToRemove += 3 * (numValue3CoinsToRemove - this.numOfValue3Coins);
			numValue3CoinsToRemove = this.numOfValue3Coins;
		}
		
		this.removeValue3(numValue3CoinsToRemove);
		this.removeValue1(numValue1CoinsToRemove);
	}

	public int getNumShields() {
		return this.numShields;
	}

	public void addNumShields(int numShields) {
		this.numShields += numShields;
	}

	public int getNumVictoryPoints() {
		return this.numVictoryPoints;
	}

	public void addNumVictoryPoints(int numPoints) {
		this.numVictoryPoints += numPoints;
	}
}