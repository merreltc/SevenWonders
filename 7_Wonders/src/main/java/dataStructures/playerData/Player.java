package dataStructures.playerData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import constants.GeneralEnums.Science;
import dataStructures.gameMaterials.Card;
import dataStructures.gameMaterials.Effect.EffectType;
import dataStructures.gameMaterials.EntityEffect;
import dataStructures.gameMaterials.Wonder;
import dataStructures.playerData.Chip.ChipType;
import dataStructures.playerData.Chip.ChipValue;
import exceptions.InsufficientFundsException;
import utils.Translate;

public class Player {
	private String name = "Jane Doe";
	private ResourceBundle messages = Translate.getNewResourceBundle();

	private int numShields = 0;
	private int numVictoryPoints = 0;

	private Wonder wonder;

	private ArrayList<Card> currentHand = new ArrayList<Card>();
	private StoragePile storagePile = new StoragePile();

	private HashMap<Enum, Integer> currentTrades = new HashMap<Enum, Integer>();
	private PlayerChips playerChips = new PlayerChips();

	private class PlayerChips {
		protected int numOfValue1Coins = 3;
		protected int numOfValue3Coins = 0;
		protected int numOfValue5Coins = 0;
		protected int numOfValue1ConflictTokens = 0;
		protected int numOfValue3ConflictTokens = 0;
		protected int numOfValue5ConflictTokens = 0;
		protected int conflictTotal = 0;
		protected int coinTotal = 3;
		public int numOfValueNeg1ConflictTokens = 0;

	}

	public Player(String playerName, Wonder.WonderType wonder) {
		this.name = playerName;
		this.wonder = new Wonder('A', wonder);
	}

	public void addValue1(int numChipsToAdd, Chip.ChipType chipType) {
		validateNumChipsToAdd(numChipsToAdd, Chip.ChipValue.ONE);

		if (chipType == ChipType.COIN) {
			this.playerChips.coinTotal += numChipsToAdd;
			this.playerChips.numOfValue1Coins += numChipsToAdd;
		} else {
			this.playerChips.conflictTotal += numChipsToAdd;
			this.playerChips.numOfValue1ConflictTokens += numChipsToAdd;
		}
	}

	public void addValue3(int numChipsToAdd, ChipType chipType) {
		validateNumChipsToAdd(numChipsToAdd, Chip.ChipValue.THREE);

		if (chipType == ChipType.COIN) {
			this.playerChips.coinTotal += 3 * numChipsToAdd;
			this.playerChips.numOfValue3Coins += numChipsToAdd;
		} else {
			this.playerChips.conflictTotal += 3 * numChipsToAdd;
			this.playerChips.numOfValue3ConflictTokens += numChipsToAdd;
		}
	}

	public void addValue5(int numChipsToAdd, ChipType chipType) {
		validateNumChipsToAdd(numChipsToAdd, Chip.ChipValue.FIVE);

		if (chipType == ChipType.COIN) {
			this.playerChips.numOfValue5Coins += numChipsToAdd;
			this.playerChips.coinTotal += 5 * numChipsToAdd;
		} else {
			this.playerChips.numOfValue5ConflictTokens += numChipsToAdd;
			this.playerChips.conflictTotal += 5 * numChipsToAdd;
		}
	}

	public void addValueNeg1(int numChipsToAdd, ChipType chipType) {
		if (chipType == ChipType.COIN) {
			throw new IllegalArgumentException("Cannot have a negative 1 coin value");
		}

		validateNumChipsToAdd(numChipsToAdd, Chip.ChipValue.NEG1);

		this.playerChips.conflictTotal += (-1) * numChipsToAdd;
		this.playerChips.numOfValueNeg1ConflictTokens += numChipsToAdd;
	}

