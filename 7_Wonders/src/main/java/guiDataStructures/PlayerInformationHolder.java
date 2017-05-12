package guiDataStructures;

import dataStructures.Player;
import dataStructures.Wonder.WonderType;

public class PlayerInformationHolder {

	public String playerName;
	public WonderType wonderType;
	public char side;
	
	public PlayerInformationHolder(String playerName, WonderType wonderType, char side){
		this.playerName = playerName;
		this.wonderType = wonderType;
		this.side = side;
	}
	
}
