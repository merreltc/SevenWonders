package dataStructures;

import java.util.HashMap;

import dataStructures.Cost.CostType;

public class Level {
	private int priority;
	private Cost cost;

	public Level(int priority, Cost cost, Effect effect) {
		this.priority = priority;
		this.cost = cost;
	}

	public int getPriority() {
		return this.priority;
	}
	
	public CostType getCostType() {
		return this.cost.getType();
	}

	public HashMap<Enum, Integer> getCost() {
		return this.cost.getCost();
	}
}