	private void validateNumChipsToAdd(int numChips, Chip.ChipValue type) {
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

	// Add second input for chips and add remove value 1
	public void removeValue1(int numCoinsToRemove, ChipType chipType) {
		validateNumChipsToRemove(numCoinsToRemove, chipType, Chip.ChipValue.ONE);
		
		if (chipType == ChipType.COIN) {
			this.playerChips.coinTotal -= numCoinsToRemove;
			this.playerChips.numOfValue1Coins -= numCoinsToRemove;
		} else {
			this.playerChips.conflictTotal -= numCoinsToRemove;
			this.playerChips.numOfValue1ConflictTokens -= numCoinsToRemove;
		}
	}

	public void removeValue3(int numChipsToRemove, ChipType chipType) {
		validateNumChipsToRemove(numChipsToRemove, chipType, Chip.ChipValue.THREE);
		
		if (chipType == ChipType.COIN) {
			this.playerChips.coinTotal -= 3 * numChipsToRemove;
			this.playerChips.numOfValue3Coins -= numChipsToRemove;
		} else {
			this.playerChips.conflictTotal -= 3 * numChipsToRemove;
			this.playerChips.numOfValue3ConflictTokens -= numChipsToRemove;
		}
	}

	public void removeValue5(int numChipsToRemove, ChipType chipType) {
		validateNumChipsToRemove(numChipsToRemove, chipType, Chip.ChipValue.FIVE);
		
		if (chipType == ChipType.COIN) {
			this.playerChips.coinTotal -= 5 * numChipsToRemove;
			this.playerChips.numOfValue5Coins -= numChipsToRemove;
		} else {
			validateNumChipsToRemove(numChipsToRemove, chipType, Chip.ChipValue.FIVE);
			this.playerChips.conflictTotal -= 5 * numChipsToRemove;
			this.playerChips.numOfValue5ConflictTokens -= numChipsToRemove;
		}
	}

	private void validateNumChipsToRemove(int numChips, ChipType chipType, Chip.ChipValue value) {
		if (numChips <= -1) {
			throw new IllegalArgumentException("Cannot remove " + numChips + " value " + chipValueToString(value) + " chip(s)");
		}

		int numChipsToCheck;
		
		if(chipType == ChipType.COIN){
			numChipsToCheck = getNumOfCoinValue(value);
		}else{
			numChipsToCheck = this.getNumberOfTokens(value);
		}
		

		if (numChips > numChipsToCheck) {
			String msg = "Player does not have " + numChips + " value " + chipValueToString(value) + " chip(s)";
			throw new InsufficientFundsException(msg);
		}
	}

	public String toString() {
		return "Name: " + this.name + "\nCoin Total: " + this.getCoinTotal();
	}

	@Override
	public boolean equals(Object object) {
		Player temp = (Player) object;
		return this.name.equals(temp.getName());
	}

	private int getNumOfCoinValue(Chip.ChipValue type) {
		if (type == Chip.ChipValue.ONE) {
			return this.playerChips.numOfValue1Coins;
		} else if (type == Chip.ChipValue.FIVE) {
			return this.playerChips.numOfValue5Coins;
		}

		return this.playerChips.numOfValue3Coins;
	}

	private String chipValueToString(Chip.ChipValue type) {
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

	private int getNumberOfTokens(Chip.ChipValue type) {
		if (type == ChipValue.ONE) {
			return this.getNumValue1ConflictTokens();
		}
		if (type == ChipValue.THREE) {
			return this.getNumValue3ConflictTokens();
		}
		if (type == ChipValue.FIVE) {
			return this.getNumValue5ConflictTokens();
		}
		
		return this.getNumValueNeg1ConflictTokens();
	}

	public String getName() {
		return this.name;
	}

	public int getCoinTotal() {
		return this.playerChips.coinTotal;
	}

	public int getNumValue1Coins() {
		return this.playerChips.numOfValue1Coins;
	}

	public int getNumValue3Coins() {
		return this.playerChips.numOfValue3Coins;
	}

	public int getNumValue5Coins() {
		return this.playerChips.numOfValue5Coins;
	}

	public int getConflictTotal() {
		return this.playerChips.conflictTotal;
	}

	public ArrayList<Card> getCurrentHand() {
		return this.currentHand;
	}

	public void setCurrentHand(ArrayList<Card> currentHand) {
		this.currentHand = currentHand;
	}

	public ArrayList<Card> getStoragePile() {
		return this.storagePile.getEntireStoragePile();
	}

	public void setStoragePile(ArrayList<Card> storagePile) {
		for (Card card : storagePile) {
			this.storagePile.addCard(card);
		}
	}

	public boolean storagePileContainsEntity(Enum entity) {
		for (Card storage : this.storagePile.getCommercePile()) {
			if (storage.getEffectType().equals(EffectType.ENTITY)) {
				EntityEffect effect = (EntityEffect) storage.getEffect();
				if (effect.getEntities().containsKey(entity)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean storagePileContainsCardByName(String name) {
		for (Card storage : this.storagePile.getEntireStoragePile()) {
			if (storage.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	public Card getCardFromEndGame(int index) {
		if (index >= this.storagePile.getEndGamePile().size()) {
			throw new IllegalArgumentException("End of End Game pile reached");
		}
		return this.storagePile.getEndGamePile().get(index);
	}

	public int[] getNumberOfEachScience() {
		int[] amounts = { 0, 0, 0 };
		ArrayList<Card> cards = this.storagePile.getSciencePile();
		for (Card card : cards) {
			EntityEffect effect = (EntityEffect) card.getEffect();
			for (Enum type : effect.getEntities().keySet()) {
				if (type == Science.PROTRACTOR) {
					amounts[0] += 1;
				} else if (type == Science.TABLET) {
					amounts[1] += 1;
				} else {
					amounts[2] += 1;
				}
			}
		}
		return amounts;
	}

	public HashMap<Enum, Integer> getCurrentTrades() {
		return this.currentTrades;
	}

	public void addTradedValue(Enum trade) {
		int value = 0;
		if (this.currentTrades.containsKey(trade)) {
			value = (int) this.currentTrades.get(trade);
		}
		this.currentTrades.put(trade, value + 1);
	}

	public void removeCurrentTrades() {
		this.currentTrades.clear();
	}

	public void addToStoragePile(Card card) {
		this.storagePile.addCard(card);
	}

	public void removeFromCurrentHand(Card card) {
		this.currentHand.remove(card);
	}

	public Wonder getWonder() {
		return wonder;
	}

	public void removeTotalCoins(int total) {
		int numValue3CoinsToRemove = total / 3;
		int numValue1CoinsToRemove = total % 3;

		if (numValue3CoinsToRemove > this.playerChips.numOfValue3Coins) {
			numValue1CoinsToRemove += 3 * (numValue3CoinsToRemove - this.playerChips.numOfValue3Coins);
			numValue3CoinsToRemove = this.playerChips.numOfValue3Coins;
		}

		this.removeValue3(numValue3CoinsToRemove, ChipType.COIN);
		this.removeValue1(numValue1CoinsToRemove, ChipType.COIN);
	}

	public int getNumShields() {
		return this.numShields;
	}

	public void addNumShields(int numShields) {
		this.numShields += numShields;
	}

	public int getNumVictoryPoints() {
		return this.numVictoryPoints;
	}

	public void addNumVictoryPoints(int numPoints) {
		this.numVictoryPoints += numPoints;
	}

	public int getNumValue1ConflictTokens() {
		return this.playerChips.numOfValue1ConflictTokens;
	}

	public int getNumValue3ConflictTokens() {
		return this.playerChips.numOfValue3ConflictTokens;
	}

	public int getNumValue5ConflictTokens() {
		return this.playerChips.numOfValue5ConflictTokens;
	}

	public int getNumValueNeg1ConflictTokens() {
		return this.playerChips.numOfValueNeg1ConflictTokens;
	}
}