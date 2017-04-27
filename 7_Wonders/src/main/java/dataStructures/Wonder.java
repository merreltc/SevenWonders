package dataStructures;

public class Wonder {
	private char side;
	private int numLevels;

	public Wonder(char side) {
		this.side = side;
	}
	
	public Wonder(char side, int numLevels) {
		this.side = side;
		this.numLevels = numLevels;
	}

	public int getNumLevels() {
		if(side == 'A') {
			return 3;
		}
		return this.numLevels;
	}

	public char getSide() {
		return this.side;
	}

}
