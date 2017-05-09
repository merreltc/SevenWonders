package backend;

import java.util.ArrayList;
import java.util.HashMap;

import dataStructures.Player;
import dataStructures.Wonder;
import dataStructures.Wonder.WonderType;

public class SetUpHandler {

	public ArrayList<Player> setUpAndReturnPlayers(HashMap<String, Wonder.WonderType> playerNamesAndWonders) {
		int count = 0;
		for(String name : playerNamesAndWonders.keySet()) {
			if(playerNamesAndWonders.get(name) == Wonder.WonderType.COLOSSUS) {
				count++;
			}
			if(count > 1) {
				throw new IllegalArgumentException("Cannot assign wonder (Colossus) to multiple players");
			}
		}
		validatePlayerNum(playerNamesAndWonders.size());
		return createPlayers(playerNamesAndWonders);
	}

	public boolean validatePlayerNum(int num) {
		if (num < 3 || num > 7) {
			throw new IllegalArgumentException("Cannot play with " + num + " players");
		}

		return true;
	}

	public ArrayList<Player> createPlayers(HashMap<String, Wonder.WonderType> playerNamesAndWonders) {
		ArrayList<Player> players = new ArrayList<Player>();
		
		for(String playerName : playerNamesAndWonders.keySet()) {
			players.add(new Player(playerName, playerNamesAndWonders.get(playerName)));
		}
		
		return players;
	}
}
