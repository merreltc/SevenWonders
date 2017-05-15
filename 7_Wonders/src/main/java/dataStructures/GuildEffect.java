package dataStructures;

import java.util.ArrayList;

import dataStructures.Card.CardType;

public class GuildEffect extends Effect{
	
	private CardType cardsToLookFor;

	public GuildEffect(EffectType effectType, CardType cardsToLookFor, Direction direction) {
		super(effectType);
		super.setDirection(direction);
		this.cardsToLookFor = cardsToLookFor;
		
	}

	public Object getObjectToLookFor() {
		return this.cardsToLookFor;
	}
	
	
}
