package backend;

import dataStructures.GameBoard;
import dataStructures.Player;
import exceptions.InvalidTradeException;

public class TradeHandler {
	private GameBoard board;
	
	public TradeHandler(GameBoard board) {
		this.board = board;
	}

	public void tradeFromTo(Player from, Player to, int valueToTrade) {
		if(to == this.board.getPlayer(2)){
			throw new InvalidTradeException("You cannot trade to this player");
		}
		
		// player's number of value 3 coins is calculated first because
		// they have precedence over number of value 1 coins
		int numValue3Coins = getNumValue3Coins(from, valueToTrade);
		int valueRemaining = valueToTrade - (numValue3Coins * 3);
		int numValue1Coins = getNumValue1Coins(valueRemaining);

		tradeFromToValue1(from, to, numValue1Coins);
		tradeFromToValue3(from, to, numValue3Coins);
	}

	private static int getNumValue3Coins(Player from, int valueToTrade) {
		int fromPlayerNumValueValue3Coins = from.getNumValue3Coins();
		int fromPlayerValueOfValue3Coins = 3 * fromPlayerNumValueValue3Coins;

		int valueofValue3Removed = getValueOfValue3CoinsRemoved(valueToTrade, fromPlayerValueOfValue3Coins);

		int numOfRemovedValue3 = valueofValue3Removed / 3;

		return numOfRemovedValue3;
	}

	private static int getValueOfValue3CoinsRemoved(int valueToTrade, int valueOfValue3Coins) {
		if (valueToTrade >= valueOfValue3Coins) {
			return valueOfValue3Coins;
		}

		return valueToTrade;
	}

	private static int getNumValue1Coins(int valueRemaining) {
		return valueRemaining;
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
