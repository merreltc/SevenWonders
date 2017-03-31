package backend;

import java.util.ArrayList;

import dataStructures.GameBoard;
import dataStructures.Player;

public class SetUpHandler {
	private static int playerNum;

	public static ArrayList<String> setUp(ArrayList<String> playerNames) {
		validatePlayerNum(playerNames.size());
		return playerNames;
	}

	public static boolean validatePlayerNum(int num) {
		if (num < 3 || num > 7) {
			throw new IllegalArgumentException("Cannot play with " + num + " players");
		}
		
		playerNum = num;

		return true;
	}

	public static GameBoard createDefaultGameBoard() {
		ArrayList<Player> players = new ArrayList<Player>();

		for (int i = 0; i < playerNum; i++) {
			players.add(new Player());
		}

		return new GameBoard(players);
	}
}
