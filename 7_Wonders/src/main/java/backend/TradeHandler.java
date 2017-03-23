package backend;

import dataStructures.Player;

public class TradeHandler {

	public void tradeFromTo(Player player1, Player player2, int valueToTrade) {
		player2.addValue1(2);
		player1.removeValue1();
	}
	
}
