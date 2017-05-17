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

	public class PlayerChips {
		public int numOfValue1Coins = 3;
		public int numOfValue3Coins = 0;
		public int numOfValue5Coins = 0;
		public int numOfValue1ConflictTokens = 0;
		public int numOfValue3ConflictTokens = 0;
		public int numOfValue5ConflictTokens = 0;
		public int conflictTotal = 0;
		public int coinTotal = 3;
		public int numOfValueNeg1ConflictTokens = 0;

	}

	public Player(String playerName, Wonder.WonderType wonder) {
		this.name = playerName;
		this.wonder = new Wonder('A', wonder);
	}

	public String toString() {
		return "Name: " + this.name + "\nCoin Total: " + this.getCoinTotal();
	}

	@Override
	public boolean equals(Object object) {
		Player temp = (Player) object;
		return this.name.equals(temp.getName());
	}

	public String getName() {
		return this.name;
	}

	public int getCoinTotal() {
		return this.getPlayerChips().coinTotal;
	}

	public int getNumValue1Coins() {
		return this.getPlayerChips().numOfValue1Coins;
	}

	public int getNumValue3Coins() {
		return this.getPlayerChips().numOfValue3Coins;
	}

	public int getNumValue5Coins() {
		return this.getPlayerChips().numOfValue5Coins;
	}

	public int getConflictTotal() {
		return this.getPlayerChips().conflictTotal;
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
		return this.getPlayerChips().numOfValue1ConflictTokens;
	}

	public int getNumValue3ConflictTokens() {
		return this.getPlayerChips().numOfValue3ConflictTokens;
	}

	public int getNumValue5ConflictTokens() {
		return this.getPlayerChips().numOfValue5ConflictTokens;
	}

	public int getNumValueNeg1ConflictTokens() {
		return this.getPlayerChips().numOfValueNeg1ConflictTokens;
	}

	public PlayerChips getPlayerChips() {
		return this.playerChips;
	}
}