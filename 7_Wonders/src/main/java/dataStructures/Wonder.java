package dataStructures;

import java.util.HashMap;

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
		setUpTypeMap();
		this.type = type;
		this.name = wonders.get(type);
		this.side = side;
		validateNumLevelsGeneral(numLevels);
		this.numLevels = numLevels;
	}

	private void setUpTypeMap() {
		this.wonders = new HashMap<WonderType, String>();
		wonders.put(WonderType.COLOSSUS, "The Colossus of Rhodes");
		wonders.put(WonderType.LIGHTHOUSE, "The Lighthouse of Alexandria");
		wonders.put(WonderType.TEMPLE, "The Temple of Artemis in Ephesus");
		wonders.put(WonderType.GARDENS, "The Hanging Gardens of Babylon");
		wonders.put(WonderType.STATUE, "The Statue of Zeus in Olympia");
		wonders.put(WonderType.MAUSOLEUM, "The Mausoleum of Halicarnassus");
		wonders.put(WonderType.PYRAMIDS, "The Pyramids of Gize");
	}

	private void validateNumLevelsGeneral(int numLevels) {
		if (this.side == 'A') {
			validateNumLevelsSideA(numLevels);
		} else {
			validateNumLevelsSideB(numLevels);
		}
	}

	private void validateNumLevelsSideB(int numLevels) {
		if (numLevels <= 1 || numLevels >= 5) {
			throw new IllegalArgumentException("Invalid Number of Wonder Levels: " + numLevels);
		}
	}

	private void validateNumLevelsSideA(int numLevels) {
		if (numLevels <= 2 || numLevels >= 4)
			throw new IllegalArgumentException("Invalid Number of Wonder Levels: " + numLevels);
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

}
