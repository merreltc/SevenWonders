package backend;

import java.util.ArrayList;

import dataStructures.GameBoard;
import dataStructures.Player;

/**
 * Controls the actions of the game and delegates those responsibilities to
 * different classes
 *
 */
public class GameManager {
	private GameBoard board;
	ArrayList<String> playerNames;

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

	public int getNumPlayers() {
		return this.playerNames.size();
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
}
