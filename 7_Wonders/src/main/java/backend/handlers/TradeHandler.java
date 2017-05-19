package backend.handlers;

import java.util.ResourceBundle;

import backend.GameManager.CardinalDirection;
import dataStructures.GameBoard;
import dataStructures.playerData.Chip;
import dataStructures.playerData.Chip.ChipType;
import dataStructures.playerData.Chip.ChipValue;
import dataStructures.playerData.Player;
import exceptions.InvalidTradeException;
import utils.Translate;
import utils.TranslateWithTemplate;

public class TradeHandler {
	private GameBoard board;

	public TradeHandler(GameBoard board) {
		this.board = board;
	}

	public void tradeCoinsFromTo(Player from, Player to, int valueToTrade) {
		if (to != this.board.getPreviousPlayer() && to != this.board.getNextPlayer()) {
			throw new InvalidTradeException(Translate.getNewResourceBundle().getString("cannotTradeToPlayer"));
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
	
	public void tradeForEntity(Player from, Player to, Enum entity) {
		boolean discountSuccesful = false;
		if (from.storagePileContainsCardByName("East Trading Post")) {
			discountSuccesful = tryTradeWithDiscountEast(from, to);
		} else if (from.storagePileContainsCardByName("West Trading Post")) {
			discountSuccesful = tryTradeWithDiscountWest(from, to);
		} else if (from.storagePileContainsCardByName("Marketplace")) {
			discountSuccesful = true;
		}

		this.tradeFromToForEntity(from, to, entity, discountSuccesful);
	}
	
	private boolean tryTradeWithDiscountEast(Player from, Player to) {
		int fromPosition = this.board.getPlayers().indexOf(from);
		int toPosition = this.board.getPlayers().indexOf(to);
		fromPosition = correctFromIndex(CardinalDirection.EAST, fromPosition);
		return (fromPosition == toPosition) ? true : false;
	}
	
	private boolean tryTradeWithDiscountWest(Player from, Player to) {
		int fromPosition = this.board.getPlayers().indexOf(from);
		int toPosition = this.board.getPlayers().indexOf(to);
		fromPosition = correctFromIndex(CardinalDirection.WEST, fromPosition);
		return (fromPosition == toPosition) ? true : false;
	}
	
	private int correctFromIndex(CardinalDirection direction, int fromPosition) {
		int indexCorrection = getIndexCorrection(direction, fromPosition);
		int comparisonIndex = getComparisonIndex(direction);
		
		if (indexCorrection == comparisonIndex) {
			indexCorrection = getNewFromIndex(direction);
		}

		return indexCorrection;
	}

	public int getIndexCorrection(CardinalDirection direction, int index) {
		int correction = (direction == CardinalDirection.EAST) ? 1 : -1;
		return index + correction;
	}

	public int getComparisonIndex(CardinalDirection direction) {
		return (direction == CardinalDirection.EAST) ? this.board.getNumPlayers() : -1;
	}

	public int getNewFromIndex(CardinalDirection direction) {
		return (direction == CardinalDirection.EAST) ? 0 : this.board.getNumPlayers() - 1;
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
			String msg = TranslateWithTemplate.prepareStringTemplateWithStringArg(Translate.getNewResourceBundle().getString(entity.toString()),
					"noResourceForTradingTemplate", Translate.getNewResourceBundle());
			throw new InvalidTradeException(msg);
		}
	}
}