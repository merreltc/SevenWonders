package backend;

import java.util.ArrayList;
import java.util.HashMap;

import dataStructures.Player;
import dataStructures.Wonder;
import guiDataStructures.PlayerInformationHolder;

public class SetUpHandler {

	public ArrayList<Player> setUpAndReturnPlayers(ArrayList<PlayerInformationHolder> holder) {
//		HashMap<Wonder.WonderType, Integer> wonderCounts = new HashMap<Wonder.WonderType, Integer>();
//		for(String name : playerNamesAndWonders.keySet()) {
//			Wonder.WonderType type = playerNamesAndWonders.get(name);
//			int currCount = wonderCounts.containsKey(type) ? wonderCounts.get(type) : 0;
//			wonderCounts.put(type, currCount + 1);
//		}
//		
//		for(Wonder.WonderType type : wonderCounts.keySet()) {
//			if(wonderCounts.get(type) > 1) {
//				throw new IllegalArgumentException("Cannot assign wonder(s) to multiple players");
//			}
//		}
//		
		validatePlayerNum(holder.size());
		return createPlayers(holder);
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
