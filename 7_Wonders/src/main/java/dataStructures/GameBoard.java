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
		if (direction == Direction.COUNTERCLOCKWISE) {
			this.currentPlayerIndex = 0;
			this.nextPlayerIndex = this.previousPlayerIndex;
			this.previousPlayerIndex = 1;
		} if (direction == Direction.CLOCKWISE) {
			this.currentPlayerIndex = 0;
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
		int currNumCCRotates = ++this.numRotateCounterClockwise;

		if (currNumCCRotates == 1) {
			if (this.numPlayers == 3) {
				this.currentPlayerIndex = 2;
				this.nextPlayerIndex = 1;
				this.previousPlayerIndex = 0;
			} else if (this.numPlayers == 7) {
				this.currentPlayerIndex = 6;
				this.nextPlayerIndex = 5;
				this.previousPlayerIndex = 0;
			} else {
				this.currentPlayerIndex = 4;
				this.nextPlayerIndex = 3;
				this.previousPlayerIndex = 0;
			}
		} else if (currNumCCRotates == 2) {
			this.currentPlayerIndex = 3;
			this.nextPlayerIndex = 2;
			this.previousPlayerIndex = 4;
		} else {
			this.currentPlayerIndex = 0;
			this.nextPlayerIndex = 4;
			this.previousPlayerIndex = 1;
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
