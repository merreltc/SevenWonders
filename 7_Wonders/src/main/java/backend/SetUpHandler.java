package backend;

import java.util.ArrayList;

import dataStructures.GameBoard;
import dataStructures.Player;

public class SetUpHandler {
	private static int playerNum;

	public static GameBoard setUp(int numPlayers) {
		setPlayerNum(numPlayers);
		return createDefaultGameBoard();
	}

	public static void setPlayerNum(int num) {
		if (num < 3 || num > 7) {
			throw new IllegalArgumentException("Cannot play with " + num + " players");
		}

		playerNum = num;
	}

	public static GameBoard createDefaultGameBoard() {
		ArrayList<Player> players = new ArrayList<Player>();

		for (int i = 0; i < playerNum; i++) {
			players.add(new Player());
		}

		return new GameBoard(players);
	}

	// TODO: get rid of this function. Not necessary - testing purposes only
	public static int getPlayerNum() {
		return playerNum;
	}
}
