package dataStructures;

import java.util.Locale;
import java.util.ResourceBundle;

import dataStructures.GeneralEnums.Resource;

public class Wonder {
	private WonderType type;
	private String name;
	private Resource resource;
	private char side;
	private int numLevels;
	ResourceBundle messages = ResourceBundle.getBundle("message", Locale.getDefault());

	public enum WonderType {
		COLOSSUS, LIGHTHOUSE, TEMPLE, GARDENS, STATUE, MAUSOLEUM, PYRAMIDS
	}

	public Wonder(char side, WonderType type) {
		this(side, type, 3);
	}

	public Wonder(char side, WonderType type, int numLevels) {
		this.type = type;
		this.name = getNameByType(type);
		this.resource = getResourceByType(type);
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
	
	public Resource getResource() {
		return this.resource;
	}

	public char getSide() {
		return this.side;
	}

	public int getNumLevels() {
		return this.numLevels;
	}
	
	public static String getNameByType(Wonder.WonderType wonder) {
		switch (wonder) {
		case COLOSSUS:
			return  "The Colossus of Rhodes";
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
	
	private String prepareString(String str){
		return str.replaceAll(" ", "");
		
	}
}
