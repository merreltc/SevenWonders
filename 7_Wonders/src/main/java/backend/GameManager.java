package backend;

import java.util.ArrayList;

import dataStructures.GameBoard;
import dataStructures.GameBoard.Direction;
import dataStructures.Player;

/**
 * Controls the actions of the game and delegates those responsibilities to
 * different classes
 *
 */
public class GameManager {
	private GameBoard board;

	public GameManager(ArrayList<String> playerNames) {
		setUpGame(playerNames);

	}

	public void setUpGame(ArrayList<String> playerNames) {
		ArrayList<Player> players = SetUpHandler.setUpHandler.setUpAndReturnPlayers(playerNames);
		this.board = new GameBoard(players);
	}

	public void trade(Player from, Player to, int valueToTrade) {
		TradeHandler.tradeFromTo(from, to, valueToTrade);
	}
	
	public void changeRotateDirectionAndResetPositions(String direction) {
		if(direction.equals("Clockwise")){
			this.board.changeRotateDirectionAndResetPositions(Direction.CLOCKWISE);
		}else {
			this.board.changeRotateDirectionAndResetPositions(Direction.COUNTERCLOCKWISE);
		}
	}
	
	public void rotateClockwise() {
		this.board.rotateClockwise();
	}
	
	public void rotateCounterClockwise() {
		this.board.rotateCounterClockwise();
	}

	public int getNumPlayers() {
		return this.board.getNumPlayers();
	}

	public GameBoard getGameBoard() {
		return this.board;
	}

	public ArrayList<Player> getPlayers() {
		return this.board.getPlayers();
	}

	public Player getPlayer(int index) {
		return this.board.getPlayer(index);
	}

	public int getPlayerCoinTotal(int index) {
		return this.board.getPlayerCoinTotal(index);
	}

	public Player getCurrentPlayer() {
		return this.board.getCurrentPlayer();
	}

	public Player getNextPlayer() {
		return this.board.getNextPlayer();
	}

	public Player getPreviousPlayer() {
		return this.board.getPreviousPlayer();
	}
}
