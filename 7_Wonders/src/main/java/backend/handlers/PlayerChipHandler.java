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
			throw new IllegalArgumentException("Cannot remove " + numChips + " value " + chipValueToString(value) + " chip(s)");
		}

		int numChipsToCheck;
		
		if(chipType == ChipType.COIN){
			numChipsToCheck = player.getCoins().get(value);
		}else{
			numChipsToCheck = player.getConflictTokens().get(value);
		}
		

		if (numChips > numChipsToCheck) {
			String msg = "Player does not have " + numChips + " value " + chipValueToString(value) + " chip(s)";
			throw new InsufficientFundsException(msg);
		}
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
