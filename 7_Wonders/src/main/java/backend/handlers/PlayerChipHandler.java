package backend.handlers;

import dataStructures.playerData.Chip;
import dataStructures.playerData.Chip.ChipType;
import dataStructures.playerData.Chip.ChipValue;
import dataStructures.playerData.Player;
import exceptions.InsufficientFundsException;

public final class PlayerChipHandler {
	
	public static void addValue1(Player player, int numChipsToAdd, Chip.ChipType chipType) {
		validateNumChipsToAdd(numChipsToAdd, Chip.ChipValue.ONE);

		if (chipType == ChipType.COIN) {
			player.getPlayerChips().coinTotal += numChipsToAdd;
			player.getPlayerChips().numOfValue1Coins += numChipsToAdd;
		} else {
			player.getPlayerChips().conflictTotal += numChipsToAdd;
			player.getPlayerChips().numOfValue1ConflictTokens += numChipsToAdd;
		}
	}

	public static void addValue3(Player player, int numChipsToAdd, ChipType chipType) {
		validateNumChipsToAdd(numChipsToAdd, Chip.ChipValue.THREE);

		if (chipType == ChipType.COIN) {
			player.getPlayerChips().coinTotal += 3 * numChipsToAdd;
			player.getPlayerChips().numOfValue3Coins += numChipsToAdd;
		} else {
			player.getPlayerChips().conflictTotal += 3 * numChipsToAdd;
			player.getPlayerChips().numOfValue3ConflictTokens += numChipsToAdd;
		}
	}

	public static void addValue5(Player player, int numChipsToAdd, ChipType chipType) {
		validateNumChipsToAdd(numChipsToAdd, Chip.ChipValue.FIVE);

		if (chipType == ChipType.COIN) {
			player.getPlayerChips().numOfValue5Coins += numChipsToAdd;
			player.getPlayerChips().coinTotal += 5 * numChipsToAdd;
		} else {
			player.getPlayerChips().numOfValue5ConflictTokens += numChipsToAdd;
			player.getPlayerChips().conflictTotal += 5 * numChipsToAdd;
		}
	}

	public static void addValueNeg1(Player player, int numChipsToAdd, ChipType chipType) {
		if (chipType == ChipType.COIN) {
			throw new IllegalArgumentException("Cannot have a negative 1 coin value");
		}

		validateNumChipsToAdd(numChipsToAdd, Chip.ChipValue.NEG1);

		player.getPlayerChips().conflictTotal += (-1) * numChipsToAdd;
		player.getPlayerChips().numOfValueNeg1ConflictTokens += numChipsToAdd;
	}

	private static void validateNumChipsToAdd(int numChips, Chip.ChipValue type) {
		int max;
		String chipType;

		if (type == ChipValue.ONE) {
			max = 46;
			chipType = "1";
		} else if (type == ChipValue.THREE) {
			max = 24;
			chipType = "3";
		} else if (type == ChipValue.FIVE) {
			max = 15;
			chipType = "5";
		} else {
			max = 21;
			chipType = "-1";
		}

		if (numChips <= -1 || numChips > max) {
			throw new IllegalArgumentException("Cannot add " + numChips + " value " + chipType + " chip(s)");
		}
	}

	public static void removeValue1(Player player, int numCoinsToRemove, ChipType chipType) {
		validateNumChipsToRemove(player, numCoinsToRemove, chipType, Chip.ChipValue.ONE);
		
		if (chipType == ChipType.COIN) {
			player.getPlayerChips().coinTotal -= numCoinsToRemove;
			player.getPlayerChips().numOfValue1Coins -= numCoinsToRemove;
		} else {
			player.getPlayerChips().conflictTotal -= numCoinsToRemove;
			player.getPlayerChips().numOfValue1ConflictTokens -= numCoinsToRemove;
		}
	}

	public static void removeValue3(Player player, int numChipsToRemove, ChipType chipType) {
		validateNumChipsToRemove(player, numChipsToRemove, chipType, Chip.ChipValue.THREE);
		
		if (chipType == ChipType.COIN) {
			player.getPlayerChips().coinTotal -= 3 * numChipsToRemove;
			player.getPlayerChips().numOfValue3Coins -= numChipsToRemove;
		} else {
			player.getPlayerChips().conflictTotal -= 3 * numChipsToRemove;
			player.getPlayerChips().numOfValue3ConflictTokens -= numChipsToRemove;
		}
	}

	public static void removeValue5(Player player, int numChipsToRemove, ChipType chipType) {
		validateNumChipsToRemove(player, numChipsToRemove, chipType, Chip.ChipValue.FIVE);
		
		if (chipType == ChipType.COIN) {
			player.getPlayerChips().coinTotal -= 5 * numChipsToRemove;
			player.getPlayerChips().numOfValue5Coins -= numChipsToRemove;
		} else {
			player.getPlayerChips().conflictTotal -= 5 * numChipsToRemove;
			player.getPlayerChips().numOfValue5ConflictTokens -= numChipsToRemove;
		}
	}

	private static void validateNumChipsToRemove(Player player, int numChips, ChipType chipType, Chip.ChipValue value) {
		if (numChips <= -1) {
			throw new IllegalArgumentException("Cannot remove " + numChips + " value " + chipValueToString(value) + " chip(s)");
		}

		int numChipsToCheck;
		
		if(chipType == ChipType.COIN){
			numChipsToCheck = getNumOfCoinValue(player, value);
		}else{
			numChipsToCheck = getNumberOfTokens(player, value);
		}
		

		if (numChips > numChipsToCheck) {
			String msg = "Player does not have " + numChips + " value " + chipValueToString(value) + " chip(s)";
			throw new InsufficientFundsException(msg);
		}
	}
	
	private static int getNumOfCoinValue(Player player, Chip.ChipValue type) {
		if (type == Chip.ChipValue.ONE) {
			return player.getPlayerChips().numOfValue1Coins;
		} else if (type == Chip.ChipValue.FIVE) {
			return player.getPlayerChips().numOfValue5Coins;
		}

		return player.getPlayerChips().numOfValue3Coins;
	}

	private static String chipValueToString(Chip.ChipValue type) {
		switch (type) {
		case ONE:
			return "1";
		case THREE:
			return "3";
		case FIVE:
			return "5";
		default:
			throw new IllegalArgumentException("Bad Coin.CoinValue");
		}
	}

	private static int getNumberOfTokens(Player player, Chip.ChipValue type) {
		if (type == ChipValue.ONE) {
			return player.getNumValue1ConflictTokens();
		}
		if (type == ChipValue.THREE) {
			return player.getNumValue3ConflictTokens();
		}
		if (type == ChipValue.FIVE) {
			return player.getNumValue5ConflictTokens();
		}
		
		return player.getNumValueNeg1ConflictTokens();
	}
	
	public static void removeTotalCoins(Player player, int total) {
		int numValue3CoinsToRemove = total / 3;
		int numValue1CoinsToRemove = total % 3;

		if (numValue3CoinsToRemove > player.getPlayerChips().numOfValue3Coins) {
			numValue1CoinsToRemove += 3 * (numValue3CoinsToRemove - player.getPlayerChips().numOfValue3Coins);
			numValue3CoinsToRemove = player.getPlayerChips().numOfValue3Coins;
		}

		removeValue3(player, numValue3CoinsToRemove, ChipType.COIN);
		removeValue1(player, numValue1CoinsToRemove, ChipType.COIN);
	}

}
