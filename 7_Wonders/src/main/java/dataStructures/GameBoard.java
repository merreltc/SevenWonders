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
		return this.getPlayer(0);
	}

	public Player getNextPlayer() {
		return this.getPlayer(1);
	}

	public Player getPreviousPlayer() {
		int previousPlayerIndex = this.players.size() - 1;
		return this.getPlayer(previousPlayerIndex);
	}

}
