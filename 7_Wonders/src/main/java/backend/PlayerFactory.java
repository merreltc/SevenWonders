package backend;

import dataStructures.Player;
import dataStructures.Wonder.WonderType;
import exceptions.NoMorePlayersException;

public class PlayerFactory {
	private int currNumPlayers = 0;

	public Player getPlayer(String name) {
		this.currNumPlayers++;
		
		if(this.currNumPlayers > 7) {
			new NoMorePlayersException("Cannot create more than 8 players");
		}
		
		return new Player(name, WonderType.COLOSSUS);
	}
}
