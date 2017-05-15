package backend;

import dataStructures.Player;
import dataStructures.Wonder.WonderType;
import exceptions.NoMorePlayersException;

public class PlayerFactory {
	private int currNumPlayers = 0;

	public Player getPlayer(String name) {
		this.currNumPlayers++;
		validateNewPlayer();
		return new Player(name, WonderType.COLOSSUS);
	}

	private void validateNewPlayer() {
		PlayerFactory factory = new PlayerFactory();
		if(this.currNumPlayers > 7) {
			throw new NoMorePlayersException("You've created the maximum number of players");
		}
	}
}
