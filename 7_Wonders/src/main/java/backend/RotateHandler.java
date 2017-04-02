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
		this.board.setPreviousPlayer(this.board.getCurrentPlayerIndex());
		this.board.setCurrentPlayer(this.board.getNextPlayerIndex());
		
		int tempNextPlayerIndex = this.board.getNextPlayerIndex();
		int lastPlayerIndex = this.board.getNumPlayers() - 1;
		int firstPlayerIndex = 0;
		
		if (tempNextPlayerIndex == lastPlayerIndex) {
			this.board.setNextPlayer(firstPlayerIndex);
		} else {
			this.board.setNextPlayer(++tempNextPlayerIndex);
		}
	}

	public void rotateCounterClockwise() {
		this.board.setPreviousPlayer(this.board.getCurrentPlayerIndex());
		this.board.setCurrentPlayer(this.board.getNextPlayerIndex());
		
		int tempNextPlayerIndex = this.board.getNextPlayerIndex();
		int lastPlayerIndex = this.board.getNumPlayers() - 1;
		int firstPlayerIndex = 0;
		
		if (tempNextPlayerIndex == firstPlayerIndex) {
			this.board.setNextPlayer(lastPlayerIndex);
		} else {
			this.board.setNextPlayer(--tempNextPlayerIndex);
		}
	}
}
