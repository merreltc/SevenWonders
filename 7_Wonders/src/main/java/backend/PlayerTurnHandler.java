package backend;

import java.util.ArrayList;
import java.util.HashMap;

import dataStructures.Card;
import dataStructures.Cost.CostType;
import dataStructures.Effect;
import dataStructures.Effect.EffectType;
import dataStructures.EntityEffect;
import dataStructures.GameBoard;
import dataStructures.Player;
import dataStructures.ValueEffect;
import exceptions.InsufficientFundsException;

public class PlayerTurnHandler {

	public void buildStructure(Player current, Card card) {
		if (card.getCostType() == CostType.COIN){
			int coinCost = card.getCost().get(CostType.COIN);
			current.removeTotalCoins(coinCost);
		}else if (card.getCostType() != CostType.NONE){
			validatePlayerHasEntitiesForCard(current, card);
		}
		
		if(card.getEffectType() == EffectType.VALUE){
			ValueEffect effect = (ValueEffect) card.getEffect();
			
			switch (effect.getValue()){
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
		
		for(Enum key: cost.keySet()){
			boolean costfound = false;
			int numcost = cost.get(key);
			
			for(Card sCards: storage){
				if(sCards.getEffectType() == EffectType.ENTITY){
					EntityEffect effect = (EntityEffect) sCards.getEffect();
					
					if(effect.getEntities().containsKey(key)){
						costfound = true;
						numcost -= effect.getEntities().get(key);
						break;
					}
				}
			}
			
			if(!costfound || numcost > 0){
				throw new InsufficientFundsException("Player doesn't not have the required items to build the structure");
			}
		}
	}

	public void discardSelectedCard(Player player, Card card, GameBoard board) {
		board.addToDiscardPile(player, card);
		player.removeFromCurrentHand(card);
	}

}
