package main;

import java.util.ResourceBundle;

import backend.GameManager;
import constants.GeneralEnums;
import constants.GeneralEnums.Good;
import constants.GeneralEnums.RawResource;
import constants.GeneralEnums.Resource;
import dataStructures.playerData.Player;
import utils.Message;
import utils.Translate;

public class TradeHelper {
	private GameManager gameManager;
	private ResourceBundle messages = Translate.getNewResourceBundle();

	public TradeHelper(GameManager gameManager) {
		this.gameManager = gameManager;
	}

	public void trade(String[] splitValue) {
		String resource = splitValue[1];
		Enum resourceEnum = translateStringToEnum(resource);
		Player tradeFrom = this.gameManager.getCurrentPlayer();
		int currentIndex = this.gameManager.getPlayers().indexOf(tradeFrom);
		Player tradeTo = decodeTradeButton(splitValue, currentIndex);
		try {
			this.gameManager.tradeForEntity(tradeFrom, tradeTo, resourceEnum);
			Message.showMessage(this.messages.getString("tradeSuccess"));
		} catch (Exception e) {
			Message.showMessage(e.getMessage());
		}
	}

	private Player decodeTradeButton(String[] splitValue, int currentIndex) {
		Player tradeTo;
		if (splitValue[0].equals("Right")) {
			int offsetRight = (currentIndex + 1 + this.gameManager.getNumPlayers());
			tradeTo = this.gameManager.getPlayer(offsetRight % this.gameManager.getNumPlayers());
		} else {
			int offsetLeft = (currentIndex - 1 + this.gameManager.getNumPlayers());
			tradeTo = this.gameManager.getPlayer(offsetLeft % this.gameManager.getNumPlayers());
		}
		return tradeTo;
	}

	private Enum translateStringToEnum(String resource) {
		if (resource.equals("Lumber")) {
			return RawResource.LUMBER;
		} else if (resource.equals("Stone")) {
			return RawResource.STONE;
		} else if (resource.equals("Ore")) {
			return RawResource.ORE;
		} else if (resource.equals("Clay")){
			return RawResource.CLAY;
		}else if (resource.equals("loom symbol")){
			return GeneralEnums.Good.LOOM;
		}else if (resource.equals("press symbol")){
			return Good.PRESS;
		}
		return Good.GLASS;
	}

}
