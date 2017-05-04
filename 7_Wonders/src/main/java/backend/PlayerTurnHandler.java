package backend;

import dataStructures.Card;
import dataStructures.Player;

public class PlayerTurnHandler {

	public void buildStructure(Player current, Card card) {
		current.addToStoragePile(card);
		current.removeFromCurrentHand(card);
	}

}
