package dataStructures;

import java.util.HashMap;

import dataStructures.Card.CardType;
import dataStructures.Card.Cost;
import dataStructures.Card.Effect;

public class Card {
	private String name = "Default Card";
	private int minNumPlayers = 3;
	
	private CardType type = CardType.DEFAULT;
	private Cost cost = Cost.NONE;
	private Effect effect = Effect.NONE;
	
	private String previousStructure = "None";
	private String nextStructure = "None";

	public enum CardType {
		DEFAULT, RAWMATERIAL, MANUFACTUREDGOOD, CIVILIANSTRUCTURE, 
		SCIENTIFICSTRUCTURE, COMMERCIALSTRUCTURE, MILITARYSTRUCTURE, GUILD
	}

	public enum Cost {
		NONE, RESOURCE, COIN
	}

	public enum ResourceType {
		LOOM, ORE, LUMBER
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

	public Card(String name, int minNumPlayers, CardType type, Cost cost, Effect effect, String previousStructure,
			String nextStructure) {
		this.name = name;
		this.minNumPlayers = minNumPlayers;
		this.type = type;
		this.cost = cost;
		this.effect = effect;
		this.previousStructure = previousStructure;
		this.nextStructure = nextStructure;
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
		if (this.cost == Cost.COIN) {
			return 1;
		}
		return 0;
	}

	public HashMap<ResourceType, Integer> getResourceCost() {
		if (this.cost == Cost.RESOURCE) {
			HashMap<ResourceType, Integer> resourceCost = new HashMap<ResourceType, Integer>();

			resourceCost.put(ResourceType.LOOM, 1);
			resourceCost.put(ResourceType.ORE, 1);
			resourceCost.put(ResourceType.LUMBER, 1);
			return resourceCost;
		}

		return new HashMap<ResourceType, Integer>();
	}

	public HashMap<ResourceType, Integer> getEffect() {
		HashMap<ResourceType, Integer> effect = new HashMap<ResourceType, Integer>();

		effect.put(ResourceType.LUMBER, 2);
		return effect;
	}

	public String getPreviousStructureName() {
		return this.previousStructure;
	}

	public String getNextStructureName() {
		return this.nextStructure;
	}
}
