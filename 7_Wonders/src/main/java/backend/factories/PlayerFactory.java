package backend.factories;

import constants.GeneralEnums.GameMode;
import dataStructures.playerData.Player;
import exceptions.NoMorePlayersException;

public class PlayerFactory {
	private int currNumPlayers = 0;
	private WonderFactory wonderFactory;

	public PlayerFactory(GameMode mode) {
		this.wonderFactory = new WonderFactory(mode);
	}

	public Player getPlayer(String name) {
		this.currNumPlayers++;
		validateNewPlayer();
		return new Player(name, this.wonderFactory.getWonder());
	}

	private void validateNewPlayer() {
		if (this.currNumPlayers > 7) {
			throw new NoMorePlayersException("You've created the maximum number of players");
		}
	}
}
