package dataStructures;

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
	public void addValue1(int i) {
		this.coinTotal += i;
		this.numOfValue1Coins += i;
	}
	public void addValue3(int i) {
		this.coinTotal += 3*i;
		this.numOfValue3Coins += i;	
	}
}
