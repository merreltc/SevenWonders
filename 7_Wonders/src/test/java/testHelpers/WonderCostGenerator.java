package testHelpers;

import java.util.HashMap;

import constants.GeneralEnums.CostType;
import constants.GeneralEnums.Good;
import constants.GeneralEnums.RawResource;
import dataStructures.gameMaterials.Cost;
import dataStructures.gameMaterials.Wonder;

public class WonderCostGenerator {
	Wonder wonder;

	public WonderCostGenerator(Wonder wonder) {
		this.wonder = wonder;
	}

	Cost getExpectedCost(int priority) {
		CostType type;
		HashMap<Enum, Integer> costs;

		switch (this.wonder.getType()) {
		case COLOSSUS:
			type = getColossusCostType(priority);
			costs = getColossusCosts(priority);
			break;
		case LIGHTHOUSE:
			type = getLighthouseCostType(priority);
			costs = getLighthouseCosts(priority);
			break;
		case TEMPLE:
			type = getTempleCostType(priority);
			costs = getTempleCosts(priority);
			break;
		case GARDENS:
			type = getGardensCostType(priority);
			costs = getGardensCosts(priority);
			break;
		case STATUE:
			type = getStatueCostType(priority);
			costs = getStatueCosts(priority);
			break;
		case MAUSOLEUM:
			type = getMausoleumCostType(priority);
			costs = getMausoleumCosts(priority);
			break;
		case PYRAMIDS:
			type = getPyramidsCostType(priority);
			costs = getPyramidsCosts(priority);
			break;
		default:
			throw new IllegalArgumentException("Invalid Wonder Type");
		}

		return new Cost(type, costs);
	}

	private HashMap<Enum, Integer> getMausoleumCosts(int priority) {
		HashMap<Enum, Integer> costs = new HashMap<Enum, Integer>();

		switch (this.wonder.getSide()) {
		case A:
			switch (priority) {
			case 1:
				costs.put(RawResource.CLAY, 2);
				break;
			case 2:
				costs.put(RawResource.ORE, 3);
				break;
			case 3:
				costs.put(Good.LOOM, 2);
				break;
			default:
				throw new IllegalArgumentException("Invalid priority");
			}
			break;
		case B:
			switch (priority) {
			case 1:
				costs.put(RawResource.ORE, 2);
				break;
			case 2:
				costs.put(RawResource.CLAY, 3);
				break;
			case 3:
				costs.put(Good.PRESS, 1);
				costs.put(Good.GLASS, 1);
				costs.put(Good.LOOM, 1);
				break;
			default:
				throw new IllegalArgumentException("Invalid priority");
			}
			break;
		default:
			throw new IllegalArgumentException("Invalid Side");
		}

		return costs;
	}

	private HashMap<Enum, Integer> getColossusCosts(int priority) {
		HashMap<Enum, Integer> costs = new HashMap<Enum, Integer>();

		switch (this.wonder.getSide()) {
		case A:
			switch (priority) {
			case 1:
				costs.put(RawResource.LUMBER, 2);
				break;
			case 2:
				costs.put(RawResource.CLAY, 3);
				break;
			case 3:
				costs.put(RawResource.ORE, 4);
				break;
			default:
				throw new IllegalArgumentException("Invalid priority");
			}
			break;
		case B:
			switch (priority) {
			case 1:
				costs.put(RawResource.STONE, 3);
				break;
			case 2:
				costs.put(RawResource.ORE, 4);
				break;
			default:
				throw new IllegalArgumentException("Invalid priority");
			}
			break;
		default:
			throw new IllegalArgumentException("Invalid Side");
		}

		return costs;
	}

	private HashMap<Enum, Integer> getLighthouseCosts(int priority) {
		HashMap<Enum, Integer> costs = new HashMap<Enum, Integer>();

		switch (this.wonder.getSide()) {
		case A:
			switch (priority) {
			case 1:
				costs.put(RawResource.STONE, 2);
				break;
			case 2:
				costs.put(RawResource.ORE, 2);
				break;
			case 3:
				costs.put(Good.GLASS, 2);
				break;
			default:
				throw new IllegalArgumentException("Invalid priority");
			}
			break;
		case B:
			switch (priority) {
			case 1:
				costs.put(RawResource.CLAY, 2);
				break;
			case 2:
				costs.put(RawResource.LUMBER, 2);
				break;
			case 3:
				costs.put(RawResource.STONE, 3);
				break;
			default:
				throw new IllegalArgumentException("Invalid priority");
			}
			break;
		default:
			throw new IllegalArgumentException("Invalid Side");
		}

		return costs;
	}

