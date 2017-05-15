package backend;

import dataStructures.Player;
import dataStructures.Wonder.WonderType;

public class PlayerFactory {

	public Player getPlayer(String name) {
		return new Player(name, WonderType.COLOSSUS);
	}
}
