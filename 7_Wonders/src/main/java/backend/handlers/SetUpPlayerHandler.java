package backend.handlers;

import java.util.ArrayList;

import backend.factories.PlayerFactory;
import constants.GeneralEnums.GameMode;
import dataStructures.playerData.Player;

public class SetUpPlayerHandler {
	private PlayerFactory playerFactory;

	public SetUpPlayerHandler(GameMode mode) {
		this.playerFactory = new PlayerFactory(mode);
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
