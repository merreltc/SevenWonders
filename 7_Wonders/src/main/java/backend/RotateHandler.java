package backend;

import java.util.ArrayList;

import dataStructures.Card;
import dataStructures.GameBoard;
import dataStructures.Player;

public class RotateHandler {
	private GameBoard board;
	
	public RotateHandler(GameBoard board) {
		this.board = board;
	}
	
	public enum Direction {
		CLOCKWISE, COUNTERCLOCKWISE;
	}

	public void changeRotateDirectionAndResetPositions(Direction direction) {
		this.board.setCurrentPlayer(0);
		int playerToRight = 1;
		int playerToLeft = this.board.getNumPlayers() - 1;

		if (direction == Direction.COUNTERCLOCKWISE) {
			this.board.setNextPlayer(playerToLeft);
			this.board.setPreviousPlayer(playerToRight);
		} else {
			this.board.setNextPlayer(playerToRight);
			this.board.setPreviousPlayer(playerToLeft);
		}
	}

	public void rotateClockwise() {
		setCurrentAndPreviousPlayers();
		setNextPlayer(Direction.CLOCKWISE);
	}

	public void rotateCounterClockwise() {
		setCurrentAndPreviousPlayers();
		setNextPlayer(Direction.COUNTERCLOCKWISE);
	}

	private void setNextPlayer(Direction rotateDirection) {
		int tempNextPlayerIndex = this.board.getNextPlayerIndex();
		int lastPlayerIndex = this.board.getNumPlayers() - 1;
		int firstPlayerIndex = 0;
		int compareIndex;
		int equalsCompareIndex;
		int notEqualsCompareIndex;
		
		if(rotateDirection == Direction.CLOCKWISE){
			compareIndex = lastPlayerIndex;
			equalsCompareIndex = firstPlayerIndex;
			notEqualsCompareIndex = tempNextPlayerIndex + 1;
		} else{
			compareIndex = firstPlayerIndex;
			equalsCompareIndex = lastPlayerIndex;
			notEqualsCompareIndex = tempNextPlayerIndex - 1;
		}
		
		if (tempNextPlayerIndex == compareIndex) {
			this.board.setNextPlayer(equalsCompareIndex);
		} else {
			this.board.setNextPlayer(notEqualsCompareIndex);
		}
		
	}

	private void setCurrentAndPreviousPlayers() {
		this.board.setPreviousPlayer(this.board.getCurrentPlayerIndex());
		this.board.setCurrentPlayer(this.board.getNextPlayerIndex());
	}

	public void rotateCurrentHands(ArrayList<Player> players) {
		ArrayList<Card> previousPlayerHand = players.get(players.size() - 1).getCurrentHand();
		
		for(int i = 0; i<players.size(); i++){
			ArrayList<Card> newPreviousPlayerHand = players.get(i).getCurrentHand();
			players.get(i).setCurrentHand(previousPlayerHand);
			previousPlayerHand = newPreviousPlayerHand;
		}
	}
}