	private HashMap<Enum, Integer> getTempleCosts(int priority) {
		HashMap<Enum, Integer> costs = new HashMap<Enum, Integer>();

		switch (this.wonder.getSide()) {
		case A:
			switch (priority) {
			case 1:
				costs.put(RawResource.STONE, 2);
				break;
			case 2:
				costs.put(RawResource.LUMBER, 2);
				break;
			case 3:
				costs.put(Good.PRESS, 2);
				break;
			default:
				throw new IllegalArgumentException("Invalid priority");
			}
			break;
		case B:
			switch (priority) {
			case 1:
				costs.put(RawResource.STONE, 2);
				break;
			case 2:
				costs.put(RawResource.LUMBER, 2);
				break;
			case 3:
				costs.put(Good.PRESS, 1);
				costs.put(Good.LOOM, 1);
				costs.put(Good.GLASS, 1);
				break;
			default:
				throw new IllegalArgumentException("Invalid priority");
			}
			break;
		default:
			throw new IllegalArgumentException("Invalid Side");
		}

		return costs;
	}

	private HashMap<Enum, Integer> getGardensCosts(int priority) {
		HashMap<Enum, Integer> costs = new HashMap<Enum, Integer>();

		switch (this.wonder.getSide()) {
		case A:
			switch (priority) {
			case 1:
				costs.put(RawResource.CLAY, 2);
				break;
			case 2:
				costs.put(RawResource.LUMBER, 3);
				break;
			case 3:
				costs.put(RawResource.CLAY, 4);
				break;
			default:
				throw new IllegalArgumentException("Invalid priority");
			}
			break;
		case B:
			switch (priority) {
			case 1:
				costs.put(Good.LOOM, 1);
				costs.put(RawResource.CLAY, 1);
				break;
			case 2:
				costs.put(Good.GLASS, 1);
				costs.put(RawResource.LUMBER, 2);
				break;
			case 3:
				costs.put(Good.PRESS, 1);
				costs.put(RawResource.CLAY, 3);
				break;
			default:
				throw new IllegalArgumentException("Invalid priority");
			}
			break;
		default:
			throw new IllegalArgumentException("Invalid Side");
		}

		return costs;
	}

	private HashMap<Enum, Integer> getStatueCosts(int priority) {
		HashMap<Enum, Integer> costs = new HashMap<Enum, Integer>();

		switch (this.wonder.getSide()) {
		case A:
			switch (priority) {
			case 1:
				costs.put(RawResource.LUMBER, 2);
				break;
			case 2:
				costs.put(RawResource.STONE, 2);
				break;
			case 3:
				costs.put(RawResource.ORE, 2);
				break;
			default:
				throw new IllegalArgumentException("Invalid priority");
			}
			break;
		case B:
			switch (priority) {
			case 1:
				costs.put(RawResource.LUMBER, 2);
				break;
			case 2:
				costs.put(RawResource.STONE, 2);
				break;
			case 3:
				costs.put(Good.LOOM, 1);
				costs.put(RawResource.ORE, 2);
				break;
			default:
				throw new IllegalArgumentException("Invalid priority");
			}
			break;
		default:
			throw new IllegalArgumentException("Invalid Side");
		}

		return costs;
	}

	private HashMap<Enum, Integer> getPyramidsCosts(int priority) {
		HashMap<Enum, Integer> costs = new HashMap<Enum, Integer>();

		switch (this.wonder.getSide()) {
		case A:
			switch (priority) {
			case 1:
				costs.put(RawResource.STONE, 2);
				break;
			case 2:
				costs.put(RawResource.LUMBER, 3);
				break;
			case 3:
				costs.put(RawResource.STONE, 4);
				break;
			default:
				throw new IllegalArgumentException("Invalid priority");
			}
			break;
		case B:
			switch (priority) {
			case 1:
				costs.put(RawResource.LUMBER, 2);
				break;
			case 2:
				costs.put(RawResource.STONE, 2);
				break;
			case 3:
				costs.put(RawResource.CLAY, 2);
				break;
			case 4:
				costs.put(Good.PRESS, 1);
				costs.put(RawResource.STONE, 4);
				break;
			default:
				throw new IllegalArgumentException("Invalid priority");
			}
			break;
		default:
			throw new IllegalArgumentException("Invalid Side");
		}

		return costs;
	}

