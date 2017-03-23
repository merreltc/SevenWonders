package backend;

import dataStructures.Player;

public class TradeHandler {

	public static void tradeFromTo(Player from, Player to, int valueToTrade) {
		//player's number of value 3 coins has precedence over number of value 1 coins
		int numValue1Coins = getNumValue1Coins(valueToTrade);
		int numValue3Coins = getNumValue3Coins(valueToTrade);

		tradeFromToValue1(from, to, numValue1Coins);
		tradeFromToValue3(from, to, numValue3Coins);
	}

	private static int getNumValue1Coins(int valueToTrade) {
		return valueToTrade % 3;
	}
	
	private static int getNumValue3Coins(int valueToTrade) {
		return valueToTrade / 3;
	}
	
	public static void tradeFromToValue1(Player from, Player to, int numCoinsToTrade) {
		from.removeValue1(numCoinsToTrade);
		to.addValue1(numCoinsToTrade);
	}

	public static void tradeFromToValue3(Player from, Player to, int numCoinsToTrade) {
		from.removeValue3(numCoinsToTrade);
		to.addValue3(numCoinsToTrade);
	}
	
}
