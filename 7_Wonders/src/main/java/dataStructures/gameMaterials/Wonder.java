package dataStructures.gameMaterials;

import java.util.ArrayList;

import constants.GeneralEnums.CostType;
import constants.GeneralEnums.Resource;
import constants.GeneralEnums.Side;
import dataStructures.gameMaterials.Effect.EffectType;
import dataStructures.gameMaterials.Level.Frequency;
import exceptions.CannotBuiltWonderException;

public class Wonder {
	private WonderType type;
	private String name;
	private Resource resource;
	private Side side;
	private int numBuiltLevels = 0;
	private ArrayList<Level> levels;

	public enum WonderType {
		COLOSSUS, LIGHTHOUSE, TEMPLE, GARDENS, STATUE, MAUSOLEUM, PYRAMIDS
	}

	public Wonder(Side side, WonderType type) {
		this.type = type;
		this.name = getNameByType(type);
		this.resource = getResourceByType(type);
		this.side = side;
		this.levels = new ArrayList<Level>();
	}

	public void buildNextLevel() {
		this.numBuiltLevels++;
		if (this.numBuiltLevels > this.getNumLevels()) {
			throw new CannotBuiltWonderException("Player has built max number of levels.");
		} else {
			Level temp = new Level(this.numBuiltLevels, new Cost(CostType.NONE, 1), new Effect(EffectType.NONE),
					Frequency.DEFAULT);
			this.levels.add(temp);
		}
	}

	public WonderType getType() {
		return this.type;
	}

	public String getName() {
		return this.name;
	}

	public Resource getResource() {
		return this.resource;
	}

	public Side getSide() {
		return this.side;
	}

	public int getNumLevels() {
		return this.getNumLevels(this.side, this.type);
	}

	public int getNumLevels(Side side, WonderType type) {
		if (side == Side.A) {
			return 3;
		} else {
			switch (type) {
			case COLOSSUS:
				return 2;
			case LIGHTHOUSE:
			case TEMPLE:
			case GARDENS:
			case STATUE:
			case MAUSOLEUM:
				return 3;
			case PYRAMIDS:
				return 4;
			default:
				throw new IllegalArgumentException("Bad Wonder Type");
			}
		}
	}

	public static String getNameByType(WonderType wonder) {
		switch (wonder) {
		case COLOSSUS:
			return "The Colossus of Rhodes";
		case LIGHTHOUSE:
			return "The Lighthouse of Alexandria";
		case TEMPLE:
			return "The Temple of Artemis in Ephesus";
		case GARDENS:
			return "The Hanging Gardens of Babylon";
		case STATUE:
			return "The Statue of Zeus in Olympia";
		case MAUSOLEUM:
			return "The Mausoleum of Halicarnassus";
		case PYRAMIDS:
			return "The Pyramids of Giza";
		default:
			throw new IllegalArgumentException("Bad Wonder Type");
		}
	}

	public static String getShortNameByType(WonderType wonder) {
		switch (wonder) {
		case COLOSSUS:
			return "Colossus";
		case LIGHTHOUSE:
			return "Lighthouse";
		case TEMPLE:
			return "Temple of Artemis";
		case GARDENS:
			return "Hanging Gardens";
		case STATUE:
			return "Statue of Zeus";
		case MAUSOLEUM:
			return "Mausoleum";
		case PYRAMIDS:
			return "Pyramids";
		default:
			throw new IllegalArgumentException("Bad Wonder Type");
		}
	}

	public static Resource getResourceByType(WonderType type) {
		switch (type) {
		case COLOSSUS:
			return Resource.ORE;
		case LIGHTHOUSE:
			return Resource.GLASS;
		case TEMPLE:
			return Resource.PRESS;
		case GARDENS:
			return Resource.CLAY;
		case STATUE:
			return Resource.LUMBER;
		case MAUSOLEUM:
			return Resource.LOOM;
		case PYRAMIDS:
			return Resource.STONE;
		default:
			throw new IllegalArgumentException("Bad Wonder Type");
		}
	}

	public int getNumBuiltLevels() {
		return this.levels.size();
	}

	public Level getLevel(int index) {
		return this.levels.get(index);
	}
}
