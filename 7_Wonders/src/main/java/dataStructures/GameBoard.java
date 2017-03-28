package dataStructures;

import java.util.ArrayList;

public class GameBoard {
	private int numPlayers = 0;
	
	public GameBoard(){
	}
	
	public GameBoard(ArrayList<Player> players) {
		this.numPlayers = 3;
	}

	public int getNumPlayers(){
		return this.numPlayers;
	}
}
