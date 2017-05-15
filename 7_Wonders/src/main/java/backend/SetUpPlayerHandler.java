package backend;

import java.util.ArrayList;
import java.util.HashMap;

import dataStructures.Player;
import dataStructures.Wonder;
import guiDataStructures.PlayerInformationHolder;

public class SetUpPlayerHandler {
	
	public ArrayList<Player> setUpAndReturnPlayers(ArrayList<PlayerInformationHolder> holder) {
		validatePlayerNum(holder.size());
		return createPlayers(holder);
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

	public ArrayList<Player> createPlayers(ArrayList<PlayerInformationHolder> holder) {
		ArrayList<Player> players = new ArrayList<Player>();
		
		for(int i = 0; i < holder.size();i++) {
			PlayerInformationHolder currentHolder = holder.get(i);
			players.add(new Player(currentHolder.playerName, currentHolder.wonderType));
		}
		
		return players;
	}
}
