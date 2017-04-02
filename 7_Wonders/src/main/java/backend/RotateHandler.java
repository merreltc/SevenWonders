package backend;

import dataStructures.GameBoard;
import dataStructures.GameBoard.Direction;

public class RotateHandler {
	private GameBoard board;
	
	public RotateHandler(GameBoard board) {
		this.board = board;
	}

	public void changeRotateDirectionAndResetPositions(Direction direction) {
		this.board.setCurrentPlayer(0);
		int playerToRight = 1;
		int playerToLeft = this.board.getNumPlayers() - 1;

		if (direction == Direction.COUNTERCLOCKWISE) {
			this.board.setNextPlayer(playerToLeft);
			this.board.setPreviousPlayer(playerToRight);
		} else if (direction == Direction.CLOCKWISE) {
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
}
