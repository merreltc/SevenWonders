package guiMain;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import GuiDataStructures.Constants;
import dataStructures.Player;
import guiMain.Interactables.CardHolder;
import guiMain.Interactables.Interactable;

public class HandManager {

	private Player currentPlayer;
	private ArrayList<Interactable> playerHand = new ArrayList<>();

	public HandManager() {
	
	}

	public void draw(Graphics graphics){
		for (Interactable card : playerHand){
			card.draw(graphics);
		}
	}
	
	public void rotatePlayers(Player nextPlayer) {
		this.currentPlayer = nextPlayer;
		playerHand.clear();
		// TODO: change 5 to the number of cards in the player's hand
		for (int i = 0; i < 7; i++) {
			Point handPosition = new Point(Constants.PlayerHandLeftMostCardPositionX + Constants.CardOffset * i,
					Constants.PlayerHandLeftMostCardPositionY);
			Point handBounds = new Point(Constants.CardWidth, Constants.CardWidth);
			Interactable card = new CardHolder(handPosition, handBounds, i + "");
			playerHand.add(card);
		}
	}

	public int getPlayerHandSize(){
		return this.playerHand.size();
	}

	public Interactable getCardHolder(int cardToGet) {
		return this.playerHand.get(cardToGet);
	}
}
