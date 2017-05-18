package backend.handlers;

import java.util.ResourceBundle;

import dataStructures.GameBoard;
import dataStructures.playerData.Chip;
import dataStructures.playerData.Chip.ChipType;
import dataStructures.playerData.Chip.ChipValue;
import dataStructures.playerData.Player;
import exceptions.InvalidTradeException;
import utils.Translate;

public class TradeHandler {
	private GameBoard board;
	private ResourceBundle messages = Translate.getNewResourceBundle();

	public TradeHandler(GameBoard board) {
		this.board = board;
	}

	public void tradeCoinsFromTo(Player from, Player to, int valueToTrade) {
		if (to != this.board.getPreviousPlayer() && to != this.board.getNextPlayer()) {
			throw new InvalidTradeException(messages.getString("cannotTradeToPlayer"));
		}

		// player's number of value 3 coins is calculated first because
		// they have precedence over number of value 1 coins
		int numValue3Coins = getNumValue3Coins(from, valueToTrade);
		int valueRemaining = valueToTrade - (numValue3Coins * 3);
		int numValue1Coins = valueRemaining;

		tradeFromToValue1(from, to, numValue1Coins);
		tradeFromToValue3(from, to, numValue3Coins);
	}

	private static int getNumValue3Coins(Player from, int valueToTrade) {
		int fromPlayerNumValueValue3Coins = from.getCoins().get(ChipValue.THREE);
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

	public static void tradeFromToValue1(Player from, Player to, int numCoinsToTrade) {
		PlayerChipHandler.removeValue1(from, numCoinsToTrade, ChipType.COIN);
		PlayerChipHandler.addValue1(to, numCoinsToTrade, Chip.ChipType.COIN);
	}

	public static void tradeFromToValue3(Player from, Player to, int numCoinsToTrade) {
		PlayerChipHandler.removeValue3(from, numCoinsToTrade, Chip.ChipType.COIN);
		PlayerChipHandler.addValue3(to, numCoinsToTrade, Chip.ChipType.COIN);
	}

	public void tradeFromToForEntity(Player from, Player to, Enum entity, boolean discount) {
		if (to.storagePileContainsEntity(entity)) {
			if (discount) {
				this.tradeCoinsFromTo(from, to, 1);
			} else {
				this.tradeCoinsFromTo(from, to, 2);
			}
			from.addTradedValue(entity);
		} else {
			String msg = Translate.prepareStringTemplateWithStringArg(this.messages.getString(entity.toString()),
					"noResourceForTradingTemplate", this.messages);
			throw new InvalidTradeException(msg);
		}
	}
}