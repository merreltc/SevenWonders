package dataStructures.playerData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import constants.GeneralEnums.Science;
import constants.GeneralEnums.Side;
import dataStructures.gameMaterials.Card;
import dataStructures.gameMaterials.Effect;
import dataStructures.gameMaterials.Effect.EffectType;
import dataStructures.gameMaterials.EntityEffect;
import dataStructures.gameMaterials.Level.Frequency;
import dataStructures.gameMaterials.Wonder;
import dataStructures.playerData.Chip.ChipValue;

public class Player {
	private String name = "Jane Doe";
	private int numShields = 0;
	private int numVictoryPoints = 0;

	private Wonder wonder;

	private ArrayList<Card> currentHand = new ArrayList<Card>();
	private StoragePile storagePile = new StoragePile();

	private HashMap<Enum, Integer> currentTrades = new HashMap<Enum, Integer>();
	private PlayerChips playerChips = new PlayerChips();

	public class PlayerChips {
		private HashMap<ChipValue, Integer> coins = new HashMap<ChipValue, Integer>();
		private HashMap<ChipValue, Integer> conflictTokens = new HashMap<ChipValue, Integer>();

		public PlayerChips() {
			coins.put(ChipValue.ONE, 3);
			coins.put(ChipValue.THREE, 0);
			conflictTokens.put(ChipValue.ONE, 0);
			conflictTokens.put(ChipValue.THREE, 0);
			conflictTokens.put(ChipValue.FIVE, 0);
			conflictTokens.put(ChipValue.NEG1, 0);
		}
	}

	public Player(String playerName, Wonder wonder) {
		this.name = playerName;
		this.wonder = wonder;
		addWonderResourceToPile();
	}

	public void addWonderResourceToPile() {
		HashMap<Frequency, HashSet<Effect>> effects = getResourceEffectMap();
		this.storagePile.addToWonderPile(effects);
	}

	private HashMap<Frequency, HashSet<Effect>> getResourceEffectMap() {
		HashMap<Frequency, HashSet<Effect>> effects = new HashMap<Frequency, HashSet<Effect>>();
		HashSet<Effect> effect = new HashSet<Effect>();
		effect.add(this.wonder.getResource());
		effects.put(Frequency.ONCEIMMEDIATE, effect);
		return effects;
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
	
	public HashMap<Frequency, HashSet<Effect>> buildNextLevel() {
		HashMap<Frequency, HashSet<Effect>> effects = this.wonder.buildNextLevel();
		addWonderEffectToStoragePile(effects);
		return effects;
	}

	public int getCoinTotal() {
		int total = 0;

		for (ChipValue value : this.getCoins().keySet()) {
			if (value == ChipValue.THREE) {
				total += (3 * this.getCoins().get(value));
			} else {
				total += this.getCoins().get(value);
			}
		}
		return total;
	}

	public int getConflictTotal() {
		int total = 0;

		for (ChipValue value : this.getConflictTokens().keySet()) {
			if (value == ChipValue.THREE) {
				total += (3 * this.getConflictTokens().get(value));
			} else if (value == ChipValue.FIVE) {
				total += (5 * this.getConflictTokens().get(value));
			} else if (value == ChipValue.NEG1) {
				total += (-1) * this.getConflictTokens().get(value);
			} else {
				total += this.getConflictTokens().get(value);
			}
		}
		return total;
	}

	public ArrayList<Card> getCurrentHand() {
		return this.currentHand;
	}

	public void setCurrentHand(ArrayList<Card> currentHand) {
		this.currentHand = currentHand;
	}

	public ArrayList<Card> getAllCards() {
		return this.storagePile.getAllCardStoragePile();
	}

	public HashSet<Effect> getAllEffects() {
		return this.storagePile.getEntireEffectStorage();
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
		return this.storagePile.containsCard(name);
	}

	public boolean storageContainsEffect(Effect effect) {
		return this.storagePile.containsEffect(effect);
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

	public void addWonderEffectToStoragePile(HashMap<Frequency, HashSet<Effect>> effect) {
		this.storagePile.addToWonderPile(effect);
	}

	public void addCardToStoragePile(Card card) {
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

	public HashMap<ChipValue, Integer> getConflictTokens() {
		return this.playerChips.conflictTokens;
	}

	public HashMap<ChipValue, Integer> getCoins() {
		return this.playerChips.coins;
	}

	public Side getSide() {
		return this.wonder.getSide();
	}
}