package dataStructures.gameMaterials;

import java.util.HashMap;

import constants.GeneralEnums.CostType;

public class Cost {
	private CostType type = CostType.NONE;
	private HashMap<Enum, Integer> cost = new HashMap<Enum, Integer>();
	
	public Cost(CostType type, HashMap<Enum, Integer> cost) {
		this.type = type;
		this.cost = cost;
	}

	public Cost(CostType type, int coinCost) {
		this.type = type;
		this.cost.put(CostType.COIN, coinCost);
	}

	public CostType getType() {
		return this.type;
	}

	public HashMap<Enum, Integer> getCost() {
		return this.cost;
	}
}
