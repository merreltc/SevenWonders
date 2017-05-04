package backend;

import java.util.ArrayList;
import java.util.HashMap;

import dataStructures.Card;
import dataStructures.Cost.CostType;
import dataStructures.Effect;
import dataStructures.Effect.EffectType;
import dataStructures.EntityEffect;
import dataStructures.Player;
import exceptions.InsufficientFundsException;

public class PlayerTurnHandler {

	public void buildStructure(Player current, Card card) {
		if (card.getCostType() == CostType.COIN){
			int coinCost = card.getCost().get(CostType.COIN);
			current.removeTotalCoins(coinCost);
		}else if (card.getCostType() == CostType.RESOURCE){
			HashMap<Enum, Integer> cost = card.getCost();
			ArrayList<Card> storage = current.getStoragePile();
			
			for(Enum key: cost.keySet()){
				boolean costfound = false;
				
				for(Card sCards: storage){
					if(sCards.getEffectType() == EffectType.ENTITY){
						EntityEffect effect = (EntityEffect) sCards.getEffect();
						
						if(effect.getEntities().containsKey(key)){
							costfound = true;
							break;
						}
					}
				}
				
				if(!costfound){
					throw new InsufficientFundsException("Player doesn't not have the required items to build the structure");
				}
			}
		}
		
		current.addToStoragePile(card);
		current.removeFromCurrentHand(card);
	}

}
