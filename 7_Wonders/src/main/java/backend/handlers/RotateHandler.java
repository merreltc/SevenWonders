package backend.handlers;

import java.util.ArrayList;

import dataStructures.GameBoard;
import dataStructures.gameMaterials.Card;
import dataStructures.playerData.Player;

public class RotateHandler {
	private GameBoard board;

	public RotateHandler(GameBoard board) {
		this.board = board;
	}

	public enum Rotation {
		CLOCKWISE, COUNTERCLOCKWISE;
	}

	public void changeRotateDirectionAndResetPositions(Rotation direction) {
		this.board.setCurrentPlayer(0);
		int playerToRight = 1;
		int playerToLeft = this.board.getNumPlayers() - 1;

		if (direction == Rotation.COUNTERCLOCKWISE) {
			this.board.setNextPlayer(playerToLeft);
			this.board.setPreviousPlayer(playerToRight);
		} else {
			this.board.setNextPlayer(playerToRight);
			this.board.setPreviousPlayer(playerToLeft);
		}
	}

	public void rotateClockwise() {
		setCurrentAndPreviousPlayers();
		setNextPlayer(Rotation.CLOCKWISE);
	}

	public void rotateCounterClockwise() {
		setCurrentAndPreviousPlayers();
		setNextPlayer(Rotation.COUNTERCLOCKWISE);
	}

	private void setNextPlayer(Rotation rotateDirection) {
		int tempNextPlayerIndex = this.board.getNextPlayerIndex();
		int lastPlayerIndex = this.board.getNumPlayers() - 1;
		int firstPlayerIndex = 0;

		if (rotateDirection == Rotation.CLOCKWISE) {
			compareToSetNextPlayer(tempNextPlayerIndex, lastPlayerIndex, firstPlayerIndex, tempNextPlayerIndex + 1);
		} else {
			compareToSetNextPlayer(tempNextPlayerIndex, firstPlayerIndex, lastPlayerIndex, tempNextPlayerIndex - 1);
		}
	}
	
	private void compareToSetNextPlayer(int tempNextPlayerIndex, int compareIndex, int equalsCompareIndex,
			int notEqualsCompareIndex) {
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

	public void rotateCurrentHands(ArrayList<Player> players, Rotation direction) {
		if (direction == Rotation.CLOCKWISE) {
			performHandRotationClockwise(players);
		}else{
			performHandRotationCounterClockwise(players);
		}
	}

	private void performHandRotationClockwise(ArrayList<Player> players) {
		ArrayList<Card> previousPlayerHand = players.get(players.size() - 1).getCurrentHand();

		for (int i = 0; i < players.size(); i++) {
			ArrayList<Card> newPreviousPlayerHand = players.get(i).getCurrentHand();
			players.get(i).setCurrentHand(previousPlayerHand);
			previousPlayerHand = newPreviousPlayerHand;
		}
	}
	
	private void performHandRotationCounterClockwise(ArrayList<Player> players) {
		ArrayList<Card> previousPlayerHand = players.get(0).getCurrentHand();

		for (int i = players.size() - 1; i >= 0; i--) {
			ArrayList<Card> newPreviousPlayerHand = players.get(i).getCurrentHand();
			players.get(i).setCurrentHand(previousPlayerHand);
			previousPlayerHand = newPreviousPlayerHand;
		}
	}

}
