package dataStructures;

import java.util.ArrayList;
import java.util.HashMap;

import dataStructures.Card.CardType;
import dataStructures.Cost.CostType;
import dataStructures.Effect.EffectType;

public class Card {
	private String name = "Default Card";
	private ArrayList<Integer> frequencyByNumPlayers;
	private static final int GUILD_NO_FREQUENCY = -1;

	private CardType type = CardType.DEFAULT;
	private Cost cost;
	private Effect effect;

	private String previousStructure = "None";
	private String nextStructure = "None";

	public enum CardType {
		DEFAULT, RAWMATERIAL, MANUFACTUREDGOOD, CIVILIANSTRUCTURE, SCIENTIFICSTRUCTURE, COMMERCIALSTRUCTURE, MILITARYSTRUCTURE, GUILD
	}

	public Card(String name, ArrayList<Integer> frequencyByNumPlayers, CardType type, Cost cost, Effect effect,
			String previousStructure, String nextStructure) {
		this.name = name;
		this.frequencyByNumPlayers = frequencyByNumPlayers;
		this.type = type;
		this.cost = cost;
		this.effect = effect;
		this.previousStructure = previousStructure;
		this.nextStructure = nextStructure;
	}

	public Card(String name, CardType type, Cost cost, Effect effect) {
		this.name = name;
		this.frequencyByNumPlayers = new ArrayList<Integer>();
		this.frequencyByNumPlayers.add(GUILD_NO_FREQUENCY);
		this.type = type;
		this.cost = cost;
		this.effect = effect;
	}

	@Override
	public boolean equals(Object obj) {
		Card card = (Card) obj;

		if (this.frequencyByNumPlayers.toString().equals(card.frequencyByNumPlayers.toString())
				&& this.cost.equals(card.cost) && this.effect.equals(card.effect) && this.name.equals(card.name)) {
			return true;
		}

		return false;
	}

	public String toString() {
		String value = "name: " + this.name + System.lineSeparator() + "minFrequencyByNumPlayers: "
				+ this.frequencyByNumPlayers.get(0) + System.lineSeparator() + "costType: "
				+ this.getCostType().toString() + System.lineSeparator() + "effectType: "
				+ this.getEffectType().toString();
		return value;
	}

	public String getName() {
		return this.name;
	}

	public ArrayList<Integer> getFrequencyByNumPlayers() {
		return this.frequencyByNumPlayers;
	}

	public CardType getCardType() {
		return this.type;
	}

	public CostType getCostType() {
		return this.cost.getType();
	}

	public EffectType getEffectType() {
		return this.effect.getEffectType();
	}

	public HashMap<Enum, Integer> getCost() {
		return this.cost.getCost();
	}

	public Effect getEffect() {
		return this.effect;
	}

	public String getPreviousStructureName() {
		return this.previousStructure;
	}

	public String getNextStructureName() {
		return this.nextStructure;
	}
}
