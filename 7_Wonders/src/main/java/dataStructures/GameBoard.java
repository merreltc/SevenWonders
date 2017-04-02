package dataStructures;

import java.util.ArrayList;

public class GameBoard {
	private ArrayList<Player> players = new ArrayList<Player>();

	private int numPlayers;

	private int currentPlayerIndex;
	private int nextPlayerIndex;
	private int previousPlayerIndex;
	private int numRotateCounterClockwise = 0;

	public enum Direction {
		CLOCKWISE, COUNTERCLOCKWISE;
	}

	public GameBoard(ArrayList<Player> players) {
		this.numPlayers = players.size();
		this.currentPlayerIndex = 0;
		this.nextPlayerIndex = 1;
		this.previousPlayerIndex = this.numPlayers - 1;
		this.players = players;
	}

	public void changeRotateDirectionAndResetPositions(Direction direction) {
		this.currentPlayerIndex = 0;

		if (direction == Direction.COUNTERCLOCKWISE) {
			this.nextPlayerIndex = this.numPlayers - 1;
			this.previousPlayerIndex = 1;
		}
		if (direction == Direction.CLOCKWISE) {
			this.nextPlayerIndex = 1;
			this.previousPlayerIndex = this.numPlayers - 1;
		}
	}

	public void rotateClockwise() {
		this.previousPlayerIndex = this.currentPlayerIndex;
		this.currentPlayerIndex = this.nextPlayerIndex;
		if (this.nextPlayerIndex == this.numPlayers - 1) {
			this.nextPlayerIndex = 0;
		} else {
			this.nextPlayerIndex++;
		}
	}

	public void rotateCounterClockwise() {
		this.previousPlayerIndex = this.currentPlayerIndex;
		this.currentPlayerIndex = this.nextPlayerIndex;
		if (this.nextPlayerIndex == 0) {
			this.nextPlayerIndex = this.numPlayers - 1;
		} else {
			this.nextPlayerIndex--;
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

	public void setCurrentPlayer(int index) {
		if(index <= -1 || index >= this.numPlayers){
			throw new IllegalArgumentException("Invalid current player index: " + index);
		}
		this.currentPlayerIndex = index;
	}

	public void setNextPlayer(int index) {
		if(index <= -1 || index >= this.numPlayers){
			throw new IllegalArgumentException("Invalid next player index: " + index);
		}
		this.nextPlayerIndex = index;
	}
}
