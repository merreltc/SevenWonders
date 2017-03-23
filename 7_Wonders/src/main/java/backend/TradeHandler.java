package backend;

import dataStructures.Player;

public class TradeHandler {

	public void tradeFromTo(Player from, Player to, int valueToTrade) {
		int numValue1Coins = getNumValue1Coins(valueToTrade);
		int numValue3Coins = getNumValue3Coins(valueToTrade);

		tradeFromToValue1(from, to, numValue1Coins);
		tradeFromToValue3(from, to, numValue3Coins);
	}

	private int getNumValue1Coins(int valueToTrade) {
		return valueToTrade % 3;
	}
	
	private int getNumValue3Coins(int valueToTrade) {
		return valueToTrade / 3;
	}
	
	public void tradeFromToValue1(Player from, Player to, int numCoinsToTrade) {
		from.removeValue1(numCoinsToTrade);
		to.addValue1(numCoinsToTrade);
	}

	public void tradeFromToValue3(Player from, Player to, int numCoinsToTrade) {
		from.removeValue3(numCoinsToTrade);
		to.addValue3(numCoinsToTrade);
	}
	
}
