package dataStructures;

import java.util.ArrayList;

public class GameBoard {
	private int numPlayers = 0;
	
	public GameBoard(){
	}
	
	public GameBoard(ArrayList<Player> players) {
		this.numPlayers = players.size();
	}

	public int getNumPlayers(){
		return this.numPlayers;
	}
}
