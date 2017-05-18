package main;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import constants.Constants;
import dataStructures.gameMaterials.Card;
import dataStructures.playerData.Player;
import gui.interactables.CardHolder;
import gui.interactables.Interactable;
import utils.RenderImage;

public class HandManager {

	private Player currentPlayer;
	private ArrayList<Interactable> playerHand = new ArrayList<>();

	public HandManager() {
	}

	public void draw(Graphics graphics) {
		for (Interactable card : this.playerHand) {
			card.draw(graphics);
		}
	}

	public void drawCurrentPlayerCards(Player player, RenderImage renderer) {
		this.playerHand.clear();
		this.currentPlayer = player;
		ArrayList<Card> currentHand = player.getCurrentHand();
		for (int i = 0; i < currentHand.size(); i++) {
			Point handPosition = new Point(Constants.PlayerHandLeftMostCardPositionX + Constants.CardOffset * i,
					Constants.PlayerHandLeftMostCardPositionY);
			Point handBounds = new Point(Constants.CardWidth, Constants.CardHeight);
			Interactable cardHolder = new CardHolder(handPosition, handBounds, i + "");
			Card card = currentHand.get(i);
			((CardHolder) cardHolder).giveCard(card);
			if (player.storagePileContainsCardByName(card.getPreviousStructureName())) {
				((CardHolder) cardHolder).setHasPreviousStructure();
			}
			this.playerHand.add(cardHolder);
		}
	}

	public int getPlayerHandSize() {
		return this.playerHand.size();
	}

	public Interactable getCardHolder(int cardToGet) {
		return this.playerHand.get(cardToGet);
	}

	public ArrayList<Interactable> getCurrentPlayerHand() {
		return this.playerHand;
	}
}
