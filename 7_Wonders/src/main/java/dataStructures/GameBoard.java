package dataStructures;

import java.util.ArrayList;

public class GameBoard {
	private int numPlayers = 3;
	private ArrayList<Player> players = new ArrayList<Player>();
	
	public GameBoard(){
	}
	
	public GameBoard(ArrayList<Player> players) {
		this.numPlayers = players.size();
		this.players = players;
	}

	public int getNumPlayers(){
		return this.numPlayers;
	}

	public ArrayList<Player> getPlayers() {
		return this.players;
	}
}
