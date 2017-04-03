package dataStructures;

import java.util.HashMap;

public class Cost {
	public enum CostType{
		NONE
	}
	
	public enum ResourceType {
		LOOM, ORE, LUMBER
	}

	public CostType getType() {
		return CostType.NONE;
	}

	public HashMap<ResourceType, Integer> getResourceCost() {
		return new HashMap<ResourceType, Integer>();
	}

	public int getCoinCost() {
		return 0;
	}

}
