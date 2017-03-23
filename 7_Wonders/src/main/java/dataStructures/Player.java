package dataStructures;

public class Player {
	private int coinTotal = 3;
	private int conflictTotal = 0;
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
		return 3;
	}
	public int getNumValue3Coins() {
		return 0;
	}
}
