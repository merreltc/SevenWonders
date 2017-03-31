package dataStructures;

import java.util.ArrayList;

public class GameBoard {
	private int numPlayers = 3;
	private ArrayList<Player> players = new ArrayList<Player>();
	
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

	public Player getPlayer(int index) {
		return this.players.get(index);
	}

	public int getPlayerCoinTotal(int index) {
		return this.getPlayer(index).getCoinTotal();
	}

	public Player getCurrentPlayer() {
		return this.players.get(0);
	}

	public Player getNextPlayer() {
		return this.players.get(1);
	}

	public Player getPreviousPlayer() {
		return this.players.get(2);
	}

}
