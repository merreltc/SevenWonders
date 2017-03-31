package dataStructures;

import java.util.ArrayList;

public class GameBoard {
	private ArrayList<Player> players = new ArrayList<Player>();
	
	private int numPlayers;
	
	private int currentPlayerIndex;
	private int nextPlayerIndex;
	private int previousPlayerIndex;
	private int numClockwiseRotates = 0;

	public GameBoard(ArrayList<Player> players) {
		this.numPlayers = players.size();
		this.currentPlayerIndex = 0;
		this.nextPlayerIndex = 1;
		this.previousPlayerIndex = this.numPlayers - 1;
		this.players = players;
	}

	public void rotateClockwise() {
		int currClockwiseRotates = ++this.numClockwiseRotates;
		
		if (currClockwiseRotates == 1) {
			this.currentPlayerIndex = 1;
			this.nextPlayerIndex = 2;
			this.previousPlayerIndex = 0;
		} else if (currClockwiseRotates == 2){
			this.currentPlayerIndex = 2;
			this.previousPlayerIndex = 1;
		}else {
			this.currentPlayerIndex = 0;
			this.previousPlayerIndex = 4;
		}

	}

	public int getNumPlayers() {
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
		return this.getPlayer(this.currentPlayerIndex);
	}

	public Player getNextPlayer() {
		return this.getPlayer(this.nextPlayerIndex);
	}

	public Player getPreviousPlayer() {
		return this.getPlayer(this.previousPlayerIndex);
	}
}
