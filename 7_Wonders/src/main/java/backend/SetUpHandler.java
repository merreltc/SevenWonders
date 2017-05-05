package backend;

import java.util.ArrayList;
import java.util.HashMap;

import dataStructures.Player;
import dataStructures.Wonder;

public class SetUpHandler {

	public ArrayList<Player> setUpAndReturnPlayers(ArrayList<String> playerNames, ArrayList<Wonder.WonderType> wonders) {
		validatePlayerNum(playerNames.size());
		return createPlayers(playerNames, wonders);
	}

	public boolean validatePlayerNum(int num) {
		if (num < 3 || num > 7) {
			throw new IllegalArgumentException("Cannot play with " + num + " players");
		}

		return true;
	}

	public ArrayList<Player> createPlayers(ArrayList<String> playerNames, ArrayList<Wonder.WonderType> wonders) {
		ArrayList<Player> players = new ArrayList<Player>();
		
		for(int i = 0; i < playerNames.size();i++) {
			players.add(new Player(playerNames.get(i), wonders.get(i)));
		}
		
		return players;
	}
}
