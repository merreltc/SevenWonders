package main;

import java.util.ArrayList;

import backend.GameManager;
import constants.GeneralEnums.Resource;
import dataStructures.playerData.Player;
import exceptions.InsufficientFundsException;
import utils.Message;

public class TradeHelper {
	private GameManager gameManager;

	public TradeHelper(GameManager gameManager) {
		this.gameManager = gameManager;
	}

	public void trade(String[] splitValue) {
		String resource = splitValue[1];
		Enum resourceEnum = translateStringToEnum(resource);
		Player tradeTo;
		Player tradeFrom = this.gameManager.getCurrentPlayer();
		int currentIndex = this.gameManager.getPlayers().indexOf(tradeFrom);
		
		if (splitValue[0].equals("Right")) {
			int offsetRight = (currentIndex - 1 + this.gameManager.getNumPlayers());
			tradeTo = this.gameManager.getPlayer(offsetRight%this.gameManager.getNumPlayers());
		} else {
			int offsetLeft = (currentIndex + 1 + this.gameManager.getNumPlayers());
			tradeTo = this.gameManager.getPlayer(offsetLeft%this.gameManager.getNumPlayers());
		}

		try {
			this.gameManager.tradeForEntity(tradeFrom, tradeTo, resourceEnum);
		} catch (Exception e) {
			Message.showMessage(e.getMessage());
		}
	}
	
	private Enum translateStringToEnum(String resource){
		if (resource.equals("Lumber")){
			return Resource.LUMBER;
		}else if (resource.equals("Stone")){
			return Resource.STONE;
		}else if (resource.equals("Ore")){
			return Resource.ORE;
		}
		return Resource.CLAY;
		
	}

}
