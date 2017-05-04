package backend;

import dataStructures.Card;
import dataStructures.Cost.CostType;
import dataStructures.Player;

public class PlayerTurnHandler {

	public void buildStructure(Player current, Card card) {
		if (card.getCostType() == CostType.COIN){
			int coinCost = card.getCost().get(CostType.COIN);
			current.removeTotalCoins(coinCost);
		}
		
		current.addToStoragePile(card);
		current.removeFromCurrentHand(card);
	}

}
