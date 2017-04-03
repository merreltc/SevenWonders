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
		DEFAULT, RAWMATERIAL, MANUFACTUREDGOOD, CIVILIANSTRUCTURE, 
		SCIENTIFICSTRUCTURE, COMMERCIALSTRUCTURE, MILITARYSTRUCTURE, GUILD
	}

	public enum Cost {
		NONE, RESOURCE, COIN
	}

	public enum Effect {
		NONE, RESOURCE, MULTI, SCIENCE, COMMERCIAL, VICTORYPOINTS, CONFLICTTOKENS
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

	public int getCoinCost() {
		if(this.cost == Cost.COIN) {
			return 1;
		}
		return 0;
	}
}
