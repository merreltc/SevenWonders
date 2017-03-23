package dataStructures;

import Exceptions.InsufficientFundsException;

public class Player {
	private int coinTotal = 3;
	private int conflictTotal = 0;
	private int numOfValue1Coins = 3;
	private int numOfValue3Coins = 0;
	private String name = "Jane Doe";

	public String getName() {
		return this.name;
	}

	public int getCoinTotal() {
		return this.coinTotal;
	}

	public int getConflictTotal() {
		return this.conflictTotal;
	}

	public int getNumValue1Coins() {
		return this.numOfValue1Coins;
	}

	public int getNumValue3Coins() {
		return this.numOfValue3Coins;
	}

	public void addValue1(int numCoinsToAdd) {
		if (numCoinsToAdd <= -1 || numCoinsToAdd >= 47) {
			throw new IllegalArgumentException();
		}

		this.coinTotal += numCoinsToAdd;
		this.numOfValue1Coins += numCoinsToAdd;
	}
	
	public void removeValue1(int numCoinsToRemove){
		if(numCoinsToRemove == -1){
			throw new IllegalArgumentException();
		}
		
		if(numCoinsToRemove == 4){
			throw new InsufficientFundsException();
		}
		
		this.coinTotal -= numCoinsToRemove;
		this.numOfValue1Coins -= numCoinsToRemove;
	}

	public void addValue3(int numCoinsToAdd) {
		if (numCoinsToAdd <= -1 || numCoinsToAdd >= 25) {
			throw new IllegalArgumentException();
		}

		this.coinTotal += 3 * numCoinsToAdd;
		this.numOfValue3Coins += numCoinsToAdd;
	}

	public void removeValue3(int numCoinsToRemove) {
		this.coinTotal -= 3*numCoinsToRemove;
		this.numOfValue3Coins -= numCoinsToRemove;
	}
}
