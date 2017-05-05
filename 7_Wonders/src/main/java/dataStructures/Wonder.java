package dataStructures;

import java.util.HashMap;
import java.util.HashSet;

import dataStructures.Wonder.WonderType;

public class Wonder {
	private WonderType type;
	private String name;
	private char side;
	private int numLevels;
	private HashMap<WonderType, String> wonders;

	public enum WonderType {
		COLOSSUS, LIGHTHOUSE, TEMPLE, GARDENS, STATUE, MAUSOLEUM, PYRAMIDS
	}

	public Wonder(char side, WonderType type) {
		this(side, type, 3);
	}

	public Wonder(char side, WonderType type, int numLevels) {
		this.type = type;
		this.name = getWonderNameByType(type);
		this.side = side;
		validateNumLevels(type, numLevels);
		this.numLevels = numLevels;
	}
	
	private void validateNumLevels(WonderType type, int numLevels) {
		if (this.side == 'A') {
			validateNumLevelsSideA(numLevels);
		} else {
			validateNumLevelsSideB(type, numLevels);
		}
	}

	private void validateNumLevelsSideA(int numLevels) {
		if (numLevels <= 2 || numLevels >= 4)
			throw new IllegalArgumentException("Invalid Number of Wonder Levels: " + numLevels);
	}

	private void validateNumLevelsSideB(WonderType type, int numLevels) {
		switch(type) {
		case LIGHTHOUSE:
		case TEMPLE:
		case GARDENS:
		case STATUE:
		case MAUSOLEUM:
			if (numLevels <= 2 || numLevels >= 4) {
				throw new IllegalArgumentException("Invalid Number of Wonder Levels: " + numLevels);
			} 
			break;
		case COLOSSUS:
			if (numLevels <=1 || numLevels >= 3) {
				throw new IllegalArgumentException("Invalid Number of Wonder Levels: " + numLevels);
			}
			break;
		case PYRAMIDS:
			if (numLevels <=3 || numLevels >= 5) {
				throw new IllegalArgumentException("Invalid Number of Wonder Levels: " + numLevels);
			}
			break;
		default:
			throw new IllegalArgumentException("Invalid WonderType: " + type);
		}
	}

	public WonderType getType() {
		return this.type;
	}

	public String getName() {
		return this.name;
	}

	public char getSide() {
		return this.side;
	}

	public int getNumLevels() {
		return this.numLevels;
	}
	
	public static String getWonderNameByType(Wonder.WonderType wonder) {
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

}
