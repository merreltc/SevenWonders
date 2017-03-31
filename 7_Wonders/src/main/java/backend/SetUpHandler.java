package backend;

import java.util.ArrayList;

import dataStructures.GameBoard;
import dataStructures.Player;

public class SetUpHandler {
	
	public static SetUpHandler setUpHandler = new SetUpHandler();

	public ArrayList<Player> setUpAndReturnPlayers(ArrayList<String> playerNames) {
		validatePlayerNum(playerNames.size());
		return createPlayers(playerNames);
	}

	public boolean validatePlayerNum(int num) {
		if (num < 3 || num > 7) {
			throw new IllegalArgumentException("Cannot play with " + num + " players");
		}

		return true;
	}

	public ArrayList<Player> createPlayers(ArrayList<String> playerNames) {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine"));
		players.add(new Player("Captain America"));
		players.add(new Player("Black Widow"));
		
		if(playerNames.size() == 7) {
			players.add(new Player("Hulk"));
			players.add(new Player("Iron Man"));
			players.add(new Player("Spider Man"));
			players.add(new Player("Thor"));
		}
		
		return players;
	}
}
