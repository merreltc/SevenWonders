package dataStructures;

import exceptions.InsufficientFundsException;

public class Player {
	private String name = "Jane Doe";

	private int coinTotal = 3;
	private int numOfValue1Coins = 3;
	private int numOfValue3Coins = 0;
	private int conflictTotal = 0;

	public Player() {

	}

	public Player(String playerName) {
		this.name = playerName;
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
}
