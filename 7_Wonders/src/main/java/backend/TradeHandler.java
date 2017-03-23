package backend;

import dataStructures.Player;

public class TradeHandler {

	public void tradeFromTo(Player from, Player to, int valueToTrade) {
		tradeFromToValue1(from, to, 1);
		tradeFromToValue3(from, to, 1);
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
