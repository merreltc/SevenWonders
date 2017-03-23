package backend;

import dataStructures.Player;

public class TradeHandler {

	public void tradeFromToValue1(Player player1, Player player2, int valueToTrade) {
		player2.addValue1(valueToTrade);
		player1.removeValue1(valueToTrade);
	}

	public void tradeFromToValue3(Player player1, Player player2, int valueToTrade) {
		player2.addValue3(valueToTrade);
		player1.removeValue3(valueToTrade);
	}	
}
