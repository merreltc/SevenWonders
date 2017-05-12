package backend;

import java.util.ArrayList;

import dataStructures.Player;
import guiDataStructures.PlayerInformationHolder;

public class SetUpHandler {

	public ArrayList<Player> setUpAndReturnPlayers(ArrayList<PlayerInformationHolder> holder) {
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
