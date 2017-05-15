package backend;

import java.util.ArrayList;

import dataStructures.Card;
import dataStructures.Chip.ChipType;
import dataStructures.Deck;
import dataStructures.Deck.Age;
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
	
	public void endAge(ArrayList<Player> players, Age age1) {
		for (int i = 0; i < players.size(); i++){
			Player player1 = players.get(i);
			Player player2 = players.get((i+1)%players.size());
			if (player1.getNumShields() > player2.getNumShields()){
				player1.addValue1(1, ChipType.CONFLICTTOKEN);
				player2.addValueNeg1(1, ChipType.CONFLICTTOKEN);
			}else if (player1.getNumShields() < player2.getNumShields()){
				player2.addValue1(1, ChipType.CONFLICTTOKEN);
				player1.addValueNeg1(1, ChipType.CONFLICTTOKEN);
			}
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
