package dataStructures;

import java.util.HashMap;

public class Cost {
	private CostType type = CostType.NONE;
	private HashMap<Enum, Integer> cost = new HashMap<Enum, Integer>();

	public enum CostType {
		NONE, COIN, RESOURCE, GOOD, MULTITYPE
	}

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
