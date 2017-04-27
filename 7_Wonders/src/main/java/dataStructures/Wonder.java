package dataStructures;

public class Wonder {
	private char side;

	public Wonder(char side) {
		this.side = side;
	}

	public int getNumLevels() {
		if(side == 'A') {
			return 3;
		}
		return 2;
	}

	public char getSide() {
		return this.side;
	}

}
