package backend.handlers;

import java.util.ArrayList;
import java.util.HashMap;

import backend.factories.PlayerFactory;
import dataStructures.gameMaterials.Wonder;
import dataStructures.playerData.Player;

public class SetUpPlayerHandler {
	private PlayerFactory playerFactory;

	public SetUpPlayerHandler() {
		this.playerFactory = new PlayerFactory();
	}

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
		for (String name : playerNames) {
			Player temp = this.playerFactory.getPlayer(name);
			players.add(temp);
		}
		return players;
	}
}