	private CostType getPyramidsCostType(int priority) {
		switch (this.wonder.getSide()) {
		case A:
			switch (priority) {
			case 1:
				return CostType.RESOURCE;
			case 2:
				return CostType.RESOURCE;
			case 3:
				return CostType.RESOURCE;
			default:
				throw new IllegalArgumentException("Invalid priority");
			}
		case B:
			switch (priority) {
			case 1:
				return CostType.RESOURCE;
			case 2:
				return CostType.RESOURCE;
			case 3:
				return CostType.RESOURCE;
			case 4:
				return CostType.MULTITYPE;
			default:
				throw new IllegalArgumentException("Invalid priority");
			}
		default:
			throw new IllegalArgumentException("Invalid Side");
		}
	}

	private CostType getMausoleumCostType(int priority) {
		switch (this.wonder.getSide()) {
		case A:
			switch (priority) {
			case 1:
				return CostType.RESOURCE;
			case 2:
				return CostType.RESOURCE;
			case 3:
				return CostType.GOOD;
			default:
				throw new IllegalArgumentException("Invalid priority");
			}
		case B:
			switch (priority) {
			case 1:
				return CostType.RESOURCE;
			case 2:
				return CostType.RESOURCE;
			case 3:
				return CostType.GOOD;
			default:
				throw new IllegalArgumentException("Invalid priority");
			}
		default:
			throw new IllegalArgumentException("Invalid Side");
		}
	}

	private CostType getStatueCostType(int priority) {
		switch (this.wonder.getSide()) {
		case A:
			switch (priority) {
			case 1:
				return CostType.RESOURCE;
			case 2:
				return CostType.RESOURCE;
			case 3:
				return CostType.RESOURCE;
			default:
				throw new IllegalArgumentException("Invalid priority");
			}
		case B:
			switch (priority) {
			case 1:
				return CostType.RESOURCE;
			case 2:
				return CostType.RESOURCE;
			case 3:
				return CostType.MULTITYPE;
			default:
				throw new IllegalArgumentException("Invalid priority");
			}
		default:
			throw new IllegalArgumentException("Invalid Side");
		}
	}

	private CostType getGardensCostType(int priority) {
		switch (this.wonder.getSide()) {
		case A:
			switch (priority) {
			case 1:
				return CostType.RESOURCE;
			case 2:
				return CostType.RESOURCE;
			case 3:
				return CostType.RESOURCE;
			default:
				throw new IllegalArgumentException("Invalid priority");
			}
		case B:
			switch (priority) {
			case 1:
				return CostType.MULTITYPE;
			case 2:
				return CostType.MULTITYPE;
			case 3:
				return CostType.MULTITYPE;
			default:
				throw new IllegalArgumentException("Invalid priority");
			}
		default:
			throw new IllegalArgumentException("Invalid Side");
		}
	}

	private CostType getTempleCostType(int priority) {
		switch (this.wonder.getSide()) {
		case A:
			switch (priority) {
			case 1:
				return CostType.RESOURCE;
			case 2:
				return CostType.RESOURCE;
			case 3:
				return CostType.GOOD;
			default:
				throw new IllegalArgumentException("Invalid priority");
			}
		case B:
			switch (priority) {
			case 1:
				return CostType.RESOURCE;
			case 2:
				return CostType.RESOURCE;
			case 3:
				return CostType.GOOD;
			default:
				throw new IllegalArgumentException("Invalid priority");
			}
		default:
			throw new IllegalArgumentException("Invalid Side");
		}
	}

	private CostType getLighthouseCostType(int priority) {
		switch (this.wonder.getSide()) {
		case A:
			switch (priority) {
			case 1:
				return CostType.RESOURCE;
			case 2:
				return CostType.RESOURCE;
			case 3:
				return CostType.GOOD;
			default:
				throw new IllegalArgumentException("Invalid priority");
			}
		case B:
			switch (priority) {
			case 1:
				return CostType.RESOURCE;
			case 2:
				return CostType.RESOURCE;
			case 3:
				return CostType.RESOURCE;
			default:
				throw new IllegalArgumentException("Invalid priority");
			}
		default:
			throw new IllegalArgumentException("Invalid Side");
		}
	}

	private CostType getColossusCostType(int priority) {
		switch (this.wonder.getSide()) {
		case A:
			switch (priority) {
			case 1:
				return CostType.RESOURCE;
			case 2:
				return CostType.RESOURCE;
			case 3:
				return CostType.RESOURCE;
			default:
				throw new IllegalArgumentException("Invalid priority");
			}
		case B:
			switch (priority) {
			case 1:
				return CostType.RESOURCE;
			case 2:
				return CostType.RESOURCE;
			default:
				throw new IllegalArgumentException("Invalid priority");
			}
		default:
			throw new IllegalArgumentException("Invalid Side");
		}
	}
}
