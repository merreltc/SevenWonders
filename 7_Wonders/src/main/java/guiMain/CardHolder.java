package guiMain;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import dataStructures.Card;

public class CardHolder extends Interactable{

	private Card card;
	
	public CardHolder(Point positionPoint, Point boundPoint, String value) {
		super(positionPoint, boundPoint, value);
		
	}
	
	public void giveCard(Card card){
		this.card = card;
	}

	@Override
	public void draw(Graphics graphics) {
		super.draw(graphics, Color.BLUE, Color.YELLOW);
	}
	
	public void activate(){
		/*TODO: Change to play card*/
		this.card.getEffect();
	}

}
