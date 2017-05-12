package dataStructures;

import dataStructures.GeneralEnums.Resource;

public class Wonder {
	private WonderType type;
	private String name;
	private Resource resource;
	private char side;

	public enum WonderType {
		COLOSSUS, LIGHTHOUSE, TEMPLE, GARDENS, STATUE, MAUSOLEUM, PYRAMIDS
	}

	public Wonder(char side, WonderType type) {
		this.type = type;
		this.name = getNameByType(type);
		this.resource = getResourceByType(type);
		this.side = side;
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

	public char getSide() {
		return this.side;
	}

	public int getNumLevels() {
		return this.getNumLevels(this.side, this.type);
	}

	public int getNumLevels(char side, Wonder.WonderType type) {
		if(side == 'A') {
			return 3;
		} else {
			switch(type) {
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

	public static String getNameByType(Wonder.WonderType wonder) {
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

	public static String getShortNameByType(Wonder.WonderType wonder) {
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
}
