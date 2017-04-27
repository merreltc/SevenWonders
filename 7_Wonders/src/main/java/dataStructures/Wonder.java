package dataStructures;

public class Wonder {
	private char side;
	private int numLevels;

	public Wonder(char side) {
		this(side, 3);
	}
	
	public Wonder(char side, int numLevels) {
		this.side = side;
		validateNumLevelsGeneral(numLevels);

		this.numLevels = numLevels;
	}

	private void validateNumLevelsGeneral(int numLevels) {
		if(this.side == 'A' && (numLevels == 2 || numLevels == 4))
			throw new IllegalArgumentException("Invalid Number of Wonder Levels: " + numLevels);
		if(numLevels <= 1 || numLevels >= 5) {
			throw new IllegalArgumentException("Invalid Number of Wonder Levels: " + numLevels);
		}
	}

	public int getNumLevels() {
		return this.numLevels;
	}

	public char getSide() {
		return this.side;
	}

}
