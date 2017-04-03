package dataStructures;

import dataStructures.Card.Cost;
import dataStructures.Card.Effect;

public class Card {
	private String name = "Default Card";
	private int minNumPlayers = 3;
	private Cost cost = Cost.NONE;
	private Effect effect = Effect.NONE;

	public enum Cost {
		NONE
	}

	public enum Effect {
		NONE, RESOURCE
	}

	public Card() {
	}

	public Card(String name, int minNumPlayers, Cost cost, Effect effect) {
		this.name = name;
		this.minNumPlayers = minNumPlayers;
		this.cost = cost;
		this.effect = effect;
	}

	public String getName() {
		return this.name;
	}

	public int getMinNumPlayers() {
		return this.minNumPlayers;
	}

	public Cost getCostType() {
		return this.cost;
	}

	public Effect getEffectType() {
		return this.effect;
	}

}
