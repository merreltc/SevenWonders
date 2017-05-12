package backend;

import java.util.ArrayList;
import java.util.HashMap;

import dataStructures.Player;
import dataStructures.Wonder;

public class SetUpPlayerHandler {

	public ArrayList<Player> setUpAndReturnPlayers(ArrayList<String> playerNames, ArrayList<Wonder.WonderType> wonders) {
		verifyNoDuplicateWonders(wonders);
		validatePlayerNum(playerNames.size());
		return createPlayers(playerNames, wonders);
	}

	private void verifyNoDuplicateWonders(ArrayList<Wonder.WonderType> wonders) {
		HashMap<Wonder.WonderType, Integer> wonderFrequency = getWonderFrequencyMap(wonders);
		checkForDuplicateWonders(wonderFrequency);
	}
	
	private HashMap<Wonder.WonderType, Integer> getWonderFrequencyMap(ArrayList<Wonder.WonderType> wonders) {
		HashMap<Wonder.WonderType, Integer> result = new HashMap<Wonder.WonderType, Integer>();
		for(Wonder.WonderType wonder : wonders) {
			if(!result.containsKey(wonder)) {
				result.put(wonder, 1);
			} else {
				int tempVal = result.get(wonder);
				result.put(wonder, tempVal + 1);
			}
		}
		return result;
	}
	
	private void checkForDuplicateWonders(HashMap<Wonder.WonderType, Integer> wonderFrequency) {
		for(Wonder.WonderType wonder : wonderFrequency.keySet()) {
			if (wonderFrequency.get(wonder) > 1) {
				throw new IllegalArgumentException("Cannot assign wonder(s) to multiple players");
			}
		}
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
