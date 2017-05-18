package backend.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import constants.GeneralEnums.CostType;
import dataStructures.GameBoard;
import dataStructures.gameMaterials.Card;
import dataStructures.gameMaterials.Effect.EffectType;
import dataStructures.gameMaterials.ValueEffect.ValueType;
import dataStructures.gameMaterials.EntityEffect;
import dataStructures.gameMaterials.ValueEffect;
import dataStructures.playerData.Player;
import exceptions.InsufficientFundsException;
import utils.Translate;

public class PlayerTurnHandler {

	private ResourceBundle messages = Translate.getNewResourceBundle();

	public void buildStructure(Player current, Card card, GameBoard board) {
		if (current.storagePileContainsCardByName(card.getName())) {
			throw new IllegalArgumentException(this.messages.getString("playerHasAlreadyHasStructure"));
		} else if (checkForPreviousStructure(current, card)) {
			return;
		}

		validateCost(current, card);
		enableValueEffect(current, card, board);
		current.addToStoragePile(card);
		current.removeFromCurrentHand(card);
	}

	private void enableValueEffect(Player current, Card card, GameBoard board) {
		if (card.getEffectType() == EffectType.VALUE) {
			ValueEffect effect = (ValueEffect) card.getEffect();

			if (effect.getValueType() == ValueType.VICTORYPOINT) {
				current.addNumVictoryPoints(effect.getValueAmount());
			} else if (effect.getValueType() == ValueType.COIN) {
				if (card.getName().equals("Tavern")) {
					board.giveNumCoins(current, effect.getValueAmount());
				}
			} else {
				current.addNumShields(effect.getValueAmount());
			}
		}
	}

	private boolean checkForPreviousStructure(Player current, Card card) {
		String previousStructure = card.getPreviousStructureName();
		for (Card storage : current.getStoragePile()) {
			if (storage.getName().contains(previousStructure)) {
				current.addToStoragePile(card);
				current.removeFromCurrentHand(card);
				return true;
			}
		}
		return false;
	}

	private void validateCost(Player current, Card card) {
		if (card.getCostType() == CostType.COIN) {
			int coinCost = card.getCost().get(CostType.COIN);
			PlayerChipHandler.removeTotalCoins(current, coinCost);
		} else if (card.getCostType() != CostType.NONE) {
			validatePlayerHasEntitiesForCard(current, card);
		}
	}

	private void validatePlayerHasEntitiesForCard(Player current, Card card) {
		HashMap<Enum, Integer> cost = card.getCost();
		ArrayList<Card> storage = current.getStoragePile();
		ArrayList<Card> usedEntities = new ArrayList<Card>();

		for (Enum key : cost.keySet()) {
			findCostInStorage(current, cost, storage, usedEntities, key);
		}
	}

	private void findCostInStorage(Player current, HashMap<Enum, Integer> cost, ArrayList<Card> storage,
			ArrayList<Card> usedEntities, Enum key) {
		int numcost = cost.get(key);
		for (Card sCards : storage) {
			if (usedEntities.contains(sCards))
				continue;
			numcost = decrementCostFromEntity(usedEntities, key, numcost, sCards);
		}
		numcost = searchCurrentTradesForCost(current, key, numcost);

		if (numcost > 0) {
			throw new InsufficientFundsException(this.messages.getString("noRequiredItemToBuildStructure"));
		}
	}

	private int searchCurrentTradesForCost(Player current, Enum key, int numcost) {
		if (numcost > 0) {
			if (current.getCurrentTrades().containsKey(key)) {
				numcost -= current.getCurrentTrades().get(key);
			}
		}
		return numcost;
	}

	private int decrementCostFromEntity(ArrayList<Card> usedEntities, Enum key, int numcost, Card sCards) {
		if (sCards.getEffectType() == EffectType.ENTITY) {
			EntityEffect effect = (EntityEffect) sCards.getEffect();
			if (effect.getEntities().containsKey(key)) {
				numcost -= effect.getEntities().get(key);
				usedEntities.add(sCards);
			}
		}
		return numcost;
	}

	public void discardSelectedCard(Player player, Card card, GameBoard board) {
		board.addToDiscardPile(player, card);
		player.removeFromCurrentHand(card);
	}
}