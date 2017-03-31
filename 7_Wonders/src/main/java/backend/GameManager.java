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
	int playerNum;

	public GameManager(ArrayList<String> playerNames) {
		setUpGame(playerNames);
	}

	public void setUpGame(ArrayList<String> playerNames) {
		playerNum = SetUpHandler.setUp(playerNames).size();
	}

	public void trade(Player from, Player to, int valueToTrade) {
		TradeHandler.tradeFromTo(from, to, valueToTrade);
	}

	public int getNumPlayers() {
		return this.playerNum;
	}

	public GameBoard getGameBoard() {
		return this.board;
	}

	public ArrayList<Player> getPlayers() {
		return this.board.getPlayers();
	}

	public Player getPlayer(int i) {
		return this.board.getPlayers().get(i);
	}

	public int getPlayerCoinTotal(int i) {
		return getPlayer(i).getCoinTotal();
	}
}
