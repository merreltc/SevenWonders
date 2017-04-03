package dataStructures;

import dataStructures.Card.Cost;
import dataStructures.Card.Effect;

public class Card {
	private String name = "Default Card";
	private int minNumPlayers = 3;
	private CardType type = CardType.DEFAULT;
	private Cost cost = Cost.NONE;
	private Effect effect = Effect.NONE;

	public enum CardType {
		DEFAULT, RAWMATERIAL, MANUFACTUREDGOOD
	}

	public enum Cost {
		NONE
	}

	public enum Effect {
		NONE, RESOURCE
	}

	public Card() {
	}

	public Card(String name, int minNumPlayers, CardType type, Cost cost, Effect effect) {
		this.name = name;
		this.minNumPlayers = minNumPlayers;
		this.type = type;
		this.cost = cost;
		this.effect = effect;
	}

	public String getName() {
		return this.name;
	}

	public int getMinNumPlayers() {
		return this.minNumPlayers;
	}

	public CardType getCardType() {
		return this.type;
	}

	public Cost getCostType() {
		return this.cost;
	}

	public Effect getEffectType() {
		return this.effect;
	}
}
