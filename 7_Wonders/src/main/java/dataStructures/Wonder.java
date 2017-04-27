package dataStructures;

public class Wonder {
	private String name;
	private char side;
	private int numLevels;

	public Wonder(char side) {
		this(side, "Default", 3);
	}
	
	public Wonder(char side, String name) {
		this(side, name, 3);
	}

	public Wonder(char side, int numLevels) {
		this(side, "Default", numLevels);
	}
	
	public Wonder(char side, String name, int numLevels) {
		this.name = name;
		this.side = side;
		validateNumLevelsGeneral(numLevels);

		this.numLevels = numLevels;
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

	public int getNumLevels() {
		return this.numLevels;
	}

	public char getSide() {
		return this.side;
	}

	public String getName() {
		return this.name;
	}

}
