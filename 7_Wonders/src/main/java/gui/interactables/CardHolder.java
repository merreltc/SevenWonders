package gui.interactables;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import backend.GameManager;
import dataStructures.gameMaterials.Card;

public class CardHolder extends Interactable {

	private Card card;
	private boolean havePreviousStructure;

	public CardHolder(Point positionPoint, Point boundPoint, String value) {
		super(positionPoint, boundPoint, value, Color.BLUE, Color.YELLOW);
	}

	public void giveCard(Card card) {
		super.addImage(renderer.getImage(card.getName()));
		this.card = card;
	}

	@Override
	public void draw(Graphics graphics) {
		super.draw(graphics);
		if (this.havePreviousStructure) {
			this.renderer.getImage("checkmark");
			this.renderer.draw(graphics, new int[]{(int) this.getPosition().getX(), (int) this.getPosition().getY(), 50, 50});
			//graphics.fillRect((int) this.getPosition().getX(), (int) this.getPosition().getY(), 50, 50);
		}
	}

	public void activate(GameManager gameManager) {
		gameManager.buildStructure(card);

	}

	public void discard(GameManager gameManager) {
		gameManager.discardSelectedCard(this.card);
	}

	public void setHasPreviousStructure() {
		this.havePreviousStructure = true;
	}
	
	public void buildWonder(GameManager manager){
		manager.buildWonder();
	}
}
