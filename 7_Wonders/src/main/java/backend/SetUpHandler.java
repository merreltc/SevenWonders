package backend;

import dataStructures.GameBoard;

public class SetUpHandler {
	private int playerNum;

	public GameBoard setUp(int numPlayers) {
		setPlayerNum(numPlayers);
		return createGameBoard();
	}

	public void setPlayerNum(int num) {
		if (num < 3 || num > 7)
			throw new IllegalArgumentException("Cannot play with " + num + " players");
		playerNum = num;

	}

	public GameBoard createGameBoard() {
		return new GameBoard();
	}

	public int getPlayerNum() {
		return this.playerNum;
	}
}
