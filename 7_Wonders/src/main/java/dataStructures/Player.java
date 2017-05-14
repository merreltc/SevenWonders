package dataStructures;

import java.util.ArrayList;
import java.util.HashMap;

import dataStructures.Chip.ChipType;
import dataStructures.Effect.EffectType;
import exceptions.InsufficientFundsException;

public class Player {
	private String name = "Jane Doe";

	private int numShields = 0;
	private int numVictoryPoints = 0;

	private Wonder wonder;

	private ArrayList<Card> currentHand = new ArrayList<Card>();
	private ArrayList<Card> storagePile = new ArrayList<Card>();

	private HashMap<Enum, Integer> currentTrades = new HashMap<Enum, Integer>();
	private PlayerChips playerChips = new PlayerChips();

	private class PlayerChips {
		protected int numOfValue1Coins = 3;
		protected int numOfValue3Coins = 0;
		protected int numOfValue1ConflictTokens = 0;
		protected int numOfValue3ConflictTokens = 0;
		protected int conflictTotal = 0;
		protected int coinTotal = 3;
		public int numOfValueNeg3ConflictTokens = 0;

	}

	public Player(String playerName, Wonder.WonderType wonder) {
		this.name = playerName;
		this.wonder = new Wonder('A', wonder);
	}

	public void addValue1(int numChipsToAdd, Chip.ChipType chipType) {
		validateNumChipsToAdd(numChipsToAdd, Chip.ChipValue.ONE);

		if (chipType == ChipType.COIN) {
			this.playerChips.coinTotal += numChipsToAdd;
			this.playerChips.numOfValue1Coins += numChipsToAdd;
		} else {
			this.playerChips.conflictTotal += numChipsToAdd;
			this.playerChips.numOfValue1ConflictTokens += numChipsToAdd;
		}
	}

	public void addValue3(int numChipsToAdd, ChipType chipType) {
		validateNumChipsToAdd(numChipsToAdd, Chip.ChipValue.THREE);
		
		if (chipType == ChipType.COIN) {
			this.playerChips.coinTotal += 3 * numChipsToAdd;
			this.playerChips.numOfValue3Coins += numChipsToAdd;
		} else {
			this.playerChips.conflictTotal += 3 * numChipsToAdd;
			this.playerChips.numOfValue3ConflictTokens += numChipsToAdd;
		}
	}
	
	public void addValueNeg1(int numChipsToAdd, ChipType chipType) {
		if(chipType == ChipType.COIN){
			throw new IllegalArgumentException("Cannot have a negative 1 coin value");
		}
		
		validateNumChipsToAdd(numChipsToAdd, Chip.ChipValue.NEG1);
		
		this.playerChips.conflictTotal += (-1) * numChipsToAdd;
		this.playerChips.numOfValueNeg3ConflictTokens  += numChipsToAdd;
	}

	private void validateNumChipsToAdd(int numChips, Chip.ChipValue type) {
		int max;
		String chipType;

		switch (type) {
		case ONE:
			max = 46;
			chipType = "1";
			break;
		case THREE:
			max = 24;
			chipType = "3";
			break;
		case NEG1:
			max = 21;
			chipType = "-1";
			break;
		default:
			throw new IllegalArgumentException("Bad CoinType");
		}

		if (numChips <= -1 || numChips > max) {
			throw new IllegalArgumentException("Cannot add " + numChips + " value " + chipType + " chips");
		}
	}

	public void removeValue1(int numCoinsToRemove) {
		validateNumCoinsToRemove(numCoinsToRemove, Chip.ChipValue.ONE);

		this.playerChips.coinTotal -= numCoinsToRemove;
		this.playerChips.numOfValue1Coins -= numCoinsToRemove;
	}

	public void removeValue3(int numCoinsToRemove) {
		validateNumCoinsToRemove(numCoinsToRemove, Chip.ChipValue.THREE);

		this.playerChips.coinTotal -= 3 * numCoinsToRemove;
		this.playerChips.numOfValue3Coins -= numCoinsToRemove;
	}

	private void validateNumCoinsToRemove(int numCoins, Chip.ChipValue type) {
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

		int numCoinsToCheck = getNumOfCoinValue(type);
		String coinType = coinTypeToString(type);

		if (numCoins > numCoinsToCheck) {
			throw new InsufficientFundsException(
					"Player does not have " + numCoins + " value " + coinType + " coin(s)");
		}
	}

	public String toString() {
		return "Name: " + this.name + "\nCoin Total: " + this.getCoinTotal();
	}

	private int getNumOfCoinValue(Chip.ChipValue type) {
		if (type == Chip.ChipValue.ONE) {
			return this.playerChips.numOfValue1Coins;
		}

		return this.playerChips.numOfValue3Coins;
	}

	private String coinTypeToString(Chip.ChipValue type) {
		switch (type) {
		case ONE:
			return "1";
		case THREE:
			return "3";
		default:
			throw new IllegalArgumentException("Bad Coin.CoinValue");
		}
	}

	public String getName() {
		return this.name;
	}

	public int getCoinTotal() {
		return this.playerChips.coinTotal;
	}

	public int getNumValue1Coins() {
		return this.playerChips.numOfValue1Coins;
	}

	public int getNumValue3Coins() {
		return this.playerChips.numOfValue3Coins;
	}

	public int getConflictTotal() {
		return this.playerChips.conflictTotal;
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

	public Wonder getWonder() {
		return wonder;
	}

	public void removeTotalCoins(int total) {
		int numValue3CoinsToRemove = total / 3;
		int numValue1CoinsToRemove = total % 3;

		if (numValue3CoinsToRemove > this.playerChips.numOfValue3Coins) {
			numValue1CoinsToRemove += 3 * (numValue3CoinsToRemove - this.playerChips.numOfValue3Coins);
			numValue3CoinsToRemove = this.playerChips.numOfValue3Coins;
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

	public int getNumValue1ConflictTokens() {
		return this.playerChips.numOfValue1ConflictTokens;
	}

	public int getNumValue3ConflictTokens() {
		return this.playerChips.numOfValue3ConflictTokens;
	}

	public int getNumValueNeg1ConflictTokens() {
		return 1;
	}
}
