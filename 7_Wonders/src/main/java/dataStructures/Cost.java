package dataStructures;

import java.util.HashMap;

import dataStructures.Card.ResourceType;
import dataStructures.Cost.CostType;


public class Cost {
	private int coinCost = 0;
	private CostType type = CostType.NONE;
	private HashMap<ResourceType, Integer> resourceCost = new HashMap<ResourceType, Integer>();
	
	public enum CostType{
		NONE, COIN, RESOURCE
	}
	
	public enum ResourceType {
		LOOM, ORE, LUMBER
	}
	
	public Cost() {
	}
	
	public Cost(CostType type, HashMap<ResourceType, Integer> resourceCost) {
		this.type = type;
		this.resourceCost = resourceCost;
	}

	public Cost(CostType type, int coinCost) {
		this.type = type;
		this.coinCost = coinCost;
	}

	public CostType getType() {
		return this.type;
	}

	public HashMap<ResourceType, Integer> getResourceCost() {
		return this.resourceCost;
	}

	public int getCoinCost() {
		return this.coinCost;
	}

}
