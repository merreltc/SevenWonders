package backend;

import java.util.ArrayList;

import dataStructures.GameBoard;
import dataStructures.Player;
import dataStructures.Wonder;

public class SetUpHandler {

	public ArrayList<Player> setUpAndReturnPlayers(ArrayList<String> playerNames, ArrayList<Wonder> wonders) {
		validatePlayerNum(playerNames.size());
		validateWonderNum(playerNames.size(), wonders.size());
		return createPlayers(playerNames, wonders);
	}

	public boolean validatePlayerNum(int num) {
		if (num < 3 || num > 7) {
			throw new IllegalArgumentException("Cannot play with " + num + " players");
		}

		return true;
	}
	
	public boolean validateWonderNum(int numOfPlayers, int numOfWonders) {
		if (numOfPlayers > numOfWonders) {
			throw new IllegalArgumentException("Cannot have more Wonders than Players");
		}
		
		return true;
	}

	public ArrayList<Player> createPlayers(ArrayList<String> playerNames, ArrayList<Wonder> wonders) {
		ArrayList<Player> players = new ArrayList<Player>();
		
		for(int i = 0; i < playerNames.size(); i++){
			players.add(new Player(playerNames.get(i), wonders.get(i)));
		}
		
		return players;
	}
}
