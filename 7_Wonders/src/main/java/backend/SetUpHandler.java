package backend;

import java.util.ArrayList;

import dataStructures.GameBoard;
import dataStructures.Player;

public class SetUpHandler {
	
	public static SetUpHandler setUpHandler = new SetUpHandler();

	public ArrayList<Player> setUpAndReturnPlayers(ArrayList<String> playerNames) {
		validatePlayerNum(playerNames.size());
		return new ArrayList<Player>();
	}

	public boolean validatePlayerNum(int num) {
		if (num < 3 || num > 7) {
			throw new IllegalArgumentException("Cannot play with " + num + " players");
		}

		return true;
	}
}
