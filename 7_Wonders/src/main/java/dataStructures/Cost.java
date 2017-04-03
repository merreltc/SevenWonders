package dataStructures;

import java.util.HashMap;


public class Cost {
	private int coinCost = 0;
	private CostType type = CostType.NONE;
	
	public enum CostType{
		NONE, COIN
	}
	
	public enum ResourceType {
		LOOM, ORE, LUMBER
	}
	
	public Cost() {
	}

	public Cost(CostType type, int coinCost) {
		this.type = type;
		this.coinCost = coinCost;
	}

	public CostType getType() {
		return this.type;
	}

	public HashMap<ResourceType, Integer> getResourceCost() {
		return new HashMap<ResourceType, Integer>();
	}

	public int getCoinCost() {
		return this.coinCost;
	}

}
