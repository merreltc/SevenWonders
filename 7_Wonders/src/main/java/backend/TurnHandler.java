package backend;

import java.util.ArrayList;

import dataStructures.Card;
import dataStructures.Deck;
import dataStructures.Player;

public class TurnHandler {
	private int numPlayersUntilPass;
	private int numTurnsTilEndOfAge = 5;

	public void dealInitialTurnCards(ArrayList<Player> players, int numPlayers, Deck deck) {
		this.numPlayersUntilPass = players.size() - 1;
		this.numTurnsTilEndOfAge = 5;
		
		for (Player player: players) {
			ArrayList<Card> currentHand = new ArrayList<Card>();
			
			for(int i = 0; i < 7; i++){
				Card toAdd = DeckHandler.drawCard(deck);
				currentHand.add(toAdd);
			}
			
			player.setCurrentHand(currentHand);
		}
	}

	public int getNumPlayersUntilPass() {
		return this.numPlayersUntilPass;
	}

	public void setNumPlayersUntilPass(int num) {
		this.numPlayersUntilPass = num;
	}

	public int getNumTurnsTilEndOfAge() {
		return this.numTurnsTilEndOfAge;
	}

	public void setNumTurnsTilEndOfAge(int num) {
		this.numTurnsTilEndOfAge = num;
	}
}
