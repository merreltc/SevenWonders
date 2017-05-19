package backend.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Set;

import constants.GeneralEnums.CostType;
import constants.GeneralEnums.Good;
import constants.GeneralEnums.RawResource;
import dataStructures.GameBoard;
import dataStructures.gameMaterials.Card;
import dataStructures.gameMaterials.Effect.EffectType;
import dataStructures.gameMaterials.ValueEffect.ValueType;
import dataStructures.gameMaterials.EntityEffect;
import dataStructures.gameMaterials.ValueEffect;
import dataStructures.playerData.Player;
import exceptions.InsufficientFundsException;
import utils.DropDownMessage;
import utils.Translate;

public class PlayerTurnHandler {
	private GameBoard board;

	public void buildStructure(Player current, Card card) {
		if (current.storagePileContainsCardByName(card.getName())) {
			throw new IllegalArgumentException(
					Translate.getNewResourceBundle().getString("playerHasAlreadyHasStructure"));
		} else if (checkForPreviousStructure(current, card)) {
			return;
		}

		validateCost(current, card);
		enableValueEffect(current, card);
		current.addToStoragePile(card);
		current.removeFromCurrentHand(card);
	}

	private void enableValueEffect(Player current, Card card) {
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
		HashMap<Enum, Integer> costs = new HashMap<Enum, Integer>(card.getCost());
		for (Card sCards : current.getStoragePile()) {
			if (sCards.getEffectType() != EffectType.ENTITY) {
				continue;
			}

			EntityEffect effect = (EntityEffect) sCards.getEffect();
			Enum singleEffect = (Enum) effect.getEntities().keySet().toArray()[0];
			if (effect.getEntities().size() > 1) {
				String choice = null;
				while (choice == null) {
					choice = chooseWhichEntity(effect.getEntities(), sCards.getName());
				}

				Enum entity;
				try {
					entity = RawResource.valueOf(choice);
				} catch (Exception e) {
					entity = Good.valueOf(choice);
				}

				if (costs.containsKey(entity)) {
					int newCost = costs.get(entity) - effect.getEntities().get(entity);
					costs.put(entity, newCost);
				}
			} else if (costs.containsKey(singleEffect)) {
				int newCost = costs.get(singleEffect) - effect.getEntities().get(singleEffect);
				costs.put(singleEffect, newCost);
			}
		}

		for (Enum key : costs.keySet()) {
			int trades = searchCurrentTradesForCost(current, key);
			costs.put(key, costs.get(key) - trades);
		}
		for (Enum key : costs.keySet()) {
			validateEndCost(costs.get(key));
		}
	}

	public String chooseWhichEntity(HashMap<Enum, Integer> entities, String cardName) {
		return new DropDownMessage().dropDownEntitySelectionMessage(entities.keySet(), cardName);
	}

	private int searchCurrentTradesForCost(Player current, Enum key) {
		if (current.getCurrentTrades().containsKey(key)) {
			return current.getCurrentTrades().get(key);
		}
		return 0;
	}

	private int decrementCostFromEntity(Enum key, EntityEffect effect) {
		if (effect.getEntities().containsKey(key)) {
			return effect.getEntities().get(key);
		}
		return 0;
	}

	private void validateEndCost(int numcost) {
		if (numcost > 0) {
			throw new InsufficientFundsException(
					Translate.getNewResourceBundle().getString("noRequiredItemToBuildStructure"));
		}
	}

	public void discardSelectedCard(Player player, Card card) {
		board.addToDiscardPile(player, card);
		player.removeFromCurrentHand(card);
	}

	public void setGameBoard(GameBoard board) {
		this.board = board;
	}
}