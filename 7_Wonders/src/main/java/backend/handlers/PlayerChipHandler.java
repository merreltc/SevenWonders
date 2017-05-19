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
			int currentNum = player.getCoins().get(ChipValue.ONE);
			player.getCoins().put(ChipValue.ONE, currentNum + numChipsToAdd);
		} else {
			int currentNum = player.getConflictTokens().get(ChipValue.ONE);
			player.getConflictTokens().put(ChipValue.ONE, currentNum + numChipsToAdd);
		}
	}

	public static void addValue3(Player player, int numChipsToAdd, ChipType chipType) {
		validateNumChipsToAdd(numChipsToAdd, Chip.ChipValue.THREE);

		if (chipType == ChipType.COIN) {
			int currentNum = player.getCoins().get(ChipValue.THREE);
			player.getCoins().put(ChipValue.THREE, currentNum + numChipsToAdd);
		} else {
			int currentNum = player.getConflictTokens().get(ChipValue.THREE);
			player.getConflictTokens().put(ChipValue.THREE, currentNum + numChipsToAdd);
		}
	}

	public static void addValue5(Player player, int numChipsToAdd, ChipType chipType) {
		if (chipType == ChipType.COIN) {
			throw new IllegalArgumentException("Cannot have a 5 coin value");
		}
		validateNumChipsToAdd(numChipsToAdd, Chip.ChipValue.FIVE);

		int currentNum = player.getConflictTokens().get(ChipValue.FIVE);
		player.getConflictTokens().put(ChipValue.FIVE, currentNum + numChipsToAdd);
	}

	public static void addValueNeg1(Player player, int numChipsToAdd, ChipType chipType) {
		if (chipType == ChipType.COIN) {
			throw new IllegalArgumentException("Cannot have a negative 1 coin value");
		}

		validateNumChipsToAdd(numChipsToAdd, Chip.ChipValue.NEG1);

		int currentNum = player.getConflictTokens().get(ChipValue.NEG1);
		player.getConflictTokens().put(ChipValue.NEG1, currentNum + numChipsToAdd);
	}

	private static void validateNumChipsToAdd(int numChips, Chip.ChipValue type) {
		int max = setMaxValue(type);
		String chipType = getTypeString(type);

		if (numChips <= -1 || numChips > max) {
			throw new IllegalArgumentException("Cannot add " + numChips + " value " + chipType + " chip(s)");
		}
	}

	private static int setMaxValue(ChipValue type) {
		if (type == ChipValue.ONE) {
			return 46;
		} else if (type == ChipValue.THREE) {
			return 24;
		} else if (type == ChipValue.FIVE) {
			return 15;
		}
		return 21;
	}

	private static String getTypeString(ChipValue type) {
		if (type == ChipValue.ONE) {
			return "1";
		} else if (type == ChipValue.THREE) {
			return "3";
		} else if (type == ChipValue.FIVE) {
			return "5";
		} else {
			return "-1";
		}
	}

	public static void removeValue1(Player player, int numChipsToRemove, ChipType chipType) {
		validateNumChipsToRemove(player, numChipsToRemove, chipType, Chip.ChipValue.ONE);

		if (chipType == ChipType.COIN) {
			int currentNum = player.getCoins().get(ChipValue.ONE);
			player.getCoins().put(ChipValue.ONE, currentNum - numChipsToRemove);
		} else {
			int currentNum = player.getConflictTokens().get(ChipValue.ONE);
			player.getConflictTokens().put(ChipValue.ONE, currentNum - numChipsToRemove);
		}
	}

	public static void removeValue3(Player player, int numChipsToRemove, ChipType chipType) {
		validateNumChipsToRemove(player, numChipsToRemove, chipType, Chip.ChipValue.THREE);

		if (chipType == ChipType.COIN) {
			int currentNum = player.getCoins().get(ChipValue.THREE);
			player.getCoins().put(ChipValue.THREE, currentNum - numChipsToRemove);
		} else {
			int currentNum = player.getConflictTokens().get(ChipValue.THREE);
			player.getConflictTokens().put(ChipValue.THREE, currentNum - numChipsToRemove);
		}
	}

	public static void removeValue5(Player player, int numChipsToRemove, ChipType chipType) {
		if (chipType == ChipType.COIN) {
			throw new IllegalArgumentException("Cannot have a negative 1 coin value");
		}

		validateNumChipsToRemove(player, numChipsToRemove, chipType, Chip.ChipValue.FIVE);

		int currentNum = player.getConflictTokens().get(ChipValue.FIVE);
		player.getConflictTokens().put(ChipValue.FIVE, currentNum - numChipsToRemove);
	}

	private static void validateNumChipsToRemove(Player player, int numChips, ChipType chipType, Chip.ChipValue value) {
		if (numChips <= -1) {
			throw new IllegalArgumentException(
					"Cannot remove " + numChips + " value " + chipValueToString(value) + " chip(s)");
		}
		int numChipsToCheck = setNumChips(player, chipType, value);

		if (numChips > numChipsToCheck) {
			String msg = "Player does not have " + numChips + " value " + chipValueToString(value) + " chip(s)";
			throw new InsufficientFundsException(msg);
		}
	}

	private static int setNumChips(Player player, ChipType chipType, Chip.ChipValue value) {
		if (chipType == ChipType.COIN) {
			return player.getCoins().get(value);
		} 
		return player.getConflictTokens().get(value);
	}

	private static String chipValueToString(Chip.ChipValue type) {
		switch (type) {
		case ONE:
			return "1";
		case THREE:
			return "3";
		case FIVE:
			return "5";
		}
		return "";
	}

	public static void removeTotalCoins(Player player, int total) {
		int numValue3CoinsToRemove = total / 3;
		int numValue1CoinsToRemove = total % 3;
		int currentNumValue3Coins = player.getCoins().get(ChipValue.THREE);

		if (numValue3CoinsToRemove > currentNumValue3Coins) {
			numValue1CoinsToRemove += 3 * (numValue3CoinsToRemove - currentNumValue3Coins);
			numValue3CoinsToRemove = currentNumValue3Coins;
		}

		removeValue3(player, numValue3CoinsToRemove, ChipType.COIN);
		removeValue1(player, numValue1CoinsToRemove, ChipType.COIN);
	}

}
