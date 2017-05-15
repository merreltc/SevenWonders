package dataStructures;

import java.util.ArrayList;

import dataStructures.Card.CardType;

public class GuildEffect extends Effect{
	
	private CardType cardsToLookFor;

	public GuildEffect(EffectType effectType, CardType cardstoLookFor, Direction direction) {
		super(effectType);
		super.setDirection(direction);
	}

	public Object getObjectToLookFor() {
		return CardType.CIVILIANSTRUCTURE;
	}
	
	
}
