package backend.handlers;

import java.util.ArrayList;
import java.util.HashMap;

import constants.GeneralEnums.CostType;
import dataStructures.GameBoard;
import dataStructures.gameMaterials.Card;
import dataStructures.gameMaterials.Effect.EffectType;
import dataStructures.gameMaterials.EntityEffect;
import dataStructures.gameMaterials.ValueEffect;
import dataStructures.playerData.Player;
import exceptions.InsufficientFundsException;

public class PlayerTurnHandler {

	public void buildStructure(Player current, Card card) {
		String previousStructure = card.getPreviousStructureName();
		
		for(Card storage : current.getStoragePile()){
			if(storage.getName().contains(previousStructure)){
				current.addToStoragePile(card);
				current.removeFromCurrentHand(card);
				return;
			}
		}
		
		if (card.getCostType() == CostType.COIN) {
			int coinCost = card.getCost().get(CostType.COIN);
			current.removeTotalCoins(coinCost);
		} else if (card.getCostType() != CostType.NONE) {
			validatePlayerHasEntitiesForCard(current, card);
		}

		if (card.getEffectType() == EffectType.VALUE) {
			ValueEffect effect = (ValueEffect) card.getEffect();

			switch (effect.getValueType()) {
			case VICTORYPOINT:
				current.addNumVictoryPoints(effect.getValueAmount());
				break;
			default:
				current.addNumShields(effect.getValueAmount());
				break;
			}
		}

		current.addToStoragePile(card);
		current.removeFromCurrentHand(card);
	}

	private void validatePlayerHasEntitiesForCard(Player current, Card card) {
		HashMap<Enum, Integer> cost = card.getCost();
		ArrayList<Card> storage = current.getStoragePile();
		ArrayList<Card> usedEntities = new ArrayList<Card>();

		for (Enum key : cost.keySet()) {
			boolean costfound = false;
			int numcost = cost.get(key);

			for (Card sCards : storage) {
				if (usedEntities.contains(sCards)) {
					continue;
				}

				if (sCards.getEffectType() == EffectType.ENTITY) {
					EntityEffect effect = (EntityEffect) sCards.getEffect();

					if (effect.getEntities().containsKey(key)) {
						costfound = true;
						numcost -= effect.getEntities().get(key);
						usedEntities.add(sCards);

						if (numcost <= 0) {
							break;
						}
					}
				}
			}

			if (!costfound || numcost > 0) {
				throw new InsufficientFundsException(
						"Player doesn't not have the required items to build the structure");
			}
		}
	}

	public void discardSelectedCard(Player player, Card card, GameBoard board) {
		board.addToDiscardPile(player, card);
		player.removeFromCurrentHand(card);
	}

}
