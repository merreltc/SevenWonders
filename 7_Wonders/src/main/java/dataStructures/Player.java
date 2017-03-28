package dataStructures;

import exceptions.InsufficientFundsException;

public class Player {
	private String name = "Jane Doe";

	private int coinTotal = 3;
	private int numOfValue1Coins = 3;
	private int numOfValue3Coins = 0;

	private int conflictTotal = 0;

	private enum CoinType {
		ONE, THREE
	}

	public void addValue1(int numCoinsToAdd) {
		validateNumCoinsToAdd(numCoinsToAdd, CoinType.ONE);

		this.coinTotal += numCoinsToAdd;
		this.numOfValue1Coins += numCoinsToAdd;
	}

	public void addValue3(int numCoinsToAdd) {
		validateNumCoinsToAdd(numCoinsToAdd, CoinType.THREE);

		this.coinTotal += 3 * numCoinsToAdd;
		this.numOfValue3Coins += numCoinsToAdd;
	}

	private void validateNumCoinsToAdd(int numCoins, CoinType type) {
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
		validateNumCoinsToRemove(numCoinsToRemove, CoinType.ONE);

		this.coinTotal -= numCoinsToRemove;
		this.numOfValue1Coins -= numCoinsToRemove;
	}

	public void removeValue3(int numCoinsToRemove) {
		validateNumCoinsToRemove(numCoinsToRemove, CoinType.THREE);

		this.coinTotal -= 3 * numCoinsToRemove;
		this.numOfValue3Coins -= numCoinsToRemove;
	}

	private void validateNumCoinsToRemove(int numCoins, CoinType type) {
		if (numCoins <= -1) {
			throw new IllegalArgumentException("Cannot remove " + numCoins + " value 1 coins");
		}

		int numCoinsToCheck = getNumOfCoinType(type);

		if (numCoins > numCoinsToCheck) {
			throw new InsufficientFundsException();
		}
	}

	private int getNumOfCoinType(CoinType type) {
		if (type == CoinType.ONE) {
			return this.numOfValue1Coins;
		}

		return this.numOfValue3Coins;
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
}
