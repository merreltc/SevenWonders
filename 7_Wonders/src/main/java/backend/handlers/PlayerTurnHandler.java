package backend.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import constants.GeneralEnums.CostType;
import dataStructures.GameBoard;
import dataStructures.gameMaterials.Card;
import dataStructures.gameMaterials.Effect.EffectType;
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
		}

		String previousStructure = card.getPreviousStructureName();

		for (Card storage : current.getAllCards()) {
			if (storage.getName().contains(previousStructure)) {
				current.addCardToStoragePile(card);
				current.removeFromCurrentHand(card);
				return;
			}
		}

		if (card.getCostType() == CostType.COIN) {
			int coinCost = card.getCost().get(CostType.COIN);
			PlayerChipHandler.removeTotalCoins(current, coinCost);
		} else if (card.getCostType() != CostType.NONE) {
			validatePlayerHasEntitiesForCard(current, card);
		}

		if (card.getEffectType() == EffectType.VALUE) {
			ValueEffect effect = (ValueEffect) card.getEffect();

			switch (effect.getValueType()) {
			case VICTORYPOINT:
				current.addNumVictoryPoints(effect.getValueAmount());
				break;
			case COIN:
				if(card.getName().equals("Tavern")){
					board.giveNumCoins(current, effect.getValueAmount());
				}
				break;
			default:
				current.addNumShields(effect.getValueAmount());
				break;
			}
		}

		current.addCardToStoragePile(card);
		current.removeFromCurrentHand(card);
	}

	private void validatePlayerHasEntitiesForCard(Player current, Card card) {
		HashMap<Enum, Integer> cost = card.getCost();
		ArrayList<Card> storage = current.getAllCards();
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
					}
				}
			}
			
			if(numcost > 0){
				if(current.getCurrentTrades().containsKey(key)){
					costfound = true;
					numcost -= current.getCurrentTrades().get(key);
				}
			}

			if (!costfound || numcost > 0) {
				throw new InsufficientFundsException(this.messages.getString("noRequiredItemToBuildStructure"));
			}
		}
	}

	public void discardSelectedCard(Player player, Card card, GameBoard board) {
		board.addToDiscardPile(player, card);
		player.removeFromCurrentHand(card);
	}
}