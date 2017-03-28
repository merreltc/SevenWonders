package backend;

import dataStructures.GameBoard;

public class GameManager {
	private GameBoard board;
	
	public GameManager(int numPlayers){
		setUpGame(numPlayers);
	}
	
	public void setUpGame(int numPlayers) {
		this.board = SetUpHandler.setUp(numPlayers);
	}
	
	public int getNumPlayers() {
		return this.board.getNumPlayers();
	}

	public GameBoard getGameBoard() {
		return this.board;
	}
}
