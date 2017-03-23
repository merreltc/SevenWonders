package backend;

import dataStructures.Player;

public class TradeHandler {

	public void tradeFromToValue1(Player from, Player to, int valueToTrade) {
		from.removeValue1(valueToTrade);
		to.addValue1(valueToTrade);
	}

	public void tradeFromToValue3(Player from, Player to, int valueToTrade) {
		from.removeValue3(valueToTrade);
		to.addValue3(valueToTrade);
	}	
}
