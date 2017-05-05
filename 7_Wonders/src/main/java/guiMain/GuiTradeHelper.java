package guiMain;

import java.util.ArrayList;

import backend.GameManager;
import dataStructures.GeneralEnums.Resource;
import dataStructures.Player;
import exceptions.InsufficientFundsException;

public class GuiTradeHelper {
	private GameManager gameManager;

	public GuiTradeHelper(GameManager gameManager) {
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
		if (resource.equals("Wood")){
			return Resource.LUMBER;
		}else if (resource.equals("Stone")){
			return Resource.STONE;
		}else if (resource.equals("Ore")){
			return Resource.ORE;
		}
		return Resource.CLAY;
		
	}

}
